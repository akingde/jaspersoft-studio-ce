/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.properties;

import com.jaspersoft.studio.components.customvisualization.model.CVCProprtiesExpressionDTO;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;

import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.engine.JRExpression;

/**
 * Editor to write and read the property between the CVC component and 
 * the dynamic widgets. Used in the advanced view
 * 
 * @author Orlandin Marco
 *
 */
public class CVCPropertyEditor extends PropertyEditorAdapter {
	
	/**
	 * The current list of property on the CVC component
	 */
	protected CVCProprtiesExpressionDTO itemProps;
	
	public CVCPropertyEditor(CVCProprtiesExpressionDTO itemProps) {
		this.itemProps = itemProps;
	}

	@Override
	public String getPropertyValue(String propertyName) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps.getItemProps(), propertyName);
		if (p != null) {
			return p.getValue();
		}
		return null;
	}
	
	@Override
	public JRExpression getPropertyValueExpression(String propertyName) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps.getItemProps(), propertyName);
		if (p != null) {
			return p.getValueExpression();
		}
		return null;
	}
	
	@Override
	public void removeProperty(String propertyName) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps.getItemProps(), propertyName);
		if (p != null) {
			itemProps.getItemProps().remove(p);
			executeChangeCommand();
		}
	}
	
	@Override
	public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
		ItemProperty p = ItemPropertyUtil.getProperty(itemProps.getItemProps(), propertyName);
		if (p != null) {
			int idx = itemProps.getItemProps().indexOf(p);
			if (idx >= 0) {
				itemProps.getItemProps().set(idx, new StandardItemProperty(propertyName, value, valueExpression));
			} else {
				itemProps.getItemProps().add(new StandardItemProperty(propertyName, value, valueExpression));
			}
		} else {
			itemProps.getItemProps().add(new StandardItemProperty(propertyName, value, valueExpression));
		}
		executeChangeCommand();
	}
	
	protected void executeChangeCommand() {
		
	}

}
