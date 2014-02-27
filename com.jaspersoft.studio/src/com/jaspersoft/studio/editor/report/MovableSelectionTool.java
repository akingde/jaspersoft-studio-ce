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
package com.jaspersoft.studio.editor.report;

import net.sf.jasperreports.engine.design.JRDesignElement;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Handle;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import com.jaspersoft.studio.editor.gef.commands.SetConstraintCommand;
import com.jaspersoft.studio.editor.report.AbstractVisualEditor.KeyPressedEventDomain;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtil;

/**
 * This selection tool should be used in order to enable the movements on
 * the primary-selected {@link EditPart} simply using the keyboard arrows,
 * without the need to iterate through all the available {@link Handle handles}
 * via the PERIOD keystroke.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class MovableSelectionTool extends SelectionTool {
	
	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		if (isInState(STATE_INITIAL) && UIUtil.isArrowKey(e.keyCode)) {
			EditPartViewer viewer = getCurrentViewer();
			if (viewer instanceof GraphicalViewer) {
				EditPart focusEditPart = getCurrentViewer().getFocusEditPart();
				if (focusEditPart instanceof GraphicalEditPart) {
					Object modelObj = focusEditPart.getModel();
					if(modelObj instanceof MGraphicElement) {
						MGraphicElement node = (MGraphicElement) modelObj;
						SetConstraintCommand moveUpCmd = new SetConstraintCommand();
						moveUpCmd.setContext(null, node, getNewLocation(e.keyCode, node));
						getDomain().getCommandStack().execute(moveUpCmd);
						return true;
					}
				}
			}
		}
		return super.handleKeyDown(e);
	}
	
	/*
	 * Computes the new position depending on the key pressed.
	 * Standard movement is 1px. If SHIFT key is also pressed 10px is the step.
	 */
	private Rectangle getNewLocation(int arrowKeyCode, MGraphicElement node) {
		int step=1;
		if(getDomain() instanceof KeyPressedEventDomain) {
			if(((KeyPressedEventDomain)getDomain()).isPressed(SWT.SHIFT)) {
				step = 10;
			}
		}
		Point location = ModelUtils.getY4Element(node);
		Integer width = (Integer) node.getPropertyValue(JRDesignElement.PROPERTY_WIDTH);
		Integer height = (Integer) node.getPropertyValue(JRDesignElement.PROPERTY_HEIGHT);
		switch (arrowKeyCode) {
		case SWT.ARROW_UP:
			return new Rectangle(location.x, location.y-step, width, height);
		case SWT.ARROW_DOWN:
			return new Rectangle(location.x, location.y+step, width, height);
		case SWT.ARROW_LEFT:
			return new Rectangle(location.x-step, location.y, width, height);
		case SWT.ARROW_RIGHT:
			return new Rectangle(location.x+step, location.y, width, height);
		default:
			throw new RuntimeException("Only arrow keys can be accepted!");
		}
	}
	
}
