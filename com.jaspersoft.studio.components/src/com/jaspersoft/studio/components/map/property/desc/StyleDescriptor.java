/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property.desc;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.swt.graphics.Image;

import com.jaspersoft.studio.components.Activator;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.properties.view.validation.ValidationError;
import com.jaspersoft.studio.property.itemproperty.desc.ADescriptor;
import com.jaspersoft.studio.widgets.framework.IPropertyEditor;
import com.jaspersoft.studio.widgets.framework.PropertyEditorAdapter;
import com.jaspersoft.studio.widgets.framework.ui.ColorPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ComboItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.FloatPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.IntegerPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.ItemPropertyDescription;
import com.jaspersoft.studio.widgets.framework.ui.TextPropertyDescription;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.engine.JRExpression;

/**
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class StyleDescriptor extends ADescriptor {
	
	private IPropertyEditor standardItemEditor = new PropertyEditorAdapter() {
		
		@Override
		public JRExpression getPropertyValueExpression(String propertyName) {

			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					StandardItemProperty stdProp = (StandardItemProperty)prop;
					return stdProp.getValueExpression();
				}
			}
			return null;
		}
		
		@Override
		public String getPropertyValue(String propertyName) {

			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					StandardItemProperty stdProp = (StandardItemProperty)prop;
					return stdProp.getValue();
				}
			}
			return null;
		}
		
		@Override
		public void createUpdateProperty(String propertyName, String value, JRExpression valueExpression) {
			boolean found = false;
			
			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					StandardItemProperty stdProp = (StandardItemProperty)prop;
					stdProp.setValue(value);
					stdProp.setValueExpression(valueExpression);
					found = true; 
					break;
				}
			}
			if (!found){
				((StandardItem)item).addItemProperty(new StandardItemProperty(propertyName, value, valueExpression));
			}
		}
		
		@Override
		public void removeProperty(String propertyName) {
			for(ItemProperty prop : item.getProperties()){
				if (prop.getName().equals(propertyName)){
					((StandardItem)item).removeItemProperty(prop);
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
		return com.jaspersoft.studio.messages.Messages.StyleDescriptor_6;
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
		itemProperties = new ItemPropertyDescription<?>[] {
				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_name,
						com.jaspersoft.studio.messages.Messages.common_name,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_0, true,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_1),
				new TextPropertyDescription<String>(MapComponent.ITEM_PROPERTY_style,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_2,
						com.jaspersoft.studio.messages.Messages.StyleDescriptor_3, false),

				new ColorPropertyDescription<String>(MapComponent.ITEM_PROPERTY_STYLE_strokeColor,
						Messages.PathDescriptor_7, Messages.PathDescriptor_8, false),

				new FloatPropertyDescription(MapComponent.ITEM_PROPERTY_STYLE_strokeOpacity,
						Messages.PathDescriptor_9, Messages.PathDescriptor_10, false, new Float(0), new Float(1)),
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_STYLE_strokeWeight,
						Messages.PathDescriptor_11, Messages.PathDescriptor_12, false, new Integer(0), null),

				new ColorPropertyDescription<String>(MapComponent.ITEM_PROPERTY_STYLE_fillColor,
						Messages.PathDescriptor_13, Messages.PathDescriptor_14, false),

				new FloatPropertyDescription(MapComponent.ITEM_PROPERTY_STYLE_fillOpacity,
						Messages.PathDescriptor_15, Messages.PathDescriptor_16, false, null, null),
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_STYLE_isPolygon,
						Messages.PathDescriptor_17, Messages.PathDescriptor_18, false, Boolean.FALSE,
						new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_clickable,
						Messages.PathDescriptor_19, Messages.PathDescriptor_20, false, Boolean.TRUE,
						new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_STYLE_editable,
						Messages.PathDescriptor_21, Messages.PathDescriptor_22, false, Boolean.FALSE,
						new String[] { "", "true", "true" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_draggable,
						Messages.PathDescriptor_23, Messages.PathDescriptor_24, false, Boolean.FALSE,
						new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_STYLE_geodesic,
						Messages.PathDescriptor_25, Messages.PathDescriptor_26, false, Boolean.FALSE,
						new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new ComboItemPropertyDescription<Boolean>(MapComponent.ITEM_PROPERTY_visible,
						Messages.PathDescriptor_27, Messages.PathDescriptor_28, false, Boolean.FALSE,
						new String[] { "", "true", "false" }), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				new IntegerPropertyDescription(MapComponent.ITEM_PROPERTY_MARKER_zIndex,
						Messages.PathDescriptor_29, Messages.PathDescriptor_30, false, new Integer(0), null) };
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
		if (itemProperty != null)
			for (ItemData id : itemDatas) {
				if (id.getItems() == null)
					continue;
				for (Item it : id.getItems()) {
					if (it.getProperties() == null)
						continue;
					ItemProperty p = ItemPropertyUtil.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_name);
					if (p == null || p == oldItemProperty)
						continue;
					if (itemProperty.getValue() != null) {
						if (p.getValue() == null)
							continue;
						if (p.getValue().equals(itemProperty.getValue()))
							throw new ValidationError(itemProperty.getName(),
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
					ItemProperty p = ItemPropertyUtil.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_name);
					if (p == null || p.getValueExpression() != null || p.getValue() == null)
						continue;
					if (names.contains(p.getValue()))
						throw new ValidationError(p.getName(),
								com.jaspersoft.studio.messages.Messages.StyleDescriptor_5);
					names.add(p.getValue());
				}
			}
		}
	}

	@Override
	public IPropertyEditor getPropertyEditor() {
		return standardItemEditor;
	}
}
