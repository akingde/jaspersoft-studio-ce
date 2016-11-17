/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action;

import org.eclipse.gef.ui.actions.SelectionAction;
import org.eclipse.ui.IWorkbenchPart;

public abstract class SetWorkbenchAction extends SelectionAction {

	public SetWorkbenchAction(IWorkbenchPart part) {
		super(part);
	}
	
	@Override
	public void setWorkbenchPart(IWorkbenchPart part) {
		super.setWorkbenchPart(part);
	}
	
	public SetWorkbenchAction(IWorkbenchPart part, int style) {
		super(part, style);
	}

}
