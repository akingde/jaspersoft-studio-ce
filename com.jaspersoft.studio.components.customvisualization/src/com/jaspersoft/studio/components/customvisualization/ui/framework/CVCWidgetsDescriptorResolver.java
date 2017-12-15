/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.customvisualization.ui.framework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDatasetDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentPropertyDescriptor;
import com.jaspersoft.studio.components.customvisualization.ui.ComponentSectionDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.framework.manager.IWidgetsDescriptorResolver;
import com.jaspersoft.studio.widgets.framework.model.SectionPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetPropertyDescriptor;
import com.jaspersoft.studio.widgets.framework.model.WidgetsDescriptor;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

/**
 * Read a JSON definition file that define the UI of a CVC and build from it a {@link WidgetsDescriptor}.
 * Because of retro compatibility the JSON file is read using the class {@link ComponentDescriptor} and the
 * converted to a {@link WidgetsDescriptor}
 * 
 * @author Orlandin Marco
 *
 */
public class CVCWidgetsDescriptorResolver implements IWidgetsDescriptorResolver {

	@Override
	public WidgetsDescriptor loadDescriptor(JasperReportsConfiguration jConfig, String URL) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(new URL(URL).openStream(), "UTF-8"));
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			ComponentDescriptor loadedValue = mapper.readValue(in, ComponentDescriptor.class);
			return convertValue(loadedValue);
		} catch (JsonParseException e) {
			UIUtils.showError(e);
		} catch (JsonMappingException e) {
			UIUtils.showError(e);
		} catch (IOException e) {
			UIUtils.showError(e);
		} catch (Throwable e) {
			UIUtils.showError(e);
		}
		return null;
	}
	
	public CVCWidgetsDescriptor resolveURL(URL URL) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(URL.openStream(), "UTF-8"));
			mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
			ComponentDescriptor loadedValue = mapper.readValue(in, ComponentDescriptor.class);
			return convertValue(loadedValue);
		} catch (JsonParseException e) {
			UIUtils.showError(e);
		} catch (JsonMappingException e) {
			UIUtils.showError(e);
		} catch (IOException e) {
			UIUtils.showError(e);
		} catch (Throwable e) {
			UIUtils.showError(e);
		}
		return null;
	}
	
	/**
	 * Convert the old {@link ComponentDescriptor} into a new {@link CVCWidgetsDescriptor}
	 * 
	 * @param oldValue a not null {@link ComponentDescriptor}
	 * @return a not null {@link CVCWidgetsDescriptor}
	 */
	public CVCWidgetsDescriptor convertValue(ComponentDescriptor oldValue){
		CVCWidgetsDescriptor result = new CVCWidgetsDescriptor();
		
		result.setLabel(oldValue.getLabel());
		result.setModule(oldValue.getModule());
		result.setDescription(oldValue.getDescription());
		result.setThumbnail(oldValue.getThumbnail());
		
		if (oldValue.getSections() != null){
			for(ComponentSectionDescriptor oldSection : oldValue.getSections()){
				SectionPropertyDescriptor section = convertSection(oldSection);
				result.getSections().add(section);
			}
		}
		
		if (oldValue.getDatasets() != null){
			for(ComponentDatasetDescriptor oldDataset : oldValue.getDatasets()){
				DatasetPropertyDescriptor dataset = new DatasetPropertyDescriptor();
				
				dataset.setCardinality(oldDataset.getCardinality());
				dataset.setLabel(oldDataset.getLabel());
				
				for(ComponentSectionDescriptor oldSection : oldDataset.getSections()){
					SectionPropertyDescriptor section = convertSection(oldSection);
					dataset.getSections().add(section);
				}
				
				result.getDatasets().add(dataset);	
			}
		}
		
		return result;
	}
	
	private SectionPropertyDescriptor convertSection(ComponentSectionDescriptor oldSection){
		SectionPropertyDescriptor section = new SectionPropertyDescriptor();
		section.setName(oldSection.getName());
		section.setExpandable(oldSection.isExpandable());
		
		for(ComponentPropertyDescriptor oldWidget : oldSection.getProperties()){
			WidgetPropertyDescriptor widget = convertWidget(oldWidget);				
			section.getProperties().add(widget);	
		}
		return section;
	}
	
	private WidgetPropertyDescriptor convertWidget(ComponentPropertyDescriptor oldWidget){
		WidgetPropertyDescriptor widget = new WidgetPropertyDescriptor();
		
		widget.setDefaultValue(oldWidget.getDefaultValue());
		widget.setLabel(oldWidget.getLabel());
		widget.setDescription(oldWidget.getDescription());
		widget.setMandatory(oldWidget.isMandatory());
		widget.setMax(oldWidget.getMax());
		widget.setMin(oldWidget.getMin());
		widget.setName(oldWidget.getName());
		widget.setReadOnly(oldWidget.isReadOnly());
		widget.setType(getType(oldWidget));
		widget.setComboOptions(getOptions(oldWidget));
		widget.setFallbackValue(null);
		
		return widget;
	}
	
	protected String getType(ComponentPropertyDescriptor oldWidget){
		if ("color".equals(oldWidget.getType()) && oldWidget.isTransparent()){
			return "transparent_color";
		} else {
			return oldWidget.getType();
		}
	}
	
	protected String[][] getOptions(ComponentPropertyDescriptor oldWidget){
		String[] oldOptions = oldWidget.getOptions();
		if (oldOptions != null){
			String[][] result = new String[oldOptions.length][2];
			for(int i = 0; i < oldOptions.length; i++){
				result[i][0] = oldOptions[i];
				result[i][1] = oldOptions[i];
			}
			return result;
		} else return oldWidget.getOptions2();
	}

	/**
	 * Create the key for a specific definition location information
	 * 
	 * @param jConfig the current {@link JasperReportsConfiguration}
	 * @param url the url of the loaded location
	 * @return an unique key for the resource, it is independent from the {@link JasperReportsConfiguration}
	 */
	public String getKey(JasperReportsConfiguration jConfig, String URL) {
		return URL;
	}
	
	/**
	 * The CVC definitions are not unloaded when the report who requested them
	 * is closed
	 */
	@Override
	public boolean unloadOnConfigurationDispose() {
		return false;
	}
}
