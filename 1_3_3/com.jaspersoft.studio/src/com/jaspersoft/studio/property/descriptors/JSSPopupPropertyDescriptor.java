/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
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
