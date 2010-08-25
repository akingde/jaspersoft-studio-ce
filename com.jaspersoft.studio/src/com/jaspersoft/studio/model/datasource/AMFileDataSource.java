package com.jaspersoft.studio.model.datasource;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public abstract class AMFileDataSource extends AMDatasource {

	public AMFileDataSource(ANode parent, int index) {
		super(parent, index);
	}

	public static final String PROPERTY_FILENAME = "PROPERTY_FILENAME";
	protected String filename;

	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		NTextPropertyDescriptor driverClassD = new NTextPropertyDescriptor(PROPERTY_FILENAME, "File Name");
		desc.add(driverClassD);
	}

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_FILENAME)) {
			return filename;
		}
		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_FILENAME)) {
			filename = (String) value;
		} else
			super.setPropertyValue(id, value);
	}
}
