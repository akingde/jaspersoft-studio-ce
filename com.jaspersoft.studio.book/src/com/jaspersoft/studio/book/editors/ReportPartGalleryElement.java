/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.editors;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.book.JRBookActivator;
import com.jaspersoft.studio.book.gallery.interfaces.IGalleryElement;
import com.jaspersoft.studio.book.model.MReportPart;

public class ReportPartGalleryElement implements IGalleryElement {
	
	private static final ImageDescriptor standardReportImgDesc;
	private MReportPart part;
	
	static {
		standardReportImgDesc = JRBookActivator.getDefault().getImageDescriptor("/icons/blankreport.png");
	}

	public ReportPartGalleryElement(MReportPart part){
		this.part = part;
	}

	@Override
	public Image getImage() {
		return ResourceManager.getImage(standardReportImgDesc);
	}

	@Override
	public String getTitle() {
		return part.getDisplayText();
	}

	@Override
	public Object getData() {
		return part;
	}

}
