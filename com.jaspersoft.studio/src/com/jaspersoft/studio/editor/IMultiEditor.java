package com.jaspersoft.studio.editor;

import org.eclipse.ui.IEditorPart;

import com.jaspersoft.studio.model.INode;

public interface IMultiEditor {
	public IEditorPart getActiveEditor();
	
	/**
	 * Return the actual model in the editor
	 */
	public INode getModel();
}
