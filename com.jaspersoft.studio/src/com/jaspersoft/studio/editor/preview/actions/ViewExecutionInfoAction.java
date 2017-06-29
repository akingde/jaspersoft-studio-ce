/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions;

import org.eclipse.jface.action.Action;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.PreviewJRPrint;
import com.jaspersoft.studio.messages.Messages;

public class ViewExecutionInfoAction extends Action {
	private PreviewJRPrint rcontainer;

	public ViewExecutionInfoAction(PreviewJRPrint rcontainer) {
		super();
		this.rcontainer = rcontainer;
		setImageDescriptor(
				JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/information-white.png")); //$NON-NLS-1$
		setToolTipText(Messages.ViewExecutionInfoAction_tooltip);
	}

	@Override
	public void run() {
		rcontainer.getConsole().showConsole();
	}
}
