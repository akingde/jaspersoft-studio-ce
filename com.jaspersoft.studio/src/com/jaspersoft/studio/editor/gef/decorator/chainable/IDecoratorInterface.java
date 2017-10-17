/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.chainable;

import java.util.ArrayList;

import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;

/**
 * Interface that define a decorator contributor for {@link ChainableElementDecorator}
 * 
 * @author Orlandin Marco
 *
 */
public interface IDecoratorInterface {
	
	/**
	 * Used to get the elements that will be painted on the figure
	 * @param fig the figure of the element
	 * @return  an array of textual element that need to be painted on the figure
	 */
	public ArrayList<AbstractPainter> getDecoratorPainter(ComponentFigure fig);
	
}
