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
package com.jaspersoft.studio.editor.gef.figures;

import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Generic figure used for the graphical elements, implementing the interface IModelFigure
 * allow the hiding and paint caching of the elements
 * 
 * @author Orlandin Marco
 *
 */
public class GenericGraphicalFigure extends org.eclipse.draw2d.RectangleFigure implements IModelFigure{

	/**
	 * The model of the element
	 */
	private MGraphicElement model;
	
	/**
	 * Create the figure
	 *  
	 * @param model the model of the element
	 */
	public GenericGraphicalFigure(MGraphicElement model){
		this.model = model;
	}
	
	/**
	 * Return the model of the element
	 */
	@Override
	public MGraphicElement getModel() {
		return model;
	}
	
}