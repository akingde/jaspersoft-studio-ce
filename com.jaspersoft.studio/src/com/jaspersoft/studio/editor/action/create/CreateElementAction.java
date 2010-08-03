/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.action.create;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.model.IIconDescriptor;

// TODO: Auto-generated Javadoc
/**
 * The Class CreateElementAction.
 */
public abstract class CreateElementAction extends Action {
	
	/** The cmd. */
	private Command cmd;
	
	/** The parent. */
	private Object parent;
	
	/** The location. */
	private Point location;
	
	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the element action.
	 * 
	 * @param iconDescriptor
	 *          the icon descriptor
	 */
	public CreateElementAction(IIconDescriptor iconDescriptor) {
		super(iconDescriptor.getTitle(), iconDescriptor.getIcon16());
		setToolTipText(iconDescriptor.getDescription());
		setDescription(iconDescriptor.getDescription());
	}

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		return null;
	}

	/**
	 * Sets the command.
	 * 
	 * @param cmd
	 *          the new command
	 */
	public void setCommand(Command cmd) {
		this.cmd = cmd;
	}

	/**
	 * Gets the command.
	 * 
	 * @return the command
	 */
	public Command getCommand() {
		return cmd;
	}

	/**
	 * Drop into.
	 * 
	 * @param parent
	 *          the parent
	 * @param location
	 *          the location
	 * @param index
	 *          the index
	 */
	public void dropInto(Object parent, Point location, int index) {
		this.parent = parent;
		this.location = location;
		this.index = index;
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Gets the parent.
	 * 
	 * @return the parent
	 */
	public Object getParent() {
		return parent;
	}

	/**
	 * Gets the location.
	 * 
	 * @return the location
	 */
	public Point getLocation() {
		return location;
	}

}
