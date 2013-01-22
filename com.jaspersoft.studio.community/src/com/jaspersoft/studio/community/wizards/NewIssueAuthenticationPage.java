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

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.community.JSSCommunityActivator;
import com.jaspersoft.studio.community.utils.CommunityUser;
import com.jaspersoft.studio.utils.UIUtils;

/**
 * Wizard page that allows to configure the authentication information when
 * submitting an new issue to the Community tracker.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class NewIssueAuthenticationPage extends WizardPage {
	private CommunityUser communityUserInformation;
	private boolean shouldSaveCredentials;
	
	private Text username;
	private Text password;
	private Button btnReuseStoredCredentials;
	private Button btnStoreCommunityUserCredentials;

	/**
	 * Create the wizard.
	 */
	public NewIssueAuthenticationPage() {
		super("wizardPage");
		setImageDescriptor(
				JSSCommunityActivator.getDefault().getImageDescriptor("resources/images/softwareBug.jpg"));
		setTitle("Community user authentication");
		setDescription("Please enter the authentication information to submit the issue");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		GridLayout gl_container = new GridLayout(2, false);
		gl_container.verticalSpacing = 10;
		gl_container.horizontalSpacing = 10;
		container.setLayout(gl_container);
		
		Label lblUsername = new Label(container, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblUsername.setText("Username");
		
		username = new Text(container, SWT.BORDER);
		username.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		username.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkForPageComplete();
			}
		});
		
		Label lblPassword = new Label(container, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPassword.setText("Password");
		
		password = new Text(container, SWT.BORDER | SWT.PASSWORD);
		password.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		password.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkForPageComplete();
			}
		});
		
		btnReuseStoredCredentials = new Button(container, SWT.CHECK);
		btnReuseStoredCredentials.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnReuseStoredCredentials.setText("Re-use stored credentials");
		btnReuseStoredCredentials.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnReuseStoredCredentials.getSelection()){
					btnStoreCommunityUserCredentials.setEnabled(false);
					btnStoreCommunityUserCredentials.setSelection(false);
					communityUserInformation = JSSCommunityActivator.getDefault().getCommunityUserInformation();
					if(communityUserInformation==null){
						MessageDialog.openWarning(UIUtils.getShell(), "Community user credentials", "No information regarding a community user were found.\nYou need to enter them in dedicated username and password fields.");
						btnReuseStoredCredentials.setSelection(false);
						btnStoreCommunityUserCredentials.setEnabled(true);
					}
					else{
						username.setEnabled(false);
						username.setText(communityUserInformation.getUsername());
						password.setEnabled(false);
						password.setText(communityUserInformation.getPassword());
					}
				}
				else {
					btnStoreCommunityUserCredentials.setSelection(false);
					btnStoreCommunityUserCredentials.setEnabled(true);
					username.setEnabled(true);
					password.setEnabled(true);
				}
				checkForPageComplete();
			}
		});
		
		btnStoreCommunityUserCredentials = new Button(container, SWT.CHECK);
		btnStoreCommunityUserCredentials.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		btnStoreCommunityUserCredentials.setText("Store community user information");
		btnStoreCommunityUserCredentials.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				shouldSaveCredentials = 
						btnStoreCommunityUserCredentials.isEnabled() && btnStoreCommunityUserCredentials.getSelection();
				checkForPageComplete();
			}
		});
		
		setPageComplete(false);
	}

	/*
	 * Checks if page is complete.
	 */
	private void checkForPageComplete() {
		boolean isComplete =
				!username.getText().isEmpty() &&
				!password.getText().isEmpty();
		setPageComplete(isComplete);
	}
	
	/**
	 * @return the authentication information for the community user
	 */
	public CommunityUser getCommunityUserInformation(){
		if(this.communityUserInformation==null){
			return new CommunityUser(username.getText(), password.getText());
		}
		return this.communityUserInformation;
	}
	
	/**
	 * @return <code>true</code> if the credentials should be saved in the
	 *         secure preference storage, <code>false</code> otherwise
	 */
	public boolean shouldSaveCredentials(){
		return this.shouldSaveCredentials;
	}
}
