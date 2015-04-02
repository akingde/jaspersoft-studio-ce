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

public class KPIWizardDialog extends WizardDialog {

	private Button deleteButton = null;

	private KPIConfiguratorWizard wizard;
	
	private List<Object> runnedThreads = new ArrayList<Object>();
	
	private class RunnableWrapper implements IRunnableWithProgress{
		
		private IRunnableWithProgress runnableElement;

		public RunnableWrapper(IRunnableWithProgress runnableElement){
			this.runnableElement = runnableElement;
		}
		
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
	
	public KPIWizardDialog(Shell parentShell, KPIConfiguratorWizard newWizard) {
		super(parentShell, newWizard);
		this.wizard = newWizard;
	}

	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		UIUtils.resizeAndCenterShell(newShell, 700, 600);
	};

	protected Button createButton(Composite parent, String text) {
		// increment the number of columns in the button bar
		((GridLayout) parent.getLayout()).numColumns++;
		Button button = new Button(parent, SWT.PUSH);
		button.setText(text);
		button.setFont(JFaceResources.getDialogFont());
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
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
		});
		setButtonLayoutData(button);
		return button;
	}

	protected void createButtonsForButtonBar(Composite parent) {
		((GridLayout) parent.getLayout()).makeColumnsEqualWidth = false;
		deleteButton = createButton(parent, Messages.KPIWizardDialog_deleteButton);
		super.createButtonsForButtonBar(parent);
	}
	
	@Override
	public void run(boolean fork, boolean cancelable, IRunnableWithProgress runnable) throws InvocationTargetException, InterruptedException {
		super.run(fork, cancelable, new RunnableWrapper(runnable));
	}
};
