/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;

/**
 * Simple redefinition of a selection action that permit to set the selection 
 * and have access to the calculateEnabled method
 * 
 * @author Orlandin Marco
 *
 */
public abstract class CustomSelectionAction extends ACachedSelectionAction {

	public CustomSelectionAction(IWorkbenchPart part) {
		super(part);
	}

	public CustomSelectionAction(IWorkbenchPart part, int style){
			super(part, style);
	}
	
	/**
	 * Set the selection, to force the element where the action will operate
	 */
	public void setSelection(ISelection selection){
		super.setSelection(selection);
	}
	
	/**
	 * Return if the action is enabled or not, using the calculatEnabled method.
	 * 
	 */
	public boolean canExecute(){
		return calculateEnabled();
	}
}
