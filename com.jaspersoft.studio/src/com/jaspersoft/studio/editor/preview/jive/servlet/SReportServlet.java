/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.editor.preview.jive.servlet;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.web.JRInteractiveException;
import net.sf.jasperreports.web.WebReportContext;
import net.sf.jasperreports.web.servlets.ReportServlet;

import com.jaspersoft.studio.editor.preview.jive.Context;

public class SReportServlet extends ReportServlet {
	public static String PRM_JSSContext = "jss.context";
	public static String PRM_JRPARAMETERS = "prm.in";
	public static String PRM_JASPERREPORT = "jasperreport";

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public void runReport(HttpServletRequest request, WebReportContext webReportContext) throws JRException,
			JRInteractiveException {
		Map<String, Object> prm = webReportContext.getParameterValues();

		String jsskey = request.getParameter(PRM_JSSContext);
		Map<String, Object> cprm = Context.getContext(jsskey);
		if (cprm != null) {
			Object das = cprm.get(PRM_JRPARAMETERS);
			if (das != null)
				prm.putAll((Map<String, Object>) das);

//			JasperPrint jasperPrint = (JasperPrint) webReportContext
//					.getParameterValue(WebReportContext.REPORT_CONTEXT_PARAMETER_JASPER_PRINT);
//			String run = request.getParameter(REQUEST_PARAMETER_RUN_REPORT);
//			if (jasperPrint == null || Boolean.valueOf(run)) {
//				String reportUri = request.getParameter(REQUEST_PARAMETER_REPORT_URI);
//
//				// webReportContext.setParameterValue(JRParameter.REPORT_FILE_RESOLVER, getFileResolver());
//				// JRResourcesUtil.setThreadFileResolver(getFileResolver());
//
//				Boolean isIgnorePagination = Boolean.valueOf(request.getParameter(REQUEST_PARAMETER_IGNORE_PAGINATION));
//				if (isIgnorePagination != null) {
//					webReportContext.setParameterValue(JRParameter.IS_IGNORE_PAGINATION, isIgnorePagination);
//				}
//
//				JasperReport jasperReport = (JasperReport) cprm.get(PRM_JASPERREPORT);
//				if (jasperReport == null) {
//					// String jrxml = request.getParameter(REQUEST_PARAMETER_REPORT_JRXML);
//					// if (jrxml != null && jrxml.trim().length() > 0) {
//					// jrxml = jrxml.trim();
//					// jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(RepositoryUtil.getInputStream(jrxml)));
//					// } else if (reportUri != null && reportUri.trim().length() > 0) {
//					// reportUri = reportUri.trim();
//					//
//					// jasperReport = RepositoryUtil.getReport(reportUri);
//					// }
//					//
//					// if (jasperReport == null) {
//					// throw new JRException("Report not found at : " + reportUri);
//					// }
//				}
//				jasperPrint = JasperFillManager.fillReport(jasperReport, webReportContext.getParameterValues());
//
//				webReportContext.setParameterValue(WebReportContext.REPORT_CONTEXT_PARAMETER_JASPER_PRINT, jasperPrint);
//			}
			Context.unsetContext(jsskey);
		} else
			super.runReport(request, webReportContext);
	}
}
