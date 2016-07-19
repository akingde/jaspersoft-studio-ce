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
package com.jaspersoft.studio.properties.view;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.internal.TabbedPropertyComposite;

/**
 * Composite used to create a single section in the preferences view. It keep the preference page
 * reference to force the refresh and calculation of the scrollbar sizes (usefull when controls are
 * created dynamically)
 */
public class SectionContainerComposite extends Composite {

	/**
	 * The page of the proeprties
	 */
	private TabbedPropertySheetPage page;
	
	public SectionContainerComposite(Composite parent, TabbedPropertySheetPage page, int style) {
		super(parent, style);
		this.page = page;
	}

	/**
	 * Refresh the content of the composite and reset the scroll area size
	 */
	public void refreshPageComposite(){
		TabbedPropertyComposite composite = page.getTabbedPropertyComposite();
		composite.layout();
		composite.updatePageMinimumSize();
	}
}
