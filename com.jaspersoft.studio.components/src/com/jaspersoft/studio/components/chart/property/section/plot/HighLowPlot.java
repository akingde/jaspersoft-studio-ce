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

import net.sf.jasperreports.charts.design.JRDesignHighLowPlot;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.ExpandableComposite;
import org.eclipse.ui.forms.widgets.Section;

import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SP3Boolean;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.property.section.widgets.SPFont;
import com.jaspersoft.studio.property.section.widgets.SPText;

public class HighLowPlot extends APlot {

	public HighLowPlot(Composite parent, AbstractSection section) {
		super(parent, section);
	}

	private SP3Boolean showCloseTicks;
	private SP3Boolean showOpenTicks;

	@Override
	protected void createComponent(Composite parent, AbstractSection section) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		section.getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_show_close_ticks,
						SWT.RIGHT);

		showCloseTicks = new SP3Boolean(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_show_close_ticks_description);

		section.getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_show_open_ticks,
						SWT.RIGHT);

		showOpenTicks = new SP3Boolean(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_show_open_ticks_description);

		section.getWidgetFactory().createCLabel(composite, "Show Tick Marks",
				SWT.RIGHT);

		createCategory(parent, section);

		createValue(parent, section);
	}

	private void createCategory(Composite parent, AbstractSection section) {
		Section sectioncmp = section.getWidgetFactory().createSection(
				parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		sectioncmp.setText("Category axis");

		parent = new Composite(sectioncmp, SWT.NONE);
		parent.setBackground(sectioncmp.getBackground());
		parent.setLayout(new RowLayout(SWT.VERTICAL));

		sectioncmp.setClient(parent);

		Composite composite = AbstractSection.createNewRow(parent);

		CLabel lbl = section.getWidgetFactory().createCLabel(composite,
				"Axis Color", SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		caxLineColor = new SPColor(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_category_axis_line_color_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Label",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		Composite cmp = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		caxLblExpr = new SPExpression(cmp, section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION);

		section.getWidgetFactory().createCLabel(composite, "Font", SWT.RIGHT);

		caxLblFont = new SPFont(composite, section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT, false);

		section.getWidgetFactory().createCLabel(composite, "Color", SWT.RIGHT);

		caxLblColor = new SPColor(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_category_axis_label_color_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Tick Label",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		caxTickLblFont = new SPFont(composite, section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT, false);

		section.getWidgetFactory().createCLabel(composite, "Color", SWT.RIGHT);

		caxTickLblColor = new SPColor(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_category_axis_tick_label_color_description);

		section.getWidgetFactory().createCLabel(composite, "Mask", SWT.RIGHT);

		caxTickLblMask = new SPText(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_category_axis_tick_label_mask_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "", SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		section.getWidgetFactory().createCLabel(composite,
				"Show Vertical Tick Label", SWT.RIGHT);

		caxTickLblShow = new SP3Boolean(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_category_axis_vertical_tick_labels_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Domain",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		section.getWidgetFactory().createCLabel(composite, "Min value",
				SWT.RIGHT);

		cmp = new Composite(composite, SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		caxRangeMin = new SPExpression(cmp, section,
				JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION);

		section.getWidgetFactory().createCLabel(composite, "Max value",
				SWT.RIGHT);

		cmp = new Composite(composite, SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		caxRangeMax = new SPExpression(cmp, section,
				JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION);

	}

	private SPColor caxLineColor;
	private SPColor caxLblColor;
	private SPFont caxLblFont;
	private SPColor caxTickLblColor;
	private SPFont caxTickLblFont;
	private SP3Boolean caxTickLblShow;
	private SPText caxTickLblMask;
	private SPExpression caxLblExpr;
	private SPExpression caxRangeMin;
	private SPExpression caxRangeMax;

	private SPColor vaxLineColor;
	private SPColor vaxLblColor;
	private SPFont vaxLblFont;
	private SPColor vaxTickLblColor;
	private SPFont vaxTickLblFont;
	private SP3Boolean vaxTickLblShow;
	private SPText vaxTickLblMask;
	private SPExpression vaxLblExpr;
	private SPExpression vaxRangeMin;
	private SPExpression vaxRangeMax;

	private void createValue(Composite parent, AbstractSection section) {
		Section sectioncmp = section.getWidgetFactory().createSection(
				parent,
				ExpandableComposite.TITLE_BAR | ExpandableComposite.TWISTIE
						| ExpandableComposite.EXPANDED);
		sectioncmp.setText("Value axis");

		parent = new Composite(sectioncmp, SWT.NONE);
		parent.setBackground(sectioncmp.getBackground());
		parent.setLayout(new RowLayout(SWT.VERTICAL));

		sectioncmp.setClient(parent);

		Composite composite = AbstractSection.createNewRow(parent);

		CLabel lbl = section.getWidgetFactory().createCLabel(composite,
				"Axis Color", SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		vaxLineColor = new SPColor(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_value_axis_line_color_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Label",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		Composite cmp = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		vaxLblExpr = new SPExpression(cmp, section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION);

		section.getWidgetFactory().createCLabel(composite, "Font", SWT.RIGHT);

		vaxLblFont = new SPFont(composite, section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, false);

		section.getWidgetFactory().createCLabel(composite, "Color", SWT.RIGHT);

		vaxLblColor = new SPColor(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_value_axis_label_color_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Tick Label",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		vaxTickLblFont = new SPFont(composite, section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT, false);

		section.getWidgetFactory().createCLabel(composite, "Color", SWT.RIGHT);

		vaxTickLblColor = new SPColor(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_value_axis_tick_label_color_description);

		section.getWidgetFactory().createCLabel(composite, "Mask", SWT.RIGHT);

		vaxTickLblMask = new SPText(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_value_axis_tick_label_mask_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "", SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		section.getWidgetFactory().createCLabel(composite,
				"Show Vertical Tick Label", SWT.RIGHT);

		vaxTickLblShow = new SP3Boolean(
				composite,
				section,
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS,
				com.jaspersoft.studio.components.chart.messages.Messages.MHighLowPlot_value_axis_vertical_tick_labels_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Range",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		section.getWidgetFactory().createCLabel(composite, "Min value",
				SWT.RIGHT);

		cmp = new Composite(composite, SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		vaxRangeMin = new SPExpression(cmp, section,
				JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION);

		section.getWidgetFactory().createCLabel(composite, "Max value",
				SWT.RIGHT);

		cmp = new Composite(composite, SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		vaxRangeMax = new SPExpression(cmp, section,
				JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION);
	}

	@Override
	public void setData(MChartPlot mplot) {
		showCloseTicks
				.setData((Boolean) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS));

		showOpenTicks
				.setData((Boolean) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS));

		caxLineColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR));
		caxLblColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR));
		caxLblFont
				.setData(
						mplot,
						(MFont) mplot
								.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT));
		caxTickLblColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR));
		caxTickLblFont
				.setData(
						mplot,
						(MFont) mplot
								.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT));
		caxTickLblShow
				.setData((Boolean) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS));
		caxTickLblMask
				.setData((String) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK));
		caxLblExpr
				.setData((JRDesignExpression) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION));

		caxRangeMin
				.setData((JRDesignExpression) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION));
		caxRangeMax
				.setData((JRDesignExpression) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION));
		// ---------------------------------
		vaxLineColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR));
		vaxLblColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR));
		vaxLblFont
				.setData(
						mplot,
						(MFont) mplot
								.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT));
		vaxTickLblColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR));
		vaxTickLblFont
				.setData(
						mplot,
						(MFont) mplot
								.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT));
		vaxTickLblShow
				.setData((Boolean) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS));
		vaxTickLblMask
				.setData((String) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK));
		vaxLblExpr
				.setData((JRDesignExpression) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION));

		vaxRangeMin
				.setData((JRDesignExpression) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION));
		vaxRangeMax
				.setData((JRDesignExpression) mplot
						.getPropertyValue(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION));
	}
}
