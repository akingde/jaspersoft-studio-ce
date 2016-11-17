/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.core;


/**
 * Groups all the marker options that can be configured for a {@link Marker} instance.
 * <p>
 * 
 * NOTE: Remember that the position must be set for the marker to display.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see {@link https://developers.google.com/maps/documentation/javascript/reference#MarkerOptions}
 */
public class MarkerOptions {

	// Missing fields: 
	// - anchorPoint: The offset from the marker's position to the tip of an InfoWindow that has been opened with the marker as anchor.
	// - icon: Icon for the foreground. If a string is provided, it is treated as though it were an Icon with the string as url.
	// - map: Map on which to display Marker.
	// - shadow: Shadow image. If a string is provided, it is treated as though it were an Icon with the string as url. Shadows are not rendered when google.maps.visualRefresh is set to true.
	// - shape: Image map region definition used for drag/click
	private Animation animation;
	private Boolean clickable;
	private Boolean crossOnDrag;
	private String cursor;
	private Boolean draggable;
	private Boolean flat;
	private Boolean optimized;
	private LatLng position;
	private Boolean raiseOnDrag;
	private String title;
	private Boolean visible;
	private Integer zIndex;
	
	public static MarkerOptions getDefault(LatLng coordinates) {
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions.setPosition(coordinates);
		markerOptions.setDraggable(Boolean.TRUE);
		markerOptions.setAnimation(Animation.DROP);
		markerOptions.setVisible(Boolean.TRUE);
		markerOptions.setClickable(Boolean.TRUE);
		return markerOptions;
	}
	
	public MarkerOptions(){
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public Boolean isClickable() {
		return clickable;
	}

	public void setClickable(Boolean clickable) {
		this.clickable = clickable;
	}

	public Boolean isCrossOnDrag() {
		return crossOnDrag;
	}

	public void setCrossOnDrag(Boolean crossOnDrag) {
		this.crossOnDrag = crossOnDrag;
	}

	public String getCursor() {
		return cursor;
	}

	public void setCursor(String cursor) {
		this.cursor = cursor;
	}

	public Boolean isDraggable() {
		return draggable;
	}

	public void setDraggable(Boolean draggable) {
		this.draggable = draggable;
	}

	public Boolean isFlat() {
		return flat;
	}

	public void setFlat(Boolean flat) {
		this.flat = flat;
	}

	public Boolean isOptimized() {
		return optimized;
	}

	public void setOptimized(Boolean optimized) {
		this.optimized = optimized;
	}

	public LatLng getPosition() {
		return position;
	}

	public void setPosition(LatLng position) {
		this.position = position;
	}

	public Boolean isRaiseOnDrag() {
		return raiseOnDrag;
	}

	public void setRaiseOnDrag(Boolean raiseOnDrag) {
		this.raiseOnDrag = raiseOnDrag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Boolean isVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public Integer getZIndex() {
		return zIndex;
	}

	public void setZIndex(Integer zIndex) {
		this.zIndex = zIndex;
	}

}
