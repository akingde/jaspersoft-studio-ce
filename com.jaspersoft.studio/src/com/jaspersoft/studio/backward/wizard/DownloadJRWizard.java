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
package com.jaspersoft.studio.backward.wizard;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map.Entry;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.backward.JRBackwardManager;
import com.jaspersoft.studio.backward.JRDefinition;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.wizards.JSSWizard;

/**
 * Wizard used to manage (delete or download) the previous version of JR
 * 
 * @author Orlandin Marco
 *
 */
public class DownloadJRWizard extends JSSWizard {

	/**
	 * Page where all the version of JR that can be managed are listed
	 */
	private ShowInstallationsWizardPage page0;
	
	/**
	 * Page to show the result of the operation
	 */
	private SummaryWizardPage page1;
	
	@Override
	public void addPages() {
		page0 = new ShowInstallationsWizardPage();
		addPage(page0);
		page1 = new SummaryWizardPage();
		addPage(page1);
	}
	
	/**
	 * Called when the user press next to go to the second page, launch the thread
	 * to download and unpack (or delete) the selected (or unslected) version.
	 * The next page will be shown automatically when the thread is completed
	 * 
	 * @param mainDialog main dialog of the wizard, the reference is used to notify that the thread
	 * is completed, must be not null
	 */
	protected void nextPressed(final DownloadJRWizardDialog mainDialog){
		run(false, true, new IRunnableWithProgress() {
			
			@Override
			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
				HashMap<JRDefinition, Boolean> selection = page0.getSelection();
				monitor.beginTask(Messages.DownloadJRWizard_monitorStart, selection.entrySet().size());
				for(Entry<JRDefinition,Boolean> def : selection.entrySet()){
					if (def.getValue().booleanValue() == false){
						JRBackwardManager.INSTANCE.deleteJR(def.getKey());
						monitor.worked(1);
					} else {
						try{
							JRBackwardManager.INSTANCE.getJRFolder(def.getKey().getVersion(), monitor);
						} catch(Exception ex){
							String errorMessage = MessageFormat.format(Messages.DownloadJRWizard_errorMessage, new Object[]{def.getKey().getVersion()});
							JaspersoftStudioPlugin.getInstance().logError(errorMessage, ex);
							page1.setError(true);
						}
						monitor.worked(1);
					}
					if(monitor.isCanceled()) {
						page1.setAborted(true);
						break;
					}
				}
				monitor.done();
				//The notification of the completed thread must be in the graphic thread since it force the advance of the page
				UIUtils.getDisplay().syncExec(new Runnable() {
					@Override
					public void run() {
						mainDialog.threadCompleted();
					}
				});
			}
		});
		getContainer().updateButtons();
	}
	
	@Override
	public boolean canFinish() {
		return getContainer().getCurrentPage() == page1;
	}
}
