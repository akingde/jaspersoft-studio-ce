/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPToolBarEnum;

public class LineStylePropertyDescriptor extends NamedEnumPropertyDescriptor<LineStyleEnum> {

	public LineStylePropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, LineStyleEnum.DASHED, type);
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<LineStyleEnum>> createWidget(Composite parent,
			AbstractSection section) {
		Image[] images = new Image[] { JaspersoftStudioPlugin.getInstance().getImage("icons/resources/line-solid.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/line-dashed.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/line-dotted.png"),
				JaspersoftStudioPlugin.getInstance().getImage("icons/resources/line-double.png"), };
		return new SPToolBarEnum<NamedEnumPropertyDescriptor<LineStyleEnum>>(parent, section, this, images, false);
	}
}
