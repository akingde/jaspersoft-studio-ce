/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.Iterator;
import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.MTableGroupFooter;
import com.jaspersoft.studio.components.table.model.MTableGroupHeader;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroup;
import com.jaspersoft.studio.components.table.model.columngroup.MColumnGroupCell;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateColumnCommand extends Command {

	private StandardBaseColumn jrColumn;
	protected StandardColumnGroup pColGroup;
	protected StandardTable jrTable;
	protected JasperDesign jrDesign;
	private int index;

	public CreateColumnCommand(AMCollection destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateColumnCommand(MTableGroupHeader destNode, MColumn srcNode,
			int index) {
		this((ANode) destNode.getParent(), srcNode, index);
	}

	public CreateColumnCommand(MTableGroupFooter destNode, MColumn srcNode,
			int index) {
		this((ANode) destNode.getParent(), srcNode, index);
	}

	public CreateColumnCommand(MTable destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
	}

	public CreateColumnCommand(MColumn destNode, MColumn srcNode, int index) {
		this((ANode) destNode, srcNode, index);
		this.index = index;// jrTable.getColumns().indexOf(destNode.getValue())
							// + 1;
	}

	protected CreateColumnCommand(ANode destNode, MColumn srcNode, int index) {
		super();
		this.jrTable = TableManager.getTable(destNode);
		this.index = index;
		this.jrColumn = (StandardBaseColumn) srcNode.getValue();
		this.jrDesign = destNode.getJasperDesign();
		if (destNode instanceof MColumnGroup
				|| destNode instanceof MColumnGroupCell)
			pColGroup = (StandardColumnGroup) destNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrColumn == null)
			jrColumn = createColumn(jrDesign, jrTable);
		if (pColGroup != null) {
			if (index >= 0 && index < pColGroup.getColumns().size())
				pColGroup.addColumn(index, jrColumn);
			else
				pColGroup.addColumn(jrColumn);
		} else {
			if (index >= 0 && index < jrTable.getColumns().size())
				jrTable.addColumn(index, jrColumn);
			else
				jrTable.addColumn(jrColumn);
		}
	}

	public StandardBaseColumn createColumn(JasperDesign jrDesign,
			StandardTable jrTable) {
		return CreateColumnCommand.addColumn(jrDesign, jrTable, true, true,
				true, true, true, true);
	}

	public static StandardColumn addColumn(JasperDesign jrDesign,
			StandardTable jrTable, boolean isTHead, boolean isTFoot,
			boolean isCHead, boolean isCFoot, boolean isGHead, boolean isGFoot) {
		StandardColumn col = new StandardColumn();
		col.setWidth(40);

		if (isTHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
					TableUtil.TABLE_HEADER, null));
			col.setTableHeader(cell);
		}

		if (isTFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
					TableUtil.TABLE_FOOTER, null));
			col.setTableFooter(cell);
		}

		if (isCHead) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
					TableUtil.COLUMN_HEADER, null));
			col.setColumnHeader(cell);
		}

		if (isCFoot) {
			DesignCell cell = new DesignCell();
			cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
					TableUtil.COLUMN_FOOTER, null));
			col.setColumnFooter(cell);
		}

		DesignCell cell = new DesignCell();
		cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
				TableUtil.COLUMN_DETAIL, null));
		col.setDetailCell(cell);

		List<?> groupsList = TableUtil.getGroupList(jrTable, jrDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				if (isGHead) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
							TableUtil.COLUMN_GROUP_HEADER, jrGroup.getName()));
					col.setGroupHeader(jrGroup.getName(), cell);
				}
				if (isGFoot) {
					cell = new DesignCell();
					cell.setHeight(TableColumnSize.getInitTableHeight(jrTable,
							TableUtil.COLUMN_GROUP_FOOTER, jrGroup.getName()));
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
		if (pColGroup != null) {
			index = pColGroup.getColumns().indexOf(jrColumn);
			pColGroup.removeColumn(jrColumn);
		} else {
			index = jrTable.getColumns().indexOf(jrColumn);
			jrTable.removeColumn(jrColumn);
		}
	}
}
