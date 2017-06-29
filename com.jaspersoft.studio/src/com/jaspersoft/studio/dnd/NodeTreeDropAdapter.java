/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.dnd;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;

import com.jaspersoft.studio.model.ANode;

/**
 * Supports dropping gadgets into a tree viewer.
 */
public class NodeTreeDropAdapter extends ViewerDropAdapter {
	public NodeTreeDropAdapter(TreeViewer viewer) {
		super(viewer);
	}

	/**
	 * Method declared on ViewerDropAdapter
	 */
	public boolean performDrop(Object data) {
		ANode target = (ANode) getCurrentTarget();
		if (target == null)
			target = (ANode) getViewer().getInput();
		ANode[] toDrop = (ANode[]) data;
		TreeViewer viewer = (TreeViewer) getViewer();
		// cannot drop a gadget onto itself or a child
		for (int i = 0; i < toDrop.length; i++)
			if (toDrop[i].equals(target) || target.hasParent(toDrop[i]))
				return false;
		for (int i = 0; i < toDrop.length; i++) {
			toDrop[i].setParent(target, -1);
			viewer.add(target, toDrop[i]);
			viewer.reveal(toDrop[i]);
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
