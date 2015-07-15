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
 * Action to delete a custom tool from the palette
 * 
 * @author Orlandin Marco
 *
 */
public class EditToolAction extends Action {

	/**
	 * The palette entry to delete
	 */
	private ToolTemplateCreationEntry elementToDelete;

	public EditToolAction(ToolTemplateCreationEntry elementToDelete) {
		super();
		setText(Messages.EditToolAction_name);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/edit-style.png")); //$NON-NLS-1$
		this.elementToDelete = elementToDelete;
	}

	public void run() {
		MCustomTool tool = elementToDelete.getTemplate();
		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		try {
			IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(tool.getPath()));
			if (!fileStore.fetchInfo().isDirectory() && fileStore.fetchInfo().exists()) {
        IDE.openEditorOnFileStore(page, fileStore);
			}
		} catch (PartInitException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public boolean isEnabled() {
		return elementToDelete != null;
	}

}
