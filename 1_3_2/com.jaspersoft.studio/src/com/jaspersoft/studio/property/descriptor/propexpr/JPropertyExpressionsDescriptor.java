/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.property.descriptor.propexpr;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.descriptor.text.NTextPropertyDescriptor;

public class JPropertyExpressionsDescriptor extends NTextPropertyDescriptor {

	public JPropertyExpressionsDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new JPropertyExpressionsCellEditor(parent);
		return editor;
	}

	@Override
	public ILabelProvider getLabelProvider() {
		if (isLabelProviderSet()) {
			return super.getLabelProvider();
		}
		return new JPropertyExpressionsLabelProvider();
	}
}
