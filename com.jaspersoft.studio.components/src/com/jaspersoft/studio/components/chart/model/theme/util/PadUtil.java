/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.BlockBorderProvider;
import net.sf.jasperreports.chartthemes.simple.BlockFrameProvider;
import net.sf.jasperreports.chartthemes.simple.ChartSettings;
import net.sf.jasperreports.chartthemes.simple.ColorProvider;
import net.sf.jasperreports.chartthemes.simple.LegendSettings;
import net.sf.jasperreports.chartthemes.simple.LineBorderProvider;
import net.sf.jasperreports.chartthemes.simple.PaintProvider;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.jfree.chart.block.BlockFrame;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.AlfaRGB;
import com.jaspersoft.studio.utils.Colors;

public class PadUtil {

	public static final RectangleInsets RECTANGLE_INSETS = new RectangleInsets(0, 0, 0, 0);
	public static final String PADDING_RIGHT = ChartSettings.PROPERTY_padding + "RIGHT";//$NON-NLS-1$
	public static final String PADDING_LEFT = ChartSettings.PROPERTY_padding + "LEFT";//$NON-NLS-1$
	public static final String PADDING_BOTTOM = ChartSettings.PROPERTY_padding + "BOTTOM";//$NON-NLS-1$
	public static final String PADDING_TOP = ChartSettings.PROPERTY_padding + "TOP";//$NON-NLS-1$
	public static final String FRAME_STROKE = "RectangleFrameStroke";//$NON-NLS-1$
	public static final String FRAME_FILL = "RectangleFrameFill";//$NON-NLS-1$
	public static final String FRAME_COLOR = "RectangleFrameColor";//$NON-NLS-1$
	
	public static final Map<String, Object> frameDefaultValues = new HashMap<String, Object>();
	
	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		createPropertyDescriptors(desc, defaultsMap, Messages.common_padding);
	}

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap, String prefix) {
		createPropertyDescriptors(desc, defaultsMap, "", prefix);//$NON-NLS-1$
	}

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap, String preID, String prefix) {
		PropertyDescriptor pd = new DoublePropertyDescriptor(preID + PadUtil.PADDING_TOP, Messages.common_top);
		pd.setDescription(Messages.common_top);
		pd.setCategory(prefix);
		desc.add(pd);

		pd = new DoublePropertyDescriptor(preID + PadUtil.PADDING_BOTTOM, Messages.common_bottom);
		pd.setDescription(Messages.common_bottom);
		pd.setCategory(prefix);
		desc.add(pd);

		pd = new DoublePropertyDescriptor(preID + PadUtil.PADDING_LEFT, Messages.common_left);
		pd.setDescription(Messages.common_left);
		pd.setCategory(prefix);
		desc.add(pd);

		pd = new DoublePropertyDescriptor(preID + PadUtil.PADDING_RIGHT, Messages.common_right);
		pd.setDescription(Messages.common_right);
		pd.setCategory(prefix);
		desc.add(pd);
		
		defaultsMap.put(preID + PadUtil.PADDING_TOP, 0.0d);
		defaultsMap.put(preID + PadUtil.PADDING_BOTTOM, 0.0d);
		defaultsMap.put(preID + PadUtil.PADDING_LEFT, 0.0d);
		defaultsMap.put(preID + PadUtil.PADDING_RIGHT, 0.0d);
	}
	
	public static void createBlockFramePropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap, String category) {
		String preID = LegendSettings.PROPERTY_frame;
		frameDefaultValues.clear();
		
		createPropertyDescriptors(desc, frameDefaultValues, preID, category);
		
		PropertyDescriptor pd = new DoublePropertyDescriptor(preID + PadUtil.FRAME_STROKE, Messages.MLinePen_line_width);
		pd.setDescription(Messages.MLinePen_line_width);
		pd.setCategory(category);
		desc.add(pd);
		
		pd = new CheckBoxPropertyDescriptor(preID + PadUtil.FRAME_FILL, Messages.common_fill);
		pd.setDescription(Messages.common_fill);
		pd.setCategory(category);
		desc.add(pd);
		
		pd = new ColorPropertyDescriptor(preID + FRAME_COLOR, Messages.common_line_color, NullEnum.INHERITED);
		pd.setDescription(Messages.common_line_color);
		pd.setCategory(category);
		desc.add(pd);

		frameDefaultValues.put(preID + PadUtil.FRAME_STROKE, 1.0d);
		frameDefaultValues.put(preID + PadUtil.FRAME_FILL, true);
		frameDefaultValues.put(preID + FRAME_COLOR, AlfaRGB.getFullyOpaque(new RGB(0, 0, 0)));
		
		defaultsMap.putAll(frameDefaultValues);
	}
	
	public static Object getBlockFrameValue(Object id, BlockFrameProvider provider){
		if (provider == null) {
			return frameDefaultValues.get(id);
		} else {
			String preID = LegendSettings.PROPERTY_frame;
			if (id.equals(preID + PadUtil.FRAME_FILL)) {
				return !(provider instanceof LineBorderProvider);
			} else if (id.equals(preID + PadUtil.FRAME_STROKE)){
				if (provider instanceof LineBorderProvider){
					BasicStroke stroke = (BasicStroke)((LineBorderProvider)provider).getLineStroke();
					return stroke != null ? stroke.getLineWidth() : null;
				} else return null;
			} else if (id.equals(preID + FRAME_COLOR)){
				return getPaintColor(provider);
			} else {
				return getPropertyValue(id, provider.getBlockFrame().getInsets(), preID);
			}
		}
	}
	
	public static void setFramePropertyValue(Object id, Object value, LegendSettings settings) {
		if (settings == null) return;
		String preID = LegendSettings.PROPERTY_frame;
		if (id.equals(preID + PadUtil.FRAME_FILL)) {
			checkValidFrame(settings);
			Boolean fill = (Boolean)value;
			if (fill && !(settings.getBlockFrame() instanceof BlockBorderProvider)){
				BlockFrameProvider currentFrame = settings.getFrame();
				BlockBorderProvider fillFrame = new BlockBorderProvider(getFrameInstets(currentFrame), getPaint(currentFrame));
				settings.setFrame(fillFrame);
			}
			if (!fill && !(settings.getBlockFrame() instanceof LineBorderProvider)){
				BlockFrameProvider currentFrame = settings.getFrame();
				LineBorderProvider fillFrame = new LineBorderProvider(getFrameInstets(currentFrame), new BasicStroke(1.0f), getPaint(currentFrame));
				settings.setFrame(fillFrame);
			}
		} else if (id.equals(preID + PadUtil.FRAME_STROKE)){
			checkValidFrame(settings);
			if (settings.getFrame() instanceof LineBorderProvider){
				LineBorderProvider currentFrame = (LineBorderProvider)settings.getFrame();
				if (value == null) value = 0.0d;
				float strokeValue = ((Number)value).floatValue();
				currentFrame.setLineStroke(new BasicStroke(strokeValue));
				//need to force the refresh
				settings.setFrame(null);
				settings.setFrame(currentFrame);
			}
		} else if (id.equals(preID + FRAME_COLOR)){
			checkValidFrame(settings);
			setFrameColor(settings.getFrame(), (AlfaRGB)value, settings);
		} else if (id.equals(preID + PadUtil.PADDING_TOP) || id.equals(preID + PadUtil.PADDING_BOTTOM) 
					|| id.equals(preID + PadUtil.PADDING_LEFT) || id.equals(preID + PADDING_RIGHT)){
			checkValidFrame(settings);
			RectangleInsets newInsets = setPropertyValue(id, value, settings.getFrame().getBlockFrame().getInsets(), preID);
			if (newInsets != null){
				setFrameInstets(newInsets, settings.getFrame(), settings);
			}
		}
	}
	
	private static void setFrameInstets(RectangleInsets newInsets, BlockFrameProvider provider, LegendSettings settings){
		if (provider instanceof BlockBorderProvider){
			BlockBorderProvider newProvider = new BlockBorderProvider(newInsets, ((BlockBorderProvider) provider).getPaint());
			settings.setFrame(newProvider);
		} else if (provider instanceof LineBorderProvider){
			LineBorderProvider oldProvider =(LineBorderProvider)provider;
			LineBorderProvider newProvider = new LineBorderProvider(newInsets, oldProvider.getLineStroke(), oldProvider.getPaint());
			settings.setFrame(newProvider);
		}
	}
	
	private static void checkValidFrame(LegendSettings settigns){
		if (settigns.getFrame() == null){
			BlockBorderProvider fillFrame = new BlockBorderProvider(RECTANGLE_INSETS, new ColorProvider(new Color(0, 0, 0)));
			settigns.setFrame(fillFrame);
		}
	}
	
	private static void setFrameColor(BlockFrameProvider provider, AlfaRGB color, LegendSettings settings){
		if (provider instanceof LineBorderProvider){
			ColorProvider newColor = new ColorProvider(Colors.getAWT4SWTRGBColor(color));
			((LineBorderProvider)provider).setPaint(newColor);
		} else if (provider instanceof BlockBorderProvider){
			ColorProvider newColor = new ColorProvider(Colors.getAWT4SWTRGBColor(color));
			((BlockBorderProvider)provider).setPaint(newColor);
		}
		settings.setFrame(null);
		settings.setFrame(provider);
	}
	
	private static RectangleInsets getFrameInstets(BlockFrameProvider provider){
		if (provider == null) return null;
		BlockFrame frame = provider.getBlockFrame();
		if (frame == null) return null;
		return frame.getInsets();
	}
	
	private static PaintProvider getPaint(BlockFrameProvider provider){
		if (provider instanceof LineBorderProvider){
			return ((LineBorderProvider)provider).getPaint();
		} else if (provider instanceof BlockBorderProvider){
			return ((BlockBorderProvider)provider).getPaint();
		} else return null;
	}
	
	private static AlfaRGB getPaintColor(BlockFrameProvider provider){
		if (provider instanceof LineBorderProvider){
			return getPaintColor(((LineBorderProvider)provider).getPaint());
		} else if (provider instanceof BlockBorderProvider){
			return getPaintColor(((BlockBorderProvider)provider).getPaint());
		} else return null;
	}
	
	private static AlfaRGB getPaintColor(PaintProvider provider){
		if (provider != null && provider.getPaint() instanceof Color){
			Color color = (Color)provider.getPaint();
			return Colors.getSWTRGB4AWTGBColor(color);
		}
		return null;
	}
	
	public static Object getPropertyValue(Object id, RectangleInsets ri) {
		return getPropertyValue(id, ri, "");
	}

	public static Object getPropertyValue(Object id, RectangleInsets ri, String preID) {
		if (ri == null)
			ri = PadUtil.RECTANGLE_INSETS;
		if (id.equals(preID + PadUtil.PADDING_TOP))
			return ri.getTop();
		if (id.equals(preID + PadUtil.PADDING_BOTTOM))
			return ri.getBottom();
		if (id.equals(preID + PadUtil.PADDING_LEFT))
			return ri.getLeft();
		if (id.equals(preID + PadUtil.PADDING_RIGHT))
			return ri.getRight();
		return null;
	}

	public static RectangleInsets setPropertyValue(Object id, Object value, RectangleInsets ri) {
		return setPropertyValue(id, value, ri, "");//$NON-NLS-1$
	}

	public static RectangleInsets setPropertyValue(Object id, Object value, RectangleInsets ri, String preID) {
		if (ri == null)
			ri = PadUtil.RECTANGLE_INSETS;
		if (value == null) value = 0.0d;
		if (id.equals(preID + PadUtil.PADDING_TOP))
			return new RectangleInsets((Double) value, ri.getLeft(), ri.getBottom(), ri.getRight());
		else if (id.equals(preID + PadUtil.PADDING_BOTTOM))
			return new RectangleInsets(ri.getTop(), ri.getLeft(), (Double) value, ri.getRight());
		else if (id.equals(preID + PadUtil.PADDING_LEFT))
			return new RectangleInsets(ri.getTop(), (Double) value, ri.getBottom(), ri.getRight());
		else if (id.equals(preID + PadUtil.PADDING_RIGHT))
			return new RectangleInsets(ri.getTop(), ri.getLeft(), ri.getBottom(), (Double) value);
		return null;
	}

	public static Composite createWidgets4Property(Composite parent, String preID, String prefix, AbstractSection section) {
		Composite group = section.getWidgetFactory().createSection(parent, prefix, true, 4);
		((Section) group.getParent()).setExpanded(false);

		section.createWidget4Property(group, preID + PadUtil.PADDING_TOP);
		section.createWidget4Property(group, preID + PadUtil.PADDING_BOTTOM);
		section.createWidget4Property(group, preID + PadUtil.PADDING_LEFT);
		section.createWidget4Property(group, preID + PadUtil.PADDING_RIGHT);
		
		return group;
	}
	
}
