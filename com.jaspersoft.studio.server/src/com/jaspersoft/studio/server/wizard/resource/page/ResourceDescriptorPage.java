package com.jaspersoft.studio.server.wizard.resource.page;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.server.model.MResource;

public class ResourceDescriptorPage extends WizardPage {
	private MResource resource;

	public ResourceDescriptorPage() {
		super("resource");
		setTitle("Resource");
		setDescription("JasperServer resource");
	}

	public void createControl(Composite parent) {
		setControl(new Button(parent, SWT.ARROW_DOWN));
	}
}
