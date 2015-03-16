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
