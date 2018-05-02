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

import com.jaspersoft.studio.editor.action.MoveGroupDownAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

/**
 * Toolitem to move the selected group bands down
 * 
 * @author Orlandin Marco
 *
 */
public class MoveGroupDownContributionItem extends CommonToolbarHandler{
	
	/**
	 * Button to move the the group down
	 */
	private ToolItem moveGroupDown;
	
	private MoveGroupDownAction moveGroupDownAction = new MoveGroupDownAction(null);
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			SetWorkbenchAction action = (SetWorkbenchAction)e.widget.getData(WIDGET_DATA_KEY);
			action.setWorkbenchPart(getWorkbenchPart());
			action.isEnabled();
			moveGroupDownAction.execute(getLastRawSelection());
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		moveGroupDown = new ToolItem(buttons, SWT.PUSH);
		moveGroupDown.setImage(ResourceManager.getImage(moveGroupDownAction.getImageDescriptor()));
		moveGroupDown.setToolTipText(moveGroupDownAction.getToolTipText());
		moveGroupDown.setData(WIDGET_DATA_KEY, moveGroupDownAction);
		moveGroupDown.addSelectionListener(pushButtonPressed);
		
		setEnablement();
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {	
		moveGroupDown = new ToolItem(parent, SWT.PUSH);
		moveGroupDown.setImage(ResourceManager.getImage(moveGroupDownAction.getImageDescriptor()));
		moveGroupDown.setToolTipText(moveGroupDownAction.getToolTipText());
		moveGroupDown.setData(WIDGET_DATA_KEY, moveGroupDownAction);
		moveGroupDown.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveGroupDown);
		
		setEnablement();
		
		return true;
	}
	
	private void setEnablement(){
		if (getWorkbenchPart() != null){
			if (moveGroupDown != null && !moveGroupDown.isDisposed()){
				moveGroupDownAction.setWorkbenchPart(getWorkbenchPart());
				moveGroupDown.setEnabled(moveGroupDownAction.calculateEnabled(getLastRawSelection()));
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
		if (moveGroupDown != null){
			moveGroupDown.dispose();
			moveGroupDown = null;
		}
	}
}
