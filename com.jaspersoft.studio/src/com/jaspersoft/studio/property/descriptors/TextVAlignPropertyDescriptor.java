/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.VerticalTextAlignEnum;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;

public class TextVAlignPropertyDescriptor extends NamedEnumPropertyDescriptor<VerticalTextAlignEnum> {

	public TextVAlignPropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, VerticalTextAlignEnum.BOTTOM, type);
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<VerticalTextAlignEnum>> createWidget(Composite parent,
			AbstractSection section) {
		Image[] images = new Image[] {
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-top.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/formatting/edit-vertical-alignment-middle.png"), };
		return new SPToolBarEnum<NamedEnumPropertyDescriptor<VerticalTextAlignEnum>>(parent, section, this, images, false);
	}
}
