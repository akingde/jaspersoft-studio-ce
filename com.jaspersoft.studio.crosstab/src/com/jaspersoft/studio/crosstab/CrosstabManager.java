package com.jaspersoft.studio.crosstab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRCrosstabOrigin;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.crosstabs.type.CrosstabTotalPositionEnum;

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

	public void init(JRDesignCrosstab crosstab) {
		boundsMap.clear();
		int rp = 0;
		int cp = 0;
		JRDesignCellContents headerCell = (JRDesignCellContents) crosstab.getHeaderCell();
		if (headerCell != null) {
			boundsMap.put(headerCell, new Rectangle(0, 0, headerCell.getWidth(), headerCell.getHeight()));
			rowCells.add(rp, headerCell);
			colCells.add(cp, headerCell);
		}
		int tx = 0;
		int ty = 0;
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
					boundsMap.put(ct, new Rectangle(tx, ty, ct.getWidth(), 0));
				}
				boundsMap.put(ch, new Rectangle(tx, ty, ch.getWidth(), ch.getHeight()));

				tx += ch.getWidth();

				cp++;

			}
		}
		if (crosstab.getColumnGroups() != null) {
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
					boundsMap.put(ct, new Rectangle(tx, ty, 0, ct.getHeight()));
				}

				boundsMap.put(ch, new Rectangle(tx, ty, ch.getWidth(), ch.getHeight()));
				ty += ch.getHeight();
				rp++;
			}
		}
		// update y
		if (crosstab.getRowGroups() != null) {
			for (JRCrosstabRowGroup p : crosstab.getRowGroups()) {
				JRDesignCellContents ch = (JRDesignCellContents) p.getHeader();
				JRDesignCellContents ct = (JRDesignCellContents) p.getTotalHeader();
				Rectangle r = boundsMap.get(ch);
				r.setLocation(r.x, r.y + ty);
				r = boundsMap.get(ct);
				r.setLocation(r.x, r.y + ty);

			}
		}

		int row, col = 0;
		List<JRDesignCrosstabCell> l = crosstab.getCellsList();
		for (JRDesignCrosstabCell c : l) {
			JRDesignCellContents cc = (JRDesignCellContents) c.getContents();
			if (cc.getOrigin().getType() == JRCrosstabOrigin.TYPE_DATA_CELL) {

				boundsMap.put(cc, new Rectangle(tx, ty, cc.getWidth(), cc.getHeight()));

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
