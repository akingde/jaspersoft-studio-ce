/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.xls;

import org.eclipse.gef.GraphicalViewer;

import com.jaspersoft.studio.editor.action.snap.ACheckResourcePrefAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * Tag in the viewer to display or not the XLS break decorator
 * 
 * @author Orlandin Marco
 * 
 */
public class ShowXLSBreakAction extends ACheckResourcePrefAction {
	
	public static final String ID = "com.jaspersoft.studio.editor.gef.decorator.xls.ShowXLSBreakAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ShowXLSBreakAction(GraphicalViewer diagramViewer, JasperReportsConfiguration jrConfig) {
		super(Messages.ShowXLSBreakAction_name, jrConfig);
		setToolTipText(Messages.ShowXLSBreakAction_tooltip);
		setId(ID);
	}

	@Override
	protected String getProperty() {
		return ID;
	}
}
