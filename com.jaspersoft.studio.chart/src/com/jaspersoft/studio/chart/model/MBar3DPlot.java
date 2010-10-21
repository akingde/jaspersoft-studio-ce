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
package com.jaspersoft.studio.chart.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.JRBar3DPlot;
import net.sf.jasperreports.charts.design.JRDesignBar3DPlot;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.property.descriptor.PlotPropertyDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;

public class MBar3DPlot extends MChartPlot {
	public MBar3DPlot(JRBar3DPlot value) {
		super(value);
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
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR, "Category Axis Label Color", NullEnum.NULL);
		catAxisLabelColorD.setDescription("Category axis label color.");
		desc.add(catAxisLabelColorD);

		JRExpressionPropertyDescriptor catAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION, "Category Axis Label Expression");
		catAxisLabelExprD.setDescription("Category axis label expression.");
		desc.add(catAxisLabelExprD);

		FontPropertyDescriptor catAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_FONT, "Category Axis Label Font");
		catAxisLabelFontD.setDescription("Category Axis Label Font.");
		desc.add(catAxisLabelFontD);

		ColorPropertyDescriptor catAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR, "Category Axis Tick Label Color", NullEnum.NULL);
		catAxisTickLabelColorD.setDescription("Category axis tick label color.");
		desc.add(catAxisTickLabelColorD);

		FontPropertyDescriptor catAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_FONT, "Category Axis Tick Label Font");
		catAxisTickLabelFontD.setDescription("Category Axis Tick Label Font.");
		desc.add(catAxisTickLabelFontD);

		ColorPropertyDescriptor catAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR, "Category Axis Line Color", NullEnum.NULL);
		catAxisLineColorD.setDescription("Category axis line color.");
		desc.add(catAxisLineColorD);

		ColorPropertyDescriptor valAxisLabelColorD = new ColorPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR, "Value Axis Label Color", NullEnum.NULL);
		valAxisLabelColorD.setDescription("Value axis label color.");
		desc.add(valAxisLabelColorD);

		JRExpressionPropertyDescriptor valAxisLabelExprD = new JRExpressionPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION, "Category Value Axis Label Expression");
		valAxisLabelExprD.setDescription("Category value axis label expression.");
		desc.add(valAxisLabelExprD);

		FontPropertyDescriptor valAxisLabelFontD = new FontPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_FONT, "Value Axis Label Font");
		valAxisLabelFontD.setDescription("Value Axis Label Font.");
		desc.add(valAxisLabelFontD);

		ColorPropertyDescriptor valAxisTickLabelColorD = new ColorPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR, "Value Axis Tick Label Color", NullEnum.NULL);
		valAxisTickLabelColorD.setDescription("Value axis tick label color.");
		desc.add(valAxisTickLabelColorD);

		FontPropertyDescriptor valAxisTickLabelFontD = new FontPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT, "Value Axis Tick Label Font");
		valAxisTickLabelFontD.setDescription("Value Axis Tick Label Font.");
		desc.add(valAxisTickLabelFontD);

		PlotPropertyDescriptor itemLabelD = new PlotPropertyDescriptor(JRDesignBar3DPlot.PROPERTY_ITEM_LABEL, "Item Label");
		itemLabelD.setDescription("Item Label.");
		desc.add(itemLabelD);

		ColorPropertyDescriptor valAxisLineColorD = new ColorPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LINE_COLOR, "Value Axis Line Color", NullEnum.NULL);
		valAxisLineColorD.setDescription("Value axis line color.");
		desc.add(valAxisLineColorD);

		JRExpressionPropertyDescriptor rangeAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION, "Range Axis Minvalue Expression");
		rangeAxisMinExprD.setDescription("Range axis minvalue expression.");
		desc.add(rangeAxisMinExprD);

		JRExpressionPropertyDescriptor rangeAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION, "Range Axis Maxvalue Expression");
		rangeAxisMaxExprD.setDescription("Range axis maxvalue expression.");
		desc.add(rangeAxisMaxExprD);

		JRExpressionPropertyDescriptor domainAxisMinExprD = new JRExpressionPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION, "Domain Axis Minvalue Expression");
		domainAxisMinExprD.setDescription("Domain axis minvalue expression.");
		desc.add(domainAxisMinExprD);

		JRExpressionPropertyDescriptor domainAxisMaxExprD = new JRExpressionPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION, "Domain Axis Maxvalue Expression");
		domainAxisMaxExprD.setDescription("Domain axis maxvalue expression.");
		desc.add(domainAxisMaxExprD);

		CheckBoxPropertyDescriptor catAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS, "Category Axis Vertical Tick Labels",
				NullEnum.NOTNULL);
		catAxisVertTickLabelD.setDescription("Category Axis Vertical Tick Labels.");
		desc.add(catAxisVertTickLabelD);

		CheckBoxPropertyDescriptor valAxisVertTickLabelD = new CheckBoxPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS, "Value Axis Vertical Tick Labels", NullEnum.NOTNULL);
		valAxisVertTickLabelD.setDescription("Value Axis Vertical Tick Labels.");
		desc.add(valAxisVertTickLabelD);

		CheckBoxPropertyDescriptor showLabelsD = new CheckBoxPropertyDescriptor(JRDesignBar3DPlot.PROPERTY_SHOW_LABELS,
				"Show Labels", NullEnum.NULL);
		showLabelsD.setDescription("Show Labels.");
		desc.add(showLabelsD);

		NTextPropertyDescriptor catAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK, "Category Axis Tick Label Mask");
		catAxisTickLabelMaskD.setDescription("Category Axis Tick Label Mask");
		desc.add(catAxisTickLabelMaskD);

		NTextPropertyDescriptor valAxisTickLabelMaskD = new NTextPropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK, "Value Axis Tick Label Mask");
		valAxisTickLabelMaskD.setDescription("Value Axis Tick Label Mask");
		desc.add(valAxisTickLabelMaskD);

		DoublePropertyDescriptor catAxisTickLabelRotation = new DoublePropertyDescriptor(
				JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION, "Category Axis Tick Label Rotation");
		catAxisTickLabelRotation.setDescription("Category Axis Tick Label Rotation.");
		desc.add(catAxisTickLabelRotation);

		DoublePropertyDescriptor xoffsetD = new DoublePropertyDescriptor(JRDesignBar3DPlot.PROPERTY_X_OFFSET, "X Offset");
		xoffsetD.setDescription("X offset.");
		desc.add(xoffsetD);

		DoublePropertyDescriptor yoffsetD = new DoublePropertyDescriptor(JRDesignBar3DPlot.PROPERTY_Y_OFFSET, "Y Offset");
		yoffsetD.setDescription("Y offset.");
		desc.add(yoffsetD);

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
		JRDesignBar3DPlot jrElement = (JRDesignBar3DPlot) getValue();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnCategoryAxisLabelColor());
		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnCategoryAxisTickLabelColor());
		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnCategoryAxisLineColor());
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLabelColor());
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisTickLabelColor());
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LINE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnValueAxisLineColor());

		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getCategoryAxisVerticalTickLabels();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			return jrElement.getValueAxisVerticalTickLabels();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_SHOW_LABELS))
			return jrElement.getShowLabels();

		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK))
			return jrElement.getCategoryAxisTickLabelMask();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			return jrElement.getValueAxisTickLabelMask();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION))
			return jrElement.getCategoryAxisTickLabelRotation();

		if (id.equals(JRDesignBar3DPlot.PROPERTY_X_OFFSET))
			return jrElement.getXOffsetDouble();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_Y_OFFSET))
			return jrElement.getYOffsetDouble();

		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION)) {
			if (ceAnchorExpression == null)
				ceAnchorExpression = new MExpression(jrElement.getCategoryAxisLabelExpression());
			return ceAnchorExpression;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (veAnchorExpression == null)
				veAnchorExpression = new MExpression(jrElement.getValueAxisLabelExpression());
			return veAnchorExpression;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (rmaxAnchorExpression == null)
				rmaxAnchorExpression = new MExpression(jrElement.getRangeAxisMaxValueExpression());
			return rmaxAnchorExpression;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (rminAnchorExpression == null)
				rminAnchorExpression = new MExpression(jrElement.getRangeAxisMinValueExpression());
			return rminAnchorExpression;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (dmaxAnchorExpression == null)
				dmaxAnchorExpression = new MExpression(jrElement.getDomainAxisMaxValueExpression());
			return dmaxAnchorExpression;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (dminAnchorExpression == null)
				dminAnchorExpression = new MExpression(jrElement.getDomainAxisMinValueExpression());
			return dminAnchorExpression;
		}

		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_FONT)) {
			if (clFont == null)
				clFont = new MFont(jrElement.getCategoryAxisLabelFont());
			return clFont;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_FONT)) {
			if (ctFont == null)
				ctFont = new MFont(jrElement.getCategoryAxisTickLabelFont());
			return ctFont;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_FONT)) {
			if (vlFont == null)
				vlFont = new MFont(jrElement.getValueAxisLabelFont());
			return vlFont;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_FONT)) {
			if (vtFont == null)
				vtFont = new MFont(jrElement.getValueAxisTickLabelFont());
			return vtFont;
		}
		if (id.equals(JRDesignBar3DPlot.PROPERTY_ITEM_LABEL)) {
			if (ilFont == null)
				ilFont = new MChartItemLabel(jrElement.getItemLabel());
			return ilFont;
		}

		return super.getPropertyValue(id);
	}

	private MFont clFont;
	private MFont ctFont;
	private MFont vlFont;
	private MFont vtFont;
	private MChartItemLabel ilFont;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignBar3DPlot jrElement = (JRDesignBar3DPlot) getValue();
		if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setCategoryAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setCategoryAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setCategoryAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_COLOR) && value instanceof RGB)
			jrElement.setValueAxisTickLabelColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LINE_COLOR) && value instanceof RGB)
			jrElement.setValueAxisLineColor(Colors.getAWT4SWTRGBColor((RGB) value));

		else if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setCategoryAxisVerticalTickLabels((Boolean) value);
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_VERTICAL_TICK_LABELS))
			jrElement.setValueAxisVerticalTickLabels((Boolean) value);
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_SHOW_LABELS))
			jrElement.setShowLabels((Boolean) value);

		else if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_MASK))
			jrElement.setCategoryAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_TICK_LABEL_MASK))
			jrElement.setValueAxisTickLabelMask((String) value);
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_TICK_LABEL_ROTATION))
			jrElement.setCategoryAxisTickLabelRotation((Double) value);

		else if (id.equals(JRDesignBar3DPlot.PROPERTY_X_OFFSET))
			jrElement.setXOffset((Double) value);
		else if (id.equals(JRDesignBar3DPlot.PROPERTY_Y_OFFSET))
			jrElement.setYOffset((Double) value);

		else if (id.equals(JRDesignBar3DPlot.PROPERTY_CATEGORY_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				ceAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) ceAnchorExpression.getValue();
				jrElement.setCategoryAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignBar3DPlot.PROPERTY_VALUE_AXIS_LABEL_EXPRESSION)) {
			if (value instanceof MExpression) {
				veAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) veAnchorExpression.getValue();
				jrElement.setValueAxisLabelExpression(expression);
			}
		} else if (id.equals(JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rmaxAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) rmaxAnchorExpression.getValue();
				jrElement.setRangeAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignBar3DPlot.PROPERTY_RANGE_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				rminAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) rminAnchorExpression.getValue();
				jrElement.setRangeAxisMinValueExpression(expression);
			}
		} else if (id.equals(JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MAXVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dmaxAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dmaxAnchorExpression.getValue();
				jrElement.setDomainAxisMaxValueExpression(expression);
			}
		} else if (id.equals(JRDesignBar3DPlot.PROPERTY_DOMAIN_AXIS_MINVALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				dminAnchorExpression = (MExpression) value;
				JRExpression expression = (JRExpression) dminAnchorExpression.getValue();
				jrElement.setDomainAxisMinValueExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);

	}
}
