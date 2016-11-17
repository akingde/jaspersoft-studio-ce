/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.fluent.Request;
import org.eclipse.core.runtime.IProgressMonitor;

public class ConnectionManager {
	private static Map<Request, IProgressMonitor> requests = new HashMap<Request, IProgressMonitor>();

	public synchronized static void register(IProgressMonitor monitor, Request req) {
		requests.put(req, monitor);
	}

	public synchronized static void unregister(Request req) {
		requests.remove(req);
	}

	private synchronized static void clean() {
		for (Request r : requests.keySet()) {
			IProgressMonitor m = requests.get(r);
			if (m.isCanceled()) {
				r.abort();
				requests.remove(r);
			}
		}
	}

	private static Thread mct = new Thread(new MonitorCancelThread());
	static {
		mct.start();
	}

	private static class MonitorCancelThread implements Runnable {

		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
					clean();
				} catch (InterruptedException e) {
					e.printStackTrace();
					break;
				}
			}
		}
	}

}
