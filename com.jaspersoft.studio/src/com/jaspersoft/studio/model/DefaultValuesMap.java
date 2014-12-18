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
package com.jaspersoft.studio.model;

import java.awt.Color;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.ModeEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRProperties;

import com.jaspersoft.studio.model.image.MImage;
import com.jaspersoft.studio.model.style.MStyle;
import com.jaspersoft.studio.model.text.MTextElement;
import com.jaspersoft.studio.model.text.MTextField;

/**
 * This class store statically the default values of many element
 * @author Marco Orlandin
 *
 */
public class DefaultValuesMap {
	
	/**
	 * Hashmap that cache the properties for every requested type. Using this every request after the 
	 * first for the same type will not rebuild the default values map.
	 */
	private static HashMap<Class<?>, HashMap<String,Object>> valuesMap = null;
	
	private static final Integer INTEGER_ZERO = Integer.valueOf(0);
	
	private static String concatenateProperties(String prop1, String prop2){
		return prop1.concat(".").concat(prop2);
	}
	
	private static void createBaseLinePen(HashMap<String, Object> result, String baseProperty){
		String widthKey = concatenateProperties(baseProperty, JRBasePen.PROPERTY_LINE_WIDTH);
		String colorKey = concatenateProperties(baseProperty, JRBasePen.PROPERTY_LINE_COLOR);
		String styleKey = concatenateProperties(baseProperty, JRBasePen.PROPERTY_LINE_STYLE);
		result.put(colorKey, Color.black);
		result.put(styleKey,LineStyleEnum.SOLID);
		result.put(widthKey,JRPen.LINE_WIDTH_0);
	}
		
	
	private static void createBaseLineBox(HashMap<String, Object> result, String baseProperty){
		String paddingKey = concatenateProperties(baseProperty, JRBaseLineBox.PROPERTY_PADDING);
		String paddingLeftKey = concatenateProperties(baseProperty, JRBaseLineBox.PROPERTY_LEFT_PADDING);
		String paddingRightKey = concatenateProperties(baseProperty, JRBaseLineBox.PROPERTY_RIGHT_PADDING);
		String paddingBottomKey = concatenateProperties(baseProperty, JRBaseLineBox.PROPERTY_BOTTOM_PADDING);
		String paddingTopKey = concatenateProperties(baseProperty, JRBaseLineBox.PROPERTY_TOP_PADDING);
		String penKey = concatenateProperties(baseProperty, MLineBox.LINE_PEN);
		String penLeftKey = concatenateProperties(baseProperty, MLineBox.LINE_PEN_LEFT);
		String penRightKey = concatenateProperties(baseProperty, MLineBox.LINE_PEN_RIGHT);
		String penBottomKey = concatenateProperties(baseProperty, MLineBox.LINE_PEN_BOTTOM);
		String penTopKey = concatenateProperties(baseProperty, MLineBox.LINE_PEN_TOP);
		
		result.put(paddingKey, INTEGER_ZERO);
		result.put(paddingTopKey, INTEGER_ZERO);
		result.put(paddingBottomKey, INTEGER_ZERO);
		result.put(paddingLeftKey, INTEGER_ZERO);
		result.put(paddingRightKey, INTEGER_ZERO);
		
		createBaseLinePen(result, penKey);
		createBaseLinePen(result, penLeftKey);
		createBaseLinePen(result, penRightKey);
		createBaseLinePen(result, penBottomKey);
		createBaseLinePen(result, penTopKey);
	}
	
	/**
	 * Create the default property map for a wide range of object
	 * @param type the type of the element for which the properties are requested
	 * @return the default properties of the requested element
	 */
	@SuppressWarnings("deprecation")
	private static HashMap<String, Object> initializeType(APropertyNode type){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(JRDesignStyle.PROPERTY_FONT_NAME, JRProperties.getProperty(JRFont.DEFAULT_FONT_NAME));
		result.put(JRDesignStyle.PROPERTY_PATTERN, null);
		result.put(JRDesignStyle.PROPERTY_MARKUP,JRCommonText.MARKUP_NONE);
		
		if (type instanceof MStyle){
			result.put(JRDesignStyle.PROPERTY_FONT_SIZE, null);
			result.put(JRDesignStyle.PROPERTY_BOLD, null);
			result.put(JRDesignStyle.PROPERTY_ITALIC, null);
			result.put(JRDesignStyle.PROPERTY_UNDERLINE, null);
			result.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, null);
			result.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,null);
			result.put(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT,null);
			result.put(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT,null);
			result.put(JRDesignStyle.PROPERTY_SCALE_IMAGE,null);
			result.put(JRDesignStyle.PROPERTY_ROTATION,null);
			result.put(JRDesignStyle.PROPERTY_RADIUS,null);
			result.put(JRBasePen.PROPERTY_LINE_WIDTH, null);
			result.put(JRBasePen.PROPERTY_LINE_COLOR, null);
			result.put(JRDesignStyle.PROPERTY_BACKCOLOR, null);
			result.put(JRDesignStyle.PROPERTY_FORECOLOR, null);
			result.put(JRDesignStyle.PROPERTY_FILL, null);
			result.put(JRDesignStyle.PROPERTY_MODE, ModeEnum.OPAQUE.getValue());
			createBaseLinePen(result, MLineBox.LINE_PEN);
			createBaseLineBox(result, MGraphicElementLineBox.LINE_BOX);
		} else {
			result.put(JRDesignStyle.PROPERTY_BACKCOLOR, Color.white);
			result.put(JRDesignStyle.PROPERTY_FORECOLOR, Color.black);
			ModeEnum transparency = ModeEnum.getByValue(ModeEnum.OPAQUE.getValue());
			
			if (type instanceof MGraphicElementLinePen){
				createBaseLinePen(result, MLineBox.LINE_PEN);
			} else if (type instanceof MGraphicElementLineBox){
				createBaseLineBox(result, MGraphicElementLineBox.LINE_BOX);
			}
			
			if (type instanceof MTextElement){
				result.put(JRDesignStyle.PROPERTY_FONT_SIZE, JRProperties.getIntegerProperty(JRFont.DEFAULT_FONT_SIZE));
				result.put(JRDesignStyle.PROPERTY_BOLD, false);
				result.put(JRDesignStyle.PROPERTY_ITALIC, false);
				result.put(JRDesignStyle.PROPERTY_UNDERLINE, false);
				result.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, false);
				result.put(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT,VerticalAlignEnum.TOP);
				result.put(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT,HorizontalAlignEnum.LEFT);
				result.put(JRDesignStyle.PROPERTY_ROTATION,RotationEnum.NONE);
				transparency = ModeEnum.getByValue(ModeEnum.TRANSPARENT.getValue());
			}
			
			if (type instanceof MTextField){
				result.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,false);
			}
			
			if (type instanceof MRectangle){
				result.put(JRDesignStyle.PROPERTY_FILL, FillEnum.SOLID);
				result.put(JRDesignStyle.PROPERTY_RADIUS,INTEGER_ZERO);
			}
			
			if (type instanceof MImage){
				result.put(JRDesignStyle.PROPERTY_SCALE_IMAGE,ScaleImageEnum.RETAIN_SHAPE);
				result.put(JRDesignStyle.PROPERTY_FILL, FillEnum.SOLID);
			}
			
			result.put(JRDesignStyle.PROPERTY_MODE, transparency);
		}
		return result;
	}
	
	/**
	 * Return all the property for an element with a requested type
	 * @param type the type of the element
	 * @return An hashmap of the default property, where the key is the property name and the 
	 * value is an object representing the property value
	 */
	public static HashMap<String, Object> getPropertiesByType(APropertyNode element){
		if (valuesMap == null) valuesMap = new HashMap<Class<?>, HashMap<String,Object>>();;
		 //Check the requested properties are cached
		 HashMap<String, Object> result = valuesMap.get(element.getClass());
		 if (result == null){
			 //The are not, so i need to create them
			 result = initializeType(element);
			 valuesMap.put(element.getClass(), result);
		 }
		 return result;
	}
}
