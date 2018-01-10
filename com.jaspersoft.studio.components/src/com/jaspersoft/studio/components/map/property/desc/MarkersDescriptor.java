/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property.desc;

import java.math.BigDecimal;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.ui.BigDecimalPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ColorPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.IntegerPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class MarkersDescriptor extends ADescriptor {

	private IPropertyEditor standardItemEditor = new PropertyEditorAdapter() {

		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {

			for (ItemProperty prop : item.getProperties()) {
				if (prop.getName().equals(propertyName)) {
					StandardItemProperty stdProp = (StandardItemProperty) prop;
					return stdProp.getValueExpression();
				}
			}
			return null;
		}

		@Override
		public String getPropertyValue(String propertyName) {

			for (ItemProperty prop : item.getProperties()) {
				if (prop.getName().equals(propertyName)) {
					StandardItemProperty stdProp = (StandardItemProperty) prop;
					return stdProp.getValue();
				}
			}
			return null;
		}

		@Override
		public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
			boolean found = false;

			for (ItemProperty prop : item.getProperties()) {
				if (prop.getName().equals(propertyName)) {
					StandardItemProperty stdProp = (StandardItemProperty) prop;
					stdProp.setValue(value);
					stdProp.setValueExpression(valueExpression);
					found = true;
					break;
				}
			}
			if (!found) {
				((StandardItem) item).addItemProperty(new StandardItemProperty(propertyName, value, valueExpression));
			}
		}

		@Override
		public void removeProperty(String propertyName) {
			for (ItemProperty prop : item.getProperties()) {
				if (prop.getName().equals(propertyName)) {
					((StandardItem) item).removeItemProperty(prop);
					break;
				}
			}
		}
	};

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.property.itemproperty.desc.ADescriptor#
	 * getDisplayName ()
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
		itemProperties = new ItemPropertyDescription<?>[] {
				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_title,
						Messages.MarkersDescriptor_1, "", false, Messages.MarkersDescriptor_0), //$NON-NLS-1$

				new BigDecimalPropertyDescription(MapComponent.ITEM_PROPERTY_latitude, Messages.MarkerPage_LatitudeColumn,
						"", false, new BigDecimal("37.7833"), new BigDecimal("-85"), new BigDecimal("85")),
				new BigDecimalPropertyDescription(MapComponent.ITEM_PROPERTY_longitude, Messages.MarkerPage_LongitudeColumn,
						"", false, new BigDecimal("-122.4167"), new BigDecimal("-180"), new BigDecimal("180")),

				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_address, Messages.MarkersDescriptor_3,
						Messages.MarkersDescriptor_4, false),

				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_url, Messages.MarkersDescriptor_5,
						Messages.MarkersDescriptor_6, false),
				new ComboItemPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_target,
						Messages.MarkersDescriptor_7, Messages.MarkersDescriptor_8, false,
						new String[] { "_blank", "_parent", "_self", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
								"_top", "" }),

				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_icon,
						Messages.MarkersDescriptor_14, Messages.MarkersDescriptor_15, false),
				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_ICON_url,
						Messages.MarkersDescriptor_16, Messages.MarkersDescriptor_17, false),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_ICON_width,
						Messages.MarkersDescriptor_18, Messages.MarkersDescriptor_19, false, new Integer(0), null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_ICON_height,
						Messages.MarkersDescriptor_20, Messages.MarkersDescriptor_21, false, new Integer(0), null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_x,
						Messages.MarkersDescriptor_22, Messages.MarkersDescriptor_23, false, new Integer(0), null,
						null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_y,
						Messages.MarkersDescriptor_24, Messages.MarkersDescriptor_25, false, new Integer(0), null,
						null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_x,
						Messages.MarkersDescriptor_26, Messages.MarkersDescriptor_27, false, new Integer(0), null,
						null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_y,
						Messages.MarkersDescriptor_28, Messages.MarkersDescriptor_29, false, new Integer(0), null,
						null),

				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_shadow,
						Messages.MarkersDescriptor_30, Messages.MarkersDescriptor_31, false),
				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_url,
						Messages.MarkersDescriptor_32, Messages.MarkersDescriptor_33, false),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_width,
						Messages.MarkersDescriptor_34, Messages.MarkersDescriptor_35, false, new Integer(0), null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_height,
						Messages.MarkersDescriptor_36, Messages.MarkersDescriptor_37, false, new Integer(0), null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_x,
						Messages.MarkersDescriptor_38, Messages.MarkersDescriptor_39, false, new Integer(0), null,
						null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_y,
						Messages.MarkersDescriptor_40, Messages.MarkersDescriptor_41, false, new Integer(0), null,
						null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_x,
						Messages.MarkersDescriptor_42, Messages.MarkersDescriptor_43, false, new Integer(0), null,
						null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_y,
						Messages.MarkersDescriptor_44, Messages.MarkersDescriptor_45, false, new Integer(0), null,
						null),

				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_content,
						Messages.MarkersDescriptor_46, Messages.MarkersDescriptor_47, false),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_pixelOffset,
						Messages.MarkersDescriptor_48, Messages.MarkersDescriptor_49, false, null, null),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_maxWidth,
						Messages.MarkersDescriptor_50, Messages.MarkersDescriptor_51, false, new Integer(0), null),

				new ColorPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_color,
						Messages.MarkersDescriptor_52, Messages.MarkersDescriptor_53, false),

				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_label,
						Messages.MarkerPage_LabelColumn, Messages.MarkersDescriptor_54, false),
				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_cursor,
						Messages.MarkersDescriptor_55, "", false), //$NON-NLS-1$
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_zIndex, Messages.MarkersDescriptor_56,
						"", false, new Integer(0), null), //$NON-NLS-1$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_clickable,
						Messages.MarkersDescriptor_57, "", false, Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_draggable,
						Messages.MarkersDescriptor_58, "", false, Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_MARKER_flat,
						Messages.MarkersDescriptor_59, "", false, Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_MARKER_optimized,
						Messages.MarkersDescriptor_60, "", false, Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_MARKER_raiseOnDrag,
						Messages.MarkersDescriptor_61, "", false, Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_visible,
						Messages.MarkersDescriptor_62, "", false, Boolean.FALSE, new String[] { "", "true", "false" }), //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
				new ComboItemPropertyDescription<String>(MapComponent.ITEM_PROPERTY_MARKER_size,
						Messages.MarkersDescriptor_63, Messages.MarkersDescriptor_64, false,
						new String[] { "", "mid", "tiny", "small" }) //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$

		};
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.tibcomaps.property.desc.AItemDescriptor#
	 * validateItem (java.util.List,
	 * net.sf.jasperreports.components.map.ItemData,
	 * net.sf.jasperreports.components.map.Item,
	 * net.sf.jasperreports.components.map.ItemProperty)
	 */
	@Override
	public void validateItem(ItemProperty itemProperty) throws ValidationError {
		super.validateItem(itemProperty);
		if (itemProperty == null)
			for (ItemData id : itemDatas) {
				if (id.getItems() == null)
					continue;
				for (Item it : id.getItems()) {
					if (Misc.isNullOrEmpty(it.getProperties()))
						throw new ValidationError(Messages.MarkersDescriptor_75);
					boolean address = false;
					boolean lon = false;
					boolean lat = false;
					if (ItemPropertyUtil.hasValue(it.getProperties(), MapComponent.ITEM_PROPERTY_latitude))
						lat = true;
					if (ItemPropertyUtil.hasValue(it.getProperties(), MapComponent.ITEM_PROPERTY_longitude))
						lon = true;
					if (ItemPropertyUtil.hasValue(it.getProperties(), MapComponent.ITEM_PROPERTY_address))
						address = true;
					if (!address && !(lon && lat))
						throw new ValidationError(Messages.MarkersDescriptor_76);
				}
			}
	}

	@Override
	public IPropertyEditor getPropertyEditor() {
		return standardItemEditor;
	}
}
