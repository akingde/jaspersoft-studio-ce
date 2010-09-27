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
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.design.JRDesignReportTemplate;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MStyleTemplate;
import com.jaspersoft.studio.model.style.MStyles;

/**
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
	 * Instantiates a new creates the style template command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreateStyleTemplateCommand(MStyles destNode, MStyleTemplate srcNode, Point position, int index) {
		super();
		this.jrDesign =  destNode.getJasperDesign();
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
		if (jrTemplate == null) {
			this.jrTemplate = MStyleTemplate.createJRTemplate();
		}
		if (jrTemplate != null) {
			if (index < 0 || index > jrDesign.getTemplatesList().size())
				jrDesign.addTemplate(jrTemplate);
			else
				jrDesign.addTemplate(index, jrTemplate);
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
