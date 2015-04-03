package com.jaspersoft.studio.kpi.template;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;

import com.jaspersoft.studio.kpi.messages.MessagesByKeys;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateEngine;
import com.jaspersoft.templates.WizardTemplateBundle;

public class KPITemplateBundle extends WizardTemplateBundle {

	public KPITemplateBundle(URL url, boolean isExternal, JasperReportsContext jrContext) throws Exception {
		super(url, isExternal, jrContext);
	}

	@Override
	public WizardPage[] getCustomWizardPages() {
		return new WizardPage[0];
	}

	@Override
	public IFile doFinish(ReportNewWizard mainWizard, IProgressMonitor monitor)throws CoreException {
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
		
		//Copy the informations from the wizard inside the map passed to the template engine
		
		templateSettings.put(ReportNewWizard.CONTAINER_NAME_KEY, containerName);
		templateSettings.put(ReportNewWizard.FILE_NAME_KEY, fileName);
		
		//Produce the new bundle using the template engine
		
		TemplateEngine templateEngine = getTemplateEngine();
		ByteArrayInputStream stream = null;
		try {
			ReportBundle reportBundle = templateEngine.generateReportBundle(this, templateSettings, jConfig);
			reportFile = saveBundleIntoFile(reportBundle, mainWizard, jConfig, monitor);
		} catch (Exception e) {
			UIUtils.showError(e);
		} 
		FileUtils.closeStream(stream);
		return reportFile;
	}

	@Override
	public void doCancel() {
	}

	/**
	 * For the book based templates return a Book Template Engine
	 */
	@Override
	protected void readProperties()
	{
		super.readProperties();
		templateEngine = new KPITemplateEnine(); 
	}
	
	@Override
	public String getLocalizedString(String key) {
		if (MessagesByKeys.hasTranslation(key)){
			return MessagesByKeys.getString(key);
		} else return super.getLocalizedString(key);
	}
}
