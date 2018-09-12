/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.properties.internal.TabbedPropertyComposite;

/**
 * Composite used to create a single section in the properties view. It keep the properties page
 * reference to force the refresh and calculation of the scrollbar sizes (useful when controls are
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
