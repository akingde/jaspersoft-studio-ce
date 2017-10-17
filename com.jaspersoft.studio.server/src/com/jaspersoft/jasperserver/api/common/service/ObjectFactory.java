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

package com.jaspersoft.jasperserver.api.common.service;

import java.util.List;
import java.util.Map;

/**
 * @author swood
 *
 */
public interface ObjectFactory {

	public Class getImplementationClass(Map classMappings, Class itfClass);

	public Class getImplementationClass(Map classMappings, String id);
	
	public String getImplementationClassName(Map classMappings, Class itfClass);

	public String getImplementationClassName(Map classMappings, String id);

	public Class getInterface(Map classMappings, Class _class);

	public String getIdForClass(Map classMappings, Class _class);

	public String getInterfaceName(Map classMappings, Class _class);

	public Object newObject(Map classMappings, Class itfClass);

	public Object newObject(Map classMappings, String id);
	
	public List getKeys(Map classMappings);
	
}
