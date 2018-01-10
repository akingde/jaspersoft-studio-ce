/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.descriptor;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.widget.ChartCustomizerCellEditor;
import com.jaspersoft.studio.components.chart.property.widget.SPChartCustomizer;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;

/**
 * Descriptor used to define the controls to handle the chart customizer property both in 
 * the properties page and in the advance one
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerPropertyDescriptor extends PropertyDescriptor implements IPropertyDescriptorWidget {

	/**
	 * The label provider used in the advanced property page to show the label entry. 
	 * It count the number of chart customizers; It is static so it can be accessed from outside
	 */
	public static ILabelProvider labelProvider = new LabelProvider() {
		
		public String getText(Object element) {
			if (element instanceof MChart){
				element = ((MChart)element).getPropertyValue(MChart.CHART_PROPERTY_CUSTOMIZER);
			}
			CustomizerPropertyExpressionsDTO currentDTO = (CustomizerPropertyExpressionsDTO)element;
			if (currentDTO != null){	
				return "Chart Customizers: " + currentDTO.getCustomizersNumber();
			}
			return "Chart Customizers: 0";
		};
		
	};
		
	
	public CustomizerPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	public ASPropertyWidget<?> createWidget(Composite parent, AbstractSection section) {
		return new SPChartCustomizer(parent, section, this);
	}
	
	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		return new ChartCustomizerCellEditor(parent);
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		return labelProvider;
	}
}
