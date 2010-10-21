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

import net.sf.jasperreports.charts.JRMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignDataRange;
import net.sf.jasperreports.charts.design.JRDesignMeterPlot;
import net.sf.jasperreports.charts.design.JRDesignValueDisplay;
import net.sf.jasperreports.charts.type.MeterShapeEnum;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExpression;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.IntegerPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;
import com.jaspersoft.studio.utils.EnumHelper;

public class MMeterPlot extends MChartPlot {
	public MMeterPlot(JRMeterPlot value) {
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

		ColorPropertyDescriptor meterBackgroundColorD = new ColorPropertyDescriptor(
				JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR, "Meter Background Color", NullEnum.NULL);
		meterBackgroundColorD.setDescription("Meter background color.");
		desc.add(meterBackgroundColorD);

		ColorPropertyDescriptor tickColorD = new ColorPropertyDescriptor(JRDesignMeterPlot.PROPERTY_TICK_COLOR,
				"Tick Color", NullEnum.NULL);
		tickColorD.setDescription("Tick color.");
		desc.add(tickColorD);

		ColorPropertyDescriptor needleColorD = new ColorPropertyDescriptor(JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR,
				"Needle Color", NullEnum.NULL);
		needleColorD.setDescription("Needle color.");
		desc.add(needleColorD);

		ColorPropertyDescriptor valueColorD = new ColorPropertyDescriptor(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "."
				+ JRDesignValueDisplay.PROPERTY_COLOR, "Value Color", NullEnum.NULL);
		valueColorD.setDescription("Value color.");
		desc.add(valueColorD);

		FontPropertyDescriptor tickLabelFontD = new FontPropertyDescriptor(JRDesignMeterPlot.PROPERTY_TICK_LABEL_FONT,
				"Tick Label Font");
		tickLabelFontD.setDescription("Tick Label Font.");
		desc.add(tickLabelFontD);

		FontPropertyDescriptor valueFontD = new FontPropertyDescriptor(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "."
				+ JRDesignValueDisplay.PROPERTY_FONT, "Value Font");
		valueFontD.setDescription("Value Font.");
		desc.add(valueFontD);

		IntegerPropertyDescriptor meterAngleD = new IntegerPropertyDescriptor(JRDesignMeterPlot.PROPERTY_METER_ANGLE,
				"Meter Angle");
		meterAngleD.setDescription("Meter angle.");
		desc.add(meterAngleD);

		DoublePropertyDescriptor tickIntervalD = new DoublePropertyDescriptor(JRDesignMeterPlot.PROPERTY_TICK_INTERVAL,
				"Tick Interval");
		tickIntervalD.setDescription("Tick Interval.");
		desc.add(tickIntervalD);

		ComboBoxPropertyDescriptor positionTypeD = new ComboBoxPropertyDescriptor(JRDesignMeterPlot.PROPERTY_SHAPE,
				"Shape", EnumHelper.getEnumNames(MeterShapeEnum.values(), NullEnum.NOTNULL));
		positionTypeD.setDescription("Shape.");
		desc.add(positionTypeD);

		JRExpressionPropertyDescriptor dataRangeHighExprD = new JRExpressionPropertyDescriptor(
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "." + JRDesignDataRange.PROPERTY_HIGH_EXPRESSION,
				"Data Range High Expression");
		dataRangeHighExprD.setDescription("Data Range High Expression.");
		desc.add(dataRangeHighExprD);

		JRExpressionPropertyDescriptor dataRangeLowExprD = new JRExpressionPropertyDescriptor(
				JRDesignMeterPlot.PROPERTY_DATA_RANGE + "." + JRDesignDataRange.PROPERTY_LOW_EXPRESSION,
				"Data Range Low Expression");
		dataRangeLowExprD.setDescription("Data Range Low Expression.");
		desc.add(dataRangeLowExprD);

		NTextPropertyDescriptor unitsD = new NTextPropertyDescriptor(JRDesignMeterPlot.PROPERTY_UNITS, "Units");
		unitsD.setDescription("Units");
		desc.add(unitsD);

		NTextPropertyDescriptor maskD = new NTextPropertyDescriptor(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "."
				+ JRDesignValueDisplay.PROPERTY_MASK, "Value Mask");
		maskD.setDescription("Units");
		desc.add(maskD);

	}

	private MExpression drhaExpression;
	private MExpression drlExpression;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignMeterPlot jrElement = (JRDesignMeterPlot) getValue();
		if (id.equals(JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getMeterBackgroundColor());
		if (id.equals(JRDesignMeterPlot.PROPERTY_TICK_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getTickColor());
		if (id.equals(JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getNeedleColor());
		if (id.equals(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." + JRDesignValueDisplay.PROPERTY_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getValueDisplay().getColor());

		if (id.equals(JRDesignMeterPlot.PROPERTY_METER_ANGLE))
			return jrElement.getMeterAngleInteger();
		if (id.equals(JRDesignMeterPlot.PROPERTY_TICK_INTERVAL))
			return jrElement.getTickIntervalDouble();

		if (id.equals(JRDesignMeterPlot.PROPERTY_SHAPE))
			return EnumHelper.getValue(jrElement.getShapeValue(), 0, false);
		if (id.equals(JRDesignMeterPlot.PROPERTY_UNITS))
			return jrElement.getUnits();
		if (id.equals(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." + JRDesignValueDisplay.PROPERTY_MASK))
			return jrElement.getValueDisplay().getMask();

		if (id.equals(JRDesignMeterPlot.PROPERTY_DATA_RANGE + "." + JRDesignDataRange.PROPERTY_HIGH_EXPRESSION)) {
			if (drhaExpression == null)
				drhaExpression = new MExpression(jrElement.getDataRange().getHighExpression());
			return drhaExpression;
		}
		if (id.equals(JRDesignMeterPlot.PROPERTY_DATA_RANGE + "." + JRDesignDataRange.PROPERTY_LOW_EXPRESSION)) {
			if (drlExpression == null)
				drlExpression = new MExpression(jrElement.getDataRange().getLowExpression());
			return drlExpression;
		}
		if (id.equals(JRDesignMeterPlot.PROPERTY_TICK_LABEL_FONT)) {
			if (tlFont == null)
				tlFont = new MFont(jrElement.getTickLabelFont());
			return tlFont;
		}
		if (id.equals(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." + JRDesignValueDisplay.PROPERTY_FONT)) {
			if (vdFont == null)
				vdFont = new MFont(jrElement.getValueDisplay().getFont());
			return vdFont;
		}
		return super.getPropertyValue(id);
	}

	private MFont tlFont;
	private MFont vdFont;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignMeterPlot jrElement = (JRDesignMeterPlot) getValue();
		if (id.equals(JRDesignMeterPlot.PROPERTY_METER_BACKGROUND_COLOR) && value instanceof RGB)
			jrElement.setMeterBackgroundColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignMeterPlot.PROPERTY_TICK_COLOR) && value instanceof RGB)
			jrElement.setTickColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignMeterPlot.PROPERTY_NEEDLE_COLOR) && value instanceof RGB)
			jrElement.setNeedleColor(Colors.getAWT4SWTRGBColor((RGB) value));
		else if (id.equals(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." + JRDesignValueDisplay.PROPERTY_COLOR)
				&& value instanceof RGB) {
			JRDesignValueDisplay jrDesignValueDisplay = new JRDesignValueDisplay(jrElement.getValueDisplay(),
					jrElement.getChart());
			jrDesignValueDisplay.setColor(Colors.getAWT4SWTRGBColor((RGB) value));
			jrElement.setValueDisplay(jrDesignValueDisplay);
		} else if (id.equals(JRDesignMeterPlot.PROPERTY_METER_ANGLE))
			jrElement.setMeterAngle((Integer) value);
		else if (id.equals(JRDesignMeterPlot.PROPERTY_TICK_INTERVAL))
			jrElement.setTickInterval((Double) value);

		else if (id.equals(JRDesignMeterPlot.PROPERTY_SHAPE))
			try {
				jrElement.setShape((MeterShapeEnum) EnumHelper.getSetValue(MeterShapeEnum.values(), value, 0, false));
			} catch (JRException e) {
				e.printStackTrace();
			}
		else if (id.equals(JRDesignMeterPlot.PROPERTY_UNITS))
			jrElement.setUnits((String) value);
		else if (id.equals(JRDesignMeterPlot.PROPERTY_VALUE_DISPLAY + "." + JRDesignValueDisplay.PROPERTY_MASK)) {
			JRDesignValueDisplay jrDesignValueDisplay = new JRDesignValueDisplay(jrElement.getValueDisplay(),
					jrElement.getChart());
			jrDesignValueDisplay.setMask((String) value);
			jrElement.setValueDisplay(jrDesignValueDisplay);
		} else if (id.equals(JRDesignMeterPlot.PROPERTY_UNITS))
			jrElement.setUnits((String) value);
		else if (id.equals(JRDesignMeterPlot.PROPERTY_UNITS))
			jrElement.setUnits((String) value);
		else if (id.equals(JRDesignMeterPlot.PROPERTY_UNITS))
			jrElement.setUnits((String) value);

		else if (id.equals(JRDesignMeterPlot.PROPERTY_DATA_RANGE + "." + JRDesignDataRange.PROPERTY_HIGH_EXPRESSION)) {
			if (value instanceof MExpression) {
				drhaExpression = (MExpression) value;
				JRExpression expression = (JRExpression) drhaExpression.getValue();
				((JRDesignDataRange) jrElement.getDataRange()).setHighExpression(expression);
			}
		} else if (id.equals(JRDesignMeterPlot.PROPERTY_DATA_RANGE + "." + JRDesignDataRange.PROPERTY_LOW_EXPRESSION)) {
			if (value instanceof MExpression) {
				drlExpression = (MExpression) value;
				JRExpression expression = (JRExpression) drlExpression.getValue();
				((JRDesignDataRange) jrElement.getDataRange()).setLowExpression(expression);
			}
		} else
			super.setPropertyValue(id, value);
	}
}
