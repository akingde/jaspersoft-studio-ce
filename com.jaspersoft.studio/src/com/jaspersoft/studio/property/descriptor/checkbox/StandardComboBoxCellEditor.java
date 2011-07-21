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
package com.jaspersoft.studio.property.descriptor.checkbox;

import java.text.MessageFormat;

import org.eclipse.swt.widgets.Composite;

public class StandardComboBoxCellEditor extends ObjectComboBoxCellEditor {
	protected Object[] fItems;

	public StandardComboBoxCellEditor(Composite parent) {
		super(parent);
	}

	public StandardComboBoxCellEditor(Composite parent, String[] displayStrings, Object[] items) {
		super(parent, displayStrings);

		fItems = items;
	}

	public void setItems(String[] displayStrings, Object[] items) {
		fItems = items;
		setItems(displayStrings);
	}

	/**
	 * Return an error message if this is not a valid value. This can be overridden if a specific message should be
	 * returned instead of the default one. This default implementation will simply see if it is one of the items in
	 * fItems or if it is null. If it isn't it will return a generic invalid value message.
	 */
	@Override
	protected String isCorrectObject(Object value) {
		if (value == null || doGetIndex(value) != NO_SELECTION)
			return null;

		return MessageFormat.format("warning {0}", new Object[] { value });
	}

	/**
	 * Subclassed need to implement returning the object that the index represents. This is called when editing and a
	 * selection from the combobox is sent in and we need to send the object that it represents up to the validators. The
	 * index to convert will be passed in.
	 */
	@Override
	protected Object doGetObject(int index) {
		return (fItems != null && index >= 0 && index < fItems.length) ? fItems[index] : null;
	}

	/**
	 * The object is being passed in, return the index to be used in the editor.
	 * 
	 * It should return sNoSelection if the value can't be converted to a index. The errormsg will have already been set
	 * in this case.
	 */
	@Override
	protected int doGetIndex(Object value) {
		if (fItems != null) {
			for (int i = 0; i < fItems.length; i++) {
				if (fItems[i] == null)
					if (value == null)
						return i;
					else
						;
				else if (fItems[i].equals(value))
					return i;
			}
		}

		return NO_SELECTION;
	}

}