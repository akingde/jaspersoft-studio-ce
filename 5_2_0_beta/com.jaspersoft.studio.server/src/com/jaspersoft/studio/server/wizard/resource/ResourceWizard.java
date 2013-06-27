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

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.ResourceFactory;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.MResource;

public class ResourceWizard extends Wizard {
	private boolean skipFirstPage = false;

	public ResourceWizard(ANode parent, MResource resource,
			boolean skipFirstPage) {
		this(parent, resource);
		this.skipFirstPage = skipFirstPage;
	}

	public ResourceWizard(ANode parent, MResource resource) {
		super();
		setWindowTitle(Messages.ResourceWizard_windowtitle);
		this.resource = resource;
		this.parent = parent;
	}

	@Override
	public IWizardPage getStartingPage() {
		IWizardPage[] pages = getPages();
		if (skipFirstPage && pages.length > 1)
			return pages[1];
		else if (pages.length == 1 && pages[0] instanceof EditResourcePage)
			((EditResourcePage) pages[0]).setFirstPage(1);
		return super.getStartingPage();
	}

	private ResourceFactory rfactory = new ResourceFactory();

	@Override
	public void addPages() {
		IWizardPage[] pages = rfactory.getResourcePage(parent, resource);
		for (IWizardPage p : pages)
			addPage(p);
	}

	private ANode parent;

	private MResource resource;

	@Override
	public boolean performFinish() {
		return true;
	}

}
