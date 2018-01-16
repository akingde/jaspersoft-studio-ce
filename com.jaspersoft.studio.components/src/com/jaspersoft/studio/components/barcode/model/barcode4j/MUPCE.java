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
import com.jaspersoft.studio.property.descriptors.JSSComboPropertyDescriptor;

import net.sf.jasperreports.components.barcode4j.UPCEComponent;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

public class MUPCE extends MBarcode4j {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;

	public MUPCE() {
		super();
	}

	public MUPCE(ANode parent, JRDesignComponentElement jrBarcode, int newIndex) {
		super(parent, jrBarcode, newIndex);
	}

	@Override
	public JRDesignComponentElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignComponentElement el = new JRDesignComponentElement();
		UPCEComponent component = new UPCEComponent();
		JRDesignExpression exp = new JRDesignExpression();
		exp.setText("\"1234567\""); //$NON-NLS-1$
		component.setCodeExpression(exp);
		el.setComponent(component);
		el.setComponentKey(new ComponentKey("http://jasperreports.sourceforge.net/jasperreports/components", "jr", "UPCE")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		
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
				UPCEComponent.PROPERTY_CHECKSUM_MODE,
				Messages.common_checksum_mode, ChecksumMode.getItems());
		checksumModeD.setDescription(Messages.MUPCE_checksum_mode_description);
		desc.add(checksumModeD);

		checksumModeD.setCategory(Messages.MUPCE_properties_category);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		UPCEComponent jrList = (UPCEComponent) jrElement.getComponent();

		if (id.equals(UPCEComponent.PROPERTY_CHECKSUM_MODE))
			return ChecksumMode.getPos4ChecksumMode(jrList.getChecksumMode());
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		UPCEComponent jrList = (UPCEComponent) jrElement.getComponent();

		if (id.equals(UPCEComponent.PROPERTY_CHECKSUM_MODE))
			jrList.setChecksumMode(ChecksumMode
					.getChecksumMode4Pos((Integer) value));
		else
			super.setPropertyValue(id, value);
	}
	
	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(UPCEComponent.PROPERTY_CHECKSUM_MODE);
		return properties;
	}
	
	@Override
	public void trasnferProperties(JRElement target){
		super.trasnferProperties(target);
		
		JRDesignComponentElement jrSourceElement = (JRDesignComponentElement) getValue();
		UPCEComponent jrSourceBarcode = (UPCEComponent) jrSourceElement.getComponent();
		
		JRDesignComponentElement jrTargetElement = (JRDesignComponentElement) target;
		UPCEComponent jrTargetBarcode = (UPCEComponent) jrTargetElement.getComponent();
		
		jrTargetBarcode.setChecksumMode(jrSourceBarcode.getChecksumMode());
	}
}
