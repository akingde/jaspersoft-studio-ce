/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.RotationEnum;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;

public class RotationPropertyDescriptor extends NamedEnumPropertyDescriptor<RotationEnum> {

	public RotationPropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, RotationEnum.LEFT, type);
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<RotationEnum>> createWidget(Composite parent,
			AbstractSection section) {
		Image[] images = new Image[] {
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/text-direction-none.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/text-direction-left.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/text-direction-right.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/text-direction-updown.png") };
		return new SPToolBarEnum<NamedEnumPropertyDescriptor<RotationEnum>>(parent, section, this, images, false);
	}
}
