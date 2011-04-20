/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
package com.jaspersoft.studio.editor.dnd;

import org.eclipse.gef.EditPartViewer;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
/*/*
 * The listener interface for receiving fileTransferDragSource events. The class that is interested in processing a
 * fileTransferDragSource event implements this interface, and the object created with that class is registered with a
 * component using the component's <code>addFileTransferDragSourceListener<code> method. When
 * the fileTransferDragSource event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see FileTransferDragSourceEvent
 */
public class FileTransferDragSourceListener extends org.eclipse.gef.dnd.AbstractTransferDragSourceListener {

	/**
	 * Instantiates a new file transfer drag source listener.
	 * 
	 * @param viewer
	 *          the viewer
	 */
	public FileTransferDragSourceListener(EditPartViewer viewer) {
		super(viewer, TextTransfer.getInstance());
	}

	/**
	 * Instantiates a new file transfer drag source listener.
	 * 
	 * @param viewer
	 *          the viewer
	 * @param xfer
	 *          the xfer
	 */
	public FileTransferDragSourceListener(EditPartViewer viewer, Transfer xfer) {
		super(viewer, xfer);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.swt.dnd.DragSourceListener#dragSetData(org.eclipse.swt.dnd.DragSourceEvent)
	 */
	public void dragSetData(DragSourceEvent event) {
		event.data = "Some text"; //$NON-NLS-1$
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.dnd.AbstractTransferDragSourceListener#dragStart(org.eclipse.swt.dnd.DragSourceEvent)
	 */
	public void dragStart(DragSourceEvent event) {
		// if (getViewer().getSelectedEditParts().get(0) instanceof
		// LogicLabelEditPart)
		// return;
		event.doit = false;
	}

}
