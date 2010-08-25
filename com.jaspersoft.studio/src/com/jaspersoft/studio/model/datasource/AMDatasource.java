package com.jaspersoft.studio.model.datasource;

import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.repository.RepositoryManager;

public abstract class AMDatasource extends APropertyNode {
	public AMDatasource(ANode parent, int index) {
		super(parent, index);
		setValue(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	@Override
	public String getDisplayText() {
		return (String) getPropertyValue(AMDatasource.PROPERTY_NAME);
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

		NTextPropertyDescriptor textD = new NTextPropertyDescriptor(PROPERTY_NAME, "Datasource Name");
		desc.add(textD);

		defaultsMap.put(PROPERTY_NAME, RepositoryManager.checkDataSourceName("DataSource"));
	}

	public static final String PROPERTY_NAME = "PROPERTY_NAME";
	private String name;

	@Override
	public Object getPropertyValue(Object id) {
		if (id.equals(PROPERTY_NAME)) {
			return name;
		}
		return null;
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		if (id.equals(PROPERTY_NAME)) {
			name = (String) value;
		}

	}

	public AMDatasource getCopy() {
		AMDatasource r = null;
		try {
			r = this.getClass().newInstance();
			IPropertyDescriptor[] d = r.getPropertyDescriptors();
			for (int i = 0; i < d.length; i++) {
				r.setPropertyValue(d[i].getId(), this.getPropertyValue(d[i].getId()));
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return r;
	}
}
