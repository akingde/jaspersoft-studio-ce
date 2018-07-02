/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.HorizontalTextAlignEnum;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;

public class TextHAlignPropertyDescriptor extends NamedEnumPropertyDescriptor<HorizontalTextAlignEnum> {

	public TextHAlignPropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, HorizontalTextAlignEnum.CENTER, type);
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<HorizontalTextAlignEnum>> createWidget(Composite parent,
			AbstractSection section) {
		Image[] images = new Image[] {
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/left_align.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/center_align.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/right_align.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/justified_align.png") };
		return new SPToolBarEnum<NamedEnumPropertyDescriptor<HorizontalTextAlignEnum>>(parent, section, this, images, false);
	}
}
