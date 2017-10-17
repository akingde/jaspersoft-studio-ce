/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.snap;

import org.eclipse.gef.ui.actions.GEFActionConstants;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.preferences.RulersGridPreferencePage;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ShowGridAction extends ACheckResourcePrefAction {

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ShowGridAction(JasperReportsConfiguration jrConfig) {
		super(Messages.common_show_grid, jrConfig);
		setToolTipText(Messages.ShowGridAction_show_grid_tool_tip);
		setId(GEFActionConstants.TOGGLE_GRID_VISIBILITY);
	}

	@Override
	protected String getProperty() {
		return RulersGridPreferencePage.P_PAGE_RULERGRID_SHOWGRID;
	}

}
