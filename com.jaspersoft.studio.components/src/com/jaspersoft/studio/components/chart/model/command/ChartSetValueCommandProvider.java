/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.model.command;

import java.util.List;

import org.eclipse.gef.commands.Command;
import org.eclipse.ui.views.properties.IPropertySource;

import com.jaspersoft.studio.JSSCompoundCommand;
import com.jaspersoft.studio.components.chart.model.MChart;
import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
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
				currentDTO = new CustomizerPropertyExpressionsDTO((PropertyExpressionsDTO)newVal, (MChart)source);
			}
			JSSCompoundCommand command = new JSSCompoundCommand("Set Chart Customizers", (ANode)source);
			
			//Create the command to set the DTO
			SetValueCommand setDTOCommand = new SetValueCommand();
			setDTOCommand.setTarget(source);
			setDTOCommand.setPropertyId(propertyId);
			setDTOCommand.setPropertyValue(currentDTO);
			command.add(setDTOCommand);
			
			List<ChartCustomizerDefinition> definedCustmizers = currentDTO.getDefinedCustomizers();
			if (definedCustmizers.isEmpty()){
				//no customizers defined, delete the class from the chart
				SetValueCommand setCustomizer = new SetValueCommand();
				setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
				setCustomizer.setPropertyValue(null);
				setCustomizer.setTarget((APropertyNode)source);
				command.add(setCustomizer);
			} else if (definedCustmizers.size() == 1){
				//there is a customizer, look if it should be set as property or as class 
				ChartCustomizerDefinition definedCustomizer = definedCustmizers.get(0);
				if (isConfigurableCustmizer(definedCustomizer)){
					//must be set as a property, remove the class customizer
					SetValueCommand setCustomizer = new SetValueCommand();
					setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
					setCustomizer.setPropertyValue(null);
					setCustomizer.setTarget((APropertyNode)source);
					command.add(setCustomizer);
					//if the customizer wasn't already a property migrate it
					if (!definedCustomizer.isPropertiesCustomizer()){
						currentDTO.createCustomizerEntry(definedCustomizer.getCustomizerClass(), true);
					}
				} else {
					//it can be set as a customizer class
					SetValueCommand setCustomizer = new SetValueCommand();
					setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
					setCustomizer.setPropertyValue(definedCustomizer.getCustomizerClass());
					setCustomizer.setTarget((APropertyNode)source);
					command.add(setCustomizer);
					//if it was set as a property in the current dto remove it to avoid duplication
					if (definedCustomizer.isPropertiesCustomizer()){
						currentDTO.deleteCustomizer(definedCustomizer, true);
					}
				}
			} else if (definedCustmizers.size() > 1){
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
	 * Check if a customizer definition provide a ConfigurableCustomizer or an old customizer
	 * 
	 * @param definition the definition to check, must be not null
	 * @return true if the definition point to a ConfigurableCustomizer, false otherwise. 
	 */
	protected boolean isConfigurableCustmizer(ChartCustomizerDefinition definition){
		if (definition.isOnlyClass()){
			//I have only the class, check if it is Configurable customizer
			String classCustomizer = definition.getCustomizerClass();
			boolean isConfigurableCustmizer = false;
			try{
				Class<?> customizerClass = JRClassLoader.loadClassForName(classCustomizer);
				isConfigurableCustmizer = (NamedChartCustomizer.class.isAssignableFrom(customizerClass));
			}catch (Exception ex){
			}
			return isConfigurableCustmizer;
		} return true;
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
