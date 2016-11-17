/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.components.map.property.desc.StyleDescriptor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.itemproperty.sp.SPItemDataList;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class StylePropertyDescriptor extends AItemDataListPropertyDescriptor {

	public StylePropertyDescriptor(Object id, String displayName, APropertyNode pNode) {
		super(id, displayName, pNode);
	}

	@Override
	protected void initShowColumns() {
		descriptor = new StyleDescriptor();
	}

	protected SPItemDataList createSPWidget(Composite parent, AbstractSection section) {
		return new SPItemDataList(parent, section, this, false) {
			@Override
			protected AItemDialog createItemDialog() {
				return new FormItemDialog(UIUtils.getShell(), getDescriptor(),
						(JasperReportsConfiguration) section.getJasperReportsContext(), false, false) {

					@Override
					protected void createValues(Composite cmp) {
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_name);

						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_style);

						createSeparator(cmp);

						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_strokeColor);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_strokeOpacity);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_strokeWeight);

						createSeparator(cmp);

						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_fillColor);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_fillOpacity);

						createSeparator(cmp);

						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_isPolygon);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_clickable);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_editable);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_draggable);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_STYLE_geodesic);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_visible);
						createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_zIndex);
					}

				};
			}
		};
	}
}
