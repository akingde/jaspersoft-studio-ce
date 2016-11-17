/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;


import org.eclipse.ui.IWorkbenchPart;


public class PdfActionTableHeader extends PdfActionAbstract {

	/** Id of the actions */
	public static final String ID_TableHeader_Full = "PdfAction_TableHeader_Full"; //$NON-NLS-1$
	public static final String ID_TableHeader_Start = "PdfAction_TableHeader_Start"; //$NON-NLS-1$
	public static final String ID_TableHeader_End = "PdfAction_TableHeader_End"; //$NON-NLS-1$
	public static final String ID_TableHeader_None = "PdfAction_TableHeader_None"; //$NON-NLS-1$
	
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionTableHeader(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableHeader_Full, ID_TableHeader_Start, ID_TableHeader_End, ID_TableHeader_None);
	}

	/**
	 * method to return the property name 
	 * @return Property for which one the value must be changed
	 */
	protected String GetPropertyName(){
		return "net.sf.jasperreports.export.pdf.tag.th";
	}

}
