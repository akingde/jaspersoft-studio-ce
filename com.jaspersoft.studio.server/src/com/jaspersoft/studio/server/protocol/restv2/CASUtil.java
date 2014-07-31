/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.util.CastorUtil;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.preferences.CASListFieldEditor;
import com.jaspersoft.studio.server.preferences.CASPreferencePage;
import com.jaspersoft.studio.server.preferences.SSOServer;
import com.jaspersoft.studio.server.protocol.ConnectionManager;
import com.jaspersoft.studio.server.utils.HttpUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class CASUtil {
	public static String getToken(ServerProfile sp, IProgressMonitor monitor) throws Exception {
		String v = null;
		v = JasperReportsConfiguration.getDefaultInstance().getPrefStore().getString(CASPreferencePage.CAS);
		for (String line : v.split("\n")) {
			if (line.isEmpty())
				continue;
			try {
				SSOServer srv = (SSOServer) CastorUtil.read(new ByteArrayInputStream(Base64.decodeBase64(line)), CASListFieldEditor.mapping);
				if (srv.getUuid().equals(sp.getSsoUuid()))
					return doGetTocken(sp, srv, monitor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		throw new Exception("Could not find SSO Server in the list.");
	}

	public static String doGetTocken(ServerProfile sp, SSOServer srv, IProgressMonitor monitor) throws Exception {
		SSLContext sslContext = SSLContext.getInstance("SSL");

		// set up a TrustManager that trusts everything
		sslContext.init(null, new TrustManager[] { new X509TrustManager() {
			public X509Certificate[] getAcceptedIssuers() {
				System.out.println("getAcceptedIssuers =============");
				return null;
			}

			public void checkClientTrusted(X509Certificate[] certs, String authType) {
				System.out.println("checkClientTrusted =============");
			}

			public void checkServerTrusted(X509Certificate[] certs, String authType) {
				System.out.println("checkServerTrusted =============");
			}
		} }, new SecureRandom());

		// Allow TLSv1 protocol only
		SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new String[] { "TLSv1" }, null, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

		CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();

		Executor exec = Executor.newInstance(httpclient);

		String url = srv.getUrl();
		if (!url.endsWith("/"))
			url += "/";
		URIBuilder ub = new URIBuilder(srv.getUrl() + "cas/v1/tickets");
		ub.addParameter("username", srv.getUser());
		ub.addParameter("password", srv.getPassword());

		Request req = HttpUtils.post(ub.build().toASCIIString(), sp);

		String tgtID = readData(exec, req, monitor);

		ub = new URIBuilder(srv.getUrl() + "cas/v1/tickets/" + tgtID);
		ub.addParameter("username", srv.getUser());
		ub.addParameter("password", srv.getPassword());

		req = HttpUtils.post(ub.build().toASCIIString(), sp);

		return null;
	}

	private static String readData(Executor exec, Request req, IProgressMonitor monitor) throws IOException {
		String obj = null;
		ConnectionManager.register(monitor, req);
		try {
			obj = exec.execute(req).handleResponse(new ResponseHandler<String>() {

				public String handleResponse(final HttpResponse response) throws IOException {
					HttpEntity entity = response.getEntity();
					InputStream in = null;
					try {
						StatusLine statusLine = response.getStatusLine();
						switch (statusLine.getStatusCode()) {
						case 200:
							in = getContent(entity);
							return IOUtils.toString(in);
						default:
							throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
						}
					} finally {
						FileUtils.closeStream(in);
					}
				}

				protected InputStream getContent(HttpEntity entity) throws ClientProtocolException, IOException {
					if (entity == null)
						throw new ClientProtocolException("Response contains no content");
					return entity.getContent();
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} finally {
			ConnectionManager.unregister(req);
		}
		return obj;
	}
}
