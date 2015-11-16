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
package com.jaspersoft.studio.kpi.dialog;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.kpi.KPIUtils;
import com.jaspersoft.studio.kpi.messages.Messages;

/**
 * Wizard dialog for the KPI configuration wizard. It show an 
 * additional button on the button bar to delete the KPI. It provide the 
 * structure to disable this additional button when a thread is runned
 * 
 * @author Orlandin Marco
 *
 */
public class KPIWizardDialog extends WizardDialog {

	/**
	 * Button to delete the kpi
	 */
	private Button deleteButton = null;

	/**
	 * The wizard
	 */
	private KPIConfiguratorWizard wizard;
	
	/**
	 * The list used to count the number of thread currently runned from this dialog
	 */
	private List<Object> runnedThreads = new ArrayList<Object>();
	
	/**
	 * Wrapper for a thread, used to do actions when a thread start or end, before
	 * and after the execution of the wrapped runnable
	 * 
	 * @author Orlandin Marco
	 *
	 */
	private class RunnableWrapper implements IRunnableWithProgress{
		
		/**
		 * The wrapped runnable
		 */
		private IRunnableWithProgress runnableElement;

		/**
		 * Create the wrapper
		 * 
		 * @param runnableElement the real runnable that want to execute, must
		 * be not null
		 */
		public RunnableWrapper(IRunnableWithProgress runnableElement){
			this.runnableElement = runnableElement;
		}
		
		/**
		 * Run the wrapped runnable executing some action after and before, and 
		 * counting the number of runnable still in execution
		 */
		@Override
		public void run(IProgressMonitor monitor) {
			try{
				synchronized (runnedThreads) {
					runnedThreads.add(new Object());
					threadStarted();
				}
				runnableElement.run(monitor);
				synchronized (runnedThreads) {
					runnedThreads.remove(0);
					threadEnded();
				}
			} catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}
	
	/**
	 * Actions to execute when a thread starts, in this case it
	 * disable the delete button
	 */
	protected void threadStarted(){
		if (deleteButton != null && !deleteButton.isDisposed()){
			UIUtils.getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					deleteButton.setEnabled(false);
				}
			});
		}
	}
	
	/**
	 * Actions to execute when a thread ends, in this case it
	 * enable the delete button if there aren't any other thread
	 * running
	 */
	protected void threadEnded(){
		if (deleteButton != null && !deleteButton.isDisposed() && runnedThreads.isEmpty()){
			UIUtils.getDisplay().syncExec(new Runnable() {
				@Override
				public void run() {
					deleteButton.setEnabled(true);
				}
			});
		}
	}
	
	/**
	 * Create the wizard dialog
	 * 
	 * @param parentShell parent shell, must be not null
	 * @param newWizard the KPI configuration wizard shown inside the dialog
	 */
	public KPIWizardDialog(Shell parentShell, KPIConfiguratorWizard newWizard) {
		super(parentShell, newWizard);
		this.wizard = newWizard;
	}

	/**
	 * When configured force the dialog to have a specific size
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		UIUtils.resizeAndCenterShell(newShell, 700, 620);
	};
	
	/**
	 * Delete the currently handled KPI
	 */
	private void deleteKPI(){
		boolean res = MessageDialog.openConfirm(UIUtils.getShell(), Messages.KPIWizardDialog_removeTitle, Messages.KPIWizardDialog_removeMessage);
		if (res)
		{
			try {
				KPIConfiguratorPage configuratorPage = (KPIConfiguratorPage)((wizard.getPages())[0]);
				
				if (configuratorPage.getKpiReportUnit() != null)
				{
					KPIUtils.deleteReportUnitKPI(configuratorPage.getWSClient(), configuratorPage.getParentReportUnit().getUriString());
				}
				MessageDialog.openInformation(UIUtils.getShell(), Messages.KPIWizardDialog_removeTitle, Messages.KPIWizardDialog_removeSuccess);
				
				
				cancelPressed();
				
			} catch (Exception ex)
			{
				MessageDialog.openError(UIUtils.getShell(), Messages.KPIWizardDialog_removeErrorTitle, Messages.KPIWizardDialog_removeErrorMessage +  ex.getMessage());
				JaspersoftStudioPlugin.getInstance().logError(ex);
			}
		}
	}

	/**
	 * Create the delete button and attach a listener to it
	 * 
	 * @param parent the parent of the button
	 * @param text the text on the button
	 * @return the button created, not null
	 */
	protected Button createButton(Composite parent, String text) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(text);
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				deleteKPI();
			}
		});
		setButtonLayoutData(button);
		return button;
	}

	/**
	 * Override the create buttons method to add the new delete button
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
		deleteButton = createButton(parent, Messages.common_delete);
		super.createButtonsForButtonBar(parent);
	}
	
	/**
	 * Override the method run of the dialog to wrap the runned thread into
	 * another runnable element that will execute some operation before and after 
	 * the wrapped one execution
	 */
	@Override
	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
		super.run(fork, cancelable, new RunnableWrapper(runnable));
	}
};
