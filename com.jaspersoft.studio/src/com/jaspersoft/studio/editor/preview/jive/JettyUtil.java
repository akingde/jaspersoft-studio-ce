/*
 * Copyright (C) 2005 - 2006 JasperSoft Corporation. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from JasperSoft, the following license terms apply:
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the GNU General Public
 * License version 2 as published by the Free Software Foundation.
 * 
 * This program is distributed WITHOUT ANY WARRANTY; and without the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with this program; if not, see
 * http://www.gnu.org/licenses/gpl.txt or write to:
 * 
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA USA 02111-1307
 */
package com.jaspersoft.studio.editor.preview.jive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.ReportPreviewUtil;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRPropertiesUtil;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.web.servlets.ImageServlet;
import net.sf.jasperreports.web.servlets.ResourceServlet;
import net.sf.jasperreports.web.servlets.ViewerServlet;
import net.sf.jasperreports.web.util.WebUtil;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandlerCollection;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.jaspersoft.studio.editor.preview.jive.servlet.SReportServlet;
import com.jaspersoft.studio.editor.preview.jive.servlet.SResourceServlet;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperDesignPreviewView.java 27 2009-11-11 12:40:27Z teodord $
 */
public final class JettyUtil {
	private static Server server;
	private static Map<IProject, List<Handler>> hmap = new HashMap<IProject, List<Handler>>();
	private static int port = 8888;
	private static ContextHandlerCollection contextHandlerCollection;

	public static void startJetty(IProject project, JasperReportsConfiguration jContext) {
		try {
			if (server == null) {
				server = new Server(port);
				HandlerCollection hc = new HandlerCollection();
				contextHandlerCollection = new ContextHandlerCollection();
				hc.setHandlers(new Handler[] { contextHandlerCollection });
				server.setHandler(hc);

				server.start();

			}
			if (hmap.get(project) == null) {

				// server.stop();

				List<Handler> handlers = createContext(project, jContext);
				hmap.put(project, handlers);
				for (Handler h : handlers) {
					contextHandlerCollection.addHandler(h);
					h.start();
				}
			}
		} catch (Exception e) {
			throw new JRRuntimeException(e);
		}
	}

	public static String getURL(IFile file, String uuid, JasperReportsConfiguration jContext) {
		String ctxName = file.getProject().getName();

		JRPropertiesUtil propUtil = JRPropertiesUtil.getInstance(jContext);
		String repuri = propUtil.getProperty(WebUtil.PROPERTY_REQUEST_PARAMETER_REPORT_URI);

		return String.format("http://localhost:%d/%s/servlets/viewer?%s=%s&%s=%s", port, ctxName, repuri,
		// file.getLocation().toString(),
				file.getProjectRelativePath().toString(), SReportServlet.PRM_JSSContext, uuid);
	}

	private static List<Handler> createContext(IProject project, final JasperReportsConfiguration jContext) {
		List<Handler> handlers = new ArrayList<Handler>();
		// final String waFolder = project.getLocation().toOSString() + "/";

		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/" + project.getName());
		context.setClassLoader(ReportPreviewUtil.createProjectClassLoader(project));

		context.addServlet(new ServletHolder(DiagnosticServlet.class), "/servlets/diag");

		ServletHolder rs = new ServletHolder(new ResourceServlet() {
			private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public JasperReportsContext getJasperReportsContext() {
				return jContext;
			}
		});
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/servlets/resource");

		context.addServlet(new ServletHolder(new SResourceServlet()), "/jquery/*");
		context.addServlet(new ServletHolder(new SResourceServlet()), "/javascript/*");

		rs = new ServletHolder(new ImageServlet() {
			private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public JasperReportsContext getJasperReportsContext() {
				return jContext;
			}
		});
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/servlets/image");
		rs = new ServletHolder(new ViewerServlet() {
			private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public JasperReportsContext getJasperReportsContext() {
				return jContext;
			}
		});
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/servlets/myviewer");

		rs = new ServletHolder(new ViewerServlet() {
			private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public JasperReportsContext getJasperReportsContext() {
				return jContext;
			}
		});
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/servlets/viewer");

		ServletHolder reportServletHolder = new ServletHolder(new SReportServlet() {
			private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

			@Override
			public JasperReportsContext getJasperReportsContext() {
				return jContext;
			}
		});
		context.addServlet(reportServletHolder, "/servlets/report");

		handlers.add(context);
		return handlers;
	}

	public static void stopJetty(IProject project) {
		if (server != null) {
			try {
				server.stop();
			} catch (Exception e) {
				throw new JRRuntimeException(e);
			}

			server = null;
		}
	}

	public static void restartJetty(IProject project, JasperReportsConfiguration jContext) {
		stopJetty(project);
		startJetty(project, jContext);
	}

}
