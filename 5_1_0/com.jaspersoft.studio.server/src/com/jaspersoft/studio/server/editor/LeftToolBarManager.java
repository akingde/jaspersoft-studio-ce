/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
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
