/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.bands;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.action.MoveGroupUpAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

/**
 * Toolitem to move the selected group bands up
 * 
 * @author Orlandin Marco
 *
 */
public class MoveGroupUpContributionItem extends CommonToolbarHandler{
	
	/**
	 * Button to move the group up
	 */
	private ToolItem moveGroupUp;
	
	private MoveGroupUpAction moveGroupUpAction = new MoveGroupUpAction(null);
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			SetWorkbenchAction action = (SetWorkbenchAction)e.widget.getData(WIDGET_DATA_KEY);
			action.setWorkbenchPart(getWorkbenchPart());
			action.isEnabled();
			moveGroupUpAction.execute(getLastRawSelection());
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		moveGroupUp = new ToolItem(buttons, SWT.PUSH);
		moveGroupUp.setImage(ResourceManager.getImage(moveGroupUpAction.getImageDescriptor()));
		moveGroupUp.setToolTipText(moveGroupUpAction.getToolTipText());
		moveGroupUp.setData(WIDGET_DATA_KEY, moveGroupUpAction);
		moveGroupUp.addSelectionListener(pushButtonPressed);
		
		setEnablement();
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		moveGroupUp = new ToolItem(parent, SWT.PUSH);
		moveGroupUp.setImage(ResourceManager.getImage(moveGroupUpAction.getImageDescriptor()));
		moveGroupUp.setToolTipText(moveGroupUpAction.getToolTipText());
		moveGroupUp.setData(WIDGET_DATA_KEY, moveGroupUpAction);
		moveGroupUp.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveGroupUp);
		
		setEnablement();
		
		return true;
	}
	
	private void setEnablement(){
		if (getWorkbenchPart() != null){
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
		if (moveGroupUp != null){
			moveGroupUp.dispose();
			moveGroupUp = null;
		}
	}
}
