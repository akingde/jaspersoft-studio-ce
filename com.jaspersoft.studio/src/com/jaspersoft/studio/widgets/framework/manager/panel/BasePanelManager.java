/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager.panel;


import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * Base implementation of an {@link IPanelManager} that allow simply to handle the 
 * widgets. The widgets are created with a label on their left. The widget are of 
 * type {@link WItemProperty}
 * 
 * @author Orlandin Marco
 *
 */
public class BasePanelManager implements IPanelManager {
	
	/**
	 * The label object is stored on the {@link WItemProperty} associated to it.
	 * It is stored in the property map of the swt element and this is the key
	 */
	protected static final String LABEL_KEY = "widgetLabel";
	
	/**
	 * List of the created widgets
	 */
	protected List<WItemProperty> properties = new ArrayList<WItemProperty>();
	
	/**
	 * Composite where the widgets are created
	 */
	protected Composite parent;
	
	/**
	 * Constructor 
	 * 
	 * @param parent composite where the widgets will be created. Must be not null. The 
	 * layout of the composite will be set to a {@link GridLayout} of two columns, one 
	 * for the label, the other for the widget
	 */
	public BasePanelManager(Composite parent) {
		this.parent = parent;
		parent.setLayout(new GridLayout(2, false));
	}
	
	/**
	 * Create the widgets and store their references. The widgets are created with default
	 * a {@link GridData} as LayoutData
	 */
	@Override
	public IWItemProperty createWidget(WidgetPropertyDescriptor widgetDesc, ItemPropertyDescription<?> widget, IPropertyEditor editor, ExpressionContext ec) {
		
		//create the label
		Label label = WidgetFactory.createLabelForProperty(parent, widgetDesc);
		label.setLayoutData(new GridData());
		
		//create the widget
		WItemProperty widgetEditor = new WItemProperty(parent, SWT.NONE, widget, editor);
		widgetEditor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetEditor.setExpressionContext(ec);
		
		//store the references
		widgetEditor.setData(LABEL_KEY, label);
		properties.add(widgetEditor);
		
		return widgetEditor;
	}

	@Override
	public void disposeWidgets() {
		properties.clear();
		for(Control control : parent.getChildren()){
			control.dispose();
		}
	}

	@Override
	public void updateWidgets() {
		for(IWItemProperty widget : properties){
			widget.updateWidget();
		}
	}

	@Override
	public List<IWItemProperty> getWidgets() {
		return new ArrayList<IWItemProperty>(properties);
	}

	@Override
	public List<String> validateWidgets(boolean stopToFirst) {
		List<String> errorMessages = new ArrayList<String>();
		for(WItemProperty property : properties){
			List<String> validationResult = property.isValueValid();
			if (validationResult != null && ! validationResult.isEmpty()){
				errorMessages.addAll(validationResult);
				if (stopToFirst){
					break;
				}
			}
		}
		return errorMessages;
	}

}
