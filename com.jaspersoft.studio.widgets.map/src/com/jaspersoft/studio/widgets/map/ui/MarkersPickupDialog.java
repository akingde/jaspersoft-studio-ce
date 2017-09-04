/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
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
import com.jaspersoft.studio.widgets.map.messages.Messages;
import com.jaspersoft.studio.widgets.map.support.BaseJavaMapSupport;

/**
 * This dialog allows to place a list of markers in the map. Markers can be
 * moved/removed and their position is update accordingly.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
@Deprecated
public class MarkersPickupDialog extends BasicInfoMapDialog {

	private java.util.List<Marker> markers;
	private List markersWidget;

	/**
	 * Create the dialog.
	 * 
	 * @param parentShell
	 */
	public MarkersPickupDialog(Shell parentShell) {
		super(parentShell);
		this.markers = new ArrayList<Marker>();
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
		markersLbl.setText(Messages.MarkersPickupDialog_MarkersLbl);
		markersLbl.setLayoutData(new GridData(SWT.TOP, SWT.LEFT, true, false));

		markersWidget = new List(panelCmp, SWT.BORDER | SWT.V_SCROLL
				| SWT.H_SCROLL);
		markersWidget
				.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Button delMarkersBtn = new Button(panelCmp, SWT.PUSH);
		delMarkersBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, false,
				false));
		delMarkersBtn.setText(Messages.MarkersPickupDialog_DeleteMarkersBtn);
		delMarkersBtn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				map.getJavascriptMapSupport().clearMarkers();
			}
		});
		markersWidget.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int markerIdx = markersWidget.getSelectionIndex();
				map.getJavascriptMapSupport().highlightMarker(markerIdx);
			}
		});
		markersWidget.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.keyCode == SWT.DEL) {
					int markerIdx = markersWidget.getSelectionIndex();
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
				MarkersPickupDialog.this.setZoomLevel(newZoomLevel);
			}

			@Override
			public void setMapCenter(LatLng position) {
				MarkersPickupDialog.this.setMapCenter(position);
			}

			@Override
			public void setMapType(MapType mapType) {
				MarkersPickupDialog.this.setMapType(mapType);
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

	/**
	 * Returns the list of markers added on the map. It's a list of coordinates.
	 * 
	 * @return list of markers
	 */
	public java.util.List<Marker> getMarkersList() {
		return markers;
	}

	private boolean initMarkers = false;

	@Override
	protected Object initConfigurationFunction(Object[] arguments) {
		initMarkers = true;
		try {
			map.getJavascriptMapSupport().evaluateJavascript(
					"MENU_KIND=_MENU_COMPLETE");
			super.initConfigurationFunction(arguments);
			if (markers.isEmpty())
				return null;
			for (Marker m : markers) {
				map.getJavascriptMapSupport().addNewMarker(m);
				markersWidget.add(String.format("%.6f", m.getPosition()
						.getLat())
						+ " : "
						+ String.format("%.6f", m.getPosition().getLng()));
			}
		} finally {
			initMarkers = false;
		}
		return null;

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
			markersWidget.remove(markerIndex);
			markers.remove(markerIndex);
		}

		@Override
		public void highlightMarker(int markerIdx) {
			if (initMarkers)
				return;
			super.highlightMarker(markerIdx);
			markersWidget.setSelection(markerIdx);
		}

		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			if (initMarkers)
				return;
			super.updateMarkerPosition(markerIdx, newPosition);
			markersWidget.setItem(markerIdx,
					String.format("%.6f", newPosition.getLat()) + " : "
							+ String.format("%.6f", newPosition.getLng()));
		}

		@Override
		public void clearMarkers() {
			if (initMarkers)
				return;
			super.clearMarkers();
			markersWidget.removeAll();
			markers.clear();
		}

		@Override
		public void removeMarker(Marker oldMarker) {
			if (initMarkers)
				return;
			int mIdx = getMarkers().indexOf(oldMarker);
			if (mIdx > 0) {
				getMarkers().remove(mIdx);
				markersWidget.remove(mIdx);
				markers.remove(mIdx);
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
			markersWidget.add(String.format("%.6f", position.getLat()) + " : "
					+ String.format("%.6f", position.getLng()));
			markers.add(newMarker);
		}

	}
}
