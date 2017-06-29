/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model.image.command.dialog;

import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.jface.dialogs.ImageSelectionDialog;
import com.jaspersoft.studio.messages.Messages;

/**
 * Dialog proposed when the image component is to be created.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class ImageCreationDialog extends ImageSelectionDialog {

	public ImageCreationDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected String getDialogTitle() {
		return Messages.ImageCreationDialog_Title;
	}
	
	@Override
	protected String[] getImageModesAndHeaderTitles() {
		return new String[]{
				Messages.ImageCreationDialog_CreationModeGroupTitle,
				Messages.ImageCreationDialog_RadioBtnWSResource,
				Messages.ImageCreationDialog_RadioBtnFSResource,
				Messages.ImageCreationDialog_RadioBtnURLResource,
				Messages.ImageCreationDialog_RadioBtnNoImage,
				Messages.ImageCreationDialog_RadioBtnCustomExpr
		};
	}
}
