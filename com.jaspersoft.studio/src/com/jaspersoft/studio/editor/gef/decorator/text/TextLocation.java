package com.jaspersoft.studio.editor.gef.decorator.text;

import java.text.AttributedCharacterIterator.Attribute;
import java.text.AttributedString;

/**
 * Define a text that need to be painted on the figure
 * @author Orlandin Marco
 *
 */
public class TextLocation {
	
	/**
	 * Enumaration that define the possible location of the text on the figure
	 * @author Orlandin Marco
	 *
	 */
	public static enum Location{TopLeft, TopRight, BottomLeft, BottomRight};
	
	/**
	 * Location of the text on the figure
	 */
	private Location loc;
	
	/**
	 * The string to paint, it can have various attributes
	 */
	private AttributedString value;
	
	/**
	 * The string to paint, without attributes
	 */
	private String stringValue;
	
	/**
	 * 
	 * @param loc location of the string
	 * @param value value of the string to print
	 */
	public TextLocation(Location loc, String value){
		this.loc = loc;
		this.value = new AttributedString(value);
		this.stringValue = value;
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
	 * Return the location for the string
	 * @return a location
	 */
	public Location getLocation(){
		return loc;
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
}
