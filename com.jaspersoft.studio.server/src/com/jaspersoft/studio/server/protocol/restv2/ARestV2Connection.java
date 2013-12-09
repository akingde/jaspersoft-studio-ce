package com.jaspersoft.studio.server.protocol.restv2;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jaspersoft.jasperserver.dto.serverinfo.ServerInfo;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.IConnection;

public abstract class ARestV2Connection implements IConnection {
	public static final String SUFFIX = "rest_v2/";
	public static final String FORMAT = "xml";
	protected ServerProfile sp;

	protected String url(String suffix) {
		return sp.getUrl() + SUFFIX + suffix;
	}

	protected SimpleDateFormat dateFormat;
	protected SimpleDateFormat timestampFormat;

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

	protected ServerInfo serverInfo;

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

}
