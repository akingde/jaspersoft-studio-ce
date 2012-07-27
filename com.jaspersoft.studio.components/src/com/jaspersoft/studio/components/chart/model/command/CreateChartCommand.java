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
package com.jaspersoft.studio.components.chart.model.command;

import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.chartAxis.command.CreateChartAxesCommand;
import com.jaspersoft.studio.components.chart.wizard.ChartWizard;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.MElementGroup;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.model.band.MBand;
import com.jaspersoft.studio.model.command.CreateElementCommand;
import com.jaspersoft.studio.model.frame.MFrame;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.SelectionHelper;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateChartCommand extends CreateElementCommand {

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateChartCommand(MElementGroup destNode, MGraphicElement srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateChartCommand(MFrame destNode, MGraphicElement srcNode,
			int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param index
	 *            the index
	 */
	public CreateChartCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *            the dest node
	 * @param srcNode
	 *            the src node
	 * @param position
	 *            the position
	 * @param index
	 *            the index
	 */
	public CreateChartCommand(ANode destNode, MGraphicElement srcNode,
			Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	/**
	 * Creates the object.
	 */
	@Override
	protected void createObject() {
		if (jrElement == null) {
			JRDesignChart newchart = MChart.createJRElement(jasperDesign,
					JRDesignChart.CHART_TYPE_AREA);

			jConfig.setFileResolver(SelectionHelper.getFileResolver());

			ChartWizard wizard = new ChartWizard(
					new MChart(null, newchart, -1),
					(JRDesignElementDataset) newchart.getDataset());
			wizard.init(jConfig);
			wizard.setExpressionContext(ModelUtils.getElementExpressionContext(
					null, destNode)); // Use the "future" parent inherited
										// information
			WizardDialog dialog = new WizardDialog(Display.getDefault()
					.getActiveShell(), wizard);
			dialog.create();
			if (dialog.open() == Dialog.OK) {
				srcNode = wizard.getChart();
				if (srcNode.getValue() == null)
					jrElement = newchart;
				else
					jrElement = (JRDesignElement) srcNode.getValue();

				if (jrElement != null)
					setElementBounds();

				if (((JRDesignChart) jrElement).getChartType() == JRDesignChart.CHART_TYPE_MULTI_AXIS) {
					CreateChartAxesCommand cmd = new CreateChartAxesCommand(
							(JRDesignChart) jrElement, null, -1, jasperDesign);
					addCommand(cmd);
				}
			}
		}
	}

}
