/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.crosstab.model.measure;

import java.util.List;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.crosstab.CrosstabNodeIconDescriptor;
import com.jaspersoft.studio.components.crosstab.messages.Messages;
import com.jaspersoft.studio.components.crosstab.model.MDatasetGroupNode;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.ICopyable;
import com.jaspersoft.studio.model.IDragable;
import com.jaspersoft.studio.model.util.IIconDescriptor;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.classname.NClassTypePropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.crosstabs.JRCrosstabMeasure;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.crosstabs.type.CrosstabPercentageEnum;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.type.CalculationEnum;

public class MMeasure extends MDatasetGroupNode implements ICopyable, IDragable {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	/** The icon descriptor. */
	private static IIconDescriptor iconDescriptor;
	
	private static IPropertyDescriptor[] descriptors;
	
	private static NamedEnumPropertyDescriptor<CalculationEnum> calculationD;
	
	private static NamedEnumPropertyDescriptor<CrosstabPercentageEnum> percentOfTypeD;

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

	@Override
	public IPropertyDescriptor[] getDescriptors() {
		return descriptors;
	}

	@Override
	public void setDescriptors(IPropertyDescriptor[] descriptors1) {
		descriptors = descriptors1;
	}

	/**
	 * Creates the property descriptors.
	 * 
	 * @param desc
	 *            the desc
	 */
	@Override
	public void createPropertyDescriptors(List<IPropertyDescriptor> desc) {
		NTextPropertyDescriptor nameD = new NTextPropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_NAME, Messages.common_name);
		nameD.setDescription(Messages.MMeasure_name_description);
		desc.add(nameD);

		calculationD = new NamedEnumPropertyDescriptor<CalculationEnum>(
				JRDesignCrosstabMeasure.PROPERTY_CALCULATION,
				Messages.common_calculation, CalculationEnum.AVERAGE,
				NullEnum.NOTNULL);
		calculationD.setDescription(Messages.MMeasure_calculation_description);
		desc.add(calculationD);

		percentOfTypeD = new NamedEnumPropertyDescriptor<CrosstabPercentageEnum>(
				JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE,
				Messages.MMeasure_percentage_of_type,
				CrosstabPercentageEnum.GRAND_TOTAL, NullEnum.NOTNULL);
		percentOfTypeD
				.setDescription(Messages.MMeasure_percentage_of_type_description);
		desc.add(percentOfTypeD);

		JRExpressionPropertyDescriptor valueExprD = new JRExpressionPropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION,
				Messages.MMeasure_value_expression);
		valueExprD
				.setDescription(Messages.MMeasure_value_expression_description);
		desc.add(valueExprD);

		NClassTypePropertyDescriptor valueClassD = new NClassTypePropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS,
				Messages.MMeasure_value_class);
		valueClassD.setDescription(Messages.MMeasure_value_class_description);
		desc.add(valueClassD);

		NClassTypePropertyDescriptor incFactClassD = new NClassTypePropertyDescriptor(
				JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME,
				Messages.MMeasure_incrementer_factory_class_name);
		incFactClassD
				.setDescription(Messages.MMeasure_incrementer_factory_class_name_description);
		desc.add(incFactClassD);

		NClassTypePropertyDescriptor percCalcClassD = new NClassTypePropertyDescriptor(
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
			return calculationD.getIntValue(jrField.getCalculationValue());
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE))
			return percentOfTypeD.getIntValue(jrField.getPercentageType());
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrField.getValueExpression());

		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_VALUE_CLASS))
			return jrField.getValueClassName();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_INCREMENTER_FACTORY_CLASS_NAME))
			return jrField.getIncrementerFactoryClassName();
		if (id.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_CALCULATION_CLASS_NAME))
			return jrField.getPercentageCalculatorClassName();
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
			jrField.setCalculation(calculationD.getEnumValue(value));
		else if (id.equals(JRDesignCrosstabMeasure.PROPERTY_PERCENTAGE_OF_TYPE))
			jrField.setPercentageType(percentOfTypeD.getEnumValue(value));
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

	public ICopyable.RESULT isCopyable2(Object parent) {
		if (parent instanceof MMeasures)
			return ICopyable.RESULT.COPYABLE;
		return ICopyable.RESULT.CHECK_PARENT;
	}
	
	@Override
	public boolean isCuttable(ISelection currentSelection) {
		return true;
	}
}
