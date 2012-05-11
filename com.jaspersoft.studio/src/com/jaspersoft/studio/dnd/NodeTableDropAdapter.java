/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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