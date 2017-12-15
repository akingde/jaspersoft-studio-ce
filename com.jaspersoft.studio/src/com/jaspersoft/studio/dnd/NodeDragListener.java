/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.dnd;

import java.util.Iterator;
import java.util.List;

import org.eclipse.gef.dnd.TemplateTransfer;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.ui.part.PluginTransfer;
import org.eclipse.ui.part.PluginTransferData;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;

/**
 * Supports dragging gadgets from a structured viewer.
 */
public class NodeDragListener extends DragSourceAdapter {
	protected StructuredViewer viewer;

	public NodeDragListener(StructuredViewer viewer) {
		this.viewer = viewer;
	}

	/**
	 * Method declared on DragSourceListener
	 */
	public void dragFinished(DragSourceEvent event) {
		if (!event.doit)
			return;
		// if the gadget was moved, remove it from the source viewer
		if (event.detail == DND.DROP_MOVE) {
			IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
			for (Iterator<?> it = selection.iterator(); it.hasNext();) {
				Object obj = it.next();
				if (obj instanceof ANode) {
					ANode n = (ANode) obj;
					if (n.isCut())
						((ANode) obj).setParent(null, -1);
				}
			}
			viewer.refresh();
		}
	}

	/**
	 * Method declared on DragSourceListener
	 */
	public void dragSetData(DragSourceEvent event) {
		IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
		List<?> list = selection.toList();
		if (NodeTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = list.toArray();
		} else if (PluginTransfer.getInstance().isSupportedType(event.dataType)) {
			ANode[] gadgets = list.toArray(new ANode[selection.size()]);
			byte[] data = NodeTransfer.getInstance().toByteArray(gadgets);
			event.data = new PluginTransferData(JaspersoftStudioPlugin.getUniqueIdentifier(), data);
		} else if (TemplateTransfer.getInstance().isSupportedType(event.dataType)) {
			event.data = list.toArray();
		} else
			event.data = list.toArray();
	}

	/**
	 * Method declared on DragSourceListener
	 */
	public void dragStart(DragSourceEvent event) {
		event.doit = !viewer.getSelection().isEmpty();
	}
}
