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
package com.jaspersoft.studio.model.field;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MField.
 * 
 * @author Chicu Veaceslav
 */
public class MField extends APropertyNode implements ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("field");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MField() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MField(ANode parent, JRDesignField jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRDesignField) getValue()).getName();
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

	private static final String PROPERTY_MAP = "PROPERTY_MAP";

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP, "Properties");
		propertiesD.setDescription("Dataset properties");
		desc.add(propertiesD);

		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignField.PROPERTY_NAME, "Name");
		nameD.setDescription("Name of the field.");
		desc.add(nameD);

		ClassTypePropertyDescriptor classD = new ClassTypePropertyDescriptor(JRDesignField.PROPERTY_VALUE_CLASS_NAME,
				"Class");
		classD.setDescription("Class of the field values.");
		desc.add(classD);

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignField.PROPERTY_DESCRIPTION,
				"Description");
		descriptionD
				.setDescription("Can be used to specify a short text description for the field and is useful especially when creating special designed data sources and an extra information is needed in order to extract the value of the field.");
		desc.add(descriptionD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignField jrField = (JRDesignField) getValue();
		if (id.equals(JRDesignField.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignField.PROPERTY_VALUE_CLASS_NAME))
			return jrField.getValueClassName();
		if (id.equals(JRDesignField.PROPERTY_DESCRIPTION))
			return jrField.getDescription();
		if (id.equals(PROPERTY_MAP))
			return jrField.getPropertiesMap();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignField jrField = (JRDesignField) getValue();
		if (id.equals(JRDesignParameter.PROPERTY_NAME))
			jrField.setName((String) value);
		else if (id.equals(JRDesignParameter.PROPERTY_VALUE_CLASS_NAME))
			jrField.setValueClassName((String) value);
		else if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION))
			jrField.setDescription((String) value);
	}

	/**
	 * Creates the jr field.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design field
	 */
	public static JRDesignField createJRField(JRDesignDataset jrDataset) {
		JRDesignField jrDesignField = new JRDesignField();
		jrDesignField.setName(ModelUtils.getDefaultName(jrDataset.getFieldsMap(), "Field_"));
		return jrDesignField;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MFields)
			return true;
		return false;
	}

}
