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
package com.jaspersoft.studio.components.chart;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.charts.JRChartAxis;
import net.sf.jasperreports.charts.JRMultiAxisPlot;
import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignCategorySeries;
import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports.charts.design.JRDesignGanttSeries;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.charts.design.JRDesignPieSeries;
import net.sf.jasperreports.charts.design.JRDesignTimePeriodDataset;
import net.sf.jasperreports.charts.design.JRDesignTimePeriodSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeries;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.charts.design.JRDesignXyDataset;
import net.sf.jasperreports.charts.design.JRDesignXySeries;
import net.sf.jasperreports.charts.design.JRDesignXyzDataset;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;
import net.sf.jasperreports.engine.JRChart;
import net.sf.jasperreports.engine.JRChartDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignChartDataset;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.jface.action.Action;
import org.eclipse.ui.part.WorkbenchPart;

import com.jaspersoft.studio.components.chart.action.CreateChartAxisAction;
import com.jaspersoft.studio.components.chart.figure.ChartFigure;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.chartAxis.MChartAxes;
import com.jaspersoft.studio.components.chart.model.chartAxis.command.CreateChartAxesCommand;
import com.jaspersoft.studio.components.chart.model.chartAxis.command.DeleteChartAxesCommand;
import com.jaspersoft.studio.components.chart.model.chartAxis.command.ReorderChartAxesCommand;
import com.jaspersoft.studio.components.chart.model.command.CreateChartCommand;
import com.jaspersoft.studio.components.chart.model.dataset.ChartDatasetFactory;
import com.jaspersoft.studio.components.chart.model.dataset.MChartCategoryDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartGanttDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartPieDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartTimePeriodDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartTimeSeriesDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartXYDataset;
import com.jaspersoft.studio.components.chart.model.dataset.MChartXYZDataset;
import com.jaspersoft.studio.components.chart.model.series.category.MCategorySeries;
import com.jaspersoft.studio.components.chart.model.series.category.action.CreateCategorySeriesAction;
import com.jaspersoft.studio.components.chart.model.series.category.command.CreateCategorySeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.category.command.DeleteCategorySeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.category.command.ReorderCategorySeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.gantt.MGanttSeries;
import com.jaspersoft.studio.components.chart.model.series.gantt.action.CreateGanttAction;
import com.jaspersoft.studio.components.chart.model.series.gantt.command.CreateGanttSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.gantt.command.DeleteGanttSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.gantt.command.ReorderGanttSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.pie.MPieSeries;
import com.jaspersoft.studio.components.chart.model.series.pie.action.CreatePieAction;
import com.jaspersoft.studio.components.chart.model.series.pie.command.CreatePieSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.pie.command.DeletePieSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.pie.command.ReorderPieSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.time.MTimeSeries;
import com.jaspersoft.studio.components.chart.model.series.time.action.CreateTimeAction;
import com.jaspersoft.studio.components.chart.model.series.time.command.CreateTimeSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.time.command.DeleteTimeSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.time.command.ReorderTimeSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.MTimePeriodSeries;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.action.CreateTimePeriodAction;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.command.CreateTimePeriodSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.command.DeleteTimePeriodSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.command.ReorderTimePeriodSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.xyseries.MXYSeries;
import com.jaspersoft.studio.components.chart.model.series.xyseries.action.CreateXYAction;
import com.jaspersoft.studio.components.chart.model.series.xyseries.command.CreateXYSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.xyseries.command.DeleteXYSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.xyseries.command.ReorderXYSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.MXYZSeries;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.action.CreateXYZAction;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.command.CreateXYZSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.command.DeleteXYZSeriesCommand;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.command.ReorderXYZSeriesCommand;
import com.jaspersoft.studio.components.chart.part.ChartEditPart;
import com.jaspersoft.studio.components.chart.wizard.action.ChartWizardAction;
import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IGroupElement;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MFrame;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.plugin.IComponentFactory;
import com.jaspersoft.studio.plugin.IPaletteContributor;
import com.jaspersoft.studio.plugin.PaletteContributor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ChartComponentFactory implements IComponentFactory {

	public ANode createNode(ANode parent, Object jrObject, int newIndex) {
		if (jrObject instanceof JRDesignChart)
			return new MChart(parent, (JRDesignChart) jrObject, newIndex);
		if (jrObject instanceof JRDesignChartAxis) {
			MChartAxes mChartAxes = new MChartAxes(parent,
					(JRChartAxis) jrObject, newIndex);
			return mChartAxes;
		}
		if (jrObject instanceof JRDesignChartDataset)
			return ChartDatasetFactory.getChartDataset(parent,
					(JRChartDataset) jrObject, newIndex);
		if (jrObject instanceof JRDesignCategorySeries)
			return new MCategorySeries(parent,
					(JRDesignCategorySeries) jrObject, newIndex);
		if (jrObject instanceof JRDesignGanttSeries)
			return new MGanttSeries(parent, (JRDesignGanttSeries) jrObject,
					newIndex);
		if (jrObject instanceof JRDesignPieSeries)
			return new MPieSeries(parent, (JRDesignPieSeries) jrObject,
					newIndex);
		if (jrObject instanceof JRDesignTimePeriodSeries)
			return new MTimePeriodSeries(parent,
					(JRDesignTimePeriodSeries) jrObject, newIndex);
		if (jrObject instanceof JRDesignTimeSeries)
			return new MTimeSeries(parent, (JRDesignTimeSeries) jrObject,
					newIndex);
		if (jrObject instanceof JRDesignXySeries)
			return new MXYSeries(parent, (JRDesignXySeries) jrObject, newIndex);
		if (jrObject instanceof JRDesignXyzSeries)
			return new MXYZSeries(parent, (JRDesignXyzSeries) jrObject,
					newIndex);
		return null;
	}

	public IFigure createFigure(ANode node) {
		if (node instanceof MChart)
			return new ChartFigure();
		return null;
	}

	public List<?> getChildren4Element(Object jrObject) {
		List<Object> ch = new ArrayList<Object>();
		if (jrObject instanceof JRDesignCategoryDataset)
			ch.addAll(((JRDesignCategoryDataset) jrObject).getSeriesList());
		else if (jrObject instanceof JRDesignGanttDataset)
			ch.addAll(((JRDesignGanttDataset) jrObject).getSeriesList());
		else if (jrObject instanceof JRDesignPieDataset)
			ch.addAll(((JRDesignPieDataset) jrObject).getSeriesList());
		else if (jrObject instanceof JRDesignTimePeriodDataset)
			ch.addAll(((JRDesignTimePeriodDataset) jrObject).getSeriesList());
		else if (jrObject instanceof JRDesignTimeSeriesDataset)
			ch.addAll(((JRDesignTimeSeriesDataset) jrObject).getSeriesList());
		else if (jrObject instanceof JRDesignXyDataset)
			ch.addAll(((JRDesignXyDataset) jrObject).getSeriesList());
		else if (jrObject instanceof JRDesignXyzDataset)
			ch.addAll(((JRDesignXyzDataset) jrObject).getSeriesList());

		else if (jrObject instanceof JRDesignChartAxis)
			ch.add(((JRDesignChartAxis) jrObject).getChart().getDataset());

		if (jrObject instanceof JRChart) {
			ch.add(((JRChart) jrObject).getDataset());
		}
		if (jrObject instanceof JRChart
				&& ((JRChart) jrObject).getPlot() instanceof JRMultiAxisPlot) {
			JRMultiAxisPlot slc = (JRMultiAxisPlot) ((JRChart) jrObject)
					.getPlot();
			if (slc.getAxes() != null)
				ch.addAll(slc.getAxes());
		}
		return ch;
	}

	public IPaletteContributor getPaletteEntries() {
		PaletteContributor pc = new PaletteContributor();
		// pc.add("com.jaspersoft.studio.components.CHARTCOMPONENTS",
		// MChart.class);
		pc.add(MChart.class);
		return pc;
	}

	public Command getCreateCommand(ANode parent, ANode child,
			Rectangle location, int newIndex) {
		if (child instanceof MChart) {
			if (parent instanceof MElementGroup)
				return new CreateChartCommand((MElementGroup) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MBand)
				return new CreateChartCommand((MBand) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MFrame)
				return new CreateChartCommand((MFrame) parent,
						(MGraphicElement) child, newIndex);
			if (parent instanceof MReport)
				return new CreateChartCommand(parent, (MGraphicElement) child,
						location, newIndex);

			if (parent instanceof IGroupElement) {
				return new CreateChartCommand(parent, (MGraphicElement) child,
						location, newIndex);
			}
		} else if (child instanceof MChartAxes) {
			if (parent instanceof MChart) {
				JRDesignChart dc = (JRDesignChart) parent.getValue();
				if (dc.getPlot() instanceof JRDesignMultiAxisPlot)
					return new CreateChartAxesCommand((MChart) parent,
							(MChartAxes) child, newIndex);
			}
		}
		if (child instanceof MCategorySeries
				&& parent instanceof MChartCategoryDataset)
			return new CreateCategorySeriesCommand((MChartDataset) parent,
					(MCategorySeries) child, newIndex);
		if (child instanceof MGanttSeries
				&& parent instanceof MChartGanttDataset)
			return new CreateGanttSeriesCommand((MChartDataset) parent,
					(MGanttSeries) child, newIndex);
		if (child instanceof MPieSeries && parent instanceof MChartPieDataset)
			return new CreatePieSeriesCommand((MChartDataset) parent,
					(MPieSeries) child, newIndex);
		if (child instanceof MTimePeriodSeries
				&& parent instanceof MChartTimePeriodDataset)
			return new CreateTimePeriodSeriesCommand((MChartDataset) parent,
					(MTimePeriodSeries) child, newIndex);
		if (child instanceof MTimeSeries
				&& parent instanceof MChartTimeSeriesDataset)
			return new CreateTimeSeriesCommand((MChartDataset) parent,
					(MTimeSeries) child, newIndex);
		if (child instanceof MXYSeries && parent instanceof MChartXYDataset)
			return new CreateXYSeriesCommand((MChartDataset) parent,
					(MXYSeries) child, newIndex);
		if (child instanceof MXYZSeries && parent instanceof MChartXYZDataset)
			return new CreateXYZSeriesCommand((MChartDataset) parent,
					(MXYZSeries) child, newIndex);

		return null;
	}

	public Command getDeleteCommand(ANode parent, ANode child) {
		if (child instanceof MChartAxes)
			return new DeleteChartAxesCommand((MChart) child.getParent(),
					(MChartAxes) child);

		if (child instanceof MCategorySeries && parent instanceof MChartDataset)
			return new DeleteCategorySeriesCommand((MChartDataset) parent,
					(MCategorySeries) child);
		if (child instanceof MGanttSeries && parent instanceof MChartDataset)
			return new DeleteGanttSeriesCommand((MChartDataset) parent,
					(MGanttSeries) child);
		if (child instanceof MPieSeries && parent instanceof MChartDataset)
			return new DeletePieSeriesCommand((MChartDataset) parent,
					(MPieSeries) child);
		if (child instanceof MTimePeriodSeries
				&& parent instanceof MChartDataset)
			return new DeleteTimePeriodSeriesCommand((MChartDataset) parent,
					(MTimePeriodSeries) child);
		if (child instanceof MTimeSeries && parent instanceof MChartDataset)
			return new DeleteTimeSeriesCommand((MChartDataset) parent,
					(MTimeSeries) child);
		if (child instanceof MXYSeries && parent instanceof MChartDataset)
			return new DeleteXYSeriesCommand((MChartDataset) parent,
					(MXYSeries) child);
		if (child instanceof MXYZSeries && parent instanceof MChartDataset)
			return new DeleteXYZSeriesCommand((MChartDataset) parent,
					(MXYZSeries) child);

		return null;
	}

	public Command getReorderCommand(ANode parent, ANode child, int newIndex) {
		if (child instanceof MChartAxes && parent instanceof MChart) {
			return new ReorderChartAxesCommand((MChartAxes) child, parent,
					newIndex);
		}
		if (child instanceof MCategorySeries && parent instanceof MChartDataset)
			return new ReorderCategorySeriesCommand((MCategorySeries) child,
					(MChartDataset) parent, newIndex);
		if (child instanceof MGanttSeries && parent instanceof MChartDataset)
			return new ReorderGanttSeriesCommand((MGanttSeries) child,
					(MChartDataset) parent, newIndex);
		if (child instanceof MPieSeries && parent instanceof MChartDataset)
			return new ReorderPieSeriesCommand((MPieSeries) child,
					(MChartDataset) parent, newIndex);
		if (child instanceof MTimePeriodSeries
				&& parent instanceof MChartDataset)
			return new ReorderTimePeriodSeriesCommand(
					(MTimePeriodSeries) child, (MChartDataset) parent, newIndex);
		if (child instanceof MTimeSeries && parent instanceof MChartDataset)
			return new ReorderTimeSeriesCommand((MTimeSeries) child,
					(MChartDataset) parent, newIndex);
		if (child instanceof MXYSeries && parent instanceof MChartDataset)
			return new ReorderXYSeriesCommand((MXYSeries) child,
					(MChartDataset) parent, newIndex);
		if (child instanceof MXYZSeries && parent instanceof MChartDataset)
			return new ReorderXYZSeriesCommand((MXYZSeries) child,
					(MChartDataset) parent, newIndex);

		return null;
	}

	public List<Action> getActions(WorkbenchPart part) {
		List<Action> lst = new ArrayList<Action>();
		lst.add(new CreateChartAxisAction(part));
		lst.add(new CreateCategorySeriesAction(part));
		lst.add(new CreateGanttAction(part));
		lst.add(new CreatePieAction(part));
		lst.add(new CreateTimePeriodAction(part));
		lst.add(new CreateTimeAction(part));
		lst.add(new CreateXYAction(part));
		lst.add(new CreateXYZAction(part));
		lst.add(new ChartWizardAction(part));
		return lst;
	}

	public List<String> getActionsID() {
		List<String> lst = new ArrayList<String>();
		lst.add(CreateChartAxisAction.ID);
		lst.add(CreateCategorySeriesAction.ID);
		lst.add(CreateGanttAction.ID);
		lst.add(CreatePieAction.ID);
		lst.add(CreateTimePeriodAction.ID);
		lst.add(CreateTimeAction.ID);
		lst.add(CreateXYAction.ID);
		lst.add(CreateXYZAction.ID);
		lst.add(ChartWizardAction.ID);
		return lst;
	}

	public EditPart createEditPart(EditPart context, Object model) {
		if (model instanceof MChart)
			return new ChartEditPart();
		return null;
	}

	public Command getOrphanCommand(ANode parent, ANode child) {
		return null;
	}

	public AbstractVisualEditor getEditor(Object node,
			JasperReportsConfiguration jrContext) {
		return null;
	}

	public ExpressionContext getElementExpressionContext(Object jrObject) {
		// FIXME - Implement this method.
		return null;
	}
}
