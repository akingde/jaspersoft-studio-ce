/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.support;

import java.util.List;

import org.eclipse.swt.browser.Browser;

import com.jaspersoft.studio.widgets.map.core.LatLng;
import com.jaspersoft.studio.widgets.map.core.MapType;
import com.jaspersoft.studio.widgets.map.core.Marker;

/**
 * Clients that want to interact with the Map, should implement this interface
 * in order to support the allowed operations for bidirectional mixed
 * Java/Javascript communication.
 * <p>
 * 
 * Whenever it is needed to keep the Java and Javascript sides separated, it is
 * better to use the dedicated interfaces that extend this one.
 * 
 * @see JavaMapSupport
 * @see JSMapSupport
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 */
public interface MapSupport {

	/**
	 * @return the map center coordinates
	 */
	LatLng getMapCenter();

	/**
	 * Sets the map center coordinates.
	 * 
	 * @param position
	 *            the new position
	 */
	void setMapCenter(LatLng position);

	
	void setPanTo(LatLng position);
	
	/**
	 * @return the current map zoom level
	 */
	int getZoomLevel();

	/**
	 * Sets the zoom level for the map.
	 * 
	 * @param newZoomLevel
	 *            the new zoom level
	 */
	void setZoomLevel(int newZoomLevel);

	/**
	 * Adds a new marker on the map.
	 * 
	 * @param newMarker
	 *            the new marker
	 */
	void addNewMarker(Marker newMarker);

	/**
	 * Removes the specified marker from the map.
	 * 
	 * @param oldMarker
	 *            the marker to be deleted
	 */
	void removeMarker(Marker oldMarker);

	/**
	 * Removes the i-th marker from the map.
	 * 
	 * @param markerIndex
	 *            the index of the marker to be deleted
	 */
	void removeMarker(int markerIndex);

	/**
	 * Removes all the markers from the map.
	 */
	void clearMarkers();

	/**
	 * @return the list of markers on the map
	 */
	List<Marker> getMarkers();

	/**
	 * @return the number of markers on the map
	 */
	int getMarkersNum();

	/**
	 * Updates the position of the i-th marker on the map.
	 * 
	 * @param markerIdx
	 *            the marker index
	 * @param newPosition
	 *            the new position
	 */
	void updateMarkerPosition(int markerIdx, LatLng newPosition);

	/**
	 * Highlights the i-th marker on the map.
	 * 
	 * @param markerIdx
	 *            the marker index
	 */
	void highlightMarker(int markerIdx);

	/**
	 * @return browser widget containing the map
	 */
	Browser getBrowserControl();

	/**
	 * Sets the map type.
	 * 
	 * @param mapType
	 *            the new map type
	 */
	void setMapType(MapType mapType);

	/**
	 * @return the map type
	 */
	MapType getMapType();

	// TODO - Add more???
}
