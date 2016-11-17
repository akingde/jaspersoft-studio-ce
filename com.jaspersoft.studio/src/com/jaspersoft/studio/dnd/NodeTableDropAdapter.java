/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.dnd;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

import com.jaspersoft.studio.model.ANode;

/**
 * Supports dropping gadgets into a table viewer.
 */
public class NodeTableDropAdapter extends ViewerDropAdapter {
	public NodeTableDropAdapter(TableViewer viewer) {
		super(viewer);
	}

	/**
	 * Method declared on ViewerDropAdapter
	 */
	public boolean performDrop(Object data) {
		// all gadgets in a table are children of the root
		ANode parent = (ANode) getViewer().getInput();
		ANode[] toDrop = (ANode[]) data;
		for (int i = 0; i < toDrop.length; i++) {
			// get the flat list of all gadgets in this tree
			ANode[] flatList = toDrop[i].flatten();
			for (int j = 0; j < flatList.length; j++) {
				flatList[j].setParent(parent, -1);
			}
			((TableViewer) getViewer()).add(flatList);
		}
		return true;
	}

	/**
	 * Method declared on ViewerDropAdapter
	 */
	public boolean validateDrop(Object target, int op, TransferData type) {
		return NodeTransfer.getInstance().isSupportedType(type);
	}
}
