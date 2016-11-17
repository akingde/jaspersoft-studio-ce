/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.table.figure;

import com.jaspersoft.studio.components.table.model.MTable;
import com.jaspersoft.studio.editor.gef.figures.JRComponentFigure;

public class TableFigure extends JRComponentFigure {
	
	/**
	 * Instantiates a new text field figure.
	 */
	public TableFigure(MTable tableModel) {
		super(tableModel);
	}
	
	@Override
	protected boolean allowsFigureDrawCache() {
		return true;
	}
}
