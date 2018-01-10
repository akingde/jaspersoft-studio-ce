/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.framework.ui;

import com.jaspersoft.studio.widgets.framework.WItemProperty;
import com.jaspersoft.studio.widgets.framework.ui.dialog.ItemPropertyElementDialog;

/**
 * Interface used by an {@link ItemPropertyDescription} to provide a custom dialog
 * when the advanced button is pressed
 * 
 * @author Orlandin Marco
 *
 */
public interface IDialogProvider {

	/**
	 * Return the advanced dialog used to edit the property
	 * 
	 * @param wItemProp the {@link WItemProperty} where the button was pressed, it is not null
	 * @return a not null {@link ItemPropertyElementDialog}
	 */
	public ItemPropertyElementDialog getDialog(WItemProperty wItemProp);
	
}
