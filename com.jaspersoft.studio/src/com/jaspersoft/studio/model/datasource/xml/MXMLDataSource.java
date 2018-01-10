/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.datasource.xml;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.datasource.AMFileDataSource;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MXMLDataSource extends AMFileDataSource {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;
	
	public static final String PROPERTY_XPATHSELECT = "PROPERTY_XPATHSELECT"; //$NON-NLS-1$

	public static final String PROPERTY_XPATHLOCALE = "PROPERTY_XPATHLOCALE"; //$NON-NLS-1$

	public static final String PROPERTY_XPATHTIMEZONE = "PROPERTY_XPATHTIMEZONE"; //$NON-NLS-1$
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	private String xpathselect;
	
	private TimeZone xpathTimeZone;
	
	private Locale xpathlocale;
	
	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("datasourceJDBC"); //$NON-NLS-1$
		return iconDescriptor;
	}

	public MXMLDataSource() {
		super(null, -1);
	}

	public MXMLDataSource(ANode parent, int index) {
		super(parent, index);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getImagePath()
	 */
	public ImageDescriptor getImagePath() {
		return getIconDescriptor().getIcon16();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getToolTip()
	 */
	@Override
	public String getToolTip() {
		return getIconDescriptor().getToolTip();
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		NTextPropertyDescriptor jdbcURLD = new NTextPropertyDescriptor(PROPERTY_XPATHSELECT, Messages.common_xpath_select);
		desc.add(jdbcURLD);

		NTextPropertyDescriptor timeZoneD = new NTextPropertyDescriptor(PROPERTY_XPATHTIMEZONE,
				Messages.common_xpath_timezone);
		desc.add(timeZoneD);

		NTextPropertyDescriptor localeD = new NTextPropertyDescriptor(PROPERTY_XPATHLOCALE, Messages.common_xpath_locale);
		desc.add(localeD);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(PROPERTY_XPATHTIMEZONE, new DefaultValue(TimeZone.getDefault(), false));
		defaultsMap.put(PROPERTY_XPATHLOCALE, new DefaultValue(Locale.getDefault(), false));
		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_XPATHSELECT))
			return xpathselect;
		if (id.equals(PROPERTY_XPATHLOCALE))
			return xpathlocale;
		if (id.equals(PROPERTY_XPATHTIMEZONE))
			return xpathTimeZone;
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_XPATHSELECT)) {
			xpathselect = (String) value;
		} else if (id.equals(PROPERTY_XPATHLOCALE)) {
			xpathlocale = (Locale) value;
		} else if (id.equals(PROPERTY_XPATHTIMEZONE)) {
			xpathTimeZone = (TimeZone) value;
		} else
			super.setPropertyValue(id, value);
	}
}
