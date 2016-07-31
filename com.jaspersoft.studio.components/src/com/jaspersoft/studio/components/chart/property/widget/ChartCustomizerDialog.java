/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.ChartCustomizerWidget;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

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
			
			@Override
			protected void buildUI(WidgetsDescriptor cd, String customizerKey) {
				super.buildUI(cd, customizerKey);
				Point size = parent.computeSize(SWT.DEFAULT, SWT.DEFAULT);
				size.x = Math.max(getShell().getSize().x, size.x);
				getShell().setSize(size.x, size.y);
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
