/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.swt.widgets.table;

import java.util.List;

public interface IEditElement<T> {
	public void editElement(List<T> input, int pos);
}
