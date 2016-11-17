/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.ireport.jasperserver.ws;

import javax.activation.DataSource;

/**
 * @author Lucian Chirita
 *
 */
public class RequestAttachment {

	private DataSource dataSource;
	private String contentID;
	
	public RequestAttachment() {
		this(null, null);
	}
	
	public RequestAttachment(DataSource dataSource) {
		this(dataSource, null);
	}
	
	public RequestAttachment(DataSource dataSource, String contentID) {
		this.dataSource = dataSource;
		this.contentID = contentID;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	public String getContentID() {
		return contentID;
	}

	public void setContentID(String contentID) {
		this.contentID = contentID;
	}
	
	
	
}
