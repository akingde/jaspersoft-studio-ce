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
package com.jaspersoft.studio.formatting.actions;

import java.util.List;

import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.SetValueCommand;

public class SameWidthMaxAction extends AbstractFormattingAction {

	/** The Constant ID. */
	public static final String ID = "matchwidthmax"; //$NON-NLS-1$

	public SameWidthMaxAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.SameWidthMaxAction_actionName);
		setToolTipText(Messages.SameWidthMaxAction_actionDescription);
		setId(ID);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/matchwidthmax.png")); //$NON-NLS-1$
	}

	@Override
	protected boolean calculateEnabled() {
		return getOperationSet().size() > 1;
	}

	public static CompoundCommand generateCommand(List<APropertyNode> nodes) {
		CompoundCommand command = new CompoundCommand();

		int width = (Integer) nodes.get(0).getPropertyValue(JRDesignElement.PROPERTY_WIDTH);

		// Find the smallest one...
		for (int i = 1; i < nodes.size(); ++i) {
			if (nodes.get(i).getValue() instanceof JRDesignElement) {
				JRDesignElement element = (JRDesignElement) nodes.get(i).getValue();
				if (width < element.getWidth())
					width = element.getWidth();
			} else if (nodes.get(i).getValue() instanceof StandardColumn) {
				StandardColumn element = (StandardColumn) nodes.get(i).getValue();
				if (width < element.getWidth())
					width = element.getWidth();
			}
		}

		for (APropertyNode node : nodes) {
			SetValueCommand setCommand = new SetValueCommand();
			setCommand.setTarget(node);
			setCommand.setPropertyId(JRDesignElement.PROPERTY_WIDTH);
			setCommand.setPropertyValue(width);
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
