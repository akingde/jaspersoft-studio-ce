/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.property.itemproperty.desc;

import java.util.List;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.eclipse.util.Misc;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public abstract class ADescriptor {
	
	protected boolean showAllProperties = false;
	
	protected ItemPropertyDescription<?>[] itemProperties;
	
	protected List<ItemData> itemDatas;
	
	protected ItemData itemData;
	
	protected Item item;
	
	protected ItemProperty oldItemProperty;
	
	protected APropertyNode pnode;


	public ADescriptor() {
	}

	public boolean isShowAllProperties() {
		return showAllProperties;
	}

	/**
	 * @return the itemProperties
	 */
	public ItemPropertyDescription<?>[] getItemPropertyDescriptors() {
		if (itemProperties == null)
			initItemPropertyDescriptors();
		return itemProperties;
	}

	public ItemPropertyDescription<?> getDescription(String id) {
		for (ItemPropertyDescription<?> ip : getItemPropertyDescriptors())
			if (ip.getName().equals(id))
				return ip;
		return null;
	}

	public void setupDefaultValue(Item selected, StandardItemProperty newitem) {
	}

	public String getDisplayName() {
		return Messages.ADescriptor_0;
	}

	protected abstract void initItemPropertyDescriptors();
	
	public abstract IPropertyEditor getPropertyEditor();

	public Image getIcon(Object element) {
		if (element instanceof ItemData)
			return JaspersoftStudioPlugin.getInstance().getImage("icons/resources/datasets-16.png"); //$NON-NLS-1$
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

	public ItemData getItemData() {
		return itemData;
	}

	public void setItemDatas(List<ItemData> itemDatas, APropertyNode pnode) {
		this.itemDatas = itemDatas;
		this.pnode = pnode;
	}

	public void validateItem(ItemProperty itemProperty) throws ValidationError {
		validateItem(itemProperty, false);
	}

	public void validateItem(ItemProperty itemProperty, boolean isNew) throws ValidationError {
		if (itemProperty != null) {
			if (Misc.isNullOrEmpty(itemProperty.getName()))
				throw new ValidationError(Messages.ADescriptor_2);
			if (item != null)
				for (ItemProperty ip : item.getProperties()) {
					if (isNew || oldItemProperty == ip || ip == itemProperty)
						continue;
					if (ip.getName().equals(itemProperty.getName()))
						throw new ValidationError(itemProperty.getName(), Messages.ADescriptor_3);
				}
		} else if (itemDatas != null)
			for (ItemData id : itemDatas)
				validateItems(id, getItemPropertyDescriptors());
		else if (itemData != null)
			validateItems(itemData, getItemPropertyDescriptors());
	}

	public static void validateItems(ItemData id, ItemPropertyDescription<?>[] itemPropDesc) throws ValidationError {
		if (id.getItems() == null)
			return;
		for (Item it : id.getItems()) {
			if (it.getProperties() == null)
				continue;
			for (ItemPropertyDescription<?> ipd : itemPropDesc) {
				if (ipd.isMandatory()) {
					ItemProperty p = ItemPropertyUtil.getProperty(it.getProperties(), ipd.getName());
					if (p == null || ((p.getValueExpression() == null || Misc.isNullOrEmpty(p.getValueExpression().getText()))
							&& Misc.isNullOrEmpty(p.getValue())))
						throw new ValidationError(ipd.getName(), ipd.getLabel() + " is mandatory property.");
				}
			}
		}
	}

}
