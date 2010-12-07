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
package com.jaspersoft.studio.barcode.model;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.barbecue.StandardBarbecueComponent;
import net.sf.jasperreports.engine.design.JRDesignComponentElement;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.type.EvaluationTimeEnum;

import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptor.combo.RComboBoxPropertyDescriptor;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;
import com.jaspersoft.studio.utils.EnumHelper;

public abstract class MBarcode extends MGraphicElement {

	public MBarcode() {
		super();
	}

	public MBarcode(ANode parent, int newIndex) {
		super(parent, newIndex);
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

		JRExpressionPropertyDescriptor codeExprD = new JRExpressionPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_CODE_EXPRESSION, Messages.MBarcode_code_expression);
		codeExprD.setDescription(Messages.MBarcode_code_expression_description);
		desc.add(codeExprD);

		ComboBoxPropertyDescriptor evaluationTimeD = new ComboBoxPropertyDescriptor(
				StandardBarbecueComponent.PROPERTY_EVALUATION_TIME, Messages.MBarcode_evaluation_time, EnumHelper.getEnumNames(
						EvaluationTimeEnum.values(), NullEnum.NOTNULL));
		evaluationTimeD.setDescription(Messages.MBarcode_evaluation_time_description);
		desc.add(evaluationTimeD);

		evaluationGroupNameD = new RComboBoxPropertyDescriptor(StandardBarbecueComponent.PROPERTY_EVALUATION_GROUP,
				Messages.MBarcode_evaluation_group, new String[] { "" }); //$NON-NLS-2$
		evaluationGroupNameD.setDescription(Messages.MBarcode_evaluation_group_description);
		desc.add(evaluationGroupNameD);

		evaluationTimeD.setCategory(Messages.MBarcode_properties_category);
		evaluationGroupNameD.setCategory(Messages.MBarcode_properties_category);
		codeExprD.setCategory(Messages.MBarcode_properties_category);

		defaultsMap.put(StandardBarbecueComponent.PROPERTY_EVALUATION_TIME, EvaluationTimeEnum.NOW);
	}

	@Override
	protected void setGroupItems(String[] items) {
		super.setGroupItems(items);
		if (evaluationGroupNameD != null)
			evaluationGroupNameD.setItems(items);
	}

	private RComboBoxPropertyDescriptor evaluationGroupNameD;

	@Override
	public void setValue(Object value) {
		if (getValue() != null) {
			Object obj = getComponent();
			if (obj instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport().removePropertyChangeListener(this);
		} else if (value != null) {
			Object obj = getComponent(value);
			if (value instanceof JRChangeEventsSupport)
				((JRChangeEventsSupport) obj).getEventSupport().addPropertyChangeListener(this);
			super.setValue(value);
			return;
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
