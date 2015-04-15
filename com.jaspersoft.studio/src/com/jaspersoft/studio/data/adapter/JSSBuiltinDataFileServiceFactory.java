/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.data.adapter;

import java.util.Map;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;

import net.sf.jasperreports.data.BuiltinDataFileServiceFactory;
import net.sf.jasperreports.data.DataFile;
import net.sf.jasperreports.data.DataFileService;
import net.sf.jasperreports.data.RepositoryDataLocation;
import net.sf.jasperreports.data.RepositoryDataLocationService;
import net.sf.jasperreports.data.http.HttpDataLocation;
import net.sf.jasperreports.data.http.HttpDataService;
import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.engine.JasperReportsContext;

public class JSSBuiltinDataFileServiceFactory extends BuiltinDataFileServiceFactory {
	private static final BuiltinDataFileServiceFactory INSTANCE = new JSSBuiltinDataFileServiceFactory();

	public static BuiltinDataFileServiceFactory instance() {
		return INSTANCE;
	}

	protected JSSBuiltinDataFileServiceFactory() {
	}

	@Override
	public DataFileService createService(JasperReportsContext context, DataFile dataFile) {
		if (dataFile instanceof RepositoryDataLocation) {
			return new RepositoryDataLocationService(context, (RepositoryDataLocation) dataFile);
		}
		if (dataFile instanceof HttpDataLocation) {
			return new HttpDataService(context, (HttpDataLocation) dataFile) {
				@Override
				protected CloseableHttpClient createHttpClient(Map<String, Object> parameters) {
					HttpClientBuilder clientBuilder = HttpClients.custom();
					HttpUtils.setupProxy(clientBuilder);
					// single connection
					BasicHttpClientConnectionManager connManager = new BasicHttpClientConnectionManager();
					clientBuilder.setConnectionManager(connManager);
					
					// ignore cookies for now
					RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.IGNORE_COOKIES).build();
					clientBuilder.setDefaultRequestConfig(requestConfig);
					
					HttpClientContext clientContext = HttpClientContext.create();
					
					setAuthentication(parameters, clientContext);
					
					CloseableHttpClient client = clientBuilder.build();
					return client;
				}
			};
		}
		return null;
	}
}
