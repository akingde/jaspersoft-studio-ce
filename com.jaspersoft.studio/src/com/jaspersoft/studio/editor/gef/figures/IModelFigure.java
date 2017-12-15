/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.figures;

import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Interface for a figure  that can provide the model of the element
 * that generated it
 * 
 * @author Orlandin Marco
 * 
 */
public interface IModelFigure {

	/**
	 * Return the model associated to this figure, can be null
	 */
	public MGraphicElement getModel();
}
