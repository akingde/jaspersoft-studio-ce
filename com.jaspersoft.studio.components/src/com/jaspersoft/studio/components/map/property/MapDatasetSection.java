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
import java.util.List;

import net.sf.jasperreports.components.map.ItemData;
import net.sf.jasperreports.components.map.StandardItem;
import net.sf.jasperreports.components.map.StandardItemData;
import net.sf.jasperreports.components.map.StandardItemProperty;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.design.JRDesignExpression;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.forms.events.HyperlinkAdapter;
import org.eclipse.ui.forms.events.HyperlinkEvent;
import org.eclipse.ui.forms.widgets.FormText;

import com.buzzcoders.yasw.widgets.map.core.LatLng;
import com.buzzcoders.yasw.widgets.map.core.MapType;
import com.buzzcoders.yasw.widgets.map.ui.MarkersPickupDialog;
import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.MMap;
import com.jaspersoft.studio.components.map.model.marker.dialog.MarkerPage;
import com.jaspersoft.studio.properties.view.TabbedPropertySheetPage;
import com.jaspersoft.studio.property.section.AbstractSection;

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
			private MarkerPage.BasicMapInfo mapInfo;

			@Override
			public void linkActivated(HyperlinkEvent e) {
				MMap mmap = (MMap) getElement();
				MarkersPickupDialog staticMarkersDialog = new MarkersPickupDialog(
						UIUtils.getShell()) {
					@Override
					protected void configureShell(Shell newShell) {
						super.configureShell(newShell);
						UIUtils.resizeAndCenterShell(newShell, 800, 600);
					}
				};
				if (mapInfo == null)
					mapInfo = MarkerPage.getBasicMapInformation(mmap);
				if (mapInfo.getLatitude() != null
						&& mapInfo.getLongitude() != null)
					staticMarkersDialog.setInitialPosition(new LatLng(mapInfo
							.getLatitude(), mapInfo.getLongitude(), true));
				if (mapInfo.getMapType() != null)
					staticMarkersDialog.setType(MapType.fromStringID(mapInfo
							.getMapType().getName()));
				if (mapInfo.getZoom() != 0)
					staticMarkersDialog.setZoom(mapInfo.getZoom());
				if (staticMarkersDialog.open() == Window.OK) {
					List<LatLng> markersList = staticMarkersDialog
							.getMarkersList();
					List<ItemData> markers = new ArrayList<ItemData>();
					StandardItemData sid = new StandardItemData();
					markers.add(sid);
					for (LatLng m : markersList) {
						StandardItem newMarker = new StandardItem();
						newMarker
								.addItemProperty(new StandardItemProperty(
										"latitude", null, new JRDesignExpression(m.getLat().floatValue() + "f"))); //$NON-NLS-1$ //$NON-NLS-2$
						newMarker
								.addItemProperty(new StandardItemProperty(
										"longitude", null, new JRDesignExpression(m.getLng().floatValue() + "f"))); //$NON-NLS-1$ //$NON-NLS-2$
						sid.addItem(newMarker);
						// mmap.getValue().getMarkers().add(newMarker);
					}
					changeProperty(
							StandardMapComponent.PROPERTY_MARKER_DATA_LIST,
							markers);
				}
			}
		});

		createWidget4Property(parent,
				StandardMapComponent.PROPERTY_MARKER_DATA_LIST, false);
	}
}
