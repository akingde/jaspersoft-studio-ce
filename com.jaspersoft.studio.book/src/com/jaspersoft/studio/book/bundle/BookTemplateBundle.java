/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.book.bundle;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.book.editors.JRBookEditor;
import com.jaspersoft.studio.book.messages.MessagesByKeys;
import com.jaspersoft.studio.book.wizards.BookWizardDataSourceDynamicPage;
import com.jaspersoft.studio.book.wizards.BookWizardFieldsDynamicPage;
import com.jaspersoft.studio.book.wizards.BookWizardSectionsDynamicPage;
import com.jaspersoft.studio.compatibility.JRXmlWriterHelper;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.templates.engine.DefaultTemplateEngine;
import com.jaspersoft.studio.utils.ModelUtils;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.wizards.ReportNewWizard;
import com.jaspersoft.studio.wizards.WizardUtils;
import com.jaspersoft.studio.wizards.datasource.ReportWizardDataSourceDynamicPage;
import com.jaspersoft.templates.ReportBundle;
import com.jaspersoft.templates.TemplateEngine;
import com.jaspersoft.templates.WizardTemplateBundle;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.FileUtils;
import net.sf.jasperreports.engine.JRImage;
import net.sf.jasperreports.engine.JRPropertiesMap;
import net.sf.jasperreports.engine.JRReportTemplate;
import net.sf.jasperreports.engine.JRSubreport;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignElement;
import net.sf.jasperreports.engine.design.JasperDesign;

/**
 * Template bundle for the book template
 * 
 * @author Orlandin Marco
 *
 */
public class BookTemplateBundle extends WizardTemplateBundle {

	/**
	 * Key for the attribute to create or not a book cover
	 */
	public static final String COVER_SETTING = "book_cover";
	
	/**
	 * Key for the attribute to create or not the toc
	 */
	public static final String TOC_SETTING = "book_toc";
	
	/**
	 * Key for the attribute to create or not the book back cover
	 */
	public static final String BACK_COVER_SETTING = "book_backcover";
	
	/**
	 * First page of the wizard where the dataset can be chosen
	 */
	private BookWizardDataSourceDynamicPage step1 = null;
	
	/**
	 * Second page of the wizard where the fields can be chosen
	 */
	private BookWizardFieldsDynamicPage step2 = null;
	
	/**
	 * Their page of the wizard where the section to create can be chosen
	 */
	private BookWizardSectionsDynamicPage step3 = null;
	
	/**
	 * Part definition for the cover
	 */
	private PartContainer coverPart = null;
	
	/**
	 * Part definition for the back cover
	 */
	private PartContainer backcoverPart = null;
	
	/**
	 * Part definition for the toc
	 */
	private PartContainer tocPart = null;
	
	/**
	 * Part definition for the main
	 */
	private PartContainer mainPart = null;
	
	
	private URL mainTemplteURL = null;
	/**
	 * Constructor for the template 
	 * 
	 * @param url the url of the template resource
	 * @param isExternal true if the resource is an external template, false if it is a JSS insternal one
	 * @param jrContext the current context
	 */
	public BookTemplateBundle(URL url, boolean isExternal, JasperReportsContext jrContext) throws Exception {
		super(url, isExternal, jrContext);
		if (isExternal){
			loadExternalResources(url);
		} else {
			loadInternalResources(url);
		}
		this.mainTemplteURL=url;
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
		
		JRDesignDataset dataset = WizardUtils.createDataset(jConfig, true, settings);
		
		//Copy the informations from the wizard inside the map passed to the template engine
		
		templateSettings.put(DefaultTemplateEngine.DATASET, dataset);
		templateSettings.put(ReportNewWizard.CONTAINER_NAME_KEY, containerName);
		templateSettings.put(ReportNewWizard.FILE_NAME_KEY, fileName);

		if (settings.containsKey(BookWizardDataSourceDynamicPage.DATASET_FIELDS)) {
			templateSettings.put(DefaultTemplateEngine.FIELDS, settings.get(BookWizardDataSourceDynamicPage.DATASET_FIELDS));
		}

		if (settings.containsKey(BookWizardDataSourceDynamicPage.GROUP_FIELDS)) {
			templateSettings.put(DefaultTemplateEngine.GROUP_FIELDS, settings.get(BookWizardDataSourceDynamicPage.GROUP_FIELDS));
		}
		
		if (settings.containsKey(BookTemplateBundle.COVER_SETTING)){
			templateSettings.put(BookTemplateBundle.COVER_SETTING, settings.get(BookTemplateBundle.COVER_SETTING));
		} else {
			templateSettings.put(BookTemplateBundle.COVER_SETTING, true);
		}
		
		if (settings.containsKey(BookTemplateBundle.TOC_SETTING)){
			templateSettings.put(BookTemplateBundle.TOC_SETTING, settings.get(BookTemplateBundle.TOC_SETTING));
		} else {
			templateSettings.put(BookTemplateBundle.TOC_SETTING, true);
		}
		
		if (settings.containsKey(BookTemplateBundle.BACK_COVER_SETTING)){
			templateSettings.put(BookTemplateBundle.BACK_COVER_SETTING, settings.get(BookTemplateBundle.BACK_COVER_SETTING));
		} else {
			templateSettings.put(BookTemplateBundle.BACK_COVER_SETTING, true);
		}

		//Produce the new bundle using the template engine
		
		TemplateEngine templateEngine = getTemplateEngine();
		ByteArrayInputStream stream = null;
		try {
			//Save the resources of the report in the destination folder since the could be used by the engine
			saveReportBundleResources(monitor, this, getReportContainer(mainWizard));
			
			//Create the target report
			ReportBundle reportBundle = templateEngine.generateReportBundle(this, templateSettings, jConfig);

			// Save the data adapter used...
			if (step1.getDataAdapter() != null) {
				Object props = settings.get(ReportWizardDataSourceDynamicPage.DATASET_PROPERTIES);
				JRPropertiesMap pmap = new JRPropertiesMap();
				if (props != null && props instanceof JRPropertiesMap) {
					pmap = (JRPropertiesMap) props;
				}
				templateEngine.setReportDataAdapter(reportBundle, step1.getDataAdapter(), pmap);

			}
			reportFile = saveBundleIntoFile(reportBundle, mainWizard, jConfig, monitor);
			// Force the default editor for the book so that it can be opened with the same one in the future
			IDE.setDefaultEditor(reportFile, JRBookEditor.BOOK_EDITOR_ID);
			saveSections(containerName, fileName, templateSettings, monitor);
			//Since the template engine could have changed the design of the part i discard them and they  
			//will be reloaded if the template is used to create another report
			clearLoadedParts();
		} catch (Exception e) {
			UIUtils.showError(e);
		} 
		FileUtils.closeStream(stream);
		return reportFile;
	}
	
	/**
	 * Clear the cached jasper design inside the defined parts
	 */
	private void clearLoadedParts(){
		if (coverPart != null) coverPart.clearDesign();
		if (backcoverPart != null) backcoverPart.clearDesign();
		if (tocPart != null) tocPart.clearDesign();
		if (mainPart != null) mainPart.clearDesign();
	}
	
	/**
	 * Save the sections if the user has requested it and if a template for them was found
	 * 
	 * @param containerName the name of the container where the resources will be saved
	 * @param fileName the name of the file to save the resource
	 * @param templateSettings the template settings provided by the user during the wizard
	 * @param monitor the monitor to execute the operation
	 */
	protected void saveSections(String containerName, String fileName, Map<String, Object> templateSettings, IProgressMonitor monitor) {
		//The following code store the bundle inside a jrxmlfile
		IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
		IResource resource = root.findMember(new Path(containerName));
		// Store the report bundle on file system
		IProject container = (IProject) resource;
		String prefix = fileName.replace(".jrxml", "");
		
		boolean createCover = (Boolean)templateSettings.get(COVER_SETTING) && coverPart != null;
		if (createCover){
			saveDesignResource(coverPart.getJasperDesign(), container, prefix + "_cover.jrxml", monitor);
		}
		
		boolean createBackcover = (Boolean)templateSettings.get(BACK_COVER_SETTING) && backcoverPart != null;
		if (createBackcover){
			saveDesignResource(backcoverPart.getJasperDesign(), container, prefix + "_backcover.jrxml", monitor);
		}
		
		boolean createToc = (Boolean)templateSettings.get(TOC_SETTING) && tocPart != null;
		if (createToc){
			saveDesignResource(tocPart.getJasperDesign(), container, prefix + "_toc.jrxml", monitor);
		}
		
		boolean crateMain = mainPart != null;
		if (crateMain){
			saveDesignResource(mainPart.getJasperDesign(), container, prefix + "_main.jrxml", monitor);
		}
	}
	
	/**
	 * Save the design of a jrxml file into the specified container with the specified resource name
	 * 
	 * @param design the design to save
	 * @param container the container where to save the design
	 * @param resourceName the name of the created file
	 * @param monitor the monitor to execute the operation
	 */
	protected void saveDesignResource(JasperDesign design, IProject container, String resourceName, IProgressMonitor monitor){
		if (design != null){
			IFile resourceFile = container.getFile(resourceName);
			ByteArrayInputStream stream = null;
			// Save the all the files...
			try {
				String contents = JRXmlWriterHelper.writeReport(JasperReportsConfiguration.getDefaultInstance(), design, resourceFile, false);
				stream = new ByteArrayInputStream(contents.getBytes());
				if (resourceFile.exists()) {
					resourceFile.setContents(stream, true, true, monitor);
				} else {
					resourceFile.create(stream, true, monitor);
				}
				saveAdditionalDesignResources(design, container, monitor);
				
				
			} catch(Exception ex){
			} finally {
				FileUtils.closeStream(stream);
			}
		}
	}
	
	
	protected void saveAdditionalDesignResources(JasperDesign jd, IProject container, IProgressMonitor monitor)
	{
				List<String> additionalResourceNames = new ArrayList<String>();
				List<JRDesignElement> list = ModelUtils.getAllGElements(jd);

				for (JRDesignElement el : list) {
					// Check for images...
					if (el instanceof JRImage) {
						JRImage im = (JRImage) el;
						String res = evalResourceName(im.getExpression());
						if (res != null) {
							additionalResourceNames.add(res);
						}
					}
					// Check for subreports (filename.jasper becomes filename.jrxml)
					if (el instanceof JRSubreport) {
						JRSubreport sr = (JRSubreport) el;
						String res = evalResourceName(sr.getExpression());
						if (res.endsWith(".jasper")) {
							res = res.substring(0, res.length() - ".jasper".length()) + ".jrxml";
							additionalResourceNames.add(res);
						}
					}
				}
				// Check for external style references
				List<JRReportTemplate> templates = getJasperDesign().getTemplatesList();
				for (JRReportTemplate t : templates) {
					String res = evalResourceName(t.getSourceExpression());
					if (res != null) {
						additionalResourceNames.add(res);
					}
				}
				
				for (String resourceName : additionalResourceNames)
				{
					IFile resourceFile = container.getFile(new Path(resourceName));
					InputStream is = null;
					try {
						if (!resourceFile.exists()) {
							is = this.getAdditionalResource(resourceName);
							if (is != null) {
								resourceFile.create(is, true, monitor);
							}
						}
					} catch (Exception e) {
						UIUtils.showError(e);
					} finally {
						FileUtils.closeStream(is);
					}
				}
	}
	

	public InputStream getAdditionalResource(String name) {
		
		// We need to replace the last name from the current templateURL..
		String url = mainTemplteURL.toString();
		String mainFileName = new File(mainTemplteURL.getFile()).getName();
		url = url.substring(0, url.length() - mainFileName.length()) + name;
		try {
			URL resourceURL = new URL(url);
			return resourceURL.openStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * On the abort all the three steps are set to null to be rebuild on the next opening
	 */
	@Override
	public void wizardClosed() {
		step1 = null;
		step2 = null;
		step3 = null;
	}
	
	/**
	 * Create the dynamic wizard steps if necessary then return them
	 * 
	 * @return a not null array of steps
	 */
	@Override
	public WizardPage[] getCustomWizardPages() {
		List<WizardPage> result = new ArrayList<WizardPage>();
		if(step1 == null) {
			step1 = new BookWizardDataSourceDynamicPage(this);
		}
		if(step2 == null) {
			step2 = new BookWizardFieldsDynamicPage(this);
		}
		boolean showSectionsPage = shouldShowSectionsPage();
		if(showSectionsPage || step3 == null) {
			step3 = new BookWizardSectionsDynamicPage(this);
		}
		result.add(step1);
		result.add(step2);
		if(showSectionsPage){
			result.add(step3);	
		}
		return result.toArray(new WizardPage[result.size()]);
	}
	
	/*
	 * Checks if the page allowing to select sections should be shown.
	 */
	private boolean shouldShowSectionsPage() {
		Object showSectionsProperty = getProperty("template.booksections.showpage");	//$NON-NLS-1$
		boolean showSectionsPage = true;
		if(showSectionsProperty instanceof String){
			showSectionsPage = Boolean.parseBoolean((String) showSectionsProperty);
		}
		return showSectionsPage;
	}
	
	/**
	 * Get the First dynamic page of the wizard where the dataset can be chosen
	 * 
	 * @return a not null BookWizardDataSourceDynamicPage
	 */
	public BookWizardDataSourceDynamicPage getStep1() {
		return step1;
	}

	/**
	 * Get the First dynamic page of the wizard where the fields can be chosen
	 * 
	 * @return a not null BookWizardFieldsDynamicPage
	 */
	public BookWizardFieldsDynamicPage getStep2() {
		return step2;
	}

	/**
	 * Get the First dynamic page of the wizard where the book sections can be chosen
	 * 
	 * @return a not null BookWizardSectionsDynamicPage
	 */
	public BookWizardSectionsDynamicPage getStep3() {
		return shouldShowSectionsPage() ? step3 : null;
	}
	
	/**
	 * Return the jasperdesign for the cover of the current template
	 * 
	 * @return a jasperdesign or null if no cover is found for this template
	 */
	public JasperDesign getCover(){
		if (coverPart != null) return coverPart.getJasperDesign();
		else return null;
	}
	
	/**
	 * Return the jasperdesign for the back cover of the current template
	 * 
	 * @return a jasperdesign or null if no back cover is found for this template
	 */
	public JasperDesign getBackCover(){
		if (backcoverPart != null) return backcoverPart.getJasperDesign();
		else return null;
	}
	
	/**
	 * Return the jasperdesign for the toc of the current template
	 * 
	 * @return a jasperdesign or null if no toc is found for this template
	 */
	public JasperDesign getTOC(){
		if (tocPart != null) return tocPart.getJasperDesign();
		else return null;
	}
	
	/**
	 * Return the jasperdesign for the main section of the current template
	 * 
	 * @return a jasperdesign or null if no main section is found for this template
	 */
	public JasperDesign getMain(){
		if (mainPart != null) return mainPart.getJasperDesign();
		else return null;
	}

	/**
	 * For the book based templates return a Book Template Engine
	 */
	protected void readProperties()
	{
		super.readProperties();
		templateEngine = new BookTemplateEngine(); 
	}

	/**
	 * Try to load the cover, back cover, toc and main resources
	 * from an external folder, at the same level of the current template
	 * 
	 * @param templateDocument the loaded template
	 */
	private void loadExternalResources(URL templateDocument){
		File reportFile = new File(templateDocument.getFile());
		if (reportFile.exists()){
			String prefix = reportFile.getName().replace(".jrxml", "");
			String coverName = prefix + "_cover.jrxml.part";
			String backCoverName = prefix + "_backcover.jrxml.part";
			String tocName = prefix + "_toc.jrxml.part";
			String mainName = prefix + "_main.jrxml.part";
			String parentPath = reportFile.getParentFile() + File.separator;
			coverPart = new PartContainer(parentPath+coverName);
			backcoverPart = new PartContainer(parentPath+backCoverName);
			tocPart = new PartContainer(parentPath+tocName);
			mainPart = new PartContainer(parentPath+mainName);
		}
	}
	
	/**
	 * Return a part container with pointing to a specified plugin path
	 */
	private PartContainer getPartFromName(String hostPlugin, String fileName){
		try {
			URL pathUrl = new URL("bundleentry", hostPlugin, fileName);
			return new PartContainer(pathUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Try to load the cover, back cover, toc and main resources
	 * from the JSS jar, at the same level of the current template
	 * 
	 * @param templateDocument the loaded template
	 */
	private void loadInternalResources(URL templateDocument){
		File reportFile = new File(templateDocument.getFile());
		String prefix = reportFile.getName().replace(".jrxml", "");
		String coverName = prefix + "_cover.jrxml.part";
		String backCoverName = prefix + "_backcover.jrxml.part";
		String tocName = prefix + "_toc.jrxml.part";
		String mainName = prefix + "_main.jrxml.part";
		String folder = "/templates/book/";
		coverPart = getPartFromName(templateDocument.getHost(), folder + coverName);
		backcoverPart = getPartFromName(templateDocument.getHost(), folder + backCoverName);
		tocPart = getPartFromName(templateDocument.getHost(), folder + tocName);
		mainPart = getPartFromName(templateDocument.getHost(), folder + mainName);
	}
	
	@Override
	public String getLocalizedString(String key) {
		if (MessagesByKeys.hasTranslation(key)){
			return MessagesByKeys.getString(key);
		} else return super.getLocalizedString(key);
	}

	@Override
	public boolean hasSupportForSubreport() {
		return false;
	}
}
