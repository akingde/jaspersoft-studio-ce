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
package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jdt.core.search.SearchEngine;
import org.eclipse.jdt.internal.core.JavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.SelectionDialog;

import com.jaspersoft.studio.property.descriptor.ATextDialogCellEditor;

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

		SelectionDialog dialog = JavaUI.createPackageDialog(shell, new ProgressMonitorDialog(shell),
				SearchEngine.createWorkspaceScope(), true, true, null);
		if (dialog.open() == Window.OK) {
			if (dialog.getResult() != null && dialog.getResult().length > 0) {
				String res = ""; //$NON-NLS-1$
				for (int i = 0; i < dialog.getResult().length; i++) {
					JavaElement jpf = (JavaElement) dialog.getResult()[i];
					res += jpf.getElementName() + ";"; //$NON-NLS-1$
				}
				return res;
			}
		}

		return null;
	}

}
