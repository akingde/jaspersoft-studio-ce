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
package com.jaspersoft.studio.editor.tools;

import java.io.File;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Model element of a custom tool
 * 
 * @author Orlandin Marco
 *
 */
public class MCustomTool extends MGraphicElement {

	private static final long serialVersionUID = -1234707361853922316L;

	/**
	 * The absolute path to the file containing the tool definition
	 */
	private String path;
	
	/**
	 * The name of the tool
	 */
	private String name;
	
	/**
	 * The small icon of the tool
	 */
	private ImageDescriptor iconSmall;
	
	/**
	 * The big icon of the tool
	 */
	private ImageDescriptor iconBig;
	
	/**
	 * The description of the tool
	 */
	private String description;
	
	/**
	 * The absolute path to the small icon of the tool
	 */
	private String iconPathSmall;
	
	/**
	 * The absolute path to the big icon of the tool
	 */
	private String iconPathBig;
	
	/**
	 * Create the definition of the tool
	 * 
	 * @param name the name of the tool, must be not null
	 * @param description the description of the tool, must be not null
	 * @param path the path to the definition of the tool, must be not null
	 * @param iconPathSmall the path to the small icon of the tool, if null or not existing a default one is used
	 * @param iconPathBig the path to the big icon of the tool, if null or not existing a default one is used
	 */
	public MCustomTool(String name, String description, String path, String iconPathSmall, String iconPathBig){
		Assert.isNotNull(name);
		Assert.isNotNull(description);
		Assert.isNotNull(path);
		this.path = path;
		this.name = name;
		this.description = description;
		this.iconPathSmall = iconPathSmall;
		this.iconPathBig = iconPathBig;
		iconSmall = JaspersoftStudioPlugin.getInstance().getImageDescriptor("/icons/resources/cell-16.png");
		iconBig = JaspersoftStudioPlugin.getInstance().getImageDescriptor("/icons/resources/cell-32.png");
		if (iconPathSmall != null){
			File toolIcon = new File(iconPathSmall);
			if (toolIcon.exists()){
				iconSmall = ResourceManager.getImageDescriptor(toolIcon.getAbsolutePath());
			}
			toolIcon = new File(iconPathBig);
			if (toolIcon.exists()){
				iconBig = ResourceManager.getImageDescriptor(toolIcon.getAbsolutePath());
			}
		}
	}
	
	/**
	 * Return the small icon of the tool 
	 * 
	 * @return a not null image data
	 */
	public ImageDescriptor getIconSmall(){
		return iconSmall;
	}
	
	/**
	 * Return the big icon of the tool 
	 * 
	 * @return a not null image data
	 */
	public ImageDescriptor getIconBig(){
		return iconBig;
	}

	/**
	 * Return the name of the tool
	 * 
	 * @return a not null string
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Return the description of the tool
	 * 
	 * @return a not null string
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Return the absolute path to the definition file of the tool
	 * 
	 * @return a not null string
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Return the absolute path to the definition file of the tool
	 * 
	 * @return an absolute path, can be null if the tool is using the default image
	 */
	public String getIconPathSmall(){
		return iconPathSmall;
	}
	
	/**
	 * Return the absolute path to the definition file of the tool
	 * 
	 * @return an absolute path, can be null if the tool is using the default image
	 */
	public String getIconPathBig(){
		return iconPathBig;
	}
}
