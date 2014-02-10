package com.jaspersoft.ireport.jasperserver.ws.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpMethod;
import org.eclipse.core.runtime.IProgressMonitor;

public class HCManager {
	private Map<IProgressMonitor, HttpMethod> cmap = new HashMap<IProgressMonitor, HttpMethod>();

	public void cancel(HttpMethod method) {
		HttpClient client = null;
		HttpConnectionManager cmanager = client.getHttpConnectionManager();

		for (IProgressMonitor m : cmap.keySet()) {
			if (m.isCanceled()) {
				method.abort();
				cmap.remove(m);
			}
		}

	}
}
