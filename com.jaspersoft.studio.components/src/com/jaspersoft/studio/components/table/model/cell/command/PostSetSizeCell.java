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
package com.jaspersoft.studio.components.table.model.cell.command;

import java.util.List;

import net.sf.jasperreports.components.table.DesignCell;
import net.sf.jasperreports.components.table.StandardBaseColumn;
import net.sf.jasperreports.engine.JRPropertiesHolder;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.table.ColumnCell;
import com.jaspersoft.studio.components.table.Guide;
import com.jaspersoft.studio.components.table.TableManager;
import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.components.table.model.column.MCell;
import com.jaspersoft.studio.components.table.model.column.MColumn;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetSizeCell implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop,
			Object value) {
		if (target instanceof MColumn
				&& (prop.equals(StandardBaseColumn.PROPERTY_WIDTH) || prop
						.equals(DesignCell.PROPERTY_HEIGHT))) {
			MCell mband = (MCell) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getResizeCommand(mband, jDesign, prop);
		}
		return null;
	}

	public Command getResizeCommand(MCell mcell, JasperDesign jDesign,
			Object prop) {
		MTable mTable = mcell.getMTable();

		JRPropertiesHolder[] pholder = new JRPropertiesHolder[3];
		pholder[2] = mTable.getValue();

		TableManager tb = mTable.getTableManager();
		CompoundCommand c = new CompoundCommand("Resize Table Cell");
		ColumnCell cc = tb.getMatrixHelper().getColumnCell(
				new ColumnCell(mcell.getType(), mcell.getGrName(), mcell
						.getValue()));
		if (prop.equals(DesignCell.PROPERTY_HEIGHT))
			if (TableManager.isBottomOfTable(cc.type)) {
				Guide guide = cc.getNorth();
				createCommands(jDesign, pholder, c, guide.getNext());
			} else {
				Guide guide = cc.getSouth();
				createCommands(jDesign, pholder, c, guide.getPrev());
			}
		else if (prop.equals(StandardBaseColumn.PROPERTY_WIDTH)) {
			Guide guide = cc.getWest();
			createCommands(jDesign, pholder, c, guide.getNext());
		}
		return c;
	}

	public void createCommands(JasperDesign jDesign,
			JRPropertiesHolder[] pholder, CompoundCommand c,
			List<ColumnCell> cells) {
		for (ColumnCell ccell : cells) {
			if (ccell.cell == null)
				continue;
			pholder[0] = ccell.cell;
			pholder[1] = ccell.column;
			ILayout layout = LayoutManager.getLayout(pholder, jDesign, null);
			Rectangle r = ccell.getBounds();
			Dimension d = new Dimension(r.width, r.height);

			c.add(new LayoutCommand(ccell.cell, layout, d));
		}
	}

}
