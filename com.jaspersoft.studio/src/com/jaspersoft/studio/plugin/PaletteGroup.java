/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.plugin;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.studio.JaspersoftStudioPlugin;

public class PaletteGroup {
	private String id;
	private String name;
	private ImageDescriptor image;
	private String afterGroup;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ImageDescriptor getImage() {
		return image;
	}

	public void setImage(ImageDescriptor image) {
		this.image = image;
	}

	public void setImage(String image) {
		if (image != null && !image.trim().isEmpty())
			setImage(JaspersoftStudioPlugin.getInstance().getImageDescriptor(image));
		else
			setImage(JaspersoftStudioPlugin.getInstance().getImageDescriptor("icons/resources/elementgroup-16.png"));
	}

	public String getAfterGroup() {
		return afterGroup;
	}

	public void setAfterGroup(String afterGroup) {
		this.afterGroup = afterGroup;
	}
}
