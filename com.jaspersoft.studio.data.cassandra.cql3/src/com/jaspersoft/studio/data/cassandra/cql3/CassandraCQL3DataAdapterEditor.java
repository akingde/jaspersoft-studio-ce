package com.jaspersoft.studio.data.cassandra.cql3;

import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;

/**
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3DataAdapterEditor implements DataAdapterEditor {

    protected CassandraCQL3DataAdapterComposite composite = null;

	public ADataAdapterComposite getComposite(Composite parent, int style, WizardPage wizardPage, JasperReportsContext jrContext) {
        if (composite == null)
			composite = new CassandraCQL3DataAdapterComposite(parent, style, jrContext);
        return composite;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jaspersoft.studio.data.DataAdapterEditor#getDataAdapter()
     */
    public DataAdapterDescriptor getDataAdapter() {
        return composite.getDataAdapter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.jaspersoft.studio.data.DataAdapterEditor#getHelpContextId()
     */
    public String getHelpContextId() {
        return composite.getHelpContextId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.jaspersoft.studio.data.DataAdapterEditor#setDataAdapter(com.jaspersoft
     * .studio.data.DataAdapter)
     */
    public void setDataAdapter(DataAdapterDescriptor dataAdapter) {
        if (dataAdapter instanceof CassandraCQL3DataAdapterDescriptor)
            composite.setDataAdapter((CassandraCQL3DataAdapterDescriptor) dataAdapter);
    }
}
