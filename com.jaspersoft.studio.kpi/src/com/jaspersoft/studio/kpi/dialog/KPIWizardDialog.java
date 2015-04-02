package com.jaspersoft.studio.kpi.dialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.jface.dialogs.MessageDialog;
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
import com.jaspersoft.studio.server.protocol.IConnection;

public class KPIWizardDialog extends WizardDialog {

	private Button deleteButton = null;

	private KPIConfiguratorWizard wizard;
	
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
				
				// TODO: Ask delete confirmation..
				boolean res = MessageDialog.openConfirm(UIUtils.getShell(), "KPI removal", "Do you really want to remove the existing KPI for this report unit?");
				
				if (res)
				{
					try {
						KPIConfiguratorPage configuratorPage = (KPIConfiguratorPage)((wizard.getPages())[0]);
						
						if (configuratorPage.getKpiReportUnit() != null)
						{
							
							IConnection client = configuratorPage.getWSClient();
							client.delete(new NullProgressMonitor(), configuratorPage.getKpiReportUnit());
						}
						MessageDialog.openInformation(UIUtils.getShell(), "KPI removal", "KPI removed.");
						
						
						cancelPressed();
						
					} catch (Exception ex)
					{
						MessageDialog.openError(UIUtils.getShell(), "KPI removal error", "I was not able to delete the KPI of the report unit...\n" +  ex.getMessage());
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
		deleteButton = createButton(parent, "Delete");
		super.createButtonsForButtonBar(parent);
	}
};
