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
package com.jaspersoft.studio.model.parameter;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MParameter.
 * 
 * @author Chicu Veaceslav
 */
public class MParameter extends MParameterSystem {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new NodeIconDescriptor("parameter");
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m parameter.
	 */
	public MParameter() {
		super();
	}

	/**
	 * Instantiates a new m parameter.
	 * 
	 * @param parent
	 *          the parent
	 * @param jrParameter
	 *          the jr parameter
	 * @param newIndex
	 *          the new index
	 */
	public MParameter(ANode parent, JRDesignParameter jrParameter, int newIndex) {
		super(parent, jrParameter, newIndex);
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
	protected void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		TextPropertyDescriptor descriptionD = new TextPropertyDescriptor(JRDesignParameter.PROPERTY_DESCRIPTION,
				"Description");
		descriptionD.setDescription("Description of parameter");
		desc.add(descriptionD);

		CheckBoxPropertyDescriptor isForPromptingD = new CheckBoxPropertyDescriptor(
				JRDesignParameter.PROPERTY_FOR_PROMPTING, "Is For Prompting", NullEnum.NOTNULL);
		isForPromptingD
				.setDescription("Optional flag that might be used by the parent application to prompt the user for the parameter value.");
		desc.add(isForPromptingD);

		defaultsMap.put(JRDesignParameter.PROPERTY_FOR_PROMPTING, Boolean.TRUE);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.parameter.MParameterSystem#getPropertyValue(java.lang.Object)
	 */
	@Override
	public Object getPropertyValue(Object id) {
		JRDesignParameter jrParameter = (JRDesignParameter) getValue();
		if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION))
			return jrParameter.getDescription();
		if (id.equals(JRDesignParameter.PROPERTY_FOR_PROMPTING))
			return new Boolean(jrParameter.isForPrompting());
		return super.getPropertyValue(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.parameter.MParameterSystem#setPropertyValue(java.lang.Object, java.lang.Object)
	 */
	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignParameter jrParameter = (JRDesignParameter) getValue();
		if (id.equals(JRDesignParameter.PROPERTY_DESCRIPTION))
			jrParameter.setDescription((String) value);
		else if (id.equals(JRDesignParameter.PROPERTY_FOR_PROMPTING))
			jrParameter.setForPrompting(((Boolean) value).booleanValue());
		super.setPropertyValue(id, value);
	}

	/**
	 * Creates the jr parameter.
	 * 
	 * @param jrDataset
	 *          the jr dataset
	 * @return the jR design parameter
	 */
	public static JRDesignParameter createJRParameter(JRDesignDataset jrDataset) {
		JRDesignParameter jrDesignParameter = new JRDesignParameter();
		jrDesignParameter.setSystemDefined(false);
		jrDesignParameter.setName(ModelUtils.getDefaultName(jrDataset.getParametersMap(), "Parameter"));
		return jrDesignParameter;
	}

}
