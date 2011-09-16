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
package com.jaspersoft.studio.components.chart.property.section.plot;

import net.sf.jasperreports.charts.design.JRDesignPie3DPlot;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.MChartItemLabel;
import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.components.chart.property.widget.SPChartItemLabel;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SP3Boolean;
import com.jaspersoft.studio.property.section.widgets.SPNumber;
import com.jaspersoft.studio.property.section.widgets.SPText;

public class Pie3dPlot extends APlot {

	public Pie3dPlot(Composite parent, AbstractSection section) {
		super(parent, section);
	}

	private SP3Boolean showLabels;
	private SP3Boolean circular;
	private SPText labelFormat;
	private SPText labelLegendFormat;
	private SPNumber depthFactor;

	@Override
	protected void createComponent(Composite parent, AbstractSection section) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.common_show_labels, SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		showLabels = new SP3Boolean(composite, section,
				JRDesignPie3DPlot.PROPERTY_SHOW_LABELS,
				Messages.common_show_labels);

		section.getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_circular_description,
						SWT.RIGHT);

		circular = new SP3Boolean(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_CIRCULAR,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_circular_description);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.common_label_format, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		labelFormat = new SPText(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_label_format_description);

		section.getWidgetFactory().createCLabel(composite,
				Messages.common_legend_label_format, SWT.RIGHT);

		labelLegendFormat = new SPText(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_legend_label_format_description);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.MPie3DPlot_depth_factor, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		depthFactor = new SPNumber(
				composite,
				section,
				JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MPie3DPlot_depth_factor_description);

		createItemLabel(parent, section);
	}

	private void createItemLabel(Composite parent, AbstractSection section) {
		Section sectioncmp = section.getWidgetFactory().createSection(
				parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		sectioncmp.setText("Item Label");

		parent = new Composite(sectioncmp, SWT.NONE);
		parent.setBackground(sectioncmp.getBackground());
		parent.setLayout(new RowLayout(SWT.VERTICAL));

		sectioncmp.setClient(parent);

		chItemLabel = new SPChartItemLabel(parent, section,
				JRDesignPie3DPlot.PROPERTY_ITEM_LABEL);

	}

	private SPChartItemLabel chItemLabel;

	@Override
	public void setData(MChartPlot mplot) {
		MChartItemLabel cil = (MChartItemLabel) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_ITEM_LABEL);
		chItemLabel.setData(cil);
		showLabels.setData((Boolean) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_SHOW_LABELS));
		circular.setData((Boolean) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_CIRCULAR));
		labelFormat.setData((String) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_LABEL_FORMAT));
		labelLegendFormat
				.setData((String) mplot
						.getPropertyValue(JRDesignPie3DPlot.PROPERTY_LEGEND_LABEL_FORMAT));
		depthFactor.setData((Double) mplot
				.getPropertyValue(JRDesignPie3DPlot.PROPERTY_DEPTH_FACTOR));
	}

}
