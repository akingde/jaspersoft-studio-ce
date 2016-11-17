/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignValueDataset;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.help.HelpReferenceBuilder;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.DefaultValue;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MChartValueDataset extends MChartDataset {
	
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	
	private static IPropertyDescriptor[] descriptors;

	public MChartValueDataset(ANode parent, JRDesignValueDataset value,
			JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
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

		JRExpressionPropertyDescriptor valExpD = new JRExpressionPropertyDescriptor(
				JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION,
				Messages.common_value_expression);
		valExpD.setDescription(Messages.MChartValueDataset_value_expression_description);
		desc.add(valExpD);
		valExpD.setHelpRefBuilder(new HelpReferenceBuilder(
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#valueExpression"));
		valExpD.setCategory(Messages.MChartValueDataset_chart_value_dataset_category);

		setHelpPrefix(desc,
				"net.sf.jasperreports.doc/docs/schema.reference.html?cp=0_1#valueDataset");
	}
	
	@Override
	protected Map<String, DefaultValue> createDefaultsMap() {
		Map<String, DefaultValue> defaultsMap = super.createDefaultsMap();
		defaultsMap.put(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION, new DefaultValue(null, true));
		return defaultsMap;
	}

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignValueDataset jrElement = (JRDesignValueDataset) getValue();

		if (id.equals(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION))
			return ExprUtil.getExpression(jrElement.getValueExpression());

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignValueDataset jrElement = (JRDesignValueDataset) getValue();

		if (id.equals(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(
					jrElement.getValueExpression(), value));
		else
			super.setPropertyValue(id, value);
	}

}
