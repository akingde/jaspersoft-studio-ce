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

import net.sf.jasperreports.charts.JRChartAxis;
import net.sf.jasperreports.charts.JRMultiAxisPlot;
import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.IComponentFactory;
import com.jaspersoft.studio.chart.action.CreateChartAxisAction;
import com.jaspersoft.studio.chart.figure.ChartFigure;
import com.jaspersoft.studio.chart.model.MChart;
import com.jaspersoft.studio.chart.model.chartAxis.MChartAxes;
import com.jaspersoft.studio.chart.model.chartAxis.command.CreateChartAxesCommand;
import com.jaspersoft.studio.chart.model.chartAxis.command.DeleteChartAxesCommand;
import com.jaspersoft.studio.chart.model.chartAxis.command.ReorderChartAxesCommand;
import com.jaspersoft.studio.chart.model.command.CreateChartCommand;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;

public class ChartComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignChart)
			return new MChart(parent, (JRDesignChart) jrObject, newIndex);
		if (jrObject instanceof JRDesignChartAxis)
			return new MChartAxes(parent, (JRChartAxis) jrObject, newIndex);
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MChart)
			return new ChartFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		if (jrObject instanceof JRChart && ((JRChart) jrObject).getPlot() instanceof JRMultiAxisPlot) {
			JRMultiAxisPlot slc = (JRMultiAxisPlot) ((JRChart) jrObject).getPlot();
			List<?> lc = slc.getAxes();
			if (lc != null)
				return lc;
		}
		return null;
	}

	public List<Class<?>> getPaletteEntries() {
		List<Class<?>> list = new ArrayList<Class<?>>();
		list.add(MChart.class);
		return list;
	}

	public Command getCreateCommand(ANode parent, ANode child, Point location, int newIndex) {
		if (child instanceof MChart) {
			if (parent instanceof MElementGroup)
				return new CreateChartCommand((MElementGroup) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateChartCommand((MBand) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateChartCommand((MFrame) parent, (MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateChartCommand(parent, (MGraphicElement) child, location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateChartCommand(parent, (MGraphicElement) child, location, newIndex);
			}
		} else if (child instanceof MChartAxes) {
			if (parent instanceof MChart) {
				JRDesignChart dc = (JRDesignChart) parent.getValue();
				if (dc.getPlot() instanceof JRDesignMultiAxisPlot)
					return new CreateChartAxesCommand((MChart) parent, (MChartAxes) child, newIndex);
			}
		}
		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		if (child instanceof MChartAxes) {
			return new DeleteChartAxesCommand((MChart) child.getParent(), (MChartAxes) child);
		}
		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		if (child instanceof MChartAxes && parent instanceof MChart) {
			return new ReorderChartAxesCommand((MChartAxes) child, parent, newIndex);
		}
		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		List<Action> lst = new ArrayList<Action>();
		lst.add(new CreateChartAxisAction(part));
		return lst;
	}

	public List<String> getActionsID() {
		List<String> lst = new ArrayList<String>();
		lst.add(CreateChartAxisAction.ID);
		return lst;
	}

}
