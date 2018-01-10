/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.widgets.framework.ui;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.expression.ExpressionContext;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.descriptors.NamedEnumPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.IWItemProperty;
import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedEvent;
import com.jaspersoft.studio.widgets.framework.events.ItemPropertyModifiedListener;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.customizers.shape.ShapeTypeEnum;

/**
 * Panel manager used to show the points definition widget when a polygon or polyline shape
 * is selected
 * 
 * @author Orlandin Marco
 *
 */
public class ShapePanelManager extends SeriesSelectionPanelManager {
	
	private ItemPropertyModifiedListener shapeTypeChange = new ItemPropertyModifiedListener() {
		
		@Override
		public void itemModified(ItemPropertyModifiedEvent event) {
			WItemProperty editedWidget = event.source;
			updatePointsVisibility(editedWidget);
		}
	};
	
	public ShapePanelManager(Composite parent) {
		super(parent);
	}
	
	protected void updatePointsVisibility(WItemProperty shapeWidget){
		if (shapeWidget != null){
			if (shapeWidget.isExpressionMode()){
				setPropertyVisible(ShapePointsDescription.SHAPE_POINTS_PROPERTY, true);
			} else {
				ShapeTypeEnum value = null;
				String editorValue = shapeWidget.getStaticValue(); 
				if (editorValue != null){
					value = NamedEnumPropertyDescriptor.getEnumValue(ShapeTypeEnum.ELLIPSE, NullEnum.NOTNULL, editorValue);
				}
				boolean pointsVisible = ShapeTypeEnum.POLYLINE.equals(value) || ShapeTypeEnum.POLYGON.equals(value);
				setPropertyVisible(ShapePointsDescription.SHAPE_POINTS_PROPERTY, pointsVisible);
			}
		}	
	}
	
	@Override
	public IWItemProperty createWidget(WidgetPropertyDescriptor widgetDesc, ItemPropertyDescription<?> widget,IPropertyEditor editor, ExpressionContext ec) {
		WItemProperty property =  (WItemProperty)super.createWidget(widgetDesc, widget, editor, ec);
		if (widget.getName().equals(ShapePointsDescription.SHAPE_TYPE_PROPERTY)){
			property.addModifyListener(shapeTypeChange);
		}
		return property;
	}
	
	@Override
	public void updateWidgets() {
		super.updateWidgets();
		WItemProperty shapeWidget = getWidget(ShapePointsDescription.SHAPE_TYPE_PROPERTY);
		updatePointsVisibility(shapeWidget);
	}
}
