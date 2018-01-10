/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.widgets.map.messages.Messages;

/**
 * Utility methods for UI related operations.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class MapUIUtils {

	/**
	 * Creates a warning composite that alerts the user about possible issues
	 * when using the browser widget in Linux.
	 * 
	 * @param parent the parent composite
	 * @return the created warning composite
	 */
	public static Composite createLinuxWarningText(Composite parent) {
		Composite warningCmp = new Composite(parent,SWT.NONE);
		GridLayout cmpL = new GridLayout(2, false);
		warningCmp.setLayout(cmpL);
		Label warningIcon = new Label(warningCmp,SWT.NONE);
		warningIcon.setImage(JFaceResources.getImage(Dialog.DLG_IMG_MESSAGE_WARNING));
		warningIcon.setLayoutData(new GridData(SWT.FILL, SWT.TOP,false,false));
		Label warningText = new Label(warningCmp,SWT.WRAP);
		warningText.setText(Messages.MapUIUtils_MapLinuxWarningMsg);
		warningText.setToolTipText(Messages.MapUIUtils_MapLinuxWarningTooltip);
		warningText.setLayoutData(new GridData(SWT.FILL,SWT.FILL,true,false));
		return warningCmp;
	}

}
