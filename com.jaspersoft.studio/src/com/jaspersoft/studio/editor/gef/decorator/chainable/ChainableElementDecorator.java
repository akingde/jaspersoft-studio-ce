/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.decorator.chainable;

import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;

import com.jaspersoft.studio.editor.gef.decorator.IElementDecorator;
import com.jaspersoft.studio.editor.gef.figures.ComponentFigure;
import com.jaspersoft.studio.editor.gef.parts.FigureEditPart;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * An element decorator that instances one and only one ChainableElementDecorator, that will be 
 * used to paint more decorators one after another
 * @author Orlandin Marco
 *
 */
public abstract class ChainableElementDecorator implements IElementDecorator {

	/**
	 * Return the text decorator for the current figure if it was created before.
	 * Otherwise first create a new text decorator and assign it to the figure, and
	 * the it is returned
	 * 
	 * @param figure the figure to check
	 * @return a not null text decorator. It will a previously created old one
	 * if available, otherwise a new one.
	 */
	protected ChainableDecorator getDecorator(ComponentFigure figure){
		ChainableDecorator decorator = (ChainableDecorator)figure.getDecorator(ChainableDecorator.class);
		if (decorator == null) {
			decorator = new ChainableDecorator();
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

	/**
	 * By default a global feedback is not provided
	 */
	@Override
	public void paintGlobal(Graphics g, IFigure figure, JasperReportsConfiguration jConfig) {
		
	}
}
