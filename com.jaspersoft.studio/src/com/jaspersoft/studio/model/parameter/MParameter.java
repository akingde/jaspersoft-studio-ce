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

import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.NodeIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.checkbox.CheckBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.properties.JPropertiesPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * The Class MParameter.
 * 
 * @author Chicu Veaceslav
 */
public class MParameter extends MParameterSystem implements ICopyable {

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
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc, Map<String, Object> defaultsMap) {
		super.createPropertyDescriptors(desc, defaultsMap);

		NTextPropertyDescriptor descriptionD = new NTextPropertyDescriptor(JRDesignParameter.PROPERTY_DESCRIPTION,
				"Description");
		descriptionD.setDescription("Description of parameter");
		desc.add(descriptionD);

		CheckBoxPropertyDescriptor isForPromptingD = new CheckBoxPropertyDescriptor(
				JRDesignParameter.PROPERTY_FOR_PROMPTING, "Is For Prompting", NullEnum.NOTNULL);
		isForPromptingD
				.setDescription("Optional flag that might be used by the parent application to prompt the user for the parameter value.");
		desc.add(isForPromptingD);

		JRExpressionPropertyDescriptor defValueExprD = new JRExpressionPropertyDescriptor(
				JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION, "Default Value Expression");
		defValueExprD
				.setDescription("Specifies the parameter default value to use when not supplied by the parent application at runtime.");
		desc.add(defValueExprD);

		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP, "Properties");
		propertiesD.setDescription("Dataset properties");
		desc.add(propertiesD);

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME,
				"Nested Type Name");
		classD.setDescription("Type of the nested elements, if the parameter's value is a collection");
		desc.add(classD);

		defaultsMap.put(JRDesignParameter.PROPERTY_FOR_PROMPTING, Boolean.TRUE);
	}

	private static final String PROPERTY_MAP = "PROPERTY_MAP";
	private MExpression mExpression;

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
		if (id.equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION)) {
			if (mExpression == null)
				mExpression = new MExpression(jrParameter.getDefaultValueExpression());
			return mExpression;
		}
		if (id.equals(PROPERTY_MAP))
			return jrParameter.getPropertiesMap();
		if (id.equals(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME))
			return jrParameter.getNestedTypeName();
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
		else if (id.equals(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME))
			jrParameter.setNestedTypeName((String) value);
		else if (id.equals(JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION)) {
			if (value instanceof MExpression) {
				mExpression = (MExpression) value;
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrParameter.setDefaultValueExpression(expression);
			}
		}
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

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MParameters)
			return true;
		return false;
	}

}
