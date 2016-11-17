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
import com.jaspersoft.studio.widgets.map.messages.Messages;

/**
 * Basic implementation of the support to the map component (Javascript side).
 * For most use case there will be no need for further customization.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class BaseJSMapSupport implements JSMapSupport {
	
	private Browser browser;
	private String mapId;
	
	public BaseJSMapSupport(Browser browser){
		this(browser,"myMap");//$NON-NLS-1$
	}
	
	public BaseJSMapSupport(Browser browser, String mapId) {
		this.browser = browser;
		this.mapId = mapId;
	}

	@Override
	public LatLng getMapCenter() {
		return new LatLng(
				(Double) getBrowserControl().evaluate("return "+mapId+".getCenter().lat();"), 
				(Double) getBrowserControl().evaluate("return "+mapId+".getCenter().lng();"));
	}

	@Override
	public void setMapCenter(LatLng position) {
		String positionVar = "new google.maps.LatLng("
				+ position.getLat() + "," + position.getLng() + ")";
		getBrowserControl().evaluate(mapId+".setCenter("+positionVar+");");
	}
	@Override
	public void setPanTo(LatLng position) {
		String positionVar = "new google.maps.LatLng("
				+ position.getLat() + "," + position.getLng() + ")";
		getBrowserControl().evaluate(mapId+".panTo("+positionVar+");");
	}

	@Override
	public int getZoomLevel() {
		return ((Double) 
				getBrowserControl().evaluate("return "+mapId+".getZoom();")).intValue();
	}

	@Override
	public void setZoomLevel(int newZoomLevel) {
		getBrowserControl().evaluate(mapId+".setZoom("+newZoomLevel+");");
	}

	@Override
	public void addNewMarker(Marker newMarker) {
		// FIXME - we currently miss to create a marker options array.
		String markerPosition = "new google.maps.LatLng("
				+ newMarker.getPosition().getLat() + ","
				+ newMarker.getPosition().getLng() + ")";
		getBrowserControl().evaluate(mapId+".addMarker("+markerPosition+");");
	}

	@Override
	public void removeMarker(Marker oldMarker) {
		// TODO - A check on position could be fine?!
		throw new UnsupportedOperationException(Messages.BaseJSMapSupport_UnsupportedMethodError);
	}

	@Override
	public void removeMarker(int markerIndex) {
		getBrowserControl().evaluate(mapId+".removeMarkerByIndex("+markerIndex+")");
	}

	@Override
	public void clearMarkers() {
		getBrowserControl().evaluate(mapId+".clearAllMarkers();");
	}

	@Override
	public List<Marker> getMarkers() {
		// TODO - we could get back an XML representation of the markers as string
		//		  and later convert them.
		throw new UnsupportedOperationException(Messages.BaseJSMapSupport_UnsupportedMethodError);
	}

	@Override
	public int getMarkersNum() {
		return ((Double) getBrowserControl().evaluate(
				"return " + mapId + ".mapMarkers.length;")).intValue();
	}

	@Override
	public void updateMarkerPosition(int markerIdx, LatLng newPosition) {
		String newPositionStr = "new google.maps.LatLng("
				+ newPosition.getLat() + "," + newPosition.getLng() + ")";
		getBrowserControl().evaluate(mapId+".mapMarkers["+markerIdx+"].setPosition("+newPositionStr+");");
	}

	@Override
	public void highlightMarker(int markerIdx) {
		getBrowserControl().evaluate(mapId+".bounceMarker("+markerIdx+");");
	}

	@Override
	public Browser getBrowserControl() {
		return this.browser;
	}

	@Override
	public void setMapType(MapType mapType) {
		getBrowserControl().evaluate(mapId+".setMapType("+mapType.getGoogleConstant()+");");
	}

	@Override
	public MapType getMapType() {
		return MapType.fromStringID(
				(String) getBrowserControl().evaluate("return "+mapId+".getMapType();"));
	}

	@Override
	public Object evaluateJavascript(String snippet) {
		return getBrowserControl().evaluate(snippet);
	}

}
