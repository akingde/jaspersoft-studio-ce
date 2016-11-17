/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.components.table.model.column.action.ColumnsEqualWidthAction;

/**
 * Create the toolbar button to make the selected column to fill the table width
 * 
 * @author Orlandin Marco
 *
 */
public class ColumnsStretchToTableContributionItem extends CommonToolbarHandler {

	/**
	 * The button to press to activate the action
	 */
	protected ToolItem button;

	/**
	 * Action that will be executed to add the column, executed when the button is pressed
	 */
	protected ColumnsEqualWidthAction resizeColumnsAction = new ColumnsEqualWidthAction(null);
	
	
	/**
	 * Selection listener that create the right command when a button is pushed
	 */
	protected SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			resizeColumnsAction.setWorkbenchPart(getWorkbenchPart());
			resizeColumnsAction.run(getLastRawSelection());
		}
	};
	
	/**
	 * Set the state of the button depending on the actual selection
	 */
	protected void setEnablement(boolean value){
		if (getWorkbenchPart() != null){
			if (button != null && !button.isDisposed()){
				resizeColumnsAction.setWorkbenchPart(getWorkbenchPart());
				button.setEnabled(value);
			}
		}
	}
	
	
	@Override
	public void dispose() {
		super.dispose();
		if (button != null){
			button.dispose(); 
			button = null;
		}
	}
	
	@Override
	protected Control createControl(Composite parent) {
		
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		button = new ToolItem(buttons, SWT.PUSH);
		button.setImage(ResourceManager.getImage(resizeColumnsAction.getImageDescriptor()));
		button.setToolTipText(resizeColumnsAction.getToolTipText());
		button.addSelectionListener(pushButtonPressed);
		
		setEnablement(false);
		return buttons;
	}	

	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		button = new ToolItem(parent, SWT.PUSH);
		button.setImage(ResourceManager.getImage(resizeColumnsAction.getImageDescriptor()));
		button.setToolTipText(resizeColumnsAction.getToolTipText());
		button.addSelectionListener(pushButtonPressed);
		getToolItems().add(button);
		setEnablement(isVisible());
		return true;
	}
	
	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		if (resizeColumnsAction.canExecute(getLastRawSelection())){
			setEnablement(true);
			return true;
		}
		setEnablement(false);
		return false;
	}
}
