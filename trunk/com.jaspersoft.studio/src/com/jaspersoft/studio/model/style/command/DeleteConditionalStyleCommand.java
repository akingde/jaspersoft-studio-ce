/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.model.style.command;

import net.sf.jasperreports.engine.design.JRDesignConditionalStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.style.MConditionalStyle;
import com.jaspersoft.studio.model.style.MStyle;

// TODO: Auto-generated Javadoc
/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteConditionalStyleCommand extends Command {
	
	/** The jr style. */
	private JRDesignStyle jrStyle;
	
	/** The jr conditional style. */
	private JRDesignConditionalStyle jrConditionalStyle;
	
	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete conditional style command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteConditionalStyleCommand(MStyle destNode, MConditionalStyle srcNode) {
		super();
		this.jrStyle = (JRDesignStyle) destNode.getValue();
		this.jrConditionalStyle = (JRDesignConditionalStyle) srcNode.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrStyle.getConditionalStyleList().indexOf(jrConditionalStyle);
		jrStyle.removeConditionalStyle(jrConditionalStyle);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrStyle == null || jrConditionalStyle == null)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (elementPosition < 0 || elementPosition > jrStyle.getConditionalStyleList().size())
			jrStyle.addConditionalStyle(jrConditionalStyle);
		else
			jrStyle.addConditionalStyle(elementPosition, jrConditionalStyle);
	}
}
