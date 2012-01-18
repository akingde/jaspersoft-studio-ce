/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.components.chart.model.series.gantt.command;

import net.sf.jasperreports.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports.charts.design.JRDesignGanttSeries;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.components.chart.model.series.gantt.MGanttSeries;
/*
 * The Class ReorderElementCommand.
 */
public class ReorderGanttSeriesCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	private JRDesignGanttSeries jrElement;
	private JRDesignGanttDataset jrGroup;

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
	public ReorderGanttSeriesCommand(MGanttSeries child, MChartDataset parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = newIndex;
		this.jrElement = (JRDesignGanttSeries) child.getValue();
		this.jrGroup = (JRDesignGanttDataset) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrGroup.getSeriesList().indexOf(jrElement);

		jrGroup.removeGanttSeries(jrElement);
		if (newIndex >= 0 && newIndex < jrGroup.getSeriesList().size())
			jrGroup.addGanttSeries(newIndex, jrElement);
		else
			jrGroup.addGanttSeries(jrElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrGroup.removeGanttSeries(jrElement);
		if (oldIndex >= 0 && oldIndex < jrGroup.getSeriesList().size())
			jrGroup.addGanttSeries(oldIndex, jrElement);
		else
			jrGroup.addGanttSeries(jrElement);
	}

}
