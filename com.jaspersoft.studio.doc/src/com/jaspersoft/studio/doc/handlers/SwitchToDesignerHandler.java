/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.doc.handlers;

import org.eclipse.jface.action.Action;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.editor.JrxmlEditor;

/**
 * 
 * Handler to switch the report editor into the design tab
 * 
 * @author Orlandin Marco
 *
 */
public class SwitchToDesignerHandler extends Action {
	
		@Override
		public void run() {
			JrxmlEditor editor = (JrxmlEditor)PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActiveEditor();
			editor.setActiveEditor(editor.getEditor(0));
		}
}
