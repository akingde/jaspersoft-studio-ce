/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.protocol.restv2;

import java.io.ByteArrayInputStream;

import net.sf.jasperreports.util.CastorUtil;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.eclipse.core.runtime.IProgressMonitor;

import com.jaspersoft.studio.server.model.server.ServerProfile;
import com.jaspersoft.studio.server.preferences.CASListFieldEditor;
import com.jaspersoft.studio.server.preferences.CASPreferencePage;
import com.jaspersoft.studio.server.preferences.SSOServer;
import com.jaspersoft.studio.server.utils.HttpUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class CASUtil {
	public static String getToken(ServerProfile sp, IProgressMonitor monitor) throws Exception {
		String v = null;
		v = JasperReportsConfiguration.getDefaultInstance().getPrefStore().getString(CASPreferencePage.CAS);
		for (String line : v.split("\n")) {
			if (line.isEmpty())
				continue;
			SSOServer srv = (SSOServer) CastorUtil.read(new ByteArrayInputStream(Base64.decodeBase64(line)), CASListFieldEditor.mapping);
			if (srv.getUuid().equals(sp.getSsoUuid())) {
				return doGetTocken(sp, srv, monitor);
			}
		}
		throw new Exception("Could not find SSO Server in the list.");
	}

	public static String doGetTocken(ServerProfile sp, SSOServer srv, IProgressMonitor monitor) throws Exception {
		Executor exec = Executor.newInstance();

		String url = srv.getUrl();
		if (!url.endsWith("/"))
			url += "/";
		URIBuilder ub = new URIBuilder(srv.getUrl() + "cas/v1/tickets");
		ub.addParameter("username", srv.getUser());
		ub.addParameter("password", srv.getPassword());

		Request req = HttpUtils.post(ub.build().toASCIIString(), sp);

		String tgtID = "TGT ID";

		ub = new URIBuilder(srv.getUrl() + "cas/v1/tickets/" + tgtID);
		ub.addParameter("username", srv.getUser());
		ub.addParameter("password", srv.getPassword());

		req = HttpUtils.post(ub.build().toASCIIString(), sp);

		return null;
	}
}
