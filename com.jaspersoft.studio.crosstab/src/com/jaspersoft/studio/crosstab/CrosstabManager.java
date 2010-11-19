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
			// rowCells.add(rp, headerCell);
			// colCells.add(cp, headerCell);
			ty = headerCell.getHeight();
		}

		if (crosstab.getRowGroups() != null) {
			for (JRCrosstabRowGroup p : crosstab.getRowGroups()) {
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
		if (crosstab.getColumnGroups() != null) {
			ty = 0;
			for (JRCrosstabColumnGroup p : crosstab.getColumnGroups()) {
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
		if (crosstab.getRowGroups() != null && crosstab.getHeaderCell() == null) {
			for (JRCrosstabRowGroup p : crosstab.getRowGroups()) {
				JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
				JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();
				Rectangle r = boundsMap.get(ch);
				r.setLocation(r.x, r.y + ty);
				r = boundsMap.get(ct);
				r.setLocation(r.x, r.y + ty);
			}
		}

		tx = ctx;
		ty = cty;
		JRCrosstabCell[][] cells = crosstab.getCells();

		// if (crosstab.getRowGroups() != null) {
		// for (int i = 0; i < crosstab.getRowGroups().length; i++) {
		// JRCrosstabRowGroup p = crosstab.getRowGroups()[i];
		//
		// JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
		// JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();
		// Rectangle r = boundsMap.get(ch);
		// // put ty = ty of the row
		// if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.START))
		// r = boundsMap.get(ct);
		// else if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END))
		// r = boundsMap.get(ct);
		// for (int j = cells[0].length - 1; j >= 0; j--) {
		// JRDesignCellContents cc = (JRDesignCellContents) cells[i][j].getContents();
		// boundsMap.put((JRDesignCellContents) cc, new Rectangle(tx, r.y, cc.getWidth(), cc.getHeight()));
		// }
		// }
		// }
		// if (crosstab.getColumnGroups() != null) {
		// for (int i = 0; i < crosstab.getColumnGroups().length; i++) {
		// JRCrosstabColumnGroup p = crosstab.getColumnGroups()[i];
		//
		// JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
		// JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();
		// Rectangle r = boundsMap.get(ch);
		// // put ty = ty of the row
		// if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.START))
		// r = boundsMap.get(ct);
		// else if (p.getTotalPositionValue().equals(CrosstabTotalPositionEnum.END))
		// r = boundsMap.get(ch);
		// for (int j = cells.length - 1; j >= 0; j--) {
		// JRDesignCellContents cc = (JRDesignCellContents) cells[j][i].getContents();
		//
		// Rectangle cr = boundsMap.get(cc);
		// if (cr == null)
		// boundsMap.put(cc, new Rectangle(r.x, cty, cc.getWidth(), cc.getHeight()));
		// else
		// cr.setLocation(r.x, cr.y);
		// }
		// }
		// }

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

		if (crosstab.getRunDirectionValue().equals(RunDirectionEnum.RTL)) {
			int cwidth = crosstab.getWidth();
			for (Rectangle r : boundsMap.values()) {
				r.setLocation(cwidth - r.x - r.width, r.y);
			}
		}
	}

	public Rectangle getBounds(JRDesignCellContents cell) {
		Rectangle b = boundsMap.get(cell);
		if (b != null)
			return b;
		return new Rectangle(0, 0, cell.getWidth(), cell.getHeight());
	}

	public void setWidth(JRDesignCellContents cell) {

	}

	public void setHeight(JRDesignCellContents cell) {

	}

}
