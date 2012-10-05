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
package com.jaspersoft.studio.components.table.model.column.command;

import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
/*
 * The Class OrphanElementGroupCommand.
 * 
 * @author Chicu Veaceslav
 */
public class OrphanColumn4GroupCommand extends Command {

	/** The index. */
	private int index;

	private StandardBaseColumn jrColumn;
	protected StandardTable jrTable;
	private StandardColumnGroup jrGroup;

	/**
	 * Instantiates a new orphan element group command.
	 * 
	 * @param parent
	 *          the parent
	 * @param child
	 *          the child
	 */
	public OrphanColumn4GroupCommand(MColumnGroup parent, MColumn child) {
		super(Messages.common_orphan_element);
		this.jrGroup = (StandardColumnGroup) parent.getValue();
		this.jrColumn = (StandardBaseColumn) child.getValue();
		this.jrTable = TableManager.getTable(parent.getMTable());
	}

	public OrphanColumn4GroupCommand(MColumnGroupCell parent, MColumn child) {
		super(Messages.common_orphan_element);
		this.jrGroup = (StandardColumnGroup) parent.getValue();
		this.jrColumn = (StandardBaseColumn) child.getValue();
		this.jrTable = TableManager.getTable(parent.getMTable());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrGroup.getColumns().indexOf(jrColumn);
		jrGroup.removeColumn(jrColumn);
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, -jrColumn.getWidth());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index >= 0 && index <= jrGroup.getColumns().size())
			jrGroup.addColumn(index, jrColumn);
		else
			jrGroup.addColumn(jrColumn);
		TableColumnSize.setGroupWidth2Top(jrTable.getColumns(), jrGroup, jrColumn.getWidth());
	}
}
