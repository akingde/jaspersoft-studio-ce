/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;


import org.eclipse.ui.IWorkbenchPart;

public class PdfActionTableRow extends PdfActionAbstract {
	
	/** Id of the actions */
	public static final String ID_TableRow_Full = "PdfAction_TableRow_Full"; //$NON-NLS-1$
	public static final String ID_TableRow_Start = "PdfAction_TableRow_Start"; //$NON-NLS-1$
	public static final String ID_TableRow_End = "PdfAction_TableRow_End"; //$NON-NLS-1$
	public static final String ID_TableRow_None = "PdfAction_TableRow_None"; //$NON-NLS-1$
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionTableRow(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableRow_Full, ID_TableRow_Start, ID_TableRow_End, ID_TableRow_None);
	}

	/**
	 * method to return the property name 
	 * @return Property for which one the value must be changed
	 */
	protected String GetPropertyName(){
		return "net.sf.jasperreports.export.pdf.tag.tr";
	}
	
}
