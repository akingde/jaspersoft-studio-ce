/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.chartAxis.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Display;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.model.chartAxis.MChartAxes;

import net.sf.jasperreports.charts.design.JRDesignChartAxis;
import net.sf.jasperreports.charts.design.JRDesignMultiAxisPlot;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JasperDesign;

/*
 * link nodes & together.
 * 
 * @author Chicu Veaceslav
 */
public class CreateChartAxesCommand extends Command {

	private JRDesignChartAxis jrElement;
	private JRDesignMultiAxisPlot jrPlot;
	private JRDesignChart chart;
	private int index;
	private JasperDesign jDesign;
	private Byte selectedAxes;

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
		this((JRDesignChart) destNode.getValue(), (JRDesignChartAxis) srcNode.getValue(), newIndex, destNode.getJasperDesign());
	}

	public CreateChartAxesCommand(JRDesignChart chart, JRDesignChartAxis chartAxis, int newIndex, JasperDesign jDesign) {
		super();
		this.jrElement = chartAxis;
		this.chart = chart;
		this.jrPlot = (JRDesignMultiAxisPlot) chart.getPlot();
		this.index = newIndex;
		this.jDesign = jDesign;
	}
	
	public void setSelectedAxes(Byte selectedAxes){
		this.selectedAxes = selectedAxes;
	}

	/**
	 * Creates the object.
	 */
	protected void createObject() {
		if (jrElement == null) {
			if(selectedAxes==null) {
				// need to trigger a wizard for selecting a proper chart axis
				ChartAxesWizard wizard = new ChartAxesWizard();
				WizardDialog dialog = new WizardDialog(Display.getDefault().getActiveShell(), wizard);
				dialog.create();
				if (dialog.open() == Dialog.OK) {
					selectedAxes = wizard.getChartAxis();
				}
			}
			if(selectedAxes!=null) {
				jrElement = new JRDesignChartAxis(this.chart);
				JRDesignChart c = MChart.createJRElement(jDesign, selectedAxes, true);
				jrElement.setChart(c);
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
			if (index >= 0 && index < jrPlot.getAxes().size())
				jrPlot.addAxis(index, jrElement);
			else
				jrPlot.addAxis(jrElement);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.gef.commands.Command#canUndo()
	 */
	@Override
	public boolean canUndo() {
		if (jrPlot == null || jrElement == null)
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
		jrPlot.removeAxis(jrElement);
	}

}
