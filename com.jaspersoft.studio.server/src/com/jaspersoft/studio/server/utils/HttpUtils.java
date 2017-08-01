/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.utils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.IDN;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

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
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.JerseyInvocation;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.protocol.restv2.RestV2Connection;

import net.sf.jasperreports.eclipse.util.Misc;

public class HttpUtils {
	public static URI toSafeUri(final URL url) throws URISyntaxException {
		String u = "";
		if (!Misc.isNullOrEmpty(url.getProtocol()))
			u += url.getProtocol() + "://";
		if (!Misc.isNullOrEmpty(url.getUserInfo()))
			u += "@" + url.getUserInfo();
		u += IDN.toASCII(url.getHost());
		if (url.getPort() > 0)
			u += ":" + url.getPort();
		if (!Misc.isNullOrEmpty(url.getPath()))
			u += url.getPath();
		if (!Misc.isNullOrEmpty(url.getQuery()))
			u += url.getQuery();
		if (!Misc.isNullOrEmpty(url.getRef()))
			u += url.getRef();
		URI uri = new URI(u);
		setupUriHost(uri, IDN.toASCII(url.getHost()));
		return uri;
	}

	private static void setupUriHost(URI uri, String host) {
		if (uri.getHost() == null) {
			try {
				Field f = URI.class.getDeclaredField("host");
				f.setAccessible(true);
				f.set(uri, host);
			} catch (NoSuchFieldException | SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	public static Builder getRequest(WebTarget target, MediaType arg) {
		return getRequest(target, target.request(arg));
	}

	public static Builder getRequest(WebTarget target, String arg) {
		return getRequest(target, target.request(arg));
	}

	public static Builder getRequest(WebTarget target) {
		return getRequest(target, target.request());
	}

	public static Builder getRequest(WebTarget target, Builder req) {
		try {
			if (!target.getUri().getHost().contains("_"))
				return req;
			Field f = JerseyInvocation.Builder.class.getDeclaredField("requestContext");
			f.setAccessible(true);
			ClientRequest cr = (ClientRequest) f.get(req);
			if (cr != null) {
				f = ClientRequest.class.getDeclaredField("requestUri");
				f.setAccessible(true);
				URI uri = (URI) f.get(cr);
				if (uri.getHost() == null)
					setupUriHost(uri, target.getUri().getHost());
			}

		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return req;
	}

	static {
		patchUri("lowMask", "L_DASH");
		patchUri("highMask", "H_DASH");
	}

	private static void patchUri(String mname, String fname) {
		try {
			Method mask = URI.class.getDeclaredMethod(mname, String.class);
			mask.setAccessible(true);
			long vmask = (long) mask.invoke(null, "-_");

			Field dash = URI.class.getDeclaredField(fname);

			Field modifiers = Field.class.getDeclaredField("modifiers");
			modifiers.setAccessible(true);
			modifiers.setInt(dash, dash.getModifiers() & ~Modifier.FINAL);

			dash.setAccessible(true);
			dash.setLong(null, vmask);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	public static void setupProxy(ClientConfig clientConfig, URI uri) {
		CredentialsProvider cp = (CredentialsProvider) clientConfig
				.getProperty(ApacheClientProperties.CREDENTIALS_PROVIDER);
		for (IProxyData d : net.sf.jasperreports.eclipse.util.HttpUtils.proxyService.select(uri)) {
			Credentials c = net.sf.jasperreports.eclipse.util.HttpUtils.getCredentials(d);
			if (c != null && cp != null)
				cp.setCredentials(new AuthScope(new HttpHost(d.getHost(), d.getPort())), c);
			clientConfig.property(ClientProperties.PROXY_URI,
					net.sf.jasperreports.eclipse.util.HttpUtils.getProxyProtocol(d) + "://" + d.getHost() + ":"
							+ d.getPort());
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
