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

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.AddNewMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.ClearMarkersList;
import com.jaspersoft.studio.widgets.map.browserfunctions.RemoveMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMarkerPosition;
import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;

/**
 * This dialog allows to place a list of points in the map. Points can be
 * moved/removed and their position is update accordingly. A polyline (path) is
 * drawed from this points
 * 
 * @author Veaceslav Chicu (schicu@users.sourceforge.net)
 * 
 */
public class PathPickupDialog extends BasicInfoMapDialog {

	private java.util.List<LatLng> points;
	private List pointsWidget;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public PathPickupDialog(Shell parentShell) {
		super(parentShell);
		this.points = new ArrayList<LatLng>();
	}

	@Override
	protected void createContent(Composite parent) {
		SashForm sash = new SashForm(parent, SWT.HORIZONTAL);
		sash.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		sash.setSashWidth(10);

		createMap(sash);

		Composite panelCmp = new Composite(sash, SWT.NONE);
		GridLayout panelCmpGL = new GridLayout(1, true);
		panelCmpGL.marginWidth = 0;
		panelCmpGL.marginHeight = 0;
		panelCmp.setLayout(panelCmpGL);
		panelCmp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		Label markersLbl = new Label(panelCmp, SWT.NONE);
		markersLbl.setText("Points");
		markersLbl.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, true, false));

		pointsWidget = new List(panelCmp, SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		pointsWidget
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Button delMarkersBtn = new Button(panelCmp, SWT.PUSH);
		delMarkersBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false,
				false));
		delMarkersBtn.setText("Delete Points");
		delMarkersBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				map.getJavascriptMapSupport().clearMarkers();
			}
		});
		pointsWidget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int markerIdx = pointsWidget.getSelectionIndex();
				map.getJavascriptMapSupport().highlightMarker(markerIdx);
			}
		});
		pointsWidget.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					int markerIdx = pointsWidget.getSelectionIndex();
					RemoveMarker.removeMarker(markerIdx,
							map.getJavaMapSupport());
					map.getJavascriptMapSupport().evaluateJavascript(
							"JAVA_TO_JAVASCRIPT_CALLED=true");
					map.getJavascriptMapSupport().removeMarker(markerIdx);
				}
			}
		});

		map.activateMapTile();

		sash.setWeights(new int[] { 75, 25 });
	}

	@Override
	protected void configureMapTile() {
		map.configureJavaSupport(new PanelJavaMapSupport(map.getMapControl()) {
			@Override
			public void setZoomLevel(int newZoomLevel) {
				PathPickupDialog.this.setZoomLevel(newZoomLevel);
			}

			@Override
			public void setMapCenter(LatLng position) {
				PathPickupDialog.this.setMapCenter(position);
			}

			@Override
			public void setMapType(MapType mapType) {
				PathPickupDialog.this.setMapType(mapType);
			}
		});
		map.getFunctions().add(
				new AddNewMarker(map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_ADD_MARKER, map
								.getJavaMapSupport()));
		map.getFunctions().add(
				new ClearMarkersList(map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_CLEAR_MARKERS, map
								.getJavaMapSupport()));
		map.getFunctions().add(
				new RemoveMarker(map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_REMOVE_MARKER, map
								.getJavaMapSupport()));
		map.getFunctions()
				.add(new UpdateMarkerPosition(
						map.getMapControl(),
						MapWidgetConstants.BROWSER_FUCTION_UPDATE_MARKER_POSITION,
						map.getJavaMapSupport()));
		map.getFunctions()
				.add(new InitialConfigurationFunction(
						map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION,
						map.getJavaMapSupport()));
	}

	/**
	 * Returns the list of markers added on the map. It's a list of coordinates.
	 * 
	 * @return list of markers
	 */
	public java.util.List<LatLng> getMarkersList() {
		return points;
	}

	private boolean initMarkers = false;

	@Override
	protected Object initConfigurationFunction(Object[] arguments) {
		initMarkers = true;
		try {
			map.getJavascriptMapSupport().evaluateJavascript(
					"MENU_KIND=_MENU_COMPLETE");
			super.initConfigurationFunction(arguments);
			drawPolyline();
		} finally {
			initMarkers = false;
		}
		return null;

	}

	private void drawPolyline() {
		if (points.isEmpty())
			return;
		String snippet = "var pathCoordinates = [\n";
		for (int i = 0; i < points.size(); i++) {
			LatLng p = points.get(i);
			snippet += "{lat:" + p.getLat() + ", lng: " + p.getLng() + "}";
			if (i < points.size() - 1)
				snippet += ",";
			snippet += "\n";
			pointsWidget.add(String.format("%.6f", p.getLat()) + " : "
					+ String.format("%.6f", p.getLng()));
		}
		snippet += "];\nvar mypath = new google.maps.Polyline({\n";
		snippet += "path: pathCoordinates,\n";
		snippet += "geodesic: true,\n";
		snippet += "strokeColor: '#FF0000',\n";
		snippet += "strokeOpacity: 1.0,\n";
		snippet += "strokeWeight: 2\n";
		snippet += " });\n";
		snippet += "mypath.setMap(myMap.map)";
		System.out.println(snippet);
		map.getJavascriptMapSupport().evaluateJavascript(snippet);
	}

	@Override
	protected void addCenterMarker(LatLng coords) {
	}

	/**
	 * Custom Java map support. We need to update properly the dialog UI and
	 * internal components.
	 * 
	 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
	 * 
	 */
	class PanelJavaMapSupport extends BaseJavaMapSupport {

		PanelJavaMapSupport(Browser browser) {
			super(browser);
		}

		@Override
		public void removeMarker(int markerIndex) {
			if (initMarkers)
				return;
			super.removeMarker(markerIndex);
			pointsWidget.remove(markerIndex);
			points.remove(markerIndex);
			drawPolyline();
		}

		@Override
		public void highlightMarker(int markerIdx) {
			if (initMarkers)
				return;
			super.highlightMarker(markerIdx);
			pointsWidget.setSelection(markerIdx);
		}

		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			if (initMarkers)
				return;
			super.updateMarkerPosition(markerIdx, newPosition);
			pointsWidget.setItem(markerIdx,
					String.format("%.6f", newPosition.getLat()) + " : "
							+ String.format("%.6f", newPosition.getLng()));
			drawPolyline();
		}

		@Override
		public void clearMarkers() {
			if (initMarkers)
				return;
			super.clearMarkers();
			pointsWidget.removeAll();
			points.clear();
			drawPolyline();
		}

		@Override
		public void removeMarker(Marker oldMarker) {
			if (initMarkers)
				return;
			int mIdx = getMarkers().indexOf(oldMarker);
			if (mIdx > 0) {
				getMarkers().remove(mIdx);
				pointsWidget.remove(mIdx);
				points.remove(mIdx);
				drawPolyline();
			} else {
				// FIXME do nothing or raise error (at least log)?!
			}
		}

		@Override
		public void addNewMarker(Marker newMarker) {
			if (initMarkers)
				return;
			super.addNewMarker(newMarker);
			LatLng position = newMarker.getPosition();
			pointsWidget.add(String.format("%.6f", position.getLat()) + " : "
					+ String.format("%.6f", position.getLng()));
			points.add(position);
			drawPolyline();
		}

	}
}
