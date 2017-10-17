/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.view.control.ReportController;
import com.jaspersoft.studio.model.parameter.MParameterSystem;

public class ViewReportParametersAction extends ASwitchAction {
	public ViewReportParametersAction(MultiPageContainer container) {
		super(container, ReportController.FORM_REPORT_PARAMETERS);
		setImageDescriptor(MParameterSystem.getIconDescriptor().getIcon16());
		setToolTipText(MParameterSystem.getIconDescriptor().getToolTip());
	}
}
