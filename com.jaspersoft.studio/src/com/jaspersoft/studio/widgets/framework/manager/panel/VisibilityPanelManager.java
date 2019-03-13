/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.manager.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * Extends the {@link BasePanelManager} adding the functionality to hide the widgets that 
 * handle a specific property
 * 
 * @author Orlandin Marco
 *
 */
public class VisibilityPanelManager extends BasePanelManager {

	/**
	 * Widget map where every widget created is stored, and the key is the property handled by it
	 */
	private HashMap<String, WItemProperty> widgetsMap = new HashMap<String, WItemProperty>();
	
	/**
	 * Constructor 
	 * 
	 * @param parent composite where the widgets will be created. Must be not null. The 
	 * layout of the composite will be set to a {@link GridLayout} of two columns, one 
	 * for the label, the other for the widget
	 */
	public VisibilityPanelManager(Composite parent) {
		super(parent);
	}

	/**
	 * Create the widget an store it in the map
	 */
	@Override
	public IWItemProperty createWidget(WidgetPropertyDescriptor widgetDesc, ItemPropertyDescription<?> widget, IPropertyEditor editor, ExpressionContext ec) {
		//create the label
		Label label = WidgetFactory.createLabelForProperty(parent, widgetDesc);
		label.setLayoutData(new GridData());
		
		//create the widget, with a validation on the mandatory only if the value is visible
		WItemProperty widgetEditor = new WItemProperty(parent, SWT.NONE, widget, editor) {
			
			@Override
			public List<String> isValueValid() {
				if (!isVisible()) return new ArrayList<String>();
				else return super.isValueValid();
			};
		};
		widgetEditor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetEditor.setExpressionContext(ec);
		
		//store the references
		widgetEditor.setData(LABEL_KEY, label);
		properties.add(widgetEditor);
		widgetsMap.put(widget.getName(), widgetEditor);
		return widgetEditor;
	}
	
	@Override
	public void disposeWidgets() {
		super.disposeWidgets();
		widgetsMap.clear();
	}
	
	protected WItemProperty getWidget(String propertyName){
		return widgetsMap.get(propertyName);
	}
	
	/**
	 * Set the visibility of a widget (and its label), with a specific property.
	 * If the widgets can't be found nothing is done
	 * 
	 * @param propertyName the name of the property associated to the widget
	 * @param visible true if the widget should be visible, false otherwise
	 */
	public void setPropertyVisible(String propertyName, boolean visible){
		WItemProperty property =  getWidget(propertyName);
		if (property != null && property.getVisible() != visible){
			property.setVisible(visible);
			GridData data = (GridData)property.getLayoutData();
			data.exclude = !visible;
			
			Label label = (Label)property.getData(LABEL_KEY);
			if (label != null){
				label.setVisible(visible);
				data = (GridData)label.getLayoutData();
				data.exclude = !visible;	
			}
			parent.layout(true, true);
		}
	}
}
