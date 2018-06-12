/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import java.util.List;

import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.customvisualization.design.CVDesignComponent;
import net.sf.jasperreports.engine.JRExpression;

/**
 * Editor to write and read the property between the CVC component and 
 * the dynamic widgets
 * 
 * @author Orlandin Marco
 *
 */
public class CVCPropertyEditor extends PropertyEditorAdapter {
	
	/**
	 * The section used to write the value inside the widget
	 */
	private AbstractSection section;
	
	/**
	 * The current list of property on the CVC component
	 */
	private List<ItemProperty> itemProps;
	
	public CVCPropertyEditor(AbstractSection section, List<ItemProperty> itemProps) {
		this.section = section;
		this.itemProps = itemProps;
	}

	@Override
	public String getPropertyValue(String propertyName) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps, propertyName);
		if (p != null) {
			return p.getValue();
		}
		return null;
	}
	
	@Override
	public JRExpression getPropertyValueExpression(String propertyName) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps, propertyName);
		if (p != null) {
			return p.getValueExpression();
		}
		return null;
	}
	
	@Override
	public void removeProperty(String propertyName) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps, propertyName);
		if (p != null) {
			itemProps.remove(p);
			section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
		}
	}
	
	@Override
	public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps, propertyName);
		if (p != null) {
			int idx = itemProps.indexOf(p);
			if (idx >= 0) {
				itemProps.set(idx, new StandardItemProperty(propertyName, value, valueExpression));
			} else {
				itemProps.add(new StandardItemProperty(propertyName, value, valueExpression));
			}
		} else {
			itemProps.add(new StandardItemProperty(propertyName, value, valueExpression));
		}
		section.changeProperty(CVDesignComponent.PROPERTY_ITEM_PROPERTIES, itemProps);
	}

}
