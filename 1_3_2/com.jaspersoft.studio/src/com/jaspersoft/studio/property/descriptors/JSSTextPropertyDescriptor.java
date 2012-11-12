/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPText;

public class JSSTextPropertyDescriptor extends TextPropertyDescriptor implements IPropertyDescriptorWidget {
	
	
	
	/**
	 * Field to check if the widget should be read only
	 */
	private boolean readOnly;
	
	public JSSTextPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		readOnly = false;
	}
	
	public void setReadOnly(boolean value){
		readOnly = value;
	}
	
	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget textWidget = new SPText(parent, section, this);
		textWidget.setReadOnly(readOnly);
		return textWidget;
	}
}
