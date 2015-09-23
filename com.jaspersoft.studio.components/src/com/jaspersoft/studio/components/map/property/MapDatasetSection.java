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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.components.map.Item;
import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.BasicMapInfoData;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.MMap;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.ui.MarkersPickupDialog;

public class MapDatasetSection extends AbstractSection {

	@Override
	public void createControls(final Composite parent,
			TabbedPropertySheetPage tabbedPropertySheetPage) {
		super.createControls(parent, tabbedPropertySheetPage);

		FormText mapPickSuggestion = new FormText(parent, SWT.NONE);
		mapPickSuggestion.setText(Messages.MarkerPage_0, true, false);
		mapPickSuggestion.setLayoutData(new GridData(
				GridData.HORIZONTAL_ALIGN_CENTER));
		mapPickSuggestion.setWhitespaceNormalized(true);
		mapPickSuggestion.addHyperlinkListener(new HyperlinkAdapter() {

			@Override
			public void linkActivated(HyperlinkEvent e) {
				MMap mmap = (MMap) getElement();
				MarkersPickupDialog d = new MarkersPickupDialog(UIUtils
						.getShell()) {
					@Override
					protected void configureShell(Shell newShell) {
						super.configureShell(newShell);
						UIUtils.resizeAndCenterShell(newShell, 800, 600);
					}
				};
				BasicMapInfoData mapInfo = mmap.getBasicMapInformation();
				if (mapInfo.getLatitude() != null
						&& mapInfo.getLongitude() != null)
					d.setMapCenter(new LatLng(mapInfo.getLatitude(), mapInfo
							.getLongitude(), true));
				if (mapInfo.getAddress() != null)
					d.setAddress(mapInfo.getAddress());
				if (mapInfo.getMapType() != null)
					d.setMapType(MapType.fromStringID(mapInfo.getMapType()
							.getName()));
				if (mapInfo.getZoom() != 0)
					d.setZoomLevel(mapInfo.getZoom());

				Map<LatLng, StandardItem> map = new HashMap<LatLng, StandardItem>();
				List<ItemData> oldMarkers = (List<ItemData>) mmap
						.getPropertyValue(StandardMapComponent.PROPERTY_MARKER_DATA_LIST);
				List<ItemData> newMarkers = new ArrayList<ItemData>();
				if (oldMarkers != null) {
					for (ItemData id : oldMarkers) {
						id = (ItemData) id.clone();
						newMarkers.add(id);
						for (Item it : id.getItems()) {
							StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil.getProperty(
									it.getProperties(),
									MapComponent.ITEM_PROPERTY_latitude);
							if (ip == null)
								continue;
							Double lat = ItemPropertyUtil
									.getItemPropertyDouble(ip, null);
							if (lat == null)
								continue;

							ip = (StandardItemProperty) ItemPropertyUtil.getProperty(
									it.getProperties(),
									MapComponent.ITEM_PROPERTY_longitude);
							if (ip == null)
								continue;
							Double lon = ItemPropertyUtil
									.getItemPropertyDouble(ip, null);
							if (lon == null)
								continue;
							LatLng m = new LatLng(lat, lon);
							map.put(m, (StandardItem) it);
							d.getMarkersList().add(m);
						}
					}
				}
				if (d.open() == Window.OK) {
					List<LatLng> markersList = d.getMarkersList();
					StandardItemData sid = null;
					for (LatLng m : markersList) {
						StandardItem si = map.get(m);
						if (si != null) {
							StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil.getProperty(
									si.getProperties(),
									MapComponent.ITEM_PROPERTY_latitude);
							if (ip.getValueExpression() != null)
								ip.setValueExpression(new JRDesignExpression(m
										.getLat().toString()));
							else
								ip.setValue(m.getLat().toString());
							ip = (StandardItemProperty) ItemPropertyUtil.getProperty(
									si.getProperties(),
									MapComponent.ITEM_PROPERTY_longitude);
							if (ip.getValueExpression() != null)
								ip.setValueExpression(new JRDesignExpression(m
										.getLng().toString()));
							else
								ip.setValue(m.getLng().toString());
						} else {
							// will add it to the last itemdata, so we append
							// markers
							if (sid == null) {
								if (newMarkers.isEmpty()) {
									sid = new StandardItemData();
									newMarkers.add(sid);
								} else
									sid = (StandardItemData) newMarkers
											.get(newMarkers.size() - 1);
							}
							si = new StandardItem();
							si.addItemProperty(new StandardItemProperty(
									MapComponent.ITEM_PROPERTY_latitude, m
											.getLat().floatValue() + "f", null)); //$NON-NLS-1$ //$NON-NLS-2$
							si.addItemProperty(new StandardItemProperty(
									MapComponent.ITEM_PROPERTY_longitude, m
											.getLng().floatValue() + "f", null)); //$NON-NLS-1$ //$NON-NLS-2$
							sid.addItem(si);
						}
					}
					changeProperty(
							StandardMapComponent.PROPERTY_MARKER_DATA_LIST,
							newMarkers);
				}
			}
		});

		createWidget4Property(parent,
				StandardMapComponent.PROPERTY_MARKER_DATA_LIST, false);
	}
}
