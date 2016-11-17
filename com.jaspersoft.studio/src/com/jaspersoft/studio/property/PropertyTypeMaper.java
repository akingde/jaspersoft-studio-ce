/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import com.jaspersoft.studio.properties.view.ITypeMapper;
/*
 * The Class PropertyTypeMaper.
 */
public class PropertyTypeMaper implements ITypeMapper {

	/* (non-Javadoc)
	 * @see org.eclipse.ui.views.properties.tabbed.ITypeMapper#mapType(java.lang.Object)
	 */
	public Class<?> mapType(Object object) {
		return object.getClass();
	}

}
