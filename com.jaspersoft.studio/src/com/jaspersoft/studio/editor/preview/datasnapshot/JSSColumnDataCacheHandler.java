/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.datasnapshot;

import java.util.Date;

import net.sf.jasperreports.data.cache.ColumnDataCacheHandler;
import net.sf.jasperreports.data.cache.DataRecorder;
import net.sf.jasperreports.data.cache.DataSnapshot;

public class JSSColumnDataCacheHandler extends ColumnDataCacheHandler {
	private Date creationTimestamp;

	public Date getCreationTimestamp() {
		return creationTimestamp;
	}

	public JSSColumnDataCacheHandler() {
		super();
	}

	public JSSColumnDataCacheHandler(DataSnapshot snapshot, Date creationTimestamp) {
		super();
		setDataSnapshot(snapshot);
		this.creationTimestamp = creationTimestamp;
	}

	@Override
	public DataRecorder createDataRecorder() {
		creationTimestamp = new Date();

		return super.createDataRecorder();
	}
}
