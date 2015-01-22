/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import java.util.List;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.StandardItemProperty;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.utils.Misc;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public abstract class ADescriptor {
	protected ItemPropertyDescriptor<?>[] itemProperties;

	/**
	 * @return the itemProperties
	 */
	public ItemPropertyDescriptor<?>[] getItemPropertyDescriptors() {
		if (itemProperties == null)
			initItemPropertyDescriptors();
		return itemProperties;
	}

	public void setupDefaultValue(Item selected, StandardItemProperty newitem) {
	}

	public String getDisplayName() {
		return "Item";
	}

	protected abstract void initItemPropertyDescriptors();

	protected List<ItemData> itemDatas;
	protected ItemData itemData;
	protected Item item;
	protected ItemProperty oldItemProperty;

	public Image getIcon(Object element) {
		if (element instanceof ItemData)
			return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/datasets-16.png");
		return null;
	}

	public void setOldItemProperty(ItemProperty oldItemProperty) {
		this.oldItemProperty = oldItemProperty;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public void setItemData(ItemData itemData) {
		this.itemData = itemData;
	}

	public void setItemDatas(List<ItemData> itemDatas) {
		this.itemDatas = itemDatas;
	}

	public void validateItem(ItemProperty itemProperty) throws Exception {
		if (itemProperty != null) {
			if (Misc.isNullOrEmpty(itemProperty.getName()))
				throw new Exception("Name of the property could not be empty.");
			for (ItemProperty ip : item.getProperties()) {
				if (oldItemProperty == ip)
					continue;
				if (ip.getName().equals(itemProperty.getName()))
					throw new Exception("Name of the property is unique. Your name already exists.");
			}
		}
	}
}
