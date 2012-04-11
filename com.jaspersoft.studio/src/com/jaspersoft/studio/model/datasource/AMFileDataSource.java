/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.model.datasource;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

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
