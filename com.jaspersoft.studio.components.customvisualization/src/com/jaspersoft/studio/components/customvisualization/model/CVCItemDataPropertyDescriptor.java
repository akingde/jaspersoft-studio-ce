/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.model;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.customvisualization.properties.SPCVCItemDataList;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.section.AbstractSection;

/**
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class CVCItemDataPropertyDescriptor extends
		AItemDataListPropertyDescriptor {

	public CVCItemDataPropertyDescriptor(Object id, String displayName, APropertyNode pNode) {
		super(id, displayName, pNode);
	}

	@Override
	protected void initShowColumns() {
		descriptor = new CVCItemDescriptor();
	}

	@Override
	protected SPCVCItemDataList createSPWidget(Composite parent,
			AbstractSection section) {
		return new SPCVCItemDataList(parent, section, this);
	}
}
