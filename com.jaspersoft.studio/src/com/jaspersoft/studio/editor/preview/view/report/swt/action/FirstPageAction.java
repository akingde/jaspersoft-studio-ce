/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.report.swt.action;

import net.sf.jasperreports.eclipse.viewer.IReportViewer;
import net.sf.jasperreports.eclipse.viewer.action.AReportAction;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;

public class FirstPageAction extends AReportAction {

	public FirstPageAction(IReportViewer rviewer) {
		super(rviewer);
		setText(Messages.FirstPageAction_actionName); 
		setToolTipText(Messages.FirstPageAction_actionTooltip); 
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/nav/first.gif"));//$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/nav/firstd.gif"));//$NON-NLS-1$
	}

	public boolean isActionEnabled() {
		return rviewer.canGotoFirstPage();
	}

	@Override
	public void run() {
		rviewer.gotoFirstPage();
	}
}
