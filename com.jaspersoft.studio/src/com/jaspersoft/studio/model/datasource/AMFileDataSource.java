/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.datasource;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.pattern.PatternPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public abstract class AMFileDataSource extends AMDatasource {

	public AMFileDataSource(ANode parent, int index) {
		super(parent, index);
	}

	public static final String PROPERTY_FILENAME = "PROPERTY_FILENAME";
	protected String filename;

	public static final String PROPERTY_NUMBERFORMAT = "PROPERTY_NUMBERFORMAT";
	protected String numberformat;

	public static final String PROPERTY_DATEFORMAT = "PROPERTY_DATEFORMAT";
	protected String dateformat;

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		NTextPropertyDescriptor driverClassD = new NTextPropertyDescriptor(PROPERTY_FILENAME, "File Name");
		desc.add(driverClassD);

		PatternPropertyDescriptor numberFormatD = new PatternPropertyDescriptor(PROPERTY_NUMBERFORMAT, "Number Format");
		desc.add(numberFormatD);

		PatternPropertyDescriptor dateFormatD = new PatternPropertyDescriptor(PROPERTY_DATEFORMAT, "Date Format");
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
