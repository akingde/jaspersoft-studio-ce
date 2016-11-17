/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.style.wizard;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import com.jaspersoft.studio.wizards.ContextData;
import com.jaspersoft.studio.wizards.ContextHelpIDs;

import net.sf.jasperreports.eclipse.builder.jdt.JDTUtils;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Extends the original WizardNewFileCreationPage to implements the method to have a contextual help.
 * It also validate the page after the control creation and force a fixed page size after the controls are created.
 * 
 * @author Orlandin Marco
 * 
 */
public class WizardHelpNewFileCreationPage extends WizardNewFileCreationPage implements ContextData {

	public WizardHelpNewFileCreationPage(String pageName, IStructuredSelection selection) {
		super(pageName, selection);
	}

	/**
	 * Set and show the help data
	 */
	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem().displayHelp(ContextHelpIDs.WIZARD_STYLE_TEMPLATE_PATH);
	}

	/**
	 * Set the help data that should be seen in this step
	 */
	@Override
	public void setHelpData() {
		PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), ContextHelpIDs.WIZARD_STYLE_TEMPLATE_PATH);
	}

	@Override
	protected void setControl(Control newControl) {
		super.setControl(newControl);
		newControl.addListener(SWT.Help, new Listener() {
			@Override
			public void handleEvent(Event event) {
				performHelp();
			}
		});
		setHelpData();
	};
	
	@Override
	public void setVisible(boolean visible) {
		JDTUtils.deactivateLinkedResourcesSupport(visible);
		super.setVisible(visible);
	}
	
	/**
	 * Override of the create controls to validate the page after its creation
	 */
	@Override
	public void createControl(Composite parent) {
		super.createControl(parent);
		validatePage();
		UIUtils.resizeAndCenterShell(getShell(), 750, SWT.DEFAULT);
	}
}
