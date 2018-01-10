/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.toolbars;

import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.components.crosstab.model.MCrosstab;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.MRowGroups;
import com.jaspersoft.studio.components.crosstab.model.rowgroup.action.CreateRowGroupAction;
import com.jaspersoft.studio.editor.action.SetWorkbenchAction;
import com.jaspersoft.studio.model.MPage;

/**
 * Toolbar button to create a row group on the selected crosstabs. The action is
 * visible only on the main editor and only when a crosstab or a row groups
 * 
 * @author Orlandin Marco
 *
 */
public class CreateRowGroupContributionItem extends CommonToolbarHandler{
	
	/**
	 * The button to press to activate the action
	 */
	private ToolItem button;

	/**
	 * Action that will be executed to add the group, executed when the button is pressed
	 */
	private SetWorkbenchAction createColumnAction = new CreateRowGroupAction(null){

		@Override
		protected ISelection getSelection() {
			return getLastRawSelection();
		}
	};
	
	/**
	 * Listener for the button, when it pressed the action is runned 
	 */
	private SelectionAdapter pushButtonPressed = new SelectionAdapter() {
		
	
		public void widgetSelected(SelectionEvent e) {
			createColumnAction.setWorkbenchPart(getWorkbenchPart());
			createColumnAction.run();
		}
	};
	
	/**
	 * Set the state of the button depending on the actual selection
	 */
	private void setEnablement(){
		if (getWorkbenchPart() != null){
			if (button != null && !button.isDisposed()){
				createColumnAction.setLazyEnablementCalculation(true);
				createColumnAction.setWorkbenchPart(getWorkbenchPart());
				button.setEnabled(createColumnAction.isEnabled());
			}
		}
	}
	
	@Override
	protected boolean fillWithToolItems(ToolBar parent) {
		button = new ToolItem(parent, SWT.PUSH);
		button.setImage(ResourceManager.getImage(createColumnAction.getImageDescriptor()));
		button.setToolTipText(createColumnAction.getToolTipText());
		button.addSelectionListener(pushButtonPressed);
		getToolItems().add(button);
		setEnablement();
		return true;
	}

	@Override
	protected Control createControl(Composite parent) {
		
		ToolBar buttons = new ToolBar(parent, SWT.FLAT | SWT.WRAP);
		
		button = new ToolItem(buttons, SWT.PUSH);
		button.setImage(ResourceManager.getImage(createColumnAction.getImageDescriptor()));
		button.setToolTipText(createColumnAction.getToolTipText());
		button.addSelectionListener(pushButtonPressed);
		
		setEnablement();
		return buttons;
	}

	@Override
	public boolean isVisible() {
		if (!super.isVisible()) return false;
		
		List<Object> selection = getSelectionForType(MCrosstab.class);
		if (selection.size() == 1){
			MCrosstab crosstab = (MCrosstab)selection.get(0);
			if (crosstab.getParent() instanceof MPage){
				setEnablement();
				return true;
			} else return false;
		}
		selection = getSelectionForType(MRowGroups.class);
		if (selection.size() == 1){
			//don't need to check if it is in the subeditor since this nodes are selectable
			//only in the crosstab subeditor 
			setEnablement();
			return true;
		}
		return false;
	}
	
	@Override
	public void dispose() {
		super.dispose();
		if (button != null){
			button.dispose(); 
			button = null;
		}
	}
	
}
