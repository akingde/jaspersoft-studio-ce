/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.view.control.ReportControler;
import com.jaspersoft.studio.messages.MessagesByKeys;

public class ViewBookmarksAction extends ASwitchAction {

	public ViewBookmarksAction(MultiPageContainer pContainer) {
		super(pContainer, ReportControler.FORM_BOOKMARKS);
		setImageDescriptor(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/bookmark.png"));
		setToolTipText(MessagesByKeys.getString(ReportControler.FORM_BOOKMARKS));
	}
}
