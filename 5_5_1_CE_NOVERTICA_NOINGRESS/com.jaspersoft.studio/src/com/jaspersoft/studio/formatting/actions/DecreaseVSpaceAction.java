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

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.messages.Messages;

public class DecreaseVSpaceAction extends AbstractFormattingAction{

	/** The Constant ID. */
	public static final String ID = "decreasevspace"; //$NON-NLS-1$
	
	public DecreaseVSpaceAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.DecreaseVSpaceAction_actionName);
		setToolTipText(Messages.DecreaseVSpaceAction_actionDescription);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/elem_add_vspace_min.png"));  //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return getOperationSet().size()>1;
	}
	
	public static CompoundCommand generateCommand(List<APropertyNode> nodes){
		CompoundCommand command = new CompoundCommand();
		   
		if (nodes.isEmpty()) return command;
		List<APropertyNode> sortedElements = sortYX( nodes );
    
    for (int i=1; i<sortedElements.size(); ++i)
    {
    		APropertyNode element = sortedElements.get(i);
    		JRDesignElement jrElement = (JRDesignElement)element.getValue();
        if (jrElement.getY() - 5*i > 0)
        {
	        	SetValueCommand setCommand = new SetValueCommand();
	    			setCommand.setTarget(element);
	    			setCommand.setPropertyId(JRDesignElement.PROPERTY_Y);
	    			setCommand.setPropertyValue(jrElement.getY() - 5*i);
	  	      command.add(setCommand);
        }
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
