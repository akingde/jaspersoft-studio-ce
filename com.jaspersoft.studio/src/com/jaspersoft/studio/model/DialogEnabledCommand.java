/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.model;

import com.jaspersoft.studio.model.image.command.CreateImageCommand;

/**
 * Provides to the commands the ability to open a dialog when needed:
 * usually before the command execution method is invoked. 
 * <p>
 * 
 * <b>Example scenario</b>: dialog to be shown when creating an image element
 * dragged from the palette. The {@link CreateImageCommand} implements
 * this interface in order to be executed (jr element creation) only
 * once the dialog has been closed successfully. If the user closes it
 * pressing "Cancel" button no command execution is invoked.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public interface DialogEnabledCommand {
	
	/**
	 * Opens the utility dialog associated to the enabled command.
	 * 
	 * @return the window return code (usually <code>OK</code> or <code>CANCEL</code>)
	 */
	int openDialog();
}
