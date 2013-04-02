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
package com.jaspersoft.studio.server.wizard.resource;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class EditResourcePage extends AWizardPage {
	private APageContent[] rcontent;
	private TabFolder tabFolder;

	public EditResourcePage(APageContent... rcontent) {
		super("editresourcepage");
		this.rcontent = rcontent;
		setTitle("Edit Resource");
		setDescription("Change Jaspersoft Server Resource");
		for (APageContent p : rcontent)
			p.setPage(this);
	}

	public void createControl(Composite parent) {
		tabFolder = new TabFolder(parent, SWT.TOP);
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		DataBindingContext bindingContext = new DataBindingContext();
		WizardPageSupport.create(this, bindingContext);

		for (APageContent pc : rcontent) {
			Control cmp = pc.createContent(tabFolder);
			if (cmp == null)
				continue;
			pc.setBindingContext(bindingContext);

			TabItem item = new TabItem(tabFolder, SWT.NONE);
			item.setText(pc.getName());

			item.setControl(cmp);
		}
		setControl(tabFolder);
	}

	public void setFirstPage(int indx) {
		if (tabFolder.getItemCount() > indx)
			tabFolder.setSelection(indx);
	}

	@Override
	public void dispose() {
		for (APageContent p : rcontent)
			p.dispose();
		super.dispose();
	}
}
