/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.tabstops;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.tabstops.dialog.TabStopsEditor;

import net.sf.jasperreports.engine.TabStop;

public class TabStopsCellEditor extends EditableDialogCellEditor {

	public TabStopsCellEditor(Composite parent) {
		super(parent);
	}

	public TabStopsCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		TabStopsEditor wizard = new TabStopsEditor();
		wizard.setValue(new ArrayList<TabStop>((List<TabStop>) getValue()));
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		if (dialog.open() == Dialog.OK)
			return wizard.getValue();
		return null;
	}

	private TabStopsLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new TabStopsLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
