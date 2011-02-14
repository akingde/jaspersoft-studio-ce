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

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.table.model.AMCollection;
import com.jaspersoft.studio.table.model.MTableGroupFooter;
import com.jaspersoft.studio.table.model.MTableGroupHeader;
import com.jaspersoft.studio.table.model.column.MColumn;
import com.jaspersoft.studio.table.util.TableColumnSize;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnCommand extends Command {

	private StandardBaseColumn jrColumn;

	protected StandardTable jrTable;
	protected JasperDesign jrDesign;
	private int index;

	public static StandardTable getTable(ANode destNode) {
		if (destNode.getValue() instanceof JRDesignComponentElement) {
			JRDesignComponentElement tbl = (JRDesignComponentElement) destNode.getValue();
			if (tbl.getComponent() instanceof StandardTable) {
				return (StandardTable) tbl.getComponent();
			}
		}
		return null;
	}

	public CreateColumnCommand(AMCollection destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateColumnCommand(MTableGroupHeader destNode, MColumn srcNode, int index) {
		this((ANode) destNode.getParent(), srcNode, index);
	}

	public CreateColumnCommand(MTableGroupFooter destNode, MColumn srcNode, int index) {
		this((ANode) destNode.getParent(), srcNode, index);
	}

	public CreateColumnCommand(MColumn destNode, MColumn srcNode, int index) {
		this(destNode.getMTable(), srcNode, index);
		this.index = jrTable.getColumns().indexOf(destNode.getValue()) + 1;
		this.jrDesign = destNode.getJasperDesign();
	}

	protected CreateColumnCommand(ANode destNode, MColumn srcNode, int index) {
		super();
		this.jrTable = getTable(destNode);
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrDesign = destNode.getJasperDesign();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrColumn == null) {
			jrColumn = createColumn(jrDesign, jrTable);
		}
		if (index >= 0 && index < jrTable.getColumns().size())
			jrTable.addColumn(index, jrColumn);
		else
			jrTable.addColumn(jrColumn);
	}

	public StandardBaseColumn createColumn(JasperDesign jrDesign, StandardTable jrTable) {
		return CreateColumnCommand.addColumn(jrDesign, jrTable, true, true, true, true, true, true);
	}

	public static StandardColumn addColumn(JasperDesign jrDesign, StandardTable jrTable, boolean isTHead,
			boolean isTFoot, boolean isCHead, boolean isCFoot, boolean isGHead, boolean isGFoot) {
		StandardColumn col = new StandardColumn();
		col.setWidth(40);

		if (isTHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.TABLE_HEADER, null));
			col.setTableHeader(cell);
		}

		if (isTFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.TABLE_FOOTER, null));
			col.setTableFooter(cell);
		}

		if (isCHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_HEADER, null));
			col.setColumnHeader(cell);
		}

		if (isCFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_FOOTER, null));
			col.setColumnFooter(cell);
		}

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_DETAIL, null));
		col.setDetailCell(cell);

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				if (isGHead) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_GROUP_HEADER, jrGroup.getName()));
					col.setGroupHeader(jrGroup.getName(), cell);
				}
				if (isGFoot) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable, TableUtil.COLUMN_GROUP_FOOTER, jrGroup.getName()));
					col.setGroupFooter(jrGroup.getName(), cell);
				}
			}

		return col;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		index = jrTable.getColumns().indexOf(jrColumn);
		jrTable.removeColumn(jrColumn);
	}
}
