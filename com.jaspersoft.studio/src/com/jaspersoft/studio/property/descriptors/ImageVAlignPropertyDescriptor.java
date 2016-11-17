/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.VerticalImageAlignEnum;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;

public class ImageVAlignPropertyDescriptor extends NamedEnumPropertyDescriptor<VerticalImageAlignEnum> {

	public ImageVAlignPropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, VerticalImageAlignEnum.BOTTOM, type);
	}

	public SPToolBarEnum<NamedEnumPropertyDescriptor<VerticalImageAlignEnum>> createWidget(Composite parent,
			AbstractSection section) {
		Image[] images = new Image[] {
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/vertical_alignment_top_img.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/vertical_alignment_center_img.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/vertical_alignment_bottom_img.png") };
		return new SPToolBarEnum<NamedEnumPropertyDescriptor<VerticalImageAlignEnum>>(parent, section, this, images, false);
	}
}
