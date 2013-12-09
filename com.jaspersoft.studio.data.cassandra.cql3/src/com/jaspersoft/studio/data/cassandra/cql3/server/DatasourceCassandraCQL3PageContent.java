package com.jaspersoft.studio.data.cassandra.cql3.server;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.data.cassandra.cql3.messages.Messages;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceCustom;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.server.wizard.resource.APageContent;
import com.jaspersoft.studio.utils.UIUtil;

public class DatasourceCassandraCQL3PageContent extends APageContent {

	public DatasourceCassandraCQL3PageContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DatasourceCassandraCQL3PageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.datasource.cassandra.cql3";
	}

	@Override
	public String getName() {
		return Messages.RDDatasourceCassandraCQL3Page_title;
	}

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		UIUtil.createLabel(composite,
				Messages.CassandraCQL3DataAdapterComposite_labelhostname);
		UIUtil.createLabel(composite,
				Messages.CassandraCQL3DataAdapterComposite_labelport);
		UIUtil.createLabel(composite,
				Messages.CassandraCQL3DataAdapterComposite_labelkeyspace);
		UIUtil.createLabel(composite,
				Messages.CassandraCQL3DataAdapterComposite_labelcassandraVersion);
		UIUtil.createLabel(composite,
				Messages.CassandraCQL3DataAdapterComposite_labelclustername);

		Text tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		ResourceProperty resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_PROPERTY_MAP, res
						.getValue().getProperties());

		resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCassandraCQL3.HOSTNAME, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$
		
		resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCassandraCQL3.PORT, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$
		
		resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCassandraCQL3.KEYSPACE, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$
		
		resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCassandraCQL3.CASSANDRAVERSION, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$
		
		resprop = ResourceDescriptorUtil.getProperty(
				MRDatasourceCassandraCQL3.CLUSTERNAME, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify),
				PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		return composite;
	}
	
	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.adapter_cassandracql3";
	}
}
