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
package com.jaspersoft.studio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;

public abstract class APropertyNode extends ANode implements IPropertySource {
	/** The descriptors. */

	public APropertyNode() {
		super();
	}

	public APropertyNode(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public abstract Map<String, Object> getDefaultsMap();

	public abstract void setDescriptors(IPropertyDescriptor[] descriptors1, Map<String, Object> defaultsMap1);

	public abstract IPropertyDescriptor[] getDescriptors();

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public abstract void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap);

	protected void postDescriptors(IPropertyDescriptor[] descriptors) {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyDescriptors()
	 */
	@Override
	public IPropertyDescriptor[] getPropertyDescriptors() {
		if (getValue() == null)
			return new IPropertyDescriptor[0];
		IPropertyDescriptor[] descriptors = getDescriptors();
		if (descriptors == null) {
			Map<String, Object> defaultsMap = new HashMap<String, Object>();
			List<IPropertyDescriptor> desc = new ArrayList<IPropertyDescriptor>();

			createPropertyDescriptors(desc, defaultsMap);

			descriptors = desc.toArray(new IPropertyDescriptor[desc.size()]);
			setDescriptors(descriptors, defaultsMap);
		}
		postDescriptors(descriptors);
		return descriptors;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#isPropertySet(java.lang.Object)
	 */
	@Override
	public boolean isPropertySet(Object id) {
		try {
			Object def = getPropertyDefaultValue((String) id);
			Object propertyValue = getPropertyValue(id);
			if ((def != null && !def.equals(propertyValue)) || (def == null && propertyValue != null))
				return true;
		} catch (Exception e) {
		}
		return false;
	}

	/**
	 * @param id
	 * @return default value
	 */
	public Object getPropertyDefaultValue(String id) throws Exception {
		Map<String, Object> defaultsMap = getDefaultsMap();
		if (defaultsMap != null && defaultsMap.containsKey(id))
			return defaultsMap.get(id);
		throw new Exception("Key not found");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
	@Override
	public void resetPropertyValue(Object id) {
		try {
			setPropertyValue(id, getPropertyDefaultValue((String) id));
		} catch (Exception e) {
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getEditableValue()
	 */
	@Override
	public Object getEditableValue() {
		return this;
	}

}
