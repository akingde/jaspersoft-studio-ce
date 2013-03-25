package com.jaspersoft.studio.components.chart.model.theme;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.PlotSettings;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.components.chart.model.enums.JFreeChartAlignEnum;
import com.jaspersoft.studio.components.chart.model.enums.JFreeChartPlotOrientationEnum;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DegreePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
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
		CheckBoxPropertyDescriptor outlineVisibleD = new CheckBoxPropertyDescriptor(PlotSettings.PROPERTY_outlineVisible, "Outline Visible");
		outlineVisibleD.setDescription("Outline Visible");
		desc.add(outlineVisibleD);

		CheckBoxPropertyDescriptor domGridLineVis = new CheckBoxPropertyDescriptor(PlotSettings.PROPERTY_domainGridlineVisible, "Domain Grid Line Visible");
		domGridLineVis.setDescription("Domain Grid Line Visible");
		desc.add(domGridLineVis);

		CheckBoxPropertyDescriptor ranGridLineVis = new CheckBoxPropertyDescriptor(PlotSettings.PROPERTY_rangeGridlineVisible, "Range Grid Line Visible");
		ranGridLineVis.setDescription("Range Grid Line Visible");
		desc.add(ranGridLineVis);

		FontPropertyDescriptor fontD = new FontPropertyDescriptor(PlotSettings.PROPERTY_displayFont, "Display Font");
		fontD.setDescription("Display Font");
		desc.add(fontD);

		FontPropertyDescriptor tickLabelFontD = new FontPropertyDescriptor(PlotSettings.PROPERTY_tickLabelFont, "Tick Label Font");
		tickLabelFontD.setDescription("Tick Label Font");
		desc.add(tickLabelFontD);

		FloatPropertyDescriptor backAlphaD = new TransparencyPropertyDescriptor(PlotSettings.PROPERTY_backgroundImageAlpha, "Background Alpha");
		backAlphaD.setDescription("Background  Alpha");
		desc.add(backAlphaD);

		FloatPropertyDescriptor foreAlphaD = new TransparencyPropertyDescriptor(PlotSettings.PROPERTY_foregroundAlpha, "Foreground Alpha");
		foreAlphaD.setDescription("Foreground  Alpha");
		desc.add(foreAlphaD);

		DoublePropertyDescriptor la = new DegreePropertyDescriptor(PlotSettings.PROPERTY_labelRotation, "Label Rotation");
		la.setDescription("Label Rotation");
		desc.add(la);

		bia = new JSSEnumPropertyDescriptor(PlotSettings.PROPERTY_backgroundImageAlignment, "Background Image Alignment", JFreeChartAlignEnum.class, NullEnum.NOTNULL);
		bia.setDescription("Background Image Alignment");
		desc.add(bia);

		orientation = new JSSEnumPropertyDescriptor(PlotSettings.PROPERTY_orientation, "Orientation", JFreeChartPlotOrientationEnum.class, NullEnum.NOTNULL);
		orientation.setDescription("Orientation");
		desc.add(orientation);

		PadUtil.createPropertyDescriptors(desc, defaultsMap);

		defaultsMap.put(PlotSettings.PROPERTY_outlineVisible, Boolean.TRUE);
		defaultsMap.put(PlotSettings.PROPERTY_domainGridlineVisible, Boolean.TRUE);
		defaultsMap.put(PlotSettings.PROPERTY_rangeGridlineVisible, Boolean.TRUE);
		defaultsMap.put(PlotSettings.PROPERTY_backgroundImageAlignment, JFreeChartAlignEnum.TOP_LEFT);
		defaultsMap.put(PlotSettings.PROPERTY_orientation, JFreeChartPlotOrientationEnum.HORIZONTAL);
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
			return Misc.nvl(ps.getBackgroundImageAlignment(), new Integer(JFreeChartAlignEnum.NORTH_WEST.getValue()));
		if (id.equals(PlotSettings.PROPERTY_orientation))
			return orientation.getEnumValue(JFreeChartPlotOrientationEnum.getValue(ps.getOrientation()));

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
			ps.setBackgroundImageAlignment((Integer) value);
		else if (id.equals(PlotSettings.PROPERTY_orientation))
			ps.setOrientation(((JFreeChartPlotOrientationEnum) orientation.getEnumValue(value)).getJFreeChartValue());

		RectangleInsets ri = PadUtil.setPropertyValue(id, value, ps.getPadding());
		if (ri != null)
			ps.setPadding(ri);
	}
}
