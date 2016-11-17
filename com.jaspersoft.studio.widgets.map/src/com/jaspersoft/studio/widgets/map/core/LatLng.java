/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.core;

import java.io.Serializable;

/**
 * A {@link LatLng} is a point in geographical coordinates: latitude and
 * longitude. It's a representation of the <code>google.maps.LatLng</code>
 * class.
 * 
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see {@link https://developers.google.com/maps/documentation/javascript/reference#LatLng}
 * 
 */
public class LatLng implements Serializable {

	private static final long serialVersionUID = 3334965103527376772L;

	private Double lat;
	private Double lng;

	/**
	 * Creates a {@link LatLng} object representing a geographic point.
	 * 
	 * @param lat
	 *            the latitude
	 * @param lng
	 *            the longitude
	 */
	public LatLng(Double lat, Double lng) {
		this(lat, lng, false);
	}

	/**
	 * Creates a {@link LatLng} object representing a geographic point. Set
	 * <code>noWrap</code> to true to enable values outside of this range.
	 * 
	 * @param lat
	 *            the latitude
	 * @param lng
	 *            the longitude
	 * @param noWrap
	 *            flag to allow values outside the ranges
	 */
	public LatLng(Double lat, Double lng, boolean noWrap) {
		this.lat = lat;
		this.lng = lng;
		if (!noWrap) {
			if (Math.abs(lat) >= 85.05115) {
				lat = lat % 85.05115;
				// throw new RuntimeException(
				// "You must specify the \'noWrap\' setting to true in order to
				// allow latitude values outside the range");
			}
			if (Math.abs(lng) >= 180) {
				lng = lng % 180;
				// throw new RuntimeException(
				// "You must specify the \'noWrap\' setting to true in order to
				// allow longitude values outside the range");
			}
		}
	}

	/**
	 * Gets the longitude value which ranges between -180 and 180 degrees,
	 * inclusive. Values above or below this range will be wrapped such that
	 * they fall within the range [-180, 180).
	 * <p>
	 * 
	 * For example, 480, 840 and 1200 will all be wrapped to 120 degrees.
	 * 
	 * @return the longitude in degrees.
	 */
	public Double getLng() {
		return this.lng % 180;
	}

	/**
	 * Gets the latitude value which ranges between -90 and 90 degrees,
	 * inclusive. Values above or below this range will be clamped to the
	 * nearest value within this range.
	 * <p>
	 * 
	 * For example, specifying a latitude of 100 will set the value to 90.
	 * 
	 * @return the latitude in degrees.
	 */
	public Double getLat() {
		return this.lat % 90;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof LatLng) {
			LatLng coords = (LatLng) obj;
			return getLat() == coords.getLat() && getLng() == coords.getLng();
		}
		return false;
	}

	public String toJsString() {
		return String.format("new google.maps.LatLng(%.7f,%.7f,true)", lat, lng);
	}
}
