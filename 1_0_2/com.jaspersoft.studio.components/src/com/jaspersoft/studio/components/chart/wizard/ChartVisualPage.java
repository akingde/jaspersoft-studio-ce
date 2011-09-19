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
package com.jaspersoft.studio.components.chart.wizard;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignAreaPlot;
import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;
import net.sf.jasperreports.charts.design.JRDesignBarPlot;
import net.sf.jasperreports.charts.design.JRDesignBubblePlot;
import net.sf.jasperreports.charts.design.JRDesignCandlestickPlot;
import net.sf.jasperreports.charts.design.JRDesignHighLowPlot;
import net.sf.jasperreports.charts.design.JRDesignLinePlot;
import net.sf.jasperreports.charts.design.JRDesignMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;
import net.sf.jasperreports.charts.design.JRDesignPiePlot;
import net.sf.jasperreports.charts.design.JRDesignScatterPlot;
import net.sf.jasperreports.charts.design.JRDesignThermometerPlot;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesPlot;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.APlotComponent;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIAreaPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIBar3DPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIBarPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIBubblePlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UICandlestickPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIHighLowPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UILinePlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIMeterPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIMultiAxisPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIPie3DPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIPiePlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIScatterPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UIThermometerPlot;
import com.jaspersoft.studio.components.chart.wizard.fragments.plot.UITimeSeriesPlot;
import com.jaspersoft.studio.utils.SelectionHelper;

public class ChartVisualPage extends WizardPage {
	private JRChartPlot jrPlot;
	private JasperDesign jrDesign;
	private JRDesignChart jrChart;
	private Map<Class<? extends JRChartPlot>, APlotComponent> map = new HashMap<Class<? extends JRChartPlot>, APlotComponent>();
	private StackLayout stacklayout;
	private Composite sComposite;
	private DrawVisitor dv;
	private SimpleFileResolver sfResolver;

	protected ChartVisualPage(MChart chart, JasperDesign jrDesign) {
		super("visualchartname");
		setTitle("Visual Chart Properties");
		setDescription("Configure how your chart looks like");
		sfResolver = SelectionHelper.getFileResolver();
		this.jrDesign = jrDesign;
		this.jrChart = (JRDesignChart) chart.getValue();
		dv = new DrawVisitor(jrDesign, null);
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));

		sComposite = new Composite(composite, SWT.NONE);
		setControl(sComposite);
		stacklayout = new StackLayout();
		sComposite.setLayout(stacklayout);
		sComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		map.put(JRDesignAreaPlot.class, new UIAreaPlot(sComposite));
		map.put(JRDesignBar3DPlot.class, new UIBar3DPlot(sComposite));
		map.put(JRDesignBarPlot.class, new UIBarPlot(sComposite));
		map.put(JRDesignBubblePlot.class, new UIBubblePlot(sComposite));
		map.put(JRDesignCandlestickPlot.class,
				new UICandlestickPlot(sComposite));
		map.put(JRDesignHighLowPlot.class, new UIHighLowPlot(sComposite));
		map.put(JRDesignLinePlot.class, new UILinePlot(sComposite));
		map.put(JRDesignMeterPlot.class, new UIMeterPlot(sComposite));
		map.put(JRDesignMultiAxisPlot.class, new UIMultiAxisPlot(sComposite));
		map.put(JRDesignPie3DPlot.class, new UIPie3DPlot(sComposite));
		map.put(JRDesignPiePlot.class, new UIPiePlot(sComposite));
		map.put(JRDesignScatterPlot.class, new UIScatterPlot(sComposite));
		map.put(JRDesignThermometerPlot.class,
				new UIThermometerPlot(sComposite));
		map.put(JRDesignTimeSeriesPlot.class, new UITimeSeriesPlot(sComposite));

		stacklayout.topControl = map.get(JRDesignAreaPlot.class).getControl();

	}

	public void updateData() {
		setDataset(jrDesign, jrChart);
	}

	@Override
	public void setVisible(boolean visible) {
		if (visible)
			updateData();
		super.setVisible(visible);
	}

	private void bindData() {

	}

	public void setDataset(JasperDesign jrDesign, JRDesignChart jrChart) {
		this.jrPlot = jrChart.getPlot();
		fillData();
	}

	private void fillData() {
		APlotComponent c = map.get(jrPlot.getClass());
		if (c != null) {
			c.setData(dv, jrChart, sfResolver);
			stacklayout.topControl = c.getControl();
		} else {
			// a label, with not implemented ...
		}
		sComposite.layout(true);
	}
}
