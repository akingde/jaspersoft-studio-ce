/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.model.server;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import com.jaspersoft.jasperserver.dto.authority.ClientUser;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.server.utils.HttpUtils;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.design.events.JRChangeEventsSupport;
import net.sf.jasperreports.engine.design.events.JRPropertyChangeSupport;
import net.sf.jasperreports.repo.Resource;

public class ServerProfile implements Resource, Cloneable, Serializable, JRChangeEventsSupport {

	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public static final String PROPERTY_NAME = "profile_name";

	private boolean supportsDateRanges;
	private String name;
	private String url;
	private String user;
	private String organisation;
	private String jrVersion = JRXmlWriterHelper.LAST_VERSION;
	private int timeout = 60000;
	private boolean chunked;
	private boolean mime = true;
	private String projectPath;
	private boolean useOnlySOAP = false;
	private boolean syncDA = false;
	private String locale;
	private String timeZone;
	private boolean askPass = false;
	private boolean useSSO = false;
	private String ssoUuid;
	private transient ClientUser clientUser;
	private boolean logging = false;

	private JRPropertyChangeSupport propertyChange = new JRPropertyChangeSupport(this);

	public boolean isLogging() {
		return logging;
	}

	public void setAskPass(boolean askPass) {
		this.askPass = askPass;
	}

	public boolean isAskPass() {
		return askPass;
	}

	public void setLogging(boolean logging) {
		this.logging = logging;
	}

	public ClientUser getClientUser() {
		return clientUser;
	}

	public void setClientUser(ClientUser clientUser) {
		this.clientUser = clientUser;
	}

	public String getSsoUuid() {
		return ssoUuid;
	}

	public void setSsoUuid(String ssoUuid) {
		this.ssoUuid = ssoUuid;
	}

	public boolean isUseSSO() {
		return useSSO;
	}

	public void setUseSSO(boolean useSSO) {
		this.useSSO = useSSO;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public boolean isSyncDA() {
		return syncDA;
	}

	public void setSyncDA(boolean syncDA) {
		this.syncDA = syncDA;
	}

	public boolean isUseOnlySOAP() {
		if (useSSO)
			return false;
		return useOnlySOAP;
	}

	public void setUseOnlySOAP(boolean useOnlySOAP) {
		this.useOnlySOAP = useOnlySOAP;
	}

	public boolean isMime() {
		return mime;
	}

	public void setMime(boolean mime) {
		this.mime = mime;
	}

	public String getProjectPath() {
		return projectPath;
	}

	public void setProjectPath(String projectPath) {
		this.projectPath = projectPath;
	}

	public String getJrVersion() {
		return jrVersion;
	}

	public void setJrVersion(String jrVersion) {
		this.jrVersion = jrVersion;
	}

	public String getOrganisation() {
		return organisation;
	}

	public void setOrganisation(String organisation) {
		this.organisation = organisation;
	}

	private String pass;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		String oldName = this.name;
		this.name = name;
		// Notify the name changed
		propertyChange.firePropertyChange(PROPERTY_NAME, oldName, name);
	}

	public String getUrl() throws MalformedURLException, URISyntaxException {
		if (url == null)
			return null;
		if (!url.endsWith("/"))
			url += "/";
		url = HttpUtils.toSafeUri(new URL(url)).toASCIIString();
		return url;
	}
	public String getUrlString() {
		return url;
	}

	private URL url_;

	public URL getURL() throws MalformedURLException, URISyntaxException {
		if (url_ == null)
			url_ = new URL(getUrl());
		return url_;
	}

	public void setUrl(String url) {
		this.url = url;
		this.url_ = null;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public boolean isChunked() {
		return chunked;
	}

	public void setChunked(boolean chunked) {
		this.chunked = chunked;
	}

	public boolean isSupportsDateRanges() {
		return supportsDateRanges;
	}

	public void setSupportsDateRanges(boolean supportsDateRanges) {
		this.supportsDateRanges = supportsDateRanges;
	}

	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public JRPropertyChangeSupport getEventSupport() {
		return propertyChange;
	}
}
