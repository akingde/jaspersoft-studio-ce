/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.callout.command;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;

import net.sf.jasperreports.engine.design.JRDesignElement;

public class CreateCalloutCommand extends Command {
	private Rectangle location;
	private ANode parent;
	private ANode originalTarget;
	private MCallout mcallout;

	public CreateCalloutCommand(ANode parent, MCallout child, Rectangle location, int newIndex) {
		super("Create Callout");
		this.location = location;
		this.originalTarget = parent;
		this.parent = (ANode)MCallout.getPinPropertyHolderNode(parent);
	}

	@Override
	public void execute() {
		// fix location if necessary
		if(this.location==null){
			this.location=new Rectangle(
					((IGraphicElement)originalTarget).getBounds().x,
					((IGraphicElement)originalTarget).getBounds().y, 
					MCallout.DEFAULT_WIDTH, MCallout.DEFAULT_HEIGHT);
		}
		if (location.width <= 0)
			location.width = MCallout.DEFAULT_WIDTH;
		if (location.height <= 0)
			location.height = MCallout.DEFAULT_HEIGHT;

		if (mcallout == null)
			mcallout = MCallout.createCallout(parent, location);
		else {
			mcallout.setParent(parent, -1);
			mcallout.setPropertyValue(JRDesignElement.PROPERTY_X, mcallout.getPropertyValue(JRDesignElement.PROPERTY_X));
		}
	}

	@Override
	public void undo() {
		mcallout.deleteCallout();
	}

	@Override
	public boolean canExecute() {
		return originalTarget instanceof IGraphicElement;
	}
}
