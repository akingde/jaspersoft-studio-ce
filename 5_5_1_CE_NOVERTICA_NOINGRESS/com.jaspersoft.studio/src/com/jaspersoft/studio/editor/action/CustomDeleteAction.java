/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.messages.Messages;


/**
 * This custom delete action override the original one provide by eclipse.
 * The only differences it that this one check if the list of commands have
 * a warning message. In this case display all the messages into only one 
 * dialog and ask a confirmation to proceed
 * 
 * @author Orlandin Marco
 *
 */
public class CustomDeleteAction extends DeleteAction{

	public CustomDeleteAction(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
	public void run() {
		CompoundCommand compCommand = (CompoundCommand)createDeleteCommand(getSelectedObjects());
		StringBuilder messages = new StringBuilder();
		messages.append(Messages.CustomDeleteAction_messageListStart+"\n"); //$NON-NLS-1$
		boolean messageFound = false;
		for(Object oCommand : compCommand.getCommands()){
			if (oCommand instanceof MessageProviderCommand){
				MessageProviderCommand messageCommand = (MessageProviderCommand)oCommand;
				String message = messageCommand.getMessage();
				if (message != null) {
					messages.append(message+"\n"); //$NON-NLS-1$
					messageFound = true;
				}
			}
		}
		messages.append(Messages.CustomDeleteAction_messageListEnd);
		if (messageFound){
			if (UIUtils.showConfirmation(Messages.ADatasetObjectDeleteCommand_confirmationtitle,messages.toString())){
				execute(compCommand);
			}
		} else {
			execute(compCommand);
		}
	}
	

}
