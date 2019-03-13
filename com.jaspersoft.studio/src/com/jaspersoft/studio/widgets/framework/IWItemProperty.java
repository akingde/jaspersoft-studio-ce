/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework;

/**
 * Wrapper for a ItemPropertyDescription widget, it provide the method to 
 * both read and write the value on the widget and on the model behind it
 * 
 */
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.swt.events.ExpressionModifiedListener;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedListener;
import com.jaspersoft.studio.widgets.framework.ui.menu.IMenuProvider;

import net.sf.jasperreports.engine.JRExpression;

public interface IWItemProperty {
	
	/**
	 * Set the property item into the refreshing status. The refreshing status 
	 * is used outside to skip some events. For example it is typically set when
	 * a value is sent to the model and on the widgets, to avoid to trigger and 
	 * infinite call stack of modify listener the flag is used to skip every modify
	 * event when another one is still ending
	 * 
	 * @param refreshing true if the item is in refreshing status, false otherwise
	 */
	public void setRefresh(boolean refreshing);

	/**
	 * Get the property item into the refreshing status. The refreshing status 
	 * is used outside to skip some events. For example it is typically set when
	 * a value is sent to the model and on the widgets, to avoid to trigger and 
	 * infinite call stack of modify listener the flag is used to skip every modify
	 * event when another one is still ending
	 * 
	 * @return true if the item is in refreshing status, false otherwise
	 */
	public boolean isRefresh();
	
	/**
	 * Return if the widget is currently set in expression mode
	 * 
	 * @return true if the widget is in expression mode, false otherwise
	 */
	public boolean isExpressionMode();

	/**
	 * Return the widget control
	 * 
	 * @return a not null control
	 */
	public Control getControl();
	
	/**
	 * Return the name of the handled property
	 * 
	 * @return a not null name
	 */
	public String getPropertyName();
	
	/**
	 * Return the static value of the property
	 * 
	 * @return a string representing the static value of the property, can be null
	 */
	public String getStaticValue();
	
	/**
	 * Get the fallback value for the current property. The fallback value is
	 * used when no value is provided. Can be overridden to provide a custom behavior
	 */
	public Object getFallbackValue();
	
	/**
	 * Return the expression value of the property
	 * 
	 * @return the expression, can be null
	 */
	public JRExpression getExpressionValue();
	
	/**
	 * Set the value both on the model element and on the widget
	 * 
	 * @param staticValue the new static value of the property
	 * @param expressionValue the new expression value of the property
	 */
	public void setValue(String staticValue, JRExpression expressionValue);
	
	/**
	 * Update the widget value with the one inside the model element. This should
	 * not trigger the edit listener since the value inside the element is already
	 * correct. This will trigger the layout of the widget
	 */
	public void updateWidget();
	
	/**
	 * Update the widget value with the one inside the model element. This should
	 * not trigger the edit listener since the value inside the element is already
	 * correct
	 * 
	 * @param layout true if the widget should be relayouted, galse otherwise
	 */
	public void updateWidget(boolean layout);
	
	/**
	 * Return the contextual menu provider for this element
	 * 
	 * @return the contextual menu provider or null if not present
	 */
	public IMenuProvider getContextualMenuProvider();
	
	/**
	 * Get the property editor of this widget, used to read and write the property
	 * from the handled element
	 * 
	 * @return a not null {@link IPropertyEditor}
 	 */
	public IPropertyEditor getPropertyEditor();
	
	/**
	 * Set the property editor of this widget, used to read and write the property
	 * from the handled element. Changing the editor will not update the widget, so
	 * to see any change reflected in the widgets the method updateWidget should be 
	 * called
	 * 
	 * @param editor a not null {@link IPropertyEditor}
 	 */
	public void setPropertyEditor(IPropertyEditor editor);
	
	/**
	 * Adds a new listener that will be notified of any expression change/notification.
	 * 
	 * @param ml
	 *          the new {@link ExpressionModifiedListener} to add
	 */
	public void addModifyListener(ItemPropertyModifiedListener ml);

	/**
	 * Removes an {@link ExpressionModifiedListener} instance.
	 * 
	 * @param ml
	 *          the {@link ExpressionModifiedListener} instance to be removed
	 */
	public void removeModifyListener(ItemPropertyModifiedListener ml);
}
