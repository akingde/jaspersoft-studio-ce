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
public class HBaseDataAdapterEditor implements DataAdapterEditor {
	private HBaseDataAdapterComposite composite;

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage, JasperReportsContext jrContext) {
		if (composite == null)
			composite = new HBaseDataAdapterComposite(parent, style, jrContext);
		return composite;
	}

	public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
		if (dataAdapter instanceof HBaseDataAdapterDescriptor)
			composite.setDataAdapter((HBaseDataAdapterDescriptor) dataAdapter);
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		return composite.getDataAdapter();
	}

	public String getHelpContextId() {
		return composite.getHelpContextId();
	}
}