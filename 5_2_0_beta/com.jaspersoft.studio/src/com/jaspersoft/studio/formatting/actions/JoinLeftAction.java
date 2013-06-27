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

public class JoinLeftAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "joinleft"; //$NON-NLS-1$
	
	public JoinLeftAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.JoinLeftAction_actionName);
		setToolTipText(Messages.JoinLeftAction_actionDescription);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/joinleft.png"));  //$NON-NLS-1$
	}
	
	public static CompoundCommand generateCommand(List<APropertyNode> nodes){
		CompoundCommand command = new CompoundCommand();

    if (nodes.isEmpty()) return command;
    List<APropertyNode> sortedElements = sortXY( nodes );
    
    JRDesignElement jrElement = (JRDesignElement)sortedElements.get(0).getValue();
    int actualX = jrElement.getX();
    for (APropertyNode element : sortedElements)
    {
    	 	jrElement = (JRDesignElement)element.getValue();
        SetValueCommand setCommand = new SetValueCommand();
  			setCommand.setTarget(element);
  			setCommand.setPropertyId(JRDesignElement.PROPERTY_X);
  			setCommand.setPropertyValue(actualX);
        actualX += jrElement.getWidth();
	      command.add(setCommand);
    }

    return command;
	}

	@Override
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

	@Override
	protected boolean calculateEnabled() {
		return getOperationSet().size()>1;
	}

}
