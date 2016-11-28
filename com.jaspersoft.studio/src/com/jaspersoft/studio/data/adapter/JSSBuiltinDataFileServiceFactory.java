/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.adapter;

import java.util.Map;

import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
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
import net.sf.jasperreports.engine.ParameterContributorContext;

public class JSSBuiltinDataFileServiceFactory extends BuiltinDataFileServiceFactory {
	private static final BuiltinDataFileServiceFactory INSTANCE = new JSSBuiltinDataFileServiceFactory();

	public static BuiltinDataFileServiceFactory instance() {
		return INSTANCE;
	}

	protected JSSBuiltinDataFileServiceFactory() {
	}

	@Override
	public DataFileService createService(ParameterContributorContext context, DataFile dataFile) {
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

//					HttpClientContext clientContext = HttpClientContext.create();

					setAuthentication(parameters, clientBuilder);

					CloseableHttpClient client = clientBuilder.build();
					return client;
				}
			};
		}
		return null;
	}
}
