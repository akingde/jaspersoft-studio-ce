package com.jaspersoft.studio.editor.palette;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.tools.CreationTool;
import org.eclipse.jface.dialogs.Dialog;

import com.jaspersoft.studio.model.DialogEnabledCommand;

/**
 * Custom creation tool that add support for dialog 
 * prompted during the creation of an element dragged from the palette.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 * @see DialogEnabledCommand
 *
 */
public final class JDCreationTool extends CreationTool {
	
	public JDCreationTool() {
		super();
	}

	public JDCreationTool(CreationFactory aFactory) {
		super(aFactory);
	}

	@Override
	protected void performCreation(int button) {
		Command currCommand = getCurrentCommand();
		if(currCommand instanceof DialogEnabledCommand){
			// If we have a special command that supports dialog (i.e: image creation)
			// we'll show the popup dialog and continue with creation only if
			// the user has confirmed.
			if(((DialogEnabledCommand)currCommand).openDialog()==Dialog.CANCEL){
				return;
			}
		}
		super.performCreation(button);
	}
	
}