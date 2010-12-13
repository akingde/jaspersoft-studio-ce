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
package com.jaspersoft.studio.property.descriptor;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.jface.DoubleCellEditorValidator;

/**
 * The Class FloatPropertyDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class DoublePropertyDescriptor extends TextPropertyDescriptor {

	/**
	 * Instantiates a new float property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public DoublePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	/**
	 * The <code>TextPropertyDescriptor</code> implementation of this <code>IPropertyDescriptor</code> method creates and
	 * returns a new <code>TextCellEditor</code>.
	 * <p>
	 * The editor is configured with the current validator if there is one.
	 * </p>
	 * 
	 * @param parent
	 *          the parent
	 * @return the cell editor
	 */
	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new TextCellEditor(parent) {
			@Override
			protected Object doGetValue() {
				String value = (String) super.doGetValue();
				if (value == null || value.equals("")) //$NON-NLS-1$
					return (Double) null;
				return new Double(value);
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					super.doSetValue(""); //$NON-NLS-1$
				else {
					Assert.isTrue(text != null && (value instanceof Double));
					super.doSetValue(((Double) value).toString());
				}
			}
		};
		editor.setValidator(DoubleCellEditorValidator.instance());
		setValidator(DoubleCellEditorValidator.instance());

		return editor;
	}
}
