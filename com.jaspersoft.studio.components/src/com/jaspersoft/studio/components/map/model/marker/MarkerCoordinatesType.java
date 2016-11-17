/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import net.sf.jasperreports.components.map.MapComponent;

/**
 * Simple enum that allows to distinguish different kind of marker coordinates.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public enum MarkerCoordinatesType {
	LATITUDE_LONGITUDE(Arrays.asList(MapComponent.ITEM_PROPERTY_latitude,
			MapComponent.ITEM_PROPERTY_longitude)), XY(Arrays.asList("x", "y")); //$NON-NLS-1$ //$NON-NLS-2$

	private List<String> mandatoryProperties;

	private MarkerCoordinatesType(List<String> properties) {
		this.mandatoryProperties = new ArrayList<String>(properties.size());
		this.mandatoryProperties.addAll(properties);
	}

	public List<String> getMandatoryProperties() {
		return this.mandatoryProperties;
	}

	public boolean isMandatoryProperty(String propertyName) {
		return this.mandatoryProperties.contains(propertyName);
	}
}
