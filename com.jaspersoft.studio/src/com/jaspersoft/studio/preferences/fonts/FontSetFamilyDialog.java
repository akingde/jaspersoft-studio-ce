/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.lang.UScript;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.fonts.FontSetFamily;
import net.sf.jasperreports.engine.fonts.SimpleFontSet;
import net.sf.jasperreports.engine.fonts.SimpleFontSetFamily;

public class FontSetFamilyDialog extends ATitledDialog {
	private SimpleFontSetFamily fsf;
	private SimpleFontSet fs;

	protected FontSetFamilyDialog(Shell parentShell, SimpleFontSet fs, SimpleFontSetFamily fsf) {
		super(parentShell);
		setTitle("Font Set Family");
		setDefaultSize(400, 200);
		this.fs = fs;
		this.fsf = fsf;
	}

	public SimpleFontSetFamily getValue() {
		return fsf;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText("Font Family");

		final Text txt = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt.setText(Misc.nvl(fsf.getFamilyName()));

		Button bPrimary = new Button(cmp, SWT.CHECK);
		bPrimary.setText("Primary");
		bPrimary.setToolTipText("Check it to mark default primary font");
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		bPrimary.setLayoutData(gd);
		bPrimary.setSelection(fsf.isPrimary());
		if (fsf.isPrimary())
			bPrimary.setEnabled(false);
		else
			bPrimary.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					for (FontSetFamily item : fs.getFamilies()) {
						if (item.getFamilyName().equals(fsf.getFamilyName()))
							fsf.setPrimary(true);
						else
							((SimpleFontSetFamily) item).setPrimary(false);
					}
				}
			});

		// add filters
		new Label(cmp, SWT.NONE).setText("Include Scripts");

		Combo cinc = new Combo(cmp, SWT.BORDER);
		cinc.setItems(getUCodes());

		new Label(cmp, SWT.NONE).setText("Exclude Scripts");

		Combo cexc = new Combo(cmp, SWT.BORDER);
		cexc.setItems(getUCodes());

		return cmp;
	}

	private static String[] uCodes;

	private static String[] getUCodes() {
		if (uCodes == null) {
			List<String> ucodes = new ArrayList<String>();
			for (int i = 0; i < UScript.CODE_LIMIT; i++) {
				String uc = UScript.getName(i);
				if (!Misc.isNullOrEmpty(uc))
					ucodes.add(UScript.getName(i));
			}
			uCodes = ucodes.toArray(new String[ucodes.size()]);
		}
		return uCodes;
	}
}
