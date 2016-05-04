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
import java.util.Arrays;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.lang.UScript;
import com.jaspersoft.studio.messages.Messages;

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
		setTitle(Messages.FontSetFamilyDialog_0);
		setDefaultSize(400, 400);
		this.fs = fs;
		this.fsf = fsf;
	}

	public SimpleFontSetFamily getValue() {
		return fsf;
	}

	@Override
	public boolean close() {
		fsf.setIncludedScripts(incl.getItemCount() > 0 ? Arrays.asList(incl.getItems()) : null);
		fsf.setExcludedScripts(excl.getItemCount() > 0 ? Arrays.asList(excl.getItems()) : null);
		return super.close();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout(2, false));

		new Label(cmp, SWT.NONE).setText(Messages.FontSetFamilyDialog_1);

		final Text txt = new Text(cmp, SWT.BORDER | SWT.READ_ONLY);
		txt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		txt.setText(Misc.nvl(fsf.getFamilyName()));

		Button bPrimary = new Button(cmp, SWT.CHECK);
		bPrimary.setText(Messages.FontSetFamilyDialog_2);
		bPrimary.setToolTipText(Messages.FontSetFamilyDialog_3);
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

		incl = createFilter(cmp, Messages.FontSetFamilyDialog_4, Messages.FontSetFamilyDialog_5, fsf.getIncludedScripts());

		excl = createFilter(cmp, Messages.FontSetFamilyDialog_6, Messages.FontSetFamilyDialog_7, fsf.getExcludedScripts());

		return cmp;
	}

	private org.eclipse.swt.widgets.List createFilter(Composite cmp, String label, String toolTip, List<String> scripts) {
		Label lbl = new Label(cmp, SWT.NONE);
		lbl.setText(label);
		lbl.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING));
		lbl.setToolTipText(toolTip);

		Composite c = new Composite(cmp, SWT.NONE);
		c.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		GridLayout l = new GridLayout(2, false);
		l.marginWidth = 0;
		l.marginHeight = 0;
		l.verticalSpacing = 0;
		c.setLayout(l);

		final org.eclipse.swt.widgets.List lst = new org.eclipse.swt.widgets.List(c,
				SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		lst.setToolTipText(toolTip);
		GridData gd = new GridData();
		gd.heightHint = 70;
		gd.widthHint = 150;
		gd.verticalSpan = 2;
		lst.setLayoutData(gd);
		if (!Misc.isNullOrEmpty(scripts))
			lst.setItems(scripts.toArray(new String[scripts.size()]));

		final Button bAdd = new Button(c, SWT.PUSH);
		bAdd.setText(Messages.common_add);
		gd = new GridData();
		gd.widthHint = 70;
		bAdd.setLayoutData(gd);
		bAdd.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				UScriptDialog d = new UScriptDialog(bAdd.getShell());
				if (d.open() == Dialog.OK) {
					String[] vals = d.getValues();
					if (!Misc.isNullOrEmpty(vals)) {
						String[] items = lst.getItems();
						List<String> all = new ArrayList<String>(Arrays.asList(items));
						for (String v : vals)
							if (!all.contains(v))
								all.add(v);
						lst.setItems(all.toArray(new String[all.size()]));
					}
				}
			}
		});

		final Button bDel = new Button(c, SWT.PUSH);
		bDel.setText(Messages.common_delete);
		gd = new GridData(GridData.VERTICAL_ALIGN_BEGINNING);
		gd.widthHint = 70;
		bDel.setLayoutData(gd);
		bDel.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				lst.remove(lst.getSelectionIndices());
			}
		});

		lst.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				bDel.setEnabled(lst.getSelectionCount() > 0);
			}
		});
		bDel.setEnabled(lst.getSelectionCount() > 0);
		return lst;
	}

	class UScriptDialog extends ATitledDialog {
		private String[] values;

		public String[] getValues() {
			return values;
		}

		protected UScriptDialog(Shell parentShell) {
			super(parentShell);
			setTitle(Messages.FontSetFamilyDialog_8);
			setDefaultSize(400, 400);
		}

		@Override
		protected Control createDialogArea(Composite parent) {
			Composite c = (Composite) super.createDialogArea(parent);

			final org.eclipse.swt.widgets.List lst = new org.eclipse.swt.widgets.List(c,
					SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
			lst.setLayoutData(new GridData(GridData.FILL_BOTH));
			lst.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					values = lst.getSelection();
				}
			});
			lst.setItems(getUCodes());

			return super.createDialogArea(parent);
		}

	}

	private static String[] uCodes;
	private org.eclipse.swt.widgets.List incl;
	private org.eclipse.swt.widgets.List excl;

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
