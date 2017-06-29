/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.community.wizards;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.viewer.BrowserUtils;

import org.apache.http.client.CookieStore;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.community.JSSCommunityActivator;
import com.jaspersoft.studio.community.RESTCommunityHelper;
import com.jaspersoft.studio.community.messages.Messages;
import com.jaspersoft.studio.community.requests.IssueRequest;
import com.jaspersoft.studio.community.utils.CommunityAPIException;
import com.jaspersoft.studio.community.utils.CommunityAPIUtils;
import com.jaspersoft.studio.community.utils.CommunityUser;
import com.jaspersoft.studio.community.zip.ZipEntry;
import com.jaspersoft.studio.utils.jobs.CheckedRunnableWithProgress;

/**
 * Wizard for the issue creation.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
@SuppressWarnings("restriction")
public final class IssueCreationWizard extends Wizard {

	private static IssueCreationWizard instance = null;
	private NewIssueDetailsPage page1;
	private IssueAttachmentDetailsPage page2;
	private NewIssueAuthenticationPage page3;
	private boolean isPublished;
	private String issuePath;
	private CloseableHttpClient httpclient = null;

	private IssueCreationWizard() {
		setWindowTitle(Messages.IssueCreationWizard_Title);
	}

	@Override
	public void addPages() {
		page1 = new NewIssueDetailsPage();
		addPage(page1);
		page2 = new IssueAttachmentDetailsPage();
		addPage(page2);
		page3 = new NewIssueAuthenticationPage();
		addPage(page3);
	}

	@Override
	public boolean performFinish() {
		// List of entries for the final zip attachment
		final List<ZipEntry> zipEntries = page2.getZipEntries();
		// Issue request that still needs the attachment file id
		final IssueRequest issueRequest = page1.getIssueRequest();
		// Authentication information
		final CommunityUser authInfo = page3.getCommunityUserInformation();
		// Let's save credentials if required
		if (page3.shouldSaveCredentials()) {
			JSSCommunityActivator.getDefault().storeCommunityUserInformation(
					authInfo);
		}
		// Tries to save issue
		try {
			getContainer().run(true, true, new CheckedRunnableWithProgress() {
				
				@Override
				protected void runOperations(IProgressMonitor monitor) {
					monitor.beginTask(Messages.IssueCreationWizard_TaskName,
							IProgressMonitor.UNKNOWN);
					isPublished = publishNewIssue(issueRequest, zipEntries,	authInfo, monitor);
					monitor.done();
				}
				
				@Override
				protected void abortOperationInvoked() {
					HttpClientUtils.closeQuietly(httpclient);
					httpclient = null;					
				}
			});
			
		} catch (Exception e) {
			UIUtils.showError(Messages.IssueCreationWizard_PublishingAbortedMessage, e);
		}
		
		if (isPublished) {
			new IssueCreatedDialog(getShell(),
					Messages.IssueCreationWizard_InfoDialogTitle, null,
					Messages.IssueCreationWizard_InfoDialogMessage,
					MessageDialog.INFORMATION,
					new String[] { IDialogConstants.OK_LABEL }, 0).open();
		}

		return isPublished;
	}

	private boolean publishNewIssue(IssueRequest issueRequest,
			List<ZipEntry> zipEntries, CommunityUser authInfo, final IProgressMonitor monitor) {
		try {
			CookieStore cookieStore = new BasicCookieStore();
			httpclient = HttpUtils.setupProxy(HttpClientBuilder.create())
					.setDefaultCookieStore(cookieStore).build();

			// Gets the authentication cookie
			Cookie authCookie = RESTCommunityHelper.getAuthenticationCookie(
					httpclient, cookieStore, authInfo.getUsername(),
					authInfo.getPassword());

			// Create the attachment file if any
			List<String> attachmentsIDs = new ArrayList<String>();
			if (!zipEntries.isEmpty()) {
				File zipAttachment = CommunityAPIUtils
						.createZipFile(zipEntries);
				String fileID = RESTCommunityHelper.uploadFile(httpclient,
						zipAttachment, authCookie);
				attachmentsIDs.add(fileID);
			}

			// Publish the issue to the community tracker
			issuePath = RESTCommunityHelper.createNewIssue(httpclient,
					issueRequest, attachmentsIDs, authCookie);

		} catch (CommunityAPIException e) {
			UIUtils.showError(e);
			return false;
		} finally {
			HttpClientUtils.closeQuietly(httpclient);
			httpclient = null;
		}
		return true;
	}

	/*
	 * Information dialog that shows a link to the newly created issue on the
	 * community tracker.
	 */
	private class IssueCreatedDialog extends MessageDialog {

		public IssueCreatedDialog(Shell parentShell, String dialogTitle,
				Image dialogTitleImage, String dialogMessage,
				int dialogImageType, String[] dialogButtonLabels,
				int defaultIndex) {
			super(parentShell, dialogTitle, dialogTitleImage, dialogMessage,
					dialogImageType, dialogButtonLabels, defaultIndex);
		}

		@Override
		protected Control createCustomArea(Composite parent) {
			final StyledText issueLink = new StyledText(parent, SWT.READ_ONLY);
			issueLink.setText(issuePath);
			issueLink.setBackground(parent.getBackground());
			issueLink.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, true,
					false, 2, 1));

			StyleRange style = new StyleRange();
			style.underline = true;
			style.underlineStyle = SWT.UNDERLINE_LINK;
			int[] ranges = { 0, issuePath.length() };
			StyleRange[] styles = { style };
			issueLink.setStyleRanges(ranges, styles);

			issueLink.addListener(SWT.MouseDown, new Listener() {
				@Override
				public void handleEvent(Event event) {
					try {
						int offset = issueLink.getOffsetAtLocation(new Point(
								event.x, event.y));
						StyleRange style = issueLink
								.getStyleRangeAtOffset(offset);
						if (style != null && style.underline
								&& style.underlineStyle == SWT.UNDERLINE_LINK) {
							BrowserUtils.openExternalBrowser(issuePath);
						}
					} catch (IllegalArgumentException e) {
						// no character under event.x, event.y
					}
				}
			});

			return issueLink;
		}

	}

	/**
	 * Static method for creating the {@link IssueCreationWizard} instance.
	 * 
	 * @return the wizard instance newly created, <code>null</code> if an
	 *         instance already exists
	 */
	public static synchronized IssueCreationWizard createWizard() {
		if (instance == null) {
			instance = new IssueCreationWizard();
			return instance;
		} else {
			return null;
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		instance = null;
	}
}
