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
package com.jaspersoft.studio.editor;

import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.part.MultiPageEditorPart;

import com.jaspersoft.studio.model.INode;

public interface IMultiEditor {
	
	/**
	 * Returns the active nested editor if there is one.
	 * 
	 * NOTE: used to extend the actual visibility of the 
	 * protected method in the {@link MultiPageEditorPart} class.
	 */
	public IEditorPart getActiveEditor();
	
	/**
	 * Return the actual model in the editor
	 */
	public INode getModel();
}
