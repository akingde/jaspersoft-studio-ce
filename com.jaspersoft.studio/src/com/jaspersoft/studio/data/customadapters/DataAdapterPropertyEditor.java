/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.customadapters;

import java.beans.PropertyChangeSupport;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JRExpression;

/**
 * {@link IPropertyEditor} used to set and get properties from a generic {@link DataAdapter}. 
 * If it is defined and it support a specific property it will use and {@link IAdapterPropertyHandler} defined
 * inside the data adapter configuration file, otherwise it will try to use the reflection
 */
public class DataAdapterPropertyEditor implements IPropertyEditor {

	/**
	 * The modified data adapter
	 */
	private DataAdapter handledAdapter = null;
	
	/**
	 * The property change support, to notify changes in the data adapter
	 */
	private PropertyChangeSupport changeSupport = null;
	
	/**
	 * The map of the setters, where the key is the method name
	 */
	private Map<String, List<Method>> methodsSettersMap = new HashMap<String, List<Method>>();
	
	/**
	 * The map of the getters, where the key is the method name
	 */
	private Map<String, List<Method>> methodsGettersMap = new HashMap<String, List<Method>>();
	
	/**
	 * Object used to set/get the data adapter properties without use the reflection
	 */
	private IAdapterPropertyHandler adapterPropertyHandler = null;
	
	/**
	 * Static map to convert primitive types to their wrapper
	 */
	private final static Map<Class<?>, Class<?>> primitiveMap = new HashMap<Class<?>, Class<?>>();
	
	static {
		primitiveMap.put(boolean.class, Boolean.class);
		primitiveMap.put(byte.class, Byte.class);
		primitiveMap.put(short.class, Short.class);
		primitiveMap.put(char.class, Character.class);
	    primitiveMap.put(int.class, Integer.class);
	    primitiveMap.put(long.class, Long.class);
	    primitiveMap.put(float.class, Float.class);
	    primitiveMap.put(double.class, Double.class);
	}
	
	/**
	 * Set to null the property of the data adapter, if there is an {@link IAdapterPropertyHandler} and
	 * the property is handled by it then it will be used. Otherwise the reflection will be used to call
	 * the first method which has as name set + name of the property and accept one parameter, and the
	 * parameter will be null
	 * 
	 * @param propertyName the name of the property to set, must be not null
	 */
	@Override
	public void removeProperty(String propertyName) {
		if (isSupportedByHandler(propertyName)) {
			adapterPropertyHandler.removeProperty(propertyName, handledAdapter);
			changeSupport.firePropertyChange("dirty", false, true);
		} else {
			List<Method> method = methodsSettersMap.get("set" + propertyName.toLowerCase());
			if (method != null && !method.isEmpty()) {
				try {
					method.get(0).invoke(handledAdapter, new Object[] {null});
					changeSupport.firePropertyChange("dirty", false, true);
				} catch (Exception e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
			}
		}
	}
	
	/**
	 * Invoke a getter methods between the ones passed as parameters. If there is between them
	 * a method then that return a string that one will be used. Otherwise will be used the first
	 * method in the list, if it return a type array or collection it will be converted into the json definition of
	 * an array, otherwise will be returned the result of the toString of whatever it return (if not null)
	 * 
	 * @param methods a not null list of methods where the one to invoke is searched
	 * @return the result of the invocation, can be null
	 */
	protected String invokeGetMethod(List<Method> methods) {
		try {
			//search for a string getter
			for(Method method : methods) {
				if (String.class.isAssignableFrom(method.getReturnType())) {
					return (String)method.invoke(handledAdapter);
				}
			}
			Method firstGetter = methods.get(0);
			if (Collection.class.isAssignableFrom(firstGetter.getReturnType()) || Array.class.isAssignableFrom(firstGetter.getReturnType())) {
				ObjectMapper mapper = new ObjectMapper();
				try {
					return mapper.writeValueAsString(firstGetter.invoke(handledAdapter));
				} catch (JsonProcessingException e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				}
			} else {
				Object value = methods.get(0).invoke(handledAdapter);
				return value != null ? value.toString() : null; 
			}
		} catch (Exception e) {
			e.printStackTrace();
			JaspersoftStudioPlugin.getInstance().logError(e);
		}
		return null;
	}
	
	/**
	 * return the property of the data adapter, if there is an {@link IAdapterPropertyHandler} and
	 * the property is handled by it then it will be used. Otherwise the reflection will be used to call
	 * the first method which has as name get + name of the property, using the invokeGetMethod method.
	 * If not get + name method is found it will be searched also the is + name
	 * 
	 * @param propertyName the name of the property to get, must be not null
	 */
	@Override
	public String getPropertyValue(String propertyName) {
		if (isSupportedByHandler(propertyName)) {
			adapterPropertyHandler.getPropertyValue(propertyName, handledAdapter);
		} else {
			//check for a method staring with get
			List<Method> methods = methodsGettersMap.get("get" + propertyName.toLowerCase());
			if (methods != null && !methods.isEmpty()) {
				return invokeGetMethod(methods);
			}
			//check for method starting with is
			methods = methodsGettersMap.get("is" + propertyName.toLowerCase());
			if (methods != null && !methods.isEmpty()) {
				return invokeGetMethod(methods);
			}
		}
		return null;
	}
	
	/**
	 * Build a list from a JSON definition of it as string, with a specified inner type
	 * 
	 * @param values
	 * @param listInnerClass
	 * @return
	 */
	private List<?> buildList(String values, Class<?> listInnerClass){
		ObjectMapper mapper = new ObjectMapper();
		String[] stringValues = new String[0];
		//deserialize the json array
		if (values != null && !values.isEmpty()) {
			try {
				stringValues = mapper.readValue(values, String[].class);
			} catch (Exception ex){
				ex.printStackTrace();
			}
		}
		if (listInnerClass.isAssignableFrom(List.class)) {
			//recursive case, it is a list of list, use the first parameter of the deserialized json array to build each nested list
			Class<?> persistentClass = (Class<?>) ((ParameterizedType)listInnerClass.getGenericSuperclass()).getActualTypeArguments()[0];
			List<List<?>> result = new ArrayList<List<?>>();
			for(String stringValue : stringValues) {
				result.add(buildList(stringValue, persistentClass));
			}
			return result;
		}  else {
			//it is a simple list, use each element of the json array to build an element of the list
			List<Object> result = new ArrayList<Object>();
			for(String stringValue : stringValues) {
				Object content = buildSimpleType(stringValue, listInnerClass);
				if (content != null) {
					result.add(content);
				}
			}
			return result;
		}
	}
	
	/**
	 * Try to build an object of the specified type from a string. To do so it is searched in the specified
	 * type a constructor that work with a string and in case it is used to build and return the object. Otherwise
	 * will be searched the first constructor with one parameter whose parameter can be built from a string. This will
	 * iterated recursively until every constructor has been checked or one that work with string is found
	 * 
	 * @param value the value as string
	 * @param type the type to build
	 * @return the object of the requested type or null if it can in any way be build from a string
	 */
	private Object buildSimpleType(String value, Class<?> type) {
		//Search a constructor that work with string
		for(Constructor<?> constructor :  type.getConstructors()) {
			if (constructor.getParameterTypes().length == 1 && constructor.getParameterTypes()[0].isAssignableFrom(String.class)) {
				try {
					return constructor.newInstance(value);
				} catch (Exception e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				} 
			}
		}
		//Search a constructor whose parameter can be build from string
		for(Constructor<?> constructor :  type.getConstructors()) {
			if (constructor.getParameterTypes().length == 1) {
				try {
					Object constructorParameter = buildSimpleType(value, constructor.getParameters()[0].getClass());
					if (constructorParameter != null) {
						return constructor.newInstance(constructorParameter);
					}
				} catch (Exception e) {
					e.printStackTrace();
					JaspersoftStudioPlugin.getInstance().logError(e);
				} 
			}
		}
		return null;
	}
	
	/**
	 * set a property inside the data adapter, using the property name. If a {@link IAdapterPropertyHandler} is 
	 * defined and it support the property then it will be used, otherwise a method with set + property name
	 * will be searched and will be called.
	 * 
	 * @param propertyName the name of the property, must be not null
	 * @param value the value of the property
	 * @param valueExpression not used since the DA doesn't support expressions
	 */
	@Override
	public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
		if (isSupportedByHandler(propertyName)) {
			adapterPropertyHandler.setPropertyValue(propertyName, value, handledAdapter);
			changeSupport.firePropertyChange("dirty", false, true);
		} else {
			List<Method> methods = methodsSettersMap.get("set" + propertyName.toLowerCase());
			if (methods != null) {
				try {
					//search first for a string setter and use that if found
					for(Method method : methods) {
						if (String.class.isAssignableFrom(method.getParameterTypes()[0])) {
							method.invoke(handledAdapter, value);
							changeSupport.firePropertyChange("dirty", false, true);
							return;
						}
					}
					//Search a method whose parameter can be built from a string
					for(Method method : methods) {
						//if it accept a primitive type convert to its wrapper class
						Class<?> parameterClass = method.getParameterTypes()[0];
						if (primitiveMap.containsKey(parameterClass)) {
							parameterClass = primitiveMap.get(parameterClass);
						} 
						try {
							if (parameterClass.isAssignableFrom(List.class) || parameterClass.isAssignableFrom(Collection.class)) {
								//try to get the list type
								Class<?> persistentClass = null;
								if (parameterClass.getGenericSuperclass() != null) {
									persistentClass = (Class<?>) ((ParameterizedType)parameterClass.getGenericSuperclass()).getActualTypeArguments()[0];
								} else {
									ParameterizedType type = (ParameterizedType)method.getGenericParameterTypes()[0];
									persistentClass = (Class<?>)type.getActualTypeArguments()[0];
								}
								Object parameter = buildList(value, persistentClass);
								if (parameter != null) {
									method.invoke(handledAdapter, parameter);
									changeSupport.firePropertyChange("dirty", false, true);
									return;
								}
							} else {
								Object parameter = buildSimpleType(value, parameterClass);
								if (parameter != null) {
									method.invoke(handledAdapter, parameter);
									changeSupport.firePropertyChange("dirty", false, true);
									return;
								}
							}
						} catch (Exception ex){
							ex.printStackTrace();
						}
						
					}
					throw new Exception("No setter found for property " + propertyName);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * Set the handled data adapter, this will rebuild also the getters and setters methods map. Will be searched
	 * all the method that start with get or is and have a not void return to create the getters map. Will be
	 * searched all the method that start with set and accept a single parameter to create the setters map.
	 * 
	 * @param handledAdapter The new data adapter to handle, must be not null
	 * @param changeSupport the {@link PropertyChangeSupport} where changes to the data adapter are notified
	 */
	public void setDataAdapter(DataAdapter handledAdapter, PropertyChangeSupport changeSupport) {
		this.changeSupport = changeSupport;
		this.handledAdapter = handledAdapter;
		methodsSettersMap.clear();
		methodsGettersMap.clear();
		for(Method method : handledAdapter.getClass().getMethods()) {
			String methodName = method.getName().toLowerCase();
			if ((methodName.startsWith("get") || methodName.startsWith("is")) && !method.getReturnType().equals(Void.TYPE)) {
				List<Method> methods = methodsGettersMap.get(methodName);
				if (methods == null) {
					methods = new ArrayList<Method>();
					methodsGettersMap.put(methodName, methods);
				}
				methods.add(method);
			} else if (methodName.startsWith("set") && method.getParameterTypes().length == 1) {
				List<Method> methods = methodsSettersMap.get(methodName);
				if (methods == null) {
					methods = new ArrayList<Method>();
					methodsSettersMap.put(methodName, methods);
				}
				methods.add(method);
			}
		
		}
	}
	
	/**
	 * Set the data {@link IAdapterPropertyHandler} for this editor
	 * 
	 * @param adapterPropertyHandler a {@link IAdapterPropertyHandler}, can be null to use only the reflection
	 */
	public void setAdapterPropertyHandler(IAdapterPropertyHandler adapterPropertyHandler) {
		this.adapterPropertyHandler = adapterPropertyHandler;
	}
	
	/**
	 * Check if the property is handled by the currently set {@link IAdapterPropertyHandler}
	 * 
	 * @param propertyName the name of the property to check
	 * @return true if a {@link IAdapterPropertyHandler} is set and it is able to handle the property, false otherwise
	 */
	protected boolean isSupportedByHandler(String propertyName) {
		return (adapterPropertyHandler != null && adapterPropertyHandler.isSupportedProperty(propertyName, handledAdapter));
	}

	@Override
	public JRExpression getPropertyValueExpression(String propertyName) {
		//never used in a context of a data adapter
		return null;
	}
}
