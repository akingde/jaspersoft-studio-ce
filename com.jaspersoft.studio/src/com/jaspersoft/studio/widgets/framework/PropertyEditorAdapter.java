/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework;

import net.sf.jasperreports.engine.JRExpression;

/**
 * A dummy property editor that can be used we want to define a property editor
 * but we don't want to implement all the methods. All the methods here are empty
 * so it is not intended to be used without implement some of them
 * 
 * @author Orlandin Marco
 *
 */
public class PropertyEditorAdapter implements IPropertyEditor {

	@Override
	public String getPropertyValue(String propertyName) {
		return null;
	}

	@Override
	public JRExpression getPropertyValueExpression(String propertyName) {
		return null;
	}

	@Override
	public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
	}

	@Override
	public void removeProperty(String propertyName) {
	}

}
