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

import net.sf.jasperreports.components.barcode4j.POSTNETComponent;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;

public class MPOSTNET extends MBarcode4j {
	public MPOSTNET() {
		super();
	}

	public MPOSTNET(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		el.setComponent(new POSTNETComponent());
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", //$NON-NLS-1$ //$NON-NLS-2$
				"POSTNET")); //$NON-NLS-1$
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
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		DoublePropertyDescriptor shortBarHeightD = new DoublePropertyDescriptor(POSTNETComponent.PROPERTY_SHORT_BAR_HEIGHT,
				Messages.MPOSTNET_short_bar_height);
		shortBarHeightD.setDescription(Messages.MPOSTNET_short_bar_height_description);
		desc.add(shortBarHeightD);

		DoublePropertyDescriptor intercharD = new DoublePropertyDescriptor(POSTNETComponent.PROPERTY_INTERCHAR_GAP_WIDTH,
				Messages.common_interchar_gap_width);
		intercharD.setDescription(Messages.MPOSTNET_interchar_gap_width_description);
		desc.add(intercharD);

		CheckBoxPropertyDescriptor displayChecksumD = new CheckBoxPropertyDescriptor(
				POSTNETComponent.PROPERTY_DISPLAY_CHECKSUM, Messages.common_display_checksum, NullEnum.NULL);
		displayChecksumD.setDescription(Messages.MPOSTNET_display_checksum_description);
		desc.add(displayChecksumD);

		ComboBoxPropertyDescriptor checksumModeD = new ComboBoxPropertyDescriptor(POSTNETComponent.PROPERTY_CHECKSUM_MODE,
				Messages.common_checksum_mode, ChecksumMode.getItems());
		checksumModeD.setDescription(Messages.MPOSTNET_checksum_mode_description);
		desc.add(checksumModeD);

		ComboBoxPropertyDescriptor baselinePositionD = new ComboBoxPropertyDescriptor(
				POSTNETComponent.PROPERTY_BASELINE_POSITION, Messages.MPOSTNET_baseline_position, BaselinePosition.getItems());
		baselinePositionD.setDescription(Messages.MPOSTNET_baseline_position_description);
		desc.add(baselinePositionD);

		shortBarHeightD.setCategory(Messages.MPOSTNET_properties_category);
		baselinePositionD.setCategory(Messages.MPOSTNET_properties_category);
		checksumModeD.setCategory(Messages.MPOSTNET_properties_category);
		displayChecksumD.setCategory(Messages.MPOSTNET_properties_category);
		intercharD.setCategory(Messages.MPOSTNET_properties_category);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		POSTNETComponent jrList = (POSTNETComponent) jrElement.getComponent();

		if (id.equals(POSTNETComponent.PROPERTY_SHORT_BAR_HEIGHT))
			return jrList.getShortBarHeight();
		if (id.equals(POSTNETComponent.PROPERTY_INTERCHAR_GAP_WIDTH))
			return jrList.getIntercharGapWidth();
		if (id.equals(POSTNETComponent.PROPERTY_DISPLAY_CHECKSUM))
			return jrList.getDisplayChecksum();
		if (id.equals(POSTNETComponent.PROPERTY_CHECKSUM_MODE))
			return ChecksumMode.getPos4ChecksumMode(jrList.getChecksumMode());
		if (id.equals(POSTNETComponent.PROPERTY_BASELINE_POSITION))
			return BaselinePosition.getPos4BaselinePosition(jrList.getBaselinePosition());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		POSTNETComponent jrList = (POSTNETComponent) jrElement.getComponent();

		if (id.equals(POSTNETComponent.PROPERTY_SHORT_BAR_HEIGHT))
			jrList.setShortBarHeight((Double) value);
		else if (id.equals(POSTNETComponent.PROPERTY_INTERCHAR_GAP_WIDTH))
			jrList.setIntercharGapWidth((Double) value);
		else if (id.equals(POSTNETComponent.PROPERTY_CHECKSUM_MODE))
			jrList.setChecksumMode(ChecksumMode.getChecksumMode4Pos((Integer) value));
		else if (id.equals(POSTNETComponent.PROPERTY_BASELINE_POSITION))
			jrList.setChecksumMode(BaselinePosition.getBaselinePosition4Pos((Integer) value));

		else if (id.equals(POSTNETComponent.PROPERTY_DISPLAY_CHECKSUM))
			jrList.setDisplayChecksum((Boolean) value);
		else
			super.setPropertyValue(id, value);
	}
}
