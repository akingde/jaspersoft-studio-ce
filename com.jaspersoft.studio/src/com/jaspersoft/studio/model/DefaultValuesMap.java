package com.jaspersoft.studio.model;

import java.awt.Color;
import java.util.HashMap;

import net.sf.jasperreports.engine.JRCommonText;
import net.sf.jasperreports.engine.JRFont;
import net.sf.jasperreports.engine.JRPen;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.base.JRBaseLineBox;
import net.sf.jasperreports.engine.base.JRBasePen;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.type.FillEnum;
import net.sf.jasperreports.engine.type.HorizontalAlignEnum;
import net.sf.jasperreports.engine.type.LineStyleEnum;
import net.sf.jasperreports.engine.type.RotationEnum;
import net.sf.jasperreports.engine.type.ScaleImageEnum;
import net.sf.jasperreports.engine.type.VerticalAlignEnum;
import net.sf.jasperreports.engine.util.JRProperties;

public class DefaultValuesMap {
	private static HashMap<Class, HashMap<String,Object>> valuesMap = null;
	
	private static final Integer INTEGER_ZERO = Integer.valueOf(0);
	
	private static void initValuesMap(){
		if (valuesMap == null) valuesMap = new HashMap<Class, HashMap<String,Object>>();
	}
	
	@SuppressWarnings("deprecation")
	private static HashMap<String, Object> initializeType(Class type){
		HashMap<String, Object> result = new HashMap<String, Object>();
		result.put(JRDesignStyle.PROPERTY_FONT_NAME, JRProperties.getProperty(JRFont.DEFAULT_FONT_NAME));
		result.put(JRBaseLineBox.PROPERTY_PADDING, INTEGER_ZERO);
		result.put(JRBaseLineBox.PROPERTY_TOP_PADDING, INTEGER_ZERO);
		result.put(JRBaseLineBox.PROPERTY_RIGHT_PADDING, INTEGER_ZERO);
		result.put(JRDesignStyle.PROPERTY_PDF_FONT_NAME, JRProperties.getProperty(JRFont.DEFAULT_PDF_FONT_NAME));
		result.put(JRDesignStyle.PROPERTY_PDF_ENCODING,JRProperties.getProperty(JRFont.DEFAULT_PDF_ENCODING));
		result.put(JRDesignStyle.PROPERTY_PATTERN, null);
		result.put(JRDesignStyle.PROPERTY_MARKUP,JRCommonText.MARKUP_NONE);
		result.put(JRBasePen.PROPERTY_LINE_STYLE, LineStyleEnum.SOLID);
		
		if (type == JRStyle.class){
			result.put(JRDesignStyle.PROPERTY_FONT_SIZE, null);
			result.put(JRDesignStyle.PROPERTY_BOLD, null);
			result.put(JRDesignStyle.PROPERTY_ITALIC, null);
			result.put(JRDesignStyle.PROPERTY_UNDERLINE, null);
			result.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, null);
			result.put(JRDesignStyle.PROPERTY_PDF_EMBEDDED,null);
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
			result.put(JRDesignStyle.PROPERTY_MODE, null);
		} else {
			result.put(JRDesignStyle.PROPERTY_FONT_SIZE, JRProperties.getIntegerProperty(JRFont.DEFAULT_FONT_SIZE));
			result.put(JRDesignStyle.PROPERTY_BOLD, false);
			result.put(JRDesignStyle.PROPERTY_ITALIC, false);
			result.put(JRDesignStyle.PROPERTY_UNDERLINE, false);
			result.put(JRDesignStyle.PROPERTY_STRIKE_THROUGH, false);
			result.put(JRDesignStyle.PROPERTY_PDF_EMBEDDED,JRProperties.getBooleanProperty(JRFont.DEFAULT_PDF_EMBEDDED));
			result.put(JRDesignStyle.PROPERTY_BLANK_WHEN_NULL,false);
			result.put(JRDesignStyle.PROPERTY_VERTICAL_ALIGNMENT,VerticalAlignEnum.TOP);
			result.put(JRDesignStyle.PROPERTY_HORIZONTAL_ALIGNMENT,HorizontalAlignEnum.LEFT);
			result.put(JRDesignStyle.PROPERTY_SCALE_IMAGE,ScaleImageEnum.RETAIN_SHAPE);
			result.put(JRDesignStyle.PROPERTY_ROTATION,RotationEnum.NONE);
			result.put(JRDesignStyle.PROPERTY_RADIUS,INTEGER_ZERO);
			result.put(JRBasePen.PROPERTY_LINE_WIDTH,JRPen.LINE_WIDTH_0);
			result.put(JRBasePen.PROPERTY_LINE_COLOR, Color.black);
			result.put(JRDesignStyle.PROPERTY_BACKCOLOR, Color.white);
			result.put(JRDesignStyle.PROPERTY_FORECOLOR, Color.black);
			result.put(JRDesignStyle.PROPERTY_FILL, FillEnum.SOLID);
		}
		return result;
	}
	
	public static HashMap<String, Object> getPropertiesByType(Class type){
		 initValuesMap();
		 HashMap<String, Object> result = valuesMap.get(type);
		 if (result == null){
			 result = initializeType(type);
			 valuesMap.put(type, result);
		 }
		 return result;
	}
	
	public static Object getValue(String key, Class type){
		return getPropertiesByType(type).get(key);
	}
}
