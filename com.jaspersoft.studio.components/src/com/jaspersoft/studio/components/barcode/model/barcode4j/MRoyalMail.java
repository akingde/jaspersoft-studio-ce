/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model.barcode4j;

import net.sf.jasperreports.components.barcode4j.FourStateBarcodeComponent;
import net.sf.jasperreports.components.barcode4j.RoyalMailCustomerComponent;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import java.util.HashSet;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptors.DoublePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;

public class MRoyalMail extends MBarcode4j {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private static IPropertyDescriptor[] descriptors;

	public MRoyalMail() {
		super();
	}

	public MRoyalMail(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
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
				FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE, Messages.common_checksum_mode,
				ChecksumMode.getItems());
		checksumModeD.setDescription(Messages.MFourStateBarcode_checksum_mode_description);
		desc.add(checksumModeD);

		DoublePropertyDescriptor intercharD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH, Messages.common_interchar_gap_width);
		intercharD.setDescription(Messages.MFourStateBarcode_interchar_gap_width_description);
		desc.add(intercharD);

		DoublePropertyDescriptor ascenderHeightD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT, Messages.MFourStateBarcode_ascender_height);
		ascenderHeightD.setDescription(Messages.MFourStateBarcode_ascender_height_description);
		desc.add(ascenderHeightD);

		DoublePropertyDescriptor trackHeightD = new DoublePropertyDescriptor(
				FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT, Messages.MFourStateBarcode_track_height);
		trackHeightD.setDescription(Messages.MFourStateBarcode_track_height_description);
		desc.add(trackHeightD);

		checksumModeD.setCategory(Messages.common_properties_category);
		intercharD.setCategory(Messages.common_properties_category);
		ascenderHeightD.setCategory(Messages.common_properties_category);
		trackHeightD.setCategory(Messages.common_properties_category);
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

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(FourStateBarcodeComponent.PROPERTY_INTERCHAR_GAP_WIDTH);
		properties.add(FourStateBarcodeComponent.PROPERTY_CHECKSUM_MODE);
		properties.add(FourStateBarcodeComponent.PROPERTY_ASCENDER_HEIGHT);
		properties.add(FourStateBarcodeComponent.PROPERTY_TRACK_HEIGHT);
		return properties;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		FourStateBarcodeComponent jrSourceBarcode = (FourStateBarcodeComponent) jrSourceElement.getComponent();

		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		FourStateBarcodeComponent jrTargetBarcode = (FourStateBarcodeComponent) jrTargetElement.getComponent();

		jrTargetBarcode.setIntercharGapWidth(jrSourceBarcode.getIntercharGapWidth());
		jrTargetBarcode.setChecksumMode(jrSourceBarcode.getChecksumMode());
		jrTargetBarcode.setAscenderHeight(jrSourceBarcode.getAscenderHeight());
		jrTargetBarcode.setTrackHeight(jrSourceBarcode.getTrackHeight());
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		RoyalMailCustomerComponent component = new RoyalMailCustomerComponent();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("\"123456789\""); //$NON-NLS-1$
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", //$NON-NLS-1$
				"RoyalMailCustomer"));

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), el);
		}

		return el;
	}
}
