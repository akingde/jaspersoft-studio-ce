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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.utils.SelectionHelper;
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
	
	@Override
	public Object getAdapter(Class key) {
		if(key == IResource.class || key == IFile.class){
			return getAssociatedFile();
		}
		return super.getAdapter(key);
	}
	
	/**
	 * Returns the file associated.
	 * <p>
	 * Given the current edit part belonging to the active JRXML editor
	 * (report designer) the related file is returned.
	 * 
	 * @return the associated file resource
	 */
	public IFile getAssociatedFile() {
		IEditorPart ep = SelectionHelper.getActiveJRXMLEditor();
		if (ep != null && ep.getEditorInput() instanceof IFileEditorInput) {
			IFileEditorInput fe = ((IFileEditorInput) ep.getEditorInput());
			return fe.getFile();
		}
		else {
			return null;
		}
	}

}
