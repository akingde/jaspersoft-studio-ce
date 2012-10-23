/*******************************************************************************
 * ---------------------------------------------------------------------
 * Copyright (C) 2005 - 2012 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 * ---------------------------------------------------------------------
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor.combo;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPButton;

/**
 * This class describe a toolbar with two buttons to increment and decrement the font 
 * size
 * @author Orlandin Marco
 *
 */
public class ButtonPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget {

	/**
	 * The edited node
	 */
	private APropertyNode elementFont;

	/**
	 * Crate a new descriptor for increment or decrement of the font size
	 * @param id id of this attribute
	 * @param fontModel model that contain the font attribute to increment\decrement
	 */
	public ButtonPropertyDescriptor(Object id, APropertyNode fontModel) {
		super(id, "");
		elementFont = fontModel;
	}
	
	/**
	 * Create the widget and return it
	 */
	@Override
	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPButton(parent, section, this, elementFont);
	}

}
