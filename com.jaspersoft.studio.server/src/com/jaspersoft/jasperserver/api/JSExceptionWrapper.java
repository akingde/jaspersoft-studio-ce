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
