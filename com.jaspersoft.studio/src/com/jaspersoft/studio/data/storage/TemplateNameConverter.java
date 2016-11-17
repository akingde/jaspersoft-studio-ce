/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.storage;

import java.io.File;
import java.util.HashMap;

import org.w3c.dom.Node;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.IConversionFilenameProvider;

/**
 * Class to provide a name provider for a template style definition that will
 * be saved into the storage. The name depends also on the template style type.
 * Use the method getConverter to get the converter of a specific type, since the
 * converter are cached and this method handle the caching
 * 
 * @author Orlandin Marco
 *
 */
public class TemplateNameConverter implements IConversionFilenameProvider {

	/**
	 * A shared map where the converter are cached with their type as key
	 */
	private static HashMap<String, TemplateNameConverter> convertersMap = new HashMap<String, TemplateNameConverter>();
	
	/**
	 * The type of the current converter
	 */
	private String templateType;
	
	/**
	 * The bigger index of the files in the storage of the current type
	 */
	private int actualIndex = 0;
	
	/**
	 * Create an instance of the class for a specific type of the template style
	 * 
	 * @param templateType the type of the template style
	 */
	private TemplateNameConverter(String templateType){
		this.templateType = templateType;
		File[] otherStyles = ConfigurationManager.getStorageContent(templateType);
		//Search in the storage of this type the bigger index on the filenames
		for(File otherStyle : otherStyles){
			String name = otherStyle.getName();
			int counterStartPosition = name.lastIndexOf("_");
			int counterEndPosition = name.lastIndexOf(".");
			if (counterStartPosition != -1 && counterEndPosition != -1){
				String counterText = name.substring(counterStartPosition+1, counterEndPosition);
				try{
					int counter = Integer.valueOf(counterText) + 1;
					if (counter > actualIndex) actualIndex = counter;
				} catch(Exception ex){
					
				}
			}
		}
	}
	
	/**
	 * Provide a valid name for a template style configuration
	 * on the file storage
	 */
	@Override
	public String getFileName(Node configurationElementNode) {
		String name = templateType + "_" +actualIndex + ".xml";
		actualIndex++;
		return name;
	}
	
	/**
	 * Return a template name converter for a specific type. If the 
	 * converter was never requested then it is created, cached and return.
	 * Otherwise the converter for the type is return from cache
	 * 
	 * @param templateType the type for the converter
	 * @return a not null template name converter for that type
	 */
	public static TemplateNameConverter getConverter(String templateType){
		TemplateNameConverter converter = convertersMap.get(templateType);
		if (converter == null){
			converter = new TemplateNameConverter(templateType);
			convertersMap.put(templateType, converter);
		}
		return converter;
	}

}
