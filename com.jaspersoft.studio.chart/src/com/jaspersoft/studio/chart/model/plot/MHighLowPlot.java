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

import org.eclipse.swt.graphics.RGB;
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

import net.sf.jasperreports.charts.JRHighLowPlot;
import net.sf.jasperreports.charts.design.JRDesignHighLowPlot;
import net.sf.jasperreports.engine.JRExpression;

public class MHighLowPlot extends MChartPlot {
	public MHighLowPlot(JRHighLowPlot value) {
		super(value);
	}
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ColorPropertyDescriptor catAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR, Messages.MHighLowPlot_category_axis_label_color, NullEnum.NULL);
		catAxisLabelColorD.setDescription(Messages.MHighLowPlot_category_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION, Messages.MHighLowPlot_category_axis_label_expression);
		catAxisLabelExprD.setDescription(Messages.MHighLowPlot_category_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT, Messages.MHighLowPlot_category_axis_label_font);
		catAxisLabelFontD.setDescription(Messages.MHighLowPlot_category_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR, Messages.MHighLowPlot_category_axis_tick_label_color, NullEnum.NULL);
		catAxisTickLabelColorD.setDescription(Messages.MHighLowPlot_category_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT, Messages.MHighLowPlot_category_axis_tick_label_font);
		catAxisTickLabelFontD.setDescription(Messages.MHighLowPlot_category_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR, Messages.MHighLowPlot_category_axis_line_color, NullEnum.NULL);
		catAxisLineColorD.setDescription(Messages.MHighLowPlot_category_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR, Messages.MHighLowPlot_value_axis_label_color, NullEnum.NULL);
		valAxisLabelColorD.setDescription(Messages.MHighLowPlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION, Messages.MHighLowPlot_category_value_axis_label_expression);
		valAxisLabelExprD.setDescription(Messages.MHighLowPlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, Messages.MHighLowPlot_value_axis_label_font);
		valAxisLabelFontD.setDescription(Messages.MHighLowPlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR, Messages.MHighLowPlot_value_axis_tick_label_color, NullEnum.NULL);
		valAxisTickLabelColorD.setDescription(Messages.MHighLowPlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT, Messages.MHighLowPlot_value_axis_tick_label_font);
		valAxisTickLabelFontD.setDescription(Messages.MHighLowPlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR, Messages.MHighLowPlot_value_axis_line_color, NullEnum.NULL);
		valAxisLineColorD.setDescription(Messages.MHighLowPlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION, Messages.MHighLowPlot_range_axis_minvalue_expression);
		rangeAxisMinExprD.setDescription(Messages.MHighLowPlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION, Messages.MHighLowPlot_range_axis_maxvalue_expression);
		rangeAxisMaxExprD.setDescription(Messages.MHighLowPlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION, Messages.MHighLowPlot_domain_axis_minvalue_expression);
		domainAxisMinExprD.setDescription(Messages.MHighLowPlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION, Messages.MHighLowPlot_domain_axis_maxvalue_expression);
		domainAxisMaxExprD.setDescription(Messages.MHighLowPlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS, Messages.MHighLowPlot_category_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		catAxisVertTickLabelD.setDescription(Messages.MHighLowPlot_category_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS, Messages.MHighLowPlot_value_axis_vertical_tick_labels,
				NullEnum.NOTNULL);
		valAxisVertTickLabelD.setDescription(Messages.MHighLowPlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK, Messages.MHighLowPlot_category_axis_tick_label_mask);
		catAxisTickLabelMaskD.setDescription(Messages.MHighLowPlot_category_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK, Messages.MHighLowPlot_value_axis_tick_label_mask);
		valAxisTickLabelMaskD.setDescription(Messages.MHighLowPlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

		CheckBoxPropertyDescriptor showCloseTicksD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS, Messages.MHighLowPlot_show_close_ticks, NullEnum.NULL);
		showCloseTicksD.setDescription(Messages.MHighLowPlot_show_close_ticks_description);
		desc.add(showCloseTicksD);

		CheckBoxPropertyDescriptor showOpenTicksD = new CheckBoxPropertyDescriptor(
				JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS, Messages.MHighLowPlot_show_open_ticks, NullEnum.NULL);
		showOpenTicksD.setDescription(Messages.MHighLowPlot_show_open_ticks_description);
		desc.add(showOpenTicksD);

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
		JRDesignHighLowPlot jrElement = (JRDesignHighLowPlot) getValue();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisTickLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisLineColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisTickLabelColor());
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLineColor());

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

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION)) {
			if (ceAnchorExpression == null)
				ceAnchorExpression = new MExpression(jrElement.getTimeAxisLabelExpression());
			return ceAnchorExpression;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (veAnchorExpression == null)
				veAnchorExpression = new MExpression(jrElement.getValueAxisLabelExpression());
			return veAnchorExpression;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (rmaxAnchorExpression == null)
				rmaxAnchorExpression = new MExpression(jrElement.getRangeAxisMaxValueExpression());
			return rmaxAnchorExpression;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (rminAnchorExpression == null)
				rminAnchorExpression = new MExpression(jrElement.getRangeAxisMinValueExpression());
			return rminAnchorExpression;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (dmaxAnchorExpression == null)
				dmaxAnchorExpression = new MExpression(jrElement.getDomainAxisMaxValueExpression());
			return dmaxAnchorExpression;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (dminAnchorExpression == null)
				dminAnchorExpression = new MExpression(jrElement.getDomainAxisMinValueExpression());
			return dminAnchorExpression;
		}

		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_FONT)) {
			if (clFont == null)
				clFont = new MFont(jrElement.getTimeAxisLabelFont());
			return clFont;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT)) {
			if (ctFont == null)
				ctFont = new MFont(jrElement.getTimeAxisTickLabelFont());
			return ctFont;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			if (vlFont == null)
				vlFont = new MFont(jrElement.getValueAxisLabelFont());
			return vlFont;
		}
		if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
			if (vtFont == null)
				vtFont = new MFont(jrElement.getValueAxisTickLabelFont());
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
		JRDesignHighLowPlot jrElement = (JRDesignHighLowPlot) getValue();
		if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));

		else if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setTimeAxisVerticalTickLabels((Boolean) value);
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setValueAxisVerticalTickLabels((Boolean) value);

		else if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			jrElement.setTimeAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			jrElement.setValueAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_SHOW_CLOSE_TICKS))
			jrElement.setShowCloseTicks((Boolean) value);
		else if (id.equals(JRDesignHighLowPlot.PROPERTY_SHOW_OPEN_TICKS))
			jrElement.setShowOpenTicks((Boolean) value);

		else if (id.equals(JRDesignHighLowPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				ceAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) ceAnchorExpression.getValue();
				jrElement.setTimeAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				veAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) veAnchorExpression.getValue();
				jrElement.setValueAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rmaxAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) rmaxAnchorExpression.getValue();
				jrElement.setRangeAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rminAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) rminAnchorExpression.getValue();
				jrElement.setRangeAxisMinValueExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dmaxAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dmaxAnchorExpression.getValue();
				jrElement.setDomainAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignHighLowPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dminAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dminAnchorExpression.getValue();
				jrElement.setDomainAxisMinValueExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}
}
