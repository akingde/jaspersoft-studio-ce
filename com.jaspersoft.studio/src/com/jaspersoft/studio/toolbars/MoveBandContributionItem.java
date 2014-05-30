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
package com.jaspersoft.studio.toolbars;

import org.eclipse.jface.viewers.ISelection;
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
import com.jaspersoft.studio.model.band.MBandGroupFooter;
import com.jaspersoft.studio.model.band.MBandGroupHeader;

/**
 * Buttons for the toolbar to move band or groups
 * 
 * @author Orlandin Marco
 *
 */
public class MoveBandContributionItem extends CommonToolbarHandler{

	/**
	 * Button to move the band or the group up
	 */
	private ToolItem moveUp;
	
	/**
	 * Button to move the band or the group down
	 */
	private ToolItem moveDown;
	
	/**
	 * Button to check if the selected element is a band or a group
	 */
	private boolean isGroup = false;

	private MoveDetailDownAction moveDetailDownAction = new MoveDetailDownAction(null){

		@Override
		protected ISelection getSelection() {
			return getLastRawSelection();
		}
	};
	
	private  MoveDetailUpAction moveDetailUpAction = new MoveDetailUpAction(null){
		
		@Override
		protected ISelection getSelection() {
			return getLastRawSelection();
		}
	};
	
	private MoveGroupDownAction moveGroupDownAction = new MoveGroupDownAction(null){
		
		@Override
		protected ISelection getSelection() {
			return getLastRawSelection();
		}
	};
	
	private MoveGroupUpAction moveGroupUpAction = new MoveGroupUpAction(null){
		
		@Override
		protected ISelection getSelection() {
			return getLastRawSelection();
		}
	};
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			SetWorkbenchAction action = null;
			if (e.widget == moveDown){
				if (isGroup) action = moveGroupDownAction;
				else action = moveDetailDownAction;
			} else {
				if (isGroup) action = moveGroupUpAction;
				else action = moveDetailUpAction;
			}
			if (action != null) {
				action.setWorkbenchPart(getWorkbenchPart());
				action.run();
			}
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		super.createControl(parent);
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		moveDown = new ToolItem(buttons, SWT.PUSH);
		moveDown.setImage(ResourceManager.getImage(moveDetailDownAction.getImageDescriptor()));
		moveDown.setToolTipText(moveDetailDownAction.getToolTipText());
		moveDown.addSelectionListener(pushButtonPressed);
		
		moveUp = new ToolItem(buttons, SWT.PUSH);
		moveUp.setImage(ResourceManager.getImage(moveDetailUpAction.getImageDescriptor()));
		moveUp.setToolTipText(moveDetailUpAction.getToolTipText());
		moveUp.addSelectionListener(pushButtonPressed);
		
		setEnablement();
		return buttons;
	}
	
	private void setEnablement(){
		if (getWorkbenchPart() != null){
			if (moveDown != null && !moveDown.isDisposed()){
				SetWorkbenchAction action =  null;
				if (isGroup) action = moveGroupDownAction;
				else action = moveDetailDownAction;
				action.setWorkbenchPart(getWorkbenchPart());
				moveDown.setEnabled(action.isEnabled());
			}
			if (moveUp != null && !moveUp.isDisposed()){
				SetWorkbenchAction action =  null;
				if (isGroup) action = moveGroupUpAction;
				else action = moveDetailUpAction;
				action.setWorkbenchPart(getWorkbenchPart());
				moveUp.setEnabled(action.isEnabled());
			}
		}
	}

	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		if (getSelectionForType(MBandGroupHeader.class).size() == 1 || getSelectionForType(MBandGroupFooter.class).size() == 1){
			isGroup = true;
			setEnablement();
			return true;
		} else if (getSelectionForType(MBand.class).size() == 1){
			isGroup = false;
			setEnablement();
			return true;
		}
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (moveUp != null){
			moveUp.dispose();
			moveUp = null;
		}
		if (moveDown != null){
			moveDown.dispose();
			moveDown = null;
		}
		isGroup = false;
	}
}
