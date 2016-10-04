/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
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
