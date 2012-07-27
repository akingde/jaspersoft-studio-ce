package com.jaspersoft.studio.components.crosstab;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.crosstabs.JRCellContents;
import net.sf.jasperreports.crosstabs.JRCrosstabCell;
import net.sf.jasperreports.crosstabs.JRCrosstabColumnGroup;
import net.sf.jasperreports.crosstabs.JRCrosstabRowGroup;
import net.sf.jasperreports.crosstabs.design.JRCrosstabOrigin;
import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;

public class CrosstabMatrix {
	private List<Guide> hGuides = new ArrayList<Guide>();
	private List<Guide> vGuides = new ArrayList<Guide>();

	public void fill(JRDesignCrosstab crosstab) {
		hGuides.clear();
		vGuides.clear();

		Guide north = new Guide(0);
		hGuides.add(north);
		Guide south = fillHeaderCell(north, addNext(north, hGuides), crosstab);

		fillColumnGroup(north, south, crosstab);

		fillRowGroup(south, addNext(south, hGuides), crosstab);
	}

	public Guide fillColumnGroup(Guide north, Guide south,
			JRDesignCrosstab crosstab) {
		JRCrosstabColumnGroup[] columns = crosstab.getColumnGroups();
		for (int i = 0; i < columns.length; i++) {
			JRCrosstabColumnGroup cg = columns[i];
			CrosstabCell cc = null;
			if (cg.hasTotal()) {
				cc = new CrosstabCell(
						(JRDesignCellContents) cg.getTotalHeader(),
						JRCrosstabOrigin.TYPE_COLUMN_GROUP_TOTAL_HEADER);
				north.addSouth(cc);
				south.addNorth(cc);
				south.setY(north, cc.cell);
			}

			cc = new CrosstabCell((JRDesignCellContents) cg.getHeader(),
					JRCrosstabOrigin.TYPE_COLUMN_GROUP_HEADER);
			north.addSouth(cc);

			Guide tsouth = null;
			if (i == columns.length - 1)
				tsouth = south;
			else
				tsouth = addNext(north, hGuides);
			tsouth.addNorth(cc);
			tsouth.setY(north, cc.cell);
			north = tsouth;
		}
		return south;
	}

	public Guide fillRowGroup(Guide north, Guide south,
			JRDesignCrosstab crosstab) {
		JRCrosstabCell[][] cells = crosstab.getCells();
		JRCrosstabRowGroup[] rows = crosstab.getRowGroups();
		for (int i = 0; i < rows.length; i++) {
			JRCrosstabRowGroup cg = rows[i];
			CrosstabCell cc = new CrosstabCell(
					(JRDesignCellContents) cg.getHeader(),
					JRCrosstabOrigin.TYPE_ROW_GROUP_HEADER);
			south.addSouth(cc);
			Guide tsouth = cg.hasTotal() ? addNext(south, hGuides) : south;
			tsouth.addNorth(cc);
			south.setY(north, cc.cell);

			if (cg.hasTotal()) {
				cc = new CrosstabCell(
						(JRDesignCellContents) cg.getTotalHeader(),
						JRCrosstabOrigin.TYPE_ROW_GROUP_TOTAL_HEADER);
				tsouth.addSouth(cc);
				south.addNorth(cc);
				south.setY(tsouth, cc.cell);
			}
			south = tsouth;
		}
		return south;
	}

	public Guide fillHeaderCell(Guide north, Guide south,
			JRDesignCrosstab crosstab) {
		JRCellContents c = crosstab.getHeaderCell();
		CrosstabCell cc = new CrosstabCell((JRDesignCellContents) c,
				JRCrosstabOrigin.TYPE_HEADER_CELL);
		cc.setNorth(north);
		cc.setSouth(south);
		south.setY(north, c);
		return south;
	}

	private Guide addNext(Guide north, List<Guide> guides) {
		Guide south = new Guide(north.getY());
		int i = guides.indexOf(north) + 1;
		if (i <= guides.size())
			guides.add(i, south);
		else
			guides.add(south);
		return south;
	}

	public void print() {
		System.out.println("-- NEW TABLE--------------------");
		for (int i = 0; i < hGuides.size(); i++) {
			Guide g = hGuides.get(i);
			System.out.println("row:" + i + "\n" + g.toString());
		}
		System.out.println("-- VERTICAL --------------------");
		for (int i = 0; i < vGuides.size(); i++) {
			Guide g = vGuides.get(i);
			System.out.println("col:" + i + "\n" + g.toString());
		}
	}
}
