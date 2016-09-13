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
import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.components.chart.property.descriptor.CustomizerPropertyExpressionsDTO;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.MGraphicElement;
import com.jaspersoft.studio.property.ISetValueCommandProvider;
import com.jaspersoft.studio.property.SetValueCommand;
import com.jaspersoft.studio.property.descriptor.propexpr.PropertyExpressionsDTO;

import net.sf.jasperreports.chartcustomizers.ConfigurableChartCustomizer;
import net.sf.jasperreports.chartcustomizers.ProxyChartCustomizer;
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
			SetValueCommand setDTOCommand = new SetValueCommand();
			setDTOCommand.setTarget(source);
			setDTOCommand.setPropertyId(propertyId);
			setDTOCommand.setPropertyValue(currentDTO);
			command.add(setDTOCommand);
			int customizersNumber = currentDTO.getCustomizersNumber();
			if (customizersNumber == 1){
				ChartCustomizerDefinition definition = currentDTO.getDefinedCustomizers().get(0);
				boolean requireProxyCustomizer = false;
				if (definition.isOnlyClass()){
					try{
						Class<?> customizerClass = JRClassLoader.loadClassForName(definition.getCustomizerClass());
						requireProxyCustomizer = (ConfigurableChartCustomizer.class.isAssignableFrom(customizerClass));
					}catch (Exception ex){
						requireProxyCustomizer = true;
					}
				} else {
					requireProxyCustomizer = true;		
				}
				if (requireProxyCustomizer){
					SetValueCommand setCustomizer = new SetValueCommand();
					setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
					setCustomizer.setPropertyValue(ProxyChartCustomizer.class.getName());
					setCustomizer.setTarget((APropertyNode)source);
					command.add(setCustomizer);
				} else {
					//Set the customizer selected as main customizer
					SetValueCommand setCustomizer = new SetValueCommand();
					setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
					setCustomizer.setPropertyValue(definition.getCustomizerClass());
					setCustomizer.setTarget((APropertyNode)source);
					command.add(setCustomizer);
					currentDTO.deleteCustomizer(definition.getKey(), true);
				}
			} else if (customizersNumber > 1){
				SetValueCommand setCustomizer = new SetValueCommand();
				setCustomizer.setPropertyId(JRDesignChart.PROPERTY_CUSTOMIZER_CLASS);
				setCustomizer.setPropertyValue(ProxyChartCustomizer.class.getName());
				setCustomizer.setTarget((APropertyNode)source);
				command.add(setCustomizer);
				String currentCustomizer = currentDTO.getPnode().getValue().getCustomizerClass();
				if (currentCustomizer != null && !currentCustomizer.equals(ProxyChartCustomizer.class.getName())){
					//Migrate the old customizer as a children of the proxy one
					currentDTO.createCustomizerEntry(currentCustomizer, true);
				}
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
