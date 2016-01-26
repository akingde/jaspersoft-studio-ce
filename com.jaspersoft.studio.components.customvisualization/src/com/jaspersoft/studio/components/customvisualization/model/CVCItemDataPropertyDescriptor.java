/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
