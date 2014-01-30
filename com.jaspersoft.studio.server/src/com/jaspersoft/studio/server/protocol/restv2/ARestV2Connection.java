package com.jaspersoft.studio.server.protocol.restv2;

import java.text.DateFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.dto.resources.ClientResource;
import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.Feature;
import com.jaspersoft.studio.server.protocol.IConnection;

public abstract class ARestV2Connection implements IConnection {
	public static final String SUFFIX = "rest_v2/";
	public static final String FORMAT = "xml";
	protected ServerProfile sp;

	@Override
	public ServerProfile getServerProfile() {
		return sp;
	}

	public boolean connect(IProgressMonitor monitor, ServerProfile sp) throws Exception {
		this.sp = sp;
		return true;
	}

	protected String url(String suffix) {
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
	protected DateFormat timeFormat = new SimpleDateFormat("h:mm:ss");
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
		if (obj instanceof java.sql.Date)
			return dateFormat.format(obj);
		if (obj instanceof Date)
			return timestampFormat.format(obj);
		if (obj instanceof Number)
			return numberFormat.format(obj);
		return obj.toString();
	}

	protected ServerInfo serverInfo;

	@Override
	public ServerInfo getServerInfo() {
		return serverInfo;
	}

	@Override
	public String getWebservicesUri() {
		return sp.getUrl();
	}

	@Override
	public String getUsername() {
		return sp.getUser();
	}

	@Override
	public String getPassword() {
		return sp.getPass();
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

	public abstract void getBundle(Map<String, String> map, String name, IProgressMonitor monitor);

	public abstract List<ResourceDescriptor> getInputControls(String uri, IProgressMonitor monitor) throws Exception;
}
