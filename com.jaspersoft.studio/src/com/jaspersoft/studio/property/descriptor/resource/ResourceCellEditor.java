/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.resource;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.property.descriptor.ATextDialogCellEditor;

public class ResourceCellEditor extends ATextDialogCellEditor {

	public ResourceCellEditor(Composite parent) {
		super(parent);
	}

	public ResourceCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		Shell shell = cellEditorWindow.getShell();
		FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(shell, false, ResourcesPlugin
				.getWorkspace().getRoot(), IResource.FILE);
		dialog.setTitle(Messages.ResourceCellEditor_open_resource);
		dialog.setInitialPattern("*.properties"); //$NON-NLS-1$
		// dialog.setMessage("Please choose the Resource bundle:");
		// dialog.setMessage("Enter the name prefix or pattern (?, *, or camel case)");
		if (dialog.open() == Window.OK) {
			IFile file = (IFile) dialog.getFirstResult();
			if (file != null)
				return file.getProjectRelativePath().toOSString();
		}
		return null;
	}
}
