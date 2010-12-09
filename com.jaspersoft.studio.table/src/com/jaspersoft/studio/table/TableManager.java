package com.jaspersoft.studio.table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import net.sf.jasperreports.components.table.BaseColumn;
import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.components.table.StandardColumn;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.StandardTable;
import net.sf.jasperreports.engine.JRDatasetRun;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignGroup;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Rectangle;

public class TableManager {
	private StandardTable table;
	private Map<Cell, Rectangle> boundsMap = new HashMap<Cell, Rectangle>();
	private JasperDesign jasperDesign;

	public TableManager(JRDesignComponentElement tbl, JasperDesign jasperDesign) {
		this.table = (StandardTable) tbl.getComponent();
		this.jasperDesign = jasperDesign;
		init(table);
	}

	public void refresh() {
		// table.preprocess();
		init(table);
	}

	public void init(StandardTable table) {
		boundsMap.clear();

		int y = 0;
		int h = 0;
		Rectangle r = new Rectangle(0, 0, 0, 0);
		for (BaseColumn bc : table.getColumns()) {
			r = initTableHeader(r, bc);
			r.setLocation(r.x, y);
			if (h < r.height)
				h = r.height;
		}
		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : table.getColumns()) {
			r = initColumnHeader(r, bc);
			r.setLocation(r.x, y);
			if (h < r.height)
				h = r.height;
		}

		List<?> groupsList = getGroupList();
		if (groupsList != null)
			for (Iterator<?> it = groupsList.iterator(); it.hasNext();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.next();
				y += h;
				r = new Rectangle(0, y, 0, 0);
				h = 0;
				for (BaseColumn bc : table.getColumns()) {
					r = initGroupHeader(r, bc, jrGroup.getName());
					r.setLocation(r.x, y);
					if (h < r.height)
						h = r.height;
				}
			}

		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : getAllColumns(table.getColumns())) {
			r = initDetail(r, bc);
			r.setLocation(r.x, y);
			if (h < r.height)
				h = r.height;
		}

		if (groupsList != null)
			for (ListIterator<?> it = groupsList.listIterator(groupsList.size()); it.hasPrevious();) {
				JRDesignGroup jrGroup = (JRDesignGroup) it.previous();
				y += h;
				r = new Rectangle(0, y, 0, 0);
				h = 0;
				for (BaseColumn bc : table.getColumns()) {
					r = initGroupFooter(r, bc, jrGroup.getName());
					r.setLocation(r.x, y);
					if (h < r.height)
						h = r.height;
				}
			}

		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : table.getColumns()) {
			r = initColumnFooter(r, bc);
			r.setLocation(r.x, y);
			if (h < r.height)
				h = r.height;
		}
		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : table.getColumns()) {
			r = initTableFooter(r, bc);
			r.setLocation(r.x, y);
			if (h < r.height)
				h = r.height;
		}
	}

	private Rectangle initGroupHeader(Rectangle p, BaseColumn bc, String grName) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = bc.getGroupHeader(grName);
		if (c != null) {
			y = p.y + c.getHeight();
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, p.y, w, h));
		}
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initGroupHeader(pi, bcg, grName);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		return new Rectangle(p.x + bc.getWidth(), y, w, h);
	}

	private Rectangle initGroupFooter(Rectangle p, BaseColumn bc, String grName) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = bc.getGroupFooter(grName);
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initGroupFooter(pi, bcg, grName);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		if (c != null) {
			y = p.y + h;
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, y, w, h));
		}
		return new Rectangle(p.x + bc.getWidth(), y, w, h);
	}

	public static List<BaseColumn> getAllColumns(List<BaseColumn> cols) {
		List<BaseColumn> lst = new ArrayList<BaseColumn>();
		for (BaseColumn bc : cols) {
			if (bc instanceof StandardColumnGroup)
				lst.addAll(getAllColumns(((StandardColumnGroup) bc).getColumns()));
			else
				lst.add(bc);
		}
		return lst;
	}

	private Rectangle initTableHeader(Rectangle p, BaseColumn bc) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = bc.getTableHeader();
		if (c != null) {
			y = p.y + c.getHeight();
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, p.y, w, h));
		}
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initTableHeader(pi, bcg);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		return new Rectangle(p.x + bc.getWidth(), y, w, h);
	}

	private Rectangle initColumnHeader(Rectangle p, BaseColumn bc) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = bc.getColumnHeader();
		if (c != null) {
			y = p.y + c.getHeight();
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, p.y, w, h));
		}
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initColumnHeader(pi, bcg);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		return new Rectangle(p.x + bc.getWidth(), y, w, h);
	}

	private Rectangle initDetail(Rectangle p, BaseColumn bc) {
		int h = 0;
		int w = 0;
		if (bc != null && bc instanceof StandardColumn) {
			Cell c = ((StandardColumn) bc).getDetailCell();
			w = bc.getWidth();
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, p.y, w, h));
		}
		return new Rectangle(p.x + w, p.y, w, h);
	}

	private Rectangle initColumnFooter(Rectangle p, BaseColumn bc) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = bc.getColumnFooter();

		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initColumnFooter(pi, bcg);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		if (c != null) {
			y = p.y + h;
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, y, w, h));
		}
		return new Rectangle(p.x + bc.getWidth(), y, w, h);
	}

	private Rectangle initTableFooter(Rectangle p, BaseColumn bc) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = bc.getTableFooter();
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initTableFooter(pi, bcg);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		if (c != null) {
			y = p.y + h;
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, y, w, h));
		}
		return new Rectangle(p.x + bc.getWidth(), y, w, h);
	}

	public Rectangle getBounds(int width, Cell cell, StandardBaseColumn col) {
		Rectangle b = boundsMap.get(cell);
		if (b != null)
			return b;
		int w = col != null ? col.getWidth() : 0;
		int h = cell != null ? cell.getHeight() : 0;
		return new Rectangle(0, 0, w, h);
	}

	public List<?> getGroupList() {
		List<?> groupsList = null;
		JRDatasetRun datasetRun = table.getDatasetRun();
		if (datasetRun != null) {
			String dataSetName = datasetRun.getDatasetName();
			JRDesignDataset ds = (JRDesignDataset) jasperDesign.getDatasetMap().get(dataSetName);
			groupsList = (ds != null ? ds.getGroupsList() : null);
		}
		return groupsList;
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
		for (ListIterator<BaseColumn> it = columns.listIterator(columns.size()); it.hasPrevious();) {
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

	private boolean setColumnWidth(List<BaseColumn> col, StandardBaseColumn cell, int delta) {
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

	public void setHeight(DesignCell cell, int height, StandardBaseColumn col) {
		if (height >= 0) {
			int delta = height - cell.getHeight();
			int y = boundsMap.get(cell).y;
			int bY = y + cell.getHeight();
			List<Cell> cells = new ArrayList<Cell>();
			for (Cell c : boundsMap.keySet()) {
				int cy = boundsMap.get(c).y;
				int cbx = c.getHeight() + cy;
				if (cy >= y && cbx <= bY) {
					cells.add(c);
					if (delta >= 0 && cbx == bY) {
						if (c.getHeight() == 0 && !isBottomCell(c, col, table.getColumns()))
							continue;
						((DesignCell) c).setHeight(c.getHeight() + delta);
					} else if (delta < 0) {
						int dy = bY + delta;
						if (cy > dy)
							((DesignCell) c).setHeight(0);
						else
							((DesignCell) c).setHeight(dy - cy);
					}
				}
			}
		}
	}

	private boolean isBottomCell(Cell c, StandardBaseColumn col, List<BaseColumn> columns) {
		if (col instanceof StandardColumnGroup)
			return false;
		for (BaseColumn bc : columns) {
			if (bc == col)
				return true;
			if (bc instanceof StandardColumnGroup) {
				if (isBottomCell(c, col, ((StandardColumnGroup) bc).getColumns()))
					return true;
			}
		}

		return false;
	}
}
