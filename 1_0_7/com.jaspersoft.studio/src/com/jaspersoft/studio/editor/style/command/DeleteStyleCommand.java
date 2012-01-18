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
package com.jaspersoft.studio.editor.style.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStylesTemplate;
/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteStyleCommand extends Command {

	private JRSimpleTemplate jrDesign;

	private JRDesignStyle jrStyle;

	private int elementPosition = 0;

	/**
	 * Instantiates a new delete style command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteStyleCommand(MStylesTemplate destNode, MStyle srcNode) {
		super();
		this.jrDesign = (JRSimpleTemplate) destNode.getValue();
		this.jrStyle = (JRDesignStyle) srcNode.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrDesign.getStylesList().indexOf(jrStyle);
		jrDesign.removeStyle(jrStyle);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrDesign == null || jrStyle == null)
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
		try {
			if (elementPosition < 0 || elementPosition > jrDesign.getStylesList().size())
				jrDesign.addStyle(jrStyle);
			else
				jrDesign.addStyle(elementPosition, jrStyle);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
