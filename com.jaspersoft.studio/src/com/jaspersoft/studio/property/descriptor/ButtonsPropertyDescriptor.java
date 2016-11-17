/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptor;

import java.lang.reflect.Constructor;

import org.eclipse.swt.widgets.Composite;
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
	private Class<? extends ASPropertyWidget<ButtonsPropertyDescriptor>> button;
	
	private IHelpRefBuilder refBuilder;

	/**
	 * Crate a new descriptor 
	 * 
	 * @param id id of the property
	 * @param button the class of the button, must extend ASPropertyWidget
	 */
	public ButtonsPropertyDescriptor(Object id, Class<? extends ASPropertyWidget<ButtonsPropertyDescriptor>> button) {
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
	public ASPropertyWidget<ButtonsPropertyDescriptor> createWidget(Composite parent, AbstractSection section) {
		Object[] convertedArgs = new Object[]{parent, section, this};
		Class<?>[] arguments = new Class<?>[]{Composite.class, AbstractSection.class, ButtonsPropertyDescriptor.class};
		try {
			Constructor<? extends ASPropertyWidget<ButtonsPropertyDescriptor>> constructor = button.getConstructor(arguments);
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
