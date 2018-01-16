/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.command;

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

import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JRDesignElementDataset;

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
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param index
	 *          the index
	 */
	public CreateChartCommand(MElementGroup destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

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
	public CreateChartCommand(MFrame destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

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
	public CreateChartCommand(MBand destNode, MGraphicElement srcNode, int index) {
		super(destNode, srcNode, index);
	}

	/**
	 * Instantiates a new creates the element command.
	 * 
	 * @param destNode
	 *          the dest node
	 * @param srcNode
	 *          the src node
	 * @param position
	 *          the position
	 * @param index
	 *          the index
	 */
	public CreateChartCommand(ANode destNode, MGraphicElement srcNode, Rectangle position, int index) {
		super(destNode, srcNode, position, index);
	}

	/**
	 * Creates the object.
	 */
	@Override
	protected void createObject() {
		if (jrElement == null) {
			JRDesignChart newchart = MChart.createJRElement(jasperDesign, JRDesignChart.CHART_TYPE_AREA, true);

			ChartWizard wizard = new ChartWizard(new MChart(null, newchart, -1), (JRDesignElementDataset) newchart.getDataset());
			wizard.setConfig(jConfig, false);
			wizard.setExpressionContext(ModelUtils.getElementExpressionContext(null, destNode)); // Use the "future" parent inherited
			
			// information
			WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
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
					CreateChartAxesCommand cmd = new CreateChartAxesCommand((JRDesignChart) jrElement, null, -1, jasperDesign);
					cmd.setSelectedAxes(wizard.getChoseAxis());
					addCommand(cmd);
				}
			}
		} else if (location == null)
			setElementBounds();
	}

}
