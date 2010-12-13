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
package com.jaspersoft.studio.model.field.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.field.MField;
import com.jaspersoft.studio.model.field.MFields;

// TODO: Auto-generated Javadoc
/**
 * The Class ReorderFieldCommand.
 */
public class ReorderFieldCommand extends Command {
	
	/** The new index. */
	private int oldIndex, newIndex;
	
	/** The jr field. */
	private JRDesignField jrField;
	
	/** The jr dataset. */
	private JRDesignDataset jrDataset;

	/**
	 * Instantiates a new reorder field command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderFieldCommand(MField child, MFields parent, int newIndex) {
		super(Messages.ReorderFieldCommand_reorder_elements);

		this.newIndex = newIndex;
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrField = (JRDesignField) child.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		try {
			oldIndex = jrDataset.getFieldsList().indexOf(jrField);
			jrDataset.removeField(jrField);
			if (newIndex < 0 || newIndex > jrDataset.getFieldsList().size())
				jrDataset.addField(jrField);
			else
				jrDataset.addField(newIndex, jrField);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		try {
			jrDataset.removeField(jrField);
			if (oldIndex < 0 || oldIndex > jrDataset.getFieldsList().size())
				jrDataset.addField(jrField);
			else
				jrDataset.addField(oldIndex, jrField);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
