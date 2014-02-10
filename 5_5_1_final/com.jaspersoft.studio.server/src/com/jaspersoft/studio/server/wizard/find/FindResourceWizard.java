package com.jaspersoft.studio.server.wizard.find;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.studio.server.model.server.MServerProfile;

public class FindResourceWizard extends Wizard {
	private MServerProfile sp;
	private FindResourcePage page0;

	public FindResourceWizard(MServerProfile sp) {
		super();
		setWindowTitle("Find resource from " + sp.getDisplayText());
		setNeedsProgressMonitor(true);
		this.sp = sp;
	}

	private String[] itypes;
	private String[] etypes;

	public void setFilterTypes(String[] in, String[] excl) {
		this.itypes = in;
		this.etypes = excl;
		setFilters();
	}

	private void setFilters() {
		if (page0 != null)
			page0.setFilterTypes(itypes, etypes);
	}

	@Override
	public void addPages() {
		page0 = new FindResourcePage(sp);
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
