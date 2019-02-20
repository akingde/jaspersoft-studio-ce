/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.support;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;

/**
 * Basic implementation of the support to the map component (Java side). Usually
 * clients will need to extends this one in order to provide for specific
 * operations, a custom behavior for UI components.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class BaseJavaMapSupport implements JavaMapSupport {

	private List<Marker> markers;
	private LatLng mapCenter;
	private LatLng panTo;
	private int zoomLevel;
	private MapType mapType;
	private Browser browser;

	public BaseJavaMapSupport(Browser browser) {
		this.browser = browser;
	}

	@Override
	public LatLng getMapCenter() {
		return mapCenter;
	}

	@Override
	public void setMapCenter(LatLng position) {
		this.mapCenter = position;
	}

	@Override
	public void setPanTo(LatLng position) {
		this.panTo = position;
	}

	public LatLng getPanTo() {
		return panTo;
	}

	@Override
	public int getZoomLevel() {
		return zoomLevel;
	}

	@Override
	public void setZoomLevel(int newZoomLevel) {
		this.zoomLevel = newZoomLevel;
	}

	@Override
	public void addNewMarker(Marker newMarker) {
		getMarkers().add(newMarker);
	}

	@Override
	public void removeMarker(Marker oldMarker) {
		int mIdx = getMarkers().indexOf(oldMarker);
		if (mIdx > 0) {
			getMarkers().remove(mIdx);
		} else {
			// FIXME do nothing or raise error (at least log)?!
		}
	}

	@Override
	public void removeMarker(int markerIndex) {
		getMarkers().remove(markerIndex);
	}

	@Override
	public void clearMarkers() {
		getMarkers().clear();
	}

	@Override
	public List<Marker> getMarkers() {
		if (markers == null) {
			markers = new ArrayList<>();
		}
		return markers;
	}

	@Override
	public int getMarkersNum() {
		return getMarkers().size();
	}

	@Override
	public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
		Marker marker = getMarkers().get(markerIdx);
		marker.setPosition(newPosition);
	}

	@Override
	public void highlightMarker(int markerIdx) {

	}

	@Override
	public Browser getBrowserControl() {
		return browser;
	}

	@Override
	public void setMapType(MapType mapType) {
		this.mapType = mapType;
	}

	@Override
	public MapType getMapType() {
		return mapType;
	}

}
