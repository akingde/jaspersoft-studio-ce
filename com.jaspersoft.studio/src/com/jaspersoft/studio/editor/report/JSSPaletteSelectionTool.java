/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.report;

import org.eclipse.gef.EditDomain;
import org.eclipse.gef.tools.SelectionTool;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;

/**
 * A palette selection tool that set the default tool when during
 * an operation the esc key is pressed
 * 
 * @author Orlandin Marco
 *
 */
public class JSSPaletteSelectionTool extends SelectionTool {

	/**
	 * The domain where the operation must be aborted
	 */
	private EditDomain operationDomain;
	
	/**
	 * Crate the tool
	 * 
	 * @param operationDomain a not null edit domain
	 */
	public JSSPaletteSelectionTool(EditDomain operationDomain){
		super();
		this.operationDomain = operationDomain;
	}

	private boolean handleAbort(KeyEvent e) {
		if (e.keyCode == SWT.ESC) {
			return (operationDomain.getDefaultTool() != null);
		}
		return false;
	}

	@Override
	protected boolean handleKeyDown(KeyEvent e) {
		if (handleAbort(e)) {
			loadDefaultTool();
			return true;
		}
		return super.handleKeyDown(e);
	}

	private void loadDefaultTool() {
		operationDomain.loadDefaultTool();
	}
	
}
