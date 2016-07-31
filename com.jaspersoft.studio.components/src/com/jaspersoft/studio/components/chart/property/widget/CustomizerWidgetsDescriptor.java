/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Class used to deserialize a chart customizer definition file
 * 
 * @author Orlandin Marco
 *
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CustomizerWidgetsDescriptor extends WidgetsDescriptor{
	
	/**
	 * The class of the chart customizer
	 */
	private String customizerClass;
	
	public String getCustomizerClass(){
		return customizerClass;
	}
	
	public void setCustomizerClass(String customizerClass){
		this.customizerClass = customizerClass;
	}

	@Override
	public String toString() {
		return getLabel();
	}
}
