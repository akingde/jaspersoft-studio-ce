/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.theme.util;

import java.awt.BasicStroke;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.jfree.chart.block.BlockFrame;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.chartthemes.simple.BlockBorderProvider;
import net.sf.jasperreports.chartthemes.simple.BlockFrameProvider;
import net.sf.jasperreports.chartthemes.simple.ChartSettings;
import net.sf.jasperreports.chartthemes.simple.ColorProvider;
import net.sf.jasperreports.chartthemes.simple.LegendSettings;
import net.sf.jasperreports.chartthemes.simple.LineBorderProvider;
import net.sf.jasperreports.chartthemes.simple.PaintProvider;

public class PadUtil {

	public static final RectangleInsets RECTANGLE_INSETS = new RectangleInsets(0, 0, 0, 0);
	public static final String PADDING_RIGHT = ChartSettings.PROPERTY_padding + "RIGHT";//$NON-NLS-1$
	public static final String PADDING_LEFT = ChartSettings.PROPERTY_padding + "LEFT";//$NON-NLS-1$
	public static final String PADDING_BOTTOM = ChartSettings.PROPERTY_padding + "BOTTOM";//$NON-NLS-1$
	public static final String PADDING_TOP = ChartSettings.PROPERTY_padding + "TOP";//$NON-NLS-1$
	public static final String FRAME_STROKE = "RectangleFrameStroke";//$NON-NLS-1$
	public static final String FRAME_FILL = "RectangleFrameFill";//$NON-NLS-1$
	public static final String FRAME_COLOR = "RectangleFrameColor";//$NON-NLS-1$
	
	/**
	 * Map of the default values for the frame provider
	 */
	public static final Map<String, DefaultValue> frameDefaultValues = new HashMap<String, DefaultValue>();
	
	static{
		String preID = LegendSettings.PROPERTY_frame;
		frameDefaultValues.put(preID + PadUtil.FRAME_STROKE, new DefaultValue(1.0d,false));
		frameDefaultValues.put(preID + PadUtil.FRAME_FILL, new DefaultValue(true, false));
		frameDefaultValues.put(preID + FRAME_COLOR, new DefaultValue(new ColorProvider(new Color(0, 0, 0)), false));
		frameDefaultValues.put(preID + PadUtil.PADDING_TOP, new DefaultValue(0.0d, false));
		frameDefaultValues.put(preID + PadUtil.PADDING_BOTTOM, new DefaultValue(0.0d, false));
		frameDefaultValues.put(preID + PadUtil.PADDING_LEFT, new DefaultValue(0.0d, false));
		frameDefaultValues.put(preID + PadUtil.PADDING_RIGHT, new DefaultValue(0.0d, false));
	}
	
	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		createPropertyDescriptors(desc, Messages.common_padding);
	}

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, String prefix) {
		createPropertyDescriptors(desc, "", prefix);//$NON-NLS-1$
	}

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, String preID, String category) {
		PropertyDescriptor pd = new FrameDoublePropertyDescriptor(preID + PadUtil.PADDING_TOP, Messages.common_top);
		pd.setDescription(Messages.common_top);
		pd.setCategory(category);
		desc.add(pd);

		pd = new FrameDoublePropertyDescriptor(preID + PadUtil.PADDING_BOTTOM, Messages.common_bottom);
		pd.setDescription(Messages.common_bottom);
		pd.setCategory(category);
		desc.add(pd);

		pd = new FrameDoublePropertyDescriptor(preID + PadUtil.PADDING_LEFT, Messages.common_left);
		pd.setDescription(Messages.common_left);
		pd.setCategory(category);
		desc.add(pd);

		pd = new FrameDoublePropertyDescriptor(preID + PadUtil.PADDING_RIGHT, Messages.common_right);
		pd.setDescription(Messages.common_right);
		pd.setCategory(category);
		desc.add(pd);
	}
	
	public static void createDefaults(String preID, Map<String, DefaultValue> defaultsMap){
		defaultsMap.put(preID + PadUtil.PADDING_TOP, new DefaultValue(0.0d, false));
		defaultsMap.put(preID + PadUtil.PADDING_BOTTOM, new DefaultValue(0.0d, false));
		defaultsMap.put(preID + PadUtil.PADDING_LEFT, new DefaultValue(0.0d, false));
		defaultsMap.put(preID + PadUtil.PADDING_RIGHT, new DefaultValue(0.0d, false));
		defaultsMap.putAll(frameDefaultValues);
	}
	
	/**
	 * Create the property descriptor for the block frame provider. Initialize also the default values
	 * for the default values map frameDefaultValues
	 * 
	 * @param desc array where the descriptor will be inserted
	 * @param defaultsMap map for the default values
	 * @param category category where the widget will be grouped
	 */
	public static void createBlockFramePropertyDescriptors(List<IPropertyDescriptor> desc, String category) {
		String preID = LegendSettings.PROPERTY_frame;

		createPropertyDescriptors(desc, preID, category);
		
		PropertyDescriptor pd = new FrameDoublePropertyDescriptor(preID + PadUtil.FRAME_STROKE, Messages.MLinePen_line_width);
		pd.setDescription(Messages.MLinePen_line_width);
		pd.setCategory(category);
		desc.add(pd);
		
		pd = new FrameCheckBoxPropertyDescriptor(preID + PadUtil.FRAME_FILL, Messages.common_fill);
		pd.setDescription(Messages.common_fill);
		pd.setCategory(category);
		desc.add(pd);
		
		pd = new FramePaintProviderPropertyDescriptor(preID + FRAME_COLOR, Messages.common_line_color);
		pd.setDescription(Messages.common_line_color);
		pd.setCategory(category);
		desc.add(pd);
	}
	
	/**
	 * Method used to extract a property from a block frame provider using an id of 
	 * the property. If the blockframe is undefined a default value is given
	 * 
	 * @param id identifier of the property
	 * @param provider object from where the property is extracted
	 * @return the result, can be null
	 */
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
				return getPaint(provider);
			} else {
				return getPropertyValue(id, provider.getBlockFrame().getInsets(), preID);
			}
		}
	}
	
	/**
	 * Method used to set the property of a blockframe.
	 * 	
	 * @param id Identifier of the property to set
	 * @param value value of the property
	 * @param settings LegendSettings that contains the block frame provider that will be set
	 */
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
			setFrameColor(settings.getFrame(), (PaintProvider)value, settings);
		} else if (id.equals(preID + PadUtil.PADDING_TOP) || id.equals(preID + PadUtil.PADDING_BOTTOM) 
					|| id.equals(preID + PadUtil.PADDING_LEFT) || id.equals(preID + PADDING_RIGHT)){
			checkValidFrame(settings);
			RectangleInsets newInsets = setPropertyValue(id, value, settings.getFrame().getBlockFrame().getInsets(), preID);
			if (newInsets != null){
				setFrameInstets(newInsets, settings.getFrame(), settings);
			}
		}
	}
	
	/**
	 * Set the insets of a block frame. Since a block frame dosen't allow to set the insets
	 * a new frame of the same type is created with the new insets
	 * 
	 * @param newInsets the new insets, can be null
	 * @param provider the provider where the insets should be set
	 * @param settings the LegendSettings where the new block frame is set
	 */
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
	
	/**
	 * Check if the current legend settings has a block frame set. If
	 * not a default one is created
	 * 
	 * @param settigns the legend settings to check, must be not null
	 */
	private static void checkValidFrame(LegendSettings settigns){
		if (settigns.getFrame() == null){
			BlockBorderProvider fillFrame = new BlockBorderProvider(RECTANGLE_INSETS, new ColorProvider(new Color(0, 0, 0)));
			settigns.setFrame(fillFrame);
		}
	}
	
	/**
	 * Set the color of a block frame. Since a block frame dosen't allow to set the color on a defined instance
	 * a new frame of the same type is created with the new color
	 * 
	 * @param provider the provider where the color should be set
	 * @color color the new color
	 * @param settings the LegendSettings where the new block frame is set
	 */
	private static void setFrameColor(BlockFrameProvider provider, PaintProvider color, LegendSettings settings){
		if (provider instanceof LineBorderProvider){
			((LineBorderProvider)provider).setPaint(color);
		} else if (provider instanceof BlockBorderProvider){
			((BlockBorderProvider)provider).setPaint(color);
		}
		//Need to do this to fire the refresh
		settings.setFrame(null);
		settings.setFrame(provider);
	}
	
	/**
	 * Get the insets of the current frame, if the frame or the provider
	 * are not available return null
	 * 
	 * @param provider the provider from where the insets are read
	 * @return the insets of the frame or null if they are not available
	 */
	private static RectangleInsets getFrameInstets(BlockFrameProvider provider){
		if (provider == null) return null;
		BlockFrame frame = provider.getBlockFrame();
		if (frame == null) return null;
		return frame.getInsets();
	}
	
	/**
	 * Get the paint provider of the current frame, if the frame or the provider
	 * are not available return null
	 * 
	 * @param provider the provider from where the insets are read
	 * @return the paint provider if available, otherwise null
	 */
	private static PaintProvider getPaint(BlockFrameProvider provider){
		if (provider instanceof LineBorderProvider){
			return ((LineBorderProvider)provider).getPaint();
		} else if (provider instanceof BlockBorderProvider){
			return ((BlockBorderProvider)provider).getPaint();
		} else return null;
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
