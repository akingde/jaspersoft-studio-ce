/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input.array;

import java.io.File;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.FilteredResourcesSelectionDialog;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.util.Misc;

public class FileElement extends AWElement {

	private Button bbuton;

	@Override
	public Class<?> getSupportedType() {
		return File.class;
	}

	@Override
	public Control createControl(Composite parent) {
		bbuton = new Button(parent, SWT.PUSH);
		bbuton.setText(Messages.FileInput_selectfile);
		bbuton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FilteredResourcesSelectionDialog fd = new FilteredResourcesSelectionDialog(Display.getCurrent()
						.getActiveShell(), false, ResourcesPlugin.getWorkspace().getRoot(), IResource.FILE);
				if (fd.open() == Dialog.OK) {
					IFile file = (IFile) fd.getFirstResult();
					setValue(new File(file.getLocationURI()));
					updateLabel();
				}
			}
		});
		if (getValue() != null && getValue() instanceof Boolean)
			bbuton.setSelection((Boolean) Misc.nvl(getValue(), Boolean.FALSE));
		updateLabel();
		return bbuton;
	}

	private void updateLabel() {
		Object v = getValue();
		if (v != null && v instanceof File)
			bbuton.setText(((File) v).getAbsolutePath());
	}

}
