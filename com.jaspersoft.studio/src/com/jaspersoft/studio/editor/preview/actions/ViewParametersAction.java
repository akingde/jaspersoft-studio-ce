/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.actions;

import com.jaspersoft.studio.editor.preview.MultiPageContainer;
import com.jaspersoft.studio.editor.preview.view.control.ReportController;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.parameter.MParameter;

public class ViewParametersAction extends ASwitchAction {

	public ViewParametersAction(MultiPageContainer container) {
		super(container, ReportController.FORM_PARAMETERS);
		setImageDescriptor(MParameter.getIconDescriptor().getIcon16());
		setToolTipText(Messages.ShowParametersAction_show_input_parameters);
	}
}
