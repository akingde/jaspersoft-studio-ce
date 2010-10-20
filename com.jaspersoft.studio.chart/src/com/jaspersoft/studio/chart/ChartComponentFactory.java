/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.chart.figure.ChartFigure;
import com.jaspersoft.studio.chart.model.MChart;
import com.jaspersoft.studio.model.ANode;

public class ChartComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignChart)
			return new MChart(parent, (JRDesignChart) jrObject, newIndex);
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MChart)
			return new ChartFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {

		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MChart.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		return null;
	}

}
