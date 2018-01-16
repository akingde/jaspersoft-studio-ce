/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;

import net.sf.jasperreports.components.barcode4j.Barcode4jComponent;
import net.sf.jasperreports.components.barcode4j.Code39Component;
import net.sf.jasperreports.components.barcode4j.Interleaved2Of5Component;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

public class MInterleaved2Of5 extends MBarcode4j {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private static IPropertyDescriptor[] descriptors;

	public MInterleaved2Of5() {
		super();
	}

	public MInterleaved2Of5(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		Interleaved2Of5Component component = new Interleaved2Of5Component();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("\"123456789\""); //$NON-NLS-1$
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", //$NON-NLS-1$
				"Interleaved2Of5"));

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), el);
		}

		return el;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		JSSComboPropertyDescriptor checksumModeD = new JSSComboPropertyDescriptor(
				Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE, Messages.common_checksum_mode,
				ChecksumMode.getItems());
		checksumModeD.setDescription(Messages.MInterleaved2Of5_checksum_mode_description);
		desc.add(checksumModeD);

		DoublePropertyDescriptor wideFactorD = new DoublePropertyDescriptor(
				Interleaved2Of5Component.PROPERTY_WIDE_FACTOR, Messages.common_wide_factor);
		wideFactorD.setDescription(Messages.MInterleaved2Of5_wide_factor_description);
		desc.add(wideFactorD);
		wideFactorD.setBounds(0, 1);

		CheckBoxPropertyDescriptor displayChecksumD = new CheckBoxPropertyDescriptor(
				Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM, Messages.common_display_checksum, NullEnum.NULL);
		displayChecksumD.setDescription(Messages.MInterleaved2Of5_display_checksum_description);
		desc.add(displayChecksumD);

		DoublePropertyDescriptor vertQuietZoneD = new DoublePropertyDescriptor(
				Barcode4jComponent.PROPERTY_VERTICAL_QUIET_ZONE, Messages.MBarcode4j_vertical_quiet_zone);
		vertQuietZoneD.setDescription(Messages.MBarcode4j_vertical_quiet_zone_description);
		desc.add(vertQuietZoneD);
		vertQuietZoneD.setCategory(Messages.common_properties_category);

		wideFactorD.setCategory(Messages.MInterleaved2Of5_properties_category);
		checksumModeD.setCategory(Messages.MInterleaved2Of5_properties_category);
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

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(Interleaved2Of5Component.PROPERTY_CHECKSUM_MODE);
		properties.add(Interleaved2Of5Component.PROPERTY_WIDE_FACTOR);
		properties.add(Interleaved2Of5Component.PROPERTY_DISPLAY_CHECKSUM);
		return properties;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		Interleaved2Of5Component jrSourceBarcode = (Interleaved2Of5Component) jrSourceElement.getComponent();

		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		Interleaved2Of5Component jrTargetBarcode = (Interleaved2Of5Component) jrTargetElement.getComponent();

		jrTargetBarcode.setChecksumMode(jrSourceBarcode.getChecksumMode());
		jrTargetBarcode.setWideFactor(jrSourceBarcode.getWideFactor());
		jrTargetBarcode.setDisplayChecksum(jrSourceBarcode.isDisplayChecksum());
	}
}
