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