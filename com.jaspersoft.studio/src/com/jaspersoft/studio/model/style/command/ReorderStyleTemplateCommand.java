/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;
/*/*
 * The Class ReorderStyleTemplateCommand.
 */
public class ReorderStyleTemplateCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr template. */
	private JRDesignReportTemplate jrTemplate;

	/** The jr design. */
	private JasperDesign jrDesign;

	/**
	 * Instantiates a new reorder style template command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderStyleTemplateCommand(MStyleTemplate child, MStyles parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = newIndex;
		this.jrDesign = parent.getJasperDesign();
		this.jrTemplate = (JRDesignReportTemplate) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrDesign.getTemplatesList().indexOf(jrTemplate);

		jrDesign.removeTemplate(jrTemplate);
		if (newIndex < 0 || newIndex > jrDesign.getTemplatesList().size())
			jrDesign.addTemplate(jrTemplate);
		else
			jrDesign.addTemplate(newIndex, jrTemplate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrDesign.removeTemplate(jrTemplate);
		if (oldIndex < 0 || oldIndex > jrDesign.getTemplatesList().size())
			jrDesign.addTemplate(jrTemplate);
		else
			jrDesign.addTemplate(oldIndex, jrTemplate);
	}

}
