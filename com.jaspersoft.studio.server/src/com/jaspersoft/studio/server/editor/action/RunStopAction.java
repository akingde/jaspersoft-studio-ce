/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.action;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.server.editor.ReportUnitEditor;

public class RunStopAction extends Action {
	public static final String ID = "PREVIEWJRSACTION"; //$NON-NLS-1$
	private ReportUnitEditor editor;

	public RunStopAction(ReportUnitEditor editor) {
		super();
		this.editor = editor;
		setId(ID);
		setText(Messages.RunStopAction_runreport);
		setDescription(Messages.RunStopAction_runreport_desc);
		setToolTipText(Messages.RunStopAction_runreport_desc);
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/start_task.gif")); //$NON-NLS-1$
		setDisabledImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/eclipse/start_task.gif")); //$NON-NLS-1$
	}

	@Override
	public boolean isEnabled() {
		return super.isEnabled() && editor.isNotRunning();
	}

	@Override
	public void run() {
		editor.runReport();
	}
}
