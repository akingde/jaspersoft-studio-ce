package com.jaspersoft.studio.editor.preview.jive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.ReportPreviewUtil;
import net.sf.jasperreports.engine.JRRuntimeException;
import net.sf.jasperreports.web.servlets.ImageServlet;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import com.jaspersoft.studio.editor.preview.jive.servlet.SReportServlet;
import com.jaspersoft.studio.editor.preview.jive.servlet.SResourceServlet;

/**
 * Utility class to interact with Jetty 6.x (org.mortbay.jetty.xxx plug-ins) 
 * server when using Jive.
 */
public class MortbayJettyUtil implements JiveJettyUtilitiesProvider {
	
	private static MortbayJettyUtil instance;
	private static Server server;
	private static Map<IProject, List<Handler>> hmap = new HashMap<IProject, List<Handler>>();
	private static int port = 8888;
	private static ContextHandlerCollection contextHandlerCollection;

	private MortbayJettyUtil(){
	}
	
	public static synchronized MortbayJettyUtil getInstance(){
		if(instance==null){
			instance=new MortbayJettyUtil();
		}
		return instance;
	}
	
	@Override
	public void startJetty(IProject project) {
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

				List<Handler> handlers = createContext(project);
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

	@Override
	public void stopJetty(IProject project) {
		if (server != null) {
			try {
				server.stop();
			} catch (Exception e) {
				throw new JRRuntimeException(e);
			}

			server = null;
		}
	}

	@Override
	public void restartJetty(IProject project) {
		stopJetty(project);
		startJetty(project);
	}

	@Override
	public String getURL(IFile file, String uuid) {
//		String ctxName = file.getProject().getName();

//		return String.format("http://localhost:%d/%s/servlets/report?%s=%s&%s=%s", port, ctxName,
//				ReportServlet.REQUEST_PARAMETER_REPORT_URI, file.getProjectRelativePath().toString(),
//				SReportServlet.PRM_JSSContext, uuid);
		return null;
	}
	
	private static List<Handler> createContext(IProject project) {
		List<Handler> handlers = new ArrayList<Handler>();
		String waFolder = project.getLocation().toOSString() + "/";

		Context context = new Context(Context.SESSIONS);
		context.setContextPath("/" + project.getName());
		context.setClassLoader(ReportPreviewUtil.createProjectClassLoader(project));

		context.addServlet(new ServletHolder(DiagnosticServlet.class), "/servlets/diag");

		ServletHolder rs = new ServletHolder(SResourceServlet.class);
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/images/*");
		rs = new ServletHolder(SResourceServlet.class);
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/jquery/*");
		rs = new ServletHolder(SResourceServlet.class);
		rs.setInitParameter("cacheControl", "max-age=0,public");
		context.addServlet(rs, "/jasperreports/*");

		ServletHolder reportServletHolder = new ServletHolder(SReportServlet.class);
		reportServletHolder.setInitParameter("repository.root", waFolder);
		context.addServlet(reportServletHolder, "/servlets/report");

		context.addServlet(new ServletHolder(ImageServlet.class), "/servlets/image");
//		context.addServlet(new ServletHolder(ResourceServlet.class), ResourceServlet.DEFAULT_PATH);

		handlers.add(context);
		return handlers;
	}

}
