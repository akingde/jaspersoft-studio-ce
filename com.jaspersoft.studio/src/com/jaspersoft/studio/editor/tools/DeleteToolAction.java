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
		}
	}
	
	@Override
	public boolean isEnabled() {
		return elementToDelete != null;
	}

}
