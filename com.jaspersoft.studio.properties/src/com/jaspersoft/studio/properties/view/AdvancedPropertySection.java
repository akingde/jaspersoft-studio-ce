/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import java.util.List;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.PropertySheetPage;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.properties.Activator;
import com.jaspersoft.studio.properties.messages.Messages;
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
	 * Create the advanced property page, also it set a custom default action to provide a more sofisticated 
	 * behavior that allow to reset also the children
	 * 
	 * @see org.eclipse.ui.views.properties.tabbed.ISection#createControls(org.eclipse.swt.widgets.Composite,
	 *      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
	 */
	public void createControls(Composite parent, final TabbedPropertySheetPage atabbedPropertySheetPage) {
		super.createControls(parent, atabbedPropertySheetPage);
		page = new PropertySheetPage(){
			
			private CustomDefaultsAction customResetAction;
			
			@Override
			public void createControl(Composite parent) {
				super.createControl(parent);
				//create the custom default action
				customResetAction = new CustomDefaultsAction();
				customResetAction.setText(Messages.AdvancedPropertySection_restoreDefaultName);
				customResetAction.setToolTipText(Messages.AdvancedPropertySection_restoreDefaultTooltip);
				customResetAction.setImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, "/images/defaults_ps.png")); //$NON-NLS-1$
				customResetAction.setDisabledImageDescriptor(ResourceManager.getPluginImageDescriptor(Activator.PLUGIN_ID, "/images/defaults_ps_disabled.png")); //$NON-NLS-1$
		        //create a custom contribution item that is always dirty, this will force the name of the 
				//label to be updated on the contextual menu everytime it is opened
				ActionContributionItem item = new ActionContributionItem(customResetAction){
		        	@Override
		        	public boolean isDirty() {
		        		return true;
		        	}
		        };
		        MenuManager menuMgr = new MenuManager("#PopupMenu"); //$NON-NLS-1$;
		        menuMgr.add(item);
		        final Menu menu = menuMgr.createContextMenu(getControl());
		        getControl().setMenu(menu);

			}
				
		    /**
		     * Handles a selection change in the entry tree. Set the new selection on 
		     * the default action
		     *
		     * @param selection the new selection
		     */
		    public void handleEntrySelection(ISelection selection) {
		    	super.handleEntrySelection(selection);
		    	if (customResetAction != null) {
		    		customResetAction.setEntries(selection);
		    	}
		    }
			
		};
		page.createControl(parent);
		GridData treeData = new GridData(GridData.FILL_BOTH);
		page.getControl().setLayoutData(treeData);
	}

	/**
	 * @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#aboutToBeShown()
	 */
	public void aboutToBeShown() {
		super.aboutToBeShown();
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
