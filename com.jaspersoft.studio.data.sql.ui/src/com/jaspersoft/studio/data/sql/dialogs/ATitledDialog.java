/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.sql.dialogs;

import net.sf.jasperreports.eclipse.ui.util.UIUtils;

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
	private int defwidth = -1;
	private int defheight = -1;

	protected ATitledDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected boolean isResizable() {
		return true;
	}

	public void setDefaultSize(int defwidth, int defheight) {
		this.defwidth = defwidth;
		this.defheight = defheight;
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
		if (defwidth > 0 && defheight > 0)
			UIUtils.resizeAndCenterShell(newShell, 680, 750);
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
