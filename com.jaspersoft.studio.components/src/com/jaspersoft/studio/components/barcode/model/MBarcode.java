/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.barcode.model;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.barcode.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

public abstract class MBarcode extends MGraphicElement {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private IPropertyDescriptor[] descriptors;

	public MBarcode() {
		super();
	}

	public MBarcode(ANode parent, int newIndex) {
		super(parent, newIndex);
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
		super.createPropertyDescriptors(desc);

		JRExpressionPropertyDescriptor codeExprD = new JRExpressionPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION,
				Messages.MBarcode_code_expression);
		codeExprD.setDescription(Messages.MBarcode_code_expression_description);
		desc.add(codeExprD);

		evaluationTimeD = new NamedEnumPropertyDescriptor<EvaluationTimeEnum>(
				StandardBarbecueComponent.PROPERTY_EVALUATION_TIME,
				Messages.MBarcode_evaluation_time, EvaluationTimeEnum.AUTO,
				NullEnum.NOTNULL);
		evaluationTimeD
				.setDescription(Messages.MBarcode_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MBarcode_evaluation_group, new String[] { "" }); //$NON-NLS-1$
		evaluationGroupNameD
				.setDescription(Messages.MBarcode_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		evaluationTimeD.setCategory(Messages.common_properties_category);
		evaluationGroupNameD.setCategory(Messages.common_properties_category);
		codeExprD.setCategory(Messages.common_properties_category);
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME, new DefaultValue(EvaluationTimeEnum.NOW, false));
		return defaultsMap;
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	@Override
	public HashSet<String> generateGraphicalProperties() {
		HashSet<String> properties = super.generateGraphicalProperties();
		properties.add(StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION);
		return properties;
	}

	private RComboBoxPropertyDescriptor evaluationGroupNameD;
	protected static NamedEnumPropertyDescriptor<EvaluationTimeEnum> evaluationTimeD;

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.removePropertyChangeListener(this);
		}
		if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport()
						.addPropertyChangeListener(this);
		}
		super.setValue(value);
	}

	private Object getComponent() {
		return getComponent(getValue());
	}

	private Object getComponent(Object value) {
		if (value != null) {
			JRDesignComponentElement jrElement = (JRDesignComponentElement) value;
			return jrElement.getComponent();
		}
		return null;
	}
}
