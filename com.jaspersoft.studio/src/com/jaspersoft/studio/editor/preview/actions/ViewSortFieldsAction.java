/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.view.control.ReportController;
import com.jaspersoft.studio.model.sortfield.MSortFields;

public class ViewSortFieldsAction extends ASwitchAction {
	public ViewSortFieldsAction(MultiPageContainer container) {
		super(container, ReportController.FORM_SORTING);
		setImageDescriptor(MSortFields.getIconDescriptor().getIcon16());
		setToolTipText(MSortFields.getIconDescriptor().getToolTip());
	}
}
