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
package com.jaspersoft.studio.chart.model.series.gantt.command;

import net.sf.jasperreports.charts.design.JRDesignGanttDataset;
import net.sf.jasperreports.charts.design.JRDesignGanttSeries;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.chart.model.series.gantt.MGanttSeries;

/**
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
		super("Reorder elements");
		this.newIndex = newIndex;
		this.jrElement = (JRDesignGanttSeries) child.getValue();
		this.jrGroup = (JRDesignGanttDataset) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	public void execute() {
		oldIndex = jrGroup.getSeriesList().indexOf(jrElement);

		jrGroup.addGanttSeries(jrElement);
		jrGroup.getEventSupport().fireCollectionElementAddedEvent("ganttSeries", jrElement,
				jrGroup.getSeriesList().size() - 1);
		jrGroup.removeGanttSeries(jrElement);
		jrGroup.getEventSupport().fireCollectionElementRemovedEvent("ganttSeries", jrElement, newIndex);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	public void undo() {
		jrGroup.removeGanttSeries(jrElement);
		jrGroup.getEventSupport().fireCollectionElementRemovedEvent("ganttSeries", jrElement, newIndex);

		jrGroup.addGanttSeries(jrElement);
		jrGroup.getEventSupport().fireCollectionElementAddedEvent("ganttSeries", jrElement,
				jrGroup.getSeriesList().size() - 1);
	}

}
