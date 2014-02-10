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

import java.lang.reflect.Field;
import java.util.List;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.GraphicalEditPart;
import org.eclipse.gef.GraphicalViewer;
import org.eclipse.gef.Handle;
import org.eclipse.gef.handles.MoveHandle;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

import com.jaspersoft.studio.editor.gef.parts.editPolicy.ElementResizableEditPolicy;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * This selection tool should be used in order to enable the {@link MoveHandle move handle}
 * on the primary-selected {@link EditPart} simply using the keyboard arrows, without the
 * need to iterate through all the available {@link Handle handles} via the PERIOD keystroke. 
 *
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MovableSelectionTool extends SelectionTool {
	
	private boolean ignoreSnapSettings=false;
	private boolean snapToGrid=false;
	private JasperReportsConfiguration jrContext=null;
	
	public MovableSelectionTool(JasperReportsConfiguration jrContext) {
		super();
		// we need the JasperReports context in order
		// to retrieve the information about the snap-to-grid
		this.jrContext=jrContext;
	}

	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		if(acceptArrowKey(e)){
			if(isInState(STATE_INITIAL)){
				EditPartViewer viewer = getCurrentViewer();
				if (viewer instanceof GraphicalViewer) {
					EditPart focusEditPart = getCurrentViewer().getFocusEditPart();
					if(focusEditPart instanceof GraphicalEditPart) {						
						EditPolicy editPolicy = focusEditPart.getEditPolicy(EditPolicy.PRIMARY_DRAG_ROLE);
						if(editPolicy instanceof ElementResizableEditPolicy) {
							List handles = ((ElementResizableEditPolicy) editPolicy).getHandles();
							Handle handle = null;
							for(Object h : handles){
								if(h instanceof MoveHandle){
									handle = (Handle) h;
									break;
								}
							}
							if(handle!=null) {
								// Place the mouse cursor in the center of the figure
								Point newLocation = ((GraphicalEditPart) focusEditPart).getFigure().getBounds().getCenter();
                ((GraphicalEditPart) focusEditPart).getFigure().translateToAbsolute(newLocation);
                placeMouseInViewer(newLocation);       
                getCurrentInput().setMouseLocation(newLocation.x , newLocation.y);

                // Force the state in order to allow the DRAG
								setState(STATE_ACCESSIBLE_DRAG);
								setStartLocation(newLocation);
								setDragTracker(handle.getDragTracker());
								
								// Ignore the snap to grid settings
								ignoreSnapSettings=true;
								if(jrContext!=null){
									snapToGrid=jrContext.getPropertyBoolean(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGRID, true);
									jrContext.getPrefStore().setValue(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGRID, false);
								}
							}
						}
					}
				}					
			}
		}
		if (_acceptTraverseHandle(e)) {
			checkHandleIndex();
		}
		return super.handleKeyDown(e);
	}
	
	/*
	 * Duplicate of the Selection#acceptTraverseHandle private method.
	 */
	private boolean _acceptTraverseHandle(KeyEvent e) {
		return (e.character == '.' || e.character == '>')
				&& isInState(STATE_INITIAL | STATE_ACCESSIBLE_DRAG
						| STATE_ACCESSIBLE_DRAG_IN_PROGRESS)
				&& ((e.stateMask & (SWT.ALT | SWT.CONTROL)) == 0);
	}
	
	/**.
	 * Workaround in order to prevent possible {@link IndexOutOfBoundsException indexing-related exceptions}.
	 * The {@link SelectionTool#handleTraverseHandle} method works internally with the private
	 * <code>handleIndex</code> variable when iterating through the handles.
	 * 
	 * We reset it to 0 when it's wrongly being negative. Being negative seems a side-effect of forcing the
	 * {@link SelectionTool#STATE_ACCESSIBLE_DRAG} state in {@link #handleKeyDown(KeyEvent)} method.
	 */
	private void checkHandleIndex() {
		try {
			Field _handleIndex = SelectionTool.class.getDeclaredField("handleIndex");
			_handleIndex.setAccessible(true);
			Integer value = (Integer) _handleIndex.get(this);
			if(value<0){
				_handleIndex.setInt(this,0);
			}
		} catch (Exception e) {
			// Not too much we can do..
		}
	}
		
	@Override
	protected void setState(int state) {
		if(state == STATE_INITIAL && ignoreSnapSettings) {
			ignoreSnapSettings=false;
			// restore snap settings
			jrContext.getPrefStore().setValue(RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGRID, snapToGrid);
		}
		super.setState(state);
	};
}
