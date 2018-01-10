/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.property;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;
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
import com.jaspersoft.studio.components.map.property.desc.PathDescriptor;
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
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;
import com.jaspersoft.studio.widgets.map.ui.GMapsPathsPanel;

import net.sf.jasperreports.components.items.Item;
import net.sf.jasperreports.components.items.ItemData;
import net.sf.jasperreports.components.items.ItemProperty;
import net.sf.jasperreports.components.items.StandardItem;
import net.sf.jasperreports.components.items.StandardItemData;
import net.sf.jasperreports.components.items.StandardItemProperty;
import net.sf.jasperreports.components.map.MapComponent;
import net.sf.jasperreports.components.map.StandardMapComponent;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.BasicMapInfoData;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRExpression;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignExpression;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class PathPropertyDescriptor extends AItemDataListPropertyDescriptor {
	private final class SPMarkerItemDataList extends SPItemDataList {
		private SPMarkerItemDataList(Composite parent, AbstractSection section,
				AItemDataListPropertyDescriptor pDescriptor, boolean showElements) {
			super(parent, section, pDescriptor, showElements);
		}

		protected ItemDataDialog createItemDataDialog(List<ItemData> clones, StandardItemData itemData) {
			ItemDataDialog dialog = new ItemDataDialog(UIUtils.getShell(), Messages.MMap_MapPaths,
					Messages.PathPropertyDescriptor_0, clones, itemData,
					(JasperReportsConfiguration) section.getJasperReportsContext(), getDescriptor(), expContext,
					pnode) {

				@Override
				protected AItemDialog createItemDialog() {
					return SPMarkerItemDataList.this.createItemDialog();
				}

				@Override
				protected String getItemName() {
					return Messages.MMap_MapPaths;
				}

				private GMapsPathsPanel pmap;

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
										pmap.initMarkers();
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
						pmap = new GMapsPathsPanel(cmp, SWT.NONE) {

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
											addPostCreateCommand(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
													getMapCenter().getLat());
											addPostCreateCommand(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
													getMapCenter().getLng());
										}
										if (mapInfo.getMapType() != null)
											setMapType(MapType.fromStringID(mapInfo.getMapType().getName()));
										else
											addPostCreateCommand(StandardMapComponent.PROPERTY_MAP_TYPE, getMapType());
										if (mapInfo.getZoom() != 0)
											setZoomLevel(mapInfo.getZoom());
										else
											addPostCreateCommand(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION,
													getZoomLevel());
									}
								} finally {
									initMarkers = false;
								}
							}

							private ExpressionInterpreter getExpressionInterpretter() {
								JasperDesign jd = mmap.getJasperDesign();
								JRDesignDataset dataset = null;
								if (itemData.getDataset() != null)
									dataset = ModelUtils.getDesignDatasetForDatasetRun(jd,
											itemData.getDataset().getDatasetRun());
								if (dataset == null)
									dataset = ModelUtils.getDataset(mmap);
								if (dataset == null)
									dataset = (JRDesignDataset) jd.getMainDataset();

								return ExpressionUtil.getCachedInterpreter(dataset, jd, mmap.getJasperConfiguration());
							}

							@Override
							public void postInitMap() {
								super.postInitMap();
								fillPaths();
							}

							@Override
							public void initMarkers() {
								initMarkers = true;
								try {
									if (markersList != null && markersList.getItemCount() > 0)
										clearMarkers();
									String path = cPaths.getText();
									ExpressionInterpreter expIntr = getExpressionInterpretter();
									for (Item it : itemData.getItems()) {
										Double lat = null;
										Double lon = null;
										String itPath = null;
										StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
												.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_name);
										if (ip != null)
											itPath = ItemPropertyUtil.getItemPropertyString(ip, expIntr);
										if (!((Misc.isNullOrEmpty(path) && Misc.isNullOrEmpty(itPath))
												|| path.equals(itPath)))
											continue;
										ip = (StandardItemProperty) ItemPropertyUtil.getProperty(it.getProperties(),
												MapComponent.ITEM_PROPERTY_latitude);
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
									drawPolyline();
								} finally {
									initMarkers = false;
								}
							}

							@Override
							protected void fillPaths() {
								initMarkers = true;
								try {
									String oldSelection = cPaths.getText();
									cPaths.removeAll();
									Set<String> set = new LinkedHashSet<String>();
									ExpressionInterpreter expIntr = getExpressionInterpretter();
									for (Item it : itemData.getItems()) {
										StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
												.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_name);
										if (ip != null) {
											String path = ItemPropertyUtil.getItemPropertyString(ip, expIntr);
											if (!Misc.isNullOrEmpty(path))
												set.add(path);
										}
									}
									cPaths.setItems(set.toArray(new String[set.size()]));
									int i = 0;
									for (String path : set) {
										if (path.equals(oldSelection)) {
											cPaths.select(i);
											break;
										}
										++i;
									}
									if (cPaths.getSelectionIndex() < 0)
										cPaths.setText(oldSelection);
								} finally {
									initMarkers = false;
								}
							}

							@Override
							protected void handlePathChanged() {
								initMarkers();
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
								String path = cPaths.getText();
								if (!Misc.isNullOrEmpty(path))
									si.addItemProperty(
											new StandardItemProperty(MapComponent.ITEM_PROPERTY_name, path, null)); // $NON-NLS-1$
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
												new JRDesignExpression(coordinatesFormatter.format(p.getLng())));
									else
										ip.setValue(coordinatesFormatter.format(p.getLng()));
								}
							}

							@Override
							protected void handleRemoveMarker(int[] mIndxs) {
								List<Item> itms = new ArrayList<Item>();
								String p = cPaths.getText();
								if (!Misc.isNullOrEmpty(p)) {
									ExpressionInterpreter expIntr = getExpressionInterpretter();
									List<Item> pitems = new ArrayList<Item>();
									for (Item it : itemData.getItems()) {
										StandardItemProperty ip = (StandardItemProperty) ItemPropertyUtil
												.getProperty(it.getProperties(), MapComponent.ITEM_PROPERTY_name);
										if (ip != null) {
											String path = ItemPropertyUtil.getItemPropertyString(ip, expIntr);
											if (!Misc.isNullOrEmpty(path) && path.equals(p))
												pitems.add(it);
										}
									}
									for (int i : mIndxs)
										itms.add(pitems.get(i));
								} else
									for (int i : mIndxs)
										itms.add(itemData.getItems().get(i));
								for (Item it : itms)
									itemData.removeItem(it);
								super.handleRemoveMarker(mIndxs);
								initMarkers = true;
								try {
									postInitMap();
									initMarkers();
								} finally {
									initMarkers = false;
								}
							}

							@Override
							protected void handleAddressChanged(String address) {
								addPostCreateCommand(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, address);
								addPostCreateCommand(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION, null);
								addPostCreateCommand(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION, null);
							}

							@Override
							protected void handleMapZoomChanged(int newZoomLevel) {
								if (initMarkers)
									return;
								addPostCreateCommand(StandardMapComponent.PROPERTY_ZOOM_EXPRESSION, newZoomLevel);
							}

							@Override
							protected void handleMapTypeChanged(MapType mapType) {
								if (initMarkers)
									return;
								addPostCreateCommand(StandardMapComponent.PROPERTY_MAP_TYPE, mapType.ordinal());
							}

							@Override
							protected void handleMapCenterChanged(LatLng position) {
								if (initMarkers)
									return;
								super.handleMapCenterChanged(position);
								if (Misc.isNullOrEmpty(getAddress())) {
									addPostCreateCommand(StandardMapComponent.PROPERTY_ADDRESS_EXPRESSION, null);
									addPostCreateCommand(StandardMapComponent.PROPERTY_LATITUDE_EXPRESSION,
											coordinatesFormatter.format(position.getLat()));
									addPostCreateCommand(StandardMapComponent.PROPERTY_LONGITUDE_EXPRESSION,
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
		protected AItemDialog createItemDialog() {
			return new FormItemDialog(UIUtils.getShell(), getDescriptor(),
					(JasperReportsConfiguration) section.getJasperReportsContext(), false, false) {

				@Override
				protected void createValues(Composite cmp) {
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_name);

					Label lbl = createCenteredLabel(cmp);
					lbl.setText(Messages.MapSection_1);

					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_latitude);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_longitude);
					createItemProperty(cmp, MapComponent.ITEM_PROPERTY_address);

					createSeparator(cmp);

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

		/*
		 * (non-Javadoc)
		 * 
		 * @see com.jaspersoft.studio.tibcomaps.property.sp.SPItemDataList#
		 * getElementViewerComparator()
		 */
		@Override
		protected ViewerComparator getElementViewerComparator() {
			return new ViewerComparator() {
				public int compare(Viewer viewer, Object e1, Object e2) {
					Item t1 = (Item) e1;
					Item t2 = (Item) e2;

					ItemProperty ip1 = getItemProperty(t1);
					ItemProperty ip2 = getItemProperty(t2);
					if (ip1 != null && ip2 != null) {
						JRExpression ip1exp = ip1.getValueExpression();
						JRExpression ip2exp = ip2.getValueExpression();
						if (ip1exp != null && ip2exp != null)
							return Misc.nvl(ip1exp.getText()).compareTo(Misc.nvl(ip2exp.getText()));
						if (ip1exp != null)
							return -1;
						if (ip2exp != null)
							return 1;
						return Misc.nvl(ip1.getValue()).compareTo(Misc.nvl(ip2.getValue()));
					}
					return -1;
				}

				private ItemProperty getItemProperty(Item item) {
					List<ItemProperty> p = item.getProperties();
					if (p != null)
						return ItemPropertyUtil.getProperty(p, MapComponent.ITEM_PROPERTY_name);

					return null;
				}
			};
		}
	};

	public PathPropertyDescriptor(Object id, String displayName, APropertyNode pNode) {
		super(id, displayName, pNode);
	}

	@Override
	protected void initShowColumns() {
		descriptor = new PathDescriptor();
	}

	protected SPItemDataList createSPWidget(Composite parent, AbstractSection section) {
		return new SPMarkerItemDataList(parent, section, this, false);
	}
}
