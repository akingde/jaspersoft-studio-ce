/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.dataset;

import java.util.List;
import java.util.Map;

import net.sf.jasperreports.charts.design.JRDesignValueDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MExpression;
import com.jaspersoft.studio.property.descriptor.expression.ExprUtil;
import com.jaspersoft.studio.property.descriptor.expression.JRExpressionPropertyDescriptor;

public class MChartValueDataset extends MChartDataset {

	public MChartValueDataset(ANode parent, JRDesignValueDataset value, JasperDesign jasperDesign) {
		super(parent, value, jasperDesign);
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

		JRExpressionPropertyDescriptor valExpD = new JRExpressionPropertyDescriptor(
				JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION, Messages.common_value_expression);
		valExpD.setDescription(Messages.MChartValueDataset_value_expression_description);
		desc.add(valExpD);

		valExpD.setCategory(Messages.MChartValueDataset_chart_value_dataset_category);

		defaultsMap.put(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION, null);

	}

	private MExpression oExpression;

	@Override
	public Object getPropertyValue(Object id) {
		JRDesignValueDataset jrElement = (JRDesignValueDataset) getValue();

		if (id.equals(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION)) {
			oExpression = ExprUtil.getExpression(this, oExpression, jrElement.getValueExpression());
			return oExpression;
		}

		return super.getPropertyValue(id);
	}

	@Override
	public void setPropertyValue(Object id, Object value) {
		JRDesignValueDataset jrElement = (JRDesignValueDataset) getValue();

		if (id.equals(JRDesignValueDataset.PROPERTY_VALUE_EXPRESSION))
			jrElement.setValueExpression(ExprUtil.setValues(jrElement.getValueExpression(), value));
		else
			super.setPropertyValue(id, value);
	}

}
