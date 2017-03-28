/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.components.map.messages.Messages;
import com.jaspersoft.studio.components.map.model.MMap;
import com.jaspersoft.studio.components.map.property.desc.MarkersDescriptor;
import com.jaspersoft.studio.model.APropertyNode;
import com.jaspersoft.studio.model.util.ItemPropertyUtil;
import com.jaspersoft.studio.property.itemproperty.desc.AItemDataListPropertyDescriptor;
import com.jaspersoft.studio.property.itemproperty.dialog.AItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.FormItemDialog;
import com.jaspersoft.studio.property.itemproperty.dialog.ItemDataDialog;
import com.jaspersoft.studio.property.itemproperty.sp.SPItemDataList;
import com.jaspersoft.studio.property.section.AbstractSection;
import com.jaspersoft.studio.utils.ExpressionInterpreter;
import com.jaspersoft.studio.utils.ExpressionUtil;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;
import com.jaspersoft.studio.widgets.map.ui.GMapsMarkersPanel;

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
			ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(), Messages.MMap_markersDescription,
					Messages.SPMarker_MarkersListBtn, clones, itemData,
					(JasperReportsConfiguration) section.getJasperReportsContext(), getDescriptor(), expContext,
					pnode) {

				@Override
				protected AItemDialog createItemDialog() {
					return SPMarkerItemDataList.this.createItemDialog();
				}

				@Override
				protected String getItemName() {
					return Messages.MMap_markersDescription;
				}

				private GMapsMarkersPanel pmap;

				@Override
				protected void createItems(final CTabFolder tabFolder) {
					super.createItems(tabFolder);

					final CTabItem tabItem = new CTabItem(tabFolder, SWT.NONE);
					tabItem.setText(Messages.MarkersPropertyDescriptor_3);
					tabFolder.addSelectionListener(new SelectionAdapter() {
						@Override
						public void widgetSelected(SelectionEvent e) {
							if (tabFolder.getSelection() == tabItem) {
								UIUtils.getDisplay().asyncExec(new Runnable() {
									public void run() {
										pmap.refresh();
									}
								});
							} else if (itemsViewer.getTable().isVisible()) {
								itemsViewer.setInput(itemData.getItems());
								itemsViewer.refresh();
							}
						}
					});

					Composite cmp = new Composite(tabFolder, SWT.NONE);
					GridLayout layout = new GridLayout(2, false);
					layout.marginWidth = 0;
					layout.marginRight = -5;
					cmp.setLayout(layout);
					try {
						pmap = new GMapsMarkersPanel(cmp, SWT.NONE) {

							private MMap mmap;
							private BasicMapInfoData mapInfo;

							@Override
							public void initMap() {
								initMarkers = true;
								try {
									mmap = (MMap) pnode;
									if (mapInfo == null) {
										mapInfo = mmap.getBasicMapInformation();
										if (mapInfo.getLatitude() != null && mapInfo.getLongitude() != null)
											setMapCenter(
													new LatLng(mapInfo.getLatitude(), mapInfo.getLongitude(), true));
										else if (mapInfo.getAddress() != null)
											setAddress(mapInfo.getAddress());
										else {
											postCreateMap.put(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
													getMapCenter().getLat());
											postCreateMap.put(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
													getMapCenter().getLng());
										}
										if (mapInfo.getMapType() != null)
											setMapType(MapType.fromStringID(mapInfo.getMapType().getName()));
										else
											postCreateMap.put(StandardMapComponent.PROPERTY_MAP_TYPE, getMapType());
										if (mapInfo.getZoom() != 0)
											setZoomLevel(mapInfo.getZoom());
										else
											postCreateMap.put(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION,
													getZoomLevel());
									}
								} finally {
									initMarkers = false;
								}
							}

							@Override
							public void postInitMap() {
								super.postInitMap();
								initMarkers = true;
								try {
									JasperDesign jd = mmap.getJasperDesign();
									clearMarkers();
									JRDesignDataset dataset = null;
									if (itemData.getDataset() != null)
										dataset = ModelUtils.getDesignDatasetForDatasetRun(jd,
												itemData.getDataset().getDatasetRun());
									if (dataset == null)
										dataset = ModelUtils.getDataset(mmap);
									if (dataset == null)
										dataset = (JRDesignDataset) jd.getMainDataset();

									ExpressionInterpreter expIntr = ExpressionUtil.getCachedInterpreter(dataset, jd,
											mmap.getJasperConfiguration());
									for (Item it : itemData.getItems()) {
										Double lat = null;
										Double lon = null;
										StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
												.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_latitude);
										if (ip != null)
											lat = ItemPropertyUtil.getItemPropertyDouble(ip, expIntr);

										ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
												MapComponent.ITEM_PROPERTY_longitude);
										if (ip != null)
											lon = ItemPropertyUtil.getItemPropertyDouble(ip, expIntr);
										if (lat == null || lon == null) {
											ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
													MapComponent.ITEM_PROPERTY_address);
											String adr = ItemPropertyUtil.getItemPropertyString(ip, expIntr);
											if (Misc.isNullOrEmpty(adr))
												continue;
											LatLng coords = GMapUtils.getAddressCoordinates(adr);
											if (coords != null) {
												lat = coords.getLat();
												lon = coords.getLng();
											}
										}
										if (lat == null || lon == null)
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
											m.getOptions()
													.setOptimized(ItemPropertyUtil.getItemPropertyBoolean(ip, expIntr));
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

										addNewMarker(m);
									}
								} finally {
									initMarkers = false;
								}
							}

							@Override
							protected void handleNewMarker(Marker newMarker) {
								if (initMarkers)
									return;
								super.handleNewMarker(newMarker);
								StandardItem si = new StandardItem();
								LatLng p = newMarker.getPosition();
								si.addItemProperty(new StandardItemProperty(MapComponent.ITEM_PROPERTY_latitude,
										coordinatesFormatter.format(p.getLat()), null));
								si.addItemProperty(new StandardItemProperty(MapComponent.ITEM_PROPERTY_longitude,
										coordinatesFormatter.format(p.getLng()), null));
								itemData.addItem(si);
							}

							@Override
							protected void handleUpdateMarkerPosition(int markerIdx, Marker m) {
								super.handleUpdateMarkerPosition(markerIdx, m);
								StandardItem si = (StandardItem) itemData.getItems().get(markerIdx);
								if (si != null) {
									StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
											.getProperty(si.getProperties(), MapComponent.ITEM_PROPERTY_latitude);
									LatLng p = m.getPosition();
									if (ip.getValueExpression() != null)
										ip.setValueExpression(
												new JRDesignExpression(coordinatesFormatter.format(p.getLat())));
									else
										ip.setValue(coordinatesFormatter.format(p.getLat()));

									ip = (StandardItemProperty) ItemPropertyUtil.getProperty(si.getProperties(),
											MapComponent.ITEM_PROPERTY_longitude);
									if (ip.getValueExpression() != null)
										ip.setValueExpression(
												new JRDesignExpression(coordinatesFormatter.format(p.getLng()))); // $NON-NLS-1$
									else
										ip.setValue(coordinatesFormatter.format(p.getLng()));
								}
							}

							@Override
							protected void handleRemoveMarker(int markerIndex) {
								super.handleRemoveMarker(markerIndex);
								itemData.removeItem(itemData.getItems().get(markerIndex));
							}

							@Override
							protected void handleRemoveMarker(int[] mIndxs) {
								List<Item> itms = new ArrayList<Item>();
								for (int i : mIndxs)
									itms.add(itemData.getItems().get(i));
								for (Item it : itms)
									itemData.removeItem(it);
								super.handleRemoveMarker(mIndxs);
								initMarkers = true;
								try {
									postInitMap();
								} finally {
									initMarkers = false;
								}
							}

							@Override
							protected void handleAddressChanged(String address) {
								postCreateMap.put(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, address);
								postCreateMap.put(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION, null);
								postCreateMap.put(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION, null);
							}

							@Override
							protected void handleMapZoomChanged(int newZoomLevel) {
								if (initMarkers)
									return;
								postCreateMap.put(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, newZoomLevel);
							}

							@Override
							protected void handleMapTypeChanged(MapType mapType) {
								if (initMarkers)
									return;
								postCreateMap.put(StandardMapComponent.PROPERTY_MAP_TYPE, mapType.ordinal());
							}

							@Override
							protected void handleMapCenterChanged(LatLng position) {
								if (initMarkers)
									return;
								super.handleMapCenterChanged(position);
								if (Misc.isNullOrEmpty(getAddress())) {
									postCreateMap.put(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, null);
									postCreateMap.put(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
											coordinatesFormatter.format(position.getLat()));
									postCreateMap.put(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
											coordinatesFormatter.format(position.getLng()));
								}
							}

							@Override
							protected void handleMarkerDoubleClick(int ind) {
								editElement.editElement(itemData.getItems(), ind);
								// update marker?
								initMarkers = true;
								try {
									postInitMap();
								} finally {
									initMarkers = false;
								}

							}

						};
					} catch (Throwable e) {
						tabItem.dispose();
						return;
					}
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
					Label lbl = createCenteredLabel(cmp);
					lbl.setText(Messages.MapSection_1);

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

	public MarkersPropertyDescriptor(Object id, String displayName, APropertyNode pNode) {
		super(id, displayName, pNode);
	}

	@Override
	protected void initShowColumns() {
		descriptor = new MarkersDescriptor();
	}

	protected SPItemDataList createSPWidget(Composite parent, AbstractSection section) {
		return new SPMarkerItemDataList(parent, section, this, false);
	}
}
