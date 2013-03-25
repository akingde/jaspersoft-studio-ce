package com.jaspersoft.studio.components.chart.model.theme;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.type.EdgeEnum;
import net.sf.jasperreports.chartthemes.simple.TitleSettings;
import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.jfree.ui.RectangleInsets;

import com.jaspersoft.studio.components.chart.model.enums.JFreeChartHorizontalAlignmentEnum;
import com.jaspersoft.studio.components.chart.model.enums.JFreeChartVerticalAlignmentEnum;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.text.MFont;
import com.jaspersoft.studio.model.text.MFontUtil;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.FontPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSEnumPropertyDescriptor;

public class MTittleSettings extends APropertyNode {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private String displayText;

	public MTittleSettings(MChartThemeSettings parent, TitleSettings ts, String displayText) {
		super(parent, -1);
		setValue(ts);
		this.displayText = displayText;
	}

	@Override
	public TitleSettings getValue() {
		return (TitleSettings) super.getValue();
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
		CheckBoxPropertyDescriptor showTitleD = new CheckBoxPropertyDescriptor(TitleSettings.PROPERTY_showTitle, "Show Title");
		showTitleD.setDescription("Show Title");
		desc.add(showTitleD);

		FontPropertyDescriptor fontD = new FontPropertyDescriptor(TitleSettings.PROPERTY_font, com.jaspersoft.studio.messages.Messages.common_font);
		fontD.setDescription(com.jaspersoft.studio.messages.Messages.common_font);
		desc.add(fontD);

		posD = new JSSEnumPropertyDescriptor(TitleSettings.PROPERTY_position, "Position", EdgeEnum.class, NullEnum.NULL);
		posD.setDescription("Position");
		desc.add(posD);

		hp = new JSSEnumPropertyDescriptor(TitleSettings.PROPERTY_horizontalAlignment, "Horizontal Alignment", JFreeChartHorizontalAlignmentEnum.class, NullEnum.NULL);
		hp.setDescription("Horizontal Alignment");
		desc.add(hp);

		vp = new JSSEnumPropertyDescriptor(TitleSettings.PROPERTY_verticalAlignment, "Vertical Alignment", JFreeChartVerticalAlignmentEnum.class, NullEnum.NULL);
		vp.setDescription("Vertical Alignment");
		desc.add(vp);

		PadUtil.createPropertyDescriptors(desc, defaultsMap);

		defaultsMap.put(TitleSettings.PROPERTY_showTitle, Boolean.TRUE);
		defaultsMap.put(TitleSettings.PROPERTY_position, EdgeEnum.TOP);
		defaultsMap.put(TitleSettings.PROPERTY_horizontalAlignment, JFreeChartHorizontalAlignmentEnum.LEFT);
		defaultsMap.put(TitleSettings.PROPERTY_verticalAlignment, JFreeChartVerticalAlignmentEnum.TOP);
	}

	private static JSSEnumPropertyDescriptor posD;
	private static JSSEnumPropertyDescriptor hp;
	private static JSSEnumPropertyDescriptor vp;
	private MFont clFont;

	@Override
	public Object getPropertyValue(Object id) {
		TitleSettings ts = getValue();
		if (id.equals(TitleSettings.PROPERTY_showTitle))
			return ts.getShowTitle();
		if (id.equals(TitleSettings.PROPERTY_font)) {
			clFont = MFontUtil.getMFont(clFont, ts.getFont(), null, this);
			return clFont;
		}
		if (id.equals(TitleSettings.PROPERTY_position))
			return posD.getEnumValue(ts.getPositionValue());
		if (id.equals(TitleSettings.PROPERTY_horizontalAlignment))
			return hp.getEnumValue(JFreeChartHorizontalAlignmentEnum.getValue(ts.getHorizontalAlignment()));
		if (id.equals(TitleSettings.PROPERTY_verticalAlignment))
			return vp.getEnumValue(JFreeChartVerticalAlignmentEnum.getValue(ts.getVerticalAlignment()));

		Object pad = PadUtil.getPropertyValue(id, ts.getPadding());
		if (pad != null)
			return pad;
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		TitleSettings ts = getValue();
		if (id.equals(TitleSettings.PROPERTY_showTitle))
			ts.setShowTitle((Boolean) value);
		else if (id.equals(TitleSettings.PROPERTY_font))
			ts.setFont(MFontUtil.setMFont(value));
		else if (id.equals(TitleSettings.PROPERTY_position))
			ts.setPosition((EdgeEnum) posD.getEnumValue(value));
		else if (id.equals(TitleSettings.PROPERTY_horizontalAlignment))
			ts.setHorizontalAlignment(((JFreeChartHorizontalAlignmentEnum) posD.getEnumValue(value)).getJFreeChartValue());
		else if (id.equals(TitleSettings.PROPERTY_verticalAlignment))
			ts.setVerticalAlignment(((JFreeChartVerticalAlignmentEnum) posD.getEnumValue(value)).getJFreeChartValue());

		RectangleInsets ri = PadUtil.setPropertyValue(id, value, ts.getPadding());
		if (ri != null)
			ts.setPadding(ri);
	}
}
