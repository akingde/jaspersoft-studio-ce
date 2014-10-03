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
package com.jaspersoft.studio.custom.adapter;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.AbstractDataAdapter;
import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterServiceFactory;

/**
 * Container for the informations on the classes loaded from the JAR of 
 * a data adapter
 * 
 * @author Orlandin Marco
 *
 */
public class LoadedClassesContainer {

	/**
	 * List of the interfaces inside the jar that implements the interface DataAdapter
	 */
	private List<Pair> adapterInterfaces = new ArrayList<Pair>();
	
	/**
	 * List of the interfaces inside the jar that extends the class AbastractDataAdapter
	 */
	private List<Pair> adapterImplementation = new ArrayList<Pair>();
	
	/**
	 * List of the interfaces inside the jar that extends the class AbstractDataAdapterService
	 */
	private List<Pair> service = new ArrayList<Pair>();
	
	/**
	 * List of the interfaces inside the jar that extends the class DataAdapterServiceFactory
	 */
	private List<Pair> serviceFatory = new ArrayList<Pair>();

	/**
	 * Add a class to the stored information
	 * 
	 * @param loadedClass a class, to be added it must implement DataAdapter or extends 
	 * AbastractDataAdapter, AbstractDataAdapterService or DataAdapterServiceFactory
	 */
	public void addClass(Class<?> loadedClass){
		if (loadedClass.isInterface() && DataAdapter.class.isAssignableFrom(loadedClass)){
			adapterInterfaces.add(new Pair(loadedClass.getPackage().getName(), loadedClass.getSimpleName()));
		} else if (!loadedClass.isInterface() && AbstractDataAdapter.class.isAssignableFrom(loadedClass)){
			adapterImplementation.add(new Pair(loadedClass.getPackage().getName(), loadedClass.getSimpleName()));
		}else if (!loadedClass.isInterface() && AbstractDataAdapterService.class.isAssignableFrom(loadedClass)){
			 service.add(new Pair(loadedClass.getPackage().getName(), loadedClass.getSimpleName()));
		}else if (!loadedClass.isInterface() && DataAdapterServiceFactory.class.isAssignableFrom(loadedClass)){
			serviceFatory.add(new Pair(loadedClass.getPackage().getName(), loadedClass.getSimpleName()));
		}
	}

	/**
	 * Return the list of the DataAdapter interfaces
	 * 
	 * @return a not null list of information on the DataAdapter interfaces defined inside the JAR
	 */
	public List<Pair> getAdapterInterfaces() {
		return adapterInterfaces;
	}

	/**
	 * Return the list of the DataAdapter implementations
	 * 
	 * @return a not null list of information on the AbastractDataAdapter subclasses defined inside the JAR
	 */
	public List<Pair> getAdapterImplementation() {
		return adapterImplementation;
	}

	/**
	 * Return the list of the DataAdapter Service implementations
	 * 
	 * @return a not null list of information on the AbstractDataAdapterService subclasses defined inside the JAR
	 */
	public List<Pair> getService() {
		return service;
	}

	/**
	 * Return the list of the DataAdapter Service Factory implementations
	 * 
	 * @return a not null list of information on the DataAdapterServiceFactory subclasses defined inside the JAR
	 */
	public List<Pair> getServiceFatory() {
		return serviceFatory;
	}
	
	/**
	 * Convert a list of pair in a string array where each
	 * string is the complete name of a class (included the name space)
	 * 
	 * @param pairList a not null list of pair
	 * @return a not null array of class names
	 */
	public static String[] converToArray(List<Pair> pairList){
		String[] result = new String[pairList.size()];
		int i = 0;
		for(Pair pair : pairList){
			result[i] = pair.toString();
			i++;
		}
		return result;
	}
	
	/**
	 * Convert a class full name (with the namespace)
	 * into a pair
	 * 
	 * @param value a class full name
	 * @return the pair with the information of the class or null
	 * if the value is not valid (empty or null for example)
	 */
	public static Pair convertToPair(String value){
		if (value == null || value.isEmpty()) return null;
		int classSeparator = value.lastIndexOf(".");
		String className = "";
		String packageName = "";
		if (classSeparator == -1){
			className = value;
		} else {
			packageName = value.substring(0, classSeparator);
			className = value.substring(classSeparator+1);
		}
		return new Pair(packageName, className);
	}
}
