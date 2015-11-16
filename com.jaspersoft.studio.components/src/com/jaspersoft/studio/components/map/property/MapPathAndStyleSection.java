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

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.BasicMapInfoData;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
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
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.ui.PathPickupDialog;

/**
 * This section should be used to customize the list of map paths and related
 * styles.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class MapPathAndStyleSection extends AbstractSection {

	@Override
	public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
		super.createControls(parent, aTabbedPropertySheetPage);
		parent.setLayout(new GridLayout(1, false));

		FormText mapPickSuggestion = new FormText(parent, SWT.NONE);
		mapPickSuggestion.setWhitespaceNormalized(true);
		mapPickSuggestion.setText(Messages.MapPathAndStyleSection_0, true, false);
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalIndent = 5;
		mapPickSuggestion.setLayoutData(gd);
		mapPickSuggestion.addHyperlinkListener(new HyperlinkAdapter() {

			@Override
			public void linkActivated(HyperlinkEvent e) {
				MMap mmap = (MMap) getElement();
				JasperDesign jd = mmap.getJasperDesign();
				JasperReportsConfiguration jConf = mmap.getJasperConfiguration();
				PathPickupDialog d = new PathPickupDialog(UIUtils.getShell()) {
					@Override
					protected void configureShell(Shell newShell) {
						super.configureShell(newShell);
						UIUtils.resizeAndCenterShell(newShell, 800, 600);
					}
				};
				BasicMapInfoData mapInfo = mmap.getBasicMapInformation();
				if (mapInfo.getLatitude() != null && mapInfo.getLongitude() != null)
					d.setMapCenter(new LatLng(mapInfo.getLatitude(), mapInfo.getLongitude(), true));
				if (mapInfo.getAddress() != null)
					d.setAddress(mapInfo.getAddress());
				if (mapInfo.getMapType() != null)
					d.setMapType(MapType.fromStringID(mapInfo.getMapType().getName()));
				if (mapInfo.getZoom() != 0)
					d.setZoomLevel(mapInfo.getZoom());

				Map<Marker, StandardItem> map = new HashMap<Marker, StandardItem>();
				List<ItemData> oldMarkers = (List<ItemData>) mmap
						.getPropertyValue(StandardMapComponent.PROPERTY_PATH_DATA_LIST);
				List<ItemData> newMarkers = new ArrayList<ItemData>();
				if (oldMarkers != null) {
					for (ItemData id : oldMarkers) {
						id = (ItemData) id.clone();
						newMarkers.add(id);

						JRDesignDataset dataset = null;
						if (id != null && id.getDataset() != null)
							dataset = ModelUtils.getDesignDatasetForDatasetRun(jd, id.getDataset().getDatasetRun());
						if (dataset == null)
							dataset = ModelUtils.getDataset(mmap);
						if (dataset == null)
							dataset = (JRDesignDataset) jd.getMainDataset();

						ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(dataset, jd, jConf);
						for (Item it : id.getItems()) {
							StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
									.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_latitude);
							if (ip == null)
								continue;
							Double lat = ItemPropertyUtil.getItemPropertyDouble(ip, expIntr);
							if (lat == null)
								continue;

							ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
									MapComponent.ITEM_PROPERTY_longitude);
							if (ip == null)
								continue;
							Double lon = ItemPropertyUtil.getItemPropertyDouble(ip, expIntr);
							if (lon == null)
								continue;
							Marker m = new Marker(new LatLng(lat, lon));
							map.put(m, (StandardItem) it);

							ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
									MapComponent.ITEM_PROPERTY_name);
							String pname = ip == null ? ""
									: Misc.nvl(ItemPropertyUtil.getItemPropertyString(ip, expIntr));
							d.addPoint(m, pname);
						}
					}
				}
				if (d.open() == Window.OK) {
					Map<String, java.util.List<Marker>> markersList = d.getPointsList();
					StandardItemData sid = null;
					for (String path : markersList.keySet()) {
						List<Marker> points = markersList.get(path);
						for (Marker m : points) {
							StandardItem si = map.get(m);
							if (si != null) {
								StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
										.getProperty(si.getProperties(), MapComponent.ITEM_PROPERTY_latitude);
								if (ip.getValueExpression() != null)
									ip.setValueExpression(new JRDesignExpression(m.getPosition().getLat().toString()));
								else
									ip.setValue(m.getPosition().getLat().toString());
								ip = (StandardItemProperty) ItemPropertyUtil.getProperty(si.getProperties(),
										MapComponent.ITEM_PROPERTY_longitude);
								if (ip.getValueExpression() != null)
									ip.setValueExpression(new JRDesignExpression(m.getPosition().getLng().toString()));
								else
									ip.setValue(m.getPosition().getLng().toString());
							} else {
								// will add it to the last itemdata, so we
								// append
								// markers
								if (sid == null) {
									if (newMarkers.isEmpty()) {
										sid = new StandardItemData();
										newMarkers.add(sid);
									} else
										sid = (StandardItemData) newMarkers.get(newMarkers.size() - 1);
								}
								si = new StandardItem();
								si.addItemProperty(
										new StandardItemProperty(MapComponent.ITEM_PROPERTY_name, path, null));
								si.addItemProperty(new StandardItemProperty(MapComponent.ITEM_PROPERTY_latitude,
										m.getPosition().getLat().floatValue() + "f", null)); //$NON-NLS-1$ //$NON-NLS-2$
								si.addItemProperty(new StandardItemProperty(MapComponent.ITEM_PROPERTY_longitude,
										m.getPosition().getLng().floatValue() + "f", null)); //$NON-NLS-1$ //$NON-NLS-2$
								sid.addItem(si);
							}
						}
					}
					changeProperty(StandardMapComponent.PROPERTY_PATH_DATA_LIST, newMarkers);
				}
			}
		});

		createWidget4Property(parent, StandardMapComponent.PROPERTY_PATH_DATA_LIST);
		createWidget4Property(parent, StandardMapComponent.PROPERTY_PATH_STYLE_LIST);
	}

}
