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
package com.jaspersoft.studio.model.dataset.command;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.model.MReport;
import com.jaspersoft.studio.model.dataset.MDataset;

// TODO: Auto-generated Javadoc
/**
 * The Class ReorderDatasetCommand.
 */
public class ReorderDatasetCommand extends Command {
	
	/** The new index. */
	private int oldIndex, newIndex;
	
	/** The jr field. */
	private JRDesignDataset jrField;
	
	/** The jr design. */
	private JasperDesign jrDesign;

	/**
	 * Instantiates a new reorder dataset command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderDatasetCommand(MReport child, MDataset parent, int newIndex) {
		super("Reorder elements");

		this.newIndex = newIndex;
		this.jrDesign = (JasperDesign) parent.getJasperDesign();
		this.jrField = (JRDesignDataset) child.getValue();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		try {
			oldIndex = jrDesign.getDatasetsList().indexOf(jrField);
			jrDesign.removeDataset(jrField);
			if (newIndex < 0 || newIndex > jrDesign.getDatasetsList().size())
				jrDesign.addDataset(jrField);
			else
				jrDesign.addDataset(newIndex, jrField);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		try {
			jrDesign.removeDataset(jrField);
			if (oldIndex < 0 || oldIndex > jrDesign.getDatasetsList().size())
				jrDesign.addDataset(jrField);
			else
				jrDesign.addDataset(oldIndex, jrField);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
