/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme;

import java.awt.Stroke;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.ChartSettings;
import net.sf.jasperreports.chartthemes.simple.FileImageProvider;
import net.sf.jasperreports.chartthemes.simple.ImageProvider;
import net.sf.jasperreports.chartthemes.simple.PaintProvider;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.components.chart.model.enums.JFreeChartAlignEnum;
import com.jaspersoft.studio.components.chart.model.theme.imageprovider.ImageProviderPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.paintprovider.PaintProviderPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.stroke.StrokePropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TransparencyPropertyDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class MChartSettings extends APropertyNode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MChartSettings(MChartThemeSettings parent, ChartSettings cs) {
		super(parent, -1);
		setValue(cs);
	}

	@Override
	public ChartSettings getValue() {
		return (ChartSettings) super.getValue();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		return "Chart";
	}

	private IPropertyDescriptor[] descriptors;
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		PropertyDescriptor pd = new CheckBoxPropertyDescriptor(ChartSettings.PROPERTY_textAntiAlias, "Text Anti Alias");
		pd.setDescription("Text anti alias");
		desc.add(pd);

		pd = new CheckBoxPropertyDescriptor(ChartSettings.PROPERTY_antiAlias, "Anti Alias");
		pd.setDescription("Anti alias");
		desc.add(pd);

		pd = new CheckBoxPropertyDescriptor(ChartSettings.PROPERTY_borderVisible, "Visible");
		pd.setDescription("Border Visible");
		pd.setCategory(Messages.common_borders);
		desc.add(pd);

		pd = new TransparencyPropertyDescriptor(ChartSettings.PROPERTY_backgroundImageAlpha, "Image Alpha");
		pd.setDescription("Background Image Alpha");
		pd.setCategory(Messages.common_background);
		desc.add(pd);

		bia = new JSSEnumPropertyDescriptor(ChartSettings.PROPERTY_backgroundImageAlignment, "Image Alignment", JFreeChartAlignEnum.class, NullEnum.NOTNULL);
		bia.setDescription("Background Image Alignment");
		bia.setCategory(Messages.common_background);
		desc.add(bia);

		PadUtil.createPropertyDescriptors(desc, defaultsMap);

		pd = new PaintProviderPropertyDescriptor(ChartSettings.PROPERTY_backgroundPaint, "Paint");
		pd.setDescription("Background paint");
		pd.setCategory(Messages.common_background);
		desc.add(pd);

		pd = new PaintProviderPropertyDescriptor(ChartSettings.PROPERTY_borderPaint, "Paint");
		pd.setDescription("Border paint");
		pd.setCategory(Messages.common_borders);
		desc.add(pd);

		pd = new ImageProviderPropertyDescriptor(ChartSettings.PROPERTY_backgroundImage, "Image");
		pd.setDescription("Background Image");
		pd.setCategory(Messages.common_background);
		desc.add(pd);

		pd = new StrokePropertyDescriptor(ChartSettings.PROPERTY_borderStroke, "Stroke");
		pd.setDescription("Border Stroke");
		pd.setCategory(Messages.common_borders);
		desc.add(pd);

		defaultsMap.put(ChartSettings.PROPERTY_textAntiAlias, Boolean.TRUE);
		defaultsMap.put(ChartSettings.PROPERTY_antiAlias, Boolean.TRUE);
		defaultsMap.put(ChartSettings.PROPERTY_borderVisible, Boolean.TRUE);
		defaultsMap.put(ChartSettings.PROPERTY_backgroundImageAlignment, JFreeChartAlignEnum.NORTH_WEST);
	}

	private static JSSEnumPropertyDescriptor bia;

	@Override
	public Object getPropertyValue(Object id) {
		ChartSettings cs = getValue();
		if (id.equals(ChartSettings.PROPERTY_textAntiAlias))
			return cs.getTextAntiAlias();
		if (id.equals(ChartSettings.PROPERTY_antiAlias))
			return cs.getAntiAlias();
		if (id.equals(ChartSettings.PROPERTY_borderVisible))
			return cs.getBorderVisible();
		if (id.equals(ChartSettings.PROPERTY_backgroundImageAlpha))
			return cs.getBackgroundImageAlpha();
		if (id.equals(ChartSettings.PROPERTY_backgroundImageAlignment))
			return Misc.nvl(cs.getBackgroundImageAlignment(), new Integer(JFreeChartAlignEnum.NORTH_WEST.getValue()));

		Object pad = PadUtil.getPropertyValue(id, cs.getPadding());
		if (pad != null)
			return pad;
		if (id.equals(ChartSettings.PROPERTY_backgroundPaint))
			return cs.getBackgroundPaint();
		if (id.equals(ChartSettings.PROPERTY_borderPaint))
			return cs.getBorderPaint();
		if (id.equals(ChartSettings.PROPERTY_backgroundImage)) {
			ImageProvider bimg = cs.getBackgroundImage();
			if (bimg != null && bimg instanceof FileImageProvider)
				return ((FileImageProvider) bimg).getFile();
			return null;
		}
		if (id.equals(ChartSettings.PROPERTY_borderStroke))
			return cs.getBorderStroke();

		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		ChartSettings cs = getValue();
		if (id.equals(ChartSettings.PROPERTY_textAntiAlias))
			cs.setTextAntiAlias((Boolean) value);
		else if (id.equals(ChartSettings.PROPERTY_antiAlias))
			cs.setAntiAlias((Boolean) value);
		else if (id.equals(ChartSettings.PROPERTY_borderVisible))
			cs.setBorderVisible((Boolean) value);
		else if (id.equals(ChartSettings.PROPERTY_backgroundImageAlpha))
			cs.setBackgroundImageAlpha((Float) value);
		else if (id.equals(ChartSettings.PROPERTY_backgroundImageAlignment))
			cs.setBackgroundImageAlignment((Integer) value);
		else if (id.equals(ChartSettings.PROPERTY_backgroundPaint))
			cs.setBackgroundPaint((PaintProvider) value);
		else if (id.equals(ChartSettings.PROPERTY_borderPaint))
			cs.setBorderPaint((PaintProvider) value);
		else if (id.equals(ChartSettings.PROPERTY_backgroundImage))
			cs.setBackgroundImage(new FileImageProvider((String) value));
		else if (id.equals(ChartSettings.PROPERTY_borderStroke))
			cs.setBorderStroke((Stroke) value);

		RectangleInsets ri = PadUtil.setPropertyValue(id, value, cs.getPadding());
		if (ri != null)
			cs.setPadding(ri);
	}
}
