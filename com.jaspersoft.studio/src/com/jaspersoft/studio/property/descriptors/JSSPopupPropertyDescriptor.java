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

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.type.JREnum;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.property.combomenu.ComboItem;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPRWPopUpCombo;
import com.jaspersoft.studio.utils.EnumHelper;

/**
 * Create a property descriptor represented by a popup combo box
 * @author Marco Orlandin
 *
 */
public class JSSPopupPropertyDescriptor extends ComboBoxPropertyDescriptor implements IPropertyDescriptorWidget {
	
	private NullEnum type;
	private int start = 0;
	private JREnum[] jrEnums;
	private List<ComboItem> items;

	public JSSPopupPropertyDescriptor(Object id, String displayName, Class<? extends JREnum> jrEnum, NullEnum type) {
		super(id, displayName, EnumHelper.getEnumNames(jrEnum.getEnumConstants(), type));
		this.type = type;
		jrEnums = jrEnum.getEnumConstants();
		this.start = jrEnums[0].getValue();
		setItemsToDefault();
	}
	
	public JSSPopupPropertyDescriptor(Object id, String displayName, Class<? extends JREnum> jrEnum, NullEnum type, List<ComboItem> items){
		super(id, displayName, EnumHelper.getEnumNames(jrEnum.getEnumConstants(), type));
		jrEnums = jrEnum.getEnumConstants();
		this.start = jrEnums[0].getValue();
		this.items = items;
	}

	private void setItemsToDefault(){
		items = new ArrayList<ComboItem>();
		int offset = 0;
		if (type != NullEnum.NOTNULL) {
			items.add(new ComboItem(type.getName(), true, 0, type, 0));
			offset = 1;
		}
		for(int i=0; i<jrEnums.length; i++){
			JREnum actualValue = jrEnums[i];
			items.add(new ComboItem(actualValue.getName(), true, i+offset, actualValue, i+offset));
		}
	}
	
	public Integer getEnumValue(JREnum jrvalue) {
		return EnumHelper.getValue(jrvalue, start, type != NullEnum.NOTNULL);
	}

	public JREnum getEnumValue(Object value) {
		return EnumHelper.getSetValue(jrEnums, value, start, type != NullEnum.NOTNULL);
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		//return new SPReadComboEnum(parent, section, this);
		return new SPRWPopUpCombo(parent, section, this, items);
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
