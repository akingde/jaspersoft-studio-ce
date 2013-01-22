/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.community.wizards;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.community.JSSCommunityActivator;
import com.jaspersoft.studio.community.RESTCommunityHelper;
import com.jaspersoft.studio.community.messages.Messages;
import com.jaspersoft.studio.community.requests.IssueRequest;
import com.jaspersoft.studio.community.utils.CommunityAPIException;
import com.jaspersoft.studio.community.utils.CommunityAPIUtils;
import com.jaspersoft.studio.community.utils.CommunityUser;
import com.jaspersoft.studio.community.zip.ZipEntry;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Wizard for the issue creation.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class IssueCreationWizard extends Wizard {

	private IssueAttachmentDetailsPage page1;
	private NewIssueDetailsPage page2;
	private NewIssueAuthenticationPage page3;
	private boolean isPublished;

	public IssueCreationWizard() {
		setWindowTitle(Messages.IssueCreationWizard_Title);
	}

	@Override
	public void addPages() {
		page1 = new IssueAttachmentDetailsPage();
		addPage(page1);
		page2 = new NewIssueDetailsPage();
		addPage(page2);
		page3 = new NewIssueAuthenticationPage();
		addPage(page3);
	}

	@Override
	public boolean performFinish() {
		// List of entries for the final zip attachment
		final List<ZipEntry> zipEntries = page1.getZipEntries();
		// Issue request that still needs the attachment file id
		final IssueRequest issueRequest = page2.getIssueRequest();
		// Authentication information
		final CommunityUser authInfo = page3.getCommunityUserInformation();
		// Let's save credentials if required		
		if(page3.shouldSaveCredentials()){
			JSSCommunityActivator.getDefault().storeCommunityUserInformation(authInfo);
		}
		// Tries to save issue
		try {
			getContainer().run(true, false, new IRunnableWithProgress() {
				@Override
				public void run(IProgressMonitor monitor) throws InvocationTargetException,
						InterruptedException {
					monitor.beginTask(Messages.IssueCreationWizard_TaskName, IProgressMonitor.UNKNOWN);
					isPublished = publishNewIssue(issueRequest,zipEntries,authInfo);
					monitor.done();
					
				}
			});
		} catch (Exception e) {
			UIUtils.showError(e);
		}
		return isPublished;
	}

	private boolean publishNewIssue(IssueRequest issueRequest,
			List<ZipEntry> zipEntries, CommunityUser authInfo) {
		HttpClient client = new HttpClient();
		try {
			// Gets the authentication cookie
			Cookie authCookie = 
					RESTCommunityHelper.getAuthenticationCookie(client, authInfo.getUsername(), authInfo.getPassword());
			
			// Create the attachment file if any
			List<String> attachmentsIDs = new ArrayList<String>();
			if(!zipEntries.isEmpty()){
				File zipAttachment = CommunityAPIUtils.createZipFile(zipEntries);
				String fileID = RESTCommunityHelper.uploadFile(client, zipAttachment, authCookie);
				attachmentsIDs.add(fileID);
			}
			
			// Publish the issue to the community tracker
			RESTCommunityHelper.createNewIssue(client, issueRequest, attachmentsIDs, authCookie);
						
		} catch (CommunityAPIException e) {
			UIUtils.showError(e);
			return false;
		}
		return true;
	}

}
