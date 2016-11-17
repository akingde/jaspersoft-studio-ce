/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import com.jaspersoft.studio.property.descriptor.NullEnum;

public interface IEnumDescriptors {
	public String[] getEnumItems();

	public NullEnum getType();
}
