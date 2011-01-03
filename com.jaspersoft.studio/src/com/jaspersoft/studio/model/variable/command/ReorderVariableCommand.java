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

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.variable.MVariable;
import com.jaspersoft.studio.model.variable.MVariables;

// TODO: Auto-generated Javadoc
/**
 * The Class ReorderVariableCommand.
 */
public class ReorderVariableCommand extends Command {
	
	/** The new index. */
	private int oldIndex, newIndex;
	
	/** The jr variable. */
	private JRDesignVariable jrVariable;
	
	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/**
	 * Instantiates a new reorder variable command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderVariableCommand(MVariable child, MVariables parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrVariable = (JRDesignVariable) child.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrDataset.getVariablesList().indexOf(jrVariable);

		try {
			jrDataset.removeVariable(jrVariable);
			if (newIndex < 0 || newIndex > jrDataset.getVariablesList().size())
				jrDataset.addVariable(jrVariable);
			else
				jrDataset.addVariable(newIndex, jrVariable);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		try {
			jrDataset.removeVariable(jrVariable);
			if (oldIndex < 0 || oldIndex > jrDataset.getVariablesList().size())
				jrDataset.addVariable(jrVariable);
			else
				jrDataset.addVariable(oldIndex, jrVariable);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
