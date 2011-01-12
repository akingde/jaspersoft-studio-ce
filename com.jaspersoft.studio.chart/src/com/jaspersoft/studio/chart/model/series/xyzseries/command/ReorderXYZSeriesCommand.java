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
package com.jaspersoft.studio.chart.model.series.xyzseries.command;

import net.sf.jasperreports.charts.design.JRDesignXyzDataset;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.chart.messages.Messages;
import com.jaspersoft.studio.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.chart.model.series.xyzseries.MXYZSeries;

/**
 * The Class ReorderElementCommand.
 */
public class ReorderXYZSeriesCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	private JRDesignXyzSeries jrElement;
	private JRDesignXyzDataset jrGroup;

	/**
	 * Instantiates a new reorder element command.
	 * 
	 * @param child
	 *          the child
	 * @param parent
	 *          the parent
	 * @param newIndex
	 *          the new index
	 */
	public ReorderXYZSeriesCommand(MXYZSeries child, MChartDataset parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = newIndex;
		this.jrElement = (JRDesignXyzSeries) child.getValue();
		this.jrGroup = (JRDesignXyzDataset) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrGroup.getSeriesList().indexOf(jrElement);

		jrGroup.removeXyzSeries(jrElement);
		if (newIndex >= 0 && newIndex < jrGroup.getSeriesList().size())
			jrGroup.addXyzSeries(newIndex, jrElement);
		else
			jrGroup.addXyzSeries(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrGroup.removeXyzSeries(jrElement);
		if (oldIndex >= 0 && oldIndex < jrGroup.getSeriesList().size())
			jrGroup.addXyzSeries(oldIndex, jrElement);
		else
			jrGroup.addXyzSeries(jrElement);
	}

}
