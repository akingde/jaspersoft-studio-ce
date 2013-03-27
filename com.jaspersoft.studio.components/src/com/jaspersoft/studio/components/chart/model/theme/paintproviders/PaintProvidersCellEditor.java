/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.paintproviders;

import java.util.List;

import net.sf.jasperreports.chartthemes.simple.PaintProvider;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

public class PaintProvidersCellEditor extends DialogCellEditor {

	public PaintProvidersCellEditor(Composite parent) {
		super(parent);
	}

	public PaintProvidersCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		PaintProvidersEditor wizard = new PaintProvidersEditor();
		wizard.setValue((List<PaintProvider>) getValue());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return wizard.getValue();
		}
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
