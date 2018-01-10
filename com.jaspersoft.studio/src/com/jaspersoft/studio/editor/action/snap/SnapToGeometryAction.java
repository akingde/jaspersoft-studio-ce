/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.ui.actions.GEFActionConstants;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SnapToGeometryAction extends ACheckResourcePrefAction {

	public SnapToGeometryAction(JasperReportsConfiguration jrConfig) {
		super(Messages.common_snap_to_guides, jrConfig);
		setText(Messages.SnapToGeometryAction_label);
		setToolTipText(Messages.SnapToGeometryAction_toolTip);
		setId(GEFActionConstants.TOGGLE_SNAP_TO_GEOMETRY);
	}

	@Override
	protected String getProperty() {
		return RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGEOMETRY;
	}
}
