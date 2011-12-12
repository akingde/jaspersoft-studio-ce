/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.properties;

import net.sf.jasperreports.engine.JRPropertiesMap;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.properties.dialog.JRPropertyEditor;

public class JPropertiesCellEditor extends DialogCellEditor {

	public JPropertiesCellEditor(Composite parent) {
		super(parent);
	}

	public JPropertiesCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		JRPropertyEditor wizard = new JRPropertyEditor();
		wizard.setValue((JRPropertiesMap) getValue());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return wizard.getValue();
		}
		return null;
	}

	private JPropertiesLabelProvider labelProvider;

	@Override
	protected void updateContents(Object value) {
		if (getDefaultLabel() == null) {
			return;
		}
		if (labelProvider == null)
			labelProvider = new JPropertiesLabelProvider();
		String text = labelProvider.getText(value);
		getDefaultLabel().setText(text);
	}
}
