/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;
import com.jaspersoft.studio.server.utils.Pass;

public abstract class ARestV2Connection implements IConnection {
	public static final String SUFFIX = "rest_v2/";
	public static final String FORMAT = "json";
	protected ServerProfile sp;
	protected IConnection parent;
	protected final Logger logger = java.util.logging.Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Override
	public void setParent(IConnection parent) {
		this.parent = parent;
	}

	@Override
	public ServerProfile getServerProfile() {
		return sp;
	}

	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		this.sp = sp;
		return true;
	}

	protected String url(String suffix) throws MalformedURLException, URISyntaxException {
		return sp.getUrl() + SUFFIX + suffix;
	}

	public Format getDateFormat() {
		return dateFormat;
	}

	public Format getTimestampFormat() {
		return timestampFormat;
	}

	public Format getTimeFormat() {
		return timeFormat;
	}

	public Format getNumberFormat() {
		return numberFormat;
	}

	protected DateFormat dateFormat = DateFormat.getDateInstance();
	protected DateFormat timestampFormat = DateFormat.getDateTimeInstance();
	protected DateFormat timeFormat = new SimpleDateFormat("h:mm:ss", Locale.US);
	protected NumberFormat numberFormat = NumberFormat.getInstance();

	public Date toDate(String sdate) throws ParseException {
		if (sdate == null)
			return null;
		return dateFormat.parse(sdate);
	}

	public Date toTimestamp(String sdate) throws ParseException {
		if (sdate == null)
			return null;
		return timestampFormat.parse(sdate);
	}

	public String date2str(Date d) throws ParseException {
		if (d == null)
			return null;
		return dateFormat.format(d);
	}

	public String timestamp2str(Date d) throws ParseException {
		if (d == null)
			return null;
		return timestampFormat.format(d);
	}

	public String toRestString(Object obj) {
		if (obj == null)
			return null;
		if (obj instanceof java.sql.Date)
			return dateFormat.format(obj);
		if (obj instanceof Date)
			return timestampFormat.format(obj);
		if (obj instanceof Number)
			return obj.toString();
		return obj.toString();
	}

	protected ServerInfo serverInfo;

	@Override
	public ServerInfo getServerInfo() {
		return serverInfo;
	}

	@Override
	public String getWebservicesUri() {
		try {
			return sp.getUrl();
		} catch (MalformedURLException | URISyntaxException e) {
			logger.log(Level.FINE, e.getMessage(), e);
		}
		return null;
	}

	@Override
	public String getUsername() {
		return sp.getUser();
	}

	@Override
	public String getPassword(IProgressMonitor monitor) throws Exception {
		if (parent != null)
			return getPassword(monitor);
		return Pass.getPass(sp.getPass());
	}

	@Override
	public ResourceDescriptor toResourceDescriptor(ClientResource<?> rest) throws Exception {
		return Rest2Soap.getRD(this, rest);
	}

	@Override
	public boolean isSupported(Feature f) {
		return false;
	}

	protected RESTv2ExceptionHandler eh;

	public RESTv2ExceptionHandler getEh() {
		return eh;
	}

	public abstract void getBundle(Map<String, String> map, String name, IProgressMonitor monitor) throws Exception;

	public abstract List<ResourceDescriptor> getInputControls(String uri, IProgressMonitor monitor) throws Exception;

	@Override
	public Integer getPermissionMask(ResourceDescriptor rd, IProgressMonitor monitor) throws Exception {
		return rd.getPermissionMask(null);
	}
}
