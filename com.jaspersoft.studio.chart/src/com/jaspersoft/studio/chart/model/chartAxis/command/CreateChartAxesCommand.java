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
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.chart.model.MChart;
import com.jaspersoft.studio.chart.model.chartAxis.MChartAxes;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateChartAxesCommand extends Command {

	private MGraphicElement srcNode;

	private JRDesignChartAxis jrElement;

	private JRDesignMultiAxisPlot jrChart;

	private int index;

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateChartAxesCommand(MChart destNode, MChartAxes srcNode, int newIndex) {
		super();
		this.srcNode = srcNode;
		this.jrElement = (JRDesignChartAxis) srcNode.getValue();
		this.jrChart = (JRDesignMultiAxisPlot) ((JRDesignChart) destNode.getValue()).getPlot();
		this.index = newIndex;
	}

	/**
	 * Creates the object.
	 */
	protected void createObject() {
		if (jrElement == null) {
			// here put a wizard
			ChartAxesWizard wizard = new ChartAxesWizard();
			WizardDialog dialog = new WizardDialog(Display.getCurrent().getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				byte type = wizard.getChartAxis();
				jrElement = new JRDesignChartAxis((JRDesignChart) jrChart.getChart());
				jrElement.setChart(new JRDesignChart(srcNode.getJasperDesign(), type));
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#execute()
	 */
	@Override
	public void execute() {
		createObject();
		if (jrElement != null) {
			jrChart.addAxis(jrElement);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrChart == null || jrElement == null)
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
		// jrChart.getAxes().remove(jrElemSent);

		jrChart.getAxes().remove(jrElement);
		JRDesignChartAxis axis0 = (JRDesignChartAxis) jrChart.getAxes().get(0);
		((JRDesignChart) jrChart.getChart()).setDataset(axis0.getChart().getDataset());
		jrChart.getEventSupport().firePropertyChange(JRDesignMultiAxisPlot.PROPERTY_AXES, jrElement, null);
	}

}
