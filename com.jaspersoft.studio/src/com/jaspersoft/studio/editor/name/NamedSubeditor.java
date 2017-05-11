/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.name;

import com.jaspersoft.studio.editor.report.AbstractVisualEditor;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.INode;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.Misc;

/**
 * Represent a subeditor that can change its name according with the name
 * of the managed model
 * 
 * @author Orlandin Marco
 *
 */
public abstract class NamedSubeditor extends AbstractVisualEditor {

	public NamedSubeditor(JasperReportsConfiguration jrContext) {
		super(jrContext);
	}
	
	/**
	 * When the model is set on the editor the listener is added to it and removed from the old one
	 * (if present)
	 */
	@Override
	public void setModel(INode model) {
		super.setModel(model);
		updateEditorName();
	}
	
	/**
	 * Update the name of the editor, if it is present on the element uses that one, otherwise
	 * a default name for the editor is used
	 */
	public void updateEditorName() {
		String currentName = getEditorName();
		if (!Misc.isNullOrEmpty(currentName)) {
			setPartName(currentName);
			return;
		}
		setPartName(getDefaultEditorName());
	}
	
	/**
	 * Return the default name for the editor, when the managed model
	 * has not a name
	 * 
	 * @return a name for the editor, must be not empty and not null
	 */
	public abstract String getDefaultEditorName();
	
	/**
	 * Return the node edited inside the editor
	 * 
	 * @return node edited inside the editor
	 */
	public abstract ANode getEditedNode();
	
	/**
	 * Return the default name for the editor, taken from the managed 
	 * model
	 * 
	 * @return a name for the editor, if null the default name will be used
	 */
	public abstract String getEditorName();
}
