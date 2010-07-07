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
package com.jaspersoft.studio.model.variable.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignVariable;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariables;

// TODO: Auto-generated Javadoc
/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateVariableCommand extends Command {
	
	/** The jr variable. */
	private JRDesignVariable jrVariable;
	
	/** The jr dataset. */
	private JRDesignDataset jrDataset;
	
	/** The index. */
	private int index;

	/**
	 * Instantiates a new creates the variable command.
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
	public CreateVariableCommand(MVariables destNode, MVariable srcNode, Point position, int index) {
		super();
		this.jrDataset = (JRDesignDataset) destNode.getValue();
		this.index = index;
		if (srcNode != null && srcNode.getValue() != null)
			this.jrVariable = (JRDesignVariable) srcNode.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		if (jrVariable == null) {
			this.jrVariable = MVariable.createJRVariable(jrDataset);
		}
		if (jrVariable != null) {
			try {
				if (index < 0 || index > jrDataset.getVariablesList().size())
					jrDataset.addVariable(jrVariable);
				else
					jrDataset.addVariable(index, jrVariable);
			} catch (JRException e) {
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		return true;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrDataset.removeVariable(jrVariable);
	}
}
