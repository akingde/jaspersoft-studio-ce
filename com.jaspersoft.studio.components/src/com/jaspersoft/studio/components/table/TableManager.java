/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.Column;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.ColumnVisitor;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

public class TableManager {
	private StandardTable table;
	private TableUtil tableUtil;
	private TableMatrix mh = new TableMatrix();
	private JasperDesign jDesign;
	
	public TableMatrix getMatrixHelper() {
		return mh;
	}

	public Rectangle getYhcolumn(int type, String grName, BaseColumn bc) {
		return mh.getYHColumn(bc, type, Misc.nvl(grName));
	}

	public TableManager(JRDesignComponentElement tbl, JasperDesign jasperDesign) {
		this((StandardTable) tbl.getComponent(), jasperDesign);
	}

	public TableManager(StandardTable table, JasperDesign jDesign) {
		this.table = table;
		this.jDesign = jDesign;
		this.tableUtil = new TableUtil(table, jDesign);

		initMaps();

		tableUtil.init(table);
		setSize();
	}

	public void initMaps() {
		mh = new TableMatrix();
		mh.fillMatrix(table, tableUtil);
		// mh.print();
	}

	public void refresh() {
		tableUtil.refresh();
		setSize();
	}

	private Dimension size;

	public Dimension getSize() {
		return size;
	}

	public void setSize() {
		int xmin = 0;
		int xmax = 0;
		int ymin = 0;
		int ymax = 0;
		for (java.awt.Rectangle r : tableUtil.getCellBounds().values()) {
			if (xmin > r.x)
				xmin = r.x;
			if (xmax < r.x + r.width)
				xmax = r.x + r.width;
			if (ymin > r.y)
				ymin = r.y;
			if (ymax < r.y + r.height)
				ymax = r.y + r.height;
		}
		size = new Dimension(xmax - xmin, ymax - ymin);
	}

	public Cell getCell(Point location) {
		Map<Cell, java.awt.Rectangle> cellBounds = tableUtil.getCellBounds();
		for (Cell cell : cellBounds.keySet()) {
			Rectangle r = getAWT2SWTRectangle(cellBounds.get(cell));
			if (r.x <= location.x && r.x + r.width >= location.x
					&& r.y <= location.y && r.y + r.height >= location.y)
				return cell;
		}
		return null;
	}

	public Rectangle getCellBounds(Cell cell) {
		return getAWT2SWTRectangle(tableUtil.getCellBounds().get(cell));
	}

	public static Rectangle getAWT2SWTRectangle(java.awt.Rectangle rect) {
		if (rect != null)
			return new Rectangle(rect.x, rect.y, rect.width, rect.height);
		return null;
	}

	public static List<BaseColumn> getAllColumns(MTable mTable) {
		JRDesignComponentElement tbl = (JRDesignComponentElement) mTable
				.getValue();
		if (tbl.getComponent() instanceof StandardTable) {
			StandardTable table = (StandardTable) tbl.getComponent();
			return TableUtil.getAllColumns(table.getColumns());
		}
		return new ArrayList<BaseColumn>(0);
	}

	public Rectangle getBounds(StandardBaseColumn col, int type, String grName) {
		Rectangle p = mh.getYHColumn(col, type, grName);
		if (p == null)
			p = new Rectangle(0, 100, col.getWidth(), 0);
		return p;
	}

	public Rectangle getBounds(int width, Cell cell, StandardBaseColumn col) {
		Rectangle b = getAWT2SWTRectangle(tableUtil.getCellBounds().get(cell));
		if (b != null)
			return b;
		int w = col != null ? col.getWidth() : 0;
		int h = cell != null ? cell.getHeight() : 0;
		return new Rectangle(0, 0, w, h);
	}

	public void setWidth(StandardBaseColumn cell, int width) {
		if (width >= 0) {
			int delta = width - cell.getWidth();
			if (cell instanceof StandardColumn)
				setGroupHeaderWidth(table.getColumns(), cell, delta);
			else if (cell instanceof StandardColumnGroup)
				setColumnGroupWidth((StandardColumnGroup) cell, delta);
			cell.setWidth(width);
		}
	}
	
	/**
	 * Set the width of this column, if it is a column group also its internal
	 * columns are resized to fill the group. They are resized keeping the ratio
	 * between them 
	 */
	public void setProportionalWidth(StandardBaseColumn column, int width) {
		if (width >= 0) {
			int delta = width - column.getWidth();
			//Look if it is inside a 
			if (column instanceof StandardColumn)
				setGroupHeaderWidth(table.getColumns(), column, delta);
			if (column instanceof StandardColumnGroup)
				setProportionalColumnGroupWidth((StandardColumnGroup) column, width);
			column.setWidth(width);
		}
	}

	/**
	 * Return the width of all the column in the table
	 */
	public int getColumnsTotalWidth(){
		int currentColumnsWidth = 0;
		for(BaseColumn col : table.getColumns()){
			currentColumnsWidth += col.getWidth();
		}
		return currentColumnsWidth;
	}
	
	private int[] getColumnsProportionalWidth(List<BaseColumn> columns, int newWidth){
		int[] proportionalWidths = new int[columns.size()];
		int index = 0;
		int currentColumnsWidth = 0;
		for(BaseColumn col : columns){
			currentColumnsWidth += col.getWidth();
		}
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;			
		for(BaseColumn col : columns){
			float proportionalFactor = (float)col.getWidth() / (float)currentColumnsWidth;
			//casting to int is the same to do the floor operation, since it drop the decimal
			int proportionalWidth = (int)(proportionalFactor * newWidth);
			proportionalWidths[index] = proportionalWidth;
			columnsTotalWidth += proportionalWidth;
			index ++;
		}
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		index = 0;
		while (remains > 0 && proportionalWidths.length > 0){
			proportionalWidths[index]++;
			index++;
			remains--;
			if (index == proportionalWidths.length){
				index = 0;
			}
		}
		return proportionalWidths;
	}
	
	/**
	 * Resize all the columns in the table to match the passed width
	 * 
	 * 
	 * @param newWidth the width that all the columns should have
	 * @param isProportional if the columns are resized proportionally or they all will have the same width
	 */
	public void fillSpace(int newWidth, boolean isProportional){
		//Only one of this operation allowed on the at the same time
		synchronized (table) {
			int currentColumnsWidth = 0;
			for(BaseColumn col : table.getColumns()){
				currentColumnsWidth += col.getWidth();
			}
			if (currentColumnsWidth == newWidth) {
				return;
			} else if(isProportional) {
				
				int[] proportionalWidths = getColumnsProportionalWidth(table.getColumns(), newWidth);
				
				//Phase 3: resize the columns
				int index = 0;
				ILayout defaultLayout = new VerticalRowLayout();
				for(BaseColumn col : table.getColumns()){
					if (col.getWidth() != proportionalWidths[index]){
						setProportionalWidth((StandardBaseColumn)col, proportionalWidths[index]);
						for(Entry<Cell, Integer> cell : getColumnCell(col).entrySet()){
							ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { cell.getKey() }, null, null, defaultLayout);
							layout.layout(cell.getKey().getElements(), new Dimension(cell.getValue(), ((DesignCell)cell.getKey()).getHeight()));
						}
					}
					index++;
				}
			} else {
				int columnsSize = newWidth / table.getColumns().size();
				int extraSpace = newWidth % table.getColumns().size();
				ILayout defaultLayout = new VerticalRowLayout();
				for(BaseColumn col : table.getColumns()){
					int additionalSpace = 0;
					if (extraSpace > 0){
						additionalSpace = 1;
						extraSpace--;
					}
					int newColumnWidth = col.getWidth() + additionalSpace + columnsSize;
					if (newColumnWidth != col.getWidth()){
						setWidth((StandardBaseColumn)col, newColumnWidth);
						for(Entry<Cell, Integer> cell : getColumnCell(col).entrySet()){
							ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { cell.getKey() }, null, null, defaultLayout);
							layout.layout(cell.getKey().getElements(), new Dimension(cell.getValue(), ((DesignCell)cell.getKey()).getHeight()));
						}
					}
				}
			}
		}
	}
	
	/**
	 * Return all the cell inside a column. If it is a group it
	 * recursively return also the other cell
	 * 
	 * @return a not null has map for every cell found where the key
	 * is a cell and the value is its width
	 */
	private HashMap<Cell, Integer> getColumnCell(BaseColumn cell){
		HashMap<Cell, Integer> result = new HashMap<Cell, Integer>();
		if (cell instanceof StandardBaseColumn){
			StandardBaseColumn col = (StandardBaseColumn)cell;
			if (col.getColumnHeader() != null) result.put(col.getColumnHeader(), col.getWidth());
			if (col.getTableHeader() != null) result.put(col.getTableHeader(), col.getWidth());
			if (col.getTableFooter() != null) result.put(col.getTableFooter(), col.getWidth());
			if (col.getColumnFooter() != null) result.put(cell.getColumnFooter(), col.getWidth());
		}
		if (cell instanceof StandardColumn){
			StandardColumn col = (StandardColumn)cell;
			if (col.getDetailCell() != null) result.put(col.getDetailCell(), col.getWidth());
		} else if (cell instanceof StandardColumnGroup){
			StandardColumnGroup group = (StandardColumnGroup)cell;
			List<BaseColumn> columns = group.getColumns();
			for(BaseColumn groupCol : columns){
				result.putAll(getColumnCell(groupCol));
			}
		}
		return result;
	}

	private boolean setColumnGroupWidth(StandardColumnGroup cell, int delta) {
		List<BaseColumn> columns = cell.getColumns();
		for (ListIterator<BaseColumn> it = columns.listIterator(columns.size()); it
				.hasPrevious();) {
			StandardBaseColumn bc = (StandardBaseColumn) it.previous();
			boolean c = false;
			if (delta < 0 && Math.abs(delta) > bc.getWidth()) {
				delta += bc.getWidth();
				bc.setWidth(0);
				c = true;
			} else
				bc.setWidth(bc.getWidth() + delta);
			if (bc instanceof StandardColumnGroup)
				setColumnGroupWidth((StandardColumnGroup) bc, delta);
			if (!c)
				return true;
		}
		return false;
	}
	
	private void setProportionalColumnGroupWidth(StandardColumnGroup columnGroup, int width) {
		List<BaseColumn> columns = columnGroup.getColumns();
		int[] proportionalNewWidth = getColumnsProportionalWidth(columns, width);
		int index = 0;
		for (BaseColumn col : columns) {
			StandardBaseColumn bc = (StandardBaseColumn) col;
			bc.setWidth(proportionalNewWidth[index]);
			if (bc instanceof StandardColumnGroup){
				setProportionalColumnGroupWidth((StandardColumnGroup) bc, proportionalNewWidth[index]);
			}
			index++;
		}
	}
	
	/**
	 * Search a group header from a cell contained in it and when it is bound change
	 * the size  of his width to increase or decrease of the same delta of the cell
	 * 
	 * @param col the columns set where the cell is searched
	 * @param cell the cell to search
	 * @param delta how much the cell increased or decreased
	 * @return used in the recursive iteration to know if the cell was found inside a specific group
	 */
	private boolean setGroupHeaderWidth(List<BaseColumn> col, StandardBaseColumn cell, int delta) {
		for (BaseColumn bc : col) {
			if (bc instanceof StandardColumnGroup) {
				StandardColumnGroup scg = (StandardColumnGroup) bc;
				boolean b = setGroupHeaderWidth(scg.getColumns(), cell, delta);
				if (b) {
					scg.setWidth(scg.getWidth() + delta);
					return true;
				}
			}
			if (cell == bc)
				return true;
		}
		return false;
	}

	public void setHeight(DesignCell cell, int height, BaseColumn col,
			int type, String grName) {
		if (height >= 0) {
			int delta = 0;
			if (cell == null)
				delta = height - getYhcolumn(type, grName, col).height;
			else
				delta = height - cell.getHeight();
			if (delta != 0)
				setRowHeight(table.getColumns(), delta, type, grName, col);
		}
	}

	private int setRowHeight(List<BaseColumn> columns, int delta, int type,
			String grName, BaseColumn col) {
		int dif = 0;
		for (BaseColumn bc : columns) {
			if (bc instanceof StandardColumn)
				dif = TableColumnSize.setCellHeightDelta(bc, type, grName,
						delta);
			else if (bc instanceof StandardColumnGroup) {
				if (col == bc) {
					TableColumnSize.setCellHeightDelta(bc, type, grName, delta);
				} else {
					dif = setRowHeight(((StandardColumnGroup) bc).getColumns(),
							delta, type, grName, col);
					if (delta < 0 && dif != 0)
						dif = TableColumnSize.setCellHeightDelta(bc, type,
								grName, dif);
				}
			}
		}
		return dif;
	}

	public void setRowsHeight(List<Integer> d) {
		int i = 0;
		List<?> groupsList = TableUtil.getGroupList(table, jDesign);
		setRowHeight(table.getColumns(), TableUtil.TABLE_HEADER, "", d.get(i++));
		setRowHeight(table.getColumns(), TableUtil.COLUMN_HEADER, "",
				d.get(i++));
		setRowHeight(table.getColumns(), TableUtil.COLUMN_DETAIL, "",
				d.get(i++));
		setRowHeight(table.getColumns(), TableUtil.COLUMN_FOOTER, "",
				d.get(i++));
		setRowHeight(table.getColumns(), TableUtil.TABLE_FOOTER, "", d.get(i++));
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				setRowHeight(table.getColumns(), TableUtil.COLUMN_GROUP_HEADER,
						jrGroup.getName(), d.get(i++));
				setRowHeight(table.getColumns(), TableUtil.COLUMN_GROUP_FOOTER,
						jrGroup.getName(), d.get(i++));
			}
	}

	public void setRowHeight(List<BaseColumn> cols, int type, String grName,
			int h) {
		for (BaseColumn bc : cols) {
			ColumnCell cc = mh.getColumnCell(new ColumnCell(type, grName, bc));
			Guide g = mh.getRowGuide(cc);
			List<ColumnCell> cells = isBottomOfTable(type) ? g.getPrev() : g
					.getNext();
			for (ColumnCell c : cells) {
				setRowHeight(cols, h, type, grName, c.column);
				return;
			}
		}
	}

	public void setColumnHeight(BaseColumn bc, List<Integer> deltas) {
		int i = 0;
		setCellHeight(bc, TableUtil.TABLE_HEADER, "", deltas.get(i++));
		setCellHeight(bc, TableUtil.COLUMN_HEADER, "", deltas.get(i++));
		setCellHeight(bc, TableUtil.COLUMN_DETAIL, "", deltas.get(i++));
		setCellHeight(bc, TableUtil.COLUMN_FOOTER, "", deltas.get(i++));
		setCellHeight(bc, TableUtil.TABLE_FOOTER, "", deltas.get(i++));
		List<?> groupsList = TableUtil.getGroupList(table, jDesign);
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				setCellHeight(bc, TableUtil.COLUMN_GROUP_HEADER,
						jrGroup.getName(), deltas.get(i++));
				setCellHeight(bc, TableUtil.COLUMN_GROUP_FOOTER,
						jrGroup.getName(), deltas.get(i++));
			}
	}

	public void setCellHeight(BaseColumn bc, int type, String grName, int delta) {
		DesignCell c = (DesignCell) TableUtil.getCell(bc, type, grName);
		if (c != null)
			c.setHeight(c.getHeight() + delta);
	}

	public static StandardTable getTable(ANode destNode) {
		if (destNode instanceof MTable)
			return ((MTable) destNode).getStandardTable();
		if (destNode instanceof MColumn)
			destNode = ((MColumn) destNode).getMTable();
		if (destNode.getValue() instanceof JRDesignComponentElement) {
			JRDesignComponentElement tbl = (JRDesignComponentElement) destNode
					.getValue();
			if (tbl.getComponent() instanceof StandardTable) {
				return (StandardTable) tbl.getComponent();
			}
		}
		return null;
	}
	
	/**
	 * Search starting from a node and going up in the hierarchy an MTable
	 * 
	 * @param currentNode a node, should be a node inside a table
	 * @return an MTable if it is in the upper hierarchy of the current node or null
	 */
	public static MTable getTableNode(ANode currentNode){
		if (currentNode == null) return null;
		else if (currentNode instanceof MTable) return (MTable)currentNode;
		else return getTableNode(currentNode.getParent());
	}

	public Dimension getCellPackSize(ColumnCell cc) {
		cc = mh.getColumnCell(cc);
		Guide g = cc.getEast();
		int w = -g.getY();
		for (ColumnCell c : g.getPrev()) {
			Cell cell = TableUtil.getCell(c.column, c.type, c.grName);
			if (cell != null) {
				List<JRChild> cells = cell.getChildren();
				if (!cells.isEmpty()) {
					int width = ModelUtils.getContainerSize(cells,
							new Dimension(0, 0)).width;

					w = Math.max(w, width - c.column.getWidth());
				}
			}
		}
		g = cc.getSouth();
		int h = -g.getY();
		for (ColumnCell c : g.getPrev()) {
			Cell cell = TableUtil.getCell(c.column, c.type, c.grName);
			if (cell != null) {
				List<JRChild> cells = cell.getChildren();
				if (!cells.isEmpty()) {
					int height = ModelUtils.getContainerSize(cells,
							new Dimension(0, 0)).height;

					h = Math.max(h, height - cell.getHeight());
				}
			}
		}
		Rectangle b = cc.getBounds();
		return new Dimension(b.width + w, b.height + h);
	}

	public void update() {
		initMaps();
		refresh();
	}

	public int getRowHeight(MColumn col) {
		return 100;
	}

	public int getRowHeight(ColumnCell ccel) {
		return mh.getRowHeight(mh.getColumnCell(ccel));
	}

	public static boolean isBottomOfTable(int type) {
		return type == TableUtil.COLUMN_FOOTER
				|| type == TableUtil.TABLE_FOOTER
				|| type == TableUtil.COLUMN_GROUP_FOOTER;
	}
	
	//SPAN CALCULATION CODE
	
	/**
	 * Class that allow to extract the cell of a specific type from a table column
	 */
	public static class ColumnCellSelector
	{
		/**
		 * The type that can be extracted from the column
		 */
		public enum TYPE{TABLE_HEADER, TABLE_FOOTER, COLUMN_HEADER, COLUMN_FOOTER, GROUP_HEADER, GROUP_FOOTER}
		
		/**
		 * Extract the cell of a specific type from a column
		 * 
		 * @param column the column, must be not null
		 * @param type the type of the cell, must be not null
		 * @param the name of the group, can be null but only if the type is different from group header or footer
		 * @return the extracted cell, can be null if the cell is not found
		 */
		public Cell getCell(Column column, TYPE type, String groupName)
		{
			return getCell((BaseColumn) column, type, groupName);
		}

		/**
		 * {@link #getCell(Column, TYPE, String)}
		 */
		public Cell getCell(ColumnGroup group, TYPE type, String groupName)
		{
			return getCell((BaseColumn) group, type, groupName);
		}
		
		/**
		 * {@link #getCell(Column, TYPE, String)}
		 */
		protected Cell getCell(BaseColumn column, TYPE type, String groupName){
			if (type == TYPE.TABLE_HEADER){
				return column.getTableHeader();
			} else if (type == TYPE.TABLE_FOOTER){
				return column.getTableFooter();
			} else if (type == TYPE.COLUMN_FOOTER){
				return column.getColumnFooter();
			} else if (type == TYPE.COLUMN_HEADER){
				return column.getColumnHeader();
			} else if (type == TYPE.GROUP_FOOTER){
				return column.getGroupFooter(groupName);
			} else if (type == TYPE.GROUP_HEADER){
				return column.getGroupHeader(groupName);
			}
			return null;
		}
	}
	
	/**
	 * The cell selector used to visit a column and extract the cell
	 */
	protected ColumnCellSelector cellSelector = new ColumnCellSelector();
	
	/**
	 * Return a map of the cells in the table header with the appropriate span value
	 * 
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getTableHeaderSpans(){
		return verifyColumnHeights(table.getColumns(), ColumnCellSelector.TYPE.TABLE_HEADER);
	}
	
	/**
	 * Return a map of the cells in the table footer with the appropriate span value
	 * 
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getTableFooterSpans(){
		return verifyColumnHeights(table.getColumns(), ColumnCellSelector.TYPE.TABLE_FOOTER);
	}
	
	/**
	 * Return a map of the cells in the column footer with the appropriate span value
	 * 
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getColumnFooterSpans(){
		return verifyColumnHeights(table.getColumns(), ColumnCellSelector.TYPE.COLUMN_FOOTER);
	}

	/**
	 * Return a map of the cells in the column header with the appropriate span value
	 * 
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getColumnHeaderSpans(){
		return verifyColumnHeights(table.getColumns(), ColumnCellSelector.TYPE.COLUMN_HEADER);
	}
	
	/**
	 * Return a map of the cells in the group header of a specific group, with the appropriate span value
	 * 
	 * @param groupName a not null group name
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getGroupHeaderSpans(String groupName){
		return verifyColumnHeights(table.getColumns(), ColumnCellSelector.TYPE.GROUP_HEADER, groupName);
	}

	/**
	 * Return a map of the cells in the group footer of a specific group, with the appropriate span value
	 * 
	 * @param groupName a not null group name
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getGroupFooterSpans(String groupName){
		return verifyColumnHeights(table.getColumns(), ColumnCellSelector.TYPE.GROUP_FOOTER, groupName);
	}

	/**
	 * Return a map of the cells in the table with the appropriate span value
	 * 
	 * @return a not null hash map where the key is a cell and the value
	 * is the required span for the cell
	 */
	public HashMap<Cell, Integer> getTableSpans(){
		HashMap<Cell, Integer> result = new HashMap<Cell, Integer>();
		result.putAll(getTableHeaderSpans());
		result.putAll(getTableFooterSpans());
		result.putAll(getColumnHeaderSpans());
		result.putAll(getColumnFooterSpans());
		
		JRDatasetRun datasetRun = table.getDatasetRun();
		if (datasetRun != null)
		{
			JRDesignDataset tableDataset = (JRDesignDataset) jDesign.getDatasetMap().get(datasetRun.getDatasetName());
			if (tableDataset!= null)
			{
				JRGroup[] groups = tableDataset.getGroups();
				if (groups != null)
				{
					for (int i = 0; i < groups.length; i++)
					{
						final String groupName = groups[i].getName();
						result.putAll(getGroupHeaderSpans(groupName));
						result.putAll(getGroupFooterSpans(groupName));
					}
				}
			}
		}
		return result;
	}
	
	/**
	 * Refresh the span value of all the cells in the table
	 */
	public void updateTableSpans(){
		HashMap<Cell, Integer> spans = getTableSpans();
		if (spans != null){
			for(Entry<Cell, Integer> value : spans.entrySet()){
				if (value.getKey().getRowSpan() != value.getValue()){
					((DesignCell)value.getKey()).setRowSpan(value.getValue());
				}
			}
		}
	}
	
	//Calculation methods
	
	/**
	 * Return a map of the cells extracted by the specified extractor form the set of columns, with the appropriate span value
	 * 
	 * @param columns a not null set of columns used to calculate the span, to have a correct result it should be all the columns 
	 * on the table, otherwise the count of the row could miss some informations
	 * @param type the section of the table used where the spans are calculated, using this method the section should not be a group header or footer
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	protected HashMap<Cell, Integer> verifyColumnHeights(List<BaseColumn> columns, final ColumnCellSelector.TYPE type){
		return verifyColumnHeights(columns, type, null);
	}
	
	/**
	 * Return a map of the cells extracted by the specified extractor form the set of columns, with the appropriate span value
	 * 
	 * @param columns a not null set of columns used to calculate the span, to have a correct result it should be all the columns 
	 * on the table, otherwise the count of the row could miss some informations
	 * @param type the section of the table used where the spans are calculated
	 * @param groupName the group if the requested section is a group header or a group footer, in this case this shouldn't be null, otherwise
	 * it can be null
	 * @return a not null hash map where the key is a cell of the requested section and the value
	 * is the required span for the cell
	 */
	protected HashMap<Cell, Integer> verifyColumnHeights(List<BaseColumn> columns, final ColumnCellSelector.TYPE type, final String groupName)
	{
		final List<List<Cell>> tableCellRows = new ArrayList<List<Cell>>();
		HashMap<Cell, Integer> result = new HashMap<Cell, Integer>();
		ColumnVisitor<Void> cellCollector = new ColumnVisitor<Void>()
		{
			int rowIdx = 0;
			
			protected List<Cell> getRow()
			{
				int currentRowCount = tableCellRows.size();
				if (rowIdx >= currentRowCount)
				{
					for (int i = currentRowCount; i <= rowIdx; i++)
					{
						tableCellRows.add(new ArrayList<Cell>());
					}
				}
				return tableCellRows.get(rowIdx);
			}
			
			public Void visitColumn(Column column)
			{
				Cell cell = cellSelector.getCell(column, type, groupName);
				if (cell != null)
				{
					getRow().add(cell);
				}
				return null;
			}

			public Void visitColumnGroup(ColumnGroup columnGroup)
			{
				Cell cell = cellSelector.getCell(columnGroup, type, groupName);
				if (cell != null)
				{
					getRow().add(cell);
				}
				
				int span = cell == null ? 0 : 1;
				if (cell != null && cell.getRowSpan() != null && cell.getRowSpan() > 1)
				{
					span = cell.getRowSpan();
				}
				
				rowIdx += span;
				for (BaseColumn subcolumn : columnGroup.getColumns())
				{
					subcolumn.visitColumn(this);
				}
				rowIdx -= span;
				
				return null;
			}
		};
		
		for (BaseColumn column : columns)
		{
			column.visitColumn(cellCollector);
		}

		List<Integer> rowHeights = new ArrayList<Integer>(tableCellRows.size());
		for (int rowIdx = 0; rowIdx < tableCellRows.size(); ++rowIdx)
		{
			Integer rowHeight = null;
			// going back on rows in order to determine row height
			int spanHeight = 0;
			prevRowLoop:
			for (int idx = rowIdx; idx >= 0; --idx)
			{
				for (Cell cell : tableCellRows.get(idx))
				{
					int rowSpan = cell.getRowSpan() == null ? 1 : cell.getRowSpan();
					if (idx + rowSpan - 1 == rowIdx && cell.getHeight() != null)
					{
						rowHeight = cell.getHeight() - spanHeight;
						break prevRowLoop;
					}
				}
				
				if (rowIdx > 0)
				{
					spanHeight += rowHeights.get(rowIdx - 1);
				}
			}
			
			if (rowHeight == null)
			{
				return result;
			}
			else
			{
				rowHeights.add(rowHeight);
			}
		}

		for (ListIterator<List<Cell>> rowIt = tableCellRows.listIterator(); rowIt.hasNext();)
		{
			List<Cell> row = rowIt.next();
			int rowIdx = rowIt.previousIndex();
			int rowHeight = rowHeights.get(rowIdx);
			
			for (Cell cell : row)
			{	
				Integer height = cell.getHeight();
				
				/*if (height != null)
				{
					int spanHeight = rowHeight;
					int maxSpan = tableCellRows.size() - rowIdx;
					for (int idx = 1; idx < maxSpan; ++idx){
						spanHeight += rowHeights.get(rowIdx + idx);
						if (cell.getHeight() == spanHeight)
						{
							result.put(cell, idx);
							break;
						} 
					}
					if (cell.getHeight() == spanHeight)
					{
						result.put(cell, maxSpan);
						break;
					} 
				}*/ 
				
				if (height != null)
				{
					int span = 1;
					while( rowIdx + span <= tableCellRows.size()){
						int spanHeight = rowHeight;
						for (int idx = 1; idx < span; ++idx){
							spanHeight += rowHeights.get(rowIdx + idx);
						}
						
						if (cell.getHeight() != spanHeight)
						{
							span++;
						} else {
							result.put(cell, span);
							break;
						}
					}
				}
			}
		}
		return result;
	}
}
