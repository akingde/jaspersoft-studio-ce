/*
 * Copyright (C) 2005 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com.
 *
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 *
 * This program is free software: you can redistribute it and/or  modify
 * it under the terms of the GNU Affero General Public License  as
 * published by the Free Software Foundation, either version 3 of  the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero  General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public  License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.jasperserver.api;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author Lucian Chirita (lucianc@users.sourceforge.net)
 * @version $Id: JSExceptionWrapper.java 22726 2012-03-22 08:47:56Z lchirita $
 */
public class JSExceptionWrapper extends JSException {

	private final String stackTrace;

	private final Exception originalException;

	public JSExceptionWrapper(String message, Exception e) {
		super(message, e);
		originalException = e;
		stackTrace = readStackTrace(e);
	}

	public JSExceptionWrapper(Exception e) {
		this(e.getMessage(), e);
	}

	private String readStackTrace(Exception e) {
		StringWriter sw = new StringWriter();
		e.printStackTrace(new PrintWriter(sw));
		return sw.toString();
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(java.io.PrintStream s) {
		synchronized (s) {
			s.print(getClass().getName() + ": ");
			s.print(stackTrace);
		}
	}

	public void printStackTrace(java.io.PrintWriter s) {
		synchronized (s) {
			s.print(getClass().getName() + ": ");
			s.print(stackTrace);
		}
	}

	public void rethrow() throws Exception {
		throw originalException;
	}

	public Exception getOriginalException() {
		return originalException;
	}
}
