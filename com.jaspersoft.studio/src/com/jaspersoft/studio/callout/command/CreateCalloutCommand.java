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
package com.jaspersoft.studio.callout.command;

import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.callout.MCallout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IContainerLayout;
import com.jaspersoft.studio.model.INode;

public class CreateCalloutCommand extends Command {
	private Rectangle location;
	private ANode parent;
	private MCallout mcallout;

	public CreateCalloutCommand(ANode parent, MCallout child, Rectangle location, int newIndex) {
		super("Create Callout");
		this.location = location;
		this.parent = getPropertyHolder((ANode) parent.getRoot());
	}

	public static ANode getPropertyHolder(ANode parent) {
		if (parent instanceof IContainerLayout)
			return parent;
		List<INode> children = parent.getChildren();
		if (children != null && !children.isEmpty()) {
			for (INode n : children) {
				ANode p = getPropertyHolder((ANode) n);
				if (p != null)
					return p;
			}
		}
		return null;
	}

	@Override
	public void execute() {
		if (location.width <= 0)
			location.width = 200;
		if (location.height <= 0)
			location.height = 200;

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
		return true;
	}
}
