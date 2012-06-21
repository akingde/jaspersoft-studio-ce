/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.list.part;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Dimension;

import com.jaspersoft.studio.components.list.model.MList;
import com.jaspersoft.studio.editor.gef.figures.APageFigure;
import com.jaspersoft.studio.editor.gef.figures.ContainerPageFigure;
import com.jaspersoft.studio.editor.gef.parts.PageEditPart;
import com.jaspersoft.studio.utils.ModelUtils;

public class ListPageEditPart extends PageEditPart {

	@Override
	protected APageFigure newPageFigure() {
		return new ContainerPageFigure(true);
	}

	@Override
	protected void setupPageFigure(APageFigure figure2) {
		updateContainerSize();
		((ContainerPageFigure) figure2).setContainerSize(getContaierSize());
	}

	private Dimension containerSize;

	public Dimension getContaierSize() {
		return containerSize;
	}

	private void updateContainerSize() {
		MList table = (MList) ModelUtils.getFirstChild(getPage());
		if (table != null) {
			Dimension d = table.getContainerSize();
			d.height = Math.max(d.height, (Integer) table
					.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT));
			d.width = Math.max(d.width, (Integer) table
					.getPropertyValue(JRDesignElement.PROPERTY_WIDTH));
			containerSize = d;
		} else
			containerSize = null;
	}
}
