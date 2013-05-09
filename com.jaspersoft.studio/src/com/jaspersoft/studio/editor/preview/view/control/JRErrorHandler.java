/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.editor.preview.view.control;

import net.sf.jasperreports.eclipse.builder.JasperReportErrorHandler;
import net.sf.jasperreports.eclipse.util.xml.SourceLocation;

import org.eclipse.jdt.core.compiler.CategorizedProblem;
import org.eclipse.jdt.core.compiler.IProblem;

import com.jaspersoft.studio.utils.Console;

public class JRErrorHandler implements JasperReportErrorHandler {
	private boolean hasErrors = false;
	private Console c;

	public JRErrorHandler(Console c) {
		this.c = c;
	}

	public void reset() {
		hasErrors = false;
	}

	public void addMarker(Throwable e) {
		hasErrors = true;
		c.addError(e);
	}

	public void addMarker(IProblem problem, SourceLocation location) {
		hasErrors = true;
		c.addProblem((CategorizedProblem)problem, location);
	}

	public void addMarker(String message, SourceLocation location) {
		hasErrors = true;
		c.addProblem(message, location);
	}

	public boolean isHasErrors() {
		return hasErrors;
	}
}
