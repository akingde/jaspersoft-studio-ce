/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager.panel;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * The panel manager can be used to create a series of {@link IWItemProperty} and handle them providing additional
 * features, that can not be obtained by the JSON definition (for example to define dependency between widgets).
 * 
 * To be created a panel manager must have a constructor with a single parameter of type {@link Composite}, that is 
 * the composite where all the controls created by this panel manager will be placed
 * 
 * @author Orlandin Marco
 *
 */
public interface IPanelManager {

	/**
	 * Create a single widget inside the the passed composite
	 * 
	 * @param widgetDesc the {@link WidgetPropertyDescriptor} that define the widget
	 * @param widget the {@link ItemPropertyDescription} for the defined widget
	 * @param editor the {@link IPropertyEditor} used to read and write values from the model object
	 * @param ec the {@link ExpressionContext} for the expression editor
	 * @return the {@link IWItemProperty} created 
	 */
	public IWItemProperty createWidget(WidgetPropertyDescriptor widgetDesc, ItemPropertyDescription<?> widget, IPropertyEditor editor, ExpressionContext ec);
	
	/**
	 * Dispose all the widgets created trough this panel manager
	 */
	public void disposeWidgets();
	
	/**
	 * Refresh all the values inside every widget create trough this panel manager
	 */
	public void updateWidgets();
	
	/**
	 * Return the list of all the widgets created trough this panel manager
	 * 
	 * @return a not null list of {@link IWItemProperty}
	 */
	public List<IWItemProperty> getWidgets();
	
	/**
	 * Validate all the widgets inside this panel manager
	 * 
	 * @param stopToFirst true if the collection of error messages and validation should stop
	 * to the first invalid widget, false if it should continue for every widgets
	 * 
	 * @return an empty array if every widget has a valid value, otherwise the list
	 * of validation errors
	 */
	public List<String> validateWidgets(boolean stopToFirst);
}
