package com.jaspersoft.studio.data.sql.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;

public class ATitledDialog extends Dialog {
	private String description;
	private String title;

	protected ATitledDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText(title);
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		if (description != null) {
			Composite tcmp = new Composite(parent, SWT.NONE);
			tcmp.setLayout(new GridLayout());
			GridData gd = new GridData(GridData.FILL_HORIZONTAL);
			// gd.w = 50;
			tcmp.setLayoutData(gd);
			tcmp.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
			tcmp.setBackgroundMode(SWT.INHERIT_DEFAULT);

			new Label(tcmp, SWT.WRAP).setText(description);
		}
		return super.createDialogArea(parent);

	}
}
