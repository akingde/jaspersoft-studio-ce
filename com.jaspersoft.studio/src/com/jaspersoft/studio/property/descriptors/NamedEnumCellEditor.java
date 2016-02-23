/*******************************************************************************
 * Copyright (C) 2005 - 2015 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
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