package com.jaspersoft.studio.components.map.model.marker;

import java.util.List;

import net.sf.jasperreports.components.map.Marker;

import com.jaspersoft.studio.model.ANode;

public class MarkersDTO {
	private List<Marker> marker;
	private ANode pnode;

	public MarkersDTO(List<Marker> propExpressions, ANode pnode) {
		super();
		this.marker = propExpressions;
		this.pnode = pnode;
	}

	public List<Marker> getMarkers() {
		return marker;
	}

	public void setMarkers(List<Marker> propExpressions) {
		this.marker = propExpressions;
	}

	public ANode getPnode() {
		return pnode;
	}

	public void setPnode(ANode pnode) {
		this.pnode = pnode;
	}

}
