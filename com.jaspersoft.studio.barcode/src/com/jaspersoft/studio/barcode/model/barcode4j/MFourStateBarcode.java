package com.jaspersoft.studio.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barcode4j.FourStateBarcodeComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;

public class MFourStateBarcode extends MBarcode4j {
	public MFourStateBarcode() {
		super();
	}

	public MFourStateBarcode(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
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

		ComboBoxPropertyDescriptor checksumModeD = new ComboBoxPropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE, "Checksum Mode", ChecksumMode.getItems());
		checksumModeD.setDescription("Checksum mode.");
		desc.add(checksumModeD);

		DoublePropertyDescriptor intercharD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH, "Interchar Gap Width");
		intercharD.setDescription("Interchar gap width.");
		desc.add(intercharD);

		DoublePropertyDescriptor ascenderHeightD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT, "Ascender Height");
		ascenderHeightD.setDescription("Interchar gap width.");
		desc.add(ascenderHeightD);

		DoublePropertyDescriptor trackHeightD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT, "Track Height");
		trackHeightD.setDescription("Interchar gap width.");
		desc.add(trackHeightD);

		checksumModeD.setCategory("Barcode Properties");
		intercharD.setCategory("Barcode Properties");
		ascenderHeightD.setCategory("Barcode Properties");
		trackHeightD.setCategory("Barcode Properties");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		FourStateBarcodeComponent jrList = (FourStateBarcodeComponent) jrElement.getComponent();

		if (id.equals(FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH))
			return jrList.getIntercharGapWidth();

		if (id.equals(FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT))
			return jrList.getAscenderHeight();
		if (id.equals(FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT))
			return jrList.getTrackHeight();

		if (id.equals(FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE))
			return ChecksumMode.getPos4ChecksumMode(jrList.getChecksumMode());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		FourStateBarcodeComponent jrList = (FourStateBarcodeComponent) jrElement.getComponent();

		if (id.equals(FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH))
			jrList.setIntercharGapWidth((Double) value);
		else if (id.equals(FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE))
			jrList.setChecksumMode(ChecksumMode.getChecksumMode4Pos((Integer) value));

		else if (id.equals(FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT))
			jrList.setAscenderHeight((Double) value);
		else if (id.equals(FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT))
			jrList.setTrackHeight((Double) value);
		else
			super.setPropertyValue(id, value);
	}
}
