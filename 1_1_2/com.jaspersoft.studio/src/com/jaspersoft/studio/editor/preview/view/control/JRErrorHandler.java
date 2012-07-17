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
package com.jaspersoft.studio.editor.preview.view.control;

import net.sf.jasperreports.eclipse.builder.JasperReportErrorHandler;
import net.sf.jasperreports.eclipse.util.xml.SourceLocation;

import org.eclipse.jdt.core.compiler.CategorizedProblem;

import com.jaspersoft.studio.utils.Console;

public class JRErrorHandler implements JasperReportErrorHandler {

	private Console c;

	public JRErrorHandler(Console c) {
		this.c = c;
	}

	public void addMarker(Throwable e) {
		c.addError(e);
		// this.jasperReportsBuilder.addMarker(file, e.getMessage(), 1, IMarker.SEVERITY_ERROR);
	}

	public void addMarker(CategorizedProblem problem, SourceLocation location) {
		c.addProblem(problem, location);
	}

	public void addMarker(String message, SourceLocation location) {
		c.addProblem(message, location);
	}
}