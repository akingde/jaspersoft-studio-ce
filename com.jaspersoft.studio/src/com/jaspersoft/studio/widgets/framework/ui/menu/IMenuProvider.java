/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui.menu;

import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * A menu provider is a class that allow to define a contextual menu on the widget provided
 * by an {@link IWItemProperty}. The instance of an {@link IMenuProvider} must be set inside
 * the instance of an {@link IWItemProperty} with the method setMenuProvider
 * 
 * @author Orlandin Marco
 *
 */
public interface IMenuProvider {

	/**
	 * Create the menu on the element
	 * 
	 * @param wProp the {@link IWItemProperty} containing the widget
	 * @param item the {@link ItemPropertyDescription} that define the widget
	 * @param c the control where the menu should be set
	 */
	public void setupMenu(IWItemProperty wProp, ItemPropertyDescription<?> item, Control c);
	
}
