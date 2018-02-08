/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.editor.defaults.DefaultManager;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.JSSStyleResolver;
import com.jaspersoft.studio.property.descriptors.IntegerPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRElement;
import net.sf.jasperreports.engine.JRRectangle;
import net.sf.jasperreports.engine.base.JRBaseStyle;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignRectangle;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * The Class MRectangle.
 */
public class MRectangle extends MGraphicElementLinePen {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private static IPropertyDescriptor[] descriptors;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("rectangle"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m rectangle.
	 */
	public MRectangle() {
		super();
	}

	/**
	 * Instantiates a new m rectangle.
	 * 
	 * @param parent
	 *            the parent
	 * @param jrRectangle
	 *            the jr rectangle
	 * @param newImage
	 *            the new image
	 */
	public MRectangle(ANode parent, JRDesignRectangle jrRectangle, int newImage) {
		super(parent, newImage);
		setValue(jrRectangle);
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

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#graphicElement");

		IntegerPropertyDescriptor rD = new IntegerPropertyDescriptor(JRBaseStyle.PROPERTY_RADIUS,
				Messages.common_radius);
		rD.setCategory(Messages.MRectangle_rectangle_properties_category);
		rD.setDescription(Messages.MRectangle_radius_description);
		rD.setBounds(0, Integer.MAX_VALUE);
		desc.add(rD);

		setHelpPrefix(desc, "net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#rectangle");
	}

	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();

		defaultsMap.put(JRBaseStyle.PROPERTY_RADIUS, new DefaultValue(null, true));

		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignRectangle jrElement = (JRDesignRectangle) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_RADIUS))
			return jrElement.getOwnRadius();
		return super.getPropertyValue(id);
	}

	@Override
	public Object getPropertyActualValue(Object id) {
		JRDesignRectangle jrElement = (JRDesignRectangle) getValue();
		JSSStyleResolver resolver = getStyleResolver();
		if (id.equals(JRBaseStyle.PROPERTY_RADIUS))
			return resolver.getRadius(jrElement);
		return super.getPropertyActualValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignRectangle jrElement = (JRDesignRectangle) getValue();
		if (id.equals(JRBaseStyle.PROPERTY_RADIUS)) {
			Integer intv = (Integer) value;
			if (intv != null) {
				jrElement.setRadius(Math.abs(intv.intValue()));
			} else {
				jrElement.setRadius(null);
			}
		} else
			super.setPropertyValue(id, value);
	}

	@Override
	public int getDefaultHeight() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_HEIGHT);
		return defaultValue != null ? (Integer) defaultValue : 50;
	}

	@Override
	public int getDefaultWidth() {
		Object defaultValue = DefaultManager.INSTANCE.getDefaultPropertiesValue(this.getClass(),
				JRDesignElement.PROPERTY_WIDTH);
		return defaultValue != null ? (Integer) defaultValue : 100;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.model.MGeneric#createJRElement(net.sf.jasperreports.
	 * engine.design.JasperDesign)
	 */
	@Override
	public JRDesignElement createJRElement(JasperDesign jasperDesign, boolean applyDefault) {
		JRDesignRectangle jrDesignRectangle = new JRDesignRectangle(jasperDesign);

		if (applyDefault) {
			DefaultManager.INSTANCE.applyDefault(this.getClass(), jrDesignRectangle);
		}

		jrDesignRectangle.setWidth(getDefaultWidth());
		jrDesignRectangle.setHeight(getDefaultHeight());
		return jrDesignRectangle;
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
		return getIconDescriptor().getToolTip();
	}

	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> result = super.generateGraphicalProperties();
		result.add(JRBaseStyle.PROPERTY_FILL);
		result.add(JRBaseStyle.PROPERTY_RADIUS);
		return result;
	}

	@Override
	public void trasnferProperties(JRElement target) {
		super.trasnferProperties(target);

		JRRectangle jrSource = (JRRectangle) getValue();
		if (jrSource != null) {
			JRRectangle jrTarget = (JRRectangle) target;
			jrTarget.setFill(jrSource.getOwnFillValue());
			jrTarget.setRadius(jrSource.getOwnRadius());
		}
	}
}
