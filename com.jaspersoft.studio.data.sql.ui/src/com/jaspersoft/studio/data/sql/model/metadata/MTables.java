package com.jaspersoft.studio.data.sql.model.metadata;

import net.sf.jasperreports.engine.JRConstants;

import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.model.ANode;

public class MTables extends MDBObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MTables(ANode parent, String value) {
		super(parent, value, "icons/table.png");
	}

	public String getTableCatalog() {
		return (String) getParent().getValue();
	}

	public String getTableSchema() {
		return ((MSqlSchema) getParent()).getTableCatalog();
	}
}
