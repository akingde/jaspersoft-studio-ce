package com.jaspersoft.ireport.jasperserver.ws.http;

import java.lang.reflect.Field;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.axis.MessageContext;
import org.apache.axis.transport.http.CommonsHTTPSender;
import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NTCredentials;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.eclipse.core.net.proxy.IProxyData;
import org.eclipse.core.net.proxy.IProxyService;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

import com.jaspersoft.studio.server.Activator;

public class JSSCommonsHTTPSender  extends CommonsHTTPSender {
	private static final long serialVersionUID = 8881188152022966420L;

	public JSSCommonsHTTPSender() {
		super();
		try {
			Field field = CommonsHTTPSender.class
					.getDeclaredField("httpChunkStream");
			field.setAccessible(true);
			field.setBoolean(this, false);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected HostConfiguration getHostConfiguration(HttpClient c,
			MessageContext arg1, URL arg2) {
		HostConfiguration config = super.getHostConfiguration(c, arg1, arg2);
		IProxyService proxyService = getProxyService();
		IProxyData[] proxyDataForHost;
		try {
			proxyDataForHost = proxyService.select(arg2.toURI());
			for (IProxyData data : proxyDataForHost) {
				if (data.isRequiresAuthentication()) {
					String userId = data.getUserId();
					Credentials proxyCred = new UsernamePasswordCredentials(
							userId, data.getPassword());
					// if the username is in the form "user\domain"
					// then use NTCredentials instead.
					int domainIndex = userId.indexOf("\\");
					if (domainIndex > 0) {
						String domain = userId.substring(0, domainIndex);
						if (userId.length() > domainIndex + 1) {
							String user = userId.substring(domainIndex + 1);
							proxyCred = new NTCredentials(user,
									data.getPassword(), data.getHost(), domain);
						}
					}
					c.getState().setProxyCredentials(AuthScope.ANY, proxyCred);
				}
				config.setProxy(data.getHost(), data.getPort());
			}
			// Close the service and close the service tracker
			proxyService = null;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return config;
	}

	public static IProxyService getProxyService() {
		BundleContext bc = Activator.getDefault().getBundle()
				.getBundleContext();
		ServiceReference<?> serviceReference = bc
				.getServiceReference(IProxyService.class.getName());
		return (IProxyService) bc.getService(serviceReference);
	}
}