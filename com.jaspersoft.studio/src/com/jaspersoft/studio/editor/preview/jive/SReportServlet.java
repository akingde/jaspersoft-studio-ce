package com.jaspersoft.studio.editor.preview.jive;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRResourcesUtil;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.repo.RepositoryUtil;
import net.sf.jasperreports.web.WebReportContext;
import net.sf.jasperreports.web.servlets.ReportServlet;

public class SReportServlet extends ReportServlet {
	public static String PRM_JSSContext = "jss.context";
	public static String PRM_JRPARAMETERS = "prm.INPUTPARAMETERS";
	public static String PRM_JASPERREPORT = "jasperreport";

	private static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;

	@Override
	public void runReport(HttpServletRequest request, WebReportContext webReportContext) throws JRException {
		Map<String, Object> prm = webReportContext.getParameterValues();

		Map<String, Object> cprm = Context.getContext(PRM_JSSContext);
		if (cprm != null) {

			Object das = cprm.get(PRM_JRPARAMETERS);
			if (das != null)
				prm.putAll((Map<String, Object>) das);

			JasperPrint jasperPrint = (JasperPrint) webReportContext
					.getParameterValue(WebReportContext.REPORT_CONTEXT_PARAMETER_JASPER_PRINT);
			String run = request.getParameter(REQUEST_PARAMETER_RUN_REPORT);
			if (jasperPrint == null || Boolean.valueOf(run)) {
				String reportUri = request.getParameter(REQUEST_PARAMETER_REPORT_URI);

				webReportContext.setParameterValue(JRParameter.REPORT_FILE_RESOLVER, getFileResolver());
				JRResourcesUtil.setThreadFileResolver(getFileResolver());

				Boolean isIgnorePagination = Boolean.valueOf(request.getParameter(REQUEST_PARAMETER_IGNORE_PAGINATION));
				if (isIgnorePagination != null) {
					webReportContext.setParameterValue(JRParameter.IS_IGNORE_PAGINATION, isIgnorePagination);
				}

				JasperReport jasperReport = (JasperReport) cprm.get(PRM_JASPERREPORT);
				if (jasperReport == null) {
					String jrxml = request.getParameter(REQUEST_PARAMETER_REPORT_JRXML);
					if (jrxml != null && jrxml.trim().length() > 0) {
						jrxml = jrxml.trim();
						jasperReport = JasperCompileManager.compileReport(JRXmlLoader.load(RepositoryUtil.getInputStream(jrxml)));
					} else if (reportUri != null && reportUri.trim().length() > 0) {
						reportUri = reportUri.trim();

						jasperReport = RepositoryUtil.getReport(reportUri);
					}

					if (jasperReport == null) {
						throw new JRException("Report not found at : " + reportUri);
					}
				}
				jasperPrint = JasperFillManager.fillReport(jasperReport, webReportContext.getParameterValues());

				webReportContext.setParameterValue(WebReportContext.REPORT_CONTEXT_PARAMETER_JASPER_PRINT, jasperPrint);
			}
		} else
			super.runReport(request, webReportContext);
	}
}
