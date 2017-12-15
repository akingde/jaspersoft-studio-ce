/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.snap;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.DesignerPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class KeepUnitsInReportAction extends ACheckResourcePrefAction {

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *            the GraphicalViewer whose grid enablement and visibility
	 *            properties are to be toggled
	 */
	public KeepUnitsInReportAction(JasperReportsConfiguration jrConfig) {
		super(Messages.KeepUnitsInReportAction_0, jrConfig);
		setToolTipText(Messages.KeepUnitsInReportAction_1);
		setId(DesignerPreferencePage.JSS_UNIT_KEEP_UNIT);
	}

	@Override
	protected String getProperty() {
		return DesignerPreferencePage.JSS_UNIT_KEEP_UNIT;
	}

}
