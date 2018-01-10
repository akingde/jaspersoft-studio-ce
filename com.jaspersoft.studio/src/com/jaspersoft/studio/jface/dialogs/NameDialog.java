/*******************************************************************************
 * Copyright (C) 2013 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.jface.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.Misc;

/**
 * A simple dialog to ask a string value to the user
 * 
 * @author Veaceslav Chicu
 * 
 */
public class NameDialog extends ATitledDialog {

	private Text tname;
	private String name;
	private int style = SWT.BORDER;
	private boolean emptyAllowed = false;

	public NameDialog(Shell parentShell, String dialogName) {
		super(parentShell);
		setTitle(dialogName);
	}

	/**
	 * Build the dialog with a title and an initial value for the text field
	 * 
	 * @param parentShell
	 * @param dialogName
	 * @param textInitialValue
	 */
	public NameDialog(Shell parentShell, String dialogName, String name) {
		this(parentShell, dialogName);
		this.name = name;
	}

	/**
	 * Build the dialog with a title and an initial value for the text field
	 * 
	 * @param parentShell
	 * @param dialogName
	 * @param textInitialValue
	 */
	public NameDialog(Shell parentShell, String dialogName, String name, int style, boolean emptyAllowed) {
		this(parentShell, dialogName);
		this.name = name;
		this.style = style;
		this.emptyAllowed = emptyAllowed;
	}

	public String getName() {
		return name;
	}

	/**
	 * Create contents of the dialog.
	 * 
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayout(new GridLayout(1, true));
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		tname = new Text(container, style);
		tname.setText(Misc.nvl(name));
		GridData textData = new GridData(GridData.FILL_HORIZONTAL);
		textData.widthHint = 200;
		tname.setLayoutData(textData);
		tname.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				fillValue(tname.getText());
			}
		});
		UIUtils.getDisplay().asyncExec(new Runnable() {

			@Override
			public void run() {
				fillValue(tname.getText());
			}
		});

		return area;
	}

	private void fillValue(String newValue) {
		if (!emptyAllowed && newValue.trim().isEmpty()) {
			setError("Fill a non empty value");
			canFinish(NameDialog.this, false);
		} else {
			setError(null);
			name = newValue;
			canFinish(NameDialog.this, true);
		}
	}

}
