/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.text;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

/**
 * Interface that define a text contributor for the text decorator
 * @author Orlandin Marco
 *
 */
public interface TextDecoratorInterface {
	
	/**
	 * Used to get the elements that will be painted on the figure
	 * @param fig the figure of the element
	 * @return  an array of textual element that need to be painted on the figure
	 */
	public ArrayList<TextLocation> getText(ComponentFigure fig);
	
	/**
	 * Color used to paint the textual elements
	 * @return an awt color
	 */
	public Color getColor();
	
	/**
	 * Font used to render the text
	 * @return an awt font
	 */
	public Font getFont();
	
}
