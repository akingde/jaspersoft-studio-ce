/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.paintprovider;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;

import net.sf.jasperreports.chartthemes.simple.PaintProvider;

public class PaintProviderCellEditor extends EditableDialogCellEditor {

	public PaintProviderCellEditor(Composite parent) {
		super(parent);
	}

	public PaintProviderCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		PaintProviderDialog dialog = new PaintProviderDialog(cellEditorWindow.getShell());
		dialog.setValue((PaintProvider) getValue());
		if (dialog.open() == Dialog.OK)
			return dialog.getValue();
		return null;
	}

	private LabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null)
			return;
		if (labelProvider == null)
			labelProvider = ColorWidget.cprovider;
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
