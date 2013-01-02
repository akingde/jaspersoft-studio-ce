/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.gef.parts;

import java.beans.PropertyChangeListener;

import org.eclipse.gef.editparts.AbstractGraphicalEditPart;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
/*
 * The Class AJDEditPart.
 * 
 * @author Chicu Veaceslav
 */
public abstract class AJDEditPart extends AbstractGraphicalEditPart {

	/**
	 * Gets the model node.
	 * 
	 * @return the model node
	 */
	public INode getModelNode() {
		return (INode) getModel();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#activate()
	 */
	@Override
	public void activate() {
		super.activate();

		ANode node = (ANode) getModel();
		node.getPropertyChangeSupport().addPropertyChangeListener((PropertyChangeListener) this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#deactivate()
	 */
	@Override
	public void deactivate() {
		super.deactivate();
		ANode node = (ANode) getModel();
		node.getPropertyChangeSupport().removePropertyChangeListener((PropertyChangeListener) this);
	}

}
