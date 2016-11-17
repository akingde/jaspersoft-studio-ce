/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.charts.type.EdgeEnum;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.SPLegendAlignementEnum;

public class EdgePropertyDescriptor extends NamedEnumPropertyDescriptor<EdgeEnum> {

	public EdgePropertyDescriptor(Object id, String displayName, NullEnum type) {
		super(id, displayName, EdgeEnum.BOTTOM, type);
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<EdgeEnum>> createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget<NamedEnumPropertyDescriptor<EdgeEnum>> widget = new SPLegendAlignementEnum<NamedEnumPropertyDescriptor<EdgeEnum>>(
				parent, section, this);
		HelpSystem.bindToHelp(this, widget.getControl());
		return widget;
	}

}
