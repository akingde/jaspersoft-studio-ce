package com.jaspersoft.studio.model.datasource.jdbc;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.model.datasource.AMDatasource;
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
			iconDescriptor = new NodeIconDescriptor("datasourceJDBC");
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
	@Override
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

		NTextPropertyDescriptor driverClassD = new NTextPropertyDescriptor(PROPERTY_DRIVERCLASS, "Driver Class");
		desc.add(driverClassD);

		NTextPropertyDescriptor jdbcURLD = new NTextPropertyDescriptor(PROPERTY_JDBC_URL, "JDBC URL");
		desc.add(jdbcURLD);

		NTextPropertyDescriptor usernameD = new NTextPropertyDescriptor(PROPERTY_USERNAME, "Username");
		desc.add(usernameD);

		NTextPropertyDescriptor passwordD = new NTextPropertyDescriptor(PROPERTY_PASSWORD, "Password");
		desc.add(passwordD);

	}

	public static final String PROPERTY_DRIVERCLASS = "PROPERTY_DRIVERCLASS";
	private String driverclass;

	public static final String PROPERTY_JDBC_URL = "PROPERTY_JDBC_URL";
	private String jdbcURL;

	public static final String PROPERTY_USERNAME = "PROPERTY_USERNAME";
	private String username;

	public static final String PROPERTY_PASSWORD = "PROPERTY_PASSWORD";
	private String password;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_DRIVERCLASS)) {
			return driverclass;
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
		} else if (id.equals(PROPERTY_PASSWORD)) {
			password = (String) value;
		} else
			super.setPropertyValue(id, value);
	}
}
