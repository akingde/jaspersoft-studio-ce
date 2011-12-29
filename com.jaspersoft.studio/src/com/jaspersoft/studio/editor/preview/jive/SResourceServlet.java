/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.jive;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: ResourceServlet.java 4595 2011-09-08 15:55:10Z teodord $
 */
public class SResourceServlet extends HttpServlet {
	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String resource = "webapp" + request.getServletPath() + request.getPathInfo();
		try {
			byte[] bytes = JRLoader.loadBytesFromResource(resource);

			response.setContentType(getServletContext().getMimeType(resource));

			// Set to expire far in the past.
			response.setHeader("Expires", "Sat, 6 May 1995 12:00:00 GMT");
			// Set standard HTTP/1.1 no-cache headers.
			response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
			// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
			response.addHeader("Cache-Control", "post-check=0, pre-check=0");
			// Set standard HTTP/1.0 no-cache header.
			response.setHeader("Pragma", "no-cache");

			response.getOutputStream().write(bytes);
		} catch (JRException e) {
			e.printStackTrace();
		}
	}
}
