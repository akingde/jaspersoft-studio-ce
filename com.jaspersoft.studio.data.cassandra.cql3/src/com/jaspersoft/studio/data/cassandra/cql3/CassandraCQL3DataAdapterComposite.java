package com.jaspersoft.studio.data.cassandra.cql3;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.conversion.NumberToStringConverter;
import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.ibm.icu.text.NumberFormat;
import com.jaspersoft.cassandra.cql3.adapter.CassandraCQL3DataAdapter;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.cassandra.cql3.messages.Messages;

/**
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3DataAdapterComposite extends ADataAdapterComposite {
	private Text hostname;
	private Text port;
	private Text keyspace;
	private Text cassandraVersion;
	private Text clustername;

	private CassandraCQL3DataAdapterDescriptor dataAdapterDescriptor;

	public CassandraCQL3DataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent, style, jrContext);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout(2, false));

		createLabel(Messages.CassandraCQL3DataAdapter_labelhostname);
		hostname = createTextField(false);
		createLabel(Messages.CassandraCQL3DataAdapter_labelport);
		port = createTextField(false);
		createLabel(Messages.CassandraCQL3DataAdapter_labelkeyspace);
		keyspace = createTextField(false);
		createLabel(Messages.CassandraCQL3DataAdapter_labelcassandraVersion);
		cassandraVersion = createTextField(false);
		createLabel(Messages.CassandraCQL3DataAdapter_labelclustername);
		clustername = createTextField(false);
	}

	private void createLabel(String text) {
		Label label = new Label(this, SWT.NONE);
		label.setText(text);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	}

	private Text createTextField(boolean password) {
		Text textField = new Text(this, !password ? SWT.BORDER : SWT.BORDER | SWT.PASSWORD);
		textField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		return textField;
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDescriptor == null) {
			dataAdapterDescriptor = new CassandraCQL3DataAdapterDescriptor();
		}
		return dataAdapterDescriptor;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDescriptor) {
		super.setDataAdapter(dataAdapterDescriptor);

		this.dataAdapterDescriptor = (CassandraCQL3DataAdapterDescriptor) dataAdapterDescriptor;
		CassandraCQL3DataAdapter dataAdapter = (CassandraCQL3DataAdapter) dataAdapterDescriptor.getDataAdapter();
		bindWidgets(dataAdapter);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeText(hostname, SWT.Modify), PojoObservables.observeValue(dataAdapter, "hostname")); //$NON-NLS-1$
		IValidator validator = new IValidator() {
			public IStatus validate(Object value) {
				String stringValue = (String) value;
				try {
					new Integer(stringValue).intValue();
					return Status.OK_STATUS;
				} catch (NumberFormatException ex) {
					return ValidationStatus.error(Messages.CassandraCQL3DataAdapterComposite_NotIntegerError);
				}
			}
		};
		NumberFormat numberFormat = NumberFormat.getIntegerInstance();
		numberFormat.setGroupingUsed(false);
		IConverter targetToModelConverter = StringToNumberConverter.toInteger(numberFormat, true);
		IConverter modelToTargetConverter = NumberToStringConverter.fromInteger(numberFormat, true);
		bindingContext.bindValue(SWTObservables.observeText(port, SWT.Modify), PojoObservables.observeValue(dataAdapter, "port"), //$NON-NLS-1$
				new UpdateValueStrategy().setAfterGetValidator(validator).setConverter(targetToModelConverter), new UpdateValueStrategy().setConverter(modelToTargetConverter));
		bindingContext.bindValue(SWTObservables.observeText(keyspace, SWT.Modify), PojoObservables.observeValue(dataAdapter, "keyspace")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(cassandraVersion, SWT.Modify), PojoObservables.observeValue(dataAdapter, "cassandraVersion")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(clustername, SWT.Modify), PojoObservables.observeValue(dataAdapter, "clustername")); //$NON-NLS-1$
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_cassandracql3"); //$NON-NLS-1$
	}
}
