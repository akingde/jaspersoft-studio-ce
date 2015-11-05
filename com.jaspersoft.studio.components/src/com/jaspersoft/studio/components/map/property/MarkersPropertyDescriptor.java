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
package com.jaspersoft.studio.components.map.property;

import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.map.property.desc.MarkersDescriptor;
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
public class MarkersPropertyDescriptor extends AItemDataListPropertyDescriptor {

	public MarkersPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	protected void initShowColumns() {
		descriptor = new MarkersDescriptor();
	}

	protected SPItemDataList createSPWidget(Composite parent,
			AbstractSection section) {
		return new SPItemDataList(parent, section, this, false) {
			@Override
			protected AItemDialog createItemDialog() {
				return new FormItemDialog(UIUtils.getShell(), getDescriptor(),
						(JasperReportsConfiguration) section
								.getJasperReportsContext(), false, false) {

					@Override
					protected void createValues(Composite cmp) {
						Label lbl = new Label(cmp, SWT.NONE);
						GridData gd = new GridData(GridData.FILL_HORIZONTAL);
						gd.horizontalSpan = 2;
						lbl.setLayoutData(gd);
						lbl.setText(com.jaspersoft.studio.messages.Messages.MapSection_1);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_latitude);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_longitude);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_address);

						createSeparator(cmp);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_title);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_url);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_target);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_icon);

						Composite c = createSection(
								cmp,
								com.jaspersoft.studio.components.map.messages.Messages.MarkersPropertyDescriptor_1,
								false);

						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_url);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_width);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_height);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_x);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_y);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_x);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_y);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_shadow);

						c = createSection(
								cmp,
								com.jaspersoft.studio.components.map.messages.Messages.MarkersPropertyDescriptor_2,
								false);

						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_url);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_width);
						createItemProperty(c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_height);
						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_x);
						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_y);
						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_x);
						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_y);

						c = createSection(
								cmp,
								com.jaspersoft.studio.components.map.messages.Messages.MarkersPropertyDescriptor_0,
								false);

						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_content);
						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_pixelOffset);
						createItemProperty(
								c,
								MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_maxWidth);

						createSeparator(cmp);

						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_color);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_label);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_cursor);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_zIndex);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_clickable);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_draggable);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_flat);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_optimized);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_raiseOnDrag);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_visible);
						createItemProperty(cmp,
								MapComponent.ITEM_PROPERTY_MARKER_size);
					}

				};
			}
		};
	}
}
