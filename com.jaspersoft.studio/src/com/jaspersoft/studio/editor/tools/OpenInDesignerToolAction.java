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
 * Action to open a custom tool element in the designer to edit it
 * 
 * @author Orlandin Marco
 *
 */
public class OpenInDesignerToolAction extends Action {

	/**
	 * The palette entry to edit
	 */
	private ToolTemplateCreationEntry elementToEdit;

	public OpenInDesignerToolAction(ToolTemplateCreationEntry elementToEdit) {
		super();
		setText(Messages.OpenInDesignerToolAction_actionName);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/tool_open_in_editor.png")); //$NON-NLS-1$
		this.elementToEdit = elementToEdit;
	}

	public void run() {
		MCustomTool tool = elementToEdit.getTemplate();
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
		return elementToEdit != null;
	}

}
