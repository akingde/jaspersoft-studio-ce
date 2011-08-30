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
package com.jaspersoft.studio.components.chart.wizard.fragments.data;

import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports.charts.design.JRDesignHighLowDataset;
import net.sf.jasperreports.charts.design.JRDesignPieDataset;
import net.sf.jasperreports.charts.design.JRDesignTimePeriodDataset;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesDataset;
import net.sf.jasperreports.charts.design.JRDesignValueDataset;
import net.sf.jasperreports.charts.design.JRDesignXyDataset;
import net.sf.jasperreports.charts.design.JRDesignXyzDataset;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.draw.DrawVisitor;
import net.sf.jasperreports.engine.util.SimpleFileResolver;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.utils.SelectionHelper;

public class DatasetSeriesWidget {
	private JRDesignElementDataset eDataset;
	private JasperDesign jrDesign;
	private JRDesignChart jrChart;
	private Map<Class<? extends JRDesignElementDataset>, ADSComponent> map = new HashMap<Class<? extends JRDesignElementDataset>, ADSComponent>();
	private StackLayout stacklayout;
	private Composite sComposite;
	private DrawVisitor dv;
	private SimpleFileResolver sfResolver;

	public DatasetSeriesWidget(Composite parent) {
		createDataset(parent);
		bindData();
		sfResolver = SelectionHelper.getFileResolver();
	}

	public void createDataset(Composite composite) {
		sComposite = new Composite(composite, SWT.NONE);
		stacklayout = new StackLayout();
		sComposite.setLayout(stacklayout);
		sComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		map.put(JRDesignCategoryDataset.class, new DSCategory(sComposite));
		map.put(JRDesignGanttDataset.class, new DSGantt(sComposite));
		map.put(JRDesignHighLowDataset.class, new DSHighLow(sComposite));
		map.put(JRDesignPieDataset.class, new DSPie(sComposite));
		map.put(JRDesignTimePeriodDataset.class, new DSTimePeriod(sComposite));
		map.put(JRDesignTimeSeriesDataset.class, new DSTimeSeries(sComposite));
		map.put(JRDesignValueDataset.class, new DSValue(sComposite));
		map.put(JRDesignXyDataset.class, new DSXy(sComposite));
		map.put(JRDesignXyzDataset.class, new DSXyz(sComposite));
		// here we can add other datasources ...

		stacklayout.topControl = map.get(JRDesignCategoryDataset.class)
				.getControl();
	}

	private void bindData() {

	}

	public void setDataset(JRDesignElementDataset eDataset,
			JasperDesign jrDesign, JRDesignChart jrChart) {
		this.eDataset = eDataset;
		if (this.jrDesign != jrDesign) {
			this.jrDesign = jrDesign;
			dv = new DrawVisitor(jrDesign, null);
		}
		this.jrChart = jrChart;
		fillData();
	}

	private void fillData() {
		ADSComponent c = map.get(eDataset.getClass());
		if (c != null) {
			c.setData(dv, jrChart, sfResolver);
			stacklayout.topControl = c.getControl();
		} else {
			// a label, with not implemented ...
		}
		sComposite.layout(true);
	}

}
