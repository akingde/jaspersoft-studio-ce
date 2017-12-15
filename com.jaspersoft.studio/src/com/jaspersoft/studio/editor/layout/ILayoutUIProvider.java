/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.layout;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * Interface used by the layout implementation to return an object
 * delegated to build the UI to configure the properties of that layout
 * 
 * @author Orlandin Marco
 */
public interface ILayoutUIProvider {

	/**
	 * Create the controls provided to configure the child element of the container that is using
	 * as layout the implementation of this class
	 * 
	 * @param parent composite where to create the controls
	 */
	public void createControls(Composite parent);
	
	/**
	 * Create the controls provided to configure the container where the layout is assigned 
	 * 
	 * @param parent composite where to create the controls, it has a {@link GridLayout} with a single column
	 */
	public void createLayoutControls(Composite parent);
	
	/**
	 * If the layout provide graphical controls to configure the layout properties of an element,
	 * this is called to set the properties of the element inside the control. The implementation of 
	 * this will be called when a children of an element that is using this implementation as layout is
	 * selected
	 * 
	 * @param selectedElement the currently selected element, it is not null
	 * @param section the currently selected section, it is not null
	 */
	public void setData(ANode selectedElement, AbstractSection section);
}
