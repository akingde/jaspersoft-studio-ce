package com.jaspersoft.studio.data.storage;

import java.io.File;
import java.util.HashMap;

import org.w3c.dom.Node;

import com.jaspersoft.studio.ConfigurationManager;
import com.jaspersoft.studio.IConversionFilenameProvider;

public class TemplateNameConverter implements IConversionFilenameProvider {

	private static HashMap<String, TemplateNameConverter> convertersMap = new HashMap<String, TemplateNameConverter>();
	
	private String templateType;
	
	private int actualIndex = 0;
	
	private TemplateNameConverter(String templateType){
		this.templateType = templateType;
		File[] otherStyles = ConfigurationManager.getStorageContent(templateType);
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
	
	@Override
	public String getFileName(Node configurationElementNode) {
		String name = templateType + "_" +actualIndex + ".xml";
		actualIndex++;
		return name;
	}
	
	public static TemplateNameConverter getConverter(String templateType){
		TemplateNameConverter converter = convertersMap.get(templateType);
		if (converter == null){
			converter = new TemplateNameConverter(templateType);
			convertersMap.put(templateType, converter);
		}
		return converter;
	}

}
