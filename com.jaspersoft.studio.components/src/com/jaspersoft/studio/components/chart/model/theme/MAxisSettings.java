package com.jaspersoft.studio.components.chart.model.theme;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.AxisSettings;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DegreePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;

public class MAxisSettings extends APropertyNode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String displayText;

	public MAxisSettings(MChartThemeSettings parent, AxisSettings as, String displayText) {
		super(parent, -1);
		setValue(as);
		this.displayText = displayText;
	}

	@Override
	public AxisSettings getValue() {
		return (AxisSettings) super.getValue();
	}

	@Override
	public ImageDescriptor getImagePath() {
		return null;
	}

	@Override
	public String getDisplayText() {
		return displayText;
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
		CheckBoxPropertyDescriptor visibleD = new CheckBoxPropertyDescriptor(AxisSettings.PROPERTY_visible, "Visible");
		visibleD.setDescription("Visible");
		desc.add(visibleD);

		CheckBoxPropertyDescriptor lineVisibleD = new CheckBoxPropertyDescriptor(AxisSettings.PROPERTY_lineVisible, "Line Visible");
		lineVisibleD.setDescription("Line Visible");
		desc.add(lineVisibleD);

		CheckBoxPropertyDescriptor labelVisibleD = new CheckBoxPropertyDescriptor(AxisSettings.PROPERTY_labelVisible, "Label Visible");
		labelVisibleD.setDescription("Label Visible");
		desc.add(labelVisibleD);

		CheckBoxPropertyDescriptor tickLabelVisibleD = new CheckBoxPropertyDescriptor(AxisSettings.PROPERTY_tickLabelsVisible, "Tick Labels Visible");
		tickLabelVisibleD.setDescription("Tick Labels Visible");
		desc.add(tickLabelVisibleD);

		CheckBoxPropertyDescriptor tickMarksVisibleD = new CheckBoxPropertyDescriptor(AxisSettings.PROPERTY_tickMarksVisible, "Tick Marks Visible");
		tickMarksVisibleD.setDescription("Tick Marks Visible");
		desc.add(tickMarksVisibleD);

		CheckBoxPropertyDescriptor axisIntUnitD = new CheckBoxPropertyDescriptor(AxisSettings.PROPERTY_axisIntegerUnit, "Axis Integer Unit");
		axisIntUnitD.setDescription("Axis Integer Unit");
		desc.add(axisIntUnitD);

		FontPropertyDescriptor labelFontD = new FontPropertyDescriptor(AxisSettings.PROPERTY_labelFont, "Label Font");
		labelFontD.setDescription("Label Font");
		desc.add(labelFontD);

		FontPropertyDescriptor tickLabelFontD = new FontPropertyDescriptor(AxisSettings.PROPERTY_tickLabelFont, "Tick Label Font");
		tickLabelFontD.setDescription("Tick Label Font");
		desc.add(tickLabelFontD);

		FloatPropertyDescriptor tmil = new FloatPropertyDescriptor(AxisSettings.PROPERTY_tickMarksInsideLength, "Tick Marks Inside Lenght");
		tmil.setDescription("Tick Marks Inside Lenght");
		desc.add(tmil);

		FloatPropertyDescriptor tmol = new FloatPropertyDescriptor(AxisSettings.PROPERTY_tickMarksOutsideLength, "Tick Marks Outside Lenght");
		tmol.setDescription("Tick Marks Outside Lenght");
		desc.add(tmol);

		DoublePropertyDescriptor la = new DegreePropertyDescriptor(AxisSettings.PROPERTY_labelAngle, "Label Angle");
		la.setDescription("Label Angle");
		desc.add(la);

		DoublePropertyDescriptor ti = new DoublePropertyDescriptor(AxisSettings.PROPERTY_tickInterval, "Tick Interval");
		ti.setDescription("Tick Interval");
		desc.add(ti);

		IntegerPropertyDescriptor tc = new IntegerPropertyDescriptor(AxisSettings.PROPERTY_tickCount, "Tick Count");
		tc.setDescription("Tick Count");
		desc.add(tc);

		PadUtil.createPropertyDescriptors(desc, defaultsMap, "Tick Label Inset");

		defaultsMap.put(AxisSettings.PROPERTY_visible, Boolean.TRUE);
		defaultsMap.put(AxisSettings.PROPERTY_lineVisible, Boolean.TRUE);
		defaultsMap.put(AxisSettings.PROPERTY_labelVisible, Boolean.TRUE);
		defaultsMap.put(AxisSettings.PROPERTY_tickLabelsVisible, Boolean.TRUE);
		defaultsMap.put(AxisSettings.PROPERTY_tickMarksVisible, Boolean.TRUE);
		defaultsMap.put(AxisSettings.PROPERTY_axisIntegerUnit, Boolean.TRUE);
	}

	private MFont lFont;
	private MFont tlFont;

	@Override
	public Object getPropertyValue(Object id) {
		AxisSettings ts = getValue();
		if (id.equals(AxisSettings.PROPERTY_visible))
			return ts.getVisible();
		if (id.equals(AxisSettings.PROPERTY_lineVisible))
			return ts.getLineVisible();
		if (id.equals(AxisSettings.PROPERTY_labelVisible))
			return ts.getLabelVisible();
		if (id.equals(AxisSettings.PROPERTY_tickLabelsVisible))
			return ts.getTickLabelsVisible();
		if (id.equals(AxisSettings.PROPERTY_tickMarksVisible))
			return ts.getTickLabelsVisible();
		if (id.equals(AxisSettings.PROPERTY_axisIntegerUnit))
			return ts.getAxisIntegerUnit();
		if (id.equals(AxisSettings.PROPERTY_labelFont)) {
			lFont = MFontUtil.getMFont(lFont, ts.getLabelFont(), null, this);
			return lFont;
		}
		if (id.equals(AxisSettings.PROPERTY_tickLabelFont)) {
			tlFont = MFontUtil.getMFont(tlFont, ts.getTickLabelFont(), null, this);
			return tlFont;
		}
		if (id.equals(AxisSettings.PROPERTY_tickMarksInsideLength))
			return ts.getTickMarksInsideLength();
		if (id.equals(AxisSettings.PROPERTY_tickMarksOutsideLength))
			return ts.getTickMarksOutsideLength();
		if (id.equals(AxisSettings.PROPERTY_labelAngle))
			return ts.getLabelAngle();
		if (id.equals(AxisSettings.PROPERTY_tickInterval))
			return ts.getTickInterval();
		if (id.equals(AxisSettings.PROPERTY_tickCount))
			return ts.getTickCount();

		Object pad = PadUtil.getPropertyValue(id, ts.getTickLabelInsets());
		if (pad != null)
			return pad;
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		AxisSettings ts = getValue();
		if (id.equals(AxisSettings.PROPERTY_visible))
			ts.setVisible((Boolean) value);
		else if (id.equals(AxisSettings.PROPERTY_lineVisible))
			ts.setLineVisible((Boolean) value);
		else if (id.equals(AxisSettings.PROPERTY_labelVisible))
			ts.setLabelVisible((Boolean) value);
		else if (id.equals(AxisSettings.PROPERTY_tickLabelsVisible))
			ts.setTickLabelsVisible((Boolean) value);
		else if (id.equals(AxisSettings.PROPERTY_tickMarksVisible))
			ts.setTickMarksVisible((Boolean) value);
		else if (id.equals(AxisSettings.PROPERTY_axisIntegerUnit))
			ts.setAxisIntegerUnit((Boolean) value);
		else if (id.equals(AxisSettings.PROPERTY_labelFont))
			ts.setLabelFont(MFontUtil.setMFont(value));
		else if (id.equals(AxisSettings.PROPERTY_tickLabelFont))
			ts.setTickLabelFont(MFontUtil.setMFont(value));
		else if (id.equals(AxisSettings.PROPERTY_tickMarksInsideLength))
			ts.setTickMarksInsideLength((Float) value);
		else if (id.equals(AxisSettings.PROPERTY_tickMarksOutsideLength))
			ts.setTickMarksOutsideLength((Float) value);
		else if (id.equals(AxisSettings.PROPERTY_labelAngle))
			ts.setLabelAngle((Double) value);
		else if (id.equals(AxisSettings.PROPERTY_tickInterval))
			ts.setTickInterval((Double) value);
		else if (id.equals(AxisSettings.PROPERTY_tickCount))
			ts.setTickCount((Integer) value);

		RectangleInsets ri = PadUtil.setPropertyValue(id, value, ts.getTickLabelInsets());
		if (ri != null)
			ts.setTickLabelInsets(ri);
	}

}
