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

import java.text.MessageFormat;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

/**
 * Action to delete a custom tool from the palette
 * 
 * @author Orlandin Marco
 *
 */
public class DeleteToolAction extends Action {

	/**
	 * The palette entry to delete
	 */
	private ToolTemplateCreationEntry elementToDelete;

	public DeleteToolAction(ToolTemplateCreationEntry elementToDelete) {
		super();
		setText(Messages.DeleteToolAction_actionName);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/delete_style.gif")); //$NON-NLS-1$
		this.elementToDelete = elementToDelete;
	}

	public void run() {
		MCustomTool tool = elementToDelete.getTemplate();
		String question = MessageFormat.format(Messages.DeleteToolAction_messageDescription, new Object[]{tool.getName()});
		boolean confirmDelete = UIUtils.showConfirmation(Messages.DeleteToolAction_messageTitle,question);
		if (confirmDelete){
			ToolManager.INSTANCE.deleteTool(tool);
			//If it is in the workspace delete the link to the file also
			try {
				IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(tool.getPath()));
				if (!fileStore.fetchInfo().isDirectory()) {
					IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
					IFile[] files = root.findFilesForLocationURI(fileStore.toURI());
					if (files != null && files.length > 0){
						 files[0].delete(true, new NullProgressMonitor());
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public boolean isEnabled() {
		return elementToDelete != null;
	}

}
