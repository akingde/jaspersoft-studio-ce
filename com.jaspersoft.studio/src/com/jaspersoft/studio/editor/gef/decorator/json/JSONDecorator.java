/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.json;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import com.jaspersoft.studio.editor.action.json.JSONPathDataAction;
import com.jaspersoft.studio.editor.gef.decorator.chainable.AbstractPainter;
import com.jaspersoft.studio.editor.gef.decorator.chainable.IDecoratorInterface;
import com.jaspersoft.studio.editor.gef.decorator.chainable.AbstractPainter.Location;
import com.jaspersoft.studio.editor.gef.decorator.text.TextLocation;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

import net.sf.jasperreports.engine.JRPropertiesMap;

/**
 * Decorator for the JSON action, it also provide an interface to became a text contributor
 * 
 * @author Veaceslav Chicu
 * 
 */
public class JSONDecorator implements IDecoratorInterface {

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
	public ArrayList<AbstractPainter> getDecoratorPainter(ComponentFigure fig) {
		JRPropertiesMap mapProperties = fig.getJrElement().getPropertiesMap();
		ArrayList<AbstractPainter> result = new ArrayList<AbstractPainter>();
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
			result.add(new TextLocation(Location.BottomRight, endString, JSS_TEXT_FONT, JSS_TEXT_COLOR));
		}

		return result;
	}
}
