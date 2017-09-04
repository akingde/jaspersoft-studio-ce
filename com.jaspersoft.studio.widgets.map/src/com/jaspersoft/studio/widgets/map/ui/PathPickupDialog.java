/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
import org.eclipse.swt.widgets.Combo;
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
@Deprecated
public class PathPickupDialog extends BasicInfoMapDialog {

	private List pointsWidget;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public PathPickupDialog(Shell parentShell) {
		super(parentShell);
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

		Label lbl = new Label(panelCmp, SWT.NONE);
		lbl.setText("Paths");
		lbl.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, true, false));

		paths = new Combo(panelCmp, SWT.NONE);
		paths.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		paths.setItems(pathMap.keySet().toArray(
				new String[pathMap.keySet().size()]));
		if (paths.getItemCount() > 0)
			paths.select(0);
		paths.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				changePath();
			}

		});

		lbl = new Label(panelCmp, SWT.NONE);
		lbl.setText("Points");
		lbl.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, true, false));

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
				int size = pointsWidget.getItemCount();
				for (int i = 0; i < size; i++)
					map.getJavascriptMapSupport().removeMarker(0);
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

	private void changePath() {
		initPoints = true;
		try {
			map.getJavascriptMapSupport().clearMarkers();
			pointsWidget.removeAll();
			java.util.List<Marker> p = getPoints4Path();
			for (Marker m : p) {
				map.getJavascriptMapSupport().addNewMarker(m);
				pointsWidget.add(String
						.format("%.6f", m.getPosition().getLat())
						+ " : "
						+ String.format("%.6f", m.getPosition().getLng()));
			}
			drawPolyline();
		} finally {
			initPoints = false;
		}
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
						MapWidgetConstants.BROWSER_FUNCTION_UPDATE_MARKER_POSITION,
						map.getJavaMapSupport()));
		map.getFunctions()
				.add(new InitialConfigurationFunction(
						map.getMapControl(),
						MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION,
						map.getJavaMapSupport()));
	}

	private Map<String, java.util.List<Marker>> pathMap = new HashMap<String, java.util.List<Marker>>();

	public void addPoint(Marker point, String name) {
		java.util.List<Marker> pl = pathMap.get(name);
		if (pl == null)
			pl = new ArrayList<Marker>();
		pl.add(point);
		pathMap.put(name, pl);
	}

	/**
	 * Returns the list of markers added on the map. It's a list of coordinates.
	 * 
	 * @return list of markers
	 */
	public Map<String, java.util.List<Marker>> getPointsList() {
		return pathMap;
	}

	private boolean initPoints = false;
	private Combo paths;

	@Override
	protected Object initConfigurationFunction(Object[] arguments) {
		initPoints = true;
		try {
			map.getJavascriptMapSupport().evaluateJavascript(
					"MENU_KIND=_MENU_COMPLETE");
			super.initConfigurationFunction(arguments);
			java.util.List<Marker> p = getPoints4Path();
			if (p == null || p.isEmpty())
				return null;
			for (Marker m : p) {
				map.getJavascriptMapSupport().addNewMarker(m);
				pointsWidget.add(String
						.format("%.6f", m.getPosition().getLat())
						+ " : "
						+ String.format("%.6f", m.getPosition().getLng()));
			}
			drawPolyline();
		} finally {
			initPoints = false;
		}
		return null;

	}

	private void drawPolyline() {
		String snippet = "var pathCoordinates = [\n";
		pointsWidget.removeAll();
		java.util.List<Marker> ps = getPoints4Path();
		for (int i = 0; i < ps.size(); i++) {
			Marker p = ps.get(i);
			snippet += "{lat:" + p.getPosition().getLat() + ", lng: "
					+ p.getPosition().getLng() + "}";
			if (i < ps.size() - 1)
				snippet += ",";
			snippet += "\n";
			pointsWidget.add(String.format("%.6f", p.getPosition().getLat())
					+ " : " + String.format("%.6f", p.getPosition().getLng()));
		}
		snippet += "];\n";
		snippet += "mypath.setMap(null);\n";
		snippet += "mypath = new google.maps.Polyline({\n";
		snippet += "path: pathCoordinates,\n";
		snippet += "geodesic: true,\n";
		snippet += "strokeColor: '#FF0000',\n";
		snippet += "strokeOpacity: 1.0,\n";
		snippet += "strokeWeight: 2\n";
		snippet += " });\n";
		snippet += "mypath.setMap(myMap.map);\n";

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
			if (initPoints)
				return;
			super.removeMarker(markerIndex);
			pointsWidget.remove(markerIndex);
			getPoints4Path().remove(markerIndex);
			drawPolyline();
		}

		@Override
		public void highlightMarker(int markerIdx) {
			if (initPoints)
				return;
			super.highlightMarker(markerIdx);
			pointsWidget.setSelection(markerIdx);
		}

		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			if (initPoints)
				return;
			super.updateMarkerPosition(markerIdx, newPosition);
			java.util.List<Marker> p = getPoints4Path();
			p.get(markerIdx).setPosition(newPosition);
			drawPolyline();
		}

		@Override
		public void clearMarkers() {
			if (initPoints)
				return;
			super.clearMarkers();
			pointsWidget.removeAll();
			getPoints4Path().clear();
			drawPolyline();
		}

		@Override
		public void removeMarker(Marker oldMarker) {
			if (initPoints)
				return;
			int mIdx = getMarkers().indexOf(oldMarker);
			if (mIdx > 0) {
				getMarkers().remove(mIdx);
				pointsWidget.remove(mIdx);
				getPoints4Path().remove(mIdx);
				drawPolyline();
			} else {
				// FIXME do nothing or raise error (at least log)?!
			}
		}

		@Override
		public void addNewMarker(Marker newMarker) {
			if (initPoints)
				return;
			super.addNewMarker(newMarker);
			java.util.List<Marker> ps = getPoints4Path();
			if (ps == null) {
				ps = new ArrayList<Marker>();
				pathMap.put(paths.getText(), ps);
				paths.add(paths.getText());
				changePath();
				initPoints = true;
				try {
					map.getJavascriptMapSupport().addNewMarker(newMarker);
				} finally {
					initPoints = false;
				}

			}
			ps.add(newMarker);
			drawPolyline();
		}
	}

	private java.util.List<Marker> getPoints4Path() {
		return pathMap.get(paths.getText());
	}
}
