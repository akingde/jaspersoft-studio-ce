package com.jaspersoft.studio.data.hbase;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseThriftDataAdapterEditor implements DataAdapterEditor {
	private HBaseThriftDataAdapterComposite composite;

	@Override
	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage, JasperReportsContext jrContext) {
		if (composite == null)
			composite = new HBaseThriftDataAdapterComposite(parent, style, jrContext);
		return composite;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (dataAdapter instanceof HBaseThriftDataAdapterDescriptor)
			composite.setDataAdapter((HBaseThriftDataAdapterDescriptor) dataAdapter);
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		return composite.getDataAdapter();
	}

	@Override
	public String getHelpContextId() {
		return composite.getHelpContextId();
	}
}