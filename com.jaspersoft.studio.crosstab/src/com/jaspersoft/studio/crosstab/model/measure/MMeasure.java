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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model.measure;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.engine.type.CalculationEnum;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.crosstab.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.ClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public class MMeasure extends APropertyNode implements ICopyable {

	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;

	/**
	 * Gets the icon descriptor.
	 * 
	 * @return the icon descriptor
	 */
	public static IIconDescriptor getIconDescriptor() {
		if (iconDescriptor == null)
			iconDescriptor = new CrosstabNodeIconDescriptor("measure"); //$NON-NLS-1$
		return iconDescriptor;
	}

	/**
	 * Instantiates a new m field.
	 */
	public MMeasure() {
		super();
	}

	/**
	 * Instantiates a new m field.
	 * 
	 * @param parent
	 *            the parent
	 * @param jfRield
	 *            the jf rield
	 * @param newIndex
	 *            the new index
	 */
	public MMeasure(ANode parent, JRCrosstabMeasure jfRield, int newIndex) {
		super(parent, newIndex);
		setValue(jfRield);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.model.INode#getDisplayText()
	 */
	public String getDisplayText() {
		return ((JRCrosstabMeasure) getValue()).getName();
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
	public void setDescriptors(IPropertyDescriptor[] descriptors1,
			Map<String, Object> defaultsMap1) {
		descriptors = descriptors1;
		defaultsMap = defaultsMap1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc,
			Map<String, Object> defaultsMap) {
		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MMeasure_name_description);
		desc.add(nameD);

		ComboBoxPropertyDescriptor calculationD = new ComboBoxPropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_CALCULATION,
				Messages.common_calculation, EnumHelper.getEnumNames(
						CalculationEnum.values(), NullEnum.NOTNULL));
		calculationD.setDescription(Messages.MMeasure_calculation_description);
		desc.add(calculationD);

		ComboBoxPropertyDescriptor percentOfTypeD = new ComboBoxPropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE,
				Messages.MMeasure_percentage_of_type, EnumHelper.getEnumNames(
						CrosstabPercentageEnum.values(), NullEnum.NOTNULL));
		percentOfTypeD
				.setDescription(Messages.MMeasure_percentage_of_type_description);
		desc.add(percentOfTypeD);

		JRExpressionPropertyDescriptor valueExprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION,
				Messages.MMeasure_value_expression);
		valueExprD
				.setDescription(Messages.MMeasure_value_expression_description);
		desc.add(valueExprD);

		ClassTypePropertyDescriptor valueClassD = new ClassTypePropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS,
				Messages.MMeasure_value_class);
		valueClassD.setDescription(Messages.MMeasure_value_class_description);
		desc.add(valueClassD);

		ClassTypePropertyDescriptor incFactClassD = new ClassTypePropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME,
				Messages.MMeasure_incrementer_factory_class_name);
		incFactClassD
				.setDescription(Messages.MMeasure_incrementer_factory_class_name_description);
		desc.add(incFactClassD);

		ClassTypePropertyDescriptor percCalcClassD = new ClassTypePropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME,
				Messages.MMeasure_percentage_calculation_class_name);
		percCalcClassD
				.setDescription(Messages.MMeasure_percentage_calculation_class_name_description);
		desc.add(percCalcClassD);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#getPropertyValue(java
	 * .lang.Object)
	 */
	public Object getPropertyValue(Object id) {
		JRDesignCrosstabMeasure jrField = (JRDesignCrosstabMeasure) getValue();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_NAME))
			return jrField.getName();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_CALCULATION))
			return EnumHelper.getValue(jrField.getCalculationValue(), 0, false);
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE))
			return EnumHelper.getValue(jrField.getPercentageType(), 0, false);
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrField.getValueExpression());

		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS))
			return jrField.getValueClass();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME))
			return jrField.getIncrementerFactoryClass();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME))
			return jrField.getPercentageCalculatorClass();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.views.properties.IPropertySource#setPropertyValue(java
	 * .lang.Object, java.lang.Object)
	 */
	public void setPropertyValue(Object id, Object value) {
		JRDesignCrosstabMeasure jrField = (JRDesignCrosstabMeasure) getValue();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_NAME))
			jrField.setName((String) value);
		else if (id.equals(JRDesignCrosstabMeasure.PROPERTY_CALCULATION))
			jrField.setCalculation((CalculationEnum) EnumHelper.getSetValue(
					CalculationEnum.values(), value, 0, false));
		else if (id.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE))
			jrField.setPercentageType((CrosstabPercentageEnum) EnumHelper
					.getSetValue(CrosstabPercentageEnum.values(), value, 0,
							false));
		else if (id.equals(JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION))
			jrField.setValueExpression(ExprUtil.setValues(
					jrField.getValueExpression(), value));
		else if (id.equals(JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS))
			jrField.setValueClassName((String) value);
		else if (id
				.equals(JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME))
			jrField.setIncrementerFactoryClassName((String) value);
		else if (id
				.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME))
			jrField.setPercentageCalculatorClassName((String) value);
	}

	public boolean isCopyable2(Object parent) {
		if (parent instanceof MMeasures)
			return true;
		return false;
	}

}
