/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import com.jaspersoft.studio.data.sql.model.query.expression.AMExpression;

public class ParameterPOperand extends AOperand {
	public ParameterPOperand(AMExpression<?> mexpr, boolean showDialog) {
		this(mexpr);
		this.showDialog = showDialog;
	}

	public ParameterPOperand(AMExpression<?> mexpr) {
		super(mexpr);
		JRDesignDataset ds = null;
		if (mexpr.getRoot() != null)
			ds = mexpr.getRoot().getValue();
		setJrParameter(jrParameter, ds);
	}

	private boolean showDialog = true;

	public boolean isShowDialog() {
		return showDialog;
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

	public void setJrParameter(String prm) {
		if (jrDataset != null)
			this.jrParameter = (JRDesignParameter) jrDataset.getParametersMap().get(prm);
		if (this.jrParameter == null) {
			this.jrParameter = new JRDesignParameter();
			this.jrParameter.setName(prm);
		}
	}

	public void setJrDataset(JRDesignDataset jrDataset) {
		this.jrDataset = jrDataset;
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
