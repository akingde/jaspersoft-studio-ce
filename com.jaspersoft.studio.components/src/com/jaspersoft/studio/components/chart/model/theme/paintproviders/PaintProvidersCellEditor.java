/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.paintproviders;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;

import net.sf.jasperreports.chartthemes.simple.PaintProvider;

public class PaintProvidersCellEditor extends EditableDialogCellEditor {

	public PaintProvidersCellEditor(Composite parent) {
		super(parent);
	}

	public PaintProvidersCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		PaintProvidersDialog dialog = new PaintProvidersDialog(cellEditorWindow.getShell());
		dialog.setValue((List<PaintProvider>) getValue());
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
			labelProvider = new PaintProvidersLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
