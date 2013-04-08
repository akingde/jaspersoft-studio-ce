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

import net.sf.jasperreports.chartthemes.simple.FileImageProvider;
import net.sf.jasperreports.chartthemes.simple.ImageProvider;
import net.sf.jasperreports.chartthemes.simple.PaintProvider;
import net.sf.jasperreports.chartthemes.simple.PlotSettings;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.components.chart.model.enums.JFreeChartAlignEnum;
import com.jaspersoft.studio.components.chart.model.enums.JFreeChartPlotOrientationEnum;
import com.jaspersoft.studio.components.chart.model.theme.imageprovider.ImageProviderPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.paintprovider.PaintProviderPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.paintproviders.PaintProvidersPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.stroke.StrokePropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.strokes.StrokesPropertyDescriptor;
import com.jaspersoft.studio.components.chart.model.theme.util.PadUtil;
import com.jaspersoft.studio.jasper.CachedImageProvider;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DegreePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.TransparencyPropertyDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class MPlotSettings extends APropertyNode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MPlotSettings(MChartThemeSettings parent, PlotSettings ps) {
		super(parent, -1);
		setValue(ps);
	}

	@Override
	public PlotSettings getValue() {
		return (PlotSettings) super.getValue();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		return "Plot";
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
		PropertyDescriptor pd = new CheckBoxPropertyDescriptor(PlotSettings.PROPERTY_outlineVisible, "Visible");
		pd.setDescription("Outline Visible");
		pd.setCategory("Outline");
		desc.add(pd);

		pd = new CheckBoxPropertyDescriptor(PlotSettings.PROPERTY_domainGridlineVisible, "Visible");
		pd.setDescription("Domain Grid Line Visible");
		pd.setCategory("Domain Grid Line");
		desc.add(pd);

		pd = new CheckBoxPropertyDescriptor(PlotSettings.PROPERTY_rangeGridlineVisible, "Visible");
		pd.setDescription("Range Grid Line Visible");
		pd.setCategory("Range Grid Line");
		desc.add(pd);

		pd = new FontPropertyDescriptor(PlotSettings.PROPERTY_displayFont, "Display Font");
		pd.setDescription("Display Font");
		desc.add(pd);

		pd = new FontPropertyDescriptor(PlotSettings.PROPERTY_tickLabelFont, "Tick Label Font");
		pd.setDescription("Tick Label Font");
		desc.add(pd);

		pd = new TransparencyPropertyDescriptor(PlotSettings.PROPERTY_backgroundImageAlpha, "Alpha");
		pd.setDescription("Background  Alpha");
		pd.setCategory(Messages.common_background);
		desc.add(pd);

		pd = new TransparencyPropertyDescriptor(PlotSettings.PROPERTY_foregroundAlpha, "Foreground Alpha");
		pd.setDescription("Foreground  Alpha");
		desc.add(pd);

		pd = new DegreePropertyDescriptor(PlotSettings.PROPERTY_labelRotation, "Label Rotation");
		pd.setDescription("Label Rotation");
		desc.add(pd);

		bia = new JSSEnumPropertyDescriptor(PlotSettings.PROPERTY_backgroundImageAlignment, "Image Alignment", JFreeChartAlignEnum.class, NullEnum.NOTNULL);
		bia.setDescription("Background Image Alignment");
		bia.setCategory(Messages.common_background);
		desc.add(bia);

		orientation = new JSSEnumPropertyDescriptor(PlotSettings.PROPERTY_orientation, "Orientation", JFreeChartPlotOrientationEnum.class, NullEnum.NOTNULL);
		orientation.setDescription("Orientation");
		desc.add(orientation);

		pd = new PaintProviderPropertyDescriptor(PlotSettings.PROPERTY_backgroundPaint, "Paint");
		pd.setDescription("Background paint");
		pd.setCategory(Messages.common_background);
		desc.add(pd);

		pd = new PaintProviderPropertyDescriptor(PlotSettings.PROPERTY_outlinePaint, "Paint");
		pd.setDescription("Outline paint");
		pd.setCategory("Outline");
		desc.add(pd);

		pd = new PaintProviderPropertyDescriptor(PlotSettings.PROPERTY_domainGridlinePaint, "Paint");
		pd.setDescription("Domain Grid Line paint");
		pd.setCategory("Domain Grid Line");
		desc.add(pd);

		pd = new PaintProviderPropertyDescriptor(PlotSettings.PROPERTY_rangeGridlinePaint, "Paint");
		pd.setDescription("Range Grid Line paint");
		pd.setCategory("Range Grid Line");
		desc.add(pd);

		pd = new ImageProviderPropertyDescriptor(PlotSettings.PROPERTY_backgroundImage, "Image");
		pd.setDescription("Background Image");
		pd.setCategory(Messages.common_background);
		desc.add(pd);

		pd = new StrokePropertyDescriptor(PlotSettings.PROPERTY_outlineStroke, "Stroke");
		pd.setDescription("Outline Stroke");
		pd.setCategory("Outline");
		desc.add(pd);

		pd = new StrokePropertyDescriptor(PlotSettings.PROPERTY_domainGridlineStroke, "Stroke");
		pd.setDescription("Domain Grid Line  Stroke");
		pd.setCategory("Domain Grid Line");
		desc.add(pd);

		pd = new StrokePropertyDescriptor(PlotSettings.PROPERTY_rangeGridlineStroke, "Stroke");
		pd.setDescription("Range Grid Line Stroke");
		pd.setCategory("Range Grid Line");
		desc.add(pd);

		pd = new StrokesPropertyDescriptor(PlotSettings.PROPERTY_seriesStrokeSequence, "Stroke Sequence");
		pd.setDescription("Series Stroke Sequence");
		pd.setCategory("Series");
		desc.add(pd);

		pd = new StrokesPropertyDescriptor(PlotSettings.PROPERTY_seriesOutlineStrokeSequence, "Outline Stroke Sequence");
		pd.setDescription("Series Outline Stroke Sequence");
		pd.setCategory("Series");
		desc.add(pd);

		pd = new PaintProvidersPropertyDescriptor(PlotSettings.PROPERTY_seriesColorSequence, "Color Sequence");
		pd.setDescription("Series Color Sequence");
		pd.setCategory("Series");
		desc.add(pd);

		pd = new PaintProvidersPropertyDescriptor(PlotSettings.PROPERTY_seriesGradientPaintSequence, "Gradient Paint Sequence");
		pd.setDescription("Series Gradient Paint Sequence");
		pd.setCategory("Series");
		desc.add(pd);

		pd = new PaintProvidersPropertyDescriptor(PlotSettings.PROPERTY_seriesOutlinePaintSequence, "Outline Paint Sequence");
		pd.setDescription("Series Outline Paint Sequence");
		pd.setCategory("Series");
		desc.add(pd);

		PadUtil.createPropertyDescriptors(desc, defaultsMap);

		defaultsMap.put(PlotSettings.PROPERTY_backgroundPaint, null);
		defaultsMap.put(PlotSettings.PROPERTY_outlinePaint, null);
		defaultsMap.put(PlotSettings.PROPERTY_domainGridlinePaint, null);
		defaultsMap.put(PlotSettings.PROPERTY_rangeGridlinePaint, null);
		defaultsMap.put(PlotSettings.PROPERTY_backgroundPaint, null);
		defaultsMap.put(PlotSettings.PROPERTY_backgroundImage, null);
		defaultsMap.put(PlotSettings.PROPERTY_outlineStroke, null);
		defaultsMap.put(PlotSettings.PROPERTY_domainGridlineStroke, null);
		defaultsMap.put(PlotSettings.PROPERTY_rangeGridlineStroke, null);
		defaultsMap.put(PlotSettings.PROPERTY_seriesStrokeSequence, null);
		defaultsMap.put(PlotSettings.PROPERTY_seriesOutlineStrokeSequence, null);
		defaultsMap.put(PlotSettings.PROPERTY_seriesColorSequence, null);
		defaultsMap.put(PlotSettings.PROPERTY_seriesGradientPaintSequence, null);
		defaultsMap.put(PlotSettings.PROPERTY_seriesOutlinePaintSequence, null);

		defaultsMap.put(PlotSettings.PROPERTY_outlineVisible, Boolean.TRUE);
		defaultsMap.put(PlotSettings.PROPERTY_domainGridlineVisible, Boolean.TRUE);
		defaultsMap.put(PlotSettings.PROPERTY_rangeGridlineVisible, Boolean.TRUE);
		defaultsMap.put(PlotSettings.PROPERTY_backgroundImageAlignment, JFreeChartAlignEnum.TOP_LEFT);
		defaultsMap.put(PlotSettings.PROPERTY_orientation, JFreeChartPlotOrientationEnum.HORIZONTAL);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/sample.reference/chartthemes/index.html#chartthemes");
	}

	private static JSSEnumPropertyDescriptor orientation;
	private static JSSEnumPropertyDescriptor bia;
	private MFont dFont;
	private MFont tlFont;

	@Override
	public Object getPropertyValue(Object id) {
		PlotSettings ps = getValue();
		if (id.equals(PlotSettings.PROPERTY_outlineVisible))
			return ps.getOutlineVisible();
		if (id.equals(PlotSettings.PROPERTY_domainGridlineVisible))
			return ps.getDomainGridlineVisible();
		if (id.equals(PlotSettings.PROPERTY_rangeGridlineVisible))
			return ps.getRangeGridlineVisible();
		if (id.equals(PlotSettings.PROPERTY_displayFont)) {
			dFont = MFontUtil.getMFont(dFont, ps.getDisplayFont(), null, this);
			return dFont;
		}
		if (id.equals(PlotSettings.PROPERTY_tickLabelFont)) {
			tlFont = MFontUtil.getMFont(tlFont, ps.getTickLabelFont(), null, this);
			return tlFont;
		}
		if (id.equals(PlotSettings.PROPERTY_backgroundImageAlpha))
			return ps.getBackgroundAlpha();
		if (id.equals(PlotSettings.PROPERTY_labelRotation))
			return ps.getLabelRotation();
		if (id.equals(PlotSettings.PROPERTY_backgroundImageAlignment))
			return bia.getEnumValue(Misc.nvl(JFreeChartAlignEnum.getValue(ps.getBackgroundImageAlignment()), JFreeChartAlignEnum.TOP_LEFT));
		if (id.equals(PlotSettings.PROPERTY_orientation))
			return orientation.getEnumValue(JFreeChartPlotOrientationEnum.getValue(ps.getOrientation()));
		if (id.equals(PlotSettings.PROPERTY_backgroundPaint))
			return ps.getBackgroundPaint();
		if (id.equals(PlotSettings.PROPERTY_outlinePaint))
			return ps.getOutlinePaint();
		if (id.equals(PlotSettings.PROPERTY_domainGridlinePaint))
			return ps.getDomainGridlinePaint();
		if (id.equals(PlotSettings.PROPERTY_rangeGridlinePaint))
			return ps.getRangeGridlinePaint();
		if (id.equals(PlotSettings.PROPERTY_backgroundImage)) {
			ImageProvider bimg = ps.getBackgroundImage();
			if (bimg != null && bimg instanceof FileImageProvider)
				return ((FileImageProvider) bimg).getFile();
			return null;
		}
		if (id.equals(PlotSettings.PROPERTY_outlineStroke))
			return ps.getOutlineStroke();
		if (id.equals(PlotSettings.PROPERTY_domainGridlineStroke))
			return ps.getDomainGridlineStroke();
		if (id.equals(PlotSettings.PROPERTY_rangeGridlineStroke))
			return ps.getRangeGridlineStroke();
		if (id.equals(PlotSettings.PROPERTY_seriesStrokeSequence))
			return ps.getSeriesStrokeSequence();
		if (id.equals(PlotSettings.PROPERTY_seriesOutlineStrokeSequence))
			return ps.getSeriesOutlineStrokeSequence();
		if (id.equals(PlotSettings.PROPERTY_seriesColorSequence))
			return ps.getSeriesColorSequence();
		if (id.equals(PlotSettings.PROPERTY_seriesGradientPaintSequence))
			return ps.getSeriesGradientPaintSequence();
		if (id.equals(PlotSettings.PROPERTY_seriesStrokeSequence))
			return ps.getSeriesStrokeSequence();

		Object pad = PadUtil.getPropertyValue(id, ps.getPadding());
		if (pad != null)
			return pad;
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		PlotSettings ps = getValue();
		if (id.equals(PlotSettings.PROPERTY_outlineVisible))
			ps.setOutlineVisible((Boolean) value);
		else if (id.equals(PlotSettings.PROPERTY_domainGridlineVisible))
			ps.setDomainGridlineVisible((Boolean) value);
		else if (id.equals(PlotSettings.PROPERTY_rangeGridlineVisible))
			ps.setRangeGridlineVisible((Boolean) value);
		else if (id.equals(PlotSettings.PROPERTY_displayFont))
			ps.setDisplayFont(MFontUtil.setMFont(value));
		else if (id.equals(PlotSettings.PROPERTY_tickLabelFont))
			ps.setTickLabelFont(MFontUtil.setMFont(value));
		else if (id.equals(PlotSettings.PROPERTY_backgroundImageAlpha))
			ps.setBackgroundAlpha((Float) value);
		else if (id.equals(PlotSettings.PROPERTY_labelRotation))
			ps.setLabelRotation((Double) value);
		else if (id.equals(PlotSettings.PROPERTY_backgroundImageAlignment))
			ps.setBackgroundImageAlignment(JFreeChartAlignEnum.getJFreeChartValue((Integer) value));
		else if (id.equals(PlotSettings.PROPERTY_orientation))
			ps.setOrientation(((JFreeChartPlotOrientationEnum) orientation.getEnumValue(value)).getJFreeChartValue());
		else if (id.equals(PlotSettings.PROPERTY_backgroundPaint))
			ps.setBackgroundPaint((PaintProvider) value);
		else if (id.equals(PlotSettings.PROPERTY_outlinePaint))
			ps.setOutlinePaint((PaintProvider) value);
		else if (id.equals(PlotSettings.PROPERTY_domainGridlinePaint))
			ps.setDomainGridlinePaint((PaintProvider) value);
		else if (id.equals(PlotSettings.PROPERTY_rangeGridlinePaint))
			ps.setRangeGridlinePaint((PaintProvider) value);
		else if (id.equals(PlotSettings.PROPERTY_backgroundImage))
			if (value == null || ((String) value).trim().isEmpty())
				ps.setBackgroundImage(null);
			else
				ps.setBackgroundImage(new CachedImageProvider((String) value));
		else if (id.equals(PlotSettings.PROPERTY_outlineStroke))
			ps.setOutlineStroke((Stroke) value);
		else if (id.equals(PlotSettings.PROPERTY_domainGridlineStroke))
			ps.setDomainGridlineStroke((Stroke) value);
		else if (id.equals(PlotSettings.PROPERTY_rangeGridlineStroke))
			ps.setRangeGridlineStroke((Stroke) value);
		else if (id.equals(PlotSettings.PROPERTY_seriesStrokeSequence))
			ps.setSeriesStrokeSequence((List<Stroke>) value);
		else if (id.equals(PlotSettings.PROPERTY_seriesOutlineStrokeSequence))
			ps.setSeriesOutlineStrokeSequence((List<Stroke>) value);

		RectangleInsets ri = PadUtil.setPropertyValue(id, value, ps.getPadding());
		if (ri != null)
			ps.setPadding(ri);
	}
}
