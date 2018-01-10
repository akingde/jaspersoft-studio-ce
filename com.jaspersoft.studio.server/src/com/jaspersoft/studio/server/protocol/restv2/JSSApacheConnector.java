/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;


public class JSSApacheConnector {//extends ApacheConnector {
//	private ClientRequest lastRequest;
//
//	public JSSApacheConnector(Configuration arg0) {
//		super(arg0);
//	}
//
//	@Override
//	public ClientResponse apply(ClientRequest req) throws ProcessingException {
//		System.out.println("call: " + req.getUri());
//		req.setUri(UrlUtil.fixUri(req.getUri()));
//		lastRequest = req;
//		return super.apply(req);
//	}
//
//	public void closeLastRequest() {
//		if (lastRequest != null)
//			lastRequest.close();
//	}
//
//	private Response doWait(Future<Response> rf, IProgressMonitor monitor) throws Exception {
//		try {
//			if (monitor != null)
//				while (!rf.isDone() && !rf.isCancelled()) {
//					if (monitor.isCanceled())
//						rf.cancel(true);
//					Thread.sleep(5);
//				}
//			return rf.get();
//		} catch (InterruptedException e) {
//			throw e;
//		} catch (ExecutionException e) {
//			throw e;
//		}
//	}
//
//	public synchronized Response get(Builder builder, IProgressMonitor monitor) throws Exception {
//		return doWait(builder.async().get(), monitor);
//	}
//
//	public Response delete(Builder builder, IProgressMonitor monitor) throws Exception {
//		return doWait(builder.async().delete(), monitor);
//	}
//
//	public Response post(Builder builder, Entity<?> entity, IProgressMonitor monitor) throws Exception {
//		return doWait(builder.async().post(entity), monitor);
//	}
//
//	public Response put(Builder builder, Entity<?> entity, IProgressMonitor monitor) throws Exception {
//		builder.header("Content-Lenght", 0);
//		return doWait(builder.async().put(entity), monitor);
//	}
}
