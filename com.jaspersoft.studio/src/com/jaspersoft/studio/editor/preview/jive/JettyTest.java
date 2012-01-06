package com.jaspersoft.studio.editor.preview.jive;

import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ContextHandlerCollection;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class JettyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {

			Server server = new Server(8080);

			HandlerCollection hc = new HandlerCollection();
			ContextHandlerCollection contextHandlerCollection = new ContextHandlerCollection();
			hc.setHandlers(new Handler[] { contextHandlerCollection });
			server.setHandler(hc);

			// HandlerList handlerList = new HandlerList();
			// server.setHandler(handlerList);

			Context context = new Context(Context.SESSIONS);
			context.setContextPath("/test");

			context.addServlet(new ServletHolder(new DiagnosticServlet()), "/*");
			contextHandlerCollection.addHandler(context);
			// handlerList.addHandler(context);

			server.start();
			String[] st = new String[100000];
			for (int i = 0; i < st.length; i++) {
				st[i] = "myhost" + i + ".com";
			}

			context.setVirtualHosts(st);

			// context = new Context(Context.SESSIONS);
			// context.setContextPath("/mytest");
			//
			// context.addServlet(new ServletHolder(new DiagnosticServlet()), "/*");
			// contextHandlerCollection.addHandler(context);
			//
			// context.start();

			// handlerList.addHandler(context);

			// handlerList.addHandler(context);

			server.start();
			
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
