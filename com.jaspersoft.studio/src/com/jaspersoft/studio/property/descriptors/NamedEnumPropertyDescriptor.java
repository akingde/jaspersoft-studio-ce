/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.engine.type.NamedEnum;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

import com.jaspersoft.studio.help.HelpSystem;
import com.jaspersoft.studio.help.IHelp;
import com.jaspersoft.studio.help.IHelpRefBuilder;
import com.jaspersoft.studio.messages.MessagesByKeys;
import com.jaspersoft.studio.property.descriptor.NullEnum;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPReadComboEnum;
import com.jaspersoft.studio.utils.Misc;

public class NamedEnumPropertyDescriptor<T extends Enum<?> & NamedEnum> extends ComboBoxPropertyDescriptor implements
		IPropertyDescriptorWidget, IHelp, IEnumDescriptors {
	private NullEnum type;
	private T[] jrEnums;
	private T nenum;
	private String[] allItems;
	private IHelpRefBuilder refBuilder;

	public NamedEnumPropertyDescriptor(Object id, String displayName, T nenum, NullEnum type) {
		super(Misc.nvl(id, ""), Misc.nvl(displayName), getEnumItems((NamedEnum[]) nenum.getDeclaringClass()
				.getEnumConstants(), type));
		this.nenum = nenum;
		this.type = type;
		this.allItems = getEnumItems((NamedEnum[]) nenum.getDeclaringClass().getEnumConstants(), type);
		jrEnums = ((Class<T>) nenum.getDeclaringClass()).getEnumConstants();
		setLabelProvider(new NamedEnumLabelProvider(allItems));
	}

	public static String[] getEnumItems(NamedEnum[] items, NullEnum type) {
		List<String> res = new ArrayList<String>();
		if (type != NullEnum.NOTNULL)
			res.add(type.getName());
		for (int i = 0; i < items.length; i++)
			res.add(MessagesByKeys.getString(items[i].getName()));
		return res.toArray(new String[res.size()]);
	}

	@Override
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new NamedEnumCellEditor(parent, allItems, SWT.READ_ONLY);
		if (getValidator() != null) {
			editor.setValidator(getValidator());
		}
		HelpSystem.bindToHelp(this, editor.getControl());
		return editor;
	}

	public Integer getIntValue(T jrvalue) {
		if (jrvalue == null)
			return 0;
		int ind = 0;
		for (; ind < jrEnums.length; ind++)
			if (jrEnums[ind] == jrvalue)
				break;
		if (type != NullEnum.NOTNULL)
			ind++;
		return ind;
	}

	public T getEnumValue(Object value) {
		if (value == null)
			return null;
		if (value.getClass().isAssignableFrom(nenum.getClass()))
			return (T) value;
		if (value instanceof String) {
			String v = (String) value;
			if (type != NullEnum.NOTNULL && Misc.isNullOrEmpty(v))
				return null;
			for (T en : jrEnums)
				if (en.getName().equals(v))
					return en;
		}
		if (value instanceof Integer) {
			int ind = (Integer) value;
			if (type != NullEnum.NOTNULL && ind == 0)
				return null;
			if (type != NullEnum.NOTNULL)
				ind--;
			return jrEnums[ind];
		}
		return null;
	}

	@Override
	public String[] getEnumItems() {
		return allItems;
	}
	
	public T[] getEnumElements() {
		return jrEnums;
	}

	public NullEnum getType() {
		return type;
	}

	public ASPropertyWidget<NamedEnumPropertyDescriptor<T>> createWidget(Composite parent, AbstractSection section) {
		return new SPReadComboEnum<NamedEnumPropertyDescriptor<T>>(parent, section, this);
	}

	@Override
	public void setHelpRefBuilder(IHelpRefBuilder refBuilder) {
		this.refBuilder = refBuilder;
	}

	@Override
	public String getHelpReference() {
		if (refBuilder != null)
			return refBuilder.getHelpReference();
		return null;
	}
	
	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new NamedEnumLabelProvider(allItems);
	}

}
