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

import net.sf.jasperreports.charts.JRAreaPlot;
import net.sf.jasperreports.charts.design.JRDesignAreaPlot;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;

public class MAreaPlot extends MChartPlot {

	public MAreaPlot(JRAreaPlot value) {
		super(value);
	}

	public String getDisplayText() {
		return Messages.MAreaPlot_area_plot;
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
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR,
				Messages.common_category_axis_label_color, NullEnum.NULL);
		catAxisLabelColorD
				.setDescription(Messages.MAreaPlot_category_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION,
				Messages.common_category_axis_label_expression);
		catAxisLabelExprD
				.setDescription(Messages.MAreaPlot_category_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_FONT,
				Messages.common_category_axis_label_font);
		catAxisLabelFontD
				.setDescription(Messages.MAreaPlot_category_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR,
				Messages.common_category_axis_tick_label_color, NullEnum.NULL);
		catAxisTickLabelColorD
				.setDescription(Messages.MAreaPlot_category_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_FONT,
				Messages.common_category_axis_tick_label_font);
		catAxisTickLabelFontD
				.setDescription(Messages.MAreaPlot_category_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR,
				Messages.common_category_axis_line_color, NullEnum.NULL);
		catAxisLineColorD
				.setDescription(Messages.MAreaPlot_category_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR,
				Messages.common_value_axis_label_color, NullEnum.NULL);
		valAxisLabelColorD
				.setDescription(Messages.MAreaPlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION,
				Messages.common_category_value_axis_label_expression);
		valAxisLabelExprD
				.setDescription(Messages.MAreaPlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_FONT,
				Messages.common_value_axis_label_font);
		valAxisLabelFontD
				.setDescription(Messages.MAreaPlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR,
				Messages.common_value_axis_tick_label_color, NullEnum.NULL);
		valAxisTickLabelColorD
				.setDescription(Messages.MAreaPlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT,
				Messages.common_value_axis_tick_label_font);
		valAxisTickLabelFontD
				.setDescription(Messages.MAreaPlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LINE_COLOR,
				Messages.common_value_axis_line_color, NullEnum.NULL);
		valAxisLineColorD
				.setDescription(Messages.MAreaPlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION,
				Messages.common_range_axis_minvalue_expression);
		rangeAxisMinExprD
				.setDescription(Messages.MAreaPlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_range_axis_maxvalue_expression);
		rangeAxisMaxExprD
				.setDescription(Messages.MAreaPlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_domain_axis_minvalue_expression);
		domainAxisMinExprD
				.setDescription(Messages.MAreaPlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION,
				Messages.common_domain_axis_maxvalue_expression);
		domainAxisMaxExprD
				.setDescription(Messages.MAreaPlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_category_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		catAxisVertTickLabelD
				.setDescription(Messages.MAreaPlot_category_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_value_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		valAxisVertTickLabelD
				.setDescription(Messages.MAreaPlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK,
				Messages.common_category_axis_tick_label_mask);
		catAxisTickLabelMaskD
				.setDescription(Messages.MAreaPlot_category_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK,
				Messages.common_value_axis_tick_label_mask);
		valAxisTickLabelMaskD
				.setDescription(Messages.MAreaPlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

		DoublePropertyDescriptor catAxisTickLabelRotation = new DoublePropertyDescriptor(
				JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION,
				Messages.common_category_axis_tick_label_rotation);
		catAxisTickLabelRotation
				.setDescription(Messages.MAreaPlot_category_axis_tick_label_rotation_description);
		desc.add(catAxisTickLabelRotation);

		catAxisLabelColorD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisTickLabelMaskD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisVertTickLabelD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisLineColorD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisLabelColorD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisLabelExprD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisLabelFontD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisTickLabelColorD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisTickLabelFontD.setCategory(Messages.MAreaPlot_category_axis);
		catAxisTickLabelRotation.setCategory(Messages.MAreaPlot_category_axis);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignAreaPlot jrElement = (JRDesignAreaPlot) getValue();
		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnCategoryAxisLabelColor());
		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnCategoryAxisTickLabelColor());
		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnCategoryAxisLineColor());
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnValueAxisLabelColor());
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnValueAxisTickLabelColor());
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement
					.getOwnValueAxisLineColor());

		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getCategoryAxisVerticalTickLabels();
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getValueAxisVerticalTickLabels();

		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK))
			return jrElement.getCategoryAxisTickLabelMask();
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			return jrElement.getValueAxisTickLabelMask();
		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION))
			return jrElement.getCategoryAxisTickLabelRotation();

		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getCategoryAxisLabelExpression());
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getValueAxisLabelExpression());
		if (id.equals(JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getRangeAxisMaxValueExpression());
		if (id.equals(JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getRangeAxisMinValueExpression());
		if (id.equals(JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getDomainAxisMaxValueExpression());
		if (id.equals(JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement
					.getDomainAxisMinValueExpression());

		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_FONT)) {
			if (clFont == null) {
				clFont = new MFont(jrElement.getCategoryAxisLabelFont());
				setChildListener(clFont);
			}
			return clFont;
		}
		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_FONT)) {
			if (ctFont == null) {
				ctFont = new MFont(jrElement.getCategoryAxisTickLabelFont());
				setChildListener(ctFont);
			}
			return ctFont;
		}
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			if (vlFont == null) {
				vlFont = new MFont(jrElement.getValueAxisLabelFont());
				setChildListener(vlFont);
			}
			return vlFont;
		}
		if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
			if (vtFont == null) {
				vtFont = new MFont(jrElement.getValueAxisTickLabelFont());
				setChildListener(vtFont);
			}
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
	public void setPropertyValue(Object id, Object value) {
		JRDesignAreaPlot jrElement = (JRDesignAreaPlot) getValue();
		if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setCategoryAxisLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setCategoryAxisTickLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR)
				&& value instanceof RGB)
			jrElement.setCategoryAxisLineColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setValueAxisLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR)
				&& value instanceof RGB)
			jrElement.setValueAxisTickLabelColor(Colors
					.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LINE_COLOR)
				&& value instanceof RGB)
			jrElement.setValueAxisLineColor(Colors
					.getAWT4SWTRGBColor((RGB) value));

		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setCategoryAxisVerticalTickLabels((Boolean) value);
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setValueAxisVerticalTickLabels((Boolean) value);

		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK))
			jrElement.setCategoryAxisTickLabelMask((String) value);
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			jrElement.setValueAxisTickLabelMask((String) value);
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION))
			jrElement.setCategoryAxisTickLabelRotation((Double) value);

		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION))
			jrElement.setCategoryAxisLabelExpression(ExprUtil.setValues(
					jrElement.getCategoryAxisLabelExpression(), value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION))
			jrElement.setValueAxisLabelExpression(ExprUtil.setValues(
					jrElement.getValueAxisLabelExpression(), value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION))
			jrElement.setRangeAxisMaxValueExpression(ExprUtil.setValues(
					jrElement.getRangeAxisMaxValueExpression(), value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION))
			jrElement.setRangeAxisMinValueExpression(ExprUtil.setValues(
					jrElement.getRangeAxisMinValueExpression(), value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION))
			jrElement.setDomainAxisMaxValueExpression(ExprUtil.setValues(
					jrElement.getDomainAxisMaxValueExpression(), value));
		else if (id
				.equals(JRDesignAreaPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION))
			jrElement.setDomainAxisMinValueExpression(ExprUtil.setValues(
					jrElement.getDomainAxisMinValueExpression(), value));
		else
			super.setPropertyValue(id, value);
	}
}
