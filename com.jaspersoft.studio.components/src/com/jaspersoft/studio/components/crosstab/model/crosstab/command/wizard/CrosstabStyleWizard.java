/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.crosstab.command.wizard;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.dialog.CrosstabStyle;
import com.jaspersoft.studio.editor.style.TemplateStyle;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * A wizard only with the step to define the crosstab style
 * 
 * @author Orlandin Marco
 *
 */
public class CrosstabStyleWizard extends JSSWizard {

	/**
	 * The layout step of the wizard
	 */
	private CrosstabWizardLayoutPage layoutPage;
	
	/**
	 * 
	 * @param showTitle True if the dialog should have the title field
	 * @param templateToOpen open the dialog in edit mode, with the field set at the value 
	 * of this template. Pass null if you don't want to edit an existing template
	 */
	public CrosstabStyleWizard(boolean showTitle, TemplateStyle templateToOpen) {
		super();
		setWindowTitle(Messages.CrosstabStyleWizard_styleWizardTitle);
		setNeedsProgressMonitor(true);
		layoutPage = new CrosstabWizardLayoutPage(showTitle);
		if (templateToOpen != null) layoutPage.setTemplateToOpen(templateToOpen);
	}
	
	public CrosstabStyleWizard() {
		this(false, null);
	}

	/**
	 * Return the Crosstab selected in the layout step
	 * 
	 * @return a TableStyle
	 */
	public CrosstabStyle getTableStyle(){
		return layoutPage.getSelectedStyle();
	}
	
	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);
	}

	@Override
	public void addPages() {
		addPage(layoutPage);
		//Hide the Next and Previous buttons
		setForcePreviousAndNextButtons(false);
	}
}
