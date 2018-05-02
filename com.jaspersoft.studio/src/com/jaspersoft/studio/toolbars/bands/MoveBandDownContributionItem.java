/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars.bands;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.editor.action.MoveDetailDownAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;
import com.jaspersoft.studio.toolbars.CommonToolbarHandler;

/**
 * Toolitem to move the selected bands down
 * 
 * @author Orlandin Marco
 *
 */
public class MoveBandDownContributionItem extends CommonToolbarHandler{

	/**
	 * Button to move the band down
	 */
	private ToolItem moveBandDown;
	
	private MoveDetailDownAction moveBandDownAction = new MoveDetailDownAction(null);
	
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
		
		moveBandDown = new ToolItem(buttons, SWT.PUSH);
		moveBandDown.setImage(ResourceManager.getImage(moveBandDownAction.getImageDescriptor()));
		moveBandDown.setToolTipText(moveBandDownAction.getToolTipText());
		
		setEnablement();
		return buttons;
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		moveBandDown = new ToolItem(parent, SWT.PUSH);
		moveBandDown.setImage(ResourceManager.getImage(moveBandDownAction.getImageDescriptor()));
		moveBandDown.setToolTipText(moveBandDownAction.getToolTipText());
		moveBandDown.setData(WIDGET_DATA_KEY, moveBandDownAction);
		moveBandDown.addSelectionListener(pushButtonPressed);
		getToolItems().add(moveBandDown);
		
		setEnablement();
		
		return true;
	}
	
	private void setEnablement(){
		if (getWorkbenchPart() != null){
			if (moveBandDown != null && !moveBandDown.isDisposed()){
				moveBandDownAction.setWorkbenchPart(getWorkbenchPart());
				moveBandDown.setEnabled(moveBandDownAction.calculateEnabled());
			}
		}
	}

	@Override
	public void dispose() {
		super.dispose();
		if (moveBandDown != null){
			moveBandDown.dispose();
			moveBandDown = null;
		}
	}
}
