/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.snap;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class SnapToGridAction extends ACheckResourcePrefAction {
	public static String ID = "ID_SNAP_TO_GRID"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public SnapToGridAction(JasperReportsConfiguration jrConfig) {
		super(Messages.common_snap_to_grid, jrConfig);
		setToolTipText(Messages.SnapToGridAction_snap_to_grid_tool_tip);
		setId(ID);
	}

	@Override
	protected String getProperty() {
		return RulersGridPreferencePage.P_PAGE_RULERGRID_SNAPTOGRID;
	}
}
