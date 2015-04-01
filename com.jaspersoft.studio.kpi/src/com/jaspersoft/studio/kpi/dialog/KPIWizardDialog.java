package com.jaspersoft.studio.kpi.dialog;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

public class KPIWizardDialog extends WizardDialog {

	private Button deleteButton = null;

	public KPIWizardDialog(Shell parentShell, IWizard newWizard) {
		super(parentShell, newWizard);
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
				System.out.println("delete pressed");
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
