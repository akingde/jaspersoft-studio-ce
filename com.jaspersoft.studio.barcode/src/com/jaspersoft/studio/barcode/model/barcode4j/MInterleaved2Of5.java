/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.barcode.model.barcode4j;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;

public class MInterleaved2Of5 extends MBarcode4j {
	public MInterleaved2Of5() {
		super();
	}

	public MInterleaved2Of5(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		el.setComponent(new Interleaved2Of5Component());
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr",
				"Interleaved2Of5"));
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

		ComboBoxPropertyDescriptor checksumModeD = new ComboBoxPropertyDescriptor(
				Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE, "Checksum Mode", ChecksumMode.getItems());
		checksumModeD.setDescription("Checksum mode.");
		desc.add(checksumModeD);

		DoublePropertyDescriptor wideFactorD = new DoublePropertyDescriptor(Interleaved2Of5Component.PROPERTY_WIDE_FACTOR,
				"Wide Factor");
		wideFactorD.setDescription("Wide Factor.");
		desc.add(wideFactorD);

		CheckBoxPropertyDescriptor displayChecksumD = new CheckBoxPropertyDescriptor(
				Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM, "Display Checksum", NullEnum.NULL);
		displayChecksumD.setDescription("Display Checksum.");
		desc.add(displayChecksumD);

		wideFactorD.setCategory("Barcode Properties, Interleaved2Of5");
		checksumModeD.setCategory("Barcode Properties, Interleaved2Of5");
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		Interleaved2Of5Component jrList = (Interleaved2Of5Component) jrElement.getComponent();

		if (id.equals(Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE))
			return ChecksumMode.getPos4ChecksumMode(jrList.getChecksumMode());
		if (id.equals(Interleaved2Of5Component.PROPERTY_WIDE_FACTOR))
			return jrList.getWideFactor();
		if (id.equals(Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM))
			return jrList.isDisplayChecksum();

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		Interleaved2Of5Component jrList = (Interleaved2Of5Component) jrElement.getComponent();

		if (id.equals(Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE))
			jrList.setChecksumMode(ChecksumMode.getChecksumMode4Pos((Integer) value));
		else if (id.equals(Interleaved2Of5Component.PROPERTY_WIDE_FACTOR))
			jrList.setWideFactor((Double) value);
		else if (id.equals(Code39Component.PROPERTY_DISPLAY_CHECKSUM))
			jrList.setDisplayChecksum((Boolean) value);
		else
			super.setPropertyValue(id, value);
	}
}
