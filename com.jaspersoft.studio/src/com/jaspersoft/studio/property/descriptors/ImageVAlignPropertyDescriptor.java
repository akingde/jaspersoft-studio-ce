/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
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
