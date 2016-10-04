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
package com.jaspersoft.studio.editor.action.exporter;

/**
 * Simple implementation of an {@link IResourceDefinition}
 * 
 * @author Orlandin Marco
 *
 */
public class BaseResource implements IResourceDefinition {

	private String name;
	
	private Object data = null;
	
	public BaseResource(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return name;
	}

	public Object getData(){
		return data;
	}
	
	public void setData(Object data){
		this.data = data;
	}
	
}
