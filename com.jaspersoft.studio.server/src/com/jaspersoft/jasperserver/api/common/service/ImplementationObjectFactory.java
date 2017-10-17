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

/**
 * 
 * Maps from interfaces to implementations and back
 * 
 * @author swood
 *
 */
public interface ImplementationObjectFactory {
	public Class getImplementationClass(Class _class);
	public Class getImplementationClass(String id);
	public String getImplementationClassName(Class _class);
	public String getImplementationClassName(String id);
	public Class getInterface(Class _class);
	public String getInterfaceName(Class _class);
	public String getIdForClass(Class _class);
	public Object newObject(Class _class);
	public Object newObject(String id);
	public List getKeys();
}
