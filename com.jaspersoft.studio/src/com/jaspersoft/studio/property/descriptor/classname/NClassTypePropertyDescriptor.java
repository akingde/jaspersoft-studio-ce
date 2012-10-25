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
package com.jaspersoft.studio.property.descriptor.classname;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.property.section.widgets.ASPropertyWidget;
import com.jaspersoft.studio.property.section.widgets.IPropertyDescriptorWidget;
import com.jaspersoft.studio.property.section.widgets.SPClassType;

public class NClassTypePropertyDescriptor extends ClassTypePropertyDescriptor implements IPropertyDescriptorWidget {

	/**
	 *  Field to check if the widget should be read only
	 */
	private boolean readOnly;
	
	public NClassTypePropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
		readOnly = false;
	}
	
	/**
	 * 
	 * @param id
	 * @param displayName
	 * @param readOnly readOnly true if the widget must be read only, false otherwise
	 */
	public NClassTypePropertyDescriptor(Object id, String displayName, boolean readOnly) {
		super(id, displayName);
		this.readOnly = readOnly;
	}

	public CellEditor createPropertyEditor(Composite parent) {
		CellEditor editor = new ClassTypeCellEditor(parent);
		editor.setValidator(NClassTypeCellEditorValidator.instance());
		setValidator(NClassTypeCellEditorValidator.instance());
		return editor;
	}

	public ASPropertyWidget createWidget(Composite parent, AbstractSection section) {
		ASPropertyWidget classNameWidget = new SPClassType(parent, section, this);
		classNameWidget.setReadOnly(readOnly);
		return classNameWidget;
	}
}
