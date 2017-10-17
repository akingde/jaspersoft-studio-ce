/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.datasource;

import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

import net.sf.jasperreports.engine.JRConstants;

public abstract class AMFileDataSource extends AMDatasource {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public AMFileDataSource(ANode parent, int index) {
		super(parent, index);
	}

	public static final String PROPERTY_FILENAME = "PROPERTY_FILENAME"; //$NON-NLS-1$
	protected String filename;

	public static final String PROPERTY_NUMBERFORMAT = "PROPERTY_NUMBERFORMAT"; //$NON-NLS-1$
	protected String numberformat;

	public static final String PROPERTY_DATEFORMAT = "PROPERTY_DATEFORMAT"; //$NON-NLS-1$
	protected String dateformat;

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		super.createPropertyDescriptors(desc);

		NTextPropertyDescriptor driverClassD = new NTextPropertyDescriptor(PROPERTY_FILENAME, Messages.common_file_name);
		desc.add(driverClassD);

		PatternPropertyDescriptor numberFormatD = new PatternPropertyDescriptor(PROPERTY_NUMBERFORMAT,
				Messages.common_number_format);
		desc.add(numberFormatD);

		PatternPropertyDescriptor dateFormatD = new PatternPropertyDescriptor(PROPERTY_DATEFORMAT,
				Messages.common_date_format);
		desc.add(dateFormatD);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_FILENAME))
			return filename;
		if (id.equals(PROPERTY_NUMBERFORMAT))
			return numberformat;
		if (id.equals(PROPERTY_DATEFORMAT))
			return dateformat;

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_FILENAME)) {
			filename = (String) value;
		} else if (id.equals(PROPERTY_NUMBERFORMAT)) {
			numberformat = (String) value;
		} else if (id.equals(PROPERTY_DATEFORMAT)) {
			dateformat = (String) value;
		} else
			super.setPropertyValue(id, value);
	}
}
