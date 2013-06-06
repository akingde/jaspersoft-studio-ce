package com.jaspersoft.studio.data.sql.model.query.operand;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignParameter;

import com.jaspersoft.studio.data.sql.model.query.MExpression;

public class ParameterOperand extends AOperand {
	public ParameterOperand(MExpression mexpr) {
		super(mexpr);
		JRDesignDataset ds = (JRDesignDataset) mexpr.getRoot().getValue();
		setJrParameter(jrParameter, ds);
	}

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	private JRDesignParameter jrParameter;
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

}
