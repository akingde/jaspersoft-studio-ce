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

import net.sf.jasperreports.charts.JRCandlestickPlot;
import net.sf.jasperreports.charts.design.JRDesignCandlestickPlot;
import net.sf.jasperreports.engine.JRExpression;

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

public class MCandlestickPlot extends MChartPlot {
	public MCandlestickPlot(JRCandlestickPlot value) {
		super(value);
	}

	public String getDisplayText() {
		return Messages.MCandlestickPlot_candlestick_plot;
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
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_COLOR, Messages.MCandlestickPlot_category_axis_label_color,
				NullEnum.NULL);
		catAxisLabelColorD.setDescription(Messages.MCandlestickPlot_category_axis_label_color_description);
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION,
				Messages.MCandlestickPlot_category_axis_label_expression);
		catAxisLabelExprD.setDescription(Messages.MCandlestickPlot_category_axis_label_expression_description);
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_FONT, Messages.MCandlestickPlot_category_axis_label_font);
		catAxisLabelFontD.setDescription(Messages.MCandlestickPlot_category_axis_label_font_description);
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR,
				Messages.MCandlestickPlot_category_axis_tick_label_color, NullEnum.INHERITED);
		catAxisTickLabelColorD.setDescription(Messages.MCandlestickPlot_category_axis_tick_label_color_description);
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT,
				Messages.MCandlestickPlot_category_axis_tick_label_font);
		catAxisTickLabelFontD.setDescription(Messages.MCandlestickPlot_category_axis_tick_label_font_description);
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LINE_COLOR, Messages.MCandlestickPlot_category_axis_line_color,
				NullEnum.NULL);
		catAxisLineColorD.setDescription(Messages.MCandlestickPlot_category_axis_line_color_description);
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR, Messages.MCandlestickPlot_value_axis_label_color,
				NullEnum.NULL);
		valAxisLabelColorD.setDescription(Messages.MCandlestickPlot_value_axis_label_color_description);
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION,
				Messages.MCandlestickPlot_category_value_axis_label_expression);
		valAxisLabelExprD.setDescription(Messages.MCandlestickPlot_category_value_axis_label_expression_description);
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, Messages.MCandlestickPlot_value_axis_label_font);
		valAxisLabelFontD.setDescription(Messages.MCandlestickPlot_value_axis_label_font_description);
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR,
				Messages.MCandlestickPlot_value_axis_tick_label_color, NullEnum.NULL);
		valAxisTickLabelColorD.setDescription(Messages.MCandlestickPlot_value_axis_tick_label_color_description);
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT,
				Messages.MCandlestickPlot_value_axis_tick_label_font);
		valAxisTickLabelFontD.setDescription(Messages.MCandlestickPlot_value_axis_tick_label_font_description);
		desc.add(valAxisTickLabelFontD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LINE_COLOR, Messages.MCandlestickPlot_value_axis_line_color,
				NullEnum.NULL);
		valAxisLineColorD.setDescription(Messages.MCandlestickPlot_value_axis_line_color_description);
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION,
				Messages.MCandlestickPlot_range_axis_minvalue_expression);
		rangeAxisMinExprD.setDescription(Messages.MCandlestickPlot_range_axis_minvalue_expression_description);
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION,
				Messages.MCandlestickPlot_range_axis_maxvalue_expression);
		rangeAxisMaxExprD.setDescription(Messages.MCandlestickPlot_range_axis_maxvalue_expression_description);
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION,
				Messages.MCandlestickPlot_domain_axis_minvalue_expression);
		domainAxisMinExprD.setDescription(Messages.MCandlestickPlot_domain_axis_minvalue_expression_description);
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION,
				Messages.MCandlestickPlot_domain_axis_maxvalue_expression);
		domainAxisMaxExprD.setDescription(Messages.MCandlestickPlot_domain_axis_maxvalue_expression_description);
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS,
				Messages.MCandlestickPlot_category_axis_vertical_tick_labels, NullEnum.NOTNULL);
		catAxisVertTickLabelD.setDescription(Messages.MCandlestickPlot_category_axis_vertical_tick_labels_description);
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS,
				Messages.MCandlestickPlot_value_axis_vertical_tick_labels, NullEnum.NOTNULL);
		valAxisVertTickLabelD.setDescription(Messages.MCandlestickPlot_value_axis_vertical_tick_labels_description);
		desc.add(valAxisVertTickLabelD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK,
				Messages.MCandlestickPlot_category_axis_tick_label_mask);
		catAxisTickLabelMaskD.setDescription(Messages.MCandlestickPlot_category_axis_tick_label_mask_description);
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK,
				Messages.MCandlestickPlot_value_axis_tick_label_mask);
		valAxisTickLabelMaskD.setDescription(Messages.MCandlestickPlot_value_axis_tick_label_mask_description);
		desc.add(valAxisTickLabelMaskD);

		CheckBoxPropertyDescriptor showVolumeD = new CheckBoxPropertyDescriptor(
				JRDesignCandlestickPlot.PROPERTY_SHOW_VOLUME, Messages.MCandlestickPlot_show_volume, NullEnum.NOTNULL);
		showVolumeD.setDescription(Messages.MCandlestickPlot_show_volume_description);
		desc.add(showVolumeD);

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
		JRDesignCandlestickPlot jrElement = (JRDesignCandlestickPlot) getValue();
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisLabelColor());
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisTickLabelColor());
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnTimeAxisLineColor());
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLabelColor());
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisTickLabelColor());
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLineColor());

		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getTimeAxisVerticalTickLabels();
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getValueAxisVerticalTickLabels();

		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			return jrElement.getTimeAxisTickLabelMask();
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			return jrElement.getValueAxisTickLabelMask();
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_SHOW_VOLUME))
			return jrElement.getShowVolume();

		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION)) {
			if (ceAnchorExpression == null) {
				ceAnchorExpression = new MExpression(jrElement.getTimeAxisLabelExpression());
				setChildListener(ceAnchorExpression);
			}
			return ceAnchorExpression;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (veAnchorExpression == null) {
				veAnchorExpression = new MExpression(jrElement.getValueAxisLabelExpression());
				setChildListener(veAnchorExpression);
			}
			return veAnchorExpression;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (rmaxAnchorExpression == null) {
				rmaxAnchorExpression = new MExpression(jrElement.getRangeAxisMaxValueExpression());
				setChildListener(rmaxAnchorExpression);
			}
			return rmaxAnchorExpression;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (rminAnchorExpression == null) {
				rminAnchorExpression = new MExpression(jrElement.getRangeAxisMinValueExpression());
				setChildListener(rminAnchorExpression);
			}
			return rminAnchorExpression;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (dmaxAnchorExpression == null) {
				dmaxAnchorExpression = new MExpression(jrElement.getDomainAxisMaxValueExpression());
				setChildListener(dmaxAnchorExpression);
			}
			return dmaxAnchorExpression;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (dminAnchorExpression == null) {
				dminAnchorExpression = new MExpression(jrElement.getDomainAxisMinValueExpression());
				setChildListener(dminAnchorExpression);
			}
			return dminAnchorExpression;
		}

		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_FONT)) {
			if (clFont == null) {
				clFont = new MFont(jrElement.getTimeAxisLabelFont());
				setChildListener(clFont);
			}
			return clFont;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_FONT)) {
			if (ctFont == null) {
				ctFont = new MFont(jrElement.getTimeAxisTickLabelFont());
				setChildListener(ctFont);
			}
			return ctFont;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			if (vlFont == null) {
				vlFont = new MFont(jrElement.getValueAxisLabelFont());
				setChildListener(vlFont);
			}
			return vlFont;
		}
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
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
		JRDesignCandlestickPlot jrElement = (JRDesignCandlestickPlot) getValue();
		if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setTimeAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));

		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setTimeAxisVerticalTickLabels((Boolean) value);
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setValueAxisVerticalTickLabels((Boolean) value);

		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_TICK_LABEL_MASK))
			jrElement.setTimeAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			jrElement.setValueAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_SHOW_VOLUME))
			jrElement.setShowVolume((Boolean) value);

		else if (id.equals(JRDesignCandlestickPlot.PROPERTY_TIME_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				ceAnchorExpression = (MExpression) value;
				setChildListener(ceAnchorExpression);
				JRExpression expression = (JRExpression) ceAnchorExpression.getValue();
				jrElement.setTimeAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignCandlestickPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				veAnchorExpression = (MExpression) value;
				setChildListener(veAnchorExpression);
				JRExpression expression = (JRExpression) veAnchorExpression.getValue();
				jrElement.setValueAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignCandlestickPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rmaxAnchorExpression = (MExpression) value;
				setChildListener(rmaxAnchorExpression);
				JRExpression expression = (JRExpression) rmaxAnchorExpression.getValue();
				jrElement.setRangeAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignCandlestickPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rminAnchorExpression = (MExpression) value;
				setChildListener(rminAnchorExpression);
				JRExpression expression = (JRExpression) rminAnchorExpression.getValue();
				jrElement.setRangeAxisMinValueExpression(expression);
			}
		} else if (id.equals(JRDesignCandlestickPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dmaxAnchorExpression = (MExpression) value;
				setChildListener(dmaxAnchorExpression);
				JRExpression expression = (JRExpression) dmaxAnchorExpression.getValue();
				jrElement.setDomainAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignCandlestickPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
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
