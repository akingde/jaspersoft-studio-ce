/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.editor.tools.wizards;

import java.text.MessageFormat;

import org.eclipse.jface.wizard.IWizardPage;

import com.jaspersoft.studio.editor.tools.CompositeElementManager;
import com.jaspersoft.studio.editor.tools.MCompositeElement;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.ModelUtils;

/**
 * Page used to define the data of an imported composie element
 * 
 * @author Orlandin Marco
 *
 */
public class CompositeElementImportWizardPage extends CompositeElementEditWizardPage {

	/**
	 * the element that will be imported
	 */
	private MCompositeElement elementToImport; 
	
	/**
	 * the description of this page. It is keep saved since it need sometime to b
	 * be restored by the update status method. Since it need some calculation it is
	 * cached
	 */
	private String description;
	
	/**
	 * Create the page
	 * 
	 * @param elementToImport the element to import, must be not null
	 * @param currentElement the number of this page, this is used to create the description
	 * @param totalElements the total number of pages at the end, this will be used to create the description
	 */
	public CompositeElementImportWizardPage(MCompositeElement elementToImport, int currentElement, int totalElements) {
		super(elementToImport.getName(), elementToImport.getDescription(), 
					elementToImport.getGroupId(), getFirstAvailableImagePath(elementToImport));
		this.elementToImport = elementToImport;
		description = MessageFormat.format(Messages.CompositeElementImportWizardPage_pageDescription, new Object[]{currentElement, totalElements});
		setTitle(Messages.CompositeElementImportWizardPage_pageTitle);
		setDescription(description);
	}
	
	/**
	 * Check if the element name is in use between other composite element inside the import 
	 * wizard 
	 * 
	 * @return true if the name is already in use, false otherwise
	 */
	protected boolean isNameUsedInOtherPage(){
		for(IWizardPage page : getWizard().getPages()){
			CompositeElementImportWizardPage importPage = (CompositeElementImportWizardPage)page;
			if (importPage != this && ModelUtils.safeEquals(importPage.getName(), getName())){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Check if the page is complete, the page is completed 
	 * if name, description and icon are not empty and if the name is 
	 * unique
	 */
	@Override
	public boolean isPageComplete() {
		String name = getName().trim();
		if (name.isEmpty()){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameEmpry);	
		} else if (!net.sf.jasperreports.eclipse.util.FileUtils.isValidFilename(name)){
			setErrorMessage(Messages.ToolDefinitionWizardPage_invalidFileName);
		} else if (CompositeElementManager.INSTANCE.isNameAlreadyUsed(name.trim())){
			setErrorMessage(Messages.ToolDefinitionWizardPage_errorNameUsed);
		} else if (isNameUsedInOtherPage()){
			setErrorMessage(Messages.CompositeElementImportWizardPage_errorNameUsed);
		} else {
			setErrorMessage(null);
			setDescription(description);
		}
		return getErrorMessage() == null;
	}
	
	/**
	 * Return the element that will be imported 
	 * 
	 * @return a not null model of a composite element
	 */
	public MCompositeElement getElementToImport(){
		return elementToImport;
	}
}
