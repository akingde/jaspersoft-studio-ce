/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.returnvalue;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.model.JReportsDTO;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.subreport.returnvalue.dialog.SubreportRVPropertyEditor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

public class RVPropertiesCellEditor extends EditableDialogCellEditor {

	public RVPropertiesCellEditor(Composite parent) {
		super(parent);
	}

	public RVPropertiesCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		SubreportRVPropertyEditor wizard = new SubreportRVPropertyEditor();
		wizard.setValue((JReportsDTO) getValue());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		UIUtils.resizeAndCenterShell(dialog.getShell(), RVPropertyPage.WIDTH_HINT, -1);
		if (dialog.open() == Dialog.OK)
			return wizard.getValue();
		return null;
	}

	private LabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new RVPropertiesLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
