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

	@Override
	public void execute() {
		tbManager.initMaps();
		if (hmap == null)
			hmap = new HashMap<DesignCell, Integer>();
		MatrixHelper mh = tbManager.getMatrixHelper();
		Map<ColumnCell, Rectangle> map = mh.getCells();
		for (ColumnCell cc : map.keySet()) {
			Rectangle b = cc.getBounds();
			if (cc.column instanceof StandardColumnGroup) {
				if (b.width != cc.column.getWidth())
					((StandardColumnGroup) cc.column).setWidth(b.width);
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
		for (DesignCell dc : hmap.keySet()) {
			dc.setHeight(hmap.get(dc));
		}
		tbManager.initMaps();
		tbManager.refresh();
	}
}
