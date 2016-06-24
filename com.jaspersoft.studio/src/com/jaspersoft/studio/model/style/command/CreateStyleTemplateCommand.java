/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.model.style.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;

import com.jaspersoft.studio.jface.dialogs.StyleTemplateSelectionDialog;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateStyleTemplateCommand extends Command {

	/** The jr template. */
	private JRDesignReportTemplate jrTemplate;

	/** The jr design. */
	private JasperDesign jrDesign;

	/** The index. */
	private int index;

	/**
	 * The configuration of the actual report
	 */
	private JasperReportsConfiguration jConfig;

	/**
	 * Instantiates a new creates the style template command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateStyleTemplateCommand(MStyles destNode, MStyleTemplate srcNode, int index) {
		super();
		this.jrDesign = destNode.getJasperDesign();
		this.jConfig = destNode.getJasperConfiguration();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrTemplate = (JRDesignReportTemplate) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrTemplate != null) {
			if (index < 0 || index > jrDesign.getTemplatesList().size())
				jrDesign.addTemplate(jrTemplate);
			else
				jrDesign.addTemplate(index, jrTemplate);
		}
	}

	/**
	 * Create the container for the selected jrtx file, by selecting it from a chooser dialog. If the selected file is not
	 * valid an error is shown
	 */
	private void createObject() {
		if (jrTemplate == null) {
			StyleTemplateSelectionDialog fsd = new StyleTemplateSelectionDialog(UIUtils.getShell());
			fsd.configureDialog(jConfig);
			if (fsd.open() == Dialog.OK) {
				jrTemplate = MStyleTemplate.createJRTemplate();
				jrTemplate.setSourceExpression(fsd.getFileExpression());
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDesign.removeTemplate(jrTemplate);
	}
}
