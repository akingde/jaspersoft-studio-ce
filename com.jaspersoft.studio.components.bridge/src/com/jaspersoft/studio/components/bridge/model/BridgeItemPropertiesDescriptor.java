/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * Licensed under commercial Jaspersoft Subscription License Agreement
 ******************************************************************************/
package com.jaspersoft.studio.components.bridge.model;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.jasperreports.components.bridge.design.BridgeDesignComponent;
import com.jaspersoft.studio.components.bridge.properties.SPBridgeItemPropertiesList;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * Property descriptor for the
 * {@link BridgeDesignComponent#PROPERTY_ITEM_PROPERTIES} property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class BridgeItemPropertiesDescriptor extends NTextPropertyDescriptor {

	public BridgeItemPropertiesDescriptor(Object id, String displayName) {
		super(id, displayName);
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return null;
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return new BridgeItemPropertiesLabelProvider();
	}
	
	@Override
	public ASPropertyWidget createWidget(Composite parent,
			AbstractSection section) {
		return new SPBridgeItemPropertiesList(parent,section,this);
	}

}
