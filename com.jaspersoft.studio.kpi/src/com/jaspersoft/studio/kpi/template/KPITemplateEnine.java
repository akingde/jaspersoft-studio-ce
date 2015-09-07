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

/**
 * The engine used to transform a kpi template into a 
 * report. At the moment the template is simply copied, there
 * are no transformation on it
 * 
 * @author Orlaldin Marco
 *
 */
public class KPITemplateEnine implements TemplateEngine {

	/**
	 * Copy the template and return it
	 */
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
