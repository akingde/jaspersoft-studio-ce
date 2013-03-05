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
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.community.CommunityConstants;
import com.jaspersoft.studio.community.JSSCommunityActivator;
import com.jaspersoft.studio.community.dialogs.HwSwDetailsDialog;
import com.jaspersoft.studio.community.messages.Messages;
import com.jaspersoft.studio.community.utils.CommunityAPIUtils;
import com.jaspersoft.studio.community.zip.ZipEntry;
import com.jaspersoft.studio.community.zip.ZipEntryType;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Wizard page containing the issue details that will be attached as a final zip
 * file.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class IssueAttachmentDetailsPage extends JSSHelpWizardPage {

	private java.util.List<ZipEntry> zipEntries=new ArrayList<ZipEntry>();
	private File hwSwInfoFile = null;
	
	// Widgets 
	private Button btnLogFile;
	private Button btnSoftwareAndHardware;
	private List zipFileContent;
	private Button btnAttachments;
	private Button btnJaspersoftStudioPreferences;
	private Link addAttachments;
	private Font standardListFont;
	
	/**
	 * Create the wizard.
	 */
	public IssueAttachmentDetailsPage() {
		super("issueAttachmentDetailsWizardPage"); //$NON-NLS-1$
		setImageDescriptor(
				JSSCommunityActivator.getDefault().getImageDescriptor(CommunityConstants.ISSUE_SUBMISSION_WIZARD_IMG));
		setTitle(Messages.IssueAttachmentDetailsPage_Title);
		setDescription(Messages.IssueAttachmentDetailsPage_Description);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		GridLayout gl_container = new GridLayout(4, false);
		gl_container.horizontalSpacing = 10;
		gl_container.verticalSpacing = 10;
		container.setLayout(gl_container);
		
		// Metadata log file
		btnLogFile = new Button(container, SWT.CHECK);
		btnLogFile.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				logFileSelectionPerformed();
			}
		});
		btnLogFile.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2, 1));
		btnLogFile.setText(Messages.IssueAttachmentDetailsPage_LogFileCheckbox);
		
		// Hardware and Software summary information
		btnSoftwareAndHardware = new Button(container, SWT.CHECK);
		btnSoftwareAndHardware.setText(Messages.IssueAttachmentDetailsPage_SwAndHwSummaryCheckbox);
		btnSoftwareAndHardware.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hwSwInfoSelectionPerformed();
			}
		});
		final Link viewHwSwSummary = new Link(container, SWT.NONE);
		viewHwSwSummary.setText(Messages.IssueAttachmentDetailsPage_ViewLink);
		final HwSwDetailsDialog hwSwDialog = new HwSwDetailsDialog(getShell(),SWT.APPLICATION_MODAL | SWT.DIALOG_TRIM);
		viewHwSwSummary.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				hwSwDialog.setLocation(
						UIUtils.getDisplay().getCursorLocation().x, UIUtils
								.getDisplay().getCursorLocation().y);
				hwSwDialog.open();
			}
		});
		
		btnAttachments = new Button(container, SWT.CHECK);
		btnAttachments.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(!btnAttachments.getSelection()){
					// Remove all generic attachments
					removeZipEntries(ZipEntryType.ATTACHMENT);
					addAttachments.setEnabled(false);
				}
				else{
					addAttachments.setEnabled(true);
				}
				refreshZipEntriesList();
			}
		});
		btnAttachments.setText(Messages.IssueAttachmentDetailsPage_AttachmentsCheckbox);
		addAttachments = new Link(container, SWT.NONE);
		addAttachments.setText(Messages.IssueAttachmentDetailsPage_AddLink);
		addAttachments.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(UIUtils.getShell());
				fd.setFilterExtensions(new String[]{"*.png","*.jpeg; *.jpg","*.gif","*.jrxml","*.*"}); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
				String selection = fd.open();
				if(selection!=null){
					// Add the new zip entry
					zipEntries.add(new ZipEntry(null,selection,ZipEntryType.ATTACHMENT));
				}
				refreshZipEntriesList();
			}
		});
		
		btnJaspersoftStudioPreferences = new Button(container, SWT.CHECK);
		btnJaspersoftStudioPreferences.setText(Messages.IssueAttachmentDetailsPage_JSSPreferencesCheckbox);
		btnJaspersoftStudioPreferences.setLayoutData(new GridData(SWT.FILL,SWT.CENTER,true,false,2,1));
		btnJaspersoftStudioPreferences.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				jssPrefsSelectionPerformed();
			}
		});
		
		// Final zip file content
		Group groupZipContent = new Group(container, SWT.NONE);
		groupZipContent.setText(Messages.IssueAttachmentDetailsPage_ZipContentsGroup);
		groupZipContent.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_groupZipContent = new GridData(SWT.FILL, SWT.FILL, true, true, 4, 1);
		gd_groupZipContent.widthHint = 579;
		groupZipContent.setLayoutData(gd_groupZipContent);
		zipFileContent = new List(groupZipContent, SWT.BORDER);
		standardListFont = zipFileContent.getFont();

		// Preselect software/hardware summary
		btnSoftwareAndHardware.setSelection(true);
		hwSwInfoSelectionPerformed();
	}
	
	/*
	 * Invoked when the checkbox for the Jaspersoft Preferences is pressed.
	 */
	private void jssPrefsSelectionPerformed() {
		if(btnJaspersoftStudioPreferences.getSelection()){
			// Add JSS preferences file
			zipEntries.add(
					new ZipEntry("jss_preferences_file.prefs",CommunityAPIUtils.getJaspersoftStudioPrefsLocation(),ZipEntryType.PREFS)); //$NON-NLS-1$
		}
		else {
			// Remove preference file if any
			removeZipEntries(ZipEntryType.PREFS);
		}
		refreshZipEntriesList();
	}
	
	/*
	 * Invoked when the checkbox for hw and sw information is pressed.
	 */
	private void hwSwInfoSelectionPerformed() {
		if(hwSwInfoFile==null){
			try {
				hwSwInfoFile = File.createTempFile("hwSwInfo", ".txt"); //$NON-NLS-1$ //$NON-NLS-2$
				FileWriter fw = new FileWriter(hwSwInfoFile);
				fw.write(CommunityAPIUtils.getHardwareSoftwareInfo());
				fw.close();
			} catch (IOException e) {
				UIUtils.showError(e);
				return;
			}
		}
		if(btnSoftwareAndHardware.getSelection()){
			zipEntries.add(
					new ZipEntry(hwSwInfoFile.getName(), hwSwInfoFile.getAbsolutePath(), ZipEntryType.HW_SW_INFO));
		}
		else{
			removeZipEntries(ZipEntryType.HW_SW_INFO);
		}
		refreshZipEntriesList();
	}
	
	/*
	 * Invoked when the checkbox for log file inclusion is pressed.
	 */
	private void logFileSelectionPerformed() {
		if(btnLogFile.getSelection()){
			MessageDialog.openWarning(getShell(), 
					Messages.IssueAttachmentDetailsPage_WarningLogFileAttachmentTitle, 
					Messages.IssueAttachmentDetailsPage_WarningLogFileAttachmentMsg);
			// Add log file to final zip attachment
			zipEntries.add(
					new ZipEntry("jss_logfile.txt",CommunityAPIUtils.getJaspersoftStudioLogFileLocation(),ZipEntryType.LOG)); //$NON-NLS-1$
		}
		else {
			// Remove log file if any
			removeZipEntries(ZipEntryType.LOG);
		}
		refreshZipEntriesList();
	}
	
	/*
	 * Refresh the zip entries.
	 */
	private void refreshZipEntriesList() {
		zipFileContent.removeAll();
		if(!zipEntries.isEmpty()){
			java.util.List<String> fileLocations = new ArrayList<String>();
			for(ZipEntry ze : zipEntries){
				fileLocations.add(ze.getLocation());
			}
			Collections.sort(fileLocations);
			for(String loc : fileLocations){
				zipFileContent.add(loc);
			}
			zipFileContent.setFont(standardListFont);
		}
		else {
			zipFileContent.add(Messages.IssueAttachmentDetailsPage_NoAttachments);
			zipFileContent.setFont(ResourceManager.getItalicFont(standardListFont));
		}
	}
	
	/*
	 * Remove the zip entries of the specified type.
	 */
	private void removeZipEntries(ZipEntryType type) {
		for (Iterator<ZipEntry> it = zipEntries.iterator(); it.hasNext(); ){
			ZipEntry ze = it.next();
			if(type.equals(ze.getType())){
				it.remove();
			}
		}
	}
	
	public java.util.List<ZipEntry> getZipEntries(){
		return this.zipEntries;
	}

	@Override
	protected String getContextName() {
		return ContextHelpIDs.WIZARD_ISSUE_ATTACHMENTS;
	}
}
