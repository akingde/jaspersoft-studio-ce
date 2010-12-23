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

import net.sf.jasperreports.charts.JRBubblePlot;
import net.sf.jasperreports.charts.design.JRDesignBubblePlot;
import net.sf.jasperreports.charts.type.ScaleTypeEnum;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

public class MBubblePlot extends MChartPlot {
	public MBubblePlot(JRBubblePlot value) {
		super(value);
	}

	public String getDisplayText() {
		return Messages.MBubblePlot_bubble_plot;
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ColorPropertyDescriptor catAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_COLOR, Messages.common_category_axis_label_color, NullEnum.NULL);
		catAxisLabelColorD.setDescription(Messages.MBubblePlot_category_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_EXPRESSION, Messages.common_category_axis_label_expression);
		catAxisLabelExprD.setDescription(Messages.MBubblePlot_category_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_FONT, Messages.common_category_axis_label_font);
		catAxisLabelFontD.setDescription(Messages.MBubblePlot_category_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_COLOR, Messages.common_category_axis_tick_label_color,
				NullEnum.NULL);
		catAxisTickLabelColorD.setDescription(Messages.MBubblePlot_category_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_FONT, Messages.common_category_axis_tick_label_font);
		catAxisTickLabelFontD.setDescription(Messages.MBubblePlot_category_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_LINE_COLOR, Messages.common_category_axis_line_color, NullEnum.NULL);
		catAxisLineColorD.setDescription(Messages.MBubblePlot_category_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_COLOR, Messages.common_value_axis_label_color, NullEnum.NULL);
		valAxisLabelColorD.setDescription(Messages.MBubblePlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_EXPRESSION, Messages.common_category_value_axis_label_expression);
		valAxisLabelExprD.setDescription(Messages.MBubblePlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_FONT, Messages.common_value_axis_label_font);
		valAxisLabelFontD.setDescription(Messages.MBubblePlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_COLOR, Messages.common_value_axis_tick_label_color,
				NullEnum.NULL);
		valAxisTickLabelColorD.setDescription(Messages.MBubblePlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_FONT, Messages.common_value_axis_tick_label_font);
		valAxisTickLabelFontD.setDescription(Messages.MBubblePlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_LINE_COLOR, Messages.common_value_axis_line_color, NullEnum.NULL);
		valAxisLineColorD.setDescription(Messages.MBubblePlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION, Messages.common_range_axis_minvalue_expression);
		rangeAxisMinExprD.setDescription(Messages.MBubblePlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION, Messages.common_range_axis_maxvalue_expression);
		rangeAxisMaxExprD.setDescription(Messages.MBubblePlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_domain_axis_minvalue_expression);
		domainAxisMinExprD.setDescription(Messages.MBubblePlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION,
				Messages.common_domain_axis_maxvalue_expression);
		domainAxisMaxExprD.setDescription(Messages.MBubblePlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_category_axis_vertical_tick_labels, NullEnum.NOTNULL);
		catAxisVertTickLabelD.setDescription(Messages.MBubblePlot_category_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_VERTICAL_TICK_LABELS, Messages.common_value_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		valAxisVertTickLabelD.setDescription(Messages.MBubblePlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_MASK, Messages.common_category_axis_tick_label_mask);
		catAxisTickLabelMaskD.setDescription(Messages.MBubblePlot_category_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_MASK, Messages.common_value_axis_tick_label_mask);
		valAxisTickLabelMaskD.setDescription(Messages.MBubblePlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

		ComboBoxPropertyDescriptor scaleType = new ComboBoxPropertyDescriptor(JRDesignBubblePlot.PROPERTY_SCALE_TYPE,
				Messages.MBubblePlot_scale_type, EnumHelper.getEnumNames(ScaleTypeEnum.values(), NullEnum.NOTNULL));
		scaleType.setDescription(Messages.MBubblePlot_scale_type_description);
		desc.add(scaleType);
	}

	private MExpression ceAnchorExpression;
	private MExpression veAnchorExpression;
	private MExpression rmaxAnchorExpression;
	private MExpression rminAnchorExpression;
	private MExpression dmaxAnchorExpression;
	private MExpression dminAnchorExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignBubblePlot jrElement = (JRDesignBubblePlot) getValue();
		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnXAxisLabelColor());
		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnXAxisTickLabelColor());
		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnXAxisLineColor());
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnYAxisLabelColor());
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnYAxisTickLabelColor());
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnYAxisLineColor());

		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getXAxisVerticalTickLabels();
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getYAxisVerticalTickLabels();

		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_MASK))
			return jrElement.getXAxisTickLabelMask();
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_MASK))
			return jrElement.getYAxisTickLabelMask();

		if (id.equals(JRDesignBubblePlot.PROPERTY_SCALE_TYPE))
			return EnumHelper.getValue(jrElement.getScaleTypeValue(), 0, false);

		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_EXPRESSION)) {
			if (ceAnchorExpression == null) {
				ceAnchorExpression = new MExpression(jrElement.getXAxisLabelExpression());
				setChildListener(ceAnchorExpression);
			}
			return ceAnchorExpression;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_EXPRESSION)) {
			if (veAnchorExpression == null) {
				veAnchorExpression = new MExpression(jrElement.getYAxisLabelExpression());
				setChildListener(veAnchorExpression);
			}
			return veAnchorExpression;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (rmaxAnchorExpression == null) {
				rmaxAnchorExpression = new MExpression(jrElement.getRangeAxisMaxValueExpression());
				setChildListener(rmaxAnchorExpression);
			}
			return rmaxAnchorExpression;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (rminAnchorExpression == null) {
				rminAnchorExpression = new MExpression(jrElement.getRangeAxisMinValueExpression());
				setChildListener(rminAnchorExpression);
			}
			return rminAnchorExpression;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (dmaxAnchorExpression == null) {
				dmaxAnchorExpression = new MExpression(jrElement.getDomainAxisMaxValueExpression());
				setChildListener(dmaxAnchorExpression);
			}
			return dmaxAnchorExpression;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (dminAnchorExpression == null) {
				dminAnchorExpression = new MExpression(jrElement.getDomainAxisMinValueExpression());
				setChildListener(dminAnchorExpression);
			}
			return dminAnchorExpression;
		}

		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_FONT)) {
			if (clFont == null) {
				clFont = new MFont(jrElement.getXAxisLabelFont());
				setChildListener(clFont);
			}
			return clFont;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_FONT)) {
			if (ctFont == null) {
				ctFont = new MFont(jrElement.getXAxisTickLabelFont());
				setChildListener(ctFont);
			}
			return ctFont;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_FONT)) {
			if (vlFont == null) {
				vlFont = new MFont(jrElement.getYAxisLabelFont());
				setChildListener(vlFont);
			}
			return vlFont;
		}
		if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_FONT)) {
			if (vtFont == null) {
				vtFont = new MFont(jrElement.getYAxisTickLabelFont());
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
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignBubblePlot jrElement = (JRDesignBubblePlot) getValue();
		if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setXAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setXAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setXAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setYAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setYAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setYAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));

		else if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setXAxisVerticalTickLabels((Boolean) value);
		else if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setYAxisVerticalTickLabels((Boolean) value);

		else if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_TICK_LABEL_MASK))
			jrElement.setXAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_TICK_LABEL_MASK))
			jrElement.setYAxisTickLabelMask((String) value);

		else if (id.equals(JRDesignBubblePlot.PROPERTY_SCALE_TYPE))
			jrElement.setScaleType((ScaleTypeEnum) EnumHelper.getSetValue(ScaleTypeEnum.values(), value, 0, false));

		else if (id.equals(JRDesignBubblePlot.PROPERTY_X_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				ceAnchorExpression = (MExpression) value;
				setChildListener(ceAnchorExpression);
				JRExpression expression = (JRExpression) ceAnchorExpression.getValue();
				jrElement.setXAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignBubblePlot.PROPERTY_Y_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				veAnchorExpression = (MExpression) value;
				setChildListener(veAnchorExpression);
				JRExpression expression = (JRExpression) veAnchorExpression.getValue();
				jrElement.setYAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignBubblePlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rmaxAnchorExpression = (MExpression) value;
				setChildListener(rmaxAnchorExpression);
				JRExpression expression = (JRExpression) rmaxAnchorExpression.getValue();
				jrElement.setRangeAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignBubblePlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rminAnchorExpression = (MExpression) value;
				setChildListener(rminAnchorExpression);
				JRExpression expression = (JRExpression) rminAnchorExpression.getValue();
				jrElement.setRangeAxisMinValueExpression(expression);
			}
		} else if (id.equals(JRDesignBubblePlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dmaxAnchorExpression = (MExpression) value;
				setChildListener(dmaxAnchorExpression);
				JRExpression expression = (JRExpression) dmaxAnchorExpression.getValue();
				jrElement.setDomainAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignBubblePlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dminAnchorExpression = (MExpression) value;
				setChildListener(dminAnchorExpression);
				JRExpression expression = (JRExpression) dminAnchorExpression.getValue();
				jrElement.setDomainAxisMinValueExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}
}
