/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts.handles;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.handles.ResizeHandle;
import org.eclipse.swt.graphics.Color;
import org.eclipse.wb.swt.SWTResourceManager;

import com.jaspersoft.studio.editor.gef.parts.IPrefEditPart;
import com.jaspersoft.studio.editor.gef.parts.editPolicy.JSSCompoundResizeTracker;

/*
 * The Class BandResizeHandle2.
 */
public class CellResizeHandle2 extends ResizeHandle {

	/** The tracker. */
	CellResizeTracker tracker = null;
	private int cursorDirection = 0;

	/**
	 * Constructor for SectionResizeHandle.
	 * 
	 * @param owner
	 *          the owner
	 * @param direction
	 *          the direction
	 */
	public CellResizeHandle2(GraphicalEditPart owner, int direction) {
		super(owner, direction);
		setLocator(new CellResizeHandleLocator2(owner, direction));
		setPreferredSize(2, 2);
		cursorDirection = direction;
	}

	@Override
	protected Color getBorderColor() {
		if (getOwner() instanceof IPrefEditPart)
			return ((IPrefEditPart) getOwner()).getMarginColor();
		return SWTResourceManager.getColor(IPrefEditPart.DEFAULT_MARGINCOLOR);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.handles.SquareHandle#getFillColor()
	 */
	@Override
	protected Color getFillColor() {
		if (getOwner().getFigure().getBounds().height == 0)
			return ColorConstants.red;
		return getBorderColor();// super.getFillColor();
	}

	@Override
	protected DragTracker createDragTracker() {
		return new JSSCompoundResizeTracker(getOwner(), cursorDirection){
			
			@Override
			protected boolean handleDragInProgress() {
				if (!isInState(STATE_TERMINAL)){
					Command command = getCurrentCommand();
					if (command == null || !command.canExecute()){
						eraseSourceFeedback();
					}
				}
				return super.handleDragInProgress();
			}
		};
	}
}
