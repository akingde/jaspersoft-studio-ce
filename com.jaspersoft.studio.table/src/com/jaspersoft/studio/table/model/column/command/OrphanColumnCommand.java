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
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.table.model.AMCollection;
import com.jaspersoft.studio.table.messages.Messages;
import com.jaspersoft.studio.table.model.column.MColumn;
/*
 * The Class OrphanElementGroupCommand.
 * 
 * @author Chicu Veaceslav
 */
public class OrphanColumnCommand extends Command {

	/** The index. */
	private int index;

	private StandardBaseColumn jrColumn;
	private StandardTable jrTable;

	/**
	 * Instantiates a new orphan element group command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanColumnCommand(AMCollection parent, MColumn child) {
		super(Messages.common_orphan_element);
		this.jrTable = CreateColumnCommand.getTable(parent);
		this.jrColumn = (StandardBaseColumn) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrTable.getColumns().indexOf(jrColumn);
		jrTable.removeColumn(jrColumn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index >= 0 && index <= jrTable.getColumns().size())
			jrTable.addColumn(index, jrColumn);
		else
			jrTable.addColumn(jrColumn);
	}
}
