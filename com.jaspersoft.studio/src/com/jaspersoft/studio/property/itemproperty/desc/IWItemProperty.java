/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import net.sf.jasperreports.components.map.StandardItemProperty;

import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.property.itemproperty.ItemPropertyLabelProvider;

public interface IWItemProperty {
	public boolean isRefresh();

	public StandardItemProperty getValue();

	public void setValue(StandardItemProperty exp);

	public ItemPropertyLabelProvider getLabelProvider();

	public Control getControl();
}
