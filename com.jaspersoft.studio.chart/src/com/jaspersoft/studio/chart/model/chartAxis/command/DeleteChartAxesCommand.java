/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of iReport.
 *
 * iReport is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * iReport is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with iReport. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.chart.model.chartAxis.command;

import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.engine.design.JRDesignChart;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.chart.model.MChart;
import com.jaspersoft.studio.chart.model.chartAxis.MChartAxes;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class DeleteChartAxesCommand extends Command {

	/** The jr group. */
	private JRDesignMultiAxisPlot jrGroup;

	/** The jr element. */
	private JRDesignChartAxis jrElement;

	/** The element position. */
	private int elementPosition = 0;

	/**
	 * Instantiates a new delete element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 */
	public DeleteChartAxesCommand(MChart destNode, MChartAxes srcNode) {
		super();
		this.jrElement = (JRDesignChartAxis) srcNode.getValue();
		this.jrGroup = (JRDesignMultiAxisPlot) ((JRDesignChart) destNode.getValue()).getPlot();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		elementPosition = jrGroup.getAxes().indexOf(jrElement);
		// jrGroup.getAxes().remove(jrElement);

		jrGroup.getAxes().remove(jrElement);
		JRDesignChartAxis axis0 = (JRDesignChartAxis) jrGroup.getAxes().get(0);
		((JRDesignChart) jrGroup.getChart()).setDataset(axis0.getChart().getDataset());
		jrGroup.getEventSupport().firePropertyChange(JRDesignMultiAxisPlot.PROPERTY_AXES, jrElement, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrGroup == null || jrElement == null)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrGroup.addAxis(jrElement);
	}
}
