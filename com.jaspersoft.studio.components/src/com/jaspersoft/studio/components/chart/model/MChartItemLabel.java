/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.JRItemLabel;
import net.sf.jasperreports.charts.design.JRDesignItemLabel;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

public class MChartItemLabel extends APropertyNode {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	public MChartItemLabel(JRItemLabel value) {
		super();
		setValue(value);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR, Messages.MChartItemLabel_background_color, NullEnum.NULL);
		backcolorD.setDescription(Messages.MChartItemLabel_background_color_description);
		desc.add(backcolorD);

		ColorPropertyDescriptor colorD = new ColorPropertyDescriptor(JRDesignItemLabel.PROPERTY_COLOR, Messages.MChartItemLabel_color, NullEnum.NULL);
		colorD.setDescription(Messages.MChartItemLabel_color_description);
		desc.add(colorD);

		FontPropertyDescriptor fontD = new FontPropertyDescriptor(JRDesignItemLabel.PROPERTY_FONT, Messages.MChartItemLabel_font);
		fontD.setDescription(Messages.MChartItemLabel_font_description);
		desc.add(fontD);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(JRDesignItemLabel.PROPERTY_FONT, new DefaultValue(true));
		defaultsMap.put(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR, new DefaultValue(true));
		defaultsMap.put(JRDesignItemLabel.PROPERTY_COLOR, new DefaultValue(true));
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
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRItemLabel jrElement = (JRItemLabel) getValue();
		if (id.equals(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getBackgroundColor());
		if (id.equals(JRDesignItemLabel.PROPERTY_COLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getColor());
		if (id.equals(JRDesignItemLabel.PROPERTY_FONT)) {
			vtFont = MFontUtil.getMFont(vtFont, jrElement.getFont(), null, this);
			return vtFont;
		}
		return null;
	}

	private MFont vtFont;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignItemLabel jrElement = (JRDesignItemLabel) getValue();
		if (id.equals(JRDesignItemLabel.PROPERTY_BACKGROUND_COLOR) && (value == null || value instanceof AlfaRGB))
			jrElement.setBackgroundColor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
		else if (id.equals(JRDesignItemLabel.PROPERTY_COLOR) && (value == null || value instanceof AlfaRGB))
			jrElement.setColor(Colors.getAWT4SWTRGBColor((AlfaRGB) value));
		else if (id.equals(JRDesignItemLabel.PROPERTY_FONT)) {
			jrElement.setFont(MFontUtil.setMFont(value));
		}
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

	public String getDisplayText() {
		return null;
	}
}
