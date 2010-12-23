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

import com.jaspersoft.studio.table.model.MTable;
import com.jaspersoft.studio.table.util.TableColumnSize;

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
		init(table);
	}

	public void init(StandardTable table) {
		boundsMap.clear();

		List<BaseColumn> allColumns = getAllColumns(table.getColumns());
		int y = 0;
		int h = 0;
		Rectangle r = new Rectangle(0, 0, 0, 0);
		for (BaseColumn bc : table.getColumns()) {
			r = initHeader(r, bc, TableColumnSize.TABLE_HEADER, null);
			r.setLocation(r.x , y);
			if (h < r.height)
				h = r.height;
		}
		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : table.getColumns()) {
			r = initHeader(r, bc, TableColumnSize.COLUMN_HEADER, null);
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
					r = initHeader(r, bc, TableColumnSize.COLUMN_GROUP_HEADER, jrGroup.getName());
					r.setLocation(r.x, y);
					if (h < r.height)
						h = r.height;
				}
			}

		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : allColumns) {
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
					r = initFooter(r, bc, TableColumnSize.COLUMN_GROUP_FOOTER, jrGroup.getName());
					r.setLocation(r.x, y);
					if (h < r.height)
						h = r.height;
				}
			}

		y += h;
		r = new Rectangle(0, y, 0, 0);
		h = 0;
		for (BaseColumn bc : table.getColumns()) {
			r = initFooter(r, bc, TableColumnSize.COLUMN_FOOTER, null);
			r.setLocation(r.x, y);
			if (h < r.height)
				h = r.height;
		}
		y += h;
		r = new Rectangle(0, y, 0, 0);
		for (BaseColumn bc : table.getColumns()) {
			r = initFooter(r, bc, TableColumnSize.TABLE_FOOTER, null);
			r.setLocation(r.x, y);
		}
	}

	public static List<BaseColumn> getAllColumns(MTable mTable) {
		JRDesignComponentElement tbl = (JRDesignComponentElement) mTable.getValue();
		if (tbl.getComponent() instanceof StandardTable) {
			StandardTable table = (StandardTable) tbl.getComponent();
			return getAllColumns(table.getColumns());
		}
		return new ArrayList<BaseColumn>(0);
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

	private Rectangle initDetail(Rectangle p, BaseColumn bc) {
		int h = 0;
		int w = 0;
		if (bc != null && bc instanceof StandardColumn) {
			Cell c = ((StandardColumn) bc).getDetailCell();
			w = bc.getWidth();
			if (c != null)
				h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, p.y, w, h));
		}
		return new Rectangle(p.x + w, p.y, w, h);
	}

	private Rectangle initHeader(Rectangle p, BaseColumn bc, int type, String grName) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = TableColumnSize.getCell(bc, type, grName);
		if (c != null) {
			y = p.y + c.getHeight();
			h = c.getHeight();
			boundsMap.put(c, new Rectangle(p.x, p.y, w, h));
		}
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initHeader(pi, bcg, type, grName);
				pi.setLocation(pi.x, y);
				if (hi < pi.height)
					hi = pi.height;
			}
			h += hi;
		}
		return new Rectangle(p.x + w, y, w, h);
	}

	private Rectangle initFooter(Rectangle p, BaseColumn bc, int type, String grName) {
		int y = p.y;
		int h = 0;
		int w = bc.getWidth();
		Cell c = TableColumnSize.getCell(bc, type, grName);
		if (bc instanceof StandardColumnGroup) {
			Rectangle pi = new Rectangle(p.x, y, w, h);
			int hi = 0;
			for (BaseColumn bcg : ((StandardColumnGroup) bc).getColumns()) {
				pi = initFooter(pi, bcg, type, grName);
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
		return new Rectangle(p.x + w, y, w, h);
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
		return getGroupList(table, jasperDesign);
	}

	public static List<?> getGroupList(StandardTable stable, JasperDesign jd) {
		List<?> groupsList = null;
		JRDatasetRun datasetRun = stable.getDatasetRun();
		if (datasetRun != null) {
			String dataSetName = datasetRun.getDatasetName();
			JRDesignDataset ds = (JRDesignDataset) jd.getDatasetMap().get(dataSetName);
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

	public void setHeight(DesignCell cell, int height, StandardBaseColumn col, int type, String grName) {
		if (height >= 0) {
			int delta = height - cell.getHeight();
			setColumnHeight(table.getColumns(), delta, type, grName, col);
		}
	}

	private int setColumnHeight(List<BaseColumn> columns, int delta, int type, String grName, StandardBaseColumn col) {
		int dif = 0;
		for (BaseColumn bc : columns) {
			if (bc instanceof StandardColumn)
				dif = TableColumnSize.setCellHeightDelta(bc, type, grName, delta);
			else if (bc instanceof StandardColumnGroup) {
				if (col == bc) {
					TableColumnSize.setCellHeightDelta(bc, type, grName, delta);
				} else {
					dif = setColumnHeight(((StandardColumnGroup) bc).getColumns(), delta, type, grName, col);
					if (delta < 0 && dif != 0)
						dif = TableColumnSize.setCellHeightDelta(bc, type, grName, dif);
				}
			}
		}
		return dif;
	}

}
