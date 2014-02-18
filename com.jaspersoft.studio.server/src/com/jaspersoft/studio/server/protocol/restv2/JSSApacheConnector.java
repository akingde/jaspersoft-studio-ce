package com.jaspersoft.studio.server.protocol.restv2;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.core.Configuration;
import javax.ws.rs.core.Response;

import org.eclipse.core.runtime.IProgressMonitor;
import org.glassfish.jersey.apache.connector.ApacheConnector;
import org.glassfish.jersey.client.ClientRequest;
import org.glassfish.jersey.client.ClientResponse;

public class JSSApacheConnector extends ApacheConnector {
	private ClientRequest lastRequest;
	private IProgressMonitor lastMonitor;

	public JSSApacheConnector(Configuration arg0) {
		super(arg0);
	}

	@Override
	public ClientResponse apply(ClientRequest req) throws ProcessingException {
		System.out.println("call: " + req.getUri());
		lastRequest = req;
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

	private Response doWait(Future<Response> rf, IProgressMonitor monitor) throws Exception {
		try {
			while (!rf.isDone() && !rf.isCancelled()) {
				if (monitor.isCanceled())
					rf.cancel(true);
				Thread.sleep(5);
			}
			return rf.get();
		} catch (InterruptedException e) {
			throw e;
		} catch (ExecutionException e) {
			throw e;
		}
	}

	public Response get(Builder builder, IProgressMonitor monitor) throws Exception {
		lastMonitor = monitor;
		return doWait(builder.async().get(), monitor);
	}

	public Response delete(Builder builder, IProgressMonitor monitor) throws Exception {
		lastMonitor = monitor;
		return doWait(builder.async().delete(), monitor);
	}

	public Response post(Builder builder, Entity<?> entity, IProgressMonitor monitor) throws Exception {
		lastMonitor = monitor;
		return doWait(builder.async().post(entity), monitor);
	}

	public Response put(Builder builder, Entity<?> entity, IProgressMonitor monitor) throws Exception {
		lastMonitor = monitor;
		return doWait(builder.async().put(entity), monitor);
	}
}
