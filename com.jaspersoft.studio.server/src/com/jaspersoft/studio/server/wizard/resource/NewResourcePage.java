/*******************************************************************************
 * Copyright (C) 2010 - 2012 Jaspersoft Corporation. All rights reserved.
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

import org.eclipse.jface.databinding.wizard.WizardPageSupport;
import org.eclipse.swt.widgets.Composite;

public class NewResourcePage extends AWizardPage {
	private APageContent rcontent;

	public NewResourcePage(APageContent rcontent) {
		super(rcontent.getPageName());
		this.rcontent = rcontent;
		rcontent.setPage(this);
		setTitle(rcontent.getName());
		setDescription(rcontent.getName());
	}

	public void createControl(Composite parent) {
		setControl(rcontent.createContent(parent));

		WizardPageSupport.create(this, rcontent.getBindingContext());
	}

	@Override
	public void dispose() {
		rcontent.dispose();
		super.dispose();
	}
}
