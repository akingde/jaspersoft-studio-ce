/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.input;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.design.JRDesignParameter;

public class ParameterJasper implements IParameter {
	
	private JRDesignParameter param;
	
	private JasperReportsConfiguration jConfig;

	public ParameterJasper(JRDesignParameter param, JasperReportsConfiguration jConfig) {
		this.param = param;
		this.jConfig = jConfig;
	}

	public JRDesignParameter getParam() {
		return param;
	}

	public String getName() {
		return param.getName();
	}

	public Class<?> getValueClass() {
		//try first the jss classloader to resolve the parameter class
		try {
			Class<?> clazz = jConfig.getClassLoader().loadClass(param.getValueClassName());
			return clazz;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(e);
			//unable to load with the JSS classloader, try the jr default one
			return param.getValueClass();
		}
	}

	public String getDescription() {
		return param.getDescription();
	}

	public String getLabel() {
		if (param.isSystemDefined())
			return MessagesByKeys.getString(param.getName());
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
