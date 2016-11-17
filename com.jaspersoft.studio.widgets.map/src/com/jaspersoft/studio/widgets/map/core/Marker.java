/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.core;

import java.io.Serializable;

/**
 * A marker that can be placed on the map.
 * <p>
 * 
 * NOTE: Remember that the position must be set for the marker to display.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see {@link https://developers.google.com/maps/documentation/javascript/reference#Marker}
 */
public class Marker implements Serializable{

	private static final long serialVersionUID = -4902093998566699479L;
	private MarkerOptions markerOptions;
	
	public Marker(LatLng position) {
		this.markerOptions = MarkerOptions.getDefault(position);
	}
	
	public Marker(MarkerOptions configuration) {
		this.markerOptions = configuration;
	}
	
	public MarkerOptions getOptions() {
		return this.markerOptions;
	}
	
	public Animation getAnimation() {
		return getOptions().getAnimation();
	}

	public Boolean getClickable() {
		return getOptions().isClickable();
	}
	
	public String getCursor() {
		return getOptions().getCursor();
	}
	
	public Boolean getDraggable() {
		return getOptions().isDraggable();
	}
	
	public Boolean getFlat() {
		return getOptions().isFlat();
	}
	
	public LatLng getPosition() {
		return getOptions().getPosition();
	}
	
	public String getTitle() {
		return getOptions().getTitle();
	}
	
	public Boolean getVisible() {
		return getOptions().isVisible();
	}
	
	public Integer getZIndex() {
		return getOptions().getZIndex();
	}
	
	public void setAnimation(Animation newAnimation) {
		getOptions().setAnimation(newAnimation);
	}
	
	public void setClickable(Boolean isClickable) {
		getOptions().setClickable(isClickable);
	}
	
	public void setCursor(String cursor) {
		getOptions().setCursor(cursor);
	}
	
	public void setDraggable(Boolean isDraggable) {
		getOptions().setDraggable(isDraggable);
	}
	
	public void setFlat(Boolean isFlat) {
		getOptions().setFlat(isFlat);
	}
	
	public void setOptions(MarkerOptions newOptions) {
		this.markerOptions = newOptions;
	}
	
	public void setPosition(LatLng newPosition) {
		getOptions().setPosition(newPosition);
	}

	public void setTitle(String title) {
		getOptions().setTitle(title);
	}
	
	public void setVisible(Boolean isVisible) {
		getOptions().setVisible(isVisible);
	}
	
	public void setZIndex(Integer zIndex) {
		getOptions().setZIndex(zIndex);
	}
}

