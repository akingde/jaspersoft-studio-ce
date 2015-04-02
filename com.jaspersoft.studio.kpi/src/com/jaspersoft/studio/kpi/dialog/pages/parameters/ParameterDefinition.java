package com.jaspersoft.studio.kpi.dialog.pages.parameters;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParameterDefinition {

	private static final List<String> parameterTypes = new ArrayList<String>();
	
	private static final HashMap<String, String> parameterJavaTypes = new HashMap<String, String>();
	
	static{
		parameterTypes.add("text");
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "java.lang.String");
		parameterTypes.add("date");
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "java.util.Date");
		parameterTypes.add("number");
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "java.lang.Number");
		parameterTypes.add("daterange");
		parameterJavaTypes.put(parameterTypes.get(parameterTypes.size()-1), "net.sf.jasperreports.types.date.DateRange");
	}
	
	private String name;
	
	private String type;
	
	private String expression;
	
	public ParameterDefinition(String name, String type, String expression){
		this.name = name;
		this.type = type;
		this.expression = expression;
	}
	
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getExpression() {
		return expression;
	}

	public static List<String> getParameterTypes(){
		return parameterTypes;
	}
	
	public static String getParameterJavaType(String parameterType){
		String javaType =  parameterJavaTypes.get(parameterType);
		if (javaType == null) javaType = "java.lang.String";
		return javaType;
	}
	
	public static String getParameterType(String javaType){
		for(Map.Entry<String,String> type : parameterJavaTypes.entrySet()){
			if (type.getValue().equals(javaType)) return type.getKey();
		}
		return javaType;
	}
}
