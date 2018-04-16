/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.spreadsheet;

import org.eclipse.gef.GraphicalViewer;

import com.jaspersoft.studio.editor.action.snap.ACheckResourcePrefAction;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class ShowSpreadsheetTagsAction extends ACheckResourcePrefAction {
	
	public static final String ID = "com.jaspersoft.studio.editor.gef.decorator.spreadsheet.ShowSpreadsheetTagsAction"; //$NON-NLS-1$

	/**
	 * Constructor
	 * 
	 * @param diagramViewer
	 *          the GraphicalViewer whose grid enablement and visibility properties are to be toggled
	 */
	public ShowSpreadsheetTagsAction(GraphicalViewer diagramViewer, JasperReportsConfiguration jrConfig) {
		super(Messages.SpreadSheetElementDecorator_actionTitle, jrConfig);
		setToolTipText(Messages.SpreadSheetElementDecorator_actionTitle);
		setId(ID);
	}

	@Override
	protected String getProperty() {
		return ID;
	}

}
