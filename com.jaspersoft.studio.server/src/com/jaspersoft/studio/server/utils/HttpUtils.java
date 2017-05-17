/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.utils;

import java.io.IOException;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.eclipse.core.net.proxy.IProxyChangeEvent;
import org.eclipse.core.net.proxy.IProxyChangeListener;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.RestV2Connection;

public class HttpUtils {
	public static URI toSafeUri(final URL url) throws URISyntaxException {
		return new URI(url.getProtocol(), url.getUserInfo(), IDN.toASCII(url.getHost()), url.getPort(), url.getPath(),
				url.getQuery(), url.getRef());
	}

	public static void setupProxy(ClientConfig clientConfig, URI uri) {
		CredentialsProvider cp = (CredentialsProvider) clientConfig
				.getProperty(ApacheClientProperties.CREDENTIALS_PROVIDER);
		for (IProxyData d : net.sf.jasperreports.eclipse.util.HttpUtils.proxyService.select(uri)) {
			Credentials c = net.sf.jasperreports.eclipse.util.HttpUtils.getCredentials(d);
			if (c != null && cp != null)
				cp.setCredentials(new AuthScope(new HttpHost(d.getHost(), d.getPort())), c);
			clientConfig.property(ClientProperties.PROXY_URI,
					d.getType().toLowerCase() + "://" + d.getHost() + ":" + d.getPort());
			break;
		}
		clientConfigs.put(clientConfig, uri);
	}

	public static Request setRequest(Request req, ServerProfile sp) {
		req.connectTimeout(sp.getTimeout());
		req.socketTimeout(sp.getTimeout());
		if (sp.isChunked())
			req.setHeader("Transfer-Encoding", "chunked");
		else
			req.removeHeaders("Transfer-Encoding");
		req.setHeader("Accept", "application/" + RestV2Connection.FORMAT);
		return req;
	}

	public static Request get(String url, ServerProfile sp) throws HttpException, IOException {
		System.out.println(url);
		return HttpUtils.setRequest(Request.Get(url), sp);
	}

	public static Request put(String url, ServerProfile sp) throws HttpException, IOException {
		System.out.println(url);
		return HttpUtils.setRequest(Request.Put(url), sp);
	}

	public static Request post(String url, ServerProfile sp) throws HttpException, IOException {
		System.out.println(url);
		return HttpUtils.setRequest(Request.Post(url), sp);
	}

	public static Request post(String url, Form form, ServerProfile sp) throws HttpException, IOException {
		System.out.println(url);
		return HttpUtils.setRequest(Request.Post(url).bodyForm(form.build()), sp);
	}

	public static Request delete(String url, ServerProfile sp) throws HttpException, IOException {
		System.out.println(url);
		return HttpUtils.setRequest(Request.Delete(url), sp);
	}

	private static Map<Executor, URI> executors = new HashMap<Executor, URI>();
	private static Map<ClientConfig, URI> clientConfigs = new HashMap<ClientConfig, URI>();

	public static IProxyService getProxyService() {
		BundleContext bc = Activator.getDefault().getBundle().getBundleContext();
		ServiceReference serviceReference = bc.getServiceReference(IProxyService.class.getName());
		IProxyService service = (IProxyService) bc.getService(serviceReference);
		service.addProxyChangeListener(new IProxyChangeListener() {

			@Override
			public void proxyInfoChanged(IProxyChangeEvent event) {
				for (Executor exe : executors.keySet())
					net.sf.jasperreports.eclipse.util.HttpUtils.setupProxy(exe, executors.get(exe));
				for (ClientConfig exe : clientConfigs.keySet())
					setupProxy(exe, clientConfigs.get(exe));
			}
		});
		return service;
	}

}
