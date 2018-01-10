/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.resource;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.model.dataset.SelectDefaultDatasetWizard;
import com.jaspersoft.studio.property.descriptor.JSSDialogCellEditor;
import com.jaspersoft.studio.utils.SelectionHelper;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * 
 * Cell editor used to show the default dataset dialog in the advanced properties
 * to configure the jr default adapter
 * 
 * @autohr Orlandin Marco
 */
public class DefaultDatasetCellEditor extends JSSDialogCellEditor {
	
	public DefaultDatasetCellEditor(Composite parent) {
		super(parent, true);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		String originalValue = (String)getValue();
		IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
		IFile context = ((IFileEditorInput) ep.getEditorInput()).getFile();
		SelectDefaultDatasetWizard defaultDAwizard = new SelectDefaultDatasetWizard(originalValue, context);
		WizardDialog defaultDAdialog = new WizardDialog(UIUtils.getShell(), defaultDAwizard);
		if (defaultDAdialog.open() == IDialogConstants.OK_ID){
			return defaultDAwizard.getValue();
		} else {
			return originalValue;
		}
	}

	protected void doSetValue(Object value) {
		//null is a valid value for the default data adapter
		if (value == null)
			super.doSetValue(""); //$NON-NLS-1$
		else {
			super.doSetValue(value);
		}
	}
	
	@Override
	protected Object doGetValue() {
		String value = (String) super.doGetValue();
		return value == null || value.trim().isEmpty() ? null : value;
	}
}
