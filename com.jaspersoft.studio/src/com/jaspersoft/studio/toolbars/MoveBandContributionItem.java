/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.action.MoveDetailDownAction;
import com.jaspersoft.studio.editor.action.MoveDetailUpAction;
import com.jaspersoft.studio.editor.action.MoveGroupDownAction;
import com.jaspersoft.studio.editor.action.MoveGroupUpAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;
import com.jaspersoft.studio.model.band.MBand;

/**
 * Buttons for the toolbar to move band or groups
 * 
 * @author Orlandin Marco
 *
 */
public class MoveBandContributionItem extends CommonToolbarHandler{

	/**
	 * Button to move the band up
	 */
	private ToolItem moveBandUp;
	
	/**
	 * Button to move the band down
	 */
	private ToolItem moveBandDown;
	
	/**
	 * Button to move the group up
	 */
	private ToolItem moveGroupUp;
	
	/**
	 * Button to move the the group down
	 */
	private ToolItem moveGroupDown;
	

	private MoveDetailDownAction moveBandDownAction = new MoveDetailDownAction(null);
	
	private MoveDetailUpAction moveBandUpAction = new MoveDetailUpAction(null);
	
	private MoveGroupDownAction moveGroupDownAction = new MoveGroupDownAction(null);
	
	private MoveGroupUpAction moveGroupUpAction = new MoveGroupUpAction(null);
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			SetWorkbenchAction action = (SetWorkbenchAction)e.widget.getData();
			action.setWorkbenchPart(getWorkbenchPart());
			action.isEnabled();
			if (action == moveBandDownAction || action == moveBandUpAction){
				action.run();
			} else {
				if (action == moveGroupDownAction) {
					moveGroupDownAction.execute(getLastRawSelection());
				} else {
					moveGroupUpAction.execute(getLastRawSelection());
				}
			}
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		super.createControl(parent);
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		moveBandDown = new ToolItem(buttons, SWT.PUSH);
		moveBandDown.setImage(ResourceManager.getImage(moveBandDownAction.getImageDescriptor()));
		moveBandDown.setToolTipText(moveBandDownAction.getToolTipText());
		moveBandDown.setData(moveBandDownAction);
		moveBandDown.addSelectionListener(pushButtonPressed);
		
		moveBandUp = new ToolItem(buttons, SWT.PUSH);
		moveBandUp.setImage(ResourceManager.getImage(moveBandUpAction.getImageDescriptor()));
		moveBandUp.setToolTipText(moveBandUpAction.getToolTipText());
		moveBandUp.setData(moveBandUpAction);
		moveBandUp.addSelectionListener(pushButtonPressed);
		
		moveGroupDown = new ToolItem(buttons, SWT.PUSH);
		moveGroupDown.setImage(ResourceManager.getImage(moveGroupDownAction.getImageDescriptor()));
		moveGroupDown.setToolTipText(moveGroupDownAction.getToolTipText());
		moveGroupDown.setData(moveGroupDownAction);
		moveGroupDown.addSelectionListener(pushButtonPressed);
		
		moveGroupUp = new ToolItem(buttons, SWT.PUSH);
		moveGroupUp.setImage(ResourceManager.getImage(moveGroupUpAction.getImageDescriptor()));
		moveGroupUp.setToolTipText(moveGroupUpAction.getToolTipText());
		moveGroupUp.setData(moveGroupUpAction);
		moveGroupUp.addSelectionListener(pushButtonPressed);
		
		setEnablement();
		return buttons;
	}
	
	private void setEnablement(){
		if (getWorkbenchPart() != null){
			if (moveBandDown != null && !moveBandDown.isDisposed()){
				moveBandDownAction.setWorkbenchPart(getWorkbenchPart());
				moveBandDown.setEnabled(moveBandDownAction.calculateEnabled());
			}
			if (moveGroupDown != null && !moveGroupDown.isDisposed()){
				moveGroupDownAction.setWorkbenchPart(getWorkbenchPart());
				moveGroupDown.setEnabled(moveGroupDownAction.calculateEnabled(getLastRawSelection()));
			}
			if (moveBandUp != null && !moveBandUp.isDisposed()){
				moveBandUpAction.setWorkbenchPart(getWorkbenchPart());
				moveBandUp.setEnabled(moveBandUpAction.calculateEnabled());
			}
			if (moveGroupUp != null && !moveGroupUp.isDisposed()){
				moveGroupUpAction.setWorkbenchPart(getWorkbenchPart());
				moveGroupUp.setEnabled(moveGroupUpAction.calculateEnabled(getLastRawSelection()));
			}
		}
	}

	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		List<Object> bands = getSelectionForType(MBand.class);
		if (!bands.isEmpty()){
			MBand first = (MBand)bands.get(0);
			for(Object rawBand : bands){
				if (!first.isSameBandType((MBand)rawBand)){
					return false;
				}
			}
			setEnablement();
			return true;
		}
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (moveBandUp != null){
			moveBandUp.dispose();
			moveBandUp = null;
		}
		if (moveBandDown != null){
			moveBandDown.dispose();
			moveBandDown = null;
		}
		if (moveGroupUp != null){
			moveGroupUp.dispose();
			moveGroupUp = null;
		}
		if (moveGroupDown != null){
			moveGroupDown.dispose();
			moveGroupDown = null;
		}
	}
}
