/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.translation.wizard;

import java.io.File;
import java.util.List;

import com.jaspersoft.studio.translation.ExtendedTranslationInformation;
import com.jaspersoft.studio.translation.FragmentCreationUtil;
import com.jaspersoft.studio.translation.ImageLocale;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;
import com.jaspersoft.studio.wizards.JSSWizard;

public class GenerateFragmentWizard extends JSSWizard {

	private ExportedResourcesWizardPage step1;
	
	private LocalesTranslationWizardPage step2;
	
	private CongratulationsWizardPage step3;
	
	private List<ExtendedTranslationInformation> sourceList;
	
	public GenerateFragmentWizard(List<ExtendedTranslationInformation> sourceList){
		super();
		this.sourceList = sourceList;
	}
	
	@Override
	public void addPages() {
		step1 = new ExportedResourcesWizardPage(sourceList);
		addPage(step1);

		step2 = new LocalesTranslationWizardPage();
		addPage(step2);
		
		step3 = new CongratulationsWizardPage("Procedure complete", "You are ready to export the translation", "The selected translation files will now be exported into the selected folder. This file can be placed inside any Jaspersoft Studio dropins folder to get loaded by the application at the startup. After the file has been placed you can see your new language from the menu Windows -> Language", "Press finish to start the export process", "Congratulations");
		addPage(step3);
	}
	
	@Override
	public boolean canFinish() {
		 List<ExtendedTranslationInformation> selectedResources = step1.getSelectedResources();
		 if (selectedResources.isEmpty()) return false;
		 String destinationPath = step2.getDestinationPath();
		 if (destinationPath.isEmpty() || !(new File(destinationPath).exists())) return false;
		 List<ImageLocale> localesList = step2.getSelectedLanguages();
		 if (localesList.isEmpty()) return false;
		 return true;
	}
	
	@Override
	public boolean performFinish() {
		 List<ExtendedTranslationInformation> selectedResources = step1.getSelectedResources();
		 String destinationPath = step2.getDestinationPath();
		 List<ImageLocale> localesList = step2.getSelectedLanguages();
		 FragmentCreationUtil.createFragment(destinationPath, selectedResources, localesList);
		 return super.performFinish();
	}

}
