/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.ChartCustomizerWidget;
import com.jaspersoft.studio.model.APropertyNode;

/**
 * Dialog to show the widget handle the chart customizer
 * 
 * @author Orlandin Marco
 *
 */
public class ChartCustomizerDialog extends TitleAreaDialog {
	
	/**
	 * The widget where the controls are placed
	 */
	private ChartCustomizerWidget widget;
	
	/**
	 * The chart model
	 */
	private MChart chart;
	
	/**
	 * The edited {@link CustomizerPropertyExpressionsDTO}
	 */
	private CustomizerPropertyExpressionsDTO currentDTO;

	/**
	 * Create the dialog
	 * 
	 * @param parentShell the dialog shell 
	 * @param chart the model of the edited chart, must be not null
	 * @param currentDTO  The edited {@link CustomizerPropertyExpressionsDTO}, must be not null and it should be
	 * a copy of the original DTO
	 */
	public ChartCustomizerDialog(Shell parentShell, MChart chart, CustomizerPropertyExpressionsDTO currentDTO) {
		super(parentShell);
		this.chart = chart;
		this.currentDTO = currentDTO;
	}

	@Override
	protected Control createDialogArea(final Composite parent) {
		setTitle("Configure the chart customizers");
		setMessage("Define or configure the chart customizers used by this chart");
		widget = new ChartCustomizerWidget(parent){
			@Override
			public void changePropertyOn(String property, CustomizerPropertyExpressionsDTO value, APropertyNode target) {
				
			}
		};
		widget.update(chart, currentDTO);
		return widget.getControl();
	}
	
	/**
	 * Return the DTO edited in the dialog
	 *  
	 * @return a not null DTO edited in the dialog
	 */
	public CustomizerPropertyExpressionsDTO getDTO(){
		return widget.getPropertyDTO();
	}
	
}
