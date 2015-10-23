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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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
	protected boolean fillWithToolItems(ToolBar parent) {
		button = new ToolItem(parent, SWT.PUSH);
		button.setImage(ResourceManager.getImage(resizeColumnsAction.getImageDescriptor()));
		button.setToolTipText(resizeColumnsAction.getToolTipText());
		button.addSelectionListener(pushButtonPressed);
		getToolItems().add(button);
		setEnablement(false);
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
