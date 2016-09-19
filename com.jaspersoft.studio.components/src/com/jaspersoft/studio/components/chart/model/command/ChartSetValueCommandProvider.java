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

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

import net.sf.jasperreports.engine.NamedChartCustomizer;
import net.sf.jasperreports.engine.design.JRDesignChart;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.util.JRClassLoader;

/**
 * SetValueCommandProvider for the chart. When a set value command is done for a chart element
 * is is checked if it is a customizer property. If it is a customizer and it is set a single customizer
 * check if should or not be set the proxy chart customizer, if it is more then one set the ProxyChartCustomizer,
 * if it is 0 remove every the ProxyChartCustomizer
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
			CustomizerPropertyExpressionsDTO currentDTO = null;
			if (newVal instanceof CustomizerPropertyExpressionsDTO){
				currentDTO = (CustomizerPropertyExpressionsDTO)newVal;
			} else {
				currentDTO = new CustomizerPropertyExpressionsDTO((PropertyExpressionsDTO)newVal);
			}
			JSSCompoundCommand command = new JSSCompoundCommand("Set Chart Customizers", (ANode)source);
			
			//Create the command to set the DTO
			SetValueCommand setDTOCommand = new SetValueCommand();
			setDTOCommand.setTarget(source);
			setDTOCommand.setPropertyId(propertyId);
			setDTOCommand.setPropertyValue(currentDTO);
			command.add(setDTOCommand);
			
			//Check if the other properties need to be updated to support the proxy
			int customizersNumber = currentDTO.getCustomizersNumber();
			if (customizersNumber == 1){
				String currentCustomizer = currentDTO.getCustomizerClassValue();
				if (currentCustomizer != null && !currentCustomizer.trim().isEmpty()){
					//it is set as the old customizer, check if it should be moved to the properties
					boolean isConfigurableCustmizer = false;
					try{
						Class<?> customizerClass = JRClassLoader.loadClassForName(currentCustomizer);
						isConfigurableCustmizer = (NamedChartCustomizer.class.isAssignableFrom(customizerClass));
					}catch (Exception ex){
					}
					
					if (isConfigurableCustmizer){
						//the customizer in the property is a configurable customizer and need to be migrated to be loaded by the proxy one
						//first remove it from the property
						SetValueCommand setCustomizer = new SetValueCommand();
						setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
						setCustomizer.setPropertyValue(null);
						setCustomizer.setTarget((APropertyNode)source);
						command.add(setCustomizer);
						if (currentCustomizer != null && !currentCustomizer.trim().isEmpty()){
							//Migrate the old customizer as a children of the proxy one
							currentDTO.createCustomizerEntry(currentCustomizer, true);
						}
					} else {
						SetValueCommand setCustomizer = new SetValueCommand();
						setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
						setCustomizer.setPropertyValue(currentDTO.getCustomizerClassValue());
						setCustomizer.setTarget((APropertyNode)source);
						command.add(setCustomizer);
					}
				}
				//if the customizer is already on the properties there is nothing to do since the proxy chart customizer
				//will be used by default
			} else if (customizersNumber > 1){
				//remove the any customizer, since the proxy one is used automatically
				SetValueCommand setCustomizer = new SetValueCommand();
				setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
				setCustomizer.setPropertyValue(null);
				setCustomizer.setTarget((APropertyNode)source);
				command.add(setCustomizer);
				
				//check if there is a customizer that should be migrated on the properties to be used among the others
				String currentCustomizer = currentDTO.getPnode().getValue().getCustomizerClass();
				if (currentCustomizer != null && !currentCustomizer.trim().isEmpty()){
					//Migrate the old customizer as a children of the proxy one
					currentDTO.createCustomizerEntry(currentCustomizer, true);
				}		
			}
			return command;
 		}  else {
 			//it is not a property that concern the customizers, create a simple SetValueCommand
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
