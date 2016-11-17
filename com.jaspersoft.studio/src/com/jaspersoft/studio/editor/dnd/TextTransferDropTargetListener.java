/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.Request;
import org.eclipse.gef.dnd.AbstractTransferDropTargetListener;
import org.eclipse.swt.dnd.Transfer;
/*
 * The listener interface for receiving textTransferDropTarget events. The class that is interested in processing a
 * textTransferDropTarget event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addTextTransferDropTargetListener<code> method. When
 * the textTransferDropTarget event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see TextTransferDropTargetEvent
 */
public class TextTransferDropTargetListener extends AbstractTransferDropTargetListener {

	/**
	 * Instantiates a new text transfer drop target listener.
	 * 
	 * @param viewer
	 *          the viewer
	 * @param xfer
	 *          the xfer
	 */
	public TextTransferDropTargetListener(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#createTargetRequest()
	 */
	protected Request createTargetRequest() {
		return new NativeDropRequest();
	}

	/**
	 * Gets the native drop request.
	 * 
	 * @return the native drop request
	 */
	protected NativeDropRequest getNativeDropRequest() {
		return (NativeDropRequest) getTargetRequest();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.dnd.AbstractTransferDropTargetListener#updateTargetRequest()
	 */
	protected void updateTargetRequest() {
		getNativeDropRequest().setData(getCurrentEvent().data);
	}

}
