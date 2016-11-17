/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.HorizontalImageAlignEnum;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;

public class ImageHAlignPropertyDescriptor extends NamedEnumPropertyDescriptor<HorizontalImageAlignEnum> {

	public ImageHAlignPropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, HorizontalImageAlignEnum.CENTER, type);
	}

	public SPToolBarEnum<NamedEnumPropertyDescriptor<HorizontalImageAlignEnum>> createWidget(Composite parent,
			AbstractSection section) {
		Image[] images = new Image[] { JaspersoftStudioPlugin.getInstance().getImage("icons/resources/left_align_img.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/center_align_img.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/right_align_img.png") };
		return new SPToolBarEnum<NamedEnumPropertyDescriptor<HorizontalImageAlignEnum>>(parent, section, this, images,
				false);
	}
}
