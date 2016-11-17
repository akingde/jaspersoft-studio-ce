/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;

public class FontPathPage extends WizardPage {
	private String value;

	protected FontPathPage() {
		super("fontPathPage"); //$NON-NLS-1$
		setTitle(Messages.FontPathPage_0);
		setDescription(Messages.FontPathPage_1);
	}

	public String getValue() {
		return value;
	}

	private static String path;

	@Override
	public void createControl(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		setControl(cmp);

		new Label(cmp, SWT.NONE).setText(Messages.FontPathPage_2);

		final Text txt = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button b = new Button(cmp, SWT.PUSH);
		b.setText("..."); //$NON-NLS-1$
		b.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				DirectoryDialog dialog = new DirectoryDialog(getShell(), SWT.OPEN);
				dialog.setText("Fonts Path");
				dialog.setMessage("A directory with TTF fonts.");
				dialog.setFilterPath(path);
				String result = dialog.open();
				if (result != null) {
					value = result;
					txt.setText(result);
					path = result;
					setPageComplete(true);
				}
			}
		});
		setPageComplete(false);
	}

}
