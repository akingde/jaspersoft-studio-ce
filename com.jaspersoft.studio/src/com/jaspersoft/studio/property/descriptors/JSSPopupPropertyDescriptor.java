/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import java.util.List;

import net.sf.jasperreports.engine.type.LineStyleEnum;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;

/**
 * Create a property descriptor represented by a popup combo box
 * 
 * @author Marco Orlandin
 * 
 */
public class JSSPopupPropertyDescriptor extends NamedEnumPropertyDescriptor<LineStyleEnum> implements
		IPropertyDescriptorWidget, IHelp {

	private List<ComboItem> items;

	public JSSPopupPropertyDescriptor(Object id, String displayName, LineStyleEnum jrEnum, NullEnum type,
			List<ComboItem> items) {
		super(id, displayName, jrEnum, type);
		this.items = items;
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<LineStyleEnum>> createWidget(Composite parent,
			AbstractSection section) {
		SPRWPopUpCombo widget = new SPRWPopUpCombo(parent, section, this, items);
		HelpSystem.bindToHelp(this, widget.getControl());
		return widget;
	}

}
