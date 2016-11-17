/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.text;

import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.ui.IWorkbenchPart;

public class StrikethroughAction extends ABooleanPropertyAction {
	public static String ID = "com.jaspersoft.studio.editor.action.text.strike";

	public StrikethroughAction(IWorkbenchPart part) {
		super(part);
		setId(ID);
	}

	@Override
	protected Object getPropertyName() {
		return JRDesignStyle.PROPERTY_STRIKE_THROUGH;
	}
}
