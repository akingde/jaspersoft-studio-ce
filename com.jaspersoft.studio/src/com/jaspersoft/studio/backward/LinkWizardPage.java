/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.backward;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSHelpWizardPage;

/**
 * Page where a JR definition link can be defined or where
 * an existing one can be edited
 * 
 * @author Orlandin Marco
 *
 */
public class LinkWizardPage extends JSSHelpWizardPage {

	/**
	 * The current value used as version
	 */
	private String version = ""; //$NON-NLS-1$
	
	/**
	 * The current value used as URL
	 */
	private String URL = ""; //$NON-NLS-1$
	
	/**
	 * Text area where the url is shown and can be typed
	 */
	private StyledText urlArea;
	
	/**
	 * Text area where the version is shown and can be typed
	 */
	private Text versionArea;
	
	/**
	 * If the dialog is open for an edit operation this value is 
	 * the edited version, otherwise it is null if this is an add
	 * operation
	 */
	private String editedVersion = null;
	
	/**
	 * Listener called when one of the text area is changed, it 
	 * simply read the current values on the text areas and store
	 * them on the fields
	 */
	private ModifyListener widgetModfied = new ModifyListener() {
		
		@Override
		public void modifyText(ModifyEvent e) {
			version = versionArea.getText();
			URL = urlArea.getText();
			getWizard().getContainer().updateButtons();
		}
	};
	
	/**
	 * Create the page for an edit operation
	 * 
	 * @param version the version to edit
	 * @param URL the url of the version to edit
	 */
	public LinkWizardPage(String version, String URL) {
		this();
		this.version = version;
		this.URL = URL;
		editedVersion = version;
	}
	
	/**
	 * Create the page for an add operation
	 */
	public LinkWizardPage() {
		super("linkpage"); //$NON-NLS-1$
		setTitle(Messages.LinkWizardPage_pageTitle);
		setMessage(Messages.LinkWizardPage_pageDescription);
	}

	@Override
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new GridLayout(2, false));
		
		//Create the version area
		new Label(container, SWT.NONE).setText(Messages.LinkWizardPage_versionLabel);
		versionArea = new Text(container, SWT.BORDER);
		versionArea.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		versionArea.setText(version);
		versionArea.setToolTipText(Messages.LinkWizardPage_versionTooltip);
		
		//create the url area
		Label urlLabel = new Label(container, SWT.NONE);
		urlLabel.setText(Messages.LinkWizardPage_urlLabel);
		urlLabel.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false));
		urlArea = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.WRAP);
		urlArea.setToolTipText(Messages.LinkWizardPage_urlTooltip);
		GridData urlData = new GridData(GridData.FILL_HORIZONTAL);
		urlData.widthHint = 250;
		urlData.heightHint = 100;
		urlArea.setLayoutData(urlData);
		urlArea.setText(URL);
		
		versionArea.addModifyListener(widgetModfied);
		urlArea.addModifyListener(widgetModfied);
		setControl(container);
	}

	@Override
	protected String getContextName() {
		return null;
	}
	
	/**
	 * The page is complete when both the version and the url are not empty and when
	 * the version is unique
	 */
	@Override
	public boolean isPageComplete() {
		if (URL.isEmpty() || version.isEmpty()){
			setErrorMessage(Messages.LinkWizardPage_errorEmpty);
			return false;
		}
		if ((editedVersion == null && JRBackwardManager.INSTANCE.getDefinition(version) != null)
				|| (!version.equals(editedVersion) && JRBackwardManager.INSTANCE.getDefinition(version) != null)){
			setErrorMessage(Messages.LinkWizardPage_errorAlreadyInUse);
			return false;
		}
		setErrorMessage(null);
		setMessage(Messages.LinkWizardPage_pageDescription);
		return true;
	}

	/**
	 * Return the current version
	 * 
	 * @return a not null string
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * Return the current URL
	 * 
	 * @return a not null String
	 */
	public String getURL() {
		return URL;
	}
}
