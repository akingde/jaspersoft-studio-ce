package com.jaspersoft.studio.components.chart.model.theme;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.ChartSettings;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;

public class PadUtil {

	public static final RectangleInsets RECTANGLE_INSETS = new RectangleInsets(0, 0, 0, 0);
	public static final String PADDING_RIGHT = ChartSettings.PROPERTY_padding + "RIGHT";
	public static final String PADDING_LEFT = ChartSettings.PROPERTY_padding + "LEFT";
	public static final String PADDING_BOTTOM = ChartSettings.PROPERTY_padding + "BOTTOM";
	public static final String PADDING_TOP = ChartSettings.PROPERTY_padding + "TOP";

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		createPropertyDescriptors(desc, defaultsMap, "Padding");
	}

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap, String prefix) {
		createPropertyDescriptors(desc, defaultsMap, "", prefix);
	}

	public static void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap, String preID, String prefix) {
		DoublePropertyDescriptor padTopD = new DoublePropertyDescriptor(preID + PadUtil.PADDING_TOP, prefix + " Top");
		padTopD.setDescription(Messages.common_padding);
		desc.add(padTopD);

		DoublePropertyDescriptor padBottomD = new DoublePropertyDescriptor(preID + PadUtil.PADDING_BOTTOM, prefix + " Bottom");
		padBottomD.setDescription(Messages.common_padding);
		desc.add(padBottomD);

		DoublePropertyDescriptor padLeftD = new DoublePropertyDescriptor(preID + PadUtil.PADDING_LEFT, prefix + " Left");
		padLeftD.setDescription(Messages.common_padding);
		desc.add(padLeftD);

		DoublePropertyDescriptor padRightD = new DoublePropertyDescriptor(preID + PadUtil.PADDING_RIGHT, prefix + " Right");
		padRightD.setDescription(Messages.common_padding);
		desc.add(padRightD);

		defaultsMap.put(preID + PadUtil.PADDING_TOP, 0.0d);
		defaultsMap.put(preID + PadUtil.PADDING_BOTTOM, 0.0d);
		defaultsMap.put(preID + PadUtil.PADDING_LEFT, 0.0d);
		defaultsMap.put(preID + PadUtil.PADDING_RIGHT, 0.0d);
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
		return setPropertyValue(id, value, ri, "");
	}

	public static RectangleInsets setPropertyValue(Object id, Object value, RectangleInsets ri, String preID) {
		if (ri == null)
			ri = PadUtil.RECTANGLE_INSETS;
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
}
