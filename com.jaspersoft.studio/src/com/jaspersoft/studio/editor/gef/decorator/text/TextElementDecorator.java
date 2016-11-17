/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.text;

import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;

/**
 * An element decorator that instances one and only one TextDecorator, that will be 
 * used to paint strings on the figures
 * @author Orlandin Marco
 *
 */
public abstract class TextElementDecorator implements IElementDecorator {

	/**
	 * Return the text decorator for the current figure if it was created before.
	 * Otherwise first create a new text decorator and assign it to the figure, and
	 * the it is returned
	 * 
	 * @param figure the figure to check
	 * @return a not null text decorator. It will a previously created old one
	 * if available, otherwise a new one.
	 */
	protected TextDecorator getDecorator(ComponentFigure figure){
		TextDecorator decorator = (TextDecorator)figure.getDecorator(TextDecorator.class);
		if (decorator == null) {
			decorator = new TextDecorator();
			figure.addDecoratorOnce(decorator);
		}
		return decorator;
	}
	
	/**
	 * Add the text decorator to the figure if it isn't already present
	 */
	@Override
	public void setupFigure(ComponentFigure fig, FigureEditPart editPart) {
		getDecorator(fig);
	}

}
