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

import org.eclipse.jface.viewers.ICellModifier;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.property.descriptor.NullEnum;
/*
 * @author Chicu Veaceslav
 * 
 */
public class CheckBoxLabelProvider extends LabelProvider {
	private NullEnum canBeNull;

	public CheckBoxLabelProvider(NullEnum canBeNull) {
		super();
		this.canBeNull = canBeNull;
	}

	@Override
	public Image getImage(Object element) {
		if (element == null || element instanceof Boolean) {
			return getCellEditorImage((Boolean) element);
		}
		return super.getImage(element);
	}

	@Override
	public String getText(Object element) {
		return element == null || !(element instanceof Boolean) ? canBeNull.getName() : element.toString(); //$NON-NLS-1$
	}

	/**
	 * To be used by LabelProviders that whant to display a checked/unchecked icon for the CheckboxCellEditor that does
	 * not have a Control.
	 * 
	 * @param cellModifier
	 *          The ICellModifier for the CellEditor to provide the value
	 * @param element
	 *          The current element
	 * @param property
	 *          The property the cellModifier should return the value from
	 */
	public Image getCellEditorImage(ICellModifier cellModifier, Object element, String property) {
		Boolean value = (Boolean) cellModifier.getValue(element, property);
		return getCellEditorImage(value);
	}

	/**
	 * returns an checked checkbox image if value if true and an unchecked checkbox image if false
	 * 
	 * @param value
	 *          the value to get the cooresponding image for
	 * @param disabled
	 *          determines if the image should be disabled or not
	 * @return an checked checkbox image if value if true and an unchecked checkbox image if false
	 * 
	 */
	public Image getCellEditorImage(Boolean value) {
		Image image = null;
		if (value == null)
			image = JaspersoftStudioPlugin.getImage("icons/CheckboxCellEditorHelper-null.16x16.png"); //$NON-NLS-1$
		else if (value.booleanValue())
			image = JaspersoftStudioPlugin.getImage("icons/CheckboxCellEditorHelper-checked.16x16.png"); //$NON-NLS-1$
		else
			image = JaspersoftStudioPlugin.getImage("icons/CheckboxCellEditorHelper-unchecked.16x16.png"); //$NON-NLS-1$
		return image;
	}
}
