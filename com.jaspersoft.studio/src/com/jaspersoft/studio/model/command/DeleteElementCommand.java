/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteElementCommand extends Command {

	/** The jr group. */
	private JRElementGroup jrGroup;

	/** The jr element. */
	private JRDesignElement jrElement;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteElementCommand(ANode destNode, MGraphicElement srcNode) {
		super();
		this.jrElement = (JRDesignElement) srcNode.getValue();
		this.jrGroup = jrElement.getElementGroup();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrGroup.getChildren().indexOf(jrElement);
		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElement(jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElement(jrElement);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrGroup instanceof JRDesignElementGroup) {
			if (elementPosition > ((JRDesignElementGroup) jrGroup).getChildren().size())
				((JRDesignElementGroup) jrGroup).addElement(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElement(elementPosition, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			if (elementPosition > ((JRDesignFrame) jrGroup).getChildren().size())
				((JRDesignFrame) jrGroup).addElement(jrElement);
			else
				((JRDesignFrame) jrGroup).addElement(elementPosition, jrElement);
		}
	}
}
