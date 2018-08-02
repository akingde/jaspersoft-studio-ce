/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.genericElement;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.component.ComponentKey;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;

public class MComponentElement extends MGraphicElement {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private IPropertyDescriptor[] descriptors;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("component"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m cross tab.
	 */
	public MComponentElement() {
		super();
	}

	/**
	 * Instantiates a new m cross tab.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrCrosstab
	 *            the jr crosstab
	 * @param newIndex
	 *            the new index
	 */
	public MComponentElement(ANode parent, JRDesignComponentElement jrCrosstab, int newIndex) {
		super(parent, newIndex);
		setValue(jrCrosstab);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		String p = getElementNameProperty();
		return Misc.isNullOrEmpty(p) ? getIconDescriptor().getTitle() : p;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getImagePath()
	 */
	@Override
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.MGeneric#getToolTip()
	 */
	@Override
	public String getToolTip() {
		String componentKey = (String) getPropertyValue(JRDesignComponentElement.PROPERTY_COMPONENT_KEY);
		String tooltip = getIconDescriptor().getToolTip();
		if (componentKey != null) {
			tooltip += "\n" + componentKey;
		}
		return tooltip;
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

		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(JRDesignComponentElement.PROPERTY_COMPONENT_KEY,
				"Component Key");
		nameD.setDescription("Component key.");
		nameD.setReadOnly(true);
		desc.add(nameD);
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignComponentElement jrElement = (JRDesignComponentElement) getValue();
		if (id.equals(JRDesignComponentElement.PROPERTY_COMPONENT_KEY)) {
			ComponentKey ckey = jrElement.getComponentKey();
			if (ckey != null) {
				return ckey.getName() + " " + ckey.getNamespacePrefix() + " " + ckey.getNamespace();
			} else return null;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		super.setPropertyValue(id, value);
	}

}
