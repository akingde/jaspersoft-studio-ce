package com.jaspersoft.studio.server.publish.wizard;

import net.sf.jasperreports.engine.design.JasperDesign;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MReportUnit;

public class ResourcesPage extends WizardPage {
	private JasperDesign jDesign;
	private MReportUnit reportUnit;
	private ANode n;

	protected ResourcesPage(JasperDesign jDesign, ANode n) {
		super("serverrespublish"); //$NON-NLS-1$
		setTitle("Publish To JasperServer");
		setDescription("Select Resources to publish");
		this.jDesign = jDesign;
		this.n = n;
	}

	public void createControl(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		setControl(composite);
		composite.setLayout(new GridLayout(2, false));

		
		// getData from jrContext, show, and check
	}

}
