/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.formatting.actions;

import java.util.List;
import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public class DecreaseHSpaceAction extends AbstractFormattingAction{

	/** The Constant ID. */
	public static final String ID = "decreasehspace"; //$NON-NLS-1$
	
	public DecreaseHSpaceAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.DecreaseHSpaceAction_actionName);
		setToolTipText(Messages.DecreaseHSpaceAction_actionDescription);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/elem_add_hspace_min.png"));  //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return getOperationSet().size()>1;
	}
	
	public static CompoundCommand generateCommand(List<APropertyNode> nodes){
		CompoundCommand command = new CompoundCommand();
		   
		if (nodes.isEmpty()) return command;
	  nodes = sortXY(nodes);
    
    for (int i=1; i<nodes.size(); ++i)
    {
    		APropertyNode node = nodes.get(i);
        JRDesignElement element = (JRDesignElement)node.getValue();
      	SetValueCommand setCommand = new SetValueCommand();
  			setCommand.setTarget(node);
  			setCommand.setPropertyId(JRDesignElement.PROPERTY_X);
  			setCommand.setPropertyValue(element.getX() - 5*i);
	      command.add(setCommand);
    }
		
		return command;
	}

	protected Command createAlignmentCommand() {
			List<APropertyNode> nodes = getOperationSet();
			CompoundCommand command = null;
			if (nodes.isEmpty()) 
				command = new CompoundCommand();
			else {
				command = generateCommand(nodes);
			}
			command.setDebugLabel(getText());
			return command;
	}
	
}
