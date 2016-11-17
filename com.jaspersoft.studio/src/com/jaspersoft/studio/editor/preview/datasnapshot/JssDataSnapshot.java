/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.datasnapshot;

import java.io.Serializable;
import java.util.Date;

import net.sf.jasperreports.data.cache.DataSnapshot;
import net.sf.jasperreports.engine.JRConstants;

public class JssDataSnapshot implements Serializable {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private Date creationTimestamp;
	private DataSnapshot snapshot;

	public JssDataSnapshot(Date creationTimestamp, DataSnapshot snapshot) {
		super();
		this.creationTimestamp = creationTimestamp;
		this.snapshot = snapshot;
	}

	public DataSnapshot getSnapshot() {
		return snapshot;
	}

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}
}
