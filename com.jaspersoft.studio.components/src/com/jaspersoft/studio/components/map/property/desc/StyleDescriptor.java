/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property.desc;

import java.util.HashSet;
import java.util.Set;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.ItemProperty;
import net.sf.jasperreports.components.map.MapComponent;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ColorPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ComboItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class StyleDescriptor extends ADescriptor {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#getDisplayName
	 * ()
	 */
	@Override
	public String getDisplayName() {
		return "Style"; //$NON-NLS-1$
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#getIcon(
	 * java.lang.Object)
	 */
	@Override
	public Image getIcon(Object element) {
		if (element instanceof Item)
			return Activator.getDefault().getImage("icons/paths_styles-16.png"); //$NON-NLS-1$
		return super.getIcon(element);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.tibcomaps.property.desc.AItemDescriptor#
	 * initItemPropertyDescriptors()
	 */
	@Override
	protected void initItemPropertyDescriptors() {
		itemProperties = new ItemPropertyDescription[] {
				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_name,
						com.jaspersoft.studio.messages.Messages.common_name,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_0,
						false, com.jaspersoft.studio.messages.Messages.StyleDescriptor_1),
				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_style,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_2,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_3,
						false),

				new ColorPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_STYLE_strokeColor,
						Messages.PathDescriptor_7, Messages.PathDescriptor_8,
						false),

				new ItemPropertyDescription<Float>(
						MapComponent.ITEM_PROPERTY_STYLE_strokeOpacity,
						Messages.PathDescriptor_9, Messages.PathDescriptor_10,
						false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_STYLE_strokeWeight,
						Messages.PathDescriptor_11, Messages.PathDescriptor_12,
						false),

				new ColorPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_STYLE_fillColor,
						Messages.PathDescriptor_13, Messages.PathDescriptor_14,
						false),

				new ItemPropertyDescription<Float>(
						MapComponent.ITEM_PROPERTY_STYLE_fillOpacity,
						Messages.PathDescriptor_15, Messages.PathDescriptor_16,
						false),
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_STYLE_isPolygon,
						Messages.PathDescriptor_17, Messages.PathDescriptor_18,
						false, Boolean.FALSE, new String[] {
								"", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_clickable,
						Messages.PathDescriptor_19, Messages.PathDescriptor_20,
						false, Boolean.TRUE,
						new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_STYLE_editable,
						Messages.PathDescriptor_21, Messages.PathDescriptor_22,
						false, Boolean.FALSE,
						new String[] { "", "true", "true" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_draggable,
						Messages.PathDescriptor_23, Messages.PathDescriptor_24,
						false, Boolean.FALSE, new String[] {
								"", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_STYLE_geodesic,
						Messages.PathDescriptor_25, Messages.PathDescriptor_26,
						false, Boolean.FALSE, new String[] {
								"", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_visible,
						Messages.PathDescriptor_27, Messages.PathDescriptor_28,
						false, Boolean.FALSE, new String[] {
								"", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_zIndex,
						Messages.PathDescriptor_29, Messages.PathDescriptor_30,
						false) };
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.tibcomaps.property.desc.AItemDescriptor#validateItem
	 * (java.util.List, net.sf.jasperreports.components.map.ItemData,
	 * net.sf.jasperreports.components.map.Item,
	 * net.sf.jasperreports.components.map.ItemProperty)
	 */
	@Override
	public void validateItem(ItemProperty itemProperty) throws Exception {
		super.validateItem(itemProperty);
		if (itemProperty != null)
			for (ItemData id : itemDatas) {
				if (id.getItems() == null)
					continue;
				for (Item it : id.getItems()) {
					if (it.getProperties() == null)
						continue;
					ItemProperty p = ItemPropertyUtil
							.getProperty(it.getProperties(),
									MapComponent.ITEM_PROPERTY_name);
					if (p == null || p == oldItemProperty)
						continue;
					if (itemProperty.getValue() != null) {
						if (p.getValue() == null)
							continue;
						if (p.getValue().equals(itemProperty.getValue()))
							throw new Exception(
									com.jaspersoft.studio.messages.Messages.StyleDescriptor_4);
					}

				}
			}
		else {
			Set<String> names = new HashSet<String>();
			for (ItemData id : itemDatas) {
				if (id.getItems() == null)
					continue;
				for (Item it : id.getItems()) {
					if (it.getProperties() == null)
						continue;
					ItemProperty p = ItemPropertyUtil
							.getProperty(it.getProperties(),
									MapComponent.ITEM_PROPERTY_name);
					if (p == null || p.getValueExpression() != null
							|| p.getValue() == null)
						continue;
					if (names.contains(p.getValue()))
						throw new Exception(
								com.jaspersoft.studio.messages.Messages.StyleDescriptor_5);
					names.add(p.getValue());
				}
			}
		}
	}
}
