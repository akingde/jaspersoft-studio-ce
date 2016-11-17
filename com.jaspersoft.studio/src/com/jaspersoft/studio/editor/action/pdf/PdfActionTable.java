/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;


import org.eclipse.ui.IWorkbenchPart;

public class PdfActionTable extends PdfActionAbstract {
	
	/** Id of the actions */
	public static final String ID_Table_Full = "PdfAction_Table_Full"; //$NON-NLS-1$
	public static final String ID_Table_Start = "PdfAction_Table_Start"; //$NON-NLS-1$
	public static final String ID_Table_End = "PdfAction_Table_End"; //$NON-NLS-1$
	public static final String ID_Table_None = "PdfAction_Table_None"; //$NON-NLS-1$
	
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionTable(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Table_Full, ID_Table_Start, ID_Table_End, ID_Table_None);
	}

	/**
	 * method to return the property name 
	 * @return Property for which one the value must be changed
	 */
	protected String GetPropertyName(){
		return "net.sf.jasperreports.export.pdf.tag.table";
	}

}
