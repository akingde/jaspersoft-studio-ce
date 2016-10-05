/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager;

import org.eclipse.swt.SWT;

import com.jaspersoft.studio.widgets.framework.WItemProperty;

/**
 * Layout data that can be set inside a {@link WItemProperty} to be used with a
 * {@link ItemPropertyLayout}
 * 
 */
public class ItemPropertyLayoutData {
	
	/**
	 * Used to know if the simple control should fill vertically the size of the {@link WItemProperty} in which is created
	 * Default value is false
	 */
	public boolean widgetFillVertical = false;

	/**
	 * Used to know if the expression control should fill vertically the size of the {@link WItemProperty} in which is created
	 * Default value is false
	 */
	public boolean expressionFillVertical = false;
	
	/**
	 * Used to know the height hint of the simple control, used only if widgetFillVertical is false. If this space is available
	 * the control will be set to this height
	 * Default value is SWT.DEFAULT
	 */
	public int widgetHeightHint = SWT.DEFAULT;

	/**
	 * Used to know the height hint of the expression control, used only if expressionFillVertical is false. If this space is available
	 * the control will be set to this height
	 * Default value is 22
	 */
	public int expressionHeightHint = 22;	
}
