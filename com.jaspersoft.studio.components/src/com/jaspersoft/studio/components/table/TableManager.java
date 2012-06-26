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
package com.jaspersoft.studio.components.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.ColumnGroup;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.components.table.util.TableUtil;
import net.sf.jasperreports.engine.JRGroup;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.components.table.util.TableColumnSize;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.utils.Misc;

public class TableManager {
	private StandardTable table;
	private TableUtil tableUtil;
	private Map<BaseColumn, Integer> xcolumn = new HashMap<BaseColumn, Integer>();
	private Map<Integer, Map<String, Map<BaseColumn, Point>>> yhcolumn = new HashMap<Integer, Map<String, Map<BaseColumn, Point>>>();

	public int getColumnX(BaseColumn bc) {
		return xcolumn.get(bc);
	}

	public Point getYhcolumn(int type, String grName, BaseColumn bc) {
		return yhcolumn.get(type).get(Misc.nvl(grName, "")).get(bc);
	}

	public TableManager(JRDesignComponentElement tbl, JasperDesign jasperDesign) {
		this.table = (StandardTable) tbl.getComponent();
		this.tableUtil = new TableUtil(table, jasperDesign);

		initMaps();

		tableUtil.init(table);
		setSize();
	}

	public void initMaps() {
		initXColumn(0, table.getColumns());

		int y = 0;
		y += initYHColumn(y, table.getColumns(), TableUtil.TABLE_HEADER, "");
		y += initYHColumn(y, table.getColumns(), TableUtil.COLUMN_HEADER, "");

		List<?> groupsList = tableUtil.getGroupList();
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRGroup jrGroup = (JRGroup) it.next();
				y += initYHColumn(y, table.getColumns(),
						TableUtil.COLUMN_GROUP_HEADER, jrGroup.getName());
			}

		y += initYHColumn(y, table.getColumns(), TableUtil.COLUMN_DETAIL, "");

		if (groupsList != null)
			for (ListIterator<?> it = groupsList
					.listIterator(groupsList.size()); it.hasPrevious();) {
				JRGroup jrGroup = (JRGroup) it.previous();
				y += initYHColumn(y, table.getColumns(),
						TableUtil.COLUMN_GROUP_FOOTER, jrGroup.getName());
			}

		y += initYHColumn(y, table.getColumns(), TableUtil.COLUMN_FOOTER, "");
		y += initYHColumn(y, table.getColumns(), TableUtil.TABLE_FOOTER, "");
	}

	private int initYHColumn(int y, List<BaseColumn> cols, int type,
			String grName) {
		List<Integer> rows = new ArrayList<Integer>();
		int h = getHeight(cols, type, grName, rows, 0);
		// System.out.println("Rows " + type + " :" + rows);
		getRowY(y, cols, type, grName, rows, 0);
		return h;
	}

	private void getRowY(int y, List<BaseColumn> cols, int type, String grName,
			List<Integer> rows, int depth) {
		Map<BaseColumn, Point> map = getMap(type, grName);
		for (BaseColumn bc : cols) {
			DesignCell c = (DesignCell) TableUtil.getCell(bc, type, grName);
			Integer h = rows.get(depth);
			// System.out.println("H:" + h);
			if (bc instanceof ColumnGroup) {
				int ch = h;
				if (c != null)
					ch = c.getHeight();
				int childh = rows.get(depth + 1);
				if (childh != 0)
					ch = h - childh;
				int tmpy = y;
				if (isBottomOfTable(type))
					tmpy = y + h - ch;
				map.put(bc, new Point(tmpy, ch));
				if (c != null)
					c.setHeight(ch);
				if (isBottomOfTable(type))
					getRowY(y, ((ColumnGroup) bc).getColumns(), type, grName,
							rows, depth + 1);
				else
					getRowY(y + ch, ((ColumnGroup) bc).getColumns(), type,
							grName, rows, depth + 1);
			} else {
				map.put(bc, new Point(y, h));
				if (c != null)
					c.setHeight(h);
			}
		}
	}

	private int getHeight(List<BaseColumn> cols, int type, String grName,
			List<Integer> rows, int depth) {
		int h = 0;
		for (BaseColumn bc : cols) {
			int hc = 0;
			Cell c = TableUtil.getCell(bc, type, grName);
			if (c != null)
				hc = c.getHeight();
			if (bc instanceof ColumnGroup)
				hc += getHeight(((ColumnGroup) bc).getColumns(), type, grName,
						rows, depth + 1);
			h = Math.max(h, hc);
		}
		if (rows.size() > depth) {
			Integer oldh = 0;
			if (rows.size() > depth)
				oldh = rows.get(depth);
			rows.add(depth, Math.max(h, oldh));
		} else
			rows.add(h);
		return h;
	}

	public Map<BaseColumn, Point> getMap(int type, String grName) {
		Map<String, Map<BaseColumn, Point>> m = yhcolumn.get(type);
		if (m == null) {
			m = new HashMap<String, Map<BaseColumn, Point>>();
			yhcolumn.put(type, m);
		}
		Map<BaseColumn, Point> map = m.get(grName);
		if (map == null) {
			map = new HashMap<BaseColumn, Point>();
			m.put(grName, map);
		}
		return map;
	}

	private void initXColumn(int x, List<BaseColumn> cols) {
		for (BaseColumn bc : cols) {
			xcolumn.put(bc, x);
			if (bc instanceof ColumnGroup)
				initXColumn(x, ((ColumnGroup) bc).getColumns());
			x += bc.getWidth();
		}
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

	public Rectangle getBounds(int width, StandardBaseColumn col, int type,
			String grName) {
		int x = getColumnX(col);
		Point p = getYhcolumn(type, grName, col);
		if (p == null)
			p = new Point(0, 100);
		return new Rectangle(x, p.x, col.getWidth(), p.y);
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
				setColumnWidth(table.getColumns(), cell, delta);
			else if (cell instanceof StandardColumnGroup)
				setColumnGroupWidth((StandardColumnGroup) cell, delta);
			cell.setWidth(width);
		}
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

	private boolean setColumnWidth(List<BaseColumn> col,
			StandardBaseColumn cell, int delta) {
		for (BaseColumn bc : col) {
			if (bc instanceof StandardColumnGroup) {
				StandardColumnGroup scg = (StandardColumnGroup) bc;
				boolean b = setColumnWidth(scg.getColumns(), cell, delta);
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

	public void setHeight(DesignCell cell, int height, StandardBaseColumn col,
			int type, String grName) {
		if (height >= 0) {
			int delta = 0;
			if (cell == null)
				delta = height - getYhcolumn(type, grName, col).y;
			else
				delta = height - cell.getHeight();
			setColumnHeight(table.getColumns(), delta, type, grName, col);
		}
	}

	private int setColumnHeight(List<BaseColumn> columns, int delta, int type,
			String grName, StandardBaseColumn col) {
		int dif = 0;
		for (BaseColumn bc : columns) {
			if (bc instanceof StandardColumn)
				dif = TableColumnSize.setCellHeightDelta(bc, type, grName,
						delta);
			else if (bc instanceof StandardColumnGroup) {
				if (col == bc) {
					TableColumnSize.setCellHeightDelta(bc, type, grName, delta);
				} else {
					dif = setColumnHeight(
							((StandardColumnGroup) bc).getColumns(), delta,
							type, grName, col);
					if (delta < 0 && dif != 0)
						dif = TableColumnSize.setCellHeightDelta(bc, type,
								grName, dif);
				}
			}
		}
		return dif;
	}

	public static StandardTable getTable(ANode destNode) {
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

	public void update() {
		initMaps();
		refresh();
	}

	public int getRowHeight(MColumn col) {
		return 100;
	}

	public static boolean isBottomOfTable(int type) {
		return type == TableUtil.COLUMN_FOOTER
				|| type == TableUtil.TABLE_FOOTER
				|| type == TableUtil.COLUMN_GROUP_FOOTER;
	}
}
