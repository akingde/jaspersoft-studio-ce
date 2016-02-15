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
package com.jaspersoft.studio.properties.view;

import java.util.List;

import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;

import com.jaspersoft.studio.properties.IEditablePropertySource;
import com.jaspersoft.studio.properties.view.validation.ValidationError;

/**
 * An advanced section that is intended to show the original table format
 * properties view provided by base Eclipse.
 * 
 * @author Anthony Hunter
 */
public class AdvancedPropertySection extends AbstractPropertySection {

	/**
	 * The Property Sheet Page.
	 */
	protected PropertySheetPage page;

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);
		page = new PropertySheetPage();
		page.createControl(parent);
		GridData treeData = new GridData(GridData.FILL_BOTH);
		page.getControl().setLayoutData(treeData);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeShown()
	 */
	public void aboutToBeShown() {
		super.aboutToBeShown();
		page.getControl().setEnabled(isEditEnabled());
		TabbedPropertySheetPage tp = getTabbedPropertySheetPage();
		if (tp != null && tp.getSite() != null) {
			IActionBars actionBars = tp.getSite().getActionBars();
			if (actionBars != null)
				actionBars.getToolBarManager().removeAll();
			page.makeContributions(actionBars.getMenuManager(), actionBars.getToolBarManager(),
					actionBars.getStatusLineManager());
			actionBars.updateActionBars();
		}
	}
	
	/**
	 * Check if the selected models are all editable
	 * 
	 * @return true if all the selected models are editable, false otherwise
	 */
	protected boolean isEditEnabled(){
		IStructuredSelection currentSelection = (IStructuredSelection)getSelection();
		for(Object selectedElement : currentSelection.toArray()){
			if (selectedElement instanceof EditPart){
				Object model = ((EditPart)selectedElement).getModel();
				if (model instanceof IEditablePropertySource){
					if (!((IEditablePropertySource)model).isEditable()){
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeHidden()
	 */
	public void aboutToBeHidden() {
		TabbedPropertySheetPage tp = getTabbedPropertySheetPage();
		if (tp != null && tp.getSite() != null) {
			IActionBars actionBars = tp.getSite().getActionBars();
			if (actionBars != null) {
				actionBars.getToolBarManager().removeAll();
				actionBars.updateActionBars();
			}
		}
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#setInput(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void setInput(IWorkbenchPart part, ISelection selection) {
		super.setInput(part, selection);
		if (page != null){
			page.selectionChanged(part, selection);
		}
	}

	@Override
	public void resetErrors() {
		page.refresh();
	}

	@Override
	public void showErrors(List<ValidationError> errors) {
		page.refresh();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#dispose()
	 */
	public void dispose() {
		super.dispose();

		if (page != null) {
			page.dispose();
			page = null;
		}

	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#refresh()
	 */
	public void refresh() {
		if (page != null)
			page.refresh();
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#shouldUseExtraSpace()
	 */
	public boolean shouldUseExtraSpace() {
		return true;
	}

}
