/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
