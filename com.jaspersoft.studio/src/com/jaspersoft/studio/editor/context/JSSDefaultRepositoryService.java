/*******************************************************************************
 * Copyright (C) 2010 - 2018. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.context;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.eclipse.util.HttpUtils;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRResourcesUtil;
import net.sf.jasperreports.repo.DefaultRepositoryService;
import net.sf.jasperreports.repo.SimpleRepositoryContext;

public class JSSDefaultRepositoryService extends DefaultRepositoryService {
	private JasperReportsConfiguration jConf;

	public JSSDefaultRepositoryService(JasperReportsConfiguration jConf) {
		super(jConf);
		this.jConf = jConf;
	}

	@Override
	public InputStream getInputStream(String uri) {
		if (Misc.isNullOrEmpty(uri) || uri.startsWith("repo:"))
			return null;
		try {
			URL url = JRResourcesUtil.createURL(uri, urlHandlerFactory);
			if (url != null) {
				if (url.getProtocol().equalsIgnoreCase("http") || url.getProtocol().equalsIgnoreCase("https")) {
					try {
						URI uuri = url.toURI();
						Executor exec = Executor.newInstance();
						HttpUtils.setupProxy(exec, uuri);

						Request req = Request.Get("http://somehost/");
						HttpUtils.setupProxy(exec, uuri, req);
						return exec.execute(req).returnContent().asStream();
					} catch (URISyntaxException e) {
						e.printStackTrace();
					} catch (IOException e) {
						throw new JRRuntimeException(JRLoader.EXCEPTION_MESSAGE_KEY_INPUT_STREAM_FROM_URL_OPEN_ERROR,
								new Object[] { url }, e);
					}
				}
				return JRLoader.getInputStream(url);
			}
			File file = JRResourcesUtil.resolveFile(SimpleRepositoryContext.of(jConf), uri);
			if (file != null)
				return JRLoader.getInputStream(file);
			url = JRResourcesUtil.findClassLoaderResource(uri, classLoader);
			if (url != null)
				return JRLoader.getInputStream(url);
		} catch (JRException e) {
			throw new JRRuntimeException(e);
		}
		return null;
	}
}