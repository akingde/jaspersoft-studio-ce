/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
