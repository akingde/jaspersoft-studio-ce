/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.tools.wizards.CompositeElementEditWizard;
import com.jaspersoft.studio.messages.Messages;

/**
 * Action to delete a custom tool from the palette
 * 
 * @author Orlandin Marco
 *
 */
public class EditCompositeElementAction extends Action {

	/**
	 * The palette entry to edit
	 */
	private CompositeElementTemplateCreationEntry elementToEdit;

	public EditCompositeElementAction(CompositeElementTemplateCreationEntry elementToEdit) {
		super();
		setText(Messages.EditToolAction_name);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/edit-style.png")); //$NON-NLS-1$
		this.elementToEdit = elementToEdit;
	}

	public void run() {
		MCompositeElement element = elementToEdit.getTemplate();
		CompositeElementEditWizard wizard = new CompositeElementEditWizard(element);
		WizardDialog dialog = new WizardDialog(UIUtils.getShell(), wizard);
		dialog.setPageSize(200, SWT.DEFAULT);
		dialog.open();		
	}
	
	@Override
	public boolean isEnabled() {
		return elementToEdit != null;
	}

}
