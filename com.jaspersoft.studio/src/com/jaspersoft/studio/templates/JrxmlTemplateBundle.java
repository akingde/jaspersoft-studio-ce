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
package com.jaspersoft.studio.templates;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.property.dataset.wizard.WizardDataSourceDynamicPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsDynamicPage;
import com.jaspersoft.studio.property.dataset.wizard.WizardFieldsGroupByDynamicPage;
import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.studio.wizards.WizardUtils;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateBundle;
import com.jaspersoft.templates.TemplateEngine;
import com.jaspersoft.templates.WizardTemplateBundle;


public class JrxmlTemplateBundle extends WizardTemplateBundle {

	private WizardDataSourceDynamicPage step1 = null;
	private WizardFieldsDynamicPage step2 = null;
	private WizardFieldsGroupByDynamicPage step3 = null;
	
	public JrxmlTemplateBundle(URL url, boolean isExternal, JasperReportsContext jrContext) throws Exception {
		super(url, isExternal, jrContext);
	}
	
	public JrxmlTemplateBundle(URL url, JasperReportsContext jrContext) throws Exception {
		super(url, jrContext);
	}
	
	@Override
	public IFile doFinish(ReportNewWizard mainWizard, IProgressMonitor monitor) throws CoreException {
		
		
		IFile reportFile = null;
		
		Map<String, Object> settings = mainWizard.getSettings();
		
		String containerName = (String)settings.get(ReportNewWizard.CONTAINER_NAME_KEY);
		String fileName = (String)settings.get(ReportNewWizard.FILE_NAME_KEY);
		
		monitor.beginTask(Messages.ReportNewWizard_3 + fileName, 2);

		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		if (!resource.exists() || !(resource instanceof IContainer)) {
			throwCoreException(String.format(Messages.ReportNewWizard_4, containerName));
		}
		
		JasperReportsConfiguration jConfig = mainWizard.getConfig();
		
		Map<String, Object> templateSettings = new HashMap<String, Object>();

		TemplateBundle templateBundle = mainWizard.getTemplateChooserStep().getTemplateBundle();

		JRDesignDataset dataset = WizardUtils.createDataset(jConfig, true, settings);

		templateSettings.put(DefaultTemplateEngine.DATASET, dataset);

		if (settings.containsKey(WizardDataSourceDynamicPage.DATASET_FIELDS)) {
			templateSettings.put(DefaultTemplateEngine.FIELDS, settings.get(WizardDataSourceDynamicPage.DATASET_FIELDS));
		}

		if (settings.containsKey(WizardDataSourceDynamicPage.GROUP_FIELDS)) {
			templateSettings.put(DefaultTemplateEngine.GROUP_FIELDS, settings.get(WizardDataSourceDynamicPage.GROUP_FIELDS));
		}

		if (settings.containsKey(WizardDataSourceDynamicPage.ORDER_GROUP)) {
			templateSettings.put(DefaultTemplateEngine.ORDER_GROUP, settings.get(WizardDataSourceDynamicPage.ORDER_GROUP));
		}

		// If i'm generating a new report for a subreport i add also to the new report parameters the ones defined for the
		// sub report
		if (settings.containsKey(WizardDataSourceDynamicPage.EXTRA_PARAMETERS)) {
			templateSettings.put(DefaultTemplateEngine.OTHER_PARAMETERS,
			settings.get(WizardDataSourceDynamicPage.EXTRA_PARAMETERS));
		}

		TemplateEngine templateEngine = templateBundle.getTemplateEngine();
		ByteArrayInputStream stream = null;
		try {
			ReportBundle reportBundle = templateEngine.generateReportBundle(templateBundle, templateSettings, jConfig);

			// Save the data adapter used...
			if (step1.getDataAdapter() != null) {
				Object props = settings.get(WizardDataSourceDynamicPage.DATASET_PROPERTIES);
				JRPropertiesMap pmap = new JRPropertiesMap();
				if (props != null && props instanceof JRPropertiesMap) {
					pmap = (JRPropertiesMap) props;
				}
				templateEngine.setReportDataAdapter(reportBundle, step1.getDataAdapter(), pmap);

			}
			reportFile = saveBundleIntoFile(reportBundle, mainWizard, jConfig, monitor);
		} catch (Exception e) {
			UIUtils.showError(e);
		} 
		FileUtils.closeStream(stream);
		step1 = null;
		step2 = null;
		step3 = null;
		return reportFile;
	}

	@Override
	public void doCancel() {
		step1 = null;
		step2 = null;
		step3 = null;
	}
	
	@Override
	public WizardPage[] getCustomWizardPages() {
		if (step1 == null || step2 == null || step3 == null){
			step1 = new WizardDataSourceDynamicPage(this);
			step2 = new WizardFieldsDynamicPage(this);
			step3 = new WizardFieldsGroupByDynamicPage(this);
		}
		WizardPage[] result = new WizardPage[]{step1, step2, step3};
		return result;
	}

	public WizardDataSourceDynamicPage getStep1() {
		return step1;
	}

	public WizardFieldsDynamicPage getStep2() {
		return step2;
	}

	public WizardFieldsGroupByDynamicPage getStep3() {
		return step3;
	}

}
