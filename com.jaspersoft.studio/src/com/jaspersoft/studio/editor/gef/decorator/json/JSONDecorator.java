/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.json;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import net.sf.jasperreports.engine.JRPropertiesMap;

import com.jaspersoft.studio.editor.action.json.JSONPathDataAction;
import com.jaspersoft.studio.editor.gef.decorator.text.TextDecoratorInterface;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation.Location;

/**
 * Decorator for the JSON action, it also provide an interface to became a text contributor
 * 
 * @author Veaceslav Chicu
 * 
 */
public class JSONDecorator implements TextDecoratorInterface {

	/**
	 * Left upper corner image
	 */
	private static ImageIcon startImageAwt = null;

	/**
	 * right lower corner image
	 */
	private static ImageIcon endImageAwt = null;

	/**
	 * Font of the text
	 */
	private static Font JSS_TEXT_FONT = new Font("SansSerif", 0, 10); //$NON-NLS-1$

	/**
	 * Color of the text
	 */
	private static Color JSS_TEXT_COLOR = new Color(29, 198, 29);

	/**
	 * Pair of id-value used by the decorator
	 */
	String[] tags;

	/**
	 * Constructor, load the images if the weren't loaded before
	 */
	public JSONDecorator() {
		if (startImageAwt == null || endImageAwt == null) {
			startImageAwt = new javax.swing.ImageIcon(JSONDecorator.class.getResource("/icons/resources/corner1.png")); //$NON-NLS-1$
			endImageAwt = new javax.swing.ImageIcon(JSONDecorator.class.getResource("/icons/resources/corner2.png")); //$NON-NLS-1$
		}
		tags = new String[] { JSONPathDataAction.JSON_EXPORTER_PATH_PROPERTY, "isPath " }; //$NON-NLS-1$
	}

	/**
	 * Set the font of the displayed text
	 * 
	 * @param newFont
	 *          the new font
	 */
	public void setTextFont(Font newFont) {
		JSS_TEXT_FONT = newFont;
	}

	/**
	 * Set the color of the displayed text
	 * 
	 * @param newColor
	 *          the new color
	 */
	public void setTextColor(Color newColor) {
		JSS_TEXT_COLOR = newColor;
	}

	/**
	 * Return an array of text element that will be printed on the element
	 */
	@Override
	public ArrayList<TextLocation> getText(JRPropertiesMap mapProperties) {
		ArrayList<TextLocation> result = new ArrayList<TextLocation>();
		boolean hasValue = false;
		String endString = ""; //$NON-NLS-1$
		for (int i = 0; i < tags.length; i += 2) {
			String prop = tags[i];
			String label = tags[i + 1];
			hasValue = mapProperties.containsProperty(prop);
			if (prop.equals(JSONPathDataAction.JSON_EXPORTER_PATH_PROPERTY) && hasValue)
				endString += label.concat(mapProperties.getProperty(JSONPathDataAction.JSON_EXPORTER_PATH_PROPERTY)).concat(
						" "); //$NON-NLS-1$
		}
		endString = endString.trim();

		if (endString.length() > 0) {
			result.add(new TextLocation(Location.BottomRight, endString));
		}

		return result;
	}

	@Override
	public Color getColor() {
		return JSS_TEXT_COLOR;
	}

	@Override
	public Font getFont() {
		return JSS_TEXT_FONT;
	}

}
