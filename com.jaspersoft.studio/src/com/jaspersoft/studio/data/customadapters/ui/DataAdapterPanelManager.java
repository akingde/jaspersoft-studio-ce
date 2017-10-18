/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters.ui;

import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.manager.ItemPropertyLayoutData;
import com.jaspersoft.studio.widgets.framework.manager.WidgetFactory;
import com.jaspersoft.studio.widgets.framework.manager.panel.BasePanelManager;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * Panel manager used to create all the widgets of the data adapter definition, they will
 * be force in simple mode since the data adapter doesn't support expressions
 * 
 * @author Orlandin Marco
 *
 */
public class DataAdapterPanelManager extends BasePanelManager {

	/**
	 * The background of the widgets
	 */
	private RGB backgroundColor;
	
	/**
	 * Create the panel manager
	 * 
	 * @param parent the parent of the widgets
	 * @param backgroundColor the background color, can be null to use the default one
	 */
	public DataAdapterPanelManager(Composite parent, RGB backgroundColor) {
		super(parent);
		this.backgroundColor = backgroundColor;
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
		WItemProperty widgetEditor = new WItemProperty(parent, WItemProperty.FORCE_SIMPLE_MODE, widget, editor);
		if (backgroundColor != null) {
			widgetEditor.setBackground(ResourceManager.getColor(backgroundColor));
			label.setBackground(ResourceManager.getColor(backgroundColor));
		}
		
		//hide the expression button
		ItemPropertyLayoutData widgetLayoutData = new ItemPropertyLayoutData();
		widgetLayoutData.buttonVisibleSimpleMode = false;
		widgetEditor.setContentLayoutData(widgetLayoutData);
		widgetEditor.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		widgetEditor.setExpressionContext(ec);
		
		//store the references
		widgetEditor.setData(LABEL_KEY, label);
		properties.add(widgetEditor);
		
		return widgetEditor;
	}
}
