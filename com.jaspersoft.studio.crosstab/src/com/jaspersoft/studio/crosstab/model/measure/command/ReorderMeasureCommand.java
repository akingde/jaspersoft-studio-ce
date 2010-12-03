/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of iReport.
 * 
 * iReport is free software: you can redistribute it and/or modify it under the terms of the GNU Affero General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * iReport is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with iReport. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.crosstab.model.measure.command;

import net.sf.jasperreports.crosstabs.design.JRDesignCrosstab;
import net.sf.jasperreports.crosstabs.design.JRDesignCrosstabMeasure;
import net.sf.jasperreports.engine.JRException;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.crosstab.model.measure.MMeasure;
import com.jaspersoft.studio.crosstab.model.measure.MMeasures;

/**
 * The Class ReorderParameterCommand.
 */
public class ReorderMeasureCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	/** The jr parameter. */
	private JRDesignCrosstabMeasure jrMeasure;

	/** The jr dataset. */
	private JRDesignCrosstab jrCrosstab;

	/**
	 * Instantiates a new reorder parameter command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderMeasureCommand(MMeasure child, MMeasures parent, int newIndex) {
		super(Messages.ReorderMeasureCommand_reorder_elements);

		this.newIndex = newIndex;
		this.jrCrosstab = (JRDesignCrosstab) parent.getValue();
		this.jrMeasure = (JRDesignCrosstabMeasure) child.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrCrosstab.getMesuresList().indexOf(jrMeasure);

		try {
			jrCrosstab.removeMeasure(jrMeasure);
			if (newIndex < 0 || newIndex > jrCrosstab.getMesuresList().size())
				jrCrosstab.addMeasure(jrMeasure);
			else
				jrCrosstab.addMeasure(jrMeasure);
			// jrCrosstab.addParameter(newIndex, jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		try {
			jrCrosstab.removeMeasure(jrMeasure);
			if (oldIndex < 0 || oldIndex > jrCrosstab.getMesuresList().size())
				jrCrosstab.addMeasure(jrMeasure);
			else
				jrCrosstab.addMeasure(jrMeasure);
			// jrCrosstab.addParameter(oldIndex, jrParameter);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
