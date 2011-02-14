package com.jaspersoft.studio.crosstab;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRCrosstabOrigin;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

public class CrosstabManager {

	private JRDesignCrosstab crosstab;

	private Map<JRDesignCellContents, Rectangle> boundsMap = new HashMap<JRDesignCellContents, Rectangle>();

	public CrosstabManager(JRDesignCrosstab crosstab) {
		this.crosstab = crosstab;
		init(crosstab);
	}

	public void refresh() {
		crosstab.preprocess();
		init(crosstab);
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
		for (Rectangle r : boundsMap.values()) {
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

	public JRDesignCellContents getCell(Point location) {
		for (JRDesignCellContents cell : boundsMap.keySet()) {
			Rectangle r = boundsMap.get(cell);
			if (r.x <= location.x && r.x + r.width >= location.x && r.y <= location.y && r.y + r.height >= location.y)
				return cell;
		}
		return null;
	}

	public Rectangle getCellBounds(JRDesignCellContents cell) {
		return boundsMap.get(cell);
	}

	public static int getHW(int hw) {
		if (hw < 0)
			return 0;
		return hw;
	}

	public static int getHW(int hw, int def) {
		if (hw < 0)
			return def;
		return hw;
	}

	public void init(JRDesignCrosstab crosstab) {
		boundsMap.clear();
		int rp = 0;
		int cp = 0;
		int tx = 0;
		int ty = 0;
		JRDesignCellContents headerCell = (JRDesignCellContents) crosstab.getHeaderCell();
		if (headerCell != null) {
			boundsMap.put(headerCell, new Rectangle(0, 0, getHW(headerCell.getWidth()), getHW(headerCell.getHeight())));
			ty = getHW(headerCell.getHeight());
		}

		JRCrosstabRowGroup[] rowGroups = crosstab.getRowGroups();
		if (rowGroups != null) {
			for (JRCrosstabRowGroup p : rowGroups) {
				JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
				JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();

				if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.START)) {
					boundsMap.put(ct, new Rectangle(tx, ty, getHW(ct.getWidth()), getHW(ct.getHeight())));
					ty += getHW(ct.getHeight());
				} else if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END)) {
					boundsMap.put(ct,
							new Rectangle(tx, ty + getHW(ch.getHeight(), 20), getHW(ct.getWidth()), getHW(ct.getHeight())));
				} else {
					boundsMap.put(ct, new Rectangle(tx, ty, 0, 0));
				}
				boundsMap.put(ch, new Rectangle(tx, ty, getHW(ch.getWidth()), getHW(ch.getHeight(), 20)));

				tx += getHW(ch.getWidth());

				cp++;

			}
		}
		int ctx = tx;
		JRCrosstabColumnGroup[] colGroups = crosstab.getColumnGroups();
		if (colGroups != null) {
			ty = 0;
			for (JRCrosstabColumnGroup p : colGroups) {
				JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
				JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();

				if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.START)) {
					boundsMap.put(ct, new Rectangle(tx, ty, getHW(ct.getWidth()), getHW(ct.getHeight())));
					tx += getHW(ct.getWidth());
				} else if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END)) {
					boundsMap.put(ct, new Rectangle(tx + getHW(ch.getWidth(), 20), ty, getHW(ct.getWidth()),
							getHW(ct.getHeight())));
				} else {
					boundsMap.put(ct, new Rectangle(tx, ty, 0, 0));
				}

				boundsMap.put(ch, new Rectangle(tx, ty, getHW(ch.getWidth(), 20), getHW(ch.getHeight())));
				ty += getHW(ch.getHeight());
				rp++;
			}
		}
		int cty = ty;
		// update y
		if (rowGroups != null && crosstab.getHeaderCell() == null) {
			for (JRCrosstabRowGroup p : rowGroups) {
				JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
				JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();
				Rectangle r = boundsMap.get(ch);
				r.setLocation(r.x, r.y + ty);
				r = boundsMap.get(ct);
				r.setLocation(r.x, r.y + ty);

				Rectangle rCt = boundsMap.get(ct);
				setCellRow(rCt.y, p.getName());
			}
		}

		tx = ctx;
		ty = cty;
		JRCrosstabCell[][] cells = crosstab.getCells();
		if (cells != null)
			for (int i = cells.length - 1; i >= 0; i--) {
				JRCrosstabCell[] rows = cells[i];
				int h = 0;
				for (int j = rows.length - 1; j >= 0; j--) {
					JRCrosstabCell cols = rows[j];
					if (cols != null) {
						JRDesignCellContents cc = (JRDesignCellContents) cols.getContents();
						boundsMap.put(cc, new Rectangle(tx, ty, getHW(cc.getWidth()), getHW(cc.getHeight())));
						tx += getHW(cc.getWidth());
						h = getHW(cc.getHeight());
					}
				}
				ty += h;
				tx = ctx;
			}

		if (rowGroups != null) {
			for (int i = 0; i < rowGroups.length; i++) {
				JRCrosstabRowGroup p = (JRCrosstabRowGroup) rowGroups[i];
				if (i == rowGroups.length - 1)
					setCellRow(boundsMap.get((JRDesignCellContents) p.getHeader()).y, null);
				if (!p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE))
					setCellRow(boundsMap.get((JRDesignCellContents) p.getTotalHeader()).y, p.getName());
			}
		}

		if (colGroups != null) {
			for (int i = 0; i < colGroups.length; i++) {
				JRCrosstabColumnGroup p = (JRCrosstabColumnGroup) colGroups[i];
				if (i == colGroups.length - 1)
					setCellColumn(boundsMap.get((JRDesignCellContents) p.getHeader()).x, null);
				if (!p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.NONE))
					setCellColumn(boundsMap.get((JRDesignCellContents) p.getTotalHeader()).x, p.getName());
			}
		}
		// mirror elements if is Right to left
		if (crosstab.getRunDirectionValue() != null && crosstab.getRunDirectionValue().equals(RunDirectionEnum.RTL)) {
			int cwidth = crosstab.getWidth();
			for (Rectangle r : boundsMap.values()) {
				r.setLocation(cwidth - r.x - r.width + crosstab.getX(), r.y);
			}
		}
		setSize();
	}

	public void setCellRow(int y, String rowTotal) {
		JRCrosstabCell[][] cells = crosstab.getCells();

		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = cells[i].length - 1; j >= 0; j--) {
				JRCrosstabCell jrCrosstabCell = cells[i][j];
				if (jrCrosstabCell != null
						&& ((jrCrosstabCell.getRowTotalGroup() != null && jrCrosstabCell.getRowTotalGroup().equals(rowTotal)) || (rowTotal == null && jrCrosstabCell
								.getRowTotalGroup() == null))) {
					Rectangle r = boundsMap.get((JRDesignCellContents) jrCrosstabCell.getContents());
					if (r != null)
						r.setLocation(r.x, y);

				}
			}
		}
	}

	public void setCellColumn(int x, String colTotal) {
		JRCrosstabCell[][] cells = crosstab.getCells();

		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = cells[i].length - 1; j >= 0; j--) {
				JRCrosstabCell jrCrosstabCell = cells[i][j];
				if (jrCrosstabCell != null
						&& ((jrCrosstabCell.getColumnTotalGroup() != null && jrCrosstabCell.getColumnTotalGroup().equals(colTotal)) || (colTotal == null && jrCrosstabCell
								.getColumnTotalGroup() == null))) {
					Rectangle r = boundsMap.get((JRDesignCellContents) jrCrosstabCell.getContents());
					r.setLocation(x, r.y);
				}
			}
		}
	}

	public Rectangle getBounds(JRDesignCellContents cell) {
		Rectangle b = boundsMap.get(cell);
		if (b != null)
			return b;
		return new Rectangle(0, 0, getHW(cell.getWidth(), 60), getHW(cell.getHeight(), 20));
	}

	public void setWidth(JRDesignCellContents cell, int width) {
		JRCrosstabCell[][] cells = crosstab.getCells();
		List<?> colGroupsList = crosstab.getColumnGroupsList();
		List<?> rowGroupsList = crosstab.getRowGroupsList();
		String rowGroupName = cell.getOrigin().getRowGroupName();
		switch (cell.getOrigin().getType()) {
		case JRCrosstabOrigin.TYPE_DATA_CELL:
			for (int i = cells.length - 1; i >= 0; i--) {
				for (int j = cells[i].length - 1; j >= 0; j--) {
					JRDesignCrosstabCell jrCrosstabCell = (JRDesignCrosstabCell) cells[i][j];
					if (jrCrosstabCell.getContents() == cell) {
						jrCrosstabCell.setWidth(width);

						for (int k = 0; k < cells.length; k++) {
							if (cells[k][j] != null) {
								((JRDesignCrosstabCell) cells[k][j]).setWidth(width);
							}
						}
					}
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_HEADER_CELL:
			if (!rowGroupsList.isEmpty()) {
				JRDesignCrosstabRowGroup p = (JRDesignCrosstabRowGroup) rowGroupsList.get(rowGroupsList.size() - 1);
				int delta = width - cell.getWidth();
				setCellWidth(p, p.getWidth(), p.getWidth() + delta);
			}
			break;
		case JRCrosstabOrigin.TYPE_ROW_GROUP_HEADER:
			for (int i = 0; i < rowGroupsList.size(); i++) {
				JRDesignCrosstabRowGroup p = (JRDesignCrosstabRowGroup) rowGroupsList.get(i);
				if (p.getName().equals(rowGroupName)) {
					setCellWidth(p, p.getWidth(), width);
					break;
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_ROW_GROUP_TOTAL_HEADER:
			rowGroupsList = crosstab.getRowGroupsList();
			for (int i = 0; i < rowGroupsList.size(); i++) {
				JRDesignCrosstabRowGroup p = (JRDesignCrosstabRowGroup) rowGroupsList.get(i);
				if (p.getName().equals(rowGroupName)) {
					int delta = width - cell.getWidth();
					setCellWidth(p, p.getWidth(), width = p.getWidth() + delta);
					break;
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_COLUMN_GROUP_TOTAL_HEADER:
			boolean calculated = false;
			String colGroupName = cell.getOrigin().getColumnGroupName();
			for (int i = cells.length - 1; i >= 0; i--) {
				for (int j = cells[i].length - 1; j >= 0; j--) {
					JRDesignCrosstabCell jrCrosstabCell = (JRDesignCrosstabCell) cells[i][j];
					if (jrCrosstabCell.getColumnTotalGroup() != null && colGroupName != null
							&& jrCrosstabCell.getColumnTotalGroup().equals(colGroupName)) {
						if (!calculated) {
							width = jrCrosstabCell.getWidth() + width - cell.getWidth();
							calculated = true;
						}
						if (width >= 0)
							jrCrosstabCell.setWidth(width);
						else
							return;
					}
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_COLUMN_GROUP_HEADER:
			colGroupName = cell.getOrigin().getColumnGroupName();
			for (int i = 0; i < colGroupsList.size(); i++) {
				JRDesignCrosstabColumnGroup rg = (JRDesignCrosstabColumnGroup) colGroupsList.get(i);
				if (rg.getName().equals(colGroupName)) {
					if (i == colGroupsList.size() - 1) {
						if (i < cells.length)
							setWidth((JRDesignCellContents) cells[cells.length - 1][cells[i].length - 1].getContents(), width);

					} else {
						int delta = width - cell.getWidth();
						JRDesignCrosstabColumnGroup rgNext = (JRDesignCrosstabColumnGroup) colGroupsList.get(i + 1);
						if (rgNext.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END)) {
							JRDesignCellContents totalHeader = (JRDesignCellContents) rgNext.getTotalHeader();
							setWidth(totalHeader, totalHeader.getWidth() + delta);
						} else {
							JRDesignCellContents header = (JRDesignCellContents) rgNext.getHeader();
							setWidth(header, header.getWidth() + delta);
						}
						break;
					}
				}
			}
			break;
		}
	}

	public void setHeight(JRDesignCellContents cell, int height) {
		JRCrosstabCell[][] cells = crosstab.getCells();
		List<?> colGroupsList = crosstab.getColumnGroupsList();
		List<?> rowGroupsList = crosstab.getRowGroupsList();
		String columnGroupName = cell.getOrigin().getColumnGroupName();
		switch (cell.getOrigin().getType()) {
		case JRCrosstabOrigin.TYPE_DATA_CELL:
			if (height >= 0)
				for (int i = cells.length - 1; i >= 0; i--) {
					for (int j = cells[i].length - 1; j >= 0; j--) {
						JRDesignCrosstabCell jrCrosstabCell = (JRDesignCrosstabCell) cells[i][j];
						if (jrCrosstabCell.getContents() == cell) {
							jrCrosstabCell.setHeight(height);

							for (int k = 0; k < cells.length; k++) {
								if (cells[k][j] != null) {
									((JRDesignCrosstabCell) cells[i][k]).setHeight(height);
								}
							}
						}
					}
				}
			break;
		case JRCrosstabOrigin.TYPE_HEADER_CELL:
			if (!colGroupsList.isEmpty()) {
				JRDesignCrosstabColumnGroup p = (JRDesignCrosstabColumnGroup) colGroupsList.get(colGroupsList.size() - 1);
				setCellHeight(p, p.getHeight(), p.getHeight() + height - cell.getHeight());
			}
			break;
		case JRCrosstabOrigin.TYPE_COLUMN_GROUP_HEADER:
			for (int i = 0; i < colGroupsList.size(); i++) {
				JRDesignCrosstabColumnGroup p = (JRDesignCrosstabColumnGroup) colGroupsList.get(i);
				if (p.getName().equals(columnGroupName)) {
					setCellHeight(p, p.getHeight(), height);
					break;
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_COLUMN_GROUP_TOTAL_HEADER:
			for (int i = 0; i < colGroupsList.size(); i++) {
				JRDesignCrosstabColumnGroup p = (JRDesignCrosstabColumnGroup) colGroupsList.get(i);
				if (p.getName().equals(columnGroupName)) {
					setCellHeight(p, p.getHeight(), p.getHeight() + height - cell.getHeight());
					break;
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_ROW_GROUP_TOTAL_HEADER:
			boolean calculated = false;
			String rowGroupName = cell.getOrigin().getRowGroupName();
			for (int i = cells.length - 1; i >= 0; i--) {
				for (int j = cells[i].length - 1; j >= 0; j--) {
					JRDesignCrosstabCell jrCrosstabCell = (JRDesignCrosstabCell) cells[i][j];
					if (jrCrosstabCell.getRowTotalGroup() != null && rowGroupName != null
							&& jrCrosstabCell.getRowTotalGroup().equals(rowGroupName)) {
						if (!calculated) {
							height = jrCrosstabCell.getHeight() + height - cell.getHeight();
							calculated = true;
						}
						if (height >= 0)
							jrCrosstabCell.setHeight(height);
						else
							return;
					}
				}
			}
			break;
		case JRCrosstabOrigin.TYPE_ROW_GROUP_HEADER:
			rowGroupName = cell.getOrigin().getRowGroupName();
			for (int i = 0; i < rowGroupsList.size(); i++) {
				JRDesignCrosstabRowGroup rg = (JRDesignCrosstabRowGroup) rowGroupsList.get(i);
				if (rg.getName().equals(rowGroupName)) {
					if (i == rowGroupsList.size() - 1) {
						setHeight((JRDesignCellContents) cells[cells.length - 1][cells[i].length - 1].getContents(), height);
					} else {
						int delta = height - cell.getHeight();
						JRDesignCrosstabRowGroup rgNext = (JRDesignCrosstabRowGroup) rowGroupsList.get(i + 1);
						if (rgNext.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END)) {
							JRDesignCellContents totalHeader = (JRDesignCellContents) rgNext.getTotalHeader();
							setHeight(totalHeader, totalHeader.getHeight() + delta);
						} else {
							JRDesignCellContents header = (JRDesignCellContents) rgNext.getHeader();
							setHeight(header, header.getHeight() + delta);
						}
						break;
					}
				}
			}
			break;
		}
	}

	private void setCellWidth(JRDesignCrosstabRowGroup p, int oldValue, int width) {
		if (width > 0) {
			p.setWidth(width);
			p.getEventSupport().firePropertyChange(JRDesignCrosstabRowGroup.PROPERTY_WIDTH, oldValue, width);
		}
	}

	private void setCellHeight(JRDesignCrosstabColumnGroup p, int oldValue, int height) {
		if (height >= 0) {
			p.setHeight(height);
			p.getEventSupport().firePropertyChange(JRDesignCrosstabColumnGroup.PROPERTY_HEIGHT, oldValue, height);
		}
	}

}
