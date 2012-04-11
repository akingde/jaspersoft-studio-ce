/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barcode4j.FourStateBarcodeComponent;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;

public class MFourStateBarcode extends MBarcode4j {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MFourStateBarcode() {
		super();
	}

	public MFourStateBarcode(ANode parent, JRDesignComponentElement jrBarcode,
			int newIndex) {
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		ComboBoxPropertyDescriptor checksumModeD = new ComboBoxPropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE,
				Messages.common_checksum_mode, ChecksumMode.getItems());
		checksumModeD
				.setDescription(Messages.MFourStateBarcode_checksum_mode_description);
		desc.add(checksumModeD);

		DoublePropertyDescriptor intercharD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH,
				Messages.common_interchar_gap_width);
		intercharD
				.setDescription(Messages.MFourStateBarcode_interchar_gap_width_description);
		desc.add(intercharD);

		DoublePropertyDescriptor ascenderHeightD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT,
				Messages.MFourStateBarcode_ascender_height);
		ascenderHeightD
				.setDescription(Messages.MFourStateBarcode_ascender_height_description);
		desc.add(ascenderHeightD);

		DoublePropertyDescriptor trackHeightD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT,
				Messages.MFourStateBarcode_track_height);
		trackHeightD
				.setDescription(Messages.MFourStateBarcode_track_height_description);
		desc.add(trackHeightD);

		checksumModeD.setCategory(Messages.common_properties_category);
		intercharD.setCategory(Messages.common_properties_category);
		ascenderHeightD.setCategory(Messages.common_properties_category);
		trackHeightD.setCategory(Messages.common_properties_category);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		FourStateBarcodeComponent jrList = (FourStateBarcodeComponent) jrElement
				.getComponent();

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
		FourStateBarcodeComponent jrList = (FourStateBarcodeComponent) jrElement
				.getComponent();

		if (id.equals(FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH))
			jrList.setIntercharGapWidth((Double) value);
		else if (id.equals(FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE))
			jrList.setChecksumMode(ChecksumMode
					.getChecksumMode4Pos((Integer) value));

		else if (id.equals(FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT))
			jrList.setAscenderHeight((Double) value);
		else if (id.equals(FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT))
			jrList.setTrackHeight((Double) value);
		else
			super.setPropertyValue(id, value);
	}
}
