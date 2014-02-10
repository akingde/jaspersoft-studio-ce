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

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.messages.Messages;

public class OrganizeAsTableAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "organizeastable"; //$NON-NLS-1$
	
	public OrganizeAsTableAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.OrganizeAsTableAction_actionName);
		setToolTipText(Messages.OrganizeAsTableAction_actionDescription);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/organize_as_table.png"));  //$NON-NLS-1$
		setId(ID);
	}

	@Override
	protected boolean calculateEnabled() {
		return getOperationSet().size()>0;
	} 

  	
	protected Command createAlignmentCommand() {
		List<APropertyNode> nodes = getOperationSet();
		CompoundCommand command = new CompoundCommand();
		command.setDebugLabel(getText());
		
		if (nodes.isEmpty()) return command;
	  nodes = sortXY(nodes);
	  
	  int currentX = 0;
	  command.add(AlignMarginTopAction.generateCommand(nodes));
	  for (APropertyNode element : nodes)
	  {
	      // 1. Find the parent...
	      Rectangle oldBounds = getElementBounds((JRDesignElement)element.getValue());
  			SetValueCommand setCommand = new SetValueCommand();
  			setCommand.setTarget(element);
  			setCommand.setPropertyId(JRDesignElement.PROPERTY_X);
  			setCommand.setPropertyValue(currentX);
	      command.add(setCommand);
	      currentX += oldBounds.width+ 5;
	  }
	  command.add(SameHeightMinAction.generateCommand(nodes));
	 
		return command;
	}

}
