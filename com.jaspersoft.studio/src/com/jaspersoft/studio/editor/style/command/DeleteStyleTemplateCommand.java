/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.style.command;

import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRTemplateReference;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MStyleTemplateReference;
import com.jaspersoft.studio.model.style.MStylesTemplate;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteStyleTemplateCommand extends Command {

	private JRSimpleTemplate jrDesign;

	private JRTemplateReference jrTemplate;

	private int index = 0;

	/**
	 * Instantiates a new delete style template command.
	 */
	public DeleteStyleTemplateCommand(MStylesTemplate destNode, MStyleTemplateReference srcNode) {
		super();
		this.jrDesign = (JRSimpleTemplate) destNode.getValue();
		this.jrTemplate = (JRTemplateReference) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		index = jrDesign.getIncludedTemplatesList().indexOf(jrTemplate);
		jrDesign.removeIncludedTemplate(jrTemplate);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDesign == null || jrTemplate == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (index < 0 || index > jrDesign.getIncludedTemplatesList().size())
			jrDesign.addIncludedTemplate(jrTemplate);
		else
			jrDesign.addIncludedTemplate(index, jrTemplate);
	}
}
