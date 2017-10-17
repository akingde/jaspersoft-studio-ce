/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.model.metadata;

import net.sf.jasperreports.engine.JRConstants;

import org.apache.commons.lang.WordUtils;

import com.jaspersoft.studio.data.sql.model.MDBObjects;
import com.jaspersoft.studio.data.sql.ui.metadata.DBMetadata;

public class MTables extends MDBObjects {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public MTables(MSqlSchema parent, String value) {
		super(parent, value, "icons/table.png");
	}

	public String getTableCatalog() {
		return ((MSqlSchema) getParent()).getTableCatalog();
	}

	public String getTableSchema() {
		return (String) getParent().getValue();
	}

	@Override
	public String getDisplayText() {
		return WordUtils.capitalizeFully(getValue());
	}

	private transient DBMetadata dbMetadata;

	public void setDbMetadata(DBMetadata dbMetadata) {
		this.dbMetadata = dbMetadata;
	}

	public DBMetadata getDbMetadata() {
		return dbMetadata;
	}
}
