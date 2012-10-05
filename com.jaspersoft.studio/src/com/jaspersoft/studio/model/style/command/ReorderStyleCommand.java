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

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.style.MStyles;
/*/*
 * The Class ReorderStyleCommand.
 */
public class ReorderStyleCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr style. */
	private JRDesignStyle jrStyle;

	/** The jr design. */
	private JasperDesign jrDesign;

	/**
	 * Instantiates a new reorder style command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderStyleCommand(MStyle child, MStyles parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrDesign = parent.getJasperDesign();
		this.jrStyle = (JRDesignStyle) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrDesign.getStylesList().indexOf(jrStyle);
		try {
			jrDesign.removeStyle(jrStyle);
			if (newIndex < 0 || newIndex > jrDesign.getStylesList().size())
				jrDesign.addStyle(jrStyle);
			else
				jrDesign.addStyle(newIndex, jrStyle);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		try {
			jrDesign.removeStyle(jrStyle);
			if (oldIndex < 0 || oldIndex > jrDesign.getStylesList().size())
				jrDesign.addStyle(jrStyle);
			else
				jrDesign.addStyle(oldIndex, jrStyle);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

}
