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
package com.jaspersoft.studio.property.descriptor.pattern;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.descriptor.ATextDialogRWCellEditor;
import com.jaspersoft.studio.property.descriptor.pattern.dialog.PatternEditor;

public class PatternCellEditor extends ATextDialogRWCellEditor {

	public PatternCellEditor(Composite parent) {
		super(parent);
	}

	public PatternCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		PatternEditor wizard = new PatternEditor();
		wizard.setValue((String) getValue());
		WizardDialog dialog = new WizardDialog(cellEditorWindow.getShell(), wizard);
		dialog.create();
		if (dialog.open() == Dialog.OK) {
			return wizard.getValue();
		}
		return null;
	}

	@Override
	protected Object doGetValue() {
		Object val = super.doGetValue();
		if (isDirty()) {
			return text.getText();
		}
		return val;
	}

	@Override
	protected void doSetValue(Object value) {
		super.doSetValue(value);
		if (value instanceof String) {
			text.setText(value == null ? "" : (String) value);
			text.addModifyListener(getModifyListener());
		}
	}
}
