/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.properties.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.views.properties.IPropertySheetEntry;

import com.jaspersoft.studio.properties.messages.Messages;
import com.jaspersoft.studio.properties.view.IResettablePropertySheetEntry.RESET_TYPE;

/**
 * Custom default action used to reset a IResettablePropertySheetEntry, that allow to reset
 * the selected node and eventually also its children. This action when the selection are
 * set update its name to highlight if the action will reset a single node or its children
 */
public class CustomDefaultsAction extends Action {
	
	/**
	 * List of the {@link IResettablePropertySheetEntry} to reset
	 */
	private List<IResettablePropertySheetEntry> entries = new ArrayList<IResettablePropertySheetEntry>();
	
	/**
	 * Set the selected entries on the viewer. This will also set the action name and enablement
	 * based on the selection
	 * 
	 * @param currentSelection a not null {@link StructuredSelection}
	 */
	public void setEntries(ISelection currentSelection){
		entries.clear();
		StructuredSelection sSel = (StructuredSelection)currentSelection;
		if (sSel.isEmpty()){
			setText(Messages.AdvancedPropertySection_restoreDefaultName);
			setEnabled(false);
		} else {
			for(Object obj : sSel.toArray()){
				if (obj instanceof IResettablePropertySheetEntry){
					IResettablePropertySheetEntry entry = (IResettablePropertySheetEntry)obj;
					entries.add(entry);
					RESET_TYPE availableReset = entry.getAvailableReset();
					if (availableReset == RESET_TYPE.NO_RESET){
						setEnabled(false);
						setText(Messages.AdvancedPropertySection_restoreDefaultName);
					} else {
						setEnabled(true);
						if (availableReset == RESET_TYPE.RESET_ELEMENT){
							setText(Messages.AdvancedPropertySection_restoreDefaultName);
						} else {
							setText(Messages.CustomDefaultsAction_restoreDefaultChildrenName);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void run() {
		for(IPropertySheetEntry entry : entries){
			entry.resetPropertyValue();
		}
	}

}
