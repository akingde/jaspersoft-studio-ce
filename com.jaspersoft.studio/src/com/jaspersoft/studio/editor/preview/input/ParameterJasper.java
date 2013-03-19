/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import net.sf.jasperreports.engine.design.JRDesignParameter;

public class ParameterJasper implements IParameter {
	private JRDesignParameter param;

	public ParameterJasper(JRDesignParameter param) {
		this.param = param;
	}

	public JRDesignParameter getParam() {
		return param;
	}

	public String getName() {
		return param.getName();
	}

	public Class<?> getValueClass() {
		return param.getValueClass();
	}

	public String getDescription() {
		return param.getDescription();
	}

	public String getLabel() {
		return param.getName();
	}

	public boolean isMandatory() {
		return false;
	}

	public boolean isReadOnly() {
		return false;
	}

	public boolean isStrictMin() {
		return false;
	}

	public String getMinValue() {
		return null;
	}

	public boolean isStrictMax() {
		return false;
	}

	public String getMaxValue() {
		return null;
	}

	public String getPattern() {
		return null;
	}
}
