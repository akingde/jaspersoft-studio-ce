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
package com.jaspersoft.studio.model.command;

import net.sf.jasperreports.engine.JRElementGroup;
import net.sf.jasperreports.engine.design.JRDesignElementGroup;
import net.sf.jasperreports.engine.design.JRDesignFrame;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;

// TODO: Auto-generated Javadoc
/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateElementGroupCommand extends Command {
	
	/** The jr element. */
	private JRDesignElementGroup jrElement;
	
	/** The jr group. */
	private JRElementGroup jrGroup;
	
	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the element group command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateElementGroupCommand(ANode destNode, MElementGroup srcNode, int index) {
		this.jrElement = (JRDesignElementGroup) srcNode.getValue();
		if (jrElement == null)
			jrElement = srcNode.createJRElementGroup();
		this.jrGroup = (JRElementGroup) destNode.getValue();
		this.index = index;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrElement != null) {
			// set on JRElementGroup ...
			if (index < 0)
				index = jrGroup.getChildren().size();
			if (jrGroup instanceof JRDesignElementGroup) {
				if (index < 0 || index > jrGroup.getChildren().size())
					((JRDesignElementGroup) jrGroup).addElementGroup(jrElement);
				else
					((JRDesignElementGroup) jrGroup).addElementGroup(index, jrElement);
			} else if (jrGroup instanceof JRDesignFrame) {
				if (index < 0 || index > jrGroup.getChildren().size())
					((JRDesignFrame) jrGroup).addElementGroup(jrElement);
				else
					((JRDesignFrame) jrGroup).addElementGroup(index, jrElement);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (jrGroup instanceof JRDesignElementGroup)
			((JRDesignElementGroup) jrGroup).removeElementGroup(jrElement);
		else if (jrGroup instanceof JRDesignFrame)
			((JRDesignFrame) jrGroup).removeElementGroup(jrElement);
	}
}
