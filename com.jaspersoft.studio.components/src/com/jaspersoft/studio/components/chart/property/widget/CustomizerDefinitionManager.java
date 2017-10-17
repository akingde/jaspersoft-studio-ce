/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.chart.property.widget;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.chart.preferences.ChartCustomizerPreferencePage;
import com.jaspersoft.studio.components.chart.property.descriptor.ChartCustomizerDefinition;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.manager.StandardJSONWidgetsDescriptorResolver;
import com.jaspersoft.studio.widgets.framework.manager.WidgetsDefinitionManager;

/**
 * The manager used to load the chart customizer definition defined in the preference page
 * 
 * @author Orlandin Marco
 *
 */
public class CustomizerDefinitionManager {
	
	/**
	 * Cache of the loaded {@link CustomizerWidgetsDescriptor}, the customizer are loaded for each {@link JasperReportsConfiguration}
	 */
	private static HashMap<JasperReportsConfiguration, List<CustomizerWidgetsDescriptor>> customizers = new HashMap<JasperReportsConfiguration, List<CustomizerWidgetsDescriptor>>();
	
	static {
		//Listener used to clear the cache when something about the chart customizer change in the preferences
		JaspersoftStudioPlugin.getInstance().getPreferenceStore().addPropertyChangeListener(new IPropertyChangeListener() {
			
			@Override
			public void propertyChange(PropertyChangeEvent event) {
				if (event.getProperty().equals(ChartCustomizerPreferencePage.CHARTCUSTOMIZER)){
					customizers.clear();
				}
			}
		});
	}
	
	/**
	 * Return the list of {@link CustomizerWidgetsDescriptor} associated to a {@link JasperReportsConfiguration}. First 
	 * look in the cache, if they are not found they are loaded from the preferences and then stored in the cache
	 * 
	 * @param jConfig the {@link JasperReportsConfiguration} of the report requesting the customizers
	 * @return a not null list of {@link CustomizerWidgetsDescriptor}
	 */
	public static List<CustomizerWidgetsDescriptor> getCustomizerDefinitions(JasperReportsConfiguration jConfig){
		List<CustomizerWidgetsDescriptor> definitions = customizers.get(jConfig);
		
		if (definitions == null){
			definitions = new ArrayList<CustomizerWidgetsDescriptor>();
			StandardJSONWidgetsDescriptorResolver resolver = new StandardJSONWidgetsDescriptorResolver(CustomizerWidgetsDescriptor.class);
			
			//Create the hardcoded definitions
			String folder = "/resources/customizers";
			try{
				File resourceFolder = new File(Activator.getDefault().getFileLocation(folder));
				for(File definition : resourceFolder.listFiles()){
					CustomizerWidgetsDescriptor loadedDecriptor = (CustomizerWidgetsDescriptor)WidgetsDefinitionManager.getWidgetsDefinition(jConfig, definition.getAbsolutePath(), resolver);
					if (loadedDecriptor != null){
						definitions.add(loadedDecriptor);
					}
				}
			} catch (Exception ex){
				Activator.getDefault().logError(ex);
			}
			
			String cList = jConfig.getProperty(ChartCustomizerPreferencePage.CHARTCUSTOMIZER);
			if (cList != null){
				for (String line : cList.split("\n")) {
					if (line.isEmpty())
						continue;
					try {
						String customizerPath = new String(Base64.decodeBase64(line));
						CustomizerWidgetsDescriptor loadedDecriptor = (CustomizerWidgetsDescriptor)WidgetsDefinitionManager.getWidgetsDefinition(jConfig, customizerPath, resolver);
						if (loadedDecriptor != null){
							definitions.add(loadedDecriptor);
						}
					} catch (Exception e) {
						JaspersoftStudioPlugin.getInstance().logError(e);
					}
				}
			}
			customizers.put(jConfig, definitions);
		}
		return definitions;
	}
	
	/**
	 * Create a {@link ChartCustomizerDefinition} from a classname. It search in the customizer defined in the preferences
	 * to find one that provide that class, if found it build a {@link ChartCustomizerDefinition} that provide also a UI, otherwise
	 * it build a {@link ChartCustomizerDefinition} for a raw class
	 * 
	 * @param className the searched customizer class, must be not null
	 * @param key the key that will be used in the returned {@link ChartCustomizerDefinition}. it will be something like "chartcustomizer.customizerX." 
	 * where X is an unique associated to the properties of this specific customizer
	 * @param jConfig the {@link JasperReportsConfiguration} of the report requesting the customizer
	 * @return a not null {@link ChartCustomizerDefinition}
	 */
	public static ChartCustomizerDefinition getCustomizerDefinition(String className, String key, JasperReportsConfiguration jConfig){
		List<CustomizerWidgetsDescriptor> definitions = getCustomizerDefinitions(jConfig);
		
		for(CustomizerWidgetsDescriptor descriptor : definitions){
			if (descriptor.getCustomizerClass().equals(className)){
				return new ChartCustomizerDefinition(descriptor, key);
			}
		}
		return new ChartCustomizerDefinition(className, key, true);
	}
}
