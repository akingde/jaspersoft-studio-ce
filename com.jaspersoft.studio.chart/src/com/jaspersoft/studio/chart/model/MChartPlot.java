package com.jaspersoft.studio.chart.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRChartPlot;
import net.sf.jasperreports.engine.base.JRBaseChartPlot;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.jfree.chart.plot.PlotOrientation;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.color.ColorPropertyDescriptor;
import com.jaspersoft.studio.utils.Colors;

public class MChartPlot extends APropertyNode {

	public MChartPlot(JRChartPlot value) {
		super();
		setValue(value);
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		ColorPropertyDescriptor backcolorD = new ColorPropertyDescriptor(JRBaseChartPlot.PROPERTY_BACKCOLOR, "Backcolor",
				NullEnum.INHERITED);
		backcolorD.setDescription("The plot backcolor.");
		desc.add(backcolorD);

		FloatPropertyDescriptor backAlphaD = new FloatPropertyDescriptor(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA,
				"Background Alpha (%)");
		backAlphaD.setDescription("Background Alpha (%).");
		desc.add(backAlphaD);

		FloatPropertyDescriptor foreAlphaD = new FloatPropertyDescriptor(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA,
				"Foreground Alpha (%)");
		foreAlphaD.setDescription("Foreground Alpha (%).");
		desc.add(foreAlphaD);

		DoublePropertyDescriptor labelRotationD = new DoublePropertyDescriptor(JRBaseChartPlot.PROPERTY_LABEL_ROTATION,
				"Label Rotation");
		labelRotationD.setDescription("Label Rotation.");
		desc.add(labelRotationD);

		ComboBoxPropertyDescriptor orientationD = new ComboBoxPropertyDescriptor(JRBaseChartPlot.PROPERTY_ORIENTATION,
				"Orientation", new String[] { "horizontal", "vertical" });
		orientationD.setDescription("Orientation.");
		desc.add(orientationD);

		defaultsMap.put(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA, null);
		defaultsMap.put(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA, null);
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRBaseChartPlot jrElement = (JRBaseChartPlot) getValue();
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKCOLOR))
			return Colors.getSWTRGB4AWTGBColor(jrElement.getBackcolor());
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA))
			return jrElement.getBackgroundAlphaFloat();
		if (id.equals(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA))
			return jrElement.getForegroundAlphaFloat();
		if (id.equals(JRBaseChartPlot.PROPERTY_LABEL_ROTATION))
			return jrElement.getLabelRotationDouble();
		if (id.equals(JRBaseChartPlot.PROPERTY_ORIENTATION)) {
			if (jrElement.getOrientation().equals(PlotOrientation.HORIZONTAL))
				return 0;
			else
				return 1;
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRBaseChartPlot jrElement = (JRBaseChartPlot) getValue();
		if (id.equals(JRBaseChartPlot.PROPERTY_BACKCOLOR)) {
			if (value instanceof RGB)
				jrElement.setBackcolor(Colors.getAWT4SWTRGBColor((RGB) value));
		} else if (id.equals(JRBaseChartPlot.PROPERTY_BACKGROUND_ALPHA))
			jrElement.setBackgroundAlpha((Float) value);
		else if (id.equals(JRBaseChartPlot.PROPERTY_FOREGROUND_ALPHA))
			jrElement.setForegroundAlpha((Float) value);
		else if (id.equals(JRBaseChartPlot.PROPERTY_LABEL_ROTATION))
			jrElement.setLabelRotation((Double) value);
		else if (id.equals(JRBaseChartPlot.PROPERTY_ORIENTATION)) {
			if (value.equals(new Integer(0)))
				jrElement.setOrientation(PlotOrientation.HORIZONTAL);
			else
				jrElement.setOrientation(PlotOrientation.VERTICAL);
		}
	}

	public ImageDescriptor getImagePath() {
		return null;
	}

	public String getDisplayText() {
		return null;
	}

}
