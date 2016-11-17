/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property;

import com.jaspersoft.studio.model.ANode;

/**
 * Interface implemented by a cell editor to support the refresh before the 
 * value is set inside it. 
 * 
 * @author Orlandin Marco
 *
 */
public interface IRefreshableCellEditor {

	/**
	 * Called when the cell editor must be refreshed
	 * 
	 * @param selectedModel the model of the selected node
	 */
	public void refresh(ANode selectedModel);
	
}
