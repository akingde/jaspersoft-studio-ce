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

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.components.map.MapComponent;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.property.itemproperty.desc.ColorPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ComboItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.ItemPropertyDescription;
import com.jaspersoft.studio.property.itemproperty.desc.NumberPropertyDescription;
import com.jaspersoft.studio.utils.Misc;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class PathDescriptor extends ADescriptor {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#getDisplayName
	 * ()
	 */
	@Override
	public String getDisplayName() {
		return Messages.PathDescriptor_0;
	}

	@Override
	public void setupDefaultValue(Item selected, StandardItemProperty newitem) {
		if (selected != null && newitem.getName().equals("name")) { //$NON-NLS-1$
			ItemProperty ip = ItemPropertyUtil.getProperty(
					selected.getProperties(), "name"); //$NON-NLS-1$
			if (ip != null && !Misc.isNullOrEmpty(ip.getValue()))
				newitem.setValue(ip.getValue());
		}
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
			return Activator.getDefault().getImage("icons/path-icon-16.png"); //$NON-NLS-1$
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
						Messages.PathDescriptor_1, Messages.PathDescriptor_2,
						false, MapComponent.DEFAULT_PATH_NAME),

				new NumberPropertyDescription<Float>(
						MapComponent.ITEM_PROPERTY_latitude,
						Messages.MarkerPage_LatitudeColumn, "", false,
						new Float("37.7833"),
						new Float("-85f"), new Float("85f")), //$NON-NLS-1$
				new NumberPropertyDescription<Float>(
						MapComponent.ITEM_PROPERTY_longitude,
						Messages.MarkerPage_LongitudeColumn, "", false,
						new Float("-122.4167"), new Float("-180"), new Float(
								"180")), //$NON-NLS-1$

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_address,
						Messages.PathDescriptor_3, Messages.PathDescriptor_4,
						false),

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_style,
						Messages.PathDescriptor_5, Messages.PathDescriptor_6,
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
						false),

		};
	}

	@Override
	public void validateItem(ItemProperty itemProperty) throws Exception {
		super.validateItem(itemProperty);
		if (itemProperty == null)
			for (ItemData id : itemDatas) {
				if (id.getItems() == null)
					continue;
				for (Item it : id.getItems()) {
					if (it.getProperties() == null)
						continue;
					boolean address = false;
					boolean lon = false;
					boolean lat = false;
					if (ItemPropertyUtil.hasValue(it.getProperties(),
							MapComponent.ITEM_PROPERTY_latitude))
						lat = true;
					if (ItemPropertyUtil.hasValue(it.getProperties(),
							MapComponent.ITEM_PROPERTY_longitude))
						lon = true;
					if (ItemPropertyUtil.hasValue(it.getProperties(),
							MapComponent.ITEM_PROPERTY_address)) {
						address = true;
						break;
					}
					if (!address && !(lon && lat))
						throw new Exception(
								"You must have Address or Latitude/Longitude");
				}
			}
	}
}
