/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.chainable;

/**
 * Abstract decorator painter, define the methods to paint a decorator into a specific
 * edge of the element
 */
public abstract class AbstractPainter implements IDecoratorPainter {
	
	/**
	 * Enumeration that define the possible location of the decorator on the figure
	 * 
	 * @author Orlandin Marco
	 *
	 */
	public static enum Location{TopLeft, TopRight, BottomLeft, BottomRight};
	
	/**
	 * Location of the decorator on the figure
	 */
	private Location loc;
	
	/**
	 * Build the class 
	 * 
	 * @param loc Location of the decorator on the figure
	 */
	public AbstractPainter(Location loc){
		this.loc = loc;
	}
	
	/**
	 * Return the location for the decorator
	 * @return a location
	 */
	public Location getLocation(){
		return loc;
	}
	
}
