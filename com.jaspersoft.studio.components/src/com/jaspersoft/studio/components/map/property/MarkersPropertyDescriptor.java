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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.components.map.model.MMap;
import com.jaspersoft.studio.components.map.property.desc.MarkersDescriptor;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemDataDialog;
import com.jaspersoft.studio.property.itemproperty.sp.SPItemDataList;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.UIUtil;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.ui.GMapsDetailsPanel2;

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

/**
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class MarkersPropertyDescriptor extends AItemDataListPropertyDescriptor {

	private final class SPMarkerItemDataList extends SPItemDataList {
		private SPMarkerItemDataList(Composite parent, AbstractSection section,
				AItemDataListPropertyDescriptor pDescriptor, boolean showElements) {
			super(parent, section, pDescriptor, showElements);
		}

		protected ItemDataDialog createItemDataDialog(List<ItemData> clones, StandardItemData itemData) {
			ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(), "Markers", "Markers list", clones, itemData,
					(JasperReportsConfiguration) section.getJasperReportsContext(), getDescriptor(), expContext,
					pnode) {

				@Override
				protected AItemDialog createItemDialog() {
					return SPMarkerItemDataList.this.createItemDialog();
				}

				@Override
				protected String getItemName() {
					return "Markers";
				}

				private GMapsDetailsPanel2 pmap;

				@Override
				protected void createItems(final CTabFolder tabFolder) {
					super.createItems(tabFolder);

					final CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
					tabItem.setText("Map");
					tabFolder.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (tabFolder.getSelection() == tabItem)
								pmap.initMap();
						}
					});

					Composite cmp = new Composite(tabFolder, SWT.NONE);
					cmp.setLayout(new GridLayout(2, false));

					pmap = new GMapsDetailsPanel2(cmp, SWT.NONE) {
						private Label lngLbl;
						private Label latLbl;
						private Map<Marker, StandardItem> msmap;

						@Override
						public void initMap() {
							initMarkers = true;
							try {
								MMap mmap = (MMap) pnode;
								JasperDesign jd = mmap.getJasperDesign();
								BasicMapInfoData mapInfo = mmap.getBasicMapInformation();
								if (mapInfo.getLatitude() != null && mapInfo.getLongitude() != null)
									setMapCenter(new LatLng(mapInfo.getLatitude(), mapInfo.getLongitude(), true));
								if (mapInfo.getAddress() != null)
									setAddress(mapInfo.getAddress());
								if (mapInfo.getMapType() != null)
									setMapType(MapType.fromStringID(mapInfo.getMapType().getName()));
								if (mapInfo.getZoom() != 0)
									setZoomLevel(mapInfo.getZoom());
								msmap = new HashMap<Marker, StandardItem>();
								List<ItemData> oldMarkers = (List<ItemData>) mmap
										.getPropertyValue(StandardMapComponent.PROPERTY_MARKER_DATA_LIST);
								List<ItemData> newMarkers = new ArrayList<ItemData>();
								if (oldMarkers != null) {
									for (ItemData id : oldMarkers) {
										id = (ItemData) id.clone();
										newMarkers.add(id);

										JRDesignDataset dataset = null;
										if (id != null && id.getDataset() != null)
											dataset = ModelUtils.getDesignDatasetForDatasetRun(jd,
													id.getDataset().getDatasetRun());
										if (dataset == null)
											dataset = ModelUtils.getDataset(mmap);
										if (dataset == null)
											dataset = (JRDesignDataset) jd.getMainDataset();

										ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(dataset, jd,
												mmap.getJasperConfiguration());

										for (Item it : id.getItems()) {
											StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
													.getProperty(it.getProperties(),
															MapComponent.ITEM_PROPERTY_latitude);
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
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_MARKER_flat);
											if (ip != null)
												m.setFlat(ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_clickable);
											if (ip != null)
												m.setClickable(ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_draggable);
											if (ip != null)
												m.setDraggable(ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_visible);
											if (ip != null)
												m.setVisible(ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_MARKER_optimized);
											if (ip != null)
												m.getOptions().setOptimized(
														ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_MARKER_raiseOnDrag);
											if (ip != null)
												m.getOptions().setRaiseOnDrag(
														ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_MARKER_cursor);
											if (ip != null)
												m.setCursor(ItemPropertyUtil.getItemPropertyString(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_MARKER_title);
											if (ip != null)
												m.setTitle(ItemPropertyUtil.getItemPropertyString(ip, expIntr));
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_MARKER_zIndex);
											if (ip != null)
												m.setZIndex(ItemPropertyUtil.getItemPropertyInteger(ip, expIntr));

											msmap.put(m, (StandardItem) it);
											LatLng position = m.getPosition();
											if (markersList != null)
												markersList.add(position.getLat() + " : " + position.getLng());
										}
									}
								}
								super.initMap();
							} finally {
								initMarkers = false;
							}
						}

						@Override
						protected void handleNewMarker(Marker newMarker) {
							super.handleNewMarker(newMarker);
							StandardItem si = new StandardItem();
							si.addItemProperty(new StandardItemProperty(MapComponent.ITEM_PROPERTY_latitude,
									newMarker.getPosition().getLat().toString(), null));
							si.addItemProperty(new StandardItemProperty(MapComponent.ITEM_PROPERTY_longitude,
									newMarker.getPosition().getLng().toString(), null));
							msmap.put(newMarker, si);
							itemData.addItem(si);
						}

						@Override
						protected void handleUpdateMarkerPosition(int markerIdx, Marker m) {
							super.handleUpdateMarkerPosition(markerIdx, m);
							StandardItem si = msmap.get(m);
							StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
									.getProperty(si.getProperties(), MapComponent.ITEM_PROPERTY_latitude);
							if (ip.getValueExpression() != null)
								ip.setValueExpression(new JRDesignExpression(m.getPosition().getLat().toString()));
							else
								ip.setValue(m.getPosition().getLat().toString());
							if (ip.getValueExpression() != null)
								ip.setValueExpression(new JRDesignExpression(m.getPosition().getLng().toString()));
							else
								ip.setValue(m.getPosition().getLng().toString());

						}

						@Override
						protected void handleRemoveMarker(Marker oldMarker, List<Marker> markers) {
							super.handleRemoveMarker(oldMarker, markers);
							StandardItem si = msmap.get(oldMarker);
							msmap.remove(oldMarker);
							itemData.removeItem(si);
						}

						@Override
						protected void handleMapCenterChanged(LatLng position) {
							lngLbl.setText("Longitude: " + position.getLng().toString());
							latLbl.setText("Latitude: " + position.getLat().toString());
						}

						@Override
						protected void createRightPanel(Composite containerCmp) {
							Composite c = UIUtil.createSection(containerCmp, "Map Center", false, 1);
							lngLbl = new Label(c, SWT.NONE);
							latLbl = new Label(c, SWT.NONE);

							Text txt = new Text(c, SWT.SEARCH | SWT.ICON_SEARCH);
							txt.setMessage("Address");
							txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

							super.createRightPanel(containerCmp);
						}
					};

					tabItem.setControl(cmp);
				}

			};
			return dialog;
		}

		@Override
		public AItemDialog createItemDialog() {
			return new FormItemDialog(UIUtils.getShell(), getDescriptor(),
					(JasperReportsConfiguration) section.getJasperReportsContext(), false, false) {

				@Override
				protected void createValues(Composite cmp) {
					Label lbl = new Label(cmp, SWT.NONE);
					GridData gd = new GridData(GridData.FILL_HORIZONTAL);
					gd.horizontalSpan = 2;
					lbl.setLayoutData(gd);
					lbl.setText(com.jaspersoft.studio.messages.Messages.MapSection_1);

					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_latitude);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_longitude);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_address);

					createSeparator(cmp);

					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_title);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_url);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_target);

					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_icon);

					Composite c = createSection(cmp,
							com.jaspersoft.studio.components.map.messages.Messages.MarkersPropertyDescriptor_1, false);

					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_url);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_width);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_height);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_x);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_ORIGIN_y);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_x);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_ICON_ANCHOR_y);

					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_shadow);

					c = createSection(cmp,
							com.jaspersoft.studio.components.map.messages.Messages.MarkersPropertyDescriptor_2, false);

					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_url);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_width);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_height);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_x);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ORIGIN_y);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_x);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_SHADOW_ANCHOR_y);

					c = createSection(cmp,
							com.jaspersoft.studio.components.map.messages.Messages.MarkersPropertyDescriptor_0, false);

					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_content);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_pixelOffset);
					createItemProperty(c, MapComponent.ITEM_PROPERTY_MARKER_INFOWINDOW_maxWidth);

					createSeparator(cmp);

					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_color);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_label);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_cursor);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_zIndex);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_clickable);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_draggable);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_flat);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_optimized);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_raiseOnDrag);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_visible);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_MARKER_size);
				}

			};
		}
	}

	public MarkersPropertyDescriptor(Object id, String displayName) {
		super(id, displayName);
	}

	@Override
	protected void initShowColumns() {
		descriptor = new MarkersDescriptor();
	}

	protected SPItemDataList createSPWidget(Composite parent, AbstractSection section) {
		return new SPMarkerItemDataList(parent, section, this, false);
	}
}
