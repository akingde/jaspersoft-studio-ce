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
package com.jaspersoft.studio.components.chart.model.command;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.jasperreports.chartcustomizers.ProxyChartCustomizer;
import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.SetValueCommand;

import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElement;

/**
 * SetValueCommandProvider for the chart. When a set value command is done for a chart element
 * is is checked if it is a customizer property. If it is a customizer set. If it is a customizer
 * set it check the number of customizers and if it is zero remove the property for the proxy customizer
 * otherwise it add it.
 * 
 * @author Orlandin Marco
 *
 */
public class ChartSetValueCommandProvider implements ISetValueCommandProvider {

	/**
	 * Since this class can be used without be instanced everytime, this static instance can recalled when 
	 * needed
	 */
	public static ChartSetValueCommandProvider INSTANCE = new ChartSetValueCommandProvider();
	
	@Override
	public Command getSetValueCommand(IPropertySource source, String commandName, Object propertyId, Object newVal) { 
		if (isCustomizerProperty(propertyId)){
			CustomizerPropertyExpressionsDTO currentDTO = (CustomizerPropertyExpressionsDTO)newVal;
			JSSCompoundCommand command = new JSSCompoundCommand("Set Chart Customizers", (ANode)source);
			SetValueCommand setDTOCommand = new SetValueCommand();
			setDTOCommand.setTarget(source);
			setDTOCommand.setPropertyId(propertyId);
			setDTOCommand.setPropertyValue(newVal);
			command.add(setDTOCommand);
			int customizersNumber = currentDTO.getCustomizerNumber();
			if (customizersNumber > 0){
				SetValueCommand setCustomizer = new SetValueCommand();
				setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
				setCustomizer.setPropertyValue(ProxyChartCustomizer.class.getName());
				setCustomizer.setTarget((APropertyNode)source);
				command.add(setCustomizer);
			} else {
				SetValueCommand setCustomizer = new SetValueCommand();
				setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
				setCustomizer.setPropertyValue(null);
				setCustomizer.setTarget((APropertyNode)source);
				command.add(setCustomizer);
			}
			return command;
 		}  else {
			SetValueCommand setCommand = new SetValueCommand(commandName);
			setCommand.setPropertyId(propertyId);
			setCommand.setTarget(source);
			setCommand.setPropertyValue(newVal);
			return setCommand;
 		}
	}
	
	/**
	 * Return true if the property passed is a property that could affect the chart customizer 
	 * 
	 * @param propertyId the property name
	 */
	protected boolean isCustomizerProperty(Object propertyId){
		return (MChart.CHART_PROPERTY_CUSTOMIZER.equals(propertyId) || 
					JRDesignElement.PROPERTY_PROPERTY_EXPRESSIONS.equals(propertyId) ||
						MGraphicElement.PROPERTY_MAP.equals(propertyId));
	}
}
