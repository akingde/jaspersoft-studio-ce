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
package com.jaspersoft.studio.handlers;


import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.ide.IDE;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.CongratulationsWizardPage;

/**
 * Class that implement the wizard to export a report as a template. the wizard is composed
 * of three steps but only the first two are important for the exporting process, the third is
 * only a "Congratulation" step
 * 
 * @author Orlandin Marco
 *
 */
public class TemplateExporterWizard extends Wizard {

	/**
	 * The selected report
	 */
	private IStructuredSelection selection;
	
	/**
	 * First page of the wizard: resource exporting and destination path
	 */
	private ResourcePage firstPage;
	
	/**
	 * Second step of the wizard: report type and categories
	 */
	private CategoriesPage secondPage;

	/**
	 * Create the three step and add them to the report, but only if a report is selected
	 */
	@Override
	public void addPages() {
		super.addPages();
		if (selection.getFirstElement() instanceof IFile){
			IFile reportFile = (IFile)selection.getFirstElement();
			try {
				firstPage = new ResourcePage(reportFile);
				secondPage = new CategoriesPage();
				
				addPage(firstPage);
				addPage(secondPage);
				addPage(createCongratPage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		} 
	}
	
	/**
	 * Create the congratulations page, setting the propper grid data. The last page in particalry is 
	 * used here to define the size of the wizard dialog
	 * 
	 * @return
	 */
	private CongratulationsWizardPage createCongratPage(){
		CongratulationsWizardPage thirdPage = new CongratulationsWizardPage(
																									Messages.TemplateExporterWizard_congratTitle, 
																									Messages.TemplateExporterWizard_congratDesc, 
																									Messages.TemplateExporterWizard_congratMessage, 
																									Messages.TemplateExporterWizard_congrattoFinish, 
																									Messages.TemplateExporterWizard_congratCongratulations);
		GridData informationData = new GridData(SWT.FILL, SWT.FILL, true, false);
		informationData.widthHint = 170;
		thirdPage.setAllInformationData(informationData);
		
		GridData finishData = new GridData(SWT.FILL, SWT.LEFT, true, false);
		finishData.heightHint = 130;
		thirdPage.setFinishData(finishData);
		
		return thirdPage;
	}
	
	/**
	 * Check if the actual selection is valid to open the wizard, so it must be not null and 
	 * not empty
	 * 
	 * @return true if the actual selection is valid to open this wizard, otherwise false
	 */
	public boolean canOpen(){
		return selection != null && !selection.isEmpty();
	}

	/**
	 * Initialize the selection of the wizard, by searching an IFile in the selected element
	 * in the Project Explorer or in the actually opened and focused editor
	 * 
	 * @param workbench 
	 * @param currentSelection the element actually selected
	 */
	public void init(IWorkbench workbench, IStructuredSelection currentSelection) {
			this.selection = currentSelection;
      @SuppressWarnings("rawtypes")
			List selectedResources = IDE.computeSelectedResources(currentSelection);
      if (!selectedResources.isEmpty()) {
          this.selection = new StructuredSelection(selectedResources);
      }

      // look it up if current selection (after resource adapting) is empty
      if (selection.isEmpty() && workbench.getActiveWorkbenchWindow() != null) {
          IWorkbenchPage page = workbench.getActiveWorkbenchWindow()
                  .getActivePage();
          if (page != null) {
              IEditorPart currentEditor = page.getActiveEditor();
              if (currentEditor != null) {
                  Object selectedResource = currentEditor.getEditorInput()
                          .getAdapter(IResource.class);
                  if (selectedResource != null) {
                      selection = new StructuredSelection(selectedResource);
                  }
              }
          }
      }
			setWindowTitle(Messages.TemplateExporterWizard_title);
			setNeedsProgressMonitor(false);
	}

	/**
	 * The finish of the wizard call the finish of every single step
	 */
	@Override
	public boolean performFinish() {
		firstPage.finish();
		secondPage.finish(firstPage.getDesign().getName(),firstPage.getDestinationPath());
		return true;
	}

}
