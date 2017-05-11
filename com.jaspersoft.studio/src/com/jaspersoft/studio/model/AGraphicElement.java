/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.PixelPropertyDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRConstants;

public abstract class AGraphicElement extends AMapElement {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AGraphicElement() {
		super();
	}

	public AGraphicElement(ANode parent, Object value, int index) {
		super(parent, value, index);
	}

	public static final String PROP_NAME = "name";
	public static final String PROP_DESCRIPTION = "description";
	public static final String PROP_X = "x";
	public static final String PROP_Y = "y";
	public static final String PROP_W = "w";
	public static final String PROP_H = "h";

	@Override
	public String getToolTip() {
		String tt = super.getToolTip();
		String desc = (String) getPropertyValue(AGraphicElement.PROP_DESCRIPTION);
		if (!Misc.isNullOrEmpty(desc))
			tt += "\n" + desc;
		return tt;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		PixelPropertyDescriptor xd = new PixelPropertyDescriptor(PROP_X, Messages.common_left);
		xd.setCategory(Messages.common_graphic);
		xd.setDescription(Messages.common_left);
		desc.add(xd);

		PixelPropertyDescriptor yd = new PixelPropertyDescriptor(PROP_Y, Messages.common_top);
		yd.setCategory(Messages.common_graphic);
		yd.setDescription(Messages.common_top);
		desc.add(yd);

		PixelPropertyDescriptor wd = new PixelPropertyDescriptor(PROP_W, Messages.MGraphicElement_width);
		wd.setCategory(Messages.common_size);
		wd.setDescription(Messages.MGraphicElement_width_description);
		desc.add(wd);

		PixelPropertyDescriptor hd = new PixelPropertyDescriptor(PROP_H, Messages.common_height);
		hd.setCategory(Messages.common_size);
		hd.setDescription(Messages.MGraphicElement_height_description);
		desc.add(hd);

		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(PROP_NAME, Messages.common_name);
		nameD.setDescription(Messages.common_name);
		desc.add(nameD);

		NTextPropertyDescriptor description = new NTextPropertyDescriptor(PROP_DESCRIPTION,
				Messages.common_descriptionLabel, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL);
		description.setDescription(Messages.common_description);
		desc.add(description);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(PROP_X, new DefaultValue(10, false));
		defaultsMap.put(PROP_Y, new DefaultValue(10, false));
		defaultsMap.put(PROP_W, new DefaultValue(100, false));
		defaultsMap.put(PROP_H, new DefaultValue(100, false));
		return defaultsMap;
	}

	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROP_X) && value instanceof String)
			value = new Integer((String) value);
		else if (id.equals(PROP_Y) && value instanceof String)
			value = new Integer((String) value);
		else if (id.equals(PROP_W) && value instanceof String)
			value = new Integer((String) value);
		else if (id.equals(PROP_H) && value instanceof String)
			value = new Integer((String) value);

		super.setPropertyValue(id, value);
	}

}
