/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.plot;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.property.descriptor.seriescolor.SeriesColorPropertyDescriptor;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TransparencyPropertyDescriptor;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

import net.sf.jasperreports.charts.type.PlotOrientationEnum;
import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.JRChartPlot.JRSeriesColor;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;

public class MChartPlot extends APropertyNode {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;
	
	private static NamedEnumPropertyDescriptor<PlotOrientationEnum> orientationD;

	public MChartPlot(JRChartPlot value) {
		super();
		setValue(value);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(
				JRBaseChartPlot.PROPERTY_BACKCOLOR,
				Messages.MChartPlot_backcolor, NullEnum.INHERITED);
		backcolorD.setDescription(Messages.MChartPlot_backcolor_description);
		desc.add(backcolorD);

		FloatPropertyDescriptor backAlphaD = new TransparencyPropertyDescriptor(
				JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA,
				Messages.MChartPlot_background_alpha_percent);
		backAlphaD
				.setDescription(Messages.MChartPlot_background_alpha_percent_description);
		desc.add(backAlphaD);

		FloatPropertyDescriptor foreAlphaD = new TransparencyPropertyDescriptor(
				JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA,
				Messages.MChartPlot_foreground_alpha_percent);
		foreAlphaD
				.setDescription(Messages.MChartPlot_foreground_alpha_percent_description);
		desc.add(foreAlphaD);

		orientationD = new NamedEnumPropertyDescriptor<PlotOrientationEnum>(
				JRBaseChartPlot.PROPERTY_ORIENTATION,
				Messages.MChartPlot_orientation,
				PlotOrientationEnum.HORIZONTAL, NullEnum.NULL);
		orientationD
				.setDescription(Messages.MChartPlot_orientation_description);
		desc.add(orientationD);

		SeriesColorPropertyDescriptor scpd = new SeriesColorPropertyDescriptor(
				JRBaseChartPlot.PROPERTY_SERIES_COLORS,
				Messages.MChartPlot_series_colors);
		scpd.setDescription(Messages.MChartPlot_series_colors_description);
		desc.add(scpd);
		scpd.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#seriesColor"));

		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#plot");
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		
		defaultsMap.put(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA, new DefaultValue(true));
		defaultsMap.put(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA, new DefaultValue(true));
		defaultsMap.put(JRBaseChartPlot.PROPERTY_BACKCOLOR, new DefaultValue(true));
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRBaseChartPlot jrElement = (JRBaseChartPlot) getValue();
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getOwnBackcolor());
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA))
			return jrElement.getBackgroundAlphaFloat();
		if (id.equals(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA))
			return jrElement.getForegroundAlphaFloat();
		if (id.equals(JRBaseChartPlot.PROPERTY_ORIENTATION))
			return orientationD.getIntValue(jrElement.getOrientationValue());
		if (id.equals(JRBaseChartPlot.PROPERTY_SERIES_COLORS))
			return jrElement.getSeriesColors();

		return null;
	}

	public Object getPropertyActualValue(Object id) {
		JRBaseChartPlot jrElement = (JRBaseChartPlot) getValue();
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getBackcolor());
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA)) {
			Float alpha = jrElement.getBackgroundAlphaFloat();
			return alpha != null ? alpha : 1.0f;
		}
		if (id.equals(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA)) {
			Float alpha = jrElement.getForegroundAlphaFloat();
			return alpha != null ? alpha : 1.0f;
		}
		return super.getPropertyActualValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRBaseChartPlot jrElement = (JRBaseChartPlot) getValue();
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKCOLOR)) {
			if (value == null|| value instanceof AlfaRGB){
				jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
			}
		} else if (id.equals(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA))
			jrElement.setBackgroundAlpha((Float) value);
		else if (id.equals(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA))
			jrElement.setForegroundAlpha((Float) value);
		else if (id.equals(JRBaseChartPlot.PROPERTY_ORIENTATION))
			jrElement.setOrientation(orientationD.getEnumValue(value));
		else if (id.equals(JRBaseChartPlot.PROPERTY_SERIES_COLORS)) {
			jrElement.setSeriesColors((Collection<JRSeriesColor>) value);
			// jrElement.clearSeriesColors();
			// if (value instanceof SortedSet) {
			// SortedSet<JRSeriesColor> set = (SortedSet<JRSeriesColor>) value;
			// for (JRSeriesColor sc : set) {
			// jrElement.addSeriesColor(sc);
			// }
			// }
		}
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

	public String getDisplayText() {
		return null;
	}

}
