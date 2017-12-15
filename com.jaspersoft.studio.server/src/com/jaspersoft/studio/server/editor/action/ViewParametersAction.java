/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.editor.action;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.actions.ASwitchAction;
import com.jaspersoft.studio.model.parameter.MParameter;
import com.jaspersoft.studio.server.editor.ReportRunControler;

public class ViewParametersAction extends ASwitchAction {

	public ViewParametersAction(MultiPageContainer container) {
		super(container, ReportRunControler.FORM_PARAMETERS);
		setImageDescriptor(MParameter.getIconDescriptor().getIcon16());
		setToolTipText(MParameter.getIconDescriptor().getToolTip());
	}
}
