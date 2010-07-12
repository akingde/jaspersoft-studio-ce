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
package com.jaspersoft.studio.property;

import java.util.Iterator;

import org.eclipse.core.runtime.Assert;
import org.eclipse.gef.EditPart;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.model.IIconDescriptor;
import com.jaspersoft.studio.model.MGraphicElement;

/**
 * Label provider for the title bar for the tabbed property sheet page.
 * 
 * @author Anthony Hunter
 */
public class ElementLabelProvider extends LabelProvider {

	private ITypeMapper typeMapper;

	/**
	 * constructor.
	 */
	public ElementLabelProvider() {
		super();
		typeMapper = new ElementTypeMapper();
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object objects) {
		if (objects == null || objects.equals(StructuredSelection.EMPTY)) {
			return null;
		}
		final boolean multiple[] = { false };
		Object object = getObject(objects, multiple);
		IIconDescriptor icon = null;
		if (object == null) {
			icon = MGraphicElement.getIconDescriptor();
		} else {
			if (object instanceof EditPart) {
				ANode element = (ANode) ((EditPart) object).getModel();
				icon = element.getIconDescriptor();
			} else if (object instanceof ANode) {
				ANode element = (ANode) object;
				icon = element.getIconDescriptor();
			}
		}
		if (icon != null)
			return JaspersoftStudioPlugin.getImage(icon.getIcon32());
		return null;
	}

	/**
	 * @see org.eclipse.jface.viewers.ILabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object objects) {
		if (objects == null || objects.equals(StructuredSelection.EMPTY)) {
			return "No items selected";//$NON-NLS-1$
		}
		final boolean multiple[] = { false };
		final Object object = getObject(objects, multiple);
		if (object == null || ((IStructuredSelection) objects).size() > 1) {
			return ((IStructuredSelection) objects).size() + " items selected";//$NON-NLS-1$
		} else {
			String name = typeMapper.mapType(object).getName();
			if (object instanceof EditPart) {
				ANode element = (ANode) ((EditPart) object).getModel();
				return name.substring(name.lastIndexOf('.') + 2) + ": " + element.getDisplayText();
			}
			return name.substring(name.lastIndexOf('.') + 1);
		}
	}

	/**
	 * Determine if a multiple object selection has been passed to the label provider. If the objects is a
	 * IStructuredSelection, see if all the objects in the selection are the same and if so, we want to provide labels for
	 * the common selected element.
	 * 
	 * @param objects
	 *          a single object or a IStructuredSelection.
	 * @param multiple
	 *          first element in the array is true if there is multiple unequal selected elements in a
	 *          IStructuredSelection.
	 * @return the object to get labels for.
	 */
	private Object getObject(Object objects, boolean multiple[]) {
		Assert.isNotNull(objects);
		Object object = null;
		if (objects instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) objects;
			object = selection.getFirstElement();
			if (selection.size() == 1) {
				// one element selected
				multiple[0] = false;
				return object;
			}
			// multiple elements selected
			multiple[0] = true;
			Class firstClass = typeMapper.mapType(object);
			// determine if all the objects in the selection are the same type
			if (selection.size() > 1) {
				for (Iterator i = selection.iterator(); i.hasNext();) {
					Object next = i.next();
					Class nextClass = typeMapper.mapType(next);
					if (!nextClass.equals(firstClass)) {
						// two elements not equal == multiple selected unequal
						multiple[0] = false;
						object = null;
						break;
					}
				}
			}
		} else {
			multiple[0] = false;
			object = objects;
		}
		return object;
	}

}