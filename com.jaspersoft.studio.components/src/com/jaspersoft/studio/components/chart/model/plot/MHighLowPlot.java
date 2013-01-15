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
package com.jaspersoft.studio.components.chart.model.plot;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.JRHighLowPlot;
import net.sf.jasperreports.charts.design.JRDesignHighLowPlot;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;

public class MHighLowPlot extends MChartPlot {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MHighLowPlot(JRHighLowPlot value) {
		super(value);
	}

	@Override
	public String getDisplayText() {
		return Messages.MHighLowPlot_highlow_plot;
	}

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ColorPropertyDescriptor catAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR,
				Messages.common_category_axis_label_color, NullEnum.NULL);
		catAxisLabelColorD
				.setDescription(Messages.MHighLowPlot_category_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION,
				Messages.common_category_axis_label_expression);
		catAxisLabelExprD
				.setDescription(Messages.MHighLowPlot_category_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT,
				Messages.common_category_axis_label_font);
		catAxisLabelFontD
				.setDescription(Messages.MHighLowPlot_category_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR,
				Messages.common_category_axis_tick_label_color, NullEnum.NULL);
		catAxisTickLabelColorD
				.setDescription(Messages.MHighLowPlot_category_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT,
				Messages.common_category_axis_tick_label_font);
		catAxisTickLabelFontD
				.setDescription(Messages.MHighLowPlot_category_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR,
				Messages.common_category_axis_line_color, NullEnum.NULL);
		catAxisLineColorD
				.setDescription(Messages.MHighLowPlot_category_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR,
				Messages.common_value_axis_label_color, NullEnum.NULL);
		valAxisLabelColorD
				.setDescription(Messages.MHighLowPlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION,
				Messages.common_category_value_axis_label_expression);
		valAxisLabelExprD
				.setDescription(Messages.MHighLowPlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT,
				Messages.common_value_axis_label_font);
		valAxisLabelFontD
				.setDescription(Messages.MHighLowPlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR,
				Messages.common_value_axis_tick_label_color, NullEnum.NULL);
		valAxisTickLabelColorD
				.setDescription(Messages.MHighLowPlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT,
				Messages.common_value_axis_tick_label_font);
		valAxisTickLabelFontD
				.setDescription(Messages.MHighLowPlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR,
				Messages.common_value_axis_line_color, NullEnum.NULL);
		valAxisLineColorD
				.setDescription(Messages.MHighLowPlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION,
				Messages.common_range_axis_minvalue_expression);
		rangeAxisMinExprD
				.setDescription(Messages.MHighLowPlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_range_axis_maxvalue_expression);
		rangeAxisMaxExprD
				.setDescription(Messages.MHighLowPlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_domain_axis_minvalue_expression);
		domainAxisMinExprD
				.setDescription(Messages.MHighLowPlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION,
				Messages.common_domain_axis_maxvalue_expression);
		domainAxisMaxExprD
				.setDescription(Messages.MHighLowPlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_category_axis_vertical_tick_labels,
				NullEnum.NULL);
		catAxisVertTickLabelD
				.setDescription(Messages.MHighLowPlot_category_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_value_axis_vertical_tick_labels,
				NullEnum.NULL);
		valAxisVertTickLabelD
				.setDescription(Messages.MHighLowPlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK,
				Messages.common_category_axis_tick_label_mask);
		catAxisTickLabelMaskD
				.setDescription(Messages.MHighLowPlot_category_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK,
				Messages.common_value_axis_tick_label_mask);
		valAxisTickLabelMaskD
				.setDescription(Messages.MHighLowPlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

		CheckBoxPropertyDescriptor showCloseTicksD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS,
				Messages.MHighLowPlot_show_close_ticks, NullEnum.NULL);
		showCloseTicksD
				.setDescription(Messages.MHighLowPlot_show_close_ticks_description);
		desc.add(showCloseTicksD);

		CheckBoxPropertyDescriptor showOpenTicksD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS,
				Messages.MHighLowPlot_show_open_ticks, NullEnum.NULL);
		showOpenTicksD
				.setDescription(Messages.MHighLowPlot_show_open_ticks_description);
		desc.add(showOpenTicksD);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignHighLowPlot jrElement = (JRDesignHighLowPlot) getValue();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnTimeAxisLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnTimeAxisTickLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnTimeAxisLineColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnValueAxisLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnValueAxisTickLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnValueAxisLineColor());

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getTimeAxisVerticalTickLabels();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getValueAxisVerticalTickLabels();

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			return jrElement.getTimeAxisTickLabelMask();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			return jrElement.getValueAxisTickLabelMask();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS))
			return jrElement.getShowCloseTicks();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS))
			return jrElement.getShowOpenTicks();

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getTimeAxisLabelExpression());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getValueAxisLabelExpression());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getRangeAxisMaxValueExpression());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getRangeAxisMinValueExpression());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getDomainAxisMaxValueExpression());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getDomainAxisMinValueExpression());

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT)) {
			clFont = MFontUtil.getMFont(clFont,
					jrElement.getTimeAxisLabelFont(), null, this);
			return clFont;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT)) {
			ctFont = MFontUtil.getMFont(ctFont,
					jrElement.getTimeAxisTickLabelFont(), null, this);
			return ctFont;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			vlFont = MFontUtil.getMFont(vlFont,
					jrElement.getValueAxisLabelFont(), null, this);
			return vlFont;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
			vtFont = MFontUtil.getMFont(vtFont,
					jrElement.getValueAxisTickLabelFont(), null, this);
			return vtFont;
		}

		return super.getPropertyValue(id);
	}

	private MFont clFont;
	private MFont ctFont;
	private MFont vlFont;
	private MFont vtFont;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignHighLowPlot jrElement = (JRDesignHighLowPlot) getValue();

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT)) {
			jrElement.setTimeAxisLabelFont(MFontUtil.setMFont(value));
		} else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT)) {
			jrElement.setTimeAxisTickLabelFont(MFontUtil.setMFont(value));
		} else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			jrElement.setValueAxisLabelFont(MFontUtil.setMFont(value));
		} else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
			jrElement.setValueAxisTickLabelFont(MFontUtil.setMFont(value));
		} else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setTimeAxisLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setTimeAxisTickLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR)
				&& value instanceof RGB)
			jrElement.setTimeAxisLineColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setValueAxisLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setValueAxisTickLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR)
				&& value instanceof RGB)
			jrElement.setValueAxisLineColor(Colors
					.getAWT4SWTRGBColor((RGB) value));

		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setTimeAxisVerticalTickLabels((Boolean) value);
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setValueAxisVerticalTickLabels((Boolean) value);

		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			jrElement.setTimeAxisTickLabelMask((String) value);
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			jrElement.setValueAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS))
			jrElement.setShowCloseTicks((Boolean) value);
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS))
			jrElement.setShowOpenTicks((Boolean) value);

		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION))
			jrElement.setTimeAxisLabelExpression(ExprUtil.setValues(
					jrElement.getTimeAxisLabelExpression(), value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION))
			jrElement.setValueAxisLabelExpression(ExprUtil.setValues(
					jrElement.getValueAxisLabelExpression(), value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION))
			jrElement.setRangeAxisMaxValueExpression(ExprUtil.setValues(
					jrElement.getRangeAxisMaxValueExpression(), value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION))
			jrElement.setRangeAxisMinValueExpression(ExprUtil.setValues(
					jrElement.getRangeAxisMinValueExpression(), value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION))
			jrElement.setDomainAxisMaxValueExpression(ExprUtil.setValues(
					jrElement.getDomainAxisMaxValueExpression(), value));
		else if (id
				.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION))
			jrElement.setDomainAxisMinValueExpression(ExprUtil.setValues(
					jrElement.getDomainAxisMinValueExpression(), value));
		else
			super.setPropertyValue(id, value);
	}
}
