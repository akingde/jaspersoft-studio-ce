package com.jaspersoft.studio.server.protocol.restv2;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

import org.eclipse.core.runtime.IProgressMonitor;
import org.glassfish.jersey.apache.connector.ApacheConnector;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.ClientResponse;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class JSSApacheConnector extends ApacheConnector {
	private ClientRequest lastRequest;
	private IProgressMonitor lastMonitor;
	private static BiMap<ClientRequest, IProgressMonitor> requests = HashBiMap.create();

	public JSSApacheConnector(Configuration arg0) {
		super(arg0);
	}

	@Override
	public ClientResponse apply(ClientRequest req) throws ProcessingException {
		System.out.println("call: " + req.getUri());
		lastRequest = req;
		requests.put(req, lastMonitor);
		return super.apply(req);
	}

	public void closeLastRequest() {
		// try {
		// ClientConnectionManager cm = getHttpClient().getConnectionManager();
		// Field m = cm.getClass().getDeclaredField("poolEntry");
		// m.setAccessible(true);
		// PoolEntry poolEntry = (PoolEntry) m.get(cm);
		// if (poolEntry != null && !poolEntry.isClosed()) {
		// // poolEntry.close();
		// DefaultClientConnection dcc = (DefaultClientConnection)
		// poolEntry.getConnection();
		// dcc.close();
		// dcc.getSocket().close();
		// }
		// } catch (SecurityException e) {
		// e.printStackTrace();
		// } catch (IllegalArgumentException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (NoSuchFieldException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		if (lastRequest != null)
			lastRequest.close();
	}

	public Response get(Builder builder, IProgressMonitor monitor) {
		lastMonitor = monitor;
		Response r = null;
		try {
			r = builder.get();
		} finally {
			requests.inverse().remove(monitor);
		}
		return r;
	}

	public Response delete(Builder builder, IProgressMonitor monitor) {
		lastMonitor = monitor;
		Response r = null;
		try {
			r = builder.delete();
		} finally {
			requests.inverse().remove(monitor);
		}
		return r;
	}

	public Response post(Builder builder, Entity<?> entity, IProgressMonitor monitor) {
		lastMonitor = monitor;
		Response r = null;
		try {
			r = builder.post(entity);
		} finally {
			requests.inverse().remove(monitor);
		}
		return r;
	}

	public Response put(Builder builder, Entity<?> entity, IProgressMonitor monitor) {
		lastMonitor = monitor;
		Response r = null;
		try {
			r = builder.put(entity);
		} finally {
			requests.inverse().remove(monitor);
		}
		return r;
	}

	private synchronized static void clean() {
		for (ClientRequest r : requests.keySet())
			if (requests.get(r).isCanceled()) {
				r.close();
				requests.remove(r);
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
