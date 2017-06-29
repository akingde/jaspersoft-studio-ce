/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved. http://www.jaspersoft.com.
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.preferences.fonts;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.jaspersoft.studio.messages.Messages;

import net.sf.jasperreports.eclipse.ui.ATitledDialog;
import net.sf.jasperreports.engine.fonts.FontSet;

public class SelectFontSetSetDialog extends ATitledDialog {
	private List<FontSet> sets;
	private FontSet value;

	protected SelectFontSetSetDialog(Shell parentShell, List<FontSet> sets) {
		super(parentShell);
		setTitle(Messages.SelectFontSetSetDialog_0);
		setDefaultSize(300, 400);
		this.sets = sets;
	}

	public FontSet getValue() {
		return value;
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite cmp = (Composite) super.createDialogArea(parent);
		cmp.setLayout(new GridLayout());

		final org.eclipse.swt.widgets.List lst = new org.eclipse.swt.widgets.List(cmp, SWT.SINGLE | SWT.BORDER);
		lst.setLayoutData(new GridData(GridData.FILL_BOTH));
		String[] items = new String[sets.size()];
		for (int i = 0; i < sets.size(); i++)
			items[i] = sets.get(i).getName();
		lst.setItems(items);
		lst.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				value = sets.get(lst.getSelectionIndex());
			}
		});

		return cmp;
	}
}
