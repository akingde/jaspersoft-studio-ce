package com.jaspersoft.studio.crosstab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;
import net.sf.jasperreports.engine.type.RunDirectionEnum;

import org.eclipse.draw2d.geometry.Rectangle;

public class CrosstabManager {

	private JRDesignCrosstab crosstab;

	private List<JRDesignCellContents> rowCells = new ArrayList<JRDesignCellContents>();
	private List<JRDesignCellContents> colCells = new ArrayList<JRDesignCellContents>();

	private Map<JRDesignCellContents, Rectangle> boundsMap = new HashMap<JRDesignCellContents, Rectangle>();

	public CrosstabManager(JRDesignCrosstab crosstab) {
		this.crosstab = crosstab;
		init(crosstab);
	}

	public void refresh() {
		crosstab.preprocess();
		init(crosstab);
	}

	public void init(JRDesignCrosstab crosstab) {
		boundsMap.clear();
		int rp = 0;
		int cp = 0;
		int tx = 0;
		int ty = 0;
		JRDesignCellContents headerCell = (JRDesignCellContents) crosstab.getHeaderCell();
		if (headerCell != null) {
			boundsMap.put(headerCell, new Rectangle(0, 0, headerCell.getWidth(), headerCell.getHeight()));
			ty = headerCell.getHeight();
		}

		JRCrosstabRowGroup[] rowGroups = crosstab.getRowGroups();
		if (rowGroups != null) {
			for (JRCrosstabRowGroup p : rowGroups) {
				JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
				JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();
				colCells.add(cp, ch);

				if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.START)) {
					boundsMap.put(ct, new Rectangle(tx, ty, ct.getWidth(), ct.getHeight()));
					ty += ct.getHeight();
				} else if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END)) {
					boundsMap.put(ct, new Rectangle(tx, ty + ch.getHeight(), ct.getWidth(), ct.getHeight()));
				} else {
					boundsMap.put(ct, new Rectangle(tx, ty, 0, 0));
				}
				boundsMap.put(ch, new Rectangle(tx, ty, ch.getWidth(), ch.getHeight()));

				tx += ch.getWidth();

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
				rowCells.add(rp, ch);

				if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.START)) {
					boundsMap.put(ct, new Rectangle(tx, ty, ct.getWidth(), ct.getHeight()));
					tx += ct.getWidth();
				} else if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END)) {
					boundsMap.put(ct, new Rectangle(tx + ch.getWidth(), ty, ct.getWidth(), ct.getHeight()));
				} else {
					boundsMap.put(ct, new Rectangle(tx, ty, 0, 0));
				}

				boundsMap.put(ch, new Rectangle(tx, ty, ch.getWidth(), ch.getHeight()));
				ty += ch.getHeight();
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

		for (int i = cells.length - 1; i >= 0; i--) {
			JRCrosstabCell[] rows = cells[i];
			int h = 0;
			for (int j = rows.length - 1; j >= 0; j--) {
				JRCrosstabCell cols = rows[j];
				if (cols != null) {
					JRDesignCellContents cc = (JRDesignCellContents) cols.getContents();
					boundsMap.put(cc, new Rectangle(tx, ty, cc.getWidth(), cc.getHeight()));
					tx += cc.getWidth();
					h = cc.getHeight();
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
		if (crosstab.getRunDirectionValue().equals(RunDirectionEnum.RTL)) {
			int cwidth = crosstab.getWidth();
			for (Rectangle r : boundsMap.values()) {
				r.setLocation(cwidth - r.x - r.width, r.y);
			}
		}
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
		return new Rectangle(0, 0, cell.getWidth(), cell.getHeight());
	}

	public void setWidth(JRDesignCellContents cell, int width) {
		JRCrosstabCell[][] cells = crosstab.getCells();
		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = cells[i].length - 1; j >= 0; j--) {
				JRDesignCrosstabCell jrCrosstabCell = (JRDesignCrosstabCell) cells[i][j];
				if (jrCrosstabCell.getContents() == cell) {
					jrCrosstabCell.setWidth(width);

					for (int k = 0; k < cells.length; k++) {
						if (cells[k][j] != null) {
							((JRDesignCrosstabCell) cells[k][j]).setWidth(width);
							// contents.setWidth(crossCells[i][k].getContents().getWidth());
							// break;
						}
					}

				}
			}
		}
	}

	public void setHeight(JRDesignCellContents cell, int height) {
		JRCrosstabCell[][] cells = crosstab.getCells();
		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = cells[i].length - 1; j >= 0; j--) {
				JRDesignCrosstabCell jrCrosstabCell = (JRDesignCrosstabCell) cells[i][j];
				jrCrosstabCell.setHeight(height);
			}
		}
	}

}
