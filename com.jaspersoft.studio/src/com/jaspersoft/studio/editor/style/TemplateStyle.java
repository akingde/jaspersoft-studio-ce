package com.jaspersoft.studio.editor.style;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.swt.graphics.RGB;

import com.jaspersoft.studio.property.color.ColorSchemaGenerator;

public class TemplateStyle {
	
	private RGB baseColor;
	
	private String variation;
	
	private HashMap<String, RGB> storedColor;
	
	private HashMap<String, Object> storedProperties;
	
	public TemplateStyle(RGB baseColor, String variation){
		this.baseColor = baseColor;
		this.variation = variation;
		storedProperties = new HashMap<String, Object>();
		storedColor = new HashMap<String, RGB>();
	}
	
	public List<String> getStoredPropertiesName(){
		return new ArrayList<String>(storedProperties.keySet());
	}
	
	public List<String> getStoredColorsName(){
		return new ArrayList<String>(storedColor.keySet());
	}
	
	public void storeColor(String name, RGB color){
		storedColor.put(name, color);
	}
	
	public RGB generateAndStoreColor(String name, int gradation){
		RGB generatedColor = ColorSchemaGenerator.createColor(baseColor, gradation, variation);
		storedColor.put(name, generatedColor);
		return generatedColor;
	}
	
	public RGB generateColor(int gradation){
		RGB generatedColor = ColorSchemaGenerator.createColor(baseColor, gradation, variation);
		return generatedColor;
	}
	
	public void storePropertiy(String name, Object value){
		storedProperties.put(name, value);
	}
	
	public Class<?> getPropertyType(String name){
		if (storedProperties.containsKey(name)){
			return (storedProperties.get(name).getClass());
		}
		return null;
	}
	
	public boolean hasProperty(String name){
		return storedProperties.containsKey(name);
	}
	
	public boolean hasColor(String name){
		return storedColor.containsKey(name);
	}
	
	public Object getProperty(String name){
		return storedProperties.get(name);
	}
	
	public RGB getColor(String name){
		return storedColor.get(name);
	}
	
	
	
}
