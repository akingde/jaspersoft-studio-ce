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
package com.jaspersoft.studio.components.chart.model.series.timeperiod.command;

import net.sf.jasperreports.charts.design.JRDesignTimePeriodDataset;
import net.sf.jasperreports.charts.design.JRDesignTimePeriodSeries;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.components.chart.model.series.timeperiod.MTimePeriodSeries;

/*
 * The Class ReorderElementCommand.
 */
public class ReorderTimePeriodSeriesCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	private JRDesignTimePeriodSeries jrElement;
	private JRDesignTimePeriodDataset jrGroup;

	/**
	 * Instantiates a new reorder element command.
	 * 
	 * @param child
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderTimePeriodSeriesCommand(MTimePeriodSeries child,
			MChartDataset parent, int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = Math.max(0, newIndex);
		this.jrElement = (JRDesignTimePeriodSeries) child.getValue();
		this.jrGroup = (JRDesignTimePeriodDataset) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrGroup.getSeriesList().indexOf(jrElement);

		if (newIndex >= 0 && newIndex < jrGroup.getSeriesList().size())
			jrGroup.addTimePeriodSeries(newIndex, jrElement);
		else
			jrGroup.addTimePeriodSeries(jrElement);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		if (oldIndex >= 0 && oldIndex < jrGroup.getSeriesList().size())
			jrGroup.addTimePeriodSeries(oldIndex, jrElement);
		else
			jrGroup.addTimePeriodSeries(jrElement);
	}

}
