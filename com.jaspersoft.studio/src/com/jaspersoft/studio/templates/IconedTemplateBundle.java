/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.templates;

import org.eclipse.jface.resource.ImageDescriptor;

import com.jaspersoft.templates.TemplateBundle;

/**
 * A template bundle that can provide an icon preview image. Actually
 * the image is used inside JSS in the new report wizard to display visually
 * the template. The image 
 * 
 * @author Orlandin Marco
 *
 */
public interface IconedTemplateBundle extends TemplateBundle {

	/**
	 * Return the image descriptor of the icon. It return a descriptor to avoid
	 * to have to worry about the dispose of the image
	 * 
	 * @return an image descriptor, can be null. When different from null should
	 * point to a valid resource
	 */
	public ImageDescriptor getIcon();
	
}
