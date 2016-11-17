/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;


public class AlignMarginTopAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "aligntotop"; //$NON-NLS-1$
	
	
	public AlignMarginTopAction(IWorkbenchPart part) {
		super(part);
		setText("Align to Top");
		setId(ID);
	}

	public static JSSCompoundCommand generateCommand(List<APropertyNode> nodes){
		JSSCompoundCommand command = new JSSCompoundCommand(null);
    // Find the smallest one...
		for (APropertyNode element : nodes)
    {
			command.setReferenceNodeIfNull(element);
			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setTarget(element);
			setCommand.setPropertyId(JRDesignElement.PROPERTY_Y);
			setCommand.setPropertyValue(0);
			command.add(setCommand);
    }
		
		return command;
	}

	@Override
	protected Command createCommand() {
			List<APropertyNode> nodes = getOperationSet();
			Command command = null;
			if (!nodes.isEmpty()) {
				command = generateCommand(nodes);
				command.setDebugLabel(getText());
			}
			return command;
	}
	
	@Override
	protected boolean calculateEnabled() {
		return true;
	}

}
