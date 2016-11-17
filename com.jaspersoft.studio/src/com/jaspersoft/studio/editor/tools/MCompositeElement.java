/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools;

import java.io.File;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Model element of a composite element
 * 
 * @author Orlandin Marco
 *
 */
public class MCompositeElement extends MGraphicElement {

	private static final long serialVersionUID = -1234707361853922316L;

	/**
	 * The absolute path to the file containing the definition of the composite element
	 */
	private String path;
	
	/**
	 * The name of the composite element
	 */
	private String name;
	
	/**
	 * The small icon of the composite element
	 */
	private ImageDescriptor iconSmall;
	
	/**
	 * The big icon of the composite element
	 */
	private ImageDescriptor iconBig;
	
	/**
	 * The description of the composite element 
	 */
	private String description;
	
	/**
	 * The absolute path to the small icon of the composite element 
	 */
	private String iconPathSmall;
	
	/**
	 * The absolute path to the big icon of the composite element 
	 */
	private String iconPathBig;
	
	/**
	 * The group in the palette where this composite element  is placed
	 */
	private String groupID;
	
	/**
	 * Create the definition of the composite element 
	 * 
	 * @param name the name of the composite element , must be not null
	 * @param description the description of the composite element , must be not null
	 * @param path the path to the definition of the composite element , must be not null
	 * @param iconPathSmall the path to the small icon of the composite element , if null or not existing a default one is used
	 * @param iconPathBig the path to the big icon of the composite element , if null or not existing a default one is used
	 */
	public MCompositeElement(String name, String description, String groupID, String path, String iconPathSmall, String iconPathBig){
		Assert.isNotNull(name);
		Assert.isNotNull(description);
		Assert.isNotNull(path);
		this.path = path;
		this.name = name;
		this.description = description;
		this.groupID = groupID;
		this.iconPathSmall = iconPathSmall;
		this.iconPathBig = iconPathBig;
		iconSmall = JaspersoftStudioPlugin.getInstance().getImageDescriptor("/icons/resources/custom_tool-16.png");
		iconBig = JaspersoftStudioPlugin.getInstance().getImageDescriptor("/icons/resources/custom_tool-32.png");
		if (iconPathSmall != null){
			File elementIcon = new File(iconPathSmall);
			if (elementIcon.exists()){
				iconSmall = ResourceManager.getImageDescriptor(elementIcon.getAbsolutePath());
			}
			elementIcon = new File(iconPathBig);
			if (elementIcon.exists()){
				iconBig = ResourceManager.getImageDescriptor(elementIcon.getAbsolutePath());
			}
		}
	}
	
	/**
	 * Return the small icon of the composite element 
	 * 
	 * @return a not null image data
	 */
	public ImageDescriptor getIconSmall(){
		return iconSmall;
	}
	
	/**
	 * Return the big icon of the composite element  
	 * 
	 * @return a not null image data
	 */
	public ImageDescriptor getIconBig(){
		return iconBig;
	}

	/**
	 * Return the name of the composite element 
	 * 
	 * @return a not null string
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Return the description of the composite element 
	 * 
	 * @return a not null string
	 */
	public String getDescription(){
		return description;
	}
	
	/**
	 * Return the group id of a palette group where this element
	 * should be placed
	 * 
	 * @return a not null string
	 */
	public String getGroupId(){
		return groupID;
	}
	
	/**
	 * Return the absolute path to the definition file of the composite element 
	 * 
	 * @return a not null string
	 */
	public String getPath(){
		return path;
	}
	
	/**
	 * Return the absolute path to the definition file of the composite element 
	 * 
	 * @return an absolute path, can be null if the composite element  is using the default image
	 */
	public String getIconPathSmall(){
		return iconPathSmall;
	}
	
	/**
	 * Return the absolute path to the definition file of the composite element 
	 * 
	 * @return an absolute path, can be null if the composite element  is using the default image
	 */
	public String getIconPathBig(){
		return iconPathBig;
	}
	
	/**
	 * Return the file location of the resource folder for this element
	 * 
	 * @return a not null File, but it could not exist if the element has no resources
	 */
	public File getResourceFolder(){
		File contentFile = new File(path);
		return new File(contentFile.getParentFile(), FilenameUtils.removeExtension(contentFile.getName()));
	}
}
