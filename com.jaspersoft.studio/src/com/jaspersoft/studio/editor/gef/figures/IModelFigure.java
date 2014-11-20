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
