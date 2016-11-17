/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.find;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class FindResourceWizard extends Wizard {
	private MServerProfile sp;
	private FindResourcePage page0;
	private boolean containedResource = false;

	public FindResourceWizard(MServerProfile sp) {
		this(sp, false);
	}

	public FindResourceWizard(MServerProfile sp, boolean containedResource) {
		super();
		this.containedResource = containedResource;
		setWindowTitle(Messages.FindResourceWizard_0 + sp.getDisplayText());
		setNeedsProgressMonitor(true);
		this.sp = sp;
	}

	private String[] itypes;
	private String[] etypes;
	private String name;

	public void setFilterTypes(String[] in, String[] excl) {
		this.itypes = in;
		this.etypes = excl;
		setFilters();
	}

	public void setDefaultName(String name) {
		this.name = name;
		setFilters();
	}

	private void setFilters() {
		if (page0 != null) {
			page0.setFilterTypes(itypes, etypes);
			page0.setDefaultName(name);
		}
	}

	@Override
	public void addPages() {
		page0 = new FindResourcePage(sp, containedResource);
		setFilters();
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

	public ResourceDescriptor getValue() {
		return page0.getValue();
	}
}
