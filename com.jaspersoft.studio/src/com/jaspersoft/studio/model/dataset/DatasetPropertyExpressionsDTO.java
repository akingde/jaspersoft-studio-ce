/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset;

import java.util.List;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRPropertyExpression;
import net.sf.jasperreports.engine.design.DesignDatasetPropertyExpression;
import net.sf.jasperreports.engine.type.PropertyEvaluationTimeEnum;

public class DatasetPropertyExpressionsDTO extends PropertyExpressionsDTO {

	public DatasetPropertyExpressionsDTO(ANode pnode) {
		super(pnode);
	}

	public DatasetPropertyExpressionsDTO(JRPropertyExpression[] propExpressions, JRPropertiesMap propMap, ANode pnode) {
		this(pnode);
		if (propExpressions != null) {
			for (JRPropertyExpression prop : propExpressions) {
				DatasetPropertyExpressionDTO newProp = new DatasetPropertyExpressionDTO(true, prop.getName(),
						prop.getValueExpression().getText(), ((DesignDatasetPropertyExpression) prop).getEvaluationTime());
				newProp.setPnode(pnode);
				properties.add(newProp);
			}
		}
		if (propMap != null) {
			for (String prop : propMap.getPropertyNames()) {
				DatasetPropertyExpressionDTO newProp = new DatasetPropertyExpressionDTO(false, prop, propMap.getProperty(prop),
						null);
				newProp.setPnode(pnode);
				properties.add(newProp);
			}
		}
	}

	public DatasetPropertyExpressionsDTO(List<PropertyExpressionDTO> properties, ANode pnode) {
		super(properties, pnode);
	}

	@Override
	public boolean addProperty(String name, String value, boolean isExpression) {
		if (!hasProperty(name, isExpression)) {
			DatasetPropertyExpressionDTO newProp = new DatasetPropertyExpressionDTO(isExpression, name, value,
					isExpression ? PropertyEvaluationTimeEnum.REPORT : null);
			newProp.setPnode(pnode);
			properties.add(newProp);
			return true;
		}
		return false;
	}

	/**
	 * Add a property to the list into a specific position, only if a property with the same name and of the same type is
	 * not already present
	 * 
	 * @param name
	 *          the name of the property
	 * @param value
	 *          the value of the property
	 * @param isExpression
	 *          true if the property is an expression property, false if it is a standard property
	 * @param position
	 *          the position where the property should be inserted
	 * @return true if a property with the same name\type was not found and the new one was inserted, false otherwise
	 */
	public boolean addProperty(String name, String value, boolean isExpression, int position) {
		if (!hasProperty(name, isExpression)) {
			DatasetPropertyExpressionDTO newProp = new DatasetPropertyExpressionDTO(isExpression, name, value,
					isExpression ? PropertyEvaluationTimeEnum.REPORT : null);
			newProp.setPnode(pnode);
			properties.add(position, newProp);
			return true;
		}
		return false;
	}

	@Override
	public DatasetPropertyExpressionsDTO clone() {
		DatasetPropertyExpressionsDTO copy = new DatasetPropertyExpressionsDTO(getPnode());
		for (PropertyExpressionDTO prop : getProperties()) {
			boolean exp = prop.isExpression();
			copy.addProperty(prop.getName(), prop.getValue(), exp);
			if (exp)
				((DatasetPropertyExpressionDTO) copy.getProperty(prop.getName(), exp))
						.setEvalTime(((DatasetPropertyExpressionDTO) prop).getEvalTime());
		}
		return copy;
	}
}
