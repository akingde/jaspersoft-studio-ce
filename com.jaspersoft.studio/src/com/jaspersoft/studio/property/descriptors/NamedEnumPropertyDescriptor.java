/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.type.NamedEnum;

public class NamedEnumPropertyDescriptor<T extends Enum<?> & NamedEnum> extends ComboBoxPropertyDescriptor
		implements IPropertyDescriptorWidget, IHelp, IEnumDescriptors {
	private NullEnum type;
	private T[] jrEnums;
	private T nenum;
	private String[] allItems;
	private IHelpRefBuilder refBuilder;

	public NamedEnumPropertyDescriptor(Object id, String displayName, T nenum, NullEnum type) {
		super(Misc.nvl(id, ""), Misc.nvl(displayName),
				getEnumItems((NamedEnum[]) nenum.getDeclaringClass().getEnumConstants(), type));
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
		for (int i = 0; i < items.length; i++) {
			String eval = MessagesByKeys.getString(items[i].getName());

			try {
				Deprecated dep = items[i].getClass().getField(((Enum<?>) items[i]).name()).getAnnotation(Deprecated.class);
				if (dep != null)
					eval += " (deprecated)";
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			}
			res.add(eval);
		}
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

	// STATIC METHOD TO GET THE ENUM VALUE FROM AND TO INDEX WITHOUT CREATE OBJECTS

	/**
	 * Map where the values of a possible enum are keep cached
	 */
	private static HashMap<Class<?>, Object[]> enumValuesMap = new HashMap<Class<?>, Object[]>();

	/**
	 * Get the values of an enum class. The values are keep cached so any following request will look in the cache instead
	 * to recalculate the values
	 * 
	 * @param enumClass
	 *          the class of the enum
	 * @return the array of values of the enum
	 */
	@SuppressWarnings("unchecked")
	private static <T extends Enum<?> & NamedEnum> T[] getEnumValues(Class<T> enumClass) {
		Object[] cachedEnum = enumValuesMap.get(enumClass);
		if (cachedEnum == null) {
			cachedEnum = enumClass.getEnumConstants();
			enumValuesMap.put(enumClass, cachedEnum);
		}
		return (T[]) cachedEnum;
	}

	public static <T extends Enum<?> & NamedEnum> Integer getIntValue(NullEnum type, T jrvalue) {
		return getIntValue(jrvalue, type, jrvalue);
	}

	/**
	 * Static version of the method getIntValue, can be used in the case the PropertyDescriptor are created statically and
	 * no reference to them is keep locally
	 * 
	 * @param nenum
	 *          a value of the enum handled, its class will be used to get the other value
	 * @param type
	 *          Specify the null value
	 * @param jrvalue
	 *          The value stored in the jr element, will be converted to the enum index
	 * @return the index of the jrvalue in the enum values list
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?> & NamedEnum> Integer getIntValue(T nenum, NullEnum type, T jrvalue) {
		if (jrvalue == null) {
			return 0;
		}
		int ind = 0;
		T[] jrEnums = getEnumValues((Class<T>) nenum.getDeclaringClass());
		for (; ind < jrEnums.length; ind++) {
			if (jrEnums[ind] == jrvalue) {
				break;
			}
		}
		if (type != NullEnum.NOTNULL) {
			ind++;
		}
		return ind;
	}

	/**
	 * Static version of the method getEnumValue, can be used in the case the PropertyDescriptor are created statically
	 * and no reference to them is keep locally
	 * 
	 * @param nenum
	 *          a value of the enum handled, its class will be used to get the other value
	 * @param type
	 *          Specify the null value
	 * @param value
	 *          The value that need to be converted to an enum
	 * @return The enum value of the passed value. Against value is tried the conversion directly to the enum value or to
	 *         string or to integer to try to find the correct enum value
	 */
	@SuppressWarnings("unchecked")
	public static <T extends Enum<?> & NamedEnum> T getEnumValue(T nenum, NullEnum type, Object value) {
		if (value == null) {
			return null;
		}
		if (value.getClass().isAssignableFrom(nenum.getClass())) {
			return (T) value;
		}
		T[] jrEnums = getEnumValues((Class<T>) nenum.getDeclaringClass());
		if (value instanceof String) {
			String v = (String) value;
			if (type != NullEnum.NOTNULL && Misc.isNullOrEmpty(v)) {
				return null;
			}
			for (T en : jrEnums) {
				if (en.getName().equals(v)) {
					return en;
				}
			}
		}
		if (value instanceof Integer) {
			int ind = (Integer) value;
			if (type != NullEnum.NOTNULL && ind == 0) {
				return null;
			}
			if (type != NullEnum.NOTNULL) {
				ind--;
			}
			return jrEnums[ind];
		}
		return null;
	}

}
