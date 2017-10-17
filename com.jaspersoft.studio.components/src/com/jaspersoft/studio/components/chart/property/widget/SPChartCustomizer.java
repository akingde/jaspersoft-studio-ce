/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyDescriptor;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.components.chart.wizard.fragments.data.widget.ChartCustomizerWidget;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;

/**
 * The ASPropertyWidget used to handle a chart customizer. It simply set the data
 * in the widgets used to handle graphically the customizers
 * 
 * @author Orlandin Marco
 *
 */
public class SPChartCustomizer extends ASPropertyWidget<CustomizerPropertyDescriptor>  {
	
	/**
	 * The widgets with all the controls to handle the customizer
	 */
	private ChartCustomizerWidget widget;
	
	public SPChartCustomizer(Composite parent, AbstractSection section, CustomizerPropertyDescriptor pDescriptor) {
		super(parent, section, pDescriptor);
	}
	
	@Override
	protected void createComponent(Composite parent) {
		widget = new ChartCustomizerWidget(parent){
			
			@Override
			public void changePropertyOn(String property, CustomizerPropertyExpressionsDTO value, APropertyNode target) {
				section.changePropertyOn(property, value, target);
			}
		};
	}

	@Override
	public void setData(APropertyNode pnode, Object b) {
		if (b != null){
			MChart chart = (MChart)pnode;
			widget.update(chart, (CustomizerPropertyExpressionsDTO)b);
		}
	}
	
	@Override
	public Control getControl() {
		return widget.getControl();
	}
}
