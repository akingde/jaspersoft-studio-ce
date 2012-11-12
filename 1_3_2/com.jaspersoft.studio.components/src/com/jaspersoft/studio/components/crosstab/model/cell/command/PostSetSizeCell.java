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
package com.jaspersoft.studio.components.crosstab.model.cell.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCellContents;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabCell;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.components.crosstab.model.cell.MCell;
import com.jaspersoft.studio.editor.layout.ILayout;
import com.jaspersoft.studio.editor.layout.LayoutCommand;
import com.jaspersoft.studio.editor.layout.LayoutManager;
import com.jaspersoft.studio.property.IPostSetValue;

public class PostSetSizeCell implements IPostSetValue {

	@Override
	public Command postSetValue(IPropertySource target, Object prop,
			Object value) {
		if (target instanceof MCell
				&& (prop.equals(JRDesignCrosstabCell.PROPERTY_WIDTH) || prop
						.equals(JRDesignCrosstabCell.PROPERTY_HEIGHT))) {
			MCell mband = (MCell) target;
			JasperDesign jDesign = mband.getJasperDesign();
			return getResizeCommand(mband, jDesign);
		}
		return null;
	}

	public Command getResizeCommand(MCell mcell, JasperDesign jDesign) {
		JRDesignCellContents band = mcell.getValue();
		ILayout layout = LayoutManager.getLayout(mcell.getPropertyHolder(),
				jDesign, null);
		Rectangle r = mcell.getBounds();
		Dimension d = new Dimension(r.width, r.height);
		return new LayoutCommand(band, layout, d);
	}

}
