/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.dnd;

import org.eclipse.jface.util.TransferDragSourceListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;

import com.jaspersoft.studio.dnd.NodeDragListener;
import com.jaspersoft.studio.dnd.NodeTransfer;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.IInputControlsContainer;
import com.jaspersoft.studio.server.model.MInputControl;
import com.jaspersoft.studio.server.model.MReportUnit;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.protocol.Feature;

/**
 * 
 * Drag listener for the report unit type, it generate and event serializing an
 * list of string with some informations of the dragged unit (its uri and the
 * name of its Resources descriptor).
 * 
 * @author Orlandin Marco
 * 
 */
public class InputControlDragSourceListener extends NodeDragListener implements TransferDragSourceListener {

	public InputControlDragSourceListener(TreeViewer viewer) {
		super(viewer);
	}

	/**
	 * Valid only if it is selected a a MReportUnit
	 */
	@Override
	public void dragStart(DragSourceEvent event) {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		Object fe = selection.getFirstElement();
		event.doit = !viewer.getSelection().isEmpty() && (fe instanceof MInputControl && isDragable(((MInputControl) fe).getParent()));
	}

	public static boolean isDragable(ANode parent) {
		if (parent instanceof MReportUnit)
			return true;
		if (parent instanceof AMResource) {
			AMResource res = (AMResource) parent;
			if (res.isSupported(Feature.INPUTCONTROLS_ORDERING) && (res instanceof IInputControlsContainer))
				return true;
		}
		return false;
	}

	@Override
	public Transfer getTransfer() {
		return NodeTransfer.getInstance();
	}

}
