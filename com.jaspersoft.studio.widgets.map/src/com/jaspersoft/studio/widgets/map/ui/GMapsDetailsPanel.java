/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.ui;

import org.eclipse.jface.dialogs.MessageDialog;
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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.widgets.map.MapWidgetConstants;
import com.jaspersoft.studio.widgets.map.browserfunctions.AddNewMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.ClearMarkersList;
import com.jaspersoft.studio.widgets.map.browserfunctions.GMapEnabledFunction;
import com.jaspersoft.studio.widgets.map.browserfunctions.RemoveMarker;
import com.jaspersoft.studio.widgets.map.browserfunctions.UpdateMarkerPosition;
import com.jaspersoft.studio.widgets.map.core.LatLng;
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
@Deprecated
public class GMapsDetailsPanel {

	// SWT widgets
	private List markersList;
	private Text addressLocation;
	private Button goToBtn;
	private Scale mapZoom;
	private Label zoomLevelLbl;
	private Label mapCenterCoordinatesLbl;
	private MapTile map;

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
	public GMapsDetailsPanel(Composite parent, int style) {
		SashForm sash = new SashForm(parent, style | SWT.HORIZONTAL);
		if (parent.getLayout() instanceof GridLayout)
			sash.setLayoutData(new GridData(GridData.FILL_BOTH));

		map = new MapTile(sash, SWT.NONE);
		map.configureJavaSupport(new DetailsPanelMapSupport(map.getMapControl()));
		// Add the functions for Java <-> Javascript communication to the
		// browser instance
		map.getFunctions().add(new AddNewMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_ADD_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new ClearMarkersList(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_CLEAR_MARKERS, map.getJavaMapSupport()));
		map.getFunctions().add(new RemoveMarker(map.getMapControl(), MapWidgetConstants.BROWSER_FUNCTION_REMOVE_MARKER,
				map.getJavaMapSupport()));
		map.getFunctions().add(new UpdateMarkerPosition(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_UPDATE_MARKER_POSITION, map.getJavaMapSupport()));
		map.getFunctions().add(new InitialConfigurationFunction(map.getMapControl(),
				MapWidgetConstants.BROWSER_FUNCTION_INITIAL_CONFIGURATION, map.getJavaMapSupport()));

		Composite containerCmp = new Composite(sash, SWT.BORDER);
		containerCmp.setLayout(new GridLayout(1, true));

		Label mapCenterLbl = new Label(containerCmp, SWT.NONE);
		mapCenterLbl.setText(Messages.GMapsDetailsPanel_MapCenterLbl);

		mapCenterCoordinatesLbl = new Label(containerCmp, SWT.NONE);
		mapCenterCoordinatesLbl.setText(Messages.GMapsDetailsPanel_NoCoordinates);
		mapCenterCoordinatesLbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		Label addressLocationLbl = new Label(containerCmp, SWT.NONE);
		addressLocationLbl.setText(Messages.GMapsDetailsPanel_AddressLookup);

		Group searchGrp = new Group(containerCmp, SWT.BORDER);
		searchGrp.setLayout(new GridLayout(2, false));
		searchGrp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		addressLocation = new Text(searchGrp, SWT.BORDER);
		addressLocation.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 2, 1));

		goToBtn = new Button(searchGrp, SWT.PUSH);
		goToBtn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false, 2, 1));
		goToBtn.setText(Messages.GMapsDetailsPanel_FindBtn);
		goToBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				LatLng coords = GMapUtils.getAddressCoordinates(addressLocation.getText());
				if (coords != null) {
					map.getJavascriptMapSupport().evaluateJavascript(
							"myMap.panTo(new google.maps.LatLng(" + coords.getLat() + "," + coords.getLng() + "));");
					map.getJavascriptMapSupport().evaluateJavascript("myMap.addMarker(new google.maps.LatLng("
							+ coords.getLat() + "," + coords.getLng() + "));");
				} else {
					MessageDialog.openError(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(),
							Messages.GMapsDetailsPanel_LocationErrorTitle, Messages.GMapsDetailsPanel_LocationErrorMsg);
				}
			}
		});

		zoomLevelLbl = new Label(containerCmp, SWT.NONE);
		zoomLevelLbl.setText(Messages.GMapsDetailsPanel_ZoomLbl1);
		zoomLevelLbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		mapZoom = new Scale(containerCmp, SWT.NONE);
		mapZoom.setMinimum(0);
		mapZoom.setMaximum(19);
		mapZoom.setIncrement(1);
		mapZoom.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		mapZoom.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				map.getJavascriptMapSupport().evaluateJavascript("JAVA_TO_JAVASCRIPT_CALLED=true");
				map.getJavascriptMapSupport().setZoomLevel(mapZoom.getSelection());
				zoomLevelLbl.setText(Messages.GMapsDetailsPanel_ZoomLbl2 + mapZoom.getSelection());
			}
		});

		Label markersLbl = new Label(containerCmp, SWT.NONE);
		markersLbl.setText(Messages.GMapsDetailsPanel_MarkersLbl);
		markersLbl.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));

		markersList = new List(containerCmp, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		markersList.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		Button b = new Button(containerCmp, SWT.PUSH);
		b.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, false));
		b.setText(Messages.GMapsDetailsPanel_DeleteMarkersBtn);
		b.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				map.getJavascriptMapSupport().clearMarkers();
			}
		});
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
				if (e.keyCode == SWT.DEL) {
					int markerIdx = markersList.getSelectionIndex();
					RemoveMarker.removeMarker(markerIdx, map.getJavaMapSupport());
					map.getJavascriptMapSupport().evaluateJavascript("JAVA_TO_JAVASCRIPT_CALLED=true");
					map.getJavascriptMapSupport().removeMarker(markerIdx);
				}
			}
		});

		map.activateMapTile();

		sash.setWeights(new int[] { 4, 1 });
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
			map.getJavascriptMapSupport().evaluateJavascript("MENU_KIND=_MENU_COMPLETE");
			return null;
		}

	}

	class DetailsPanelMapSupport extends BaseJavaMapSupport {

		DetailsPanelMapSupport(Browser browser) {
			super(browser);
		}

		@Override
		public void removeMarker(int markerIndex) {
			super.removeMarker(markerIndex);
			markersList.remove(markerIndex);
		}

		@Override
		public void highlightMarker(int markerIdx) {
			super.highlightMarker(markerIdx);
			markersList.setSelection(markerIdx);
		}

		@Override
		public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
			super.updateMarkerPosition(markerIdx, newPosition);
			markersList.setItem(markerIdx, newPosition.getLat() + " : " + newPosition.getLng());
		}

		@Override
		public void clearMarkers() {
			super.clearMarkers();
			markersList.removeAll();
		}

		@Override
		public void removeMarker(Marker oldMarker) {
			int mIdx = getMarkers().indexOf(oldMarker);
			if (mIdx > 0) {
				getMarkers().remove(mIdx);
				markersList.remove(mIdx);
			} else {
				// FIXME do nothing or raise error (at least log)?!
			}
		}

		@Override
		public void addNewMarker(Marker newMarker) {
			super.addNewMarker(newMarker);
			LatLng position = newMarker.getPosition();
			markersList.add(position.getLat() + " : " + position.getLng());
		}

		@Override
		public void setZoomLevel(int newZoomLevel) {
			super.setZoomLevel(newZoomLevel);
			mapZoom.setSelection(newZoomLevel);
			zoomLevelLbl.setText(Messages.GMapsDetailsPanel_ZoomLbl2 + newZoomLevel);
		}

		@Override
		public int getZoomLevel() {
			return mapZoom.getSelection();
		}

		@Override
		public void setMapCenter(LatLng position) {
			super.setMapCenter(position);
			mapCenterCoordinatesLbl.setText("<" + position.getLat() + " , " + position.getLng() + ">");
		}
	}
}
