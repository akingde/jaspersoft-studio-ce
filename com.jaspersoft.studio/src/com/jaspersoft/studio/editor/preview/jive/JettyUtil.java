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
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.web.servlets.ImageServlet;
import net.sf.jasperreports.web.servlets.ReportServlet;
import net.sf.jasperreports.web.servlets.ResourceServlet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JasperDesignPreviewView.java 27 2009-11-11 12:40:27Z teodord $
 */
public final class JettyUtil {
	private static Server server;
	private static Map<IProject, List<Handler>> hmap = new HashMap<IProject, List<Handler>>();
	private static int port = 8888;

	public static void startJetty(IProject project) {
		if (server == null) {
			server = new Server(port);
			HandlerList handlerList = new HandlerList();
			server.setHandler(handlerList);
		}
		if (hmap.get(project) == null) {
			try {
				server.stop();

				List<Handler> handlers = createContext(project);
				hmap.put(project, handlers);
				for (Handler h : handlers)
					((HandlerList) server.getHandler()).addHandler(h);

				server.start();
			} catch (Exception e) {
				throw new JRRuntimeException(e);
			}
		}

	}

	public static String getURL(IFile file) {
		String ctxName = file.getProject().getName();

		return String.format("http://localhost:%d/%s/servlets/report?%s=%s", port, ctxName,
				ReportServlet.REQUEST_PARAMETER_REPORT_JRXML, file.getProjectRelativePath().toString());
	}

	private static List<Handler> createContext(IProject project) {
		List<Handler> handlers = new ArrayList<Handler>();
		// ResourceHandler resourceHandler = new ResourceHandler();
		// resourceHandler.setWelcomeFiles(new String[] { "index.html" });
		String waFolder = project.getLocation().toOSString() + "/";
		// resourceHandler.setResourceBase(waFolder);

		Context context = new Context(Context.SESSIONS);
		context.setContextPath("/" + project.getName());
		context.setClassLoader(ReportPreviewUtil.createProjectClassLoader(project));

		context.addServlet(new ServletHolder(DiagnosticServlet.class), "/servlets/diag");

		context.addServlet(new ServletHolder(SResourceServlet.class), "/images/*");
		context.addServlet(new ServletHolder(SResourceServlet.class), "/jquery/*");
		context.addServlet(new ServletHolder(SResourceServlet.class), "/jasperreports/*");

		ServletHolder reportServletHolder = new ServletHolder(SReportServlet.class);
		reportServletHolder.setInitParameter("repository.root", waFolder);
		context.addServlet(reportServletHolder, "/servlets/report");

		context.addServlet(new ServletHolder(ImageServlet.class), "/servlets/image");
		context.addServlet(new ServletHolder(ResourceServlet.class), ResourceServlet.DEFAULT_PATH);

		// handlers.add(resourceHandler);
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

	public static void restartJetty(IProject project) {
		stopJetty(project);
		startJetty(project);
	}

}
