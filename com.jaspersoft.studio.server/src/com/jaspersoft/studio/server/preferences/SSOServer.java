/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.preferences;

import java.util.UUID;

public class SSOServer implements Cloneable {
	private String uuid = UUID.randomUUID().toString();
	private String url = "https://hostname/";
	private SSOTypes type = SSOTypes.CAS;

	public SSOServer() {
		super();
		uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		if (uuid == null)
			uuid = UUID.randomUUID().toString();
		return uuid;
	}

	public void setUuid(String uuid) {
		if (uuid == null)
			uuid = UUID.randomUUID().toString();
		this.uuid = uuid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public SSOTypes getType() {
		return type;
	}

	public void setType(SSOTypes type) {
		this.type = type;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	private String user;
	private String password;
	private String passuuid;

	public String getPassuuid() {
		return passuuid;
	}

	public void setPassuuid(String passuuid) {
		this.passuuid = passuuid;
	}

	@Override
	public SSOServer clone() {
		try {
			return (SSOServer) super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return null;
	}
}
