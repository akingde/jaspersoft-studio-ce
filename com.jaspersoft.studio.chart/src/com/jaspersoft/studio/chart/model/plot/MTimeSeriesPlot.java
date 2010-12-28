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

import net.sf.jasperreports.charts.JRTimeSeriesPlot;
import net.sf.jasperreports.charts.design.JRDesignTimeSeriesPlot;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;

public class MTimeSeriesPlot extends MChartPlot {
	public MTimeSeriesPlot(JRTimeSeriesPlot value) {
		super(value);
	}

	public String getDisplayText() {
		return Messages.MTimeSeriesPlot_timeseries_plot;
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
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_COLOR, Messages.MTimeSeriesPlot_time_axis_label_color,
				NullEnum.NULL);
		catAxisLabelColorD.setDescription(Messages.MTimeSeriesPlot_time_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION, Messages.MTimeSeriesPlot_time_axis_label_expression);
		catAxisLabelExprD.setDescription(Messages.MTimeSeriesPlot_time_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_FONT, Messages.MTimeSeriesPlot_time_axis_label_font);
		catAxisLabelFontD.setDescription(Messages.MTimeSeriesPlot_time_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR,
				Messages.MTimeSeriesPlot_time_axis_tick_label_color, NullEnum.NULL);
		catAxisTickLabelColorD.setDescription(Messages.MTimeSeriesPlot_time_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT, Messages.MTimeSeriesPlot_time_axis_tick_label_font);
		catAxisTickLabelFontD.setDescription(Messages.MTimeSeriesPlot_time_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LINE_COLOR, Messages.MTimeSeriesPlot_time_axis_line_color,
				NullEnum.NULL);
		catAxisLineColorD.setDescription(Messages.MTimeSeriesPlot_time_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR, Messages.common_value_axis_label_color,
				NullEnum.NULL);
		valAxisLabelColorD.setDescription(Messages.MTimeSeriesPlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION,
				Messages.common_category_value_axis_label_expression);
		valAxisLabelExprD.setDescription(Messages.MTimeSeriesPlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, Messages.common_value_axis_label_font);
		valAxisLabelFontD.setDescription(Messages.MTimeSeriesPlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR,
				Messages.common_value_axis_tick_label_color, NullEnum.NULL);
		valAxisTickLabelColorD.setDescription(Messages.MTimeSeriesPlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT, Messages.common_value_axis_tick_label_font);
		valAxisTickLabelFontD.setDescription(Messages.MTimeSeriesPlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LINE_COLOR, Messages.common_value_axis_line_color,
				NullEnum.NULL);
		valAxisLineColorD.setDescription(Messages.MTimeSeriesPlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION,
				Messages.common_range_axis_minvalue_expression);
		rangeAxisMinExprD.setDescription(Messages.MTimeSeriesPlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_range_axis_maxvalue_expression);
		rangeAxisMaxExprD.setDescription(Messages.MTimeSeriesPlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION,
				Messages.common_domain_axis_minvalue_expression);
		domainAxisMinExprD.setDescription(Messages.MTimeSeriesPlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION,
				Messages.common_domain_axis_maxvalue_expression);
		domainAxisMaxExprD.setDescription(Messages.MTimeSeriesPlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS,
				Messages.MTimeSeriesPlot_time_axis_vertical_tick_labels, NullEnum.NOTNULL);
		catAxisVertTickLabelD.setDescription(Messages.MTimeSeriesPlot_time_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS,
				Messages.common_value_axis_vertical_tick_labels, NullEnum.NOTNULL);
		valAxisVertTickLabelD.setDescription(Messages.MTimeSeriesPlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		CheckBoxPropertyDescriptor showShapesD = new CheckBoxPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_SHOW_SHAPES, Messages.common_show_shapes, NullEnum.NULL);
		showShapesD.setDescription(Messages.MTimeSeriesPlot_show_shapes_description);
		desc.add(showShapesD);

		CheckBoxPropertyDescriptor showLinesD = new CheckBoxPropertyDescriptor(JRDesignTimeSeriesPlot.PROPERTY_SHOW_LINES,
				Messages.common_show_lines, NullEnum.NULL);
		showLinesD.setDescription(Messages.MTimeSeriesPlot_show_lines_description);
		desc.add(showLinesD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK, Messages.MTimeSeriesPlot_time_axis_tick_label_mask);
		catAxisTickLabelMaskD.setDescription(Messages.MTimeSeriesPlot_time_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK, Messages.common_value_axis_tick_label_mask);
		valAxisTickLabelMaskD.setDescription(Messages.MTimeSeriesPlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

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
		JRDesignTimeSeriesPlot jrElement = (JRDesignTimeSeriesPlot) getValue();
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisLabelColor());
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisTickLabelColor());
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisLineColor());
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLabelColor());
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisTickLabelColor());
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLineColor());

		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getTimeAxisVerticalTickLabels();
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getValueAxisVerticalTickLabels();

		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			return jrElement.getTimeAxisTickLabelMask();
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			return jrElement.getValueAxisTickLabelMask();

		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_SHOW_LINES))
			return jrElement.getShowLines();
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_SHOW_SHAPES))
			return jrElement.getShowShapes();

		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION)) {
			if (ceAnchorExpression == null) {
				ceAnchorExpression = new MExpression(jrElement.getTimeAxisLabelExpression());
				setChildListener(ceAnchorExpression);
			}
			return ceAnchorExpression;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (veAnchorExpression == null) {
				veAnchorExpression = new MExpression(jrElement.getValueAxisLabelExpression());
				setChildListener(veAnchorExpression);
			}
			return veAnchorExpression;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (rmaxAnchorExpression == null) {
				rmaxAnchorExpression = new MExpression(jrElement.getRangeAxisMaxValueExpression());
				setChildListener(rmaxAnchorExpression);
			}
			return rmaxAnchorExpression;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (rminAnchorExpression == null) {
				rminAnchorExpression = new MExpression(jrElement.getRangeAxisMinValueExpression());
				setChildListener(rminAnchorExpression);
			}
			return rminAnchorExpression;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (dmaxAnchorExpression == null) {
				dmaxAnchorExpression = new MExpression(jrElement.getDomainAxisMaxValueExpression());
				setChildListener(dmaxAnchorExpression);
			}
			return dmaxAnchorExpression;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (dminAnchorExpression == null) {
				dminAnchorExpression = new MExpression(jrElement.getDomainAxisMinValueExpression());
				setChildListener(dminAnchorExpression);
			}
			return dminAnchorExpression;
		}

		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_FONT)) {
			if (clFont == null) {
				clFont = new MFont(jrElement.getTimeAxisLabelFont());
				setChildListener(clFont);
			}
			return clFont;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT)) {
			if (ctFont == null) {
				ctFont = new MFont(jrElement.getTimeAxisTickLabelFont());
				setChildListener(ctFont);
			}
			return ctFont;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			if (vlFont == null) {
				vlFont = new MFont(jrElement.getValueAxisLabelFont());
				setChildListener(vlFont);
			}
			return vlFont;
		}
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
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
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignTimeSeriesPlot jrElement = (JRDesignTimeSeriesPlot) getValue();
		if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));

		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setTimeAxisVerticalTickLabels((Boolean) value);
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setValueAxisVerticalTickLabels((Boolean) value);

		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_SHOW_LINES))
			jrElement.setShowLines((Boolean) value);
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_SHOW_SHAPES))
			jrElement.setShowShapes((Boolean) value);

		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			jrElement.setTimeAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			jrElement.setValueAxisTickLabelMask((String) value);

		else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				ceAnchorExpression = (MExpression) value;
				setChildListener(ceAnchorExpression);
				JRExpression expression = (JRExpression) ceAnchorExpression.getValue();
				jrElement.setTimeAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				veAnchorExpression = (MExpression) value;
				setChildListener(veAnchorExpression);
				JRExpression expression = (JRExpression) veAnchorExpression.getValue();
				jrElement.setValueAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rmaxAnchorExpression = (MExpression) value;
				setChildListener(rmaxAnchorExpression);
				JRExpression expression = (JRExpression) rmaxAnchorExpression.getValue();
				jrElement.setRangeAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rminAnchorExpression = (MExpression) value;
				setChildListener(rminAnchorExpression);
				JRExpression expression = (JRExpression) rminAnchorExpression.getValue();
				jrElement.setRangeAxisMinValueExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dmaxAnchorExpression = (MExpression) value;
				setChildListener(dmaxAnchorExpression);
				JRExpression expression = (JRExpression) dmaxAnchorExpression.getValue();
				jrElement.setDomainAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignTimeSeriesPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
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
