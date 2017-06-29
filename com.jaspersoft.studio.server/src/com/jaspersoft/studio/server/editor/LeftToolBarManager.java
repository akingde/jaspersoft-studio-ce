/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor;

import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.server.editor.action.ViewParametersAction;

public class LeftToolBarManager extends
		com.jaspersoft.studio.editor.preview.toolbar.LeftToolBarManager {

	public LeftToolBarManager(ReportUnitEditor container, Composite parent) {
		super(container, parent);
	}

	private ViewParametersAction vprmAction;

	protected void fillToolbar(IToolBarManager tbManager) {
		ReportUnitEditor pvcont = (ReportUnitEditor) container;
		if (vprmAction == null)
			vprmAction = new ViewParametersAction(pvcont.getLeftContainer());
		tbManager.add(vprmAction);

		addExporterSettings(tbManager, pvcont);

		addPin(container, tbManager);
	}

}
