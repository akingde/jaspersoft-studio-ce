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

	private Text tname;
	private Text tport;
	private Text tkeyspace;
	private Text tversion;
	private Text tcluster;
	private Text tuser;
	private Text tpass;

	public DatasourceCassandraCQL3PageContent(ANode parent, MResource resource, DataBindingContext bindingContext) {
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

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelhostname);
		tname = new Text(composite, SWT.BORDER);
		tname.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelport);
		tport = new Text(composite, SWT.BORDER);
		tport.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelkeyspace);
		tkeyspace = new Text(composite, SWT.BORDER);
		tkeyspace.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelcassandraVersion);
		tversion = new Text(composite, SWT.BORDER);
		tversion.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelclustername);
		tcluster = new Text(composite, SWT.BORDER);
		tcluster.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelusername);
		tuser = new Text(composite, SWT.BORDER);
		tuser.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, Messages.CassandraCQL3DataAdapter_labelpassword);
		tpass = new Text(composite, SWT.BORDER);
		tpass.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		rebind();
		return composite;
	}

	@Override
	protected void rebind() {
		ResourceProperty resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCustom.PROP_DATASOURCE_CUSTOM_PROPERTY_MAP, res.getValue().getProperties());

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.HOSTNAME, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tname, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.PORT, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tport, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.KEYSPACE, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tkeyspace, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.CASSANDRAVERSION, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tversion, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.CLUSTERNAME, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tcluster, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.USERNAME, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tuser, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$

		resprop = ResourceDescriptorUtil.getProperty(MRDatasourceCassandraCQL3.PASSWORD, resprop.getProperties());

		bindingContext.bindValue(SWTObservables.observeText(tpass, SWT.Modify), PojoObservables.observeValue(resprop, "value")); //$NON-NLS-1$
	}

	@Override
	public String getHelpContext() {
		return "com.jaspersoft.studio.doc.adapter_cassandracql3";
	}
}
