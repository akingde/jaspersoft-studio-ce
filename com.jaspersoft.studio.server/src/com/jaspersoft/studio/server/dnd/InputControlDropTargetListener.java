/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.dnd;

import org.eclipse.jface.util.TransferDropTargetListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.TreeItem;

import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.dnd.NodeTreeDropAdapter;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;

/**
 * A target drop listener that creates a generic file resource element when
 * something is dropped on the JRS repository tree.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class InputControlDropTargetListener extends NodeTreeDropAdapter implements TransferDropTargetListener {

	public InputControlDropTargetListener(TreeViewer treeViewer) {
		super(treeViewer);
	}

	@Override
	public boolean performDrop(Object data) {
		// insert in the right place

		return true;
	}

	@Override
	public boolean isEnabled(DropTargetEvent event) {
		if (event.item instanceof TreeItem) {
			TreeItem item = (TreeItem) event.item;
			Object d = item.getData();
			System.out.println(d);
			if (d instanceof MInputControl && ((MInputControl) d).getParent() instanceof MReportUnit) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Transfer getTransfer() {
		return NodeTransfer.getInstance();
	}

}
