/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.map.model.marker.dialog.MarkerEditor;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;

public class MarkerCellEditor extends EditableDialogCellEditor {

	public MarkerCellEditor(Composite parent) {
		super(parent);
	}

	public MarkerCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		MarkerEditor wizard = new MarkerEditor();
		wizard.setValue((MarkersDTO) getValue());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(),
				wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK)
			return wizard.getValue();
		return null;
	}

	private MarkerLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new MarkerLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
