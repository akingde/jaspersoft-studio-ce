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
package com.jaspersoft.studio.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.IPropertySource2;

import com.jaspersoft.studio.property.ElementLabelProvider;

public abstract class APropertyNode extends ANode implements IPropertySource, IPropertySource2 {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	/** The descriptors. */

	public APropertyNode() {
		super();
	}

	public APropertyNode(ANode parent, int newIndex) {
		super(parent, newIndex);
	}

	public boolean isPropertyResettable(Object id) {
		return true;
	}

	private boolean editable = true;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
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
		throw new Exception("Key not found"); //$NON-NLS-1$
	}

	public void initProperties() {
		IPropertyDescriptor[] pd = getPropertyDescriptors();
		for (int i = 0; i < pd.length; i++) {
			try {
				Object o = getPropertyDefaultValue((String) pd[i].getId());
				setPropertyValue(pd[i].getId(), o);
			} catch (Exception e) {
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#resetPropertyValue(java.lang.Object)
	 */
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
	public Object getEditableValue() {
		return this;
	}

	/**
	 * Returns a custom title that should be shown in the property sheets page when the node is selected.<br>
	 * Actually this method returns <code>null</code>, so the standard behavior provided by the contributed label provider
	 * {@link ElementLabelProvider} is used.
	 * <p>
	 * 
	 * Nodes (sub-classes) that want to provide a different behavior should override this method and provide a meaningful
	 * human-readable text.
	 * 
	 * @return a custom title
	 * 
	 * @see ElementLabelProvider#getText(Object)
	 */
	public String getCustomPropertyTitle() {
		return null;
	}

}
