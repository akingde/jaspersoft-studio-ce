package com.jaspersoft.studio.data.cassandra.cql3;

import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRConstants;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.cassandra.cql3.CassandraCQL3DataSource;
import com.jaspersoft.cassandra.cql3.adapter.CassandraCQL3DataAdapter;
import com.jaspersoft.cassandra.cql3.adapter.CassandraCQL3DataAdapterImpl;
import com.jaspersoft.studio.data.AWizardDataEditorComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.IWizardDataEditorProvider;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.data.ui.WizardQueryEditorComposite;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

/**
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3DataAdapterDescriptor extends DataAdapterDescriptor implements IFieldsProvider, IWizardDataEditorProvider {
	public static final long serialVersionUID = JRConstants.SERIAL_VERSION_UID;
	private IFieldsProvider fieldsProvider;

	@Override
	public CassandraCQL3DataAdapter getDataAdapter() {
		if (dataAdapter == null)
			dataAdapter = new CassandraCQL3DataAdapterImpl();
		return (CassandraCQL3DataAdapter) dataAdapter;
	}

	@Override
	public CassandraCQL3DataAdapterEditor getEditor() {
		return new CassandraCQL3DataAdapterEditor();
	}

	@Override
	public Image getIcon(int size) {
		if (size == 16)
			return Activator.getDefault().getImage("icons/cassandracql3.png");
		return null;
	}

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset reportDataset) throws JRException, UnsupportedOperationException {
		getFieldProvider();
		return fieldsProvider.getFields(con, jConfig, reportDataset);
	}

	private void getFieldProvider() {
		if (fieldsProvider == null)
			fieldsProvider = new CassandraCQL3FieldsProvider();
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		getFieldProvider();
		return fieldsProvider.supportsGetFieldsOperation(jConfig);
	}

	@Override
	public AWizardDataEditorComposite createDataEditorComposite(Composite parent, WizardPage page) {
		return new WizardQueryEditorComposite(parent, page, this, CassandraCQL3DataSource.QUERY_LANGUAGE);
	}
}
