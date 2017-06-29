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
