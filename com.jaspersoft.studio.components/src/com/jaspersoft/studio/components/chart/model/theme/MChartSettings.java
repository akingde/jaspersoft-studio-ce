package com.jaspersoft.studio.components.chart.model.theme;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.chartthemes.simple.ChartSettings;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.components.chart.model.enums.JFreeChartAlignEnum;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.FloatPropertyDescriptor;
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
		CheckBoxPropertyDescriptor tantiAliasD = new CheckBoxPropertyDescriptor(ChartSettings.PROPERTY_textAntiAlias, "Text Anti Alias");
		tantiAliasD.setDescription("Text anti alias");
		desc.add(tantiAliasD);

		CheckBoxPropertyDescriptor antiAliasD = new CheckBoxPropertyDescriptor(ChartSettings.PROPERTY_antiAlias, "Anti Alias");
		antiAliasD.setDescription("Anti alias");
		desc.add(antiAliasD);

		CheckBoxPropertyDescriptor borderVisibleD = new CheckBoxPropertyDescriptor(ChartSettings.PROPERTY_borderVisible, "Border Visible");
		borderVisibleD.setDescription("Border Visible");
		desc.add(borderVisibleD);

		FloatPropertyDescriptor backAlphaD = new TransparencyPropertyDescriptor(ChartSettings.PROPERTY_backgroundImageAlpha, "Background Image Alpha");
		backAlphaD.setDescription("Background Image Alpha");
		desc.add(backAlphaD);

		bia = new JSSEnumPropertyDescriptor(ChartSettings.PROPERTY_backgroundImageAlignment, "Background Image Alignment", JFreeChartAlignEnum.class, NullEnum.NOTNULL);
		bia.setDescription("Background Image Alignment");
		desc.add(bia);

		PadUtil.createPropertyDescriptors(desc, defaultsMap);

		borderVisibleD.setCategory(Messages.BordersSection_border);

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

		RectangleInsets ri = PadUtil.setPropertyValue(id, value, cs.getPadding());
		if (ri != null)
			cs.setPadding(ri);
	}
}
