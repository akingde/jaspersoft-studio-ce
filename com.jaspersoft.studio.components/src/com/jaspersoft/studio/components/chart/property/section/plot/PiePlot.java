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

import net.sf.jasperreports.charts.design.JRDesignPiePlot;

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
import com.jaspersoft.studio.components.chart.property.widget.Btn3Boolean;
import com.jaspersoft.studio.components.chart.property.widget.BtnChartItemLabel;
import com.jaspersoft.studio.components.chart.property.widget.BtnText;
import com.jaspersoft.studio.property.section.AbstractSection;

public class PiePlot extends APlot {

	public PiePlot(Composite parent, AbstractSection section) {
		super(parent, section);
	}

	private Btn3Boolean showLabels;
	private Btn3Boolean circular;
	private BtnText labelFormat;
	private BtnText labelLegendFormat;

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

		showLabels = new Btn3Boolean(composite, section,
				JRDesignPiePlot.PROPERTY_SHOW_LABELS,
				Messages.common_show_labels);

		section.getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MPiePlot_circular_description,
						SWT.RIGHT);

		circular = new Btn3Boolean(
				composite,
				section,
				JRDesignPiePlot.PROPERTY_CIRCULAR,
				com.jaspersoft.studio.components.chart.messages.Messages.MPiePlot_circular_description);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.common_label_format, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		labelFormat = new BtnText(
				composite,
				section,
				JRDesignPiePlot.PROPERTY_LABEL_FORMAT,
				com.jaspersoft.studio.components.chart.messages.Messages.MPiePlot_label_format_description);

		section.getWidgetFactory().createCLabel(composite,
				Messages.common_legend_label_format, SWT.RIGHT);

		labelLegendFormat = new BtnText(
				composite,
				section,
				JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT,
				com.jaspersoft.studio.components.chart.messages.Messages.MPiePlot_legend_label_format_description);

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

		chItemLabel = new BtnChartItemLabel(parent, section,
				JRDesignPiePlot.PROPERTY_ITEM_LABEL);

	}

	private BtnChartItemLabel chItemLabel;

	@Override
	public void setData(MChartPlot mplot) {
		MChartItemLabel cil = (MChartItemLabel) mplot
				.getPropertyValue(JRDesignPiePlot.PROPERTY_ITEM_LABEL);
		chItemLabel.setData(cil);

		showLabels.setData((Boolean) mplot
				.getPropertyValue(JRDesignPiePlot.PROPERTY_SHOW_LABELS));
		circular.setData((Boolean) mplot
				.getPropertyValue(JRDesignPiePlot.PROPERTY_CIRCULAR));
		labelFormat.setData((String) mplot
				.getPropertyValue(JRDesignPiePlot.PROPERTY_LABEL_FORMAT));
		labelLegendFormat
				.setData((String) mplot
						.getPropertyValue(JRDesignPiePlot.PROPERTY_LEGEND_LABEL_FORMAT));
	}

}
