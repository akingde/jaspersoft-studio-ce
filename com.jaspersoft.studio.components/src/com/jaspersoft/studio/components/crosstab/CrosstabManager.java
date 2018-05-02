/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;

import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.editor.layout.VerticalRowLayout;
import com.jaspersoft.studio.utils.ModelUtils;

import net.sf.jasperreports.crosstabs.JRCellContents;
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
import net.sf.jasperreports.engine.JRChild;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JasperDesign;

public class CrosstabManager {

	private JRDesignCrosstab crosstab;
	
	private JasperDesign jDesign;

	public CrosstabManager(JRDesignCrosstab crosstab, JasperDesign jd) {
		this.crosstab = crosstab;
		this.jDesign = jd;
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
		for (Rectangle r : matrix.getCells().values()) {
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

	public CrosstabCell getCell(Point location) {
		Map<CrosstabCell, Rectangle> cellmap = matrix.getCells();
		for (CrosstabCell cell : cellmap.keySet()) {
			Rectangle r = cellmap.get(cell);
			if (r.x <= location.x && r.x + r.width >= location.x && r.y <= location.y && r.y + r.height >= location.y)
				return cell;
		}
		return null;
	}

	public Rectangle getCellBounds(CrosstabCell cell) {
		return matrix.getBounds(cell);
	}

	public static int getHW(int hw) {
		return getHW(hw, 0);
	}

	public static int getHW(int hw, int def) {
		if (hw < 0)
			return def;
		return hw;
	}

	private CrosstabMatrix matrix = new CrosstabMatrix();

	public void init(JRDesignCrosstab crosstab) {
		matrix.fill(crosstab);
		setSize();
	}

	public void setCellRow(int y, String rowTotal) {
		JRCrosstabCell[][] cells = crosstab.getCells();

		for (int i = cells.length - 1; i >= 0; i--) {
			for (int j = cells[i].length - 1; j >= 0; j--) {
				JRCrosstabCell jrCrosstabCell = cells[i][j];
				if (jrCrosstabCell != null
						&& ((jrCrosstabCell.getRowTotalGroup() != null && jrCrosstabCell.getRowTotalGroup().equals(rowTotal)) || (rowTotal == null && jrCrosstabCell.getRowTotalGroup() == null))) {
					Rectangle r = matrix.getBounds(new CrosstabCell((JRDesignCellContents) jrCrosstabCell.getContents()));
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
						&& ((jrCrosstabCell.getColumnTotalGroup() != null && jrCrosstabCell.getColumnTotalGroup().equals(colTotal)) || (colTotal == null && jrCrosstabCell.getColumnTotalGroup() == null))) {
					Rectangle r = matrix.getBounds(new CrosstabCell((JRDesignCellContents) jrCrosstabCell.getContents()));
					r.setLocation(x, r.y);
				}
			}
		}
	}

	public Rectangle getBounds(CrosstabCell cell) {
		return matrix.getBounds(cell);

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
					if (jrCrosstabCell != null && jrCrosstabCell.getContents() == cell) {
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
		case JRCrosstabOrigin.TYPE_COLUMN_GROUP_CROSSTAB_HEADER:
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
					if (jrCrosstabCell != null && jrCrosstabCell.getColumnTotalGroup() != null && colGroupName != null && jrCrosstabCell.getColumnTotalGroup().equals(colGroupName)) {
						if (!calculated) {
							width = getCellWidth(jrCrosstabCell) + width - cell.getWidth();
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
						JRDesignCrosstabColumnGroup cgNext = (JRDesignCrosstabColumnGroup) colGroupsList.get(i + 1);
						if (cgNext.getHeader() != null && cgNext.getTotalHeader() != null){
							int cgNextWidth = getCellWidth(cgNext.getHeader()) + getCellWidth(cgNext.getTotalHeader());
							int cgNextNewWidth = cgNextWidth + delta;
							int[] proprtionalWidth = getColumnsProportionalWidth(cgNext, cgNextNewWidth);
							setWidth((JRDesignCellContents)cgNext.getHeader(), proprtionalWidth[0]);
							setWidth((JRDesignCellContents)cgNext.getTotalHeader(), proprtionalWidth[1]);
						} else if (rg.getTotalHeader() != null) {
							JRDesignCellContents totalHeader = (JRDesignCellContents) cgNext.getTotalHeader();
							setWidth(totalHeader, totalHeader.getWidth() + delta);
						} else {
							JRDesignCellContents header = (JRDesignCellContents) cgNext.getHeader();
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
						if (jrCrosstabCell != null&& jrCrosstabCell.getContents() == cell) {
							jrCrosstabCell.setHeight(height);

							for (int k = 0; k < cells[i].length; k++) {
								if (cells[i][k] != null) {
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
		case JRCrosstabOrigin.TYPE_COLUMN_GROUP_CROSSTAB_HEADER:
			if (!colGroupsList.isEmpty()) {
				JRDesignCrosstabColumnGroup p = (JRDesignCrosstabColumnGroup) colGroupsList.get(colGroupsList.size() - 1);
				setCellHeight(p, p.getHeight(), height);
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
					if (jrCrosstabCell != null && jrCrosstabCell.getRowTotalGroup() != null && rowGroupName != null && jrCrosstabCell.getRowTotalGroup().equals(rowGroupName)) {
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

	private void setCellHeight(JRDesignCrosstabColumnGroup p, int oldValue, int height) {
		if (height >= 0) {
			p.setHeight(height);
		} else {
			p.setHeight(0);
		}
		p.getEventSupport().firePropertyChange(JRDesignCrosstabColumnGroup.PROPERTY_HEIGHT, oldValue, height);
	}

	private void setCellWidth(JRDesignCrosstabRowGroup p, int oldValue, int width) {
		if (width > 0) {
			p.setWidth(width);
		} else {
			p.setWidth(0);			
		}
		p.getEventSupport().firePropertyChange(JRDesignCrosstabRowGroup.PROPERTY_WIDTH, oldValue, width);
	}

	public Dimension getCellPackSize(CrosstabCell cc) {
		cc = matrix.getCrosstabCell(cc);
		if (cc == null)
			return null;
		Guide g = cc.getEast();
		int w = -g.getY();
		for (CrosstabCell c : g.getPrev()) {
			if (c.cell != null) {
				List<JRChild> cells = c.cell.getChildren();
				if (!cells.isEmpty()) {
					int width = ModelUtils.getContainerSize(cells, new Dimension(0, 0)).width;

					w = Math.max(w, width - c.cell.getWidth());
				}
			}
		}
		g = cc.getSouth();
		int h = 0;
		if (g != null) {
			h = -g.getY();
			for (CrosstabCell c : g.getPrev()) {
				if (c.cell != null) {
					List<JRChild> cells = c.cell.getChildren();
					if (!cells.isEmpty()) {
						int height = ModelUtils.getContainerSize(cells, new Dimension(0, 0)).height;

						h = Math.max(h, height - c.cell.getHeight());
					}
				}
			}
		}
		Rectangle b = cc.getBounds();

		return new Dimension(b.width + w, b.height + h);
	}

	public List<CrosstabCell> getLeftOf(CrosstabCell cell) {
		cell = matrix.getCrosstabCell(cell);
		Guide g = cell.getWest();
		return g.getPrev();
	}

	public List<CrosstabCell> getTopOf(CrosstabCell cell) {
		cell = matrix.getCrosstabCell(cell);
		Guide g = cell.getNorth();
		return g.getPrev();
	}

	public List<CrosstabCell> getBottomOf(CrosstabCell cell) {
		cell = matrix.getCrosstabCell(cell);
		Guide g = cell.getSouth();
		return g.getPrev();
	}

	public List<CrosstabCell> getRightOf(CrosstabCell cell) {
		cell = matrix.getCrosstabCell(cell);
		Guide g = cell.getEast();
		return g.getPrev();
	}
	
	/**
	 * Get the width of every cell in the crosstab
	 * 
	 * @return a not null hashmap where the key is every cell of the crosstab and the value is 
	 * its width
	 */
	private HashMap<JRCellContents, Integer> getCellsWidth(){
		HashMap<JRCellContents, Integer> originalWidth = new HashMap<JRCellContents, Integer>();
		for(JRCrosstabColumnGroup group : crosstab.getColumnGroups()){
			if (group.getHeader() != null){
				originalWidth.put(group.getHeader(), group.getHeader().getWidth());
			}
			if (group.getTotalHeader() != null){
				originalWidth.put(group.getTotalHeader(), group.getTotalHeader().getWidth());
			}
			if (group.getCrosstabHeader() != null){
				originalWidth.put(group.getCrosstabHeader(), group.getCrosstabHeader().getWidth());
			}
		}
		for(JRCrosstabRowGroup group : crosstab.getRowGroups()){
			if (group.getHeader() != null){
				originalWidth.put(group.getHeader(), group.getHeader().getWidth());
			}
			if (group.getTotalHeader() != null){
				originalWidth.put(group.getTotalHeader(), group.getTotalHeader().getWidth());
			}
		}
		for(JRCrosstabCell cell : crosstab.getCellsList()){
			if (cell.getContents() != null){
				originalWidth.put(cell.getContents(), cell.getWidth());
			}
		}
		return originalWidth;
	}
	
	private void relayoutChangedCells(HashMap<JRCellContents, Integer> originalWidth){
		List<JRCellContents> cellsToLayout = new ArrayList<JRCellContents>();
	
		for(JRCrosstabColumnGroup group : crosstab.getColumnGroups()){
			
			if (group.getHeader() != null){
				Integer size = originalWidth.get(group.getHeader());
				if (!group.getHeader().equals(size)){
					cellsToLayout.add(group.getHeader());
				}
			}
			
			if (group.getTotalHeader() != null){
				Integer size = originalWidth.get(group.getTotalHeader());
				if (!group.getTotalHeader().equals(size)){
					cellsToLayout.add(group.getTotalHeader());
				}
			}
			
			if (group.getCrosstabHeader() != null){
				Integer size = originalWidth.get(group.getCrosstabHeader());
				if (!group.getCrosstabHeader().equals(size)){
					cellsToLayout.add(group.getCrosstabHeader());
				}
			}
		}
		
		for(JRCrosstabRowGroup group : crosstab.getRowGroups()){
			
			if (group.getHeader() != null){
				Integer size = originalWidth.get(group.getHeader());
				if (!group.getHeader().equals(size)){
					cellsToLayout.add(group.getHeader());
				}
			}
			
			if (group.getTotalHeader() != null){
				Integer size = originalWidth.get(group.getTotalHeader());
				if (!group.getTotalHeader().equals(size)){
					cellsToLayout.add(group.getTotalHeader());
				}
			}
		}
		
		for(JRCrosstabCell cell : crosstab.getCellsList()){
			if (cell.getContents() != null){
				Integer size = originalWidth.get(cell.getContents());
				if (!cell.getContents().equals(size)){
					cellsToLayout.add(cell.getContents());
				}
			}
		}

		ILayout defaultLayout = new VerticalRowLayout();
		for(JRCellContents cell : cellsToLayout){
			ILayout layout = LayoutManager.getLayout(new JRPropertiesHolder[] { cell }, null, null, defaultLayout);
			layout.layout(jDesign, cell, cell.getElements(), new Dimension(cell.getWidth(), cell.getHeight()));
		}
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
		synchronized (crosstab) {
			
			//Calculate the current size of the column
			int rowGroupsHeaderSize = 0;
			int columnGroupHeaderSize = 0;
			int columnGroupTotalHeaderSize = 0;
			
			if (crosstab.getRowGroups().length > 0){
				rowGroupsHeaderSize = crosstab.getRowGroups()[0].getTotalHeader().getWidth();
			}
			
			if (crosstab.getColumnGroups().length > 0){
				JRCrosstabColumnGroup firstColGroup = crosstab.getColumnGroups()[0];
				columnGroupHeaderSize = getCellWidth(firstColGroup.getHeader());
				columnGroupTotalHeaderSize = getCellWidth(firstColGroup.getTotalHeader());
			}
			
			int currentColumnsWidth = rowGroupsHeaderSize + columnGroupHeaderSize + columnGroupTotalHeaderSize;
			
			if (currentColumnsWidth == newWidth) {
				return;
			} else if(isProportional) {
				
				HashMap<JRCellContents, Integer> originalSize = getCellsWidth();
				int[] proportionalWidths = getColumnsProportionalWidth(newWidth);
				
				if (crosstab.getRowGroups().length > 0){
					setWidth((JRDesignCellContents)crosstab.getRowGroups()[0].getTotalHeader(), proportionalWidths[2]);
				}
				
				if (crosstab.getColumnGroups().length > 0){
					JRCrosstabColumnGroup group = crosstab.getColumnGroups()[0];
					if(group.getHeader() != null){
						setWidth((JRDesignCellContents)group.getHeader(), proportionalWidths[0]);
					}
					if(group.getTotalHeader() != null){
						setWidth((JRDesignCellContents)group.getTotalHeader(), proportionalWidths[1]);
					}	
				}
				refresh();
				crosstab.getEventSupport().firePropertyChange(new PropertyChangeEvent(crosstab, JRDesignCrosstabCell.PROPERTY_WIDTH, null, null));
				relayoutChangedCells(originalSize);
			} else {
				/*int columnsSize = newWidth / table.getColumns().size();
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
				}*/
			}
		}
	}
	
	private static int getCellWidth(JRCellContents cell){
		if (cell != null) return cell.getWidth();
		else return 0;
	}

	private static int getCellWidth(JRCrosstabCell cell){
		if (cell != null && cell.getWidth() != null) return cell.getWidth();
		else return 0;
	}
	
	
	/**
	 * Return the proportional size of a crosstab column group header cells
	 * 
	 * @param newWidth the new width of the columns
	 * @return an array containing in the first position the proportional new size of the header cell and in 
	 * the second position the proportional new size of the total header cell and in the third posizion
	 * the size of the first row group header
	 */
	private int[] getColumnsProportionalWidth(int newWidth){
		int[] result = {0, 0, 0};
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;		
		
		int rowGroupsHeaderSize = 0;
		int columnGroupHeaderSize = 0;
		int columnGroupTotalHeaderSize = 0;
		
		if (crosstab.getRowGroups().length > 0){
			rowGroupsHeaderSize = crosstab.getRowGroups()[0].getTotalHeader().getWidth();
		}
		 
		if (crosstab.getColumnGroups().length > 0){
			JRCrosstabColumnGroup firstColGroup = crosstab.getColumnGroups()[0];
			columnGroupHeaderSize = getCellWidth(firstColGroup.getHeader());
			columnGroupTotalHeaderSize = getCellWidth(firstColGroup.getTotalHeader());
		}
		
		int currentColumnsWidth =  columnGroupHeaderSize + columnGroupTotalHeaderSize + rowGroupsHeaderSize;

		float proportionalFactor = (float)columnGroupHeaderSize / (float)currentColumnsWidth;
		//casting to int is the same to do the floor operation, since it drop the decimal
		int proportionalWidth = (int)(proportionalFactor * newWidth);
		result[0] = proportionalWidth;
		columnsTotalWidth += proportionalWidth;
		
		proportionalFactor = (float)columnGroupTotalHeaderSize / (float)currentColumnsWidth;
		//casting to int is the same to do the floor operation, since it drop the decimal
		proportionalWidth = (int)(proportionalFactor * newWidth);
		result[1] = proportionalWidth;
		columnsTotalWidth += proportionalWidth;
		
		proportionalFactor = (float)rowGroupsHeaderSize / (float)currentColumnsWidth;
		//casting to int is the same to do the floor operation, since it drop the decimal
		proportionalWidth = (int)(proportionalFactor * newWidth);
		result[2] = proportionalWidth;
		columnsTotalWidth += proportionalWidth;
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		int index = 0;
		while (remains > 0){
			result[index]++;
			index++;
			remains--;
			if (index == result.length){
				index = 0;
			}
		}
		return result;
	}
	
	/**
	 * Return the proportional size of a crosstab column group header cells
	 * 
	 * @param group the group to calculate the relative size
	 * @param newWidth the new width the group must fit
	 * @return an array containing in the first position the proportional new size of the header cell and in 
	 * the second position the proportional new size of the total header cell
	 */
	private int[] getColumnsProportionalWidth(JRCrosstabColumnGroup group, int newWidth){
		int[] result = {0, 0};
		//Phase 1: change proportionally the width of each column
		int columnsTotalWidth = 0;		
		int currentColumnsWidth = getCellWidth(group.getHeader()) + getCellWidth(group.getTotalHeader());
		if (group.getHeader() != null){
			float proportionalFactor = (float)group.getHeader().getWidth() / (float)currentColumnsWidth;
			//casting to int is the same to do the floor operation, since it drop the decimal
			int proportionalWidth = (int)(proportionalFactor * newWidth);
			result[0] = proportionalWidth;
			columnsTotalWidth += proportionalWidth;
		}
		if (group.getTotalHeader() != null){
			float proportionalFactor = (float)group.getTotalHeader().getWidth() / (float)currentColumnsWidth;
			//casting to int is the same to do the floor operation, since it drop the decimal
			int proportionalWidth = (int)(proportionalFactor * newWidth);
			result[1] = proportionalWidth;
			columnsTotalWidth += proportionalWidth;
		}
		
		//Phase 2: reassign what remains
		int remains = newWidth - columnsTotalWidth;
		int index = 0;
		while (remains > 0){
			result[index]++;
			index++;
			remains--;
			if (index == result.length){
				index = 0;
			}
		}
		
		return result;
	}
}
