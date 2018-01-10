/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme;

import java.beans.PropertyChangeEvent;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.enums.JFreeChartHorizontalAlignmentEnum;
import com.jaspersoft.studio.components.chart.model.enums.JFreeChartVerticalAlignmentEnum;
import com.jaspersoft.studio.components.chart.model.theme.paintprovider.PaintProviderPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.chartthemes.simple.BlockFrameProvider;
import net.sf.jasperreports.chartthemes.simple.LegendSettings;
import net.sf.jasperreports.chartthemes.simple.PaintProvider;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

public class MLegendSettings extends APropertyNode {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static NamedEnumPropertyDescriptor<EdgeEnum> posD;
	
	private static NamedEnumPropertyDescriptor<JFreeChartHorizontalAlignmentEnum> hp;
	
	private static NamedEnumPropertyDescriptor<JFreeChartVerticalAlignmentEnum> vp;
	
	private IPropertyDescriptor[] descriptors;
	
	private MFont clFont;

	public MLegendSettings(MChartThemeSettings parent, LegendSettings cts) {
		super(parent, -1);
		setValue(cts);
	}

	@Override
	public LegendSettings getValue() {
		return (LegendSettings) super.getValue();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		return "Legend"; //$NON-NLS-1$
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		PropertyDescriptor pd = new CheckBoxPropertyDescriptor(
				LegendSettings.PROPERTY_showLegend,
				Messages.MLegendSettings_showLegendTitle);
		pd.setDescription(Messages.MLegendSettings_showLegendDescription);
		desc.add(pd);

		pd = new FontPropertyDescriptor(LegendSettings.PROPERTY_font, "Font");
		pd.setDescription("Font");
		desc.add(pd);

		posD = new NamedEnumPropertyDescriptor<EdgeEnum>(
				LegendSettings.PROPERTY_position,
				Messages.MLegendSettings_positionTitle, EdgeEnum.BOTTOM,
				NullEnum.NULL);
		posD.setDescription(Messages.MLegendSettings_positionDescription);
		desc.add(posD);

		hp = new NamedEnumPropertyDescriptor<JFreeChartHorizontalAlignmentEnum>(
				LegendSettings.PROPERTY_horizontalAlignment,
				Messages.MLegendSettings_legendHAlignTitle,
				JFreeChartHorizontalAlignmentEnum.CENTER, NullEnum.NULL);
		hp.setDescription(Messages.MLegendSettings_legendHAlignDescription);
		desc.add(hp);

		vp = new NamedEnumPropertyDescriptor<JFreeChartVerticalAlignmentEnum>(
				LegendSettings.PROPERTY_verticalAlignment,
				Messages.MLegendSettings_legendVAlignTitle,
				JFreeChartVerticalAlignmentEnum.BOTTOM, NullEnum.NULL);
		vp.setDescription(Messages.MLegendSettings_legendVAlignDescription);
		desc.add(vp);

		PadUtil.createPropertyDescriptors(desc);

		pd = new PaintProviderPropertyDescriptor(
				LegendSettings.PROPERTY_foregroundPaint,
				Messages.MLegendSettings_legendForegroundColorTitle);
		pd.setDescription(Messages.MLegendSettings_legendForegroundColorDescription);
		desc.add(pd);

		pd = new PaintProviderPropertyDescriptor(
				LegendSettings.PROPERTY_backgroundPaint,
				Messages.MLegendSettings_legendBackgroundColorTitle);
		pd.setDescription(Messages.MLegendSettings_legendBackgroundColorDescription);
		desc.add(pd);

		PadUtil.createBlockFramePropertyDescriptors(desc, "Block Frame"); //$NON-NLS-1$

		setHelpPrefix(
				desc,
				"net.sf.jasperreports.doc/docs/sample.reference/chartthemes/index.html#chartthemes"); //$NON-NLS-1$
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(LegendSettings.PROPERTY_backgroundPaint, new DefaultValue(null, true));
		defaultsMap.put(LegendSettings.PROPERTY_foregroundPaint, new DefaultValue(null, true));

		defaultsMap.put(LegendSettings.PROPERTY_showLegend, new DefaultValue(true, false));
		
		int positionValue = NamedEnumPropertyDescriptor.getIntValue(NullEnum.NULL, EdgeEnum.TOP);		
		defaultsMap.put(LegendSettings.PROPERTY_position, new DefaultValue(positionValue, true));
		
		int horizontalValue = NamedEnumPropertyDescriptor.getIntValue(NullEnum.NULL, JFreeChartHorizontalAlignmentEnum.LEFT);
		defaultsMap.put(LegendSettings.PROPERTY_horizontalAlignment, new DefaultValue(horizontalValue, true));
		
		int vertivalValue = NamedEnumPropertyDescriptor.getIntValue(NullEnum.NULL, JFreeChartVerticalAlignmentEnum.TOP);
		defaultsMap.put(LegendSettings.PROPERTY_verticalAlignment, new DefaultValue(vertivalValue, true));
		
		PadUtil.createDefaults("", defaultsMap);
		
		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		LegendSettings ts = getValue();
		if (id.equals(LegendSettings.PROPERTY_showLegend))
			return Misc.nvl(ts.getShowLegend(), Boolean.TRUE);
		if (id.equals(LegendSettings.PROPERTY_font)) {
			clFont = MFontUtil.getMFont(clFont, ts.getFont(), null, this);
			return clFont;
		}
		if (id.equals(LegendSettings.PROPERTY_position))
			return posD.getIntValue(ts.getPositionValue());
		if (id.equals(LegendSettings.PROPERTY_horizontalAlignment))
			return hp.getIntValue(JFreeChartHorizontalAlignmentEnum.getValue(ts
					.getHorizontalAlignment()));
		if (id.equals(LegendSettings.PROPERTY_verticalAlignment))
			return vp.getIntValue(JFreeChartVerticalAlignmentEnum.getValue(ts
					.getVerticalAlignment()));
		if (id.equals(LegendSettings.PROPERTY_backgroundPaint))
			return ts.getBackgroundPaint();
		if (id.equals(LegendSettings.PROPERTY_foregroundPaint))
			return ts.getForegroundPaint();
		
		Object pad = PadUtil.getPropertyValue(id, ts.getPadding());
		if (pad != null)
			return pad;
		
		BlockFrameProvider bf = ts.getFrame();
		pad = PadUtil.getBlockFrameValue(id, bf);
		if (pad != null)
			return pad;
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		LegendSettings ts = getValue();
		if (id.equals(LegendSettings.PROPERTY_showLegend))
			ts.setShowLegend((Boolean) value);
		else if (id.equals(LegendSettings.PROPERTY_font))
			ts.setFont(MFontUtil.setMFont(value));
		else if (id.equals(LegendSettings.PROPERTY_position))
			ts.setPosition(posD.getEnumValue(value));
		else if (id.equals(LegendSettings.PROPERTY_horizontalAlignment))
			ts.setHorizontalAlignment(hp.getEnumValue(value)
					.getJFreeChartValue());
		else if (id.equals(LegendSettings.PROPERTY_verticalAlignment))
			ts.setVerticalAlignment(vp.getEnumValue(value).getJFreeChartValue());
		else if (id.equals(LegendSettings.PROPERTY_backgroundPaint))
			ts.setBackgroundPaint((PaintProvider) value);
		else if (id.equals(LegendSettings.PROPERTY_foregroundPaint))
			ts.setForegroundPaint((PaintProvider) value);

		RectangleInsets ri = PadUtil.setPropertyValue(id, value,
				ts.getPadding());
		if (ri != null){
			ts.setPadding(ri);
		} else {
			PadUtil.setFramePropertyValue(id, value, ts);
		}
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		super.propertyChange(evt);
	}
}
