/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
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
