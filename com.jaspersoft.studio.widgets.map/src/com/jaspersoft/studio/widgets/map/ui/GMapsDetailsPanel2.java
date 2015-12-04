/*******************************************************************************
 * Copyright (c) 2014 Massimo Rabbi.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Massimo Rabbi <mrabbi@users.sourceforge.net> - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.utils.NumberValidator;
import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.AddNewMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.ClearMarkersList;
import com.jaspersoft.studio.widgets.map.browserfunctions.GMapEnabledFunction;
import com.jaspersoft.studio.widgets.map.browserfunctions.RemoveMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMarkerPosition;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.messages.Messages;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;
import com.jaspersoft.studio.widgets.map.support.GMapUtils;
import com.jaspersoft.studio.widgets.map.support.JavaMapSupport;

/**
 * This class implements the support for the Google Map component. The panel
 * that is shown contains:
 * <ul>
 * <li>a browser with the Google Maps component loaded</li>
 * <li>an additional panel with controls that allows the interaction with the
 * Google Maps in the browser</li>
 * </ul>
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public class GMapsDetailsPanel2 {

	protected List markersList;
	protected MapTile map;

	protected LatLng mapCenter;
	protected int zoomLevel;
	protected MapType mapType;
	protected String address;
	protected Text addressBar;

	/**
	 * Creates a new panel containing the controls to work with a Google Maps
	 * component presented inside a browser instance.
	 * 
	 * @param parent
	 *            a composite control which will be the parent of the new
	 *            instance (cannot be null)
	 * @param style
	 *            the style of widget to construct
	 */
	public GMapsDetailsPanel2(Composite parent, int style) {
		createTop(parent);

		SashForm sash = new SashForm(parent, style | SWT.HORIZONTAL);
		if (parent.getLayout() instanceof GridLayout)
			sash.setLayoutData(new GridData(GridData.FILL_BOTH));

		map = new MapTile(sash, SWT.NONE);
		map.configureJavaSupport(new DetailsPanelMapSupport(map.getMapControl()));

		map.getFunctions().add(new AddNewMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_ADD_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new ClearMarkersList(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_CLEAR_MARKERS, map.getJavaMapSupport()));
		map.getFunctions().add(new RemoveMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_REMOVE_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new UpdateMarkerPosition(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUCTION_UPDATE_MARKER_POSITION, map.getJavaMapSupport()));
		map.getFunctions().add(new InitialConfigurationFunction(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION, map.getJavaMapSupport()));

		Composite containerCmp = new Composite(sash, SWT.NONE);
		GridLayout layout = new GridLayout();
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		containerCmp.setLayout(layout);

		createRightPanel(containerCmp);

		map.activateMapTile();

		sash.setWeights(new int[] { 4, 1 });
	}

	protected void createTop(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(7, false);
		layout.marginWidth = 0;
		layout.marginHeight = 0;
		layout.marginRight = -5;
		cmp.setLayout(layout);

		if (parent.getLayout() instanceof GridLayout) {
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			gd.horizontalSpan = 2;
			cmp.setLayoutData(gd);
		}

		final Text tadr = new Text(cmp, SWT.SEARCH | SWT.ICON_SEARCH);
		tadr.setMessage("Address");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		tadr.setLayoutData(gd);
		tadr.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				refreshing = true;
				try {
					LatLng coords = GMapUtils.getAddressCoordinates(tadr.getText());
					if (coords != null)
						centerMap(coords);
				} finally {
					refreshing = false;
				}
			}
		});
		tadr.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				String txt = tadr.getText();
				if (txt.isEmpty())
					return;
				refreshing = true;
				try {
					address = txt;
					LatLng coords = GMapUtils.getAddressCoordinates(txt);
					if (coords != null) {
						centerMap(coords);
						handleAddressChanged(txt);
					}
				} finally {
					refreshing = false;
				}
			}
		});

		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText("Latitude");

		tlat = new Text(cmp, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		tlat.setLayoutData(gd);
		tlat.addVerifyListener(new NumberValidator(new Float("-85"), new Float("85"), Float.class));
		tlat.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (refreshing)
					return;
				String txt = tlat.getText();
				if (txt.isEmpty())
					return;
				refreshing = true;
				try {
					Double d = Double.valueOf(txt);
					centerMap(new LatLng(d, mapCenter.getLng()));
					address = null;
					tadr.setText("");
				} finally {
					refreshing = false;
				}
			}
		});

		lbl = new Label(cmp, SWT.NONE);
		lbl.setText("Longitude");

		tlon = new Text(cmp, SWT.NONE);
		gd = new GridData();
		gd.widthHint = 100;
		tlon.setLayoutData(gd);
		tlon.addVerifyListener(new NumberValidator(new Float("-180"), new Float("180"), Float.class));
		tlon.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				if (refreshing)
					return;
				String txt = tlon.getText();
				if (txt.isEmpty())
					return;
				refreshing = true;
				try {
					Double d = Double.valueOf(txt);
					centerMap(new LatLng(mapCenter.getLat(), d));
					address = null;
					tadr.setText("");
				} finally {
					refreshing = false;
				}
			}
		});
	}

	public void initMap() {
	}

	public void initMarkers() {
	}

	protected void createRightPanel(Composite containerCmp) {
		Label markersLbl = new Label(containerCmp, SWT.NONE);
		markersLbl.setText(Messages.GMapsDetailsPanel_MarkersLbl);
		markersLbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		markersList = new List(containerCmp, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL | SWT.MULTI);
		markersList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		markersList.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int markerIdx = markersList.getSelectionIndex();
				map.getJavascriptMapSupport().highlightMarker(markerIdx);
			}
		});
		markersList.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.DEL || e.keyCode == SWT.BS) {
					if (markersList.getSelectionCount() <= 0)
						return;
					MessageDialog dialog = new MessageDialog(UIUtils.getShell(), "Delete", null,
							"Are you sure you want to delete selected items?", MessageDialog.QUESTION,
							new String[] { "Yes", "No" }, 1);
					if (dialog.open() == Dialog.OK)
						handleRemoveMarker(markersList.getSelectionIndices());
				}
			}
		});
		markersList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				if (markersList.getSelectionCount() <= 0)
					return;
				handleMarkerDoubleClick(markersList.getSelectionIndex());
			}
		});
	}

	/**
	 * Browser function for correctly configuring the initial settings of the
	 * map.
	 * 
	 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
	 * 
	 */
	class InitialConfigurationFunction extends GMapEnabledFunction {

		public InitialConfigurationFunction(Browser browser, String name, JavaMapSupport mapSupport) {
			super(browser, name, mapSupport);
		}

		@Override
		public Object function(Object[] arguments) {
			initMap();
			initMarkers = true;
			try {
				map.getJavascriptMapSupport().setZoomLevel(getZoomLevel());
				map.getJavascriptMapSupport().setMapType(mapType != null ? mapType : MapType.ROADMAP);
				if ((mapCenter == null || mapCenter.getLat() == null || mapCenter.getLat() == null)) {
					if (address != null && !address.isEmpty()) {
						LatLng coords = GMapUtils.getAddressCoordinates(address);
						if (coords != null)
							centerMap(coords);
					}
				} else
					centerMap(mapCenter);
				initMarkers();
				map.getJavascriptMapSupport().evaluateJavascript("MENU_KIND=_MENU_COMPLETE");
			} finally {
				initMarkers = false;
			}
			return null;
		}

	}

	private LatLng lastCoords;
	private boolean centering = false;

	protected void centerMap(LatLng coords) {
		if (coords == null)
			return;
		lastCoords = coords;
		if (centering)
			return;
		centering = true;
		try {
			setMapCenter(lastCoords);
			lastCoords = null;
			map.getJavascriptMapSupport().evaluateJavascript("myMap.panTo(" + mapCenter.toJsString() + "); ");
			// addCenterMarker(coords);
		} finally {
			centering = false;
			centerMap(lastCoords);
		}
	}

	protected boolean initMarkers = false;
	protected Text tlon;
	protected Text tlat;
	protected boolean refreshing = false;

	class DetailsPanelMapSupport extends BaseJavaMapSupport {

		DetailsPanelMapSupport(Browser browser) {
			super(browser);
		}

		@Override
		public void removeMarker(int markerIndex) {
			if (initMarkers)
				return;
			super.removeMarker(markerIndex);
			handleRemoveMarker(markerIndex);
		}

		@Override
		public void removeMarker(Marker oldMarker) {
			if (initMarkers)
				return;
			int mIdx = getMarkers().indexOf(oldMarker);
			if (mIdx > 0) {
				removeMarker(mIdx);
			} else {
				// FIXME do nothing or raise error (at least log)?!
			}
		}

		@Override
		public void highlightMarker(int markerIdx) {
			if (initMarkers)
				return;
			super.highlightMarker(markerIdx);
			handleHighlightMarker(markerIdx);
		}

		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			if (initMarkers)
				return;
			super.updateMarkerPosition(markerIdx, newPosition);
			handleUpdateMarkerPosition(markerIdx, getMarkers().get(markerIdx));
		}

		@Override
		public void clearMarkers() {
			if (initMarkers)
				return;
			super.clearMarkers();
			handleClearMarkers();
		}

		@Override
		public void addNewMarker(Marker newMarker) {
			if (initMarkers)
				return;
			super.addNewMarker(newMarker);
			handleNewMarker(newMarker);
		}

		@Override
		public void setMapType(MapType mapType) {
			if (initMarkers)
				return;
			super.setMapType(mapType);
			handleMapTypeChanged(mapType);
		}

		@Override
		public void setZoomLevel(int newZoomLevel) {
			if (initMarkers)
				return;
			super.setZoomLevel(newZoomLevel);
			handleMapZoomChanged(newZoomLevel);
		}

		@Override
		public void setMapCenter(LatLng position) {
			if (initMarkers)
				return;
			super.setMapCenter(position);
			handleMapCenterChanged(position);
		}
	}

	protected void handleHighlightMarker(int markerIdx) {
		markersList.setSelection(markerIdx);
	}

	protected void handleUpdateMarkerPosition(int markerIdx, Marker m) {
		markersList.setItem(markerIdx, formatMarker(m));
	}

	protected void handleRemoveMarker(int markerIndex) {
		markersList.remove(markerIndex);
	}

	protected void handleRemoveMarker(int[] mIndxs) {

	}

	protected void handleClearMarkers() {
		markersList.removeAll();
	}

	protected void handleNewMarker(Marker newMarker) {
		if (initMarkers)
			return;
		markersList.add(formatMarker(newMarker));
	}

	protected void handleMapTypeChanged(MapType mapType) {

	}

	protected void handleMapZoomChanged(int newZoomLevel) {

	}

	protected void handleMapCenterChanged(LatLng position) {
		if (refreshing)
			return;
		refreshing = true;
		try {
			tlon.setText(String.format("%.7f", position.getLng()));
			tlat.setText(String.format("%.7f", position.getLat()));
		} finally {
			refreshing = false;
		}
	}

	protected void handleAddressChanged(String address) {

	}

	protected void handleMarkerDoubleClick(int ind) {

	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
		if (addressBar != null)
			addressBar.setText(address);
	}

	public LatLng getMapCenter() {
		return mapCenter;
	}

	public void setMapCenter(LatLng mapCenter) {
		this.mapCenter = mapCenter;
	}

	public MapType getMapType() {
		return mapType;
	}

	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}

	public int getZoomLevel() {
		return zoomLevel;
	}

	public void setZoomLevel(int zoomLevel) {
		this.zoomLevel = zoomLevel;
	}

	public void addNewMarker(Marker m) {
		LatLng p = m.getPosition();
		if (p != null) {
			markersList.add(p.getLat() + " : " + p.getLng());
			map.getJavascriptMapSupport().addNewMarker(m);
		}
	}

	public void clearMarkers() {
		map.getJavascriptMapSupport().clearMarkers();
		markersList.removeAll();
	}

	protected String formatMarker(Marker m) {
		LatLng p = m.getPosition();
		return String.format("%.7f : %.7f", p.getLat(), p.getLng());
	}
}
