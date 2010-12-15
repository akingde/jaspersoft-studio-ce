/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.table.model.column.command;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.table.model.column.MColumn;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.table.model.columngroup.MColumnGroupCell;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteColumnFromGroupCommand extends Command {

	private StandardColumnGroup jrGroup;
	private StandardBaseColumn jrColumn;

	/** The element position. */
	private int elementPosition = 0;

	public DeleteColumnFromGroupCommand(MColumnGroup destNode, MColumn srcNode) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	public DeleteColumnFromGroupCommand(MColumnGroupCell destNode, MColumn srcNode) {
		super();
		this.jrGroup = (StandardColumnGroup) destNode.getValue();
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrGroup.getColumns().indexOf(jrColumn);

		jrGroup.removeColumn(jrColumn);
		jrGroup.setWidth(jrGroup.getWidth() - jrColumn.getWidth());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrColumn == null)
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
		if (elementPosition < 0 || elementPosition > jrGroup.getColumns().size())
			jrGroup.addColumn(elementPosition, jrColumn);
		else
			jrGroup.addColumn(jrColumn);
		jrGroup.setWidth(jrGroup.getWidth() + jrColumn.getWidth());
	}

}
