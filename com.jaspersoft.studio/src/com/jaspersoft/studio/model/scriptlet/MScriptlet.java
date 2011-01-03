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
package com.jaspersoft.studio.model.scriptlet;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRScriptlet;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignScriptlet;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MScriptlet.
 * 
 * @author Chicu Veaceslav
 */
public class MScriptlet extends APropertyNode implements ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("scriptlet"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m scriptlet.
	 */
	public MScriptlet() {
		super();
	}

	/**
	 * Instantiates a new m scriptlet.
	 * 
	 * @param parent
	 *          the parent
	 * @param jfRield
	 *          the jf rield
	 * @param newIndex
	 *          the new index
	 */
	public MScriptlet(ANode parent, JRScriptlet jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRScriptlet) getValue()).getName();
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

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *          the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		TextPropertyDescriptor nameD = new TextPropertyDescriptor(JRDesignScriptlet.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MScriptlet_name_description);
		desc.add(nameD);

		ClassTypePropertyDescriptor classD = new ClassTypePropertyDescriptor(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME,
				Messages.common_class);
		classD.setDescription(Messages.MScriptlet_class_description);
		desc.add(classD);

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignScriptlet.PROPERTY_DESCRIPTION,
				Messages.common_description);
		descriptionD.setDescription(Messages.MScriptlet_description_description);
		desc.add(descriptionD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignScriptlet jrField = (JRDesignScriptlet) getValue();
		if (id.equals(JRDesignScriptlet.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME))
			return jrField.getValueClassName();
		if (id.equals(JRDesignScriptlet.PROPERTY_DESCRIPTION))
			return jrField.getDescription();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignScriptlet jrField = (JRDesignScriptlet) getValue();
		if (id.equals(JRDesignScriptlet.PROPERTY_NAME))
			jrField.setName((String) value);
		else if (id.equals(JRDesignScriptlet.PROPERTY_VALUE_CLASS_NAME))
			jrField.setValueClassName((String) value);
		else if (id.equals(JRDesignScriptlet.PROPERTY_DESCRIPTION))
			jrField.setDescription((String) value);
	}

	/**
	 * Creates the jr scriptlet.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design scriptlet
	 */
	public static JRDesignScriptlet createJRScriptlet(JRDesignDataset jrDataset) {
		JRDesignScriptlet jrScriptlet = new JRDesignScriptlet();
		jrScriptlet.setName(ModelUtils.getDefaultName(jrDataset.getScriptletsMap(), "Scriptlet_")); //$NON-NLS-1$
		return jrScriptlet;

	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MScriptlets)
			return true;
		return false;
	}
}
