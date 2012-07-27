/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2012 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.table.model.column.command;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.components.table.Cell;
import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardColumnGroup;
import net.sf.jasperreports.components.table.util.TableUtil;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.table.ColumnCell;
import com.jaspersoft.studio.components.table.MatrixHelper;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.column.MColumn;

public class FixCellHeightsCommand extends Command {
	private TableManager tbManager;

	public FixCellHeightsCommand(MColumn mcolumn) {
		super("Fix Table Cells Height");
		tbManager = mcolumn.getMTable().getTableManager();
	}

	private Map<DesignCell, Integer> hmap;
	private Map<StandardColumnGroup, Integer> wmap;

	@Override
	public void execute() {
		tbManager.initMaps();
		if (hmap == null)
			hmap = new HashMap<DesignCell, Integer>();
		hmap.clear();
		if (wmap == null)
			wmap = new HashMap<StandardColumnGroup, Integer>();
		wmap.clear();
		MatrixHelper mh = tbManager.getMatrixHelper();
		Map<ColumnCell, Rectangle> map = mh.getCells();
		for (ColumnCell cc : map.keySet()) {
			Rectangle b = cc.getBounds();
			if (cc.column instanceof StandardColumnGroup) {
				if (b.width != cc.column.getWidth()) {
					wmap.put((StandardColumnGroup) cc.column,
							cc.column.getWidth());
					((StandardColumnGroup) cc.column).setWidth(b.width);
				}
				continue;
			}
			Cell dc = TableUtil.getCell(cc.column, cc.type, cc.grName);
			if (dc == null)
				continue;

			int oldh = dc.getHeight();
			if (b.height != dc.getHeight()) {
				((DesignCell) dc).setHeight(b.height);
			} else
				continue;
			hmap.put((DesignCell) dc, oldh);
		}
		tbManager.initMaps();
		tbManager.refresh();
	}

	@Override
	public void undo() {
		for (DesignCell dc : hmap.keySet())
			dc.setHeight(hmap.get(dc));
		for (StandardColumnGroup bc : wmap.keySet())
			bc.setWidth(wmap.get(bc));
		tbManager.initMaps();
		tbManager.refresh();
	}
}
