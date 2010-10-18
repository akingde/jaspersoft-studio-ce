package com.jaspersoft.studio.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;

public class MCode39 extends MBarcode4j {
	public MCode39() {
		super();
	}

	public MCode39(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		el.setComponent(new Code39Component());
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", "Code39"));
		return el;
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);
		DoublePropertyDescriptor wideFactorD = new DoublePropertyDescriptor(Code39Component.PROPERTY_WIDE_FACTOR,
				"Wide Factor");
		wideFactorD.setDescription("Wide Factor.");
		desc.add(wideFactorD);

		DoublePropertyDescriptor intercharD = new DoublePropertyDescriptor(Code39Component.PROPERTY_INTERCHAR_GAP_WIDTH,
				"Interchar Gap Width");
		intercharD.setDescription("Interchar gap width.");
		desc.add(intercharD);

		CheckBoxPropertyDescriptor displayChecksumD = new CheckBoxPropertyDescriptor(
				Code39Component.PROPERTY_DISPLAY_CHECKSUM, "Display Checksum", NullEnum.NULL);
		displayChecksumD.setDescription("Display Checksum.");
		desc.add(displayChecksumD);

		CheckBoxPropertyDescriptor displayStartStopD = new CheckBoxPropertyDescriptor(
				Code39Component.PROPERTY_DISPLAY_START_STOP, "Display Start Stop", NullEnum.NULL);
		displayStartStopD.setDescription("Display start stop.");
		desc.add(displayStartStopD);

		CheckBoxPropertyDescriptor extendedCharsetD = new CheckBoxPropertyDescriptor(
				Code39Component.PROPERTY_EXTENDED_CHARSET_ENABLED, "Extended Charset Enabled", NullEnum.NULL);
		extendedCharsetD.setDescription("Extended charset enabled.");
		desc.add(extendedCharsetD);

		ComboBoxPropertyDescriptor checksumModeD = new ComboBoxPropertyDescriptor(Code39Component.PROPERTY_CHECKSUM_MODE,
				"Checksum Mode", ChecksumMode.getItems());
		checksumModeD.setDescription("Checksum mode.");
		desc.add(checksumModeD);

		checksumModeD.setCategory("Barcode Properties, Code39");
		extendedCharsetD.setCategory("Barcode Properties, Code39");
		displayChecksumD.setCategory("Barcode Properties, Code39");
		displayStartStopD.setCategory("Barcode Properties, Code39");
		wideFactorD.setCategory("Barcode Properties, Code39");
		intercharD.setCategory("Barcode Properties, Code39");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		Code39Component jrList = (Code39Component) jrElement.getComponent();

		if (id.equals(Code39Component.PROPERTY_WIDE_FACTOR))
			return jrList.getWideFactor();
		if (id.equals(Code39Component.PROPERTY_INTERCHAR_GAP_WIDTH))
			return jrList.getIntercharGapWidth();

		if (id.equals(Code39Component.PROPERTY_DISPLAY_CHECKSUM))
			return jrList.isDisplayChecksum();
		if (id.equals(Code39Component.PROPERTY_DISPLAY_START_STOP))
			return jrList.isDisplayStartStop();
		if (id.equals(Code39Component.PROPERTY_EXTENDED_CHARSET_ENABLED))
			return jrList.isExtendedCharSetEnabled();

		if (id.equals(Code39Component.PROPERTY_CHECKSUM_MODE))
			return ChecksumMode.getPos4ChecksumMode(jrList.getChecksumMode());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		Code39Component jrList = (Code39Component) jrElement.getComponent();

		if (id.equals(Code39Component.PROPERTY_WIDE_FACTOR))
			jrList.setWideFactor((Double) value);
		else if (id.equals(Code39Component.PROPERTY_INTERCHAR_GAP_WIDTH))
			jrList.setIntercharGapWidth((Double) value);

		else if (id.equals(Code39Component.PROPERTY_CHECKSUM_MODE))
			jrList.setChecksumMode(ChecksumMode.getChecksumMode4Pos((Integer) value));

		else if (id.equals(Code39Component.PROPERTY_DISPLAY_CHECKSUM))
			jrList.setDisplayChecksum((Boolean) value);
		else if (id.equals(Code39Component.PROPERTY_DISPLAY_START_STOP))
			jrList.setDisplayStartStop((Boolean) value);
		else if (id.equals(Code39Component.PROPERTY_EXTENDED_CHARSET_ENABLED))
			jrList.setExtendedCharSetEnabled((Boolean) value);
		else
			super.setPropertyValue(id, value);
	}
}
