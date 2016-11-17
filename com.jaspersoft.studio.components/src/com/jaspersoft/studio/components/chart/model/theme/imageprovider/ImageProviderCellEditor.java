/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.imageprovider;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.ATextDialogCellEditor;

public class ImageProviderCellEditor extends ATextDialogCellEditor {

	public ImageProviderCellEditor(Composite parent) {
		super(parent);
	}

	public ImageProviderCellEditor(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected Object openDialogBox(Control cellEditorWindow) {
		Shell shell = cellEditorWindow.getShell();
		FilteredResourcesSelectionDialog dialog = new FilteredResourcesSelectionDialog(shell, false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
		dialog.setTitle(Messages.ResourceCellEditor_open_resource);
		dialog.setInitialPattern("*.*"); //$NON-NLS-1$
		// dialog.setMessage("Please choose the Resource bundle:");
		// dialog.setMessage("Enter the name prefix or pattern (?, *, or camel case)");
		if (dialog.open() == Window.OK) {
			IFile file = (IFile) dialog.getFirstResult();
			if (file != null)
				return file.getRawLocation().toOSString();
		}
		return null;
	}
}
