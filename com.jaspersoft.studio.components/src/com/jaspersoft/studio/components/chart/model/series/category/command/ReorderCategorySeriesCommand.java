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
package com.jaspersoft.studio.components.chart.model.series.category.command;

import net.sf.jasperreports.charts.design.JRDesignCategoryDataset;
import net.sf.jasperreports.charts.design.JRDesignCategorySeries;

import org.eclipse.gef.commands.Command;

import com.jaspersoft.studio.components.chart.messages.Messages;
import com.jaspersoft.studio.components.chart.model.dataset.MChartDataset;
import com.jaspersoft.studio.components.chart.model.series.category.MCategorySeries;
/*
 * The Class ReorderElementCommand.
 */
public class ReorderCategorySeriesCommand extends Command {

	/** The new index. */
	private int oldIndex, newIndex;

	private JRDesignCategorySeries jrElement;
	private JRDesignCategoryDataset jrGroup;

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
	public ReorderCategorySeriesCommand(MCategorySeries child, MChartDataset parent, int newIndex) {
		super(Messages.common_reorder_elements); 
		this.newIndex = newIndex;
		this.jrElement = (JRDesignCategorySeries) child.getValue();
		this.jrGroup = (JRDesignCategoryDataset) parent.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		oldIndex = jrGroup.getSeriesList().indexOf(jrElement);
		jrGroup.removeCategorySeries(jrElement);

		if (newIndex >= 0 && newIndex < jrGroup.getSeriesList().size())
			jrGroup.addCategorySeries(newIndex, jrElement);
		else
			jrGroup.addCategorySeries(jrElement);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#undo()
	 */
	@Override
	public void undo() {
		jrGroup.removeCategorySeries(jrElement);

		if (oldIndex >= 0 && oldIndex < jrGroup.getSeriesList().size())
			jrGroup.addCategorySeries(oldIndex, jrElement);
		else
			jrGroup.addCategorySeries(jrElement);
	}

}
