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
package com.jaspersoft.studio.model;

import com.jaspersoft.studio.editor.layout.ILayout;

import net.sf.jasperreports.engine.JRPropertiesHolder;

public interface IContainerLayout {
	
	public JRPropertiesHolder[] getPropertyHolder();
	
	/**
	 * Return the default layout used for this element when no other
	 * layout is defined
	 * 
	 * @return the layout to be used by default, if it return null then 
	 * the FreeLayout is used
	 */
	public ILayout getDefaultLayout();
}
