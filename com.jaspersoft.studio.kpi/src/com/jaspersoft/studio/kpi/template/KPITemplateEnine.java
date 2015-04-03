package com.jaspersoft.studio.kpi.template;

import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JasperDesign;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateBundle;
import com.jaspersoft.templates.TemplateEngine;
import com.jaspersoft.templates.TemplateEngineException;

public class KPITemplateEnine implements TemplateEngine {

	@Override
	public ReportBundle generateReportBundle(TemplateBundle template,Map<String, Object> settings, JasperReportsContext jContext) throws TemplateEngineException {
		JasperDesign jdCopy = null;
		try {
			// N.B: We need a fresh new copy of the jasper design!
			jdCopy = ModelUtils.copyJasperDesign(jContext, template.getJasperDesign());
		} catch (JRException e) {
			UIUtils.showError(e);
			return null;
		}

		ReportBundle reportBundle = new ReportBundle(template);

		reportBundle.setJasperDesign(jdCopy);

		return reportBundle;
	}

	@Override
	public void setReportDataAdapter(ReportBundle bundle, DataAdapterDescriptor dataadapter, JRPropertiesMap properties) {
	}

}
