/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.components.map.model.marker;

import java.util.List;

import net.sf.jasperreports.components.items.Item;

import com.jaspersoft.studio.model.ANode;

public class MarkersDTO {
	private List<Item> marker;
	private ANode pnode;

	public MarkersDTO(List<Item> propExpressions, ANode pnode) {
		super();
		this.marker = propExpressions;
		this.pnode = pnode;
	}

	public List<Item> getMarkers() {
		return marker;
	}

	public void setMarkers(List<Item> propExpressions) {
		this.marker = propExpressions;
	}

	public ANode getPnode() {
		return pnode;
	}

	public void setPnode(ANode pnode) {
		this.pnode = pnode;
	}

}
