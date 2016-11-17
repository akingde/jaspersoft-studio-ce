/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.editor.preview.toolbar.ATopToolBarManager;
import com.jaspersoft.studio.server.editor.action.RunStopAction;

public class PreviewTopToolBarManager extends ATopToolBarManager {

	public PreviewTopToolBarManager(ReportUnitEditor container, Composite parent) {
		super(container, parent);
	}

	private RunStopAction vexecAction;

	protected void fillToolbar(IToolBarManager tbManager) {
		ReportUnitEditor pvcont = (ReportUnitEditor) container;

		if (vexecAction == null)
			vexecAction = new RunStopAction(pvcont);
		tbManager.add(vexecAction);

	}

}
