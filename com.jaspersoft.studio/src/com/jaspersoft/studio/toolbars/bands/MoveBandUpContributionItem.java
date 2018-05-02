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

import com.jaspersoft.studio.editor.action.MoveDetailUpAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

/**
 * Toolitem to move the selected bands up
 * 
 * @author Orlandin Marco
 *
 */
public class MoveBandUpContributionItem extends CommonToolbarHandler{

	/**
	 * Button to move the band up
	 */
	private ToolItem moveBandUp;
	
	private MoveDetailUpAction moveBandUpAction = new MoveDetailUpAction(null);
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			SetWorkbenchAction action = (SetWorkbenchAction)e.widget.getData(WIDGET_DATA_KEY);
			action.setWorkbenchPart(getWorkbenchPart());
			action.isEnabled();
			action.run();
		}
	};
	
	@Override
	protected Control createControl(Composite parent) {
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		moveBandUp = new ToolItem(buttons, SWT.PUSH);
		moveBandUp.setImage(ResourceManager.getImage(moveBandUpAction.getImageDescriptor()));
		moveBandUp.setToolTipText(moveBandUpAction.getToolTipText());
		moveBandUp.setData(WIDGET_DATA_KEY, moveBandUpAction);
		moveBandUp.addSelectionListener(pushButtonPressed);
		
		setEnablement();
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		
		moveBandUp = new ToolItem(parent, SWT.PUSH);
		moveBandUp.setImage(ResourceManager.getImage(moveBandUpAction.getImageDescriptor()));
		moveBandUp.setToolTipText(moveBandUpAction.getToolTipText());
		moveBandUp.setData(WIDGET_DATA_KEY, moveBandUpAction);
		moveBandUp.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveBandUp);
		
		setEnablement();
		
		return true;
	}
	
	private void setEnablement(){
		if (getWorkbenchPart() != null){
			if (moveBandUp != null && !moveBandUp.isDisposed()){
				moveBandUpAction.setWorkbenchPart(getWorkbenchPart());
				moveBandUp.setEnabled(moveBandUpAction.calculateEnabled());
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
	}
}
