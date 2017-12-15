/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.text.AttributedCharacterIterator.Attribute;

import com.jaspersoft.studio.editor.gef.decorator.chainable.AbstractPainter;

import java.text.AttributedString;

/**
 * Define a text that need to be painted on the figure
 * @author Orlandin Marco
 *
 */
public class TextLocation extends AbstractPainter {	
	
	/**
	 * The string to paint, it can have various attributes
	 */
	private AttributedString value;
	
	/**
	 * The string to paint, without attributes
	 */
	private String stringValue;
	
	/**
	 * the font to paint the text
	 */
	private Font font;
	
	/**
	 * the color to paint the text
	 */
	private Color color;
	
	/**
	 * 
	 * @param loc location of the string
	 * @param value value of the string to print
	 */
	public TextLocation(Location loc, String value, Font font, Color color){
		super(loc);
		this.value = new AttributedString(value);
		this.stringValue = value;
		this.font = font;
		this.color = color;
	}
	
	/**
	 * Add an attribute to the string
	 * @param attribute an attribute from java.awt.font.TextAttribute
	 * @param value the value of the string
	 */
	public void addAttribute(Attribute attribute, Object value){
		this.value.addAttribute(attribute, value);
	}
	
	/**
	 * Return the string with the attributes
	 * @return 
	 */
	public AttributedString getValue(){
		return value;
	}
	
	/**
	 * Return the string without attributes
	 * @return
	 */
	public String getText(){
		return stringValue;
	}
	
	/**
	 * Return the string lenght
	 * @return
	 */
	public int getLenght(){
		return stringValue.length();
	}
	
	/**
	 * Return true if there is a string to print
	 * @return true if the string is not null and its lenght is greater than zero, 
	 * false otherwise
	 */
	public boolean hasValue(){
		return value != null && stringValue.length()>0;
	}

	@Override
	public void paint(Graphics2D g, int x, int y) {
		if (hasValue()) {
			Font oldFont = g.getFont();
			Color oldColor = g.getColor();
			if (font != null) {
				g.setFont(font);
			}
			if (color != null) {
				g.setColor(color);
			}
			g.drawString(getValue().getIterator(), x, y);
			g.setFont(oldFont);
			g.setColor(oldColor);
		} 
	}

	@Override
	public Point getElementSize(Graphics2D g) {
		if (hasValue()) {
			return new Point(g.getFontMetrics().stringWidth(getText())+(getLenght()), 7);
		}
		return new Point(0,0);
	}
}
