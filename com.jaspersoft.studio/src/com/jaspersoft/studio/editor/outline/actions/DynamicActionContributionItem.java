/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.outline.actions;

import org.eclipse.jface.action.ActionContributionItem;

import com.jaspersoft.studio.editor.action.ACachedSelectionAction;

/**
 * {@link ActionContributionItem} used to the {@link ACachedSelectionAction} that allow
 * to define dynamic action to refresh the widget when the selection change. It ovverrides
 * the original isDirty and isDynamic to relay to the ones provided by the action
 */
public class DynamicActionContributionItem extends ActionContributionItem {

	public DynamicActionContributionItem(ACachedSelectionAction action) {
		super(action);
	}
	
	@Override
	public boolean isDirty() {
		return ((ACachedSelectionAction)getAction()).isDirty();
	}
	
	@Override
	public boolean isDynamic() {
		return ((ACachedSelectionAction)getAction()).isDynamic();
	}
}
