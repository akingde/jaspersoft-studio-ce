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

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
/*/*
 * The Class ReorderElementGroupCommand.
 */
public class ReorderElementGroupCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;
	
	/** The jr element. */
	private JRDesignElementGroup jrElement;
	
	/** The jr group. */
	private JRElementGroup jrGroup;

	/**
	 * Instantiates a new reorder element group command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderElementGroupCommand(MElementGroup child, ANode parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = newIndex;
		this.jrElement = (JRDesignElementGroup) child.getValue();
		this.jrGroup = jrElement.getElementGroup();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrGroup.getChildren().indexOf(jrElement);

		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElementGroup(jrElement);
			if (newIndex < 0 || newIndex > jrGroup.getChildren().size())
				((JRDesignElementGroup) jrGroup).addElementGroup(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElementGroup(newIndex, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElementGroup(jrElement);
			if (newIndex < 0 || newIndex > jrGroup.getChildren().size())
				((JRDesignFrame) jrGroup).addElementGroup(jrElement);
			else
				((JRDesignFrame) jrGroup).addElementGroup(newIndex, jrElement);
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		if (jrGroup instanceof JRDesignElementGroup) {
			((JRDesignElementGroup) jrGroup).removeElementGroup(jrElement);
			if (oldIndex < 0 || oldIndex > jrGroup.getChildren().size())
				((JRDesignElementGroup) jrGroup).addElementGroup(jrElement);
			else
				((JRDesignElementGroup) jrGroup).addElementGroup(oldIndex, jrElement);
		} else if (jrGroup instanceof JRDesignFrame) {
			((JRDesignFrame) jrGroup).removeElementGroup(jrElement);
			if (oldIndex < 0 || oldIndex > jrGroup.getChildren().size())
				((JRDesignFrame) jrGroup).addElementGroup(jrElement);
			else
				((JRDesignFrame) jrGroup).addElementGroup(oldIndex, jrElement);
		}
	}

}
