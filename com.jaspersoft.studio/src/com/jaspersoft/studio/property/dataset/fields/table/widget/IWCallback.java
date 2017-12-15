/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/

package com.jaspersoft.studio.property.dataset.fields.table.widget;

import com.jaspersoft.studio.property.dataset.fields.table.TColumn;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

public interface IWCallback {
	public ItemPropertyDescription<?> create(TColumn c);
}
