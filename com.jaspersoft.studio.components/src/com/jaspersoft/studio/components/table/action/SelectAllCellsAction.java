/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.GroupCell;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.editor.TableEditor;
import com.jaspersoft.studio.components.table.messages.Messages;
import com.jaspersoft.studio.components.table.model.AMCollection;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.action.ACachedSelectionAction;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MPage;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 *
 */
public class SelectAllCellsAction extends ACachedSelectionAction {

	/**
	 * The id of the action
	 */
	public static final String ID = "com.jaspersoft.studio.components.table.action.SelectAllCellsAction"; //$NON-NLS-1$

	public SelectAllCellsAction(IWorkbenchPart part) {
		super(part);
		setText(Messages.SelectAllCellsAction_1);
		setId(ID);
	}

	/**
	 * The action is enable only if enabled if and only if the first element of
	 * the selection is a TableEditPart with inside an MTable
	 */
	@Override
	protected boolean calculateEnabled() {
		List<?> tables = getSelectedObjects();
		for (Object obj : tables) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (obj instanceof MTable){
				MTable table = (MTable)obj;
				if (table != null){
					StandardTable jrTable = table.getStandardTable();
					if ((table.getParent() instanceof MPage) && jrTable.getColumns().size()>0){
						return true;
					}
				}
			} else if (obj instanceof MColumn || obj instanceof AMCollection){
				MTable table = TableManager.getTableNode((ANode)obj);
				if (table != null){
					StandardTable jrTable = table.getStandardTable();
					if ((table.getParent() instanceof MPage) && jrTable.getColumns().size()>0){
						return true;
					}
				}
			}
		}
		return false;
	}

	private StandardTable getTable() {
		List<?> tables = getSelectedObjects();
		for (Object obj : tables) {
			if (obj instanceof EditPart)
				obj = ((EditPart) obj).getModel();
			if (obj instanceof MTable)
				return ((MTable) obj).getStandardTable();
			if (obj instanceof MColumn)
				return ((MColumn) obj).getMTable().getStandardTable();
			if (obj instanceof AMCollection)
				return ((AMCollection) obj).getStandardTable();
		}
		return null;
	}

	/**
	 * Execute the action
	 */
	@Override
	public void run() {
		TableEditor part = (TableEditor) getWorkbenchPart();
		StandardTable tbl = getTable();
		List<Cell> tblels = getTableCells(tbl);
		List<EditPart> parts = new ArrayList<EditPart>();
		Map<?, ?> map = part.getGraphicalViewer().getEditPartRegistry();
		for (Object obj : map.keySet()) {
			if (obj instanceof MCell
					&& tblels.contains(((MCell) obj).getCell())) {
				EditPart ep = (EditPart) map.get(obj);
				if (ep != null)
					parts.add(ep);
			}
		}
		StructuredSelection s = new StructuredSelection(parts);

		part.getGraphicalViewer().setSelection(s);
	}

	public static List<Cell> getTableCells(StandardTable table) {
		List<Cell> list2 = new ArrayList<Cell>();
		getTableCell(table.getColumns(), list2);
		return list2;
	}

	public static void getTableCell(List<BaseColumn> cols, List<Cell> list2) {
		for (BaseColumn c : cols) {
			getTableCell(c.getTableHeader(), list2);
			getTableCell(c.getTableFooter(), list2);
			getTableCell(c.getColumnHeader(), list2);
			getTableCell(c.getColumnFooter(), list2);
			for (GroupCell gc : c.getGroupHeaders())
				if (gc != null)
					getTableCell(gc.getCell(), list2);
			for (GroupCell gc : c.getGroupFooters())
				if (gc != null)
					getTableCell(gc.getCell(), list2);
			if (c instanceof StandardColumn)
				getTableCell(((StandardColumn) c).getDetailCell(), list2);
			if (c instanceof StandardColumnGroup)
				getTableCell(((StandardColumnGroup) c).getColumns(), list2);
		}
	}

	public static void getTableCell(Cell cell, List<Cell> list2) {
		if (cell == null)
			return;
		list2.add(cell);
	}
}
