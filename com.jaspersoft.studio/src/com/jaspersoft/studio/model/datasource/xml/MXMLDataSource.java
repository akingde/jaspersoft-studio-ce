package com.jaspersoft.studio.model.datasource.xml;

import java.util.List;
import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.model.datasource.AMFileDataSource;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class MXMLDataSource extends AMFileDataSource {
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

		NTextPropertyDescriptor jdbcURLD = new NTextPropertyDescriptor(PROPERTY_XPATHSELECT, "XPath Select");
		desc.add(jdbcURLD);
	}

	public static final String PROPERTY_XPATHSELECT = "PROPERTY_XPATHSELECT";
	private String xpathselect;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_XPATHSELECT)) {
			return xpathselect;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_XPATHSELECT)) {
			xpathselect = (String) value;
		} else
			super.setPropertyValue(id, value);
	}
}
