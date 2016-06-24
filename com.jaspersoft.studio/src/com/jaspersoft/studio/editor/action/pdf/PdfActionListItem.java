/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;

import org.eclipse.ui.IWorkbenchPart;

public class PdfActionListItem extends PdfActionList {
	
	/**
	 * The id of the action when it is instanced as default
	 */
	public static final String ID_FULL = "net.sf.jasperreports.listitem.generate.pdf.tags.full"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as enabled
	 */	
	public static final String ID_START = "net.sf.jasperreports.listitem.generate.pdf.tags.start"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as disabled
	 */
	public static final String ID_END = "net.sf.jasperreports.listitem.generate.pdf.tags.end"; //$NON-NLS-1$
	
	/**
	 * The id of the action when it is instanced as disabled
	 */
	public static final String ID_NONE = "net.sf.jasperreports.listitem.generate.pdf.tags.none"; //$NON-NLS-1$
	
	public final static String JR_PROPERTY = "net.sf.jasperreports.export.pdf.tag.li";
	
	public PdfActionListItem(IWorkbenchPart part, TYPE status) {
		super(part, status);
	}
	
	@Override
	protected String getProperty() {
		return JR_PROPERTY;
	}
	
	protected String getIdNone(){
		return ID_NONE;
	}
	
	protected String getIdFull(){
		return ID_FULL;
	}
	
	protected String getIdEnd(){
		return ID_END;
	}
	
	protected String getIdStart(){
		return ID_START;
	}
}
