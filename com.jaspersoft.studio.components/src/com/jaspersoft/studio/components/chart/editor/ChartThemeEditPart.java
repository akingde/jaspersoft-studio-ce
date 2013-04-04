/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.editor;

import net.sf.jasperreports.charts.design.JRDesignAreaPlot;
import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;
import net.sf.jasperreports.charts.design.JRDesignBarPlot;
import net.sf.jasperreports.charts.design.JRDesignBubblePlot;
import net.sf.jasperreports.charts.design.JRDesignCandlestickPlot;
import net.sf.jasperreports.charts.design.JRDesignHighLowPlot;
import net.sf.jasperreports.charts.design.JRDesignLinePlot;
import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.charts.design.JRDesignScatterPlot;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesPlot;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.core.runtime.Platform;
import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.GridData;
import org.eclipse.draw2d.GridLayout;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LineBorder;
import org.eclipse.draw2d.RectangleFigure;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.chart.figure.ChartFigure;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.editor.gef.figures.borders.CornerBorder;
import com.jaspersoft.studio.editor.gef.figures.borders.ElementLineBorder;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;

public class ChartThemeEditPart extends FigureEditPart {

	private GridData gd;
	private ChartFigure cf;

	@Override
	protected IFigure createFigure() {
		RectangleFigure rf = new RectangleFigure();
		rf.setBorder(new LineBorder(ColorConstants.white));
		GridLayout lm = new GridLayout(2, false);
		lm.marginHeight = 20;
		lm.marginWidth = 20;
		lm.horizontalSpacing = 20;
		rf.setLayoutManager(lm);

		JRDesignChart jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_AREA);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Area Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Areas\""));
		((JRDesignAreaPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));
		((JRDesignAreaPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_BAR);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Bar Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Bars\""));
		((JRDesignBarPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));
		((JRDesignBarPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_BAR3D);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Bar 3D Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying 3D Bars\""));
		((JRDesignBar3DPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));
		((JRDesignBar3DPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_BUBBLE);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Bubble Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Bubbles\""));
		((JRDesignBubblePlot) jdc.getPlot()).setYAxisLabelExpression(new JRDesignExpression("\"Amount\""));
		((JRDesignBubblePlot) jdc.getPlot()).setXAxisLabelExpression(new JRDesignExpression("\"Probability\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_CANDLESTICK);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Candlestick Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Candlesticks\""));
		((JRDesignCandlestickPlot) jdc.getPlot()).setTimeAxisLabelExpression(new JRDesignExpression("\"Time\""));
		((JRDesignCandlestickPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_HIGHLOW);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"High Low Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying High Low Values\""));
		((JRDesignHighLowPlot) jdc.getPlot()).setTimeAxisLabelExpression(new JRDesignExpression("\"Time\""));
		((JRDesignHighLowPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_LINE);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Line Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Lines\""));
		((JRDesignLinePlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));
		((JRDesignLinePlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_METER);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Meter Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying a Meter\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_PIE);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Pie Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying a Pie\""));
		((JRDesignPiePlot) jdc.getPlot()).setCircular(Boolean.TRUE);

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_PIE3D);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Pie 3D Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying a Pie 3D\""));
		((JRDesignPie3DPlot) jdc.getPlot()).setCircular(Boolean.TRUE);

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_SCATTER);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Scatter Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Scattered Dots\""));
		((JRDesignScatterPlot) jdc.getPlot()).setYAxisLabelExpression(new JRDesignExpression("\"Amount\""));
		((JRDesignScatterPlot) jdc.getPlot()).setXAxisLabelExpression(new JRDesignExpression("\"Probability\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_STACKEDAREA);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Stacked Area Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Stacked Areas\""));
		((JRDesignAreaPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));
		((JRDesignAreaPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_STACKEDBAR);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Stacked Bar Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Stacked Bars\""));
		((JRDesignBarPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));
		((JRDesignBarPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_STACKEDBAR3D);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Stacked Bar 3D Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Stacked Bars 3D\""));
		((JRDesignBar3DPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));
		((JRDesignBar3DPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_THERMOMETER);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Thermometer Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Thermometer\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_TIMESERIES);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"Time Series Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying Time Series\""));
		((JRDesignTimeSeriesPlot) jdc.getPlot()).setTimeAxisLabelExpression(new JRDesignExpression("\"Time\""));
		((JRDesignTimeSeriesPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_XYAREA);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"XY Area Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying XY Area\""));
		((JRDesignAreaPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Name\""));
		((JRDesignAreaPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_XYBAR);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"XY Bar Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying XY Bars\""));
		((JRDesignBarPlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Probability\""));
		((JRDesignBarPlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		jdc = MChart.createJRElement(getModel().getJasperDesign(), JRDesignChart.CHART_TYPE_XYLINE);
		setupChartSize(jdc);
		jdc.setTitleExpression(new JRDesignExpression("\"XY Line Chart\""));
		jdc.setSubtitleExpression(new JRDesignExpression("\"Chart Displaying XY Lines\""));
		((JRDesignLinePlot) jdc.getPlot()).setCategoryAxisLabelExpression(new JRDesignExpression("\"Probability\""));
		((JRDesignLinePlot) jdc.getPlot()).setValueAxisLabelExpression(new JRDesignExpression("\"Amount\""));

		addChart(rf, lm, jdc);

		rf.setSize(2 * jdc.getWidth() + 50, (jdc.getHeight() + 20) * rf.getChildren().size() / 2 + 100);

		setPrefsBorder(rf);
		return rf;
	}

	protected void setupChartSize(JRDesignChart jdc) {
		jdc.setX(20);
		jdc.setY(20);
		jdc.setWidth(555);
		jdc.setHeight(300);
		jdc.setTheme("");
	}

	protected void addChart(RectangleFigure rf, GridLayout lm, JRDesignChart jdc) {
		cf = new ChartFigure();
		cf.setJRElement(jdc, getDrawVisitor());

		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.heightHint = jdc.getHeight();
		gd.widthHint = jdc.getWidth();
		lm.setConstraint(cf, gd);
		rf.add(cf);
	}

	public void setPrefsBorder(IFigure rect) {
		String pref = Platform.getPreferencesService().getString(JaspersoftStudioPlugin.getUniqueIdentifier(), DesignerPreferencePage.P_ELEMENT_DESIGN_BORDER_STYLE, "rectangle", null); //$NON-NLS-1$

		if (pref.equals("rectangle")) //$NON-NLS-1$
			cf.setBorder(new ElementLineBorder(ColorConstants.black));
		else
			cf.setBorder(new CornerBorder(ColorConstants.black, 5));
	}

	@Override
	protected void setupFigure(IFigure rect) {
	}

	@Override
	protected void createEditPolicies() {
		// installEditPolicy(EditPolicy.COMPONENT_ROLE, new ElementEditPolicy());
	}

}
