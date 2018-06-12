/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.customvisualization.properties.SPCVItemDataList;
import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

import net.sf.jasperreports.customvisualization.design.CVDesignComponent;

/**
 * Property descriptor for the {@link CVDesignComponent#PROPERTY_ITEM_DATA} property.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class CVItemDataDescriptor extends NTextPropertyDescriptor {

	public CVItemDataDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return null;
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return new CVItemDataLabelProvider();
	}
	
	@Override
	public SPCVItemDataList createWidget(Composite parent,
			AbstractSection section) {
		return new SPCVItemDataList(parent,section,this);
	}
	
}
