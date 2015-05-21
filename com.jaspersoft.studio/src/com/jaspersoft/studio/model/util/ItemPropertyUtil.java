/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/

package com.jaspersoft.studio.model.util;

import java.util.List;

import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemProperty;

import org.eclipse.ui.views.properties.IPropertyDescriptor;

import com.jaspersoft.studio.property.infoList.ElementDescription;
import com.jaspersoft.studio.property.itemproperty.ItemPropertyDescriptor;

public class ItemPropertyUtil {
	public static ItemProperty getProperty(List<ItemProperty> items, String name) {
		if (items != null)
			for (ItemProperty it : items)
				if (it.getName().equals(name))
					return it;
		return null;
	}

	public static void setProperty(StandardItem item, String key, ItemProperty ip) {
		List<ItemProperty> props = item.getProperties();
		if (props != null) {
			StandardItemProperty sp = (StandardItemProperty) getProperty(props, key);
			if (sp != null) {
				if (ip == null)
					item.removeItemProperty(sp);
				else {
					sp.setValue(ip.getValue());
					sp.setValueExpression(ip.getValueExpression());
				}
				return;
			}
		}
		item.addItemProperty(ip);
	}

	public static void setupElementDescriptors(List<ElementDescription> elementDescriptions,
			List<IPropertyDescriptor> descriptors) {
		for (IPropertyDescriptor pd : descriptors)
			if (pd instanceof ItemPropertyDescriptor)
				((ItemPropertyDescriptor) pd).setElementDescriptions(elementDescriptions);
	}
}
