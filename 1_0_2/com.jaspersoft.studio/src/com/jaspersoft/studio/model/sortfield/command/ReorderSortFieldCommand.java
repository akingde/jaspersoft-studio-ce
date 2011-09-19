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
package com.jaspersoft.studio.model.sortfield.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignSortField;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.sortfield.MSortField;
import com.jaspersoft.studio.model.sortfield.MSortFields;
/*
 * The Class ReorderFieldCommand.
 */
public class ReorderSortFieldCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr field. */
	private JRDesignSortField jrField;

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
	public ReorderSortFieldCommand(MSortField child, MSortFields parent, int newIndex) {
		super(Messages.common_reorder_elements);

		this.newIndex = newIndex;
		this.jrDataset = (JRDesignDataset) parent.getValue();
		this.jrField = (JRDesignSortField) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		try {
			oldIndex = jrDataset.getSortFieldsList().indexOf(jrField);
			jrDataset.removeSortField(jrField);
			if (newIndex < 0 || newIndex > jrDataset.getSortFieldsList().size())
				jrDataset.addSortField(jrField);
			else
				jrDataset.addSortField(newIndex, jrField);
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
			jrDataset.removeSortField(jrField);
			if (oldIndex < 0 || oldIndex > jrDataset.getSortFieldsList().size())
				jrDataset.addSortField(jrField);
			else
				jrDataset.addSortField(oldIndex, jrField);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
