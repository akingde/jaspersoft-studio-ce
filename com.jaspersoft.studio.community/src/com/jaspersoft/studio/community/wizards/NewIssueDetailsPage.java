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

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.community.CommunityConstants;
import com.jaspersoft.studio.community.JSSCommunityActivator;
import com.jaspersoft.studio.community.issues.IssueField;
import com.jaspersoft.studio.community.issues.enums.Category;
import com.jaspersoft.studio.community.issues.enums.Priority;
import com.jaspersoft.studio.community.issues.enums.Reproducibility;
import com.jaspersoft.studio.community.issues.enums.Resolution;
import com.jaspersoft.studio.community.issues.enums.Severity;
import com.jaspersoft.studio.community.issues.enums.Status;
import com.jaspersoft.studio.community.messages.Messages;
import com.jaspersoft.studio.community.requests.IssueRequest;

/**
 * Wizard page that allows to configure the issue basic information.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class NewIssueDetailsPage extends WizardPage {
	private Text title;
	private Text description;
	private Combo category;
	private Combo priority;
	private Combo severity;
	private Combo reproducibility;
	private Combo resolution;
	private Combo status;

	/**
	 * Create the wizard.
	 */
	public NewIssueDetailsPage() {
		super("issueDetailsWizardPage"); //$NON-NLS-1$
		setImageDescriptor(
				JSSCommunityActivator.getDefault().getImageDescriptor("resources/images/softwareBug.jpg")); //$NON-NLS-1$
		setTitle(Messages.NewIssueDetailsPage_Title);
		setDescription(Messages.NewIssueDetailsPage_Description);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		GridLayout gl_container = new GridLayout(4, false);
		gl_container.verticalSpacing = 10;
		gl_container.horizontalSpacing = 10;
		container.setLayout(gl_container);
		
		Label lblTitle = new Label(container, SWT.NONE);
		lblTitle.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTitle.setText(Messages.NewIssueDetailsPage_IssueTitle);
		
		title = new Text(container, SWT.BORDER);
		title.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		title.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkPageComplete();
			}
		});
		
		Label lblDescription = new Label(container, SWT.NONE);
		lblDescription.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescription.setText(Messages.NewIssueDetailsPage_IssueDescription);
		
		description = new Text(container, SWT.BORDER | SWT.MULTI);
		GridData gd_description = new GridData(SWT.FILL, SWT.FILL, true, false, 3, 1);
		gd_description.heightHint = 150;
		description.setLayoutData(gd_description);
		description.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent e) {
				checkPageComplete();
			}
		});
		
		Label lblCategory = new Label(container, SWT.NONE);
		lblCategory.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategory.setText(Messages.NewIssueDetailsPage_IssueCategory);
		category = new Combo(container, SWT.READ_ONLY);
		category.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		category.addSelectionListener(new PageCompletionChecker());
		for(Category c : Category.values()){
			category.add(c.getText());
			category.setData(c.getText(), c.getValue());
		}
		category.select(0); // BugReport
		
		Label lblPriority = new Label(container, SWT.NONE);
		lblPriority.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPriority.setText(Messages.NewIssueDetailsPage_IssuePriority);
		priority = new Combo(container, SWT.READ_ONLY);
		priority.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		priority.addSelectionListener(new PageCompletionChecker());
		for(Priority p : Priority.values()){
			priority.add(p.getText());
			priority.setData(p.getText(),p.getStringValue());
		}
		priority.select(1); // Low
		
		Label lblSeverity = new Label(container, SWT.NONE);
		lblSeverity.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSeverity.setText(Messages.NewIssueDetailsPage_IssueSeverity);
		severity = new Combo(container, SWT.READ_ONLY);
		severity.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		severity.addSelectionListener(new PageCompletionChecker());
		for(Severity s : Severity.values()){
			severity.add(s.getText());
			severity.setData(s.getText(),s.getStringValue());
		}
		severity.select(4); // Minor
		
		Label lblReproducibility = new Label(container, SWT.NONE);
		lblReproducibility.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblReproducibility.setText(Messages.NewIssueDetailsPage_IssueReproducibility);
		reproducibility = new Combo(container, SWT.READ_ONLY);
		reproducibility.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		reproducibility.addSelectionListener(new PageCompletionChecker());
		for(Reproducibility r : Reproducibility.values()){
			reproducibility.add(r.getText());
			reproducibility.setData(r.getText(),r.getStringValue());
		}
		reproducibility.select(0); // Always
		
		Label lblResolution = new Label(container, SWT.NONE);
		lblResolution.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblResolution.setText(Messages.NewIssueDetailsPage_IssueResolution);
		resolution = new Combo(container, SWT.READ_ONLY);
		resolution.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		resolution.add(Resolution.Open.getText());
		resolution.setData(Resolution.Open.getText(),Resolution.Open.getStringValue());
		resolution.select(0); //Open
		
		Label lblStatus = new Label(container, SWT.NONE);
		lblStatus.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStatus.setText(Messages.NewIssueDetailsPage_IssueStatus);
		status = new Combo(container, SWT.READ_ONLY);
		status.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		status.add(Status.New.getText());
		status.setData(Status.New.getText(),Status.New.getStringValue());
		status.select(0); // New
		
		setPageComplete(false);
	}

	/**
	 * @return the issue request data
	 */
	public IssueRequest getIssueRequest(){
		IssueRequest issue = new IssueRequest(title.getText(), description.getText());
		issue.setCategory(new IssueField(Category.FIELD_NAME,(String)category.getData(category.getText())));
		issue.setPriority(new IssueField(Priority.FIELD_NAME,(String)priority.getData(priority.getText())));
		issue.setSeverity(new IssueField(Severity.FIELD_NAME,(String)severity.getData(severity.getText())));
		issue.setReproducibility(new IssueField(Reproducibility.FIELD_NAME,(String)reproducibility.getData(reproducibility.getText())));
		issue.setResolution(new IssueField(Resolution.FIELD_NAME,(String)resolution.getData(resolution.getText())));
		issue.setStatus(new IssueField(Status.FIELD_NAME,(String)status.getData(status.getText())));
		issue.setProject(new IssueField("field_bug_project", String.valueOf(CommunityConstants.JSSPROJECT_COMMUNITY_ID)){ //$NON-NLS-1$
			@Override
			protected String getValueAttributeName() {
				return "target_id"; //$NON-NLS-1$
			}
		});
		
		return issue;
	}
	
	/*
	 * Checks for wizard page completion.
	 */
	private void checkPageComplete() {
		// To be complete all the fields must be set
		boolean pageComplete = 
				!title.getText().isEmpty() && 
				!description.getText().isEmpty() && 
				!category.getText().isEmpty() &&
				!priority.getText().isEmpty() && 
				!severity.getText().isEmpty() &&
				!reproducibility.getText().isEmpty() &&
				!resolution.getText().isEmpty() &&
				!status.getText().isEmpty();
		setPageComplete(pageComplete);
	}
	
	/*
	 * Selection listener for the combo boxes.
	 */
	private class PageCompletionChecker extends SelectionAdapter{
		@Override
		public void widgetSelected(SelectionEvent e) {
			checkPageComplete();
		}
	}
	
}
