package com.essiembre.eclipse.rbe.ui.editor.resources;

import com.jaspersoft.translation.resources.AbstractResourceDefinition;

public class ResourceElement {
	
	private String pluginName;
	
	private AbstractResourceDefinition resource;
	
	public ResourceElement(String packageName, AbstractResourceDefinition resource){
		this.pluginName = packageName;
		this.resource = resource;
	}
	
	public String getPackageName(){
		return pluginName;
	}
	
	public AbstractResourceDefinition getResource(){
		return resource;
	}
	
}
