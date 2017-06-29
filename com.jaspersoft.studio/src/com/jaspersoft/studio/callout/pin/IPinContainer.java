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
package com.jaspersoft.studio.callout.pin;

import net.sf.jasperreports.engine.JRPropertiesHolder;

/**
 * Interface that a node must implement to specify that on it the property
 * of a callout can be stored
 * 
 * @author Orlandin Marco
 */
public interface IPinContainer {
	
	/**
	 * Get the properties holder of the current element
	 * 
	 * @return a not null array of properties holder.
	 */
	public JRPropertiesHolder[] getPropertyHolder();
	
}
