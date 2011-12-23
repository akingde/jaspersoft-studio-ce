package com.jaspersoft.studio.editor.preview.jive;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.HandlerList;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class JettyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Server server = new Server(8080);
			HandlerList handlerList = new HandlerList();
			server.setHandler(handlerList);

			ResourceHandler resourceHandler = new ResourceHandler();
			resourceHandler.setWelcomeFiles(new String[] { "index.html" });
			resourceHandler.setResourceBase("/home/slavic/tmp/jetty");

			Context context = new Context(Context.SESSIONS);
			context.setContextPath("/test");

			context.addServlet(new ServletHolder(new DiagnosticServlet()), "/*");

			handlerList.addHandler(resourceHandler);
			handlerList.addHandler(context);

			server.start();
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
