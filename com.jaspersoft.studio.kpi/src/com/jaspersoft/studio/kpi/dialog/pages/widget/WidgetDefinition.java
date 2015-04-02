package com.jaspersoft.studio.kpi.dialog.pages.widget;


import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.kpi.Activator;

public class WidgetDefinition {

	private String widgetName;
	
	private String imagePath;
	
	public WidgetDefinition(String widgetName, String imagePath){
		this.widgetName = widgetName;
		this.imagePath = imagePath;
	}
	
	public Image getImage(){
		ImageDescriptor descriptor = Activator.getImageDescriptor(imagePath);
		return ResourceManager.getImage(descriptor);
	}

	public String getWidgetName() {
		return widgetName;
	}
	
}
