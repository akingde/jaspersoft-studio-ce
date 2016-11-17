/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
 * Action to delete a composite element from the palette
 * 
 * @author Orlandin Marco
 *
 */
public class DeleteCompositeElementAction extends Action {

	/**
	 * The palette entry to delete
	 */
	private CompositeElementTemplateCreationEntry elementToDelete;

	public DeleteCompositeElementAction(CompositeElementTemplateCreationEntry elementToDelete) {
		super();
		setText(Messages.DeleteToolAction_actionName);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/delete_style.gif")); //$NON-NLS-1$
		this.elementToDelete = elementToDelete;
	}

	public void run() {
		MCompositeElement element = elementToDelete.getTemplate();
		String question = MessageFormat.format(Messages.DeleteToolAction_messageDescription, new Object[]{element.getName()});
		boolean confirmDelete = UIUtils.showConfirmation(Messages.DeleteToolAction_messageTitle,question);
		if (confirmDelete){
			CompositeElementManager.INSTANCE.deleteCompositeElement(element);
			//If it is in the workspace delete the link to the file also
			try {
				IFileStore fileStore = EFS.getLocalFileSystem().getStore(new Path(element.getPath()));
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
