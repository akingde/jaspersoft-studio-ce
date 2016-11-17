/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

/**
 * Dialog proposed when an image needs to be selected.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ImageSelectionDialog extends FilePreviewSelectionDialog {

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public ImageSelectionDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * @return the title for the dialog
	 */
	protected String getDialogTitle() {
		return Messages.ImageSelectionDialog_0;
	}

	/**
	 * Returns an array of strings containing the title for the modes section, plus the title of every mode.
	 * <p>
	 * 
	 * Default implementation would return 6 strings, including 1 title and the following 5 modes:
	 * <ol>
	 * <li>workspace resource;</li>
	 * <li>absolute path in filesystem;</li>
	 * <li>URL;</li>
	 * <li>no image;</li>
	 * <li>custom expression</li>
	 * </ol>
	 * 
	 * @return the title and labels for the group of modes
	 */
	protected String[] getImageModesAndHeaderTitles() {
		return new String[] { Messages.ImageSelectionDialog_1, Messages.ImageSelectionDialog_2,
				Messages.ImageSelectionDialog_3,
				Messages.ImageSelectionDialog_4,
				Messages.ImageSelectionDialog_5,
				Messages.ImageSelectionDialog_6 };
	}

	@Override
	protected String getFileExtension() {
		return "*.png"; //$NON-NLS-1$
	}
	
	@Override
	protected String[] getFileExtensionsNames() {
		return new String[] {"All Images", "PNG", "JPEG", "GIF", "SVG", "All Files"};
	}

	@Override
	protected String[] getFileExtensions() {
		return new String[] {"*.png;*.jpeg;*.jpg;*.gif;*.svg", "*.png", "*.jpeg; *.jpg", "*.gif", ".svg", "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
	}
}
