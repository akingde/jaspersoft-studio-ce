/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.action.pdf;


import org.eclipse.ui.IWorkbenchPart;

public class PdfActionTableDetail extends PdfActionAbstact {
	
	/** Id of the actions */
	public static final String ID_TableDetail_Full = "PdfAction_TableDetail_Full"; //$NON-NLS-1$
	public static final String ID_TableDetail_Start = "PdfAction_TableDetail_Start"; //$NON-NLS-1$
	public static final String ID_TableDetail_End = "PdfAction_TableDetail_End"; //$NON-NLS-1$
	public static final String ID_TableDetail_None = "PdfAction_TableDetail_None"; //$NON-NLS-1$
	
	
	/**
	 * Constructs a <code>CreateAction</code> using the specified part.
	 * 
	 * @param part
	 *          The part for this action
	 * @param action_position
	 * 					Identify The position of the label
	 */
	public PdfActionTableDetail(IWorkbenchPart part,Position action_position) {
		super(part, action_position, ID_TableDetail_Full, ID_TableDetail_Start, ID_TableDetail_End, ID_TableDetail_None);
	}

	/**
	 * method to return the property name 
	 * @return Property for which one the value must be changed
	 */
	protected String GetPropertyName(){
		return "net.sf.jasperreports.export.pdf.tag.td";
	}

}
