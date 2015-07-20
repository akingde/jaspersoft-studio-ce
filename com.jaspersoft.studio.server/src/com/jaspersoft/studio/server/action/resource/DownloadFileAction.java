/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.action.resource;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.dialogs.SaveAsDialog;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.server.export.AExporter;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.AFileResource;

public class DownloadFileAction extends OpenInEditorAction {
	private static final String ID = "DOWNLOADJSRESOURCE"; //$NON-NLS-1$

	// private TreeViewer treeViewer;

	public DownloadFileAction(TreeViewer treeViewer) {
		super(treeViewer, true);
		setId(ID);
		setText(Messages.DownloadFileAction_1);
		setDescription(Messages.DownloadFileAction_2);
		setToolTipText(Messages.DownloadFileAction_3);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/drive-download.png")); //$NON-NLS-1$
		setDisabledImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/drive-download.png")); //$NON-NLS-1$
	}

	@Override
	protected boolean preDownload(AFileResource fres) {
		SaveAsDialog saveAsDialog = new SaveAsDialog(Display.getDefault().getActiveShell());
		saveAsDialog.setOriginalName(AExporter.getNewFileName(fres.getValue(), "." + fres.getDefaultFileExtension())); //$NON-NLS-1$
		if (saveAsDialog.open() == Dialog.OK) {
			path = saveAsDialog.getResult();
			return true;
		}
		return false;
	}

}
