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

import java.util.List;

import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.design.JRDesignMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDisplay;
import net.sf.jasperreports.charts.type.MeterShapeEnum;
import net.sf.jasperreports.charts.util.JRMeterInterval;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.plot.MChartPlot;
import com.jaspersoft.studio.components.chart.property.widget.SPMeterInterval;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.property.section.widgets.SPFont;
import com.jaspersoft.studio.property.section.widgets.SPNumber;
import com.jaspersoft.studio.property.section.widgets.SPRCombo;
import com.jaspersoft.studio.property.section.widgets.SPText;
import com.jaspersoft.studio.utils.EnumHelper;

public class MeterPlot extends APlot {

	public MeterPlot(Composite parent, AbstractSection section) {
		super(parent, section);
	}

	@Override
	protected void createComponent(Composite parent, AbstractSection section) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.MMeterPlot_units, SWT.RIGHT);
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		units = new SPText(composite, section,
				JRDesignMeterPlot.PROPERTY_UNITS,
				Messages.MMeterPlot_units_description);

		section.getWidgetFactory().createCLabel(composite,
				Messages.MMeterPlot_shape);

		shape = new SPRCombo(composite, section,
				JRDesignMeterPlot.PROPERTY_SHAPE,
				Messages.MMeterPlot_shape_description, EnumHelper.getEnumNames(
						MeterShapeEnum.values(), NullEnum.NOTNULL));

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Tick",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		tickLblFont = new SPFont(composite, section,
				JRDesignMeterPlot.PROPERTY_TICK_LABEL_FONT, false);

		section.getWidgetFactory().createCLabel(composite, "Color", SWT.RIGHT);

		tickLblColor = new SPColor(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_TICK_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_tick_color_description);

		section.getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_tick_interval,
						SWT.RIGHT);

		tickInterval = new SPNumber(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_TICK_INTERVAL,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_tick_interval_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.MMeterPlot_needle_color, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		needleLblColor = new SPColor(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_needle_color_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite,
				Messages.MMeterPlot_meter_background_color, SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		meterBgColor = new SPColor(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_meter_background_color_description);

		section.getWidgetFactory().createCLabel(composite,
				Messages.MMeterPlot_meter_angle, SWT.RIGHT);

		meterAngle = new SPNumber(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_METER_ANGLE,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_meter_angle_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Value",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		valFont = new SPFont(composite, section,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_FONT, false);

		section.getWidgetFactory().createCLabel(composite, "Color", SWT.RIGHT);

		valColor = new SPColor(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_value_color_description);

		section.getWidgetFactory().createCLabel(composite, "Mask", SWT.RIGHT);

		valMask = new SPText(
				composite,
				section,
				JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." //$NON-NLS-1$
						+ JRDesignValueDisplay.PROPERTY_MASK,
				com.jaspersoft.studio.components.chart.messages.Messages.MMeterPlot_value_mask_description);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "Data Range",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		section.getWidgetFactory().createCLabel(composite, "Low", SWT.RIGHT);

		Composite cmp = new Composite(composite, SWT.NONE);
		GridLayout gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		dRangeLow = new SPExpression(cmp, section,
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "."
						+ JRDesignDataRange.PROPERTY_LOW_EXPRESSION);

		section.getWidgetFactory().createCLabel(composite, "High", SWT.RIGHT);

		cmp = new Composite(composite, SWT.NONE);
		gl = new GridLayout(3, false);
		gl.marginTop = 0;
		gl.marginHeight = 0;
		gl.marginWidth = 0;
		gl.marginLeft = 0;
		cmp.setLayout(gl);
		cmp.setBackground(parent.getBackground());
		dRangeHigh = new SPExpression(cmp, section,
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "."
						+ JRDesignDataRange.PROPERTY_HIGH_EXPRESSION);

		composite = AbstractSection.createNewRow(parent);

		lbl = section.getWidgetFactory().createCLabel(composite, "", SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		mInterval = new SPMeterInterval(composite, section,
				JRDesignMeterPlot.PROPERTY_INTERVALS, "Meter Intervals");
	}

	private SPText units;
	private SPFont tickLblFont;
	private SPColor tickLblColor;
	private SPNumber tickInterval;

	private SPColor needleLblColor;
	private SPColor meterBgColor;
	private SPNumber meterAngle;
	private SPRCombo shape;

	private SPFont valFont;
	private SPColor valColor;
	private SPText valMask;

	private SPExpression dRangeLow;
	private SPExpression dRangeHigh;
	private SPMeterInterval mInterval;

	@Override
	public void setData(MChartPlot mplot) {
		mInterval.setData((List<JRMeterInterval>) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_INTERVALS));

		units.setData((String) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_UNITS));

		shape.setData((Integer) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_SHAPE));

		tickLblFont.setData(mplot, (MFont) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_TICK_LABEL_FONT));
		tickLblColor.setData((RGB) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_TICK_COLOR));
		tickInterval.setData((Double) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_TICK_INTERVAL));

		needleLblColor.setData((RGB) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR));

		meterBgColor
				.setData((RGB) mplot
						.getPropertyValue(JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR));

		meterAngle.setData((Integer) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_METER_ANGLE));

		valFont.setData(mplot, (MFont) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY
						+ "." + JRDesignValueDisplay.PROPERTY_FONT)); //$NON-NLS-1$
		valColor.setData((RGB) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY
						+ "." + JRDesignValueDisplay.PROPERTY_COLOR)); //$NON-NLS-1$
		valMask.setData((String) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY
						+ "." + JRDesignValueDisplay.PROPERTY_MASK)); //$NON-NLS-1$

		dRangeLow.setData((JRDesignExpression) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_DATA_RANGE
						+ "." + JRDesignDataRange.PROPERTY_LOW_EXPRESSION)); //$NON-NLS-1$

		dRangeHigh.setData((JRDesignExpression) mplot
				.getPropertyValue(JRDesignMeterPlot.PROPERTY_DATA_RANGE
						+ "." + JRDesignDataRange.PROPERTY_HIGH_EXPRESSION)); //$NON-NLS-1$

	}

}
