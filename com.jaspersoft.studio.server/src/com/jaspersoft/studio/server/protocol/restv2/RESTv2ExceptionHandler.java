/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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

import com.jaspersoft.jasperserver.jaxrs.client.dto.common.ErrorDescriptor;

import net.sf.jasperreports.eclipse.util.Misc;

public class RESTv2ExceptionHandler {
	private ARestV2Connection c;
	private Map<String, String> map;

	public RESTv2ExceptionHandler(ARestV2Connection c) {
		this.c = c;
	}

	private Map<String, String> getMap(IProgressMonitor monitor) {
		if (map == null) {
			map = new HashMap<String, String>();
			try {
				c.getBundle(map, "jasperserver_messages", monitor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public void handleException(Response res, IProgressMonitor monitor) throws ClientProtocolException {
		String msg = "";
		int status = res.getStatus();
		String ct = res.getHeaderString("Content-Type");
		switch (status) {
		case 200:
			return;
		case 400:
			if (ct != null) {
				if (ct.equals("application/xml"))
					handleErrorDescriptor(res, monitor, status);
				else if (ct.equals("application/json"))
					handleErrorDescriptor(res, monitor, status);
				else if (ct.contains("application/collection.errorDescriptor+xml")
						|| ct.contains("application/collection.errorDescriptor+json"))
					handleErrorDescriptorList(res, monitor, status);
				else if (ct.contains("application/errorDescriptor+json")
						|| ct.contains("application/errorDescriptor+xml"))
					handleErrorDescriptor(res, monitor, status);
				else
					handleErrorDescriptor(res, monitor, status);
			}
		case 401:
			throw new HttpResponseException(status, res.getStatusInfo().getReasonPhrase());
		case 403:
		case 409:
		case 404:
		case 500:
			if (ct != null) {
				if (ct.contains("application/collection.errorDescriptor+xml")
						|| ct.contains("application/collection.errorDescriptor+json"))
					handleErrorDescriptorList(res, monitor, status);
				else if (ct.contains("xml"))
					handleErrorDescriptor(res, monitor, status);
				else if (ct.contains("text/html")) {
					System.out.println(res.readEntity(String.class));
					msg = res.getStatusInfo().getReasonPhrase() + "\n";
					throw new HttpResponseException(status, msg);
				} else if (ct.contains("application/errorDescriptor+json")
						|| ct.contains("application/errorDescriptor+xml"))
					handleErrorDescriptor(res, monitor, status);
			} else {
				System.out.println(res.readEntity(String.class));
				msg = res.getStatusInfo().getReasonPhrase() + "\n";
				throw new HttpResponseException(status, msg);
			}
		default:
			String cnt = res.readEntity(String.class);
			if (cnt.length() > 100)
				cnt = "";
			msg = res.getStatusInfo().getReasonPhrase() + "\n" + cnt;
			throw new HttpResponseException(status, msg);
		}
	}

	protected void handleErrorDescriptorList(Response res, IProgressMonitor monitor, int status)
			throws HttpResponseException {
		String msg;
		List<ErrorDescriptor> list = res.readEntity(new GenericType<List<ErrorDescriptor>>() {
		});
		if (list != null) {
			msg = "";
			for (ErrorDescriptor ed : list)
				msg = buildMessage(monitor, msg, ed) + "\n";
			throw new HttpResponseException(status, msg);
		}
	}

	protected void handleErrorDescriptor(Response res, IProgressMonitor monitor, int status)
			throws HttpResponseException {
		res.bufferEntity();
		try {
			ErrorDescriptor ed = res.readEntity(ErrorDescriptor.class);
			String msg = ed.getErrorCode() != null ? ed.getErrorCode() + "\n" : "";
			msg += buildMessage(monitor, "", ed);
			if (ed.getErrorCode() != null && !ed.getErrorCode().contains("{0}") && ed.getParameters() != null)
				for (String str : ed.getParameters())
					msg += "\n" + str;
			throw new HttpResponseException(status, msg);
		} catch (Throwable e) {
			if (e instanceof HttpResponseException)
				throw (HttpResponseException) e;
			else if (status == 400)
				handleErrorDescriptorList(res, monitor, status);
			else
				throw new RuntimeException(e);
		}
	}

	public String buildMessage(IProgressMonitor monitor, String msg, ErrorDescriptor ed) {
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
