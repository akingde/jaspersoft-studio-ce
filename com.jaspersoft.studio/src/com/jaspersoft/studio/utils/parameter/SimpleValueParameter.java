/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.utils.parameter;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.design.JRDesignParameter;

public class SimpleValueParameter extends JRDesignParameter implements JRValueParameter {
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private Object value;

	public SimpleValueParameter(Object value) {
		super();
		this.value = value;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
