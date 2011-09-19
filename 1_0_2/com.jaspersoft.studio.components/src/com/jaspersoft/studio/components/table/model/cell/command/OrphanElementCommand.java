/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.table.model.cell.command;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.model.MGraphicElement;

public class OrphanElementCommand extends Command {

	/** The index. */
	private int index;
	private JRDesignElement jrElement;
	private DesignCell jrCell;

	/**
	 * Instantiates a new orphan element command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanElementCommand(MCell parent, MGraphicElement child) {
		super(Messages.common_orphan_child);
		this.jrElement = (JRDesignElement) child.getValue();
		this.jrCell = parent.getCell();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrCell.getChildren().indexOf(jrElement);
		jrCell.removeElement(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index >= 0 && index <= jrCell.getChildren().size())
			jrCell.addElement(index, jrElement);
		else
			jrCell.addElement(jrElement);
	}

}
