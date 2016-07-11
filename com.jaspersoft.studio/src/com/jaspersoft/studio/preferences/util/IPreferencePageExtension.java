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
package com.jaspersoft.studio.preferences.util;

import org.eclipse.swt.widgets.Composite;

/**
 * Define an extension for an existing preference page. This is used to contribute
 * controls to a predefined preference page, without creating a new one
 * 
 * @author Orlandin Marco
 *
 */
public interface IPreferencePageExtension {
	
	/**
	 * Create the fields on an existing page
	 * 
	 * @param parent the composite of the target page, not null
	 * @param page the host page where the controls will be created, not null
	 */
	public void createContributedFields(Composite parent, FieldEditorOverlayPage page);
	
	/**
	 * When the apply button is pressed on the host page is pressed, this is called on 
	 * every contributed extensions on that page
	 */
	public void performApply();
	
	/**
	 * When the cancel button is pressed on the host page is pressed, this is called on 
	 * every contributed extensions on that page
	 */
	public void performCancel();
	
	/**
	 * When the defaults button is pressed on the host page is pressed, this is called on 
	 * every contributed extensions on that page
	 */
	public void performDefaults();

}
