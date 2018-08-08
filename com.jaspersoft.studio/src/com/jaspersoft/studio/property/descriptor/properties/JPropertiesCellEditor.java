/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.properties;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.EditableDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.properties.dialog.JRPropertyEditor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRPropertiesMap;

public class JPropertiesCellEditor extends EditableDialogCellEditor {
	private JasperReportsConfiguration jConfig;
	private Object jrElement;

	public JPropertiesCellEditor(Composite parent, JasperReportsConfiguration jConfig, Object jrElement) {
		super(parent);
		this.jConfig = jConfig;
		this.jrElement = jrElement;
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		JRPropertyEditor wizard = new JRPropertyEditor(jConfig, jrElement);
		wizard.setValue((JRPropertiesMap) ((JRPropertiesMap) getValue()).clone());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK)
			return wizard.getValue();
		return null;
	}

	private JPropertiesLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null)
			return;
		if (labelProvider == null)
			labelProvider = new JPropertiesLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
