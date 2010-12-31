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
package com.jaspersoft.studio.model.datasource.jdbc;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MJDBCDataSource extends AMDatasource {
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

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

	public MJDBCDataSource() {
		super(null, -1);
	}

	public MJDBCDataSource(ANode parent, int index) {
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

	private static IPropertyDescriptor[] descriptors;
	private static Map<String, Object> defaultsMap;

	@Override
	public Map<String, Object> getDefaultsMap() {
		return defaultsMap;
	}

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		NTextPropertyDescriptor driverClassD = new NTextPropertyDescriptor(PROPERTY_DRIVERCLASS, Messages.MJDBCDataSource_driver_class);
		desc.add(driverClassD);

		NTextPropertyDescriptor jdbcURLD = new NTextPropertyDescriptor(PROPERTY_JDBC_URL, Messages.MJDBCDataSource_jdbc_url);
		desc.add(jdbcURLD);

		NTextPropertyDescriptor usernameD = new NTextPropertyDescriptor(PROPERTY_USERNAME, Messages.MJDBCDataSource_username);
		desc.add(usernameD);

		NTextPropertyDescriptor passwordD = new NTextPropertyDescriptor(PROPERTY_PASSWORD, Messages.MJDBCDataSource_password);
		desc.add(passwordD);

		NTextPropertyDescriptor jarD = new NTextPropertyDescriptor(PROPERTY_JAR, Messages.MJDBCDataSource_classpath);
		desc.add(jarD);

		NTextPropertyDescriptor connectionD = new NTextPropertyDescriptor(PROPERTY_CONNECTION, Messages.MJDBCDataSource_connection);
		desc.add(connectionD);

	}

	public static final String PROPERTY_DRIVERCLASS = "PROPERTY_DRIVERCLASS"; //$NON-NLS-1$
	private String driverclass;

	public static final String PROPERTY_JDBC_URL = "PROPERTY_JDBC_URL"; //$NON-NLS-1$
	private String jdbcURL;

	public static final String PROPERTY_USERNAME = "PROPERTY_USERNAME"; //$NON-NLS-1$
	private String username;

	public static final String PROPERTY_PASSWORD = "PROPERTY_PASSWORD"; //$NON-NLS-1$
	private String password;

	public static final String PROPERTY_JAR = "PROPERTY_JAR"; //$NON-NLS-1$
	private String jar;

	public static final String PROPERTY_CONNECTION = "PROPERTY_CONNECTION"; //$NON-NLS-1$
	private Connection connection;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_DRIVERCLASS)) {
			return driverclass;
		}
		if (id.equals(PROPERTY_JAR)) {
			return jar;
		}
		if (id.equals(PROPERTY_JDBC_URL)) {
			return jdbcURL;
		}
		if (id.equals(PROPERTY_USERNAME)) {
			return username;
		}
		if (id.equals(PROPERTY_PASSWORD)) {
			return password;
		}
		if (id.equals(PROPERTY_CONNECTION)) {
			return connection;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_DRIVERCLASS)) {
			driverclass = (String) value;
		} else if (id.equals(PROPERTY_JDBC_URL)) {
			jdbcURL = (String) value;
		} else if (id.equals(PROPERTY_USERNAME)) {
			username = (String) value;
		} else if (id.equals(PROPERTY_JAR)) {
			jar = (String) value;
		} else if (id.equals(PROPERTY_PASSWORD)) {
			password = (String) value;
		} else if (id.equals(PROPERTY_CONNECTION)) {
			connection = (Connection) value;
		} else
			super.setPropertyValue(id, value);
	}
}
