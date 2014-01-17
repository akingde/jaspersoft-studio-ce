package com.jaspersoft.studio.server.protocol.restv2;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.jasperserver.remote.exception.xml.ErrorDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class RESTv2ExceptionHandler {
	private ARestV2Connection c;
	private Map<String, String> map;

	public RESTv2ExceptionHandler(ARestV2Connection c) {
		this.c = c;
	}

	private Map<String, String> getMap(IProgressMonitor monitor) {
		if (map == null) {
			map = new HashMap<String, String>();
			c.getBundle(map, "jasperserver_messages", monitor);
		}
		return map;
	}

	public void handleException(Response res, IProgressMonitor monitor) throws ClientProtocolException {
		switch (res.getStatus()) {
		case 403:
			String msg = res.getStatusInfo().getReasonPhrase() + "\n" + res.readEntity(String.class);
			throw new HttpResponseException(res.getStatus(), msg);
		case 400:
			if (res.getHeaderString("Content-Type").contains("xml")) {
				List<ErrorDescriptor> list = res.readEntity(new GenericType<List<ErrorDescriptor>>() {
				});
				if (list != null) {
					msg = "";
					for (ErrorDescriptor ed : list)
						msg += buildMessage(monitor, msg, ed);
					throw new HttpResponseException(res.getStatus(), msg);
				}
			}
		case 409:
		case 404:
		case 500:
			if (res.getHeaderString("Content-Type").contains("xml")) {
				ErrorDescriptor ed = res.readEntity(ErrorDescriptor.class);
				msg = buildMessage(monitor, "", ed);
				if (!ed.getErrorCode().contains("{0}") && ed.getParameters() != null)
					for (String str : ed.getParameters())
						msg += "\n" + str;
				throw new HttpResponseException(res.getStatus(), msg);
			}
		default:
			throw new HttpResponseException(res.getStatus(), res.getStatusInfo().getReasonPhrase());
		}
	}

	protected String buildMessage(IProgressMonitor monitor, String msg, ErrorDescriptor ed) {
		if (!msg.isEmpty())
			msg += "\n";
		if (ed.getMessage() != null) {
			if (ed.getParameters() != null)
				msg += MessageFormat.format(ed.getMessage(), (Object[]) ed.getParameters());
			else
				msg += ed.getMessage();
		} else {
			String m = getMap(monitor).get(ed.getErrorCode());
			if (Misc.isNullOrEmpty(m))
				msg += ed.getErrorCode();
			else
				msg += MessageFormat.format(m, (Object[]) ed.getParameters());
		}
		return msg;
	}
}
