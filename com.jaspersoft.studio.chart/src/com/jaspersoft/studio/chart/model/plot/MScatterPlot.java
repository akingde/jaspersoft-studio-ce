/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.plot;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.JRScatterPlot;
import net.sf.jasperreports.charts.design.JRDesignScatterPlot;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.messages.Messages;
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

public class MScatterPlot extends MChartPlot {
	public MScatterPlot(JRScatterPlot value) {
		super(value);
	}

	@Override
	public String getDisplayText() {
		return Messages.MScatterPlot_scatter_plot;
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
				JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_COLOR,
				Messages.common_category_axis_label_color, NullEnum.NULL);
		catAxisLabelColorD
				.setDescription(Messages.MScatterPlot_category_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_EXPRESSION,
				Messages.common_category_axis_label_expression);
		catAxisLabelExprD
				.setDescription(Messages.MScatterPlot_category_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_FONT,
				Messages.common_category_axis_label_font);
		catAxisLabelFontD
				.setDescription(Messages.MScatterPlot_category_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_COLOR,
				Messages.common_category_axis_tick_label_color, NullEnum.NULL);
		catAxisTickLabelColorD
				.setDescription(Messages.MScatterPlot_category_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_FONT,
				Messages.common_category_axis_tick_label_font);
		catAxisTickLabelFontD
				.setDescription(Messages.MScatterPlot_category_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_LINE_COLOR,
				Messages.common_category_axis_line_color, NullEnum.NULL);
		catAxisLineColorD
				.setDescription(Messages.MScatterPlot_category_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_COLOR,
				Messages.common_value_axis_label_color, NullEnum.NULL);
		valAxisLabelColorD
				.setDescription(Messages.MScatterPlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_EXPRESSION,
				Messages.common_category_value_axis_label_expression);
		valAxisLabelExprD
				.setDescription(Messages.MScatterPlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_FONT,
				Messages.common_value_axis_label_font);
		valAxisLabelFontD
				.setDescription(Messages.MScatterPlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_COLOR,
				Messages.common_value_axis_tick_label_color, NullEnum.NULL);
		valAxisTickLabelColorD
				.setDescription(Messages.MScatterPlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_FONT,
				Messages.common_value_axis_tick_label_font);
		valAxisTickLabelFontD
				.setDescription(Messages.MScatterPlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_LINE_COLOR,
				Messages.common_value_axis_line_color, NullEnum.NULL);
		valAxisLineColorD
				.setDescription(Messages.MScatterPlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION,
				Messages.common_range_axis_minvalue_expression);
		rangeAxisMinExprD
				.setDescription(Messages.MScatterPlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_range_axis_maxvalue_expression);
		rangeAxisMaxExprD
				.setDescription(Messages.MScatterPlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_domain_axis_minvalue_expression);
		domainAxisMinExprD
				.setDescription(Messages.MScatterPlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION,
				Messages.common_domain_axis_maxvalue_expression);
		domainAxisMaxExprD
				.setDescription(Messages.MScatterPlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_category_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		catAxisVertTickLabelD
				.setDescription(Messages.MScatterPlot_category_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_value_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		valAxisVertTickLabelD
				.setDescription(Messages.MScatterPlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_MASK,
				Messages.common_category_axis_tick_label_mask);
		catAxisTickLabelMaskD
				.setDescription(Messages.MScatterPlot_category_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_MASK,
				Messages.common_value_axis_tick_label_mask);
		valAxisTickLabelMaskD
				.setDescription(Messages.MScatterPlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

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
		JRDesignScatterPlot jrElement = (JRDesignScatterPlot) getValue();
		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnXAxisLabelColor());
		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnXAxisTickLabelColor());
		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LINE_COLOR))
			return Colors
					.getSWTRGB4AWTGBColor(jrElement.getOwnXAxisLineColor());
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnYAxisLabelColor());
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnYAxisTickLabelColor());
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LINE_COLOR))
			return Colors
					.getSWTRGB4AWTGBColor(jrElement.getOwnYAxisLineColor());

		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getXAxisVerticalTickLabels();
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getYAxisVerticalTickLabels();

		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_MASK))
			return jrElement.getXAxisTickLabelMask();
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_MASK))
			return jrElement.getYAxisTickLabelMask();

		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getXAxisLabelExpression());
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getYAxisLabelExpression());
		if (id.equals(JRDesignScatterPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getRangeAxisMaxValueExpression());
		if (id.equals(JRDesignScatterPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getRangeAxisMinValueExpression());
		if (id.equals(JRDesignScatterPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getDomainAxisMaxValueExpression());
		if (id.equals(JRDesignScatterPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getDomainAxisMinValueExpression());

		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_FONT)) {
			clFont = MFontUtil.getMFont(clFont, jrElement.getXAxisLabelFont(),
					null, this);
			return clFont;
		}
		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_FONT)) {
			ctFont = MFontUtil.getMFont(ctFont,
					jrElement.getXAxisTickLabelFont(), null, this);
			return ctFont;
		}
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_FONT)) {
			vlFont = MFontUtil.getMFont(vlFont, jrElement.getYAxisLabelFont(),
					null, this);
			return vlFont;
		}
		if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_FONT)) {
			vtFont = MFontUtil.getMFont(vtFont,
					jrElement.getYAxisTickLabelFont(), null, this);
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
		JRDesignScatterPlot jrElement = (JRDesignScatterPlot) getValue();

		if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_FONT)) {
			jrElement.setXAxisLabelFont(MFontUtil.setMFont(value));
		} else if (id
				.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_FONT)) {
			jrElement.setXAxisTickLabelFont(MFontUtil.setMFont(value));
		} else if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_FONT)) {
			jrElement.setYAxisLabelFont(MFontUtil.setMFont(value));
		} else if (id
				.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_FONT)) {
			jrElement.setYAxisTickLabelFont(MFontUtil.setMFont(value));
		} else if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement
					.setXAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setXAxisTickLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LINE_COLOR)
				&& value instanceof RGB)
			jrElement.setXAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement
					.setYAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setYAxisTickLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LINE_COLOR)
				&& value instanceof RGB)
			jrElement.setYAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));

		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setXAxisVerticalTickLabels((Boolean) value);
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setYAxisVerticalTickLabels((Boolean) value);

		else if (id.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_TICK_LABEL_MASK))
			jrElement.setXAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_TICK_LABEL_MASK))
			jrElement.setYAxisTickLabelMask((String) value);

		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_X_AXIS_LABEL_EXPRESSION))
			jrElement.setXAxisLabelExpression(ExprUtil.setValues(
					jrElement.getXAxisLabelExpression(), value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_Y_AXIS_LABEL_EXPRESSION))
			jrElement.setYAxisLabelExpression(ExprUtil.setValues(
					jrElement.getYAxisLabelExpression(), value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION))
			jrElement.setRangeAxisMaxValueExpression(ExprUtil.setValues(
					jrElement.getRangeAxisMaxValueExpression(), value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION))
			jrElement.setRangeAxisMinValueExpression(ExprUtil.setValues(
					jrElement.getRangeAxisMinValueExpression(), value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION))
			jrElement.setDomainAxisMaxValueExpression(ExprUtil.setValues(
					jrElement.getDomainAxisMaxValueExpression(), value));
		else if (id
				.equals(JRDesignScatterPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION))
			jrElement.setDomainAxisMinValueExpression(ExprUtil.setValues(
					jrElement.getDomainAxisMinValueExpression(), value));
		else
			super.setPropertyValue(id, value);
	}
}
