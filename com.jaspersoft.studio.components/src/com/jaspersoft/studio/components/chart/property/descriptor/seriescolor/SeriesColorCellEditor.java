/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.descriptor.seriescolor;

import java.util.SortedSet;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.chart.property.descriptor.seriescolor.dialog.SeriesColorEditor;
import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;

import net.sf.jasperreports.engine.base.JRBaseChartPlot.JRBaseSeriesColor;

public class SeriesColorCellEditor extends EditableDialogCellEditor {

	public SeriesColorCellEditor(Composite parent) {
		super(parent);
	}

	public SeriesColorCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@SuppressWarnings("unchecked")
	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		SeriesColorEditor wizard = new SeriesColorEditor();
		wizard.setValue((SortedSet<JRBaseSeriesColor>) getValue());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(),
				wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return wizard.getValue();
		}
		return null;
	}

	private LabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new SeriesColorLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
