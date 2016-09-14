/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.dataset;

import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionDTO;

import net.sf.jasperreports.engine.type.PropertyEvaluationTimeEnum;

public class DatasetPropertyExpressionDTO extends PropertyExpressionDTO {
	private PropertyEvaluationTimeEnum evalTime;

	public DatasetPropertyExpressionDTO(boolean isExpression, String name, String value,
			PropertyEvaluationTimeEnum evalTime) {
		super(isExpression, name, value);
		this.evalTime = evalTime;
	}

	public PropertyEvaluationTimeEnum getEvalTime() {
		return evalTime;
	}

	public void setEvalTime(PropertyEvaluationTimeEnum evalTime) {
		this.evalTime = evalTime;
	}

	@Override
	public DatasetPropertyExpressionDTO clone() {
		DatasetPropertyExpressionDTO result = new DatasetPropertyExpressionDTO(this.isExpression(),
				new String(this.getName()), new String(this.getValue()), evalTime);
		result.setPnode(getPnode());
		return result;
	}
}
