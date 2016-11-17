/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.style.command;

import org.eclipse.core.resources.IFile;
import org.eclipse.gef.commands.Command;
import org.eclipse.ui.IWorkbenchPart;

import com.jaspersoft.studio.editor.outline.actions.SaveStyleAsTemplateAction;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.model.style.MStylesTemplate;

import net.sf.jasperreports.engine.JRTemplateReference;

/**
 * Action to open the wizard to export one or more JRStyle as an external template style file.
 * This also propose a question to substitute the exported styles with the template reference.
 * This action should be used only on the style template editor, it is not suited for
 * the report editor.
 * 
 * @author Orlandin Marco
 * 
 */
public class SaveTemplateStyleAsTemplateAction extends SaveStyleAsTemplateAction {

	public SaveTemplateStyleAsTemplateAction(IWorkbenchPart part) {
		super(part);
	}
	
	/**
	 * Generate the command to create the template style node 
	 * 
	 * @param templateFile the file of the template. Must be a jrtx file
	 * @param parent the parent where the new node to create. Must be an MStyle
	 * @return the command to create the template reference node on the parent.
	 */
	@Override
	protected Command getCreateCommand(IFile templateFile, ANode parent) {
		JRTemplateReference jrTemplate = MStyleTemplateReference.createJRTemplate();
		jrTemplate.setLocation(templateFile.getRawLocation().makeAbsolute().toOSString());
		MStyleTemplateReference templateModel = new MStyleTemplateReference(null, jrTemplate, -1);
		return new CreateStyleTemplateReferenceCommand((MStylesTemplate) parent, templateModel, 0);
	}
	
	/**
	 * Return the command to delete a style 
	 * 
	 * @param style the style to delete
	 * @return the command to delete the style
	 */
	@Override
	protected Command getDeleteCommand(MStyle style) {
		return new DeleteStyleCommand((MStylesTemplate) style.getParent(), style);
	}

}
