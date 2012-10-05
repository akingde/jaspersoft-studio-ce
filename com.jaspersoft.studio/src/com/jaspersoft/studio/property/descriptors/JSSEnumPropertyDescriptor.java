/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptors;

import net.sf.jasperreports.engine.type.JREnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPReadComboEnum;
import com.jaspersoft.studio.utils.EnumHelper;

public class JSSEnumPropertyDescriptor extends ComboBoxPropertyDescriptor implements IPropertyDescriptorWidget {
	private NullEnum type;
	private int start = 0;
	private JREnum[] jrEnums;

	public JSSEnumPropertyDescriptor(Object id, String displayName, Class<? extends JREnum> jrEnum, NullEnum type) {
		super(id, displayName, EnumHelper.getEnumNames(jrEnum.getEnumConstants(), type));
		this.type = type;
		jrEnums = jrEnum.getEnumConstants();
		this.start = jrEnums[0].getValue();
	}
	

	
	public Integer getEnumValue(JREnum jrvalue) {
		return EnumHelper.getValue(jrvalue, start, type != NullEnum.NOTNULL);
	}

	public JREnum getEnumValue(Object value) {
		return EnumHelper.getSetValue(jrEnums, value, start, type != NullEnum.NOTNULL);
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPReadComboEnum(parent, section, this);
	}

	public NullEnum getType() {
		return type;
	}

	public int getStart() {
		return start;
	}

	public JREnum[] getJrEnums() {
		return jrEnums;
	}
}
