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

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class MarkersDescriptor extends ADescriptor {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#getDisplayName
	 * ()
	 */
	@Override
	public String getDisplayName() {
		return Messages.MarkersDescriptor_0;
	}

	@Override
	public Image getIcon(Object element) {
		if (element instanceof Item)
			return Activator.getDefault().getImage("icons/map-16.png"); //$NON-NLS-1$
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
						MapComponent.ITEM_PROPERTY_MARKER_title,
						Messages.MarkersDescriptor_1, "", false, "Marker"), //$NON-NLS-1$

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
						Messages.MarkersDescriptor_3,
						Messages.MarkersDescriptor_4, false),

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_url,
						Messages.MarkersDescriptor_5,
						Messages.MarkersDescriptor_6, false),
				new ComboItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_target,
						Messages.MarkersDescriptor_7,
						Messages.MarkersDescriptor_8, false, new String[] {
								"_blank", "_parent", "_self", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								"_top", "" }, false), //$NON-NLS-1$ //$NON-NLS-2$

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_icon,
						Messages.MarkersDescriptor_14,
						Messages.MarkersDescriptor_15, false),
				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_url,
						Messages.MarkersDescriptor_16,
						Messages.MarkersDescriptor_17, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_width,
						Messages.MarkersDescriptor_18,
						Messages.MarkersDescriptor_19, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_height,
						Messages.MarkersDescriptor_20,
						Messages.MarkersDescriptor_21, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_x,
						Messages.MarkersDescriptor_22,
						Messages.MarkersDescriptor_23, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_y,
						Messages.MarkersDescriptor_24,
						Messages.MarkersDescriptor_25, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_x,
						Messages.MarkersDescriptor_26,
						Messages.MarkersDescriptor_27, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_y,
						Messages.MarkersDescriptor_28,
						Messages.MarkersDescriptor_29, false),

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_shadow,
						Messages.MarkersDescriptor_30,
						Messages.MarkersDescriptor_31, false),
				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_url,
						Messages.MarkersDescriptor_32,
						Messages.MarkersDescriptor_33, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_width,
						Messages.MarkersDescriptor_34,
						Messages.MarkersDescriptor_35, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_height,
						Messages.MarkersDescriptor_36,
						Messages.MarkersDescriptor_37, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_x,
						Messages.MarkersDescriptor_38,
						Messages.MarkersDescriptor_39, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_y,
						Messages.MarkersDescriptor_40,
						Messages.MarkersDescriptor_41, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_x,
						Messages.MarkersDescriptor_42,
						Messages.MarkersDescriptor_43, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_y,
						Messages.MarkersDescriptor_44,
						Messages.MarkersDescriptor_45, false),

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_content,
						Messages.MarkersDescriptor_46,
						Messages.MarkersDescriptor_47, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_pixelOffset,
						Messages.MarkersDescriptor_48,
						Messages.MarkersDescriptor_49, false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_maxWidth,
						Messages.MarkersDescriptor_50,
						Messages.MarkersDescriptor_51, false),

				new ColorPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_color,
						Messages.MarkersDescriptor_52,
						Messages.MarkersDescriptor_53, false),

				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_label,
						Messages.MarkerPage_LabelColumn,
						Messages.MarkersDescriptor_54, false),
				new ItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_cursor,
						Messages.MarkersDescriptor_55, "", false),
				new ItemPropertyDescription<Integer>(
						MapComponent.ITEM_PROPERTY_MARKER_zIndex,
						Messages.MarkersDescriptor_56, "", false),
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_clickable,
						Messages.MarkersDescriptor_57, "", false,
						Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_draggable,
						Messages.MarkersDescriptor_58, "", false,
						Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_MARKER_flat,
						Messages.MarkersDescriptor_59, "", false,
						Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_MARKER_optimized,
						Messages.MarkersDescriptor_60, "", false,
						Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_MARKER_raiseOnDrag,
						Messages.MarkersDescriptor_61, "", false,
						Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(
						MapComponent.ITEM_PROPERTY_visible,
						Messages.MarkersDescriptor_62, "", false,
						Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<String>(
						MapComponent.ITEM_PROPERTY_MARKER_size,
						Messages.MarkersDescriptor_63,
						Messages.MarkersDescriptor_64, false, new String[] {
								"", "mid", "tiny", "small" }) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		};
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
