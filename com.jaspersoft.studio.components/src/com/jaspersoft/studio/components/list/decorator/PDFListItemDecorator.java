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
package com.jaspersoft.studio.components.list.decorator;

import java.util.ArrayList;

import net.sf.jasperreports.engine.JRPropertiesMap;

import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation.Location;

/**
 * Decorator for the PDF list item action
 * 
 * @author Orlandin Marco
 *
 */
public class PDFListItemDecorator extends PDFListDecorator {
	
	/**
	 * Return an array of text element that will be printed on the element
	 */
	@Override
	public ArrayList<TextLocation> getText(JRPropertiesMap mapProperties) {
		ArrayList<TextLocation> result = new ArrayList<TextLocation>();
		String endString = ""; //$NON-NLS-1$
		Object value = mapProperties.getProperty(PdfActionListItem.JR_PROPERTY);
		if (value != null) {
			if (value.toString().equalsIgnoreCase("full")){
				endString += "PDF Full";
				result.add(new TextLocation(Location.TopLeft, endString));
			} else if (value.toString().equalsIgnoreCase("end")){
				endString += "PDF End";
				result.add(new TextLocation(Location.BottomRight, endString));
			} else if (value.toString().equalsIgnoreCase("start")){
				endString += "PDF Start";
				result.add(new TextLocation(Location.TopLeft, endString));
			}
		} 
		return result;
	}

}
