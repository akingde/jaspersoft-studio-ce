/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.command;

import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.model.ANode;

public class DeleteCalloutCommand extends Command {
	private ANode parent;
	private MCallout mcallout;

	public DeleteCalloutCommand(ANode parent, MCallout child) {
		super("Delete Callout");
		this.parent = (ANode)MCallout.getPinPropertyHolderNode(parent);
		this.mcallout = child;
	}

	@Override
	public void execute() {
		mcallout.deleteCallout();
	}

	@Override
	public void undo() {
		mcallout.setParent(parent, -1);
		mcallout.setPropertyValue(JRDesignElement.PROPERTY_X, mcallout.getPropertyValue(JRDesignElement.PROPERTY_X));
		parent.getPropertyChangeSupport()
				.fireIndexedPropertyChange(JRDesignElementGroup.PROPERTY_CHILDREN, -1, true, false);
	}

	@Override
	public boolean canExecute() {
		return true;
	}
}
