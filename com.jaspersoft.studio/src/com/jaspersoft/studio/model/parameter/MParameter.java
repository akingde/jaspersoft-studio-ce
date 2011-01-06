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
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.model.util.NodeIconDescriptor;
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
			iconDescriptor = new NodeIconDescriptor("parameter"); //$NON-NLS-1$
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
				Messages.common_description);
		descriptionD.setDescription(Messages.MParameter_description_description);
		desc.add(descriptionD);

		CheckBoxPropertyDescriptor isForPromptingD = new CheckBoxPropertyDescriptor(
				JRDesignParameter.PROPERTY_FOR_PROMPTING, Messages.MParameter_is_for_prompting, NullEnum.NOTNULL);
		isForPromptingD.setDescription(Messages.MParameter_is_for_prompting_description);
		desc.add(isForPromptingD);

		JRExpressionPropertyDescriptor defValueExprD = new JRExpressionPropertyDescriptor(
				JRDesignParameter.PROPERTY_DEFAULT_VALUE_EXPRESSION, Messages.MParameter_default_value_expression);
		defValueExprD.setDescription(Messages.MParameter_default_value_expression_description);
		desc.add(defValueExprD);

		JPropertiesPropertyDescriptor propertiesD = new JPropertiesPropertyDescriptor(PROPERTY_MAP,
				Messages.common_properties);
		propertiesD.setDescription(Messages.MParameter_properties_description);
		desc.add(propertiesD);

		NClassTypePropertyDescriptor classD = new NClassTypePropertyDescriptor(JRDesignParameter.PROPERTY_NESTED_TYPE_NAME,
				Messages.MParameter_nested_type_name);
		classD.setDescription(Messages.MParameter_nested_type_name_description);
		desc.add(classD);

		defaultsMap.put(JRDesignParameter.PROPERTY_FOR_PROMPTING, Boolean.TRUE);
	}

	private static final String PROPERTY_MAP = "PROPERTY_MAP"; //$NON-NLS-1$
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
			if (mExpression == null) {
				mExpression = new MExpression(jrParameter.getDefaultValueExpression());
				setChildListener(mExpression);
			}
			return mExpression;
		}
		if (id.equals(PROPERTY_MAP)) {
			return jrParameter.getPropertiesMap();
		}
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
				setChildListener(mExpression);
				JRExpression expression = (JRExpression) mExpression.getValue();
				jrParameter.setDefaultValueExpression(expression);
			}
		} else if (id.equals(PROPERTY_MAP)) {
			JRPropertiesMap v = (JRPropertiesMap) value;
			String[] names = jrParameter.getPropertiesMap().getPropertyNames();
			for (int i = 0; i < names.length; i++) {
				jrParameter.getPropertiesMap().removeProperty(names[i]);
			}
			names = v.getPropertyNames();
			for (int i = 0; i < names.length; i++)
				jrParameter.getPropertiesMap().setProperty(names[i], v.getProperty(names[i]));
			this.getPropertyChangeSupport().firePropertyChange(PROPERTY_MAP, false, true);
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
		jrDesignParameter.setName(ModelUtils.getDefaultName(jrDataset.getParametersMap(), "Parameter")); //$NON-NLS-1$
		return jrDesignParameter;
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MParameters)
			return true;
		return false;
	}

}
