/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

/**
 * Provide the base definition of a dynamic widget widget. An implementation of this
 * class must have and empty constructor to build the contributed elements
 */
public interface ItemPropertyDescription<T> {
	
	/**
	 * Return if the widget is in readonly mode
	 * 
	 * @return true if the widget is readonly, false otherwise
	 */
	public boolean isReadOnly();

	/**
	 * The label for the widget
	 * 
	 * @return the label to show aside of the widget
	 */
	public String getLabel();

	/**
	 * The name of the property handled by this widget
	 * 
	 * @return a not null string
	 */
	public String getName();

	/**
	 * The description of the widget
	 * 
	 */
	public String getDescription();

	/**
	 * The flag to know if a value on the widget is mandatory or not
	 */
	public boolean isMandatory();

	/**
	 * The default value as a string
	 *
	 */
	public String getDefaultValueString();

	/**
	 * The default value of the widget
	 */
	public T getDefaultValue();
	
	public T getFallbackValue();
	
	/**
	 * Return the tooltip text
	 * @return a not null tooltip
	 */
	public String getToolTip();

	/**
	 * Called when a value in the widget is edited by the user
	 * 
	 * @param editedControl the control that triggered the edit
	 * @param wiProp the {@link IWItemProperty} in which this widget is wrapped
	 */
	public void handleEdit(Control editedControl, IWItemProperty wiProp);

	/**
	 * Create the SWT control of the widget
	 * 
	 * @param wiProp the {@link IWItemProperty} in which this widget is wrapped
	 * @param parent the parent composite
	 * @return an swt control to handle this property
	 */
	public Control createControl(IWItemProperty wiProp, Composite parent);

	/**
	 * Update the value inside the widget with the one inside the model element
	 * 
	 * @param c the current control
	 * @param wiProp the {@link IWItemProperty} in which this widget is wrapped
	 */
	public void update(Control c, IWItemProperty wip);


	/**
	 * Clone the current descriptor 
	 * 
	 * @return the cloned {@link ItemPropertyDescription}
	 */
	public ItemPropertyDescription<T> clone();
	
	/**
	 * Create an instance of the widget from a {@link WidgetPropertyDescriptor}. It is used to
	 * create the contributed widgets
	 * 
	 * @param cd the {@link WidgetsDescriptor}, typically used for the localization
	 * @param cpd the description of the widget to create
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @return an instance of an {@link ItemPropertyDescription} or null if the passed {@link WidgetPropertyDescriptor} doesn't describe a 
	 * the current implementation
	 */
	public ItemPropertyDescription<?> getInstance(WidgetsDescriptor cd, WidgetPropertyDescriptor cpd, JasperReportsConfiguration jConfig);

}
