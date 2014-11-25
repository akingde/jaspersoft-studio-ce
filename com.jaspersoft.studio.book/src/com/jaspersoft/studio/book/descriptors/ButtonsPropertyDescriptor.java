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
package com.jaspersoft.studio.book.descriptors;

import java.lang.reflect.Constructor;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetEntry;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;

/**
 * This class describe a generic descriptor that show a button to do some stuff.
 * The button is build trough reflection and must have a constructor with three 
 * parameters with the follwing types in this order: Composite, AbstractSection 
 * and IPropertyDescriptor
 * 
 * @author Orlandin Marco
 * 
 */
public class ButtonsPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget, IHelp {

	/**
	 * The class of the button
	 */
	private Class<? extends ASPropertyWidget> button;
	
	private IHelpRefBuilder refBuilder;

	/**
	 * Crate a new descriptor 
	 * 
	 * @param id id of the property
	 * @param button the class of the button, must extend ASPropertyWidget
	 */
	public ButtonsPropertyDescriptor(Object id, Class<? extends ASPropertyWidget> button) {
		super(id, "");
		this.button = button;
		//This filter avoid to show this element on the advanced properties views
		setFilterFlags(new String[]{IPropertySheetEntry.FILTER_ID_EXPERT});
	}

	/**
	 * Create the widget and return it or null if the type of the button is 
	 * not valid
	 */
	@Override
	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		Object[] convertedArgs = new Object[]{parent, section, this};
		Class<?>[] arguments = new Class<?>[]{Composite.class, AbstractSection.class, IPropertyDescriptor.class};
		try {
			Constructor<? extends ASPropertyWidget> constructor = button.getConstructor(arguments);
			return constructor.newInstance(convertedArgs);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}
}
