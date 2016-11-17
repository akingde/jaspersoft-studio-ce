/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.combo.EditableComboBoxCellEditor;
import com.jaspersoft.studio.utils.EnumHelper;

import net.sf.jasperreports.engine.type.JREnum;

/**
 * Specialized cell editor for {@link NamedEnumPropertyDescriptor} elements.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 *
 */
public class NamedEnumCellEditor extends EditableComboBoxCellEditor {

	public NamedEnumCellEditor(Composite parent, String[] items, int style) {
		super(parent, items, style);
	}

	@Override
	protected void doSetValue(Object value) {
		if (value instanceof JREnum) {
			// translates into a valid index since combo cell editor works with indexes
			value = EnumHelper.getEnumIndexByTranslatedName(getItems(), (JREnum) value);
		}
		if (value == null || !(value instanceof Integer))
			value = -1;
		super.doSetValue(value);
	}

}
