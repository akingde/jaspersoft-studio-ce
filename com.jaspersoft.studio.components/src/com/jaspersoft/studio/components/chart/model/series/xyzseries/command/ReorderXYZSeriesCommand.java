/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.series.xyzseries.command;

import net.sf.jasperreports.charts.design.JRDesignXyzDataset;
import net.sf.jasperreports.charts.design.JRDesignXyzSeries;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.components.chart.model.series.xyzseries.MXYZSeries;

/*
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
	 *            the child
	 * @param parent
	 *            the parent
	 * @param newIndex
	 *            the new index
	 */
	public ReorderXYZSeriesCommand(MXYZSeries child, MChartDataset parent,
			int newIndex) {
		super(Messages.common_reorder_elements);
		this.newIndex = Math.max(0, newIndex);
		this.jrElement = (JRDesignXyzSeries) child.getValue();
		this.jrGroup = (JRDesignXyzDataset) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
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
	@Override
	public void undo() {
		jrGroup.removeXyzSeries(jrElement);
		if (oldIndex >= 0 && oldIndex < jrGroup.getSeriesList().size())
			jrGroup.addXyzSeries(oldIndex, jrElement);
		else
			jrGroup.addXyzSeries(jrElement);
	}

}
