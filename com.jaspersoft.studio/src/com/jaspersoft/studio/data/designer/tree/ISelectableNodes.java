/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.designer.tree;

import java.util.List;

import com.jaspersoft.studio.model.ANode;

/**
 * This interface is supposed to be implemented by those clients that want
 * to give the ability to support the nodes selection.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 * @param <T> node class type
 */
public interface ISelectableNodes<T extends ANode> {

	/**
	 * Returns a list of selected nodes based on the
	 * specified input query string.
	 * 
	 * @param query the query string
	 * @return the list of selected nodes
	 */
	List<T> getSelectableNodes(String query);
}
