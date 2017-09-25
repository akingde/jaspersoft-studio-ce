/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.Column;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.ColumnVisitor;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.GroupCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.Pair;
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

	public synchronized void refresh() {
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
	
	public static List<Cell> getAllCells(List<BaseColumn> columns){
		List<Cell> result = new ArrayList<Cell>();
		for(BaseColumn column : columns){
			if (column.getTableFooter() != null){
				result.add(column.getTableFooter());
			}
			if (column.getTableHeader() != null){
				result.add(column.getTableHeader());
			}
			if (column.getColumnFooter() != null){
				result.add(column.getColumnFooter());
			}
			if (column.getColumnHeader() != null){
				result.add(column.getColumnHeader());
			}
			for(GroupCell groupHeader : column.getGroupHeaders()){
				if (groupHeader.getCell() != null) result.add(groupHeader.getCell());
			}
			for(GroupCell groupFooter : column.getGroupFooters()){
				if (groupFooter.getCell() != null) result.add(groupFooter.getCell());
			}
			if (column instanceof StandardColumnGroup){
				StandardColumnGroup groupColumn = (StandardColumnGroup)column;
				result.addAll(getAllCells(groupColumn.getColumns()));
			} else if (column instanceof StandardColumn){
				StandardColumn standardCol = (StandardColumn)column;
				if (standardCol.getDetailCell() != null){
					result.add(standardCol.getDetailCell());
				}
			}
		}
		return result;
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

	protected void setWidth(StandardBaseColumn cell, int width) {
		if (width >= 0) {
			int delta = width - cell.getWidth();
			if (cell instanceof StandardBaseColumn)
				setGroupHeaderWidth(table.getColumns(), cell, delta);
			if (cell instanceof StandardColumnGroup)
				setColumnGroupWidth((StandardColumnGroup) cell, delta);
			cell.setWidth(width);
		}
	}
	
	/**
	 * Set the width of this column, if it is a column group also its internal
	 * columns are resized to fill the group. They are resized keeping the ratio
	 * between them 
	 */
	protected void setProportionalWidth(StandardBaseColumn column, int width, HashSet<BaseColumn> fixedColumns) {
		if (width >= 0) {
			int delta = width - column.getWidth();
			//Look if it is inside a 
			if (column instanceof StandardBaseColumn)
				setGroupHeaderWidth(table.getColumns(), column, delta);
			if (column instanceof StandardColumnGroup)
				setProportionalColumnGroupWidth((StandardColumnGroup) column, width, fixedColumns);
			column.setWidth(width);
		}
	}
	
	/**
	 * Calculate the new width of a set of column to fit a new width of their parent. The width of the column
	 * resized is calculated propertionally to their original width
	 * 
	 * @param columns the columns to resize
	 * @param newWidth the new width the columns should occupy
	 * @param fixedColumns the list of columns that should not be resized in the process. However if the resize operation
	 * because every column is marked as fixed then the last one will be resized anyway
	 * @return and array with the same number of entry of the passed columns. Each value of the array is the size the 
	 * corrispective column should have to fit the available space
	 */
	private int[] getColumnsProportionalWidth(List<BaseColumn> columns, int newWidth, HashSet<BaseColumn> fixedColumns){
		int[] proportionalWidths = new int[columns.size()];
		int index = 0;
		int currentColumnsWidth = 0;
		for(BaseColumn col : columns){
			currentColumnsWidth += col.getWidth();
		}
		
		//Phase 0: check that at least one columns is not excluded, otherwise make the last column not excluded
		HashSet<BaseColumn> acceptableFixedColumns = new HashSet<BaseColumn>();
		int currentFixedWidth = 0;
		for(BaseColumn col : columns){
			if (fixedColumns.contains(col)){
				if (currentFixedWidth + col.getWidth() >  newWidth){
					break;
				} else {
					currentFixedWidth += col.getWidth();
					acceptableFixedColumns.add(col);
				}
			}
		}
		
		boolean allExcluded = true;
		for(BaseColumn col : columns){
			if (!acceptableFixedColumns.contains(col)){
				allExcluded = false;
				break;
			}
		}
		if (allExcluded){
			acceptableFixedColumns.remove(columns.get(columns.size()-1));
		}
		
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;			
		//space not used by the fixed columns that can be used by other columns
		int dinamycWidth = newWidth - currentFixedWidth;
		for(BaseColumn col : columns){
			if(acceptableFixedColumns.contains(col)){
				proportionalWidths[index] = col.getWidth();
				columnsTotalWidth += col.getWidth();		
			} else {
				float proportionalFactor = (float)col.getWidth() / (float)currentColumnsWidth;
				//casting to int is the same to do the floor operation, since it drop the decimal
				int proportionalWidth = (int)(proportionalFactor * dinamycWidth);
				proportionalWidths[index] = proportionalWidth;
				columnsTotalWidth += proportionalWidth;				
			}
			index ++;
		}
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		index = 0;
		while (remains > 0 && proportionalWidths.length > 0){
			BaseColumn currentColumn = columns.get(index);
			if (!acceptableFixedColumns.contains(currentColumn)){
				proportionalWidths[index]++;
				remains--;	
			}
			index++;
			if (index == proportionalWidths.length){
				index = 0;
			}
		}
		return proportionalWidths;
	}

	/**
	 * Resize all the columns in the table to match the passed width. The fill is done only if 
	 * the table is not already filling the available space
	 * 
	 * @param newWidth the width that all the columns should have
	 * @param isProportional if the columns are resized proportionally or they all will have the same width
	 * @param fixedColumns the list of columns that will not resized by the fill space operation. If this list
	 * of columns doesn't allow to table to fill the space (ie all the columns are ecluded) then the last column
	 * is removed from the exlusion set and used to fill the space
	 * @return true if some changes were done to the table, false if it was already of the right size
	 */
	public boolean fillSpace(int newWidth, boolean isProportional, HashSet<BaseColumn> fixedColumns){
		return fillSpace(newWidth, isProportional, fixedColumns, false);
	}
	
	/**
	 * Resize all the columns in the table to match the passed width
	 * 
	 * @param newWidth the width that all the columns should have
	 * @param isProportional if the columns are resized proportionally or they all will have the same width
	 * @param fixedColumns the list of columns that will not resized by the fill space operation. If this list
	 * of columns doesn't allow to table to fill the space (ie all the columns are ecluded) then the last column
	 * is removed from the exlusion set and used to fill the space
	 * @param force prameter used to force the table resize without any check to see if the table is already filling the space
	 * @return true if some changes were done to the table, false if it was already of the right size
	 */
	public boolean fillSpace(int newWidth, boolean isProportional, HashSet<BaseColumn> fixedColumns, boolean force){
		//Only one of this operation allowed on the at the same time
		synchronized (table) {
			int currentColumnsWidth = 0;
			if (!force){
				for(BaseColumn col : table.getColumns()){
					currentColumnsWidth += col.getWidth();
				}
			}
			if (currentColumnsWidth == newWidth && !force) {
				return false;
			} else if(isProportional) {
				
				int[] proportionalWidths = getColumnsProportionalWidth(table.getColumns(), newWidth, fixedColumns);
				
				//Phase 3: resize the columns
				int index = 0;
				ILayout defaultLayout = new VerticalRowLayout();
				for(BaseColumn col : table.getColumns()){
					if (col.getWidth() != proportionalWidths[index]){
						setProportionalWidth((StandardBaseColumn)col, proportionalWidths[index], fixedColumns);
						for(Entry<Cell, Integer> cell : getColumnCell(col).entrySet()){
							ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { cell.getKey() }, null, null, defaultLayout);
							Dimension baseSize = new Dimension(cell.getValue(), ((DesignCell)cell.getKey()).getHeight());
							Dimension paddedSize = LayoutManager.getPaddedSize(cell.getKey(), baseSize);
							layout.layout(jDesign, cell.getKey(), cell.getKey().getElements(), paddedSize);
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
							layout.layout(jDesign, cell.getKey(), cell.getKey().getElements(), new Dimension(cell.getValue(), ((DesignCell)cell.getKey()).getHeight()));
						}
					}
				}
			}
		}
		return true;
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
	
	public JSSCompoundCommand getLayoutCommand(){
		ILayout defaultLayout = new VerticalRowLayout();
		JSSCompoundCommand layoutCommands = new JSSCompoundCommand(null);
		for(BaseColumn col : table.getColumns()){
			for(Entry<Cell, Integer> cell : getColumnCell(col).entrySet()){
				ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { cell.getKey() }, null, null, defaultLayout);
				Dimension size = new Dimension(cell.getValue(), ((DesignCell)cell.getKey()).getHeight());
				size = LayoutManager.getPaddedSize(cell.getKey(), size);
				LayoutCommand layoutCommand = new LayoutCommand(jDesign, cell.getKey(), layout, size);
				layoutCommands.add(layoutCommand);
			}
		}
		return layoutCommands;
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
	
	private void setProportionalColumnGroupWidth(StandardColumnGroup columnGroup, int width, HashSet<BaseColumn> fixedColumns) {
		List<BaseColumn> columns = columnGroup.getColumns();
		int[] proportionalNewWidth = getColumnsProportionalWidth(columns, width, fixedColumns);
		int index = 0;
		for (BaseColumn col : columns) {
			StandardBaseColumn bc = (StandardBaseColumn) col;
			bc.setWidth(proportionalNewWidth[index]);
			if (bc instanceof StandardColumnGroup){
				setProportionalColumnGroupWidth((StandardColumnGroup) bc, proportionalNewWidth[index], fixedColumns);
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
		Dimension result = new Dimension(0, 0);
		cc = mh.getColumnCell(cc);
		if (cc != null){
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
			result.setWidth(b.width + w);
			result.setHeight(b.height + h);
		}
		return result;
	}

	public void  update() {
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
		//the boolean flag means that the cell is a group header cell
		final List<List<Pair<Cell,Boolean>>> tableCellRows = new ArrayList<List<Pair<Cell, Boolean>>>();
		HashMap<Cell, Integer> result = new HashMap<Cell, Integer>();
		//This collector will split all the cells of a section in an array. The first position of 
		//the array will contains all the cell in the first row of the section, the second position
		//the cells of the second row and so on.
		ColumnVisitor<Void> cellCollector = new ColumnVisitor<Void>()
		{
			int rowIdx = 0;
			
			protected List<Pair<Cell, Boolean>> getRow()
			{
				int currentRowCount = tableCellRows.size();
				if (rowIdx >= currentRowCount)
				{
					for (int i = currentRowCount; i <= rowIdx; i++)
					{
						tableCellRows.add(new ArrayList<Pair<Cell, Boolean>>());
					}
				}
				return tableCellRows.get(rowIdx);
			}
			
			public Void visitColumn(Column column)
			{
				Cell cell = cellSelector.getCell(column, type, groupName);
				if (cell != null)
				{
					//it is not a group header cell
					getRow().add(new Pair<Cell, Boolean>(cell,false));
				}
				return null;
			}

			public Void visitColumnGroup(ColumnGroup columnGroup)
			{
				Cell cell = cellSelector.getCell(columnGroup, type, groupName);
				if (cell != null)
				{
					//it is a group header cell
					getRow().add(new Pair<Cell, Boolean>(cell, true));
					rowIdx ++;
					for (BaseColumn subcolumn : columnGroup.getColumns())
					{
						subcolumn.visitColumn(this);
					}
					rowIdx --;
				} else {
					//without an group header cell the subcells are on the same row
					for (BaseColumn subcolumn : columnGroup.getColumns())
					{
						subcolumn.visitColumn(this);
					}		
				}
				
				return null;
			}
		};
		
		try {
			for (BaseColumn column : columns)
			{
				column.visitColumn(cellCollector);
			}
			for (int rowIdx = 0; rowIdx < tableCellRows.size(); rowIdx++)
			{
				List<Pair<Cell, Boolean>> rowCells = tableCellRows.get(rowIdx);
				for(Pair<Cell, Boolean> rowCell : rowCells){
					int span;
					if (rowCell.getValue()){
						//the group header cells have always span 1
						span = 1;
					} else {
						span = Math.max(0, tableCellRows.size() - rowIdx);
					}
					result.put(rowCell.getKey(), span);
				}
			}
		} catch (Exception ex){
			ex.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError("Error while computing table spans", ex);
			result.clear();
		}
		return result;
	}
	
	/**
	 * Search in all the table the parent of a specific colum
	 * 
	 * @param child the searched column
	 * @return the group containing the searched column or null if no group
	 * is containing it (for example beause the column if child of the table)
	 */
	public static StandardColumnGroup getParent(StandardTable table, BaseColumn child){
		for(BaseColumn tableChild : table.getColumns()){
			if (tableChild == child) return null;
		}
		for(BaseColumn tableChild : table.getColumns()){
			if (tableChild instanceof StandardColumnGroup){
				StandardColumnGroup searchInGroup = getParent(child, (StandardColumnGroup)tableChild);
				if (searchInGroup != null) return searchInGroup;
			}
		}
		return null;
	}

	/**
	 * Iterate recursivley the table groups to find the parent of a specified child 
	 * 
	 * @param child the searched column
	 * @param currentGroup the group (and its descendant) where the column is searched
	 * @return the group containing the searched column or null if the passed group or its descendent doesn't 
	 * contain the searched column
	 */
	private static StandardColumnGroup getParent(BaseColumn child, StandardColumnGroup currentGroup){
		for(BaseColumn groupChild : currentGroup.getColumns()){
			if (groupChild == child) return currentGroup;
		}
		for(BaseColumn groupChild : currentGroup.getColumns()){
			if (groupChild instanceof StandardColumnGroup){
				StandardColumnGroup searchInGroup = getParent(child, (StandardColumnGroup)groupChild);
				if (searchInGroup != null) return searchInGroup;
			}
		}
		return null;
	}
}
