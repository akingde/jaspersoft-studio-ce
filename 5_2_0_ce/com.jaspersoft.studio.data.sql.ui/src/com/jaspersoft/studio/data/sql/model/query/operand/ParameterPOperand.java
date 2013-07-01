/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;

public class ParameterPOperand extends AOperand {
	public ParameterPOperand(AMExpression<?> mexpr) {
		super(mexpr);
		JRDesignDataset ds = (JRDesignDataset) mexpr.getRoot().getValue();
		setJrParameter(jrParameter, ds);
	}

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	protected JRDesignParameter jrParameter;
	private JRDesignDataset jrDataset;

	public JRDesignParameter getJrParameter() {
		return jrParameter;
	}

	public void setJrParameter(JRDesignParameter jrParameter) {
		this.jrParameter = jrParameter;
	}

	public JRDesignDataset getJrDataset() {
		return jrDataset;
	}

	public void setJrParameter(JRDesignParameter jrParameter, JRDesignDataset jrDataset) {
		this.jrParameter = jrParameter;
		this.jrDataset = jrDataset;
	}

	@Override
	public String toSQLString() {
		if (jrParameter != null)
			return "$P{" + jrParameter.getName() + "}";
		return "$P{}";
	}

	@Override
	public String toXString() {
		if (jrParameter != null)
			return jrParameter.getName();
		return "";
	}

}
