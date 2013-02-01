/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.model.variable;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignVariable;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Color;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.JSSTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/*
 * The Class MVariableSystem.
 * 
 * @author Chicu Veaceslav
 */
public class MVariableSystem extends APropertyNode implements IDragable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("variable"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m variable system.
	 */
	public MVariableSystem() {
		super();
	}

	/**
	 * Instantiates a new m variable system.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrVariable
	 *          the jr variable
	 * @param newIndex
	 *          the new index
	 */
	public MVariableSystem(ANode parent, JRDesignVariable jrVariable, int newIndex) {
		super(parent, newIndex);
		setValue(jrVariable);
	}

	@Override
	public Color getForeground() {
		return ColorConstants.lightGray;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRDesignVariable) getValue()).getName();
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
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		createPropertyDescriptors(desc, defaultsMap, true);
	}

	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap,
			boolean readOnly) {
		JSSTextPropertyDescriptor nameD = new JSSTextPropertyDescriptor(JRDesignVariable.PROPERTY_NAME,
				Messages.common_name);
		nameD.setReadOnly(readOnly);
		nameD.setDescription(Messages.MVariableSystem_name_description);
		desc.add(nameD);

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME,
				Messages.common_value_class_name);
		classD.setReadOnly(readOnly);
		classD.setDescription(Messages.MVariableSystem_value_class_name_description);
		desc.add(classD);
		classD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#variable_class"));

		defaultsMap.put(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME, "java.lang.String"); //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java.lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignVariable jrVariable = (JRDesignVariable) getValue();
		if (id.equals(JRDesignVariable.PROPERTY_NAME))
			return jrVariable.getName();
		if (id.equals(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME))
			return jrVariable.getValueClassName();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignVariable jrVariable = (JRDesignVariable) getValue();
		if (id.equals(JRDesignVariable.PROPERTY_NAME))
			jrVariable.setName((String) value);
		else if (id.equals(JRDesignVariable.PROPERTY_VALUE_CLASS_NAME))
			jrVariable.setValueClassName((String) value);
	}

	/**
	 * Creates the jr variable.
	 * 
	 * @param jrDesign
	 *          the jr design
	 * @return the jR design variable
	 */
	public static JRDesignVariable createJRVariable(JasperDesign jrDesign) {
		JRDesignVariable jrDesignVariable = new JRDesignVariable();
		jrDesignVariable.setSystemDefined(true);
		jrDesignVariable.setName(ModelUtils.getDefaultName(jrDesign.getVariablesMap(), "Variable_")); //$NON-NLS-1$
		return jrDesignVariable;
	}
}
