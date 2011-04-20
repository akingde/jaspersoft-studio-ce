/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.gef.commands;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGraphicElement;
/*
 * The Class SetConstraintCommand.
 */
public class SetPageConstraintCommand extends Command {

	/** The new bounds. */
	private Rectangle newBounds;

	/** The old bounds. */
	private Rectangle oldBounds;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The parent bounds. */
	private Rectangle parentBounds;

	/**
	 * Sets the context.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 * @param constraint
	 *          the constraint
	 */
	public void setContext(ANode parent, ANode child, Rectangle constraint) {
		if (child.getValue() instanceof JRDesignElement) {
			jrElement = (JRDesignElement) child.getValue();
			newBounds = constraint;
			parentBounds = ((IGraphicElement) child).getBounds();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {

		if (jrElement != null) {
			oldBounds = new Rectangle(jrElement.getX(), jrElement.getY(), jrElement.getWidth(), jrElement.getHeight());
			// check position,
			// if top-left corner outside the bottom bar bands, move to bottom band
			// if bottom-left corner outside the top bar, move to top band
			int y = jrElement.getY() + newBounds.y - parentBounds.y;

			int x = jrElement.getX() + newBounds.x - parentBounds.x;

			jrElement.setX(x);
			jrElement.setY(y);
			jrElement.setWidth(newBounds.width);
			jrElement.setHeight(newBounds.height);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (jrElement != null) {
			jrElement.setWidth(oldBounds.width);
			jrElement.setHeight(oldBounds.height);
			jrElement.setX(oldBounds.x);
			jrElement.setY(oldBounds.y);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#getLabel()
	 */
	public String getLabel() {
		if (oldBounds != null && (oldBounds.x != newBounds.x || oldBounds.y != newBounds.y))
			return "set location"; //$NON-NLS-1$
		return "resize"; //$NON-NLS-1$
	}
}
