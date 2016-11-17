/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * Action to open a composite element in the designer to edit it
 * 
 * @author Orlandin Marco
 *
 */
public class OpenElementInDesignerAction extends Action {

	/**
	 * The palette entry to edit
	 */
	private CompositeElementTemplateCreationEntry elementToEdit;

	public OpenElementInDesignerAction(CompositeElementTemplateCreationEntry elementToEdit) {
		super();
		setText(Messages.OpenInDesignerToolAction_actionName);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/tool_open_in_editor.png")); //$NON-NLS-1$
		this.elementToEdit = elementToEdit;
	}

	public void run() {
		MCompositeElement element = elementToEdit.getTemplate();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(element.getPath()));
			if (!fileStore.fetchInfo().isDirectory() && fileStore.fetchInfo().exists()) {
        IDE.openEditorOnFileStore(page, fileStore);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isEnabled() {
		return elementToEdit != null;
	}

}
