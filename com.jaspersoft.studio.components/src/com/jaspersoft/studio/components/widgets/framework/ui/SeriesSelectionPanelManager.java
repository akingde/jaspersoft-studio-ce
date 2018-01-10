/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedListener;
import com.jaspersoft.studio.widgets.framework.manager.panel.VisibilityPanelManager;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

/**
 * Panel manager used to handle the item selection mode. Since there are three selection mode:
 *  all, select series by name, select series by index, this panel manager will allow to
 *  show the appropriate controls to configure the desired selection mode
 * 
 * @author Orlandin Marco
 *
 */
public class SeriesSelectionPanelManager extends VisibilityPanelManager {

	public static final String SERIES_SELECTION_TYPE_PROPERTY = "selectionMode";
	
	public static final String SERIES_SELECTION_TYPE_NAME_PROPERTY = "itemSeries";
	
	public static final String SERIES_SELECTION_TYPE_INDEX_PROPERTY = "itemIndex";
	
	public static final String SERIES_SELECTION_TYPE_ALL_PROPERTY = "allItems";
	
	private ItemPropertyModifiedListener shapeSelectionChange = new ItemPropertyModifiedListener() {
		
		@Override
		public void itemModified(ItemPropertyModifiedEvent event) {
			WItemProperty editedWidget = event.source;
			if (event.expressionValue != null){
				//since we are using a fake property for the combo set the value of the expression also on the real property
				editedWidget.getPropertyEditor().createUpdateProperty(SERIES_SELECTION_TYPE_ALL_PROPERTY, event.staticValue, event.expressionValue);
			} else {
				if (SERIES_SELECTION_TYPE_ALL_PROPERTY.equals(event.staticValue)){
					editedWidget.getPropertyEditor().createUpdateProperty(SERIES_SELECTION_TYPE_ALL_PROPERTY, Boolean.TRUE.toString(), event.expressionValue);
					editedWidget.getPropertyEditor().removeProperty(SERIES_SELECTION_TYPE_INDEX_PROPERTY);
					editedWidget.getPropertyEditor().removeProperty(SERIES_SELECTION_TYPE_NAME_PROPERTY);
				} else if (SERIES_SELECTION_TYPE_NAME_PROPERTY.equals(event.staticValue)) {
					editedWidget.getPropertyEditor().removeProperty(SERIES_SELECTION_TYPE_ALL_PROPERTY);
					editedWidget.getPropertyEditor().removeProperty(SERIES_SELECTION_TYPE_INDEX_PROPERTY);					
				} else if (SERIES_SELECTION_TYPE_INDEX_PROPERTY.equals(event.staticValue)) {
					editedWidget.getPropertyEditor().removeProperty(SERIES_SELECTION_TYPE_ALL_PROPERTY);
					editedWidget.getPropertyEditor().removeProperty(SERIES_SELECTION_TYPE_NAME_PROPERTY);					
				}
			}
			updateSelectionVisibility(editedWidget);
		}
	};

	public SeriesSelectionPanelManager(Composite parent) {
		super(parent);
	}

	protected void updateSelectionVisibility(WItemProperty selectionWidget){
		if (selectionWidget != null){
			if (selectionWidget.isExpressionMode()){
				setPropertyVisible(SERIES_SELECTION_TYPE_INDEX_PROPERTY, true);
				setPropertyVisible(SERIES_SELECTION_TYPE_NAME_PROPERTY, true);
			} else {
				String editorValue = selectionWidget.getStaticValue(); 
				boolean seriesNameVisible = SERIES_SELECTION_TYPE_NAME_PROPERTY.equals(editorValue);
				boolean seriesIndexVisible = SERIES_SELECTION_TYPE_INDEX_PROPERTY.equals(editorValue);
				setPropertyVisible(SERIES_SELECTION_TYPE_NAME_PROPERTY, seriesNameVisible);
				setPropertyVisible(SERIES_SELECTION_TYPE_INDEX_PROPERTY, seriesIndexVisible);
			}
		}	
	}
	
	@Override
	public IWItemProperty createWidget(WidgetPropertyDescriptor widgetDesc, ItemPropertyDescription<?> widget,IPropertyEditor editor, ExpressionContext ec) {
		WItemProperty property =  (WItemProperty)super.createWidget(widgetDesc, widget, editor, ec);
		if (widget.getName().equals(SERIES_SELECTION_TYPE_PROPERTY)){
			property.addModifyListener(shapeSelectionChange);
		} 
		return property;
	}
	
	@Override
	public void updateWidgets() {
		super.updateWidgets();
		WItemProperty selectionWidget = getWidget(SERIES_SELECTION_TYPE_PROPERTY);
		updateSelectionVisibility(selectionWidget);
	}
}
