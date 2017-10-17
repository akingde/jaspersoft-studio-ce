/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.gallery.implementations;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;

import net.sf.jasperreports.eclipse.util.FileExtension;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;

/**
 * Implementations of a gallery element to represent
 * a page of a pagebook
 * 
 * @author Orlandin Marco
 *
 */
public class PageElement implements IGalleryElement {
	
	/**
	 * Descriptor of the page image
	 */
	private ImageDescriptor imageDescriptor;
	
	/**
	 * Path of the report that generate the page
	 */
	private String reportPath;
	
	/**
	 * Create the container
	 * 
	 * @param image Descriptor of the page image
	 * @param reportPath Path of the report that generate the page
	 */
	public PageElement(ImageDescriptor image, String reportPath){
		this.imageDescriptor = image;
		this.reportPath = reportPath;
	}
	
	/**
	 * Create the container. An image for the container is searched
	 * on the same path by looking for a file with the same name
	 * but with extension png, jpeg or gif. If no image is found
	 * then a default one is used
	 * 
	 * @param reportPath Path of the report that generate the page
	 */
	public PageElement(String reportPath){
		this.reportPath = reportPath;
		this.imageDescriptor = getElementImage(reportPath);
	}

	/**
	 * Return the image from the imageDescriptor. The image 
	 * is cached inside the resource image
	 * 
	 * @return a cached a not null swt image
	 */
	public Image getImage(){
		return ResourceManager.getImage(imageDescriptor);
	}

	/**
	 * Return the title of the element as the filename
	 * of the report
	 * 
	 * @return a not null filename without the path
	 */
	@Override
	public String getTitle() {
		 File selectedResource = new File(reportPath);
		 return selectedResource.getName();
	}

	/**
	 * Return the path of the report
	 */
	@Override
	public Object getData() {
		return reportPath;
	}
	
	/**
	 * Try to get a sample image for a report searching it on the same location.
	 * If the image can't be found a default one is used
	 * 
	 * @param jrxmlPath the absolute path of the report
	 * @return a not null image that can be used as sample for the report
	 */
	private ImageDescriptor getElementImage(String jrxmlPath){
		try{
			String[] imageExtensions = new String[] { ".png", ".gif", ".jpg" };
			String baseImageUrl = URLDecoder.decode(jrxmlPath, "utf-8");
			// remove the .jrxml...
			baseImageUrl = baseImageUrl.substring(0, baseImageUrl.length() - FileExtension.PointJRXML.length());
			for (String extension : imageExtensions) {
				try {
					URL iconURL = new File(baseImageUrl + extension).toURI().toURL();
					ImageDescriptor result = getIconFromUrl(iconURL);
					if (result != null) return result;
				} catch (MalformedURLException e) {
					e.printStackTrace();
				}
			}
		} catch(Exception ex){
		}
		return JRBookActivator.getDefault().getImageDescriptor("/icons/blankreport.png");
	}
	
	/**
	 * Check for an icon provided by the template. If it is available it is returned, otherwise
	 * it return null
	 * 
	 * @param iconURL the url of the icon
	 * @return the icon or null if it can't be found
	 */
	private ImageDescriptor getIconFromUrl(URL iconURL) {
		ImageDescriptor descriptor = ImageDescriptor.createFromURL(iconURL);
		if (descriptor != null && descriptor.getImageData() != null) return descriptor;
		return null;
	}
}
