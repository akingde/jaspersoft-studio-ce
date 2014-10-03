package com.jaspersoft.studio.custom.adapter;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.data.AbstractDataAdapter;
import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterServiceFactory;

public class LoadedClassesContainer {

	private List<Pair> adapterInterfaces = new ArrayList<Pair>();
	
	private List<Pair> adapterImplementation = new ArrayList<Pair>();
	
	private List<Pair> service = new ArrayList<Pair>();
	
	private List<Pair> serviceFatory = new ArrayList<Pair>();
	
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

	public List<Pair> getAdapterInterfaces() {
		return adapterInterfaces;
	}

	public List<Pair> getAdapterImplementation() {
		return adapterImplementation;
	}

	public List<Pair> getService() {
		return service;
	}

	public List<Pair> getServiceFatory() {
		return serviceFatory;
	}
	
	public static String[] converToArray(List<Pair> pairList){
		String[] result = new String[pairList.size()];
		int i = 0;
		for(Pair pair : pairList){
			result[i] = pair.toString();
			i++;
		}
		return result;
	}
	
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
