/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.field;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
/*
 * The Class MField.
 * 
 * @author Chicu Veaceslav
 */
public class MField extends APropertyNode implements ICopyable, IDragable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("field"); //$NON-NLS-1$
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

	private static final String PROPERTY_MAP = "PROPERTY_MAP"; //$NON-NLS-1$

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP,
				Messages.common_properties);
		propertiesD.setDescription(Messages.MField_properties_description);
		desc.add(propertiesD);

		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignField.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MField_name_description);
		desc.add(nameD);

		ClassTypePropertyDescriptor classD = new ClassTypePropertyDescriptor(JRDesignField.PROPERTY_VALUE_CLASS_NAME,
				Messages.common_class);
		classD.setDescription(Messages.MField_class_description);
		desc.add(classD);

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignField.PROPERTY_DESCRIPTION,
				Messages.common_description);
		descriptionD.setDescription(Messages.MField_description_description);
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
		else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrField.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrField.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrField.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, false, true);
		}
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
		jrDesignField.setName(ModelUtils.getDefaultName(jrDataset.getFieldsMap(), "Field_")); //$NON-NLS-1$
		return jrDesignField;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MFields)
			return true;
		return false;
	}

}
