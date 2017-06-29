/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.style.wizard;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.dialogs.WizardNewFileCreationPage;

import com.jaspersoft.studio.backward.JRVersionPreferencesPages;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRSimpleTemplate;
import net.sf.jasperreports.engine.JRStyle;
import net.sf.jasperreports.engine.design.JRDesignStyle;
import net.sf.jasperreports.engine.xml.JRXmlBaseWriter;
import net.sf.jasperreports.engine.xml.JRXmlTemplateWriter;

/**
 * Wizard to export one or more JRStyle as a separate TemplateStyle file
 * 
 * @author Orlandin Marco
 *
 */
public class StyleTemplateExportWizard extends StyleTemplateNewWizard {

	/**
	 * List of the style to export
	 */
	private List<JRStyle> stylesToImport;
	
	/**
	 * Create the wizard
	 * 
	 * @param stylesToImport styles to export
	 */
	public StyleTemplateExportWizard(List<JRStyle> stylesToImport){
		this.stylesToImport = stylesToImport;
	}
	
	/**
	 * We will initialize file contents with the imported styles
	 */
	@Override
	protected InputStream openContentStream() {
		try {
			JRSimpleTemplate tmp = new JRSimpleTemplate();
			if (stylesToImport == null || stylesToImport.isEmpty()){
				JRDesignStyle jrDesignStyle = new JRDesignStyle();
				jrDesignStyle.setName("SimpleStyle"); //$NON-NLS-1$
				tmp.addStyle(jrDesignStyle);
			} else {
				for(JRStyle style : stylesToImport){
					tmp.addStyle(style);
				}
			}
			JasperReportsConfiguration jConf = null;
			if (file != null)
				jConf = JasperReportsConfiguration.getDefaultJRConfig(file);
			else if (reportFile != null)
				jConf = JasperReportsConfiguration.getDefaultJRConfig(reportFile);
			else
				jConf = JasperReportsConfiguration.getDefaultJRConfig();

			jConf.setProperty(JRXmlBaseWriter.PROPERTY_REPORT_VERSION,
					jConf.getProperty(JRVersionPreferencesPages.JSS_COMPATIBILITY_VERSION));
			String contents = JRXmlTemplateWriter.writeTemplate(jConf, tmp);
			return new ByteArrayInputStream(contents.getBytes());
		} catch (JRException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	protected boolean hasConditionalStyles(){
		for(JRStyle style : stylesToImport){
			if (style.getConditionalStyles().length > 0){
				return true;
			}
		}
		return false;
	}
	
	@Override
	protected void openEditor(IFile file) {
		//Having this empty block the editor to be opened at the end of 
		//the wizard, so it must be called explicitly by outside
	}
	
	public void openStyleEditor() {
		if (getReportFile() != null) {
			super.openEditor(getReportFile());
		}
	}
	
	/**
	 * Return the WizardPage used to select the destination resource for the template reference. The override
	 * show a warning message when at least one of the selected styles is using a conditional style
	 * 
	 * @return a not null {@link WizardNewFileCreationPage}
	 */
	@Override
	protected WizardNewFileCreationPage getDestinationPage(){
		WizardHelpNewFileCreationPage page = new WizardHelpNewFileCreationPage("newFilePage1", (IStructuredSelection) selection){ //$NON-NLS-1$
			
			@Override
			protected boolean validatePage() {
				boolean isValid = super.validatePage();
				if (isValid){
					if (hasConditionalStyles()){
						step1.setMessage(Messages.StyleTemplateExportWizard_conditionalStyleWarning, IStatus.WARNING);
					} else {
						step1.setMessage(Messages.StyleTemplateImportWizard_description);
					}
				}
				return isValid;
			}
		};
		page.setTitle(Messages.StyleTemplateImportWizard_title);
		page.setMessage(Messages.StyleTemplateImportWizard_description);
		page.setFileExtension("jrtx");//$NON-NLS-1$
		return page;
	}
	
	/**
	 * Override of add pages to set a different page title\description from the superclass
	 */
}
