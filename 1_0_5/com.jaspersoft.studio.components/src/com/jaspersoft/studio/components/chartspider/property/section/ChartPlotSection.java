/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.components.chartspider.property.section;

import net.sf.jasperreports.components.spiderchart.StandardSpiderPlot;
import net.sf.jasperreports.components.spiderchart.type.SpiderRotationEnum;
import net.sf.jasperreports.components.spiderchart.type.TableOrderEnum;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SP3Boolean;
import com.jaspersoft.studio.property.section.widgets.SPColor;
import com.jaspersoft.studio.property.section.widgets.SPExpression;
import com.jaspersoft.studio.property.section.widgets.SPFont;
import com.jaspersoft.studio.property.section.widgets.SPNumber;
import com.jaspersoft.studio.property.section.widgets.SPRCombo;
import com.jaspersoft.studio.utils.EnumHelper;

/*
 * The location section on the location tab.
 * 
 * @author Chicu Veaceslav
 */
public class ChartPlotSection extends AbstractSection {
	private SPColor backButton;
	private Composite pcomposite;
	private SPNumber bgAlpha;
	private SPNumber fgAlpha;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		parent = new Composite(parent, SWT.NONE);
		parent.setLayout(new RowLayout(SWT.VERTICAL));
		parent.setBackground(parent.getDisplay()
				.getSystemColor(SWT.COLOR_WHITE));
		this.pcomposite = parent;

		Composite composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		CLabel lbl = getWidgetFactory().createCLabel(composite,
				Messages.common_backcolor + ":", SWT.RIGHT); //$NON-NLS-1$
		RowData rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		backButton = new SPColor(
				composite,
				this,
				StandardSpiderPlot.PROPERTY_BACKCOLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_backcolor_description);

		getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_background_alpha_percent);

		bgAlpha = new SPNumber(
				composite,
				this,
				StandardSpiderPlot.PROPERTY_BACKGROUND_ALPHA,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_background_alpha_percent_description);

		getWidgetFactory()
				.createCLabel(
						composite,
						com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_foreground_alpha_percent);

		fgAlpha = new SPNumber(
				composite,
				this,
				StandardSpiderPlot.PROPERTY_FOREGROUND_ALPHA,
				com.jaspersoft.studio.components.chart.messages.Messages.MChartPlot_foreground_alpha_percent_description);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "Axis Line Color",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		axLineColor = new SPColor(
				composite,
				this,
				StandardSpiderPlot.PROPERTY_AXIS_LINE_COLOR,
				com.jaspersoft.studio.components.chart.messages.Messages.MAreaPlot_category_axis_line_color_description);

		getWidgetFactory().createCLabel(composite, "Line Width", SWT.RIGHT);

		axLineWidth = new SPNumber(composite, this,
				StandardSpiderPlot.PROPERTY_AXIS_LINE_WIDTH, "Axis line width");

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "Label Color",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		labelColor = new SPColor(composite, this,
				StandardSpiderPlot.PROPERTY_LABEL_COLOR, "Label color");

		labelFont = new SPFont(composite, this,
				StandardSpiderPlot.PROPERTY_LABEL_FONT, false);

		getWidgetFactory().createCLabel(composite, "Gap", SWT.RIGHT);

		labelGap = new SPNumber(composite, this,
				StandardSpiderPlot.PROPERTY_LABEL_GAP, "Label Gap");

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory()
				.createCLabel(composite, "Max Value", SWT.RIGHT);
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
		maxValExpr = new SPExpression(cmp, this,
				StandardSpiderPlot.PROPERTY_MAX_VALUE_EXPRESSION);

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "Web Filled",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		webFilled = new SP3Boolean(composite, this,
				StandardSpiderPlot.PROPERTY_WEB_FILLED, "Web Filled");

		getWidgetFactory().createCLabel(composite, "Table Order", SWT.RIGHT);

		tableOrder = new SPRCombo(composite, this,
				StandardSpiderPlot.PROPERTY_TABLE_ORDER, "Table Order",
				EnumHelper.getEnumNames(TableOrderEnum.values(), NullEnum.NULL));

		composite = new Composite(parent, SWT.NONE);
		composite.setBackground(parent.getBackground());
		composite.setLayout(new RowLayout());

		lbl = getWidgetFactory().createCLabel(composite, "Start Angle",
				SWT.RIGHT);
		rd = new RowData();
		rd.width = 101;
		lbl.setLayoutData(rd);

		startAngle = new SPNumber(composite, this,
				StandardSpiderPlot.PROPERTY_START_ANGLE, "Start Angle");

		getWidgetFactory().createCLabel(composite, "Head Percent", SWT.RIGHT);

		headPercent = new SPNumber(composite, this,
				StandardSpiderPlot.PROPERTY_HEAD_PERCENT, "Head Percent");

		getWidgetFactory().createCLabel(composite, "Interior Gap", SWT.RIGHT);

		interiorGap = new SPNumber(composite, this,
				StandardSpiderPlot.PROPERTY_INTERIOR_GAP, "Interior Gap");

		getWidgetFactory().createCLabel(composite, "Rotation", SWT.RIGHT);

		rotation = new SPRCombo(composite, this,
				StandardSpiderPlot.PROPERTY_ROTATION, "Rotation",
				EnumHelper.getEnumNames(SpiderRotationEnum.values(),
						NullEnum.NULL));
	}

	private SPColor axLineColor;
	private SPNumber axLineWidth;

	private SPColor labelColor;
	private SPNumber labelGap;
	private SPFont labelFont;
	private SPExpression maxValExpr;
	private SP3Boolean webFilled;
	private SPRCombo tableOrder;
	private SPRCombo rotation;
	private SPNumber startAngle;
	private SPNumber headPercent;
	private SPNumber interiorGap;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
	 */
	public void refresh() {
		isRefreshing = true;
		APropertyNode element = getElement();
		if (element != null) {
			rotation.setData((Integer) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_ROTATION));
			startAngle.setData((Double) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_START_ANGLE));
			headPercent
					.setData((Double) element
							.getPropertyValue(StandardSpiderPlot.PROPERTY_HEAD_PERCENT));
			interiorGap
					.setData((Double) element
							.getPropertyValue(StandardSpiderPlot.PROPERTY_INTERIOR_GAP));

			webFilled.setData((Boolean) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_WEB_FILLED));
			tableOrder.setData((Integer) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_TABLE_ORDER));

			maxValExpr
					.setData((JRDesignExpression) element
							.getPropertyValue(StandardSpiderPlot.PROPERTY_MAX_VALUE_EXPRESSION));

			labelColor.setData((RGB) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_LABEL_COLOR));
			labelGap.setData((Double) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_LABEL_GAP));
			labelFont.setData(element, (MFont) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_LABEL_FONT));

			axLineColor
					.setData((RGB) element
							.getPropertyValue(StandardSpiderPlot.PROPERTY_AXIS_LINE_COLOR));
			axLineWidth
					.setData((Float) element
							.getPropertyValue(StandardSpiderPlot.PROPERTY_AXIS_LINE_WIDTH));

			backButton.setData((RGB) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_BACKCOLOR));

			bgAlpha.setData((Float) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_BACKGROUND_ALPHA));

			fgAlpha.setData((Float) element
					.getPropertyValue(StandardSpiderPlot.PROPERTY_FOREGROUND_ALPHA));
		}
		isRefreshing = false;
	}

	@Override
	public boolean isDisposed() {
		return pcomposite.isDisposed();
	}
}
