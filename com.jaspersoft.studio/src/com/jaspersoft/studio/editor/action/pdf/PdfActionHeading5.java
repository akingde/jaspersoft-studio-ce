/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.action.pdf;


import org.eclipse.ui.IWorkbenchPart;


public class PdfActionHeading5 extends PdfActionAbstract {
	
	/** Id of the actions */
	public static final String ID_Heading5_Full = "PdfAction_Heading5_Full"; //$NON-NLS-1$
	public static final String ID_Heading5_Start = "PdfAction_Heading5_Start"; //$NON-NLS-1$
	public static final String ID_Heading5_End = "PdfAction_Heading5_End"; //$NON-NLS-1$
	public static final String ID_Heading5_None = "PdfAction_Heading5_None"; //$NON-NLS-1$
	
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionHeading5(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_Heading5_Full, ID_Heading5_Start, ID_Heading5_End, ID_Heading5_None);
	}

	/**
	 * method to return the property name 
	 * @return Property for which one the value must be changed
	 */
	protected String GetPropertyName(){
		return "net.sf.jasperreports.export.pdf.tag.h5";
	}

}
