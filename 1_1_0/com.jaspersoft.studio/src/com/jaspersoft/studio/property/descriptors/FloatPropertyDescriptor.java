/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptors;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.jaspersoft.studio.jface.FloatCellEditorValidator;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPNumber;

/*
 * The Class FloatPropertyDescriptor.
 * 
 * @author Chicu Veaceslav
 */
public class FloatPropertyDescriptor extends TextPropertyDescriptor implements IPropertyDescriptorWidget {

	/**
	 * Instantiates a new float property descriptor.
	 * 
	 * @param id
	 *          the id
	 * @param displayName
	 *          the display name
	 */
	public FloatPropertyDescriptor(Object id, String displayName) {
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
					return (Float) null;
				return new Float(value);
			}

			@Override
			protected void doSetValue(Object value) {
				if (value == null)
					super.doSetValue(""); //$NON-NLS-1$
				else {
					Assert.isTrue(text != null && (value instanceof Float));
					super.doSetValue(((Float) value).toString());
				}
			}
		};
		editor.setValidator(FloatCellEditorValidator.instance());
		setValidator(FloatCellEditorValidator.instance());

		return editor;
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		return new SPNumber(parent, section, this);
	}
}
