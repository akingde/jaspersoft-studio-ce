/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.widgets.map.core;

import com.jaspersoft.studio.widgets.map.messages.Messages;

/**
 * The common types that can be set on a Google Map.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * @see Marker
 * @see {@link https://developers.google.com/maps/documentation/javascript/reference#MapTypeId}
 *
 */
public enum MapType {
	ROADMAP("roadmap","google.maps.MapTypeId.ROADMAP"), //$NON-NLS-1$ //$NON-NLS-2$
	SATELLITE("satellite","google.maps.MapTypeId.SATELLITE"), //$NON-NLS-1$ //$NON-NLS-2$
	TERRAIN("terrain","google.maps.MapTypeId.TERRAIN"), //$NON-NLS-1$ //$NON-NLS-2$
	HYBRID("hybrid","google.maps.MapTypeId.HYBRID"); //$NON-NLS-1$ //$NON-NLS-2$
	
	private String stringID;
	private String googleConstant;
	
	private MapType(String stringID, String googleConstant) {
		this.stringID=stringID;
		this.googleConstant=googleConstant;
	}

	public String getStringID() {
		return stringID;
	}

	public String getGoogleConstant() {
		return googleConstant;
	}

	public static MapType fromStringID(String stringID){
		for(MapType val : MapType.values()) {
			if(val.getStringID().equals(stringID)) {
				return val;
			}
		}
		throw new IllegalArgumentException(Messages.MapType_InvalidType);
	}
}
