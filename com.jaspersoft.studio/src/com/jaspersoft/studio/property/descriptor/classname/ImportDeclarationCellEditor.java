/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.property.descriptor.ATextDialogCellEditor;
import com.jaspersoft.studio.property.descriptor.classname.dialog.ImportDialog;

public class ImportDeclarationCellEditor extends ATextDialogCellEditor {

	public ImportDeclarationCellEditor(Composite parent) {
		super(parent);
	}

	public ImportDeclarationCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		Shell shell = cellEditorWindow.getShell();

		String value = (String) getValue();
		ImportDialog dialog = new ImportDialog(shell, value);
		if (dialog.open() == Dialog.OK)
			return dialog.getImports();

		return null;
	}

}
