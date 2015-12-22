/*******************************************************************************
 * Copyright (C) 2005 - 2014 TIBCO Software Inc. All rights reserved.
 * http://www.jaspersoft.com.
 * 
 * Unless you have purchased  a commercial license agreement from Jaspersoft,
 * the following license terms  apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import java.util.List;

import org.eclipse.core.databinding.Binding;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceDescriptor;
import com.jaspersoft.jasperserver.api.metadata.xml.domain.impl.ResourceProperty;
import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.AMResource;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceAWS;
import com.jaspersoft.studio.server.utils.ResourceDescriptorUtil;
import com.jaspersoft.studio.utils.UIUtil;

public class DatasourceAWSPageContent extends DatasourceJDBCPageContent {

	private Text awsAccessKey;
	private Text awsSecretKey;
	private Text awsArn;
	private Binding bAccessKey;
	private Binding bSecretKey;
	private Binding bArn;
	private Button ec2Cred;
	private Button awsCred;
	private Proxy p;
	private Text awsRegion;
	private Text awsService;
	private Text awsDBInstance;
	private Text awsDSDBName;

	public DatasourceAWSPageContent(ANode parent, AMResource resource, DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DatasourceAWSPageContent(ANode parent, AMResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.datasource.aws";
	}

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		ec2Cred = new Button(composite, SWT.RADIO);
		ec2Cred.setText("Use EC2 instance credentials.");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec2Cred.setLayoutData(gd);
		ec2Cred.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (ec2Cred.getSelection()) {
					ResourceDescriptor rd = res.getValue();
					rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_ACCESS_KEY, null);
					rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_SECRET_KEY, null);
					rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_ROLE_ARN, null);

					unbindAWS();
					setEC2Settings(true);
				}
			}
		});

		awsCred = new Button(composite, SWT.RADIO);
		awsCred.setText(
				"Use AWS Credentials (Click here to generate using CloudFormation, see Outputs for keys after stack completes).");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		awsCred.setLayoutData(gd);
		awsCred.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				if (awsCred.getSelection()) {
					bindAWS();
					setEC2Settings(false);
				}
			}
		});

		UIUtil.createLabel(composite, "AWS Access Key");

		awsAccessKey = new Text(composite, SWT.BORDER);
		awsAccessKey.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, "AWS Secret Key");

		awsSecretKey = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		awsSecretKey.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, "ARN");

		awsArn = new Text(composite, SWT.BORDER);
		awsArn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, "AWS Region");

		awsRegion = new Text(composite, SWT.BORDER);
		awsRegion.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, "AWS Datasource");

		awsService = new Text(composite, SWT.BORDER);
		awsService.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, "AWS Instance DB Identifier");

		awsDBInstance = new Text(composite, SWT.BORDER);
		awsDBInstance.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtil.createLabel(composite, "Data Source DB Name");

		awsDSDBName = new Text(composite, SWT.BORDER);
		awsDSDBName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control c = super.createContent(composite, false, false);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);

		rebind();

		return composite;
	}

	@Override
	protected void rebind() {
		super.rebind();
		ResourceDescriptor rd = res.getValue();

		p = new Proxy(rd);

		List<ResourceProperty> props = rd.getProperties();
		boolean ec2 = ResourceDescriptorUtil.getProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_ACCESS_KEY, props) == null;
		setEC2Settings(ec2);
		if (!ec2)
			bindAWS();

		bindingContext.bindValue(SWTObservables.observeText(awsRegion, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsRegion")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(awsService, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsDbService")); // $NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(awsDBInstance, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsDbInstanceIdentifier")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(awsDSDBName, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsDbName")); //$NON-NLS-1$
	}

	private void setEC2Settings(boolean ec2) {
		ec2Cred.setSelection(ec2);

		awsCred.setSelection(!ec2);
		awsAccessKey.setEnabled(!ec2);
		awsSecretKey.setEnabled(!ec2);
		awsArn.setEnabled(!ec2);
	}

	@SuppressWarnings("deprecation")
	private void bindAWS() {
		bAccessKey = bindingContext.bindValue(SWTObservables.observeText(awsAccessKey, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsAccessKey"));
		bSecretKey = bindingContext.bindValue(SWTObservables.observeText(awsSecretKey, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsSecretKey"));
		bArn = bindingContext.bindValue(SWTObservables.observeText(awsArn, SWT.Modify),
				PojoObservables.observeValue(p, "datasourceAwsRoleArn"));
	}

	private void unbindAWS() {
		if (bAccessKey != null)
			bindingContext.removeBinding(bAccessKey);
		if (bSecretKey != null)
			bindingContext.removeBinding(bSecretKey);
		if (bArn != null)
			bindingContext.removeBinding(bArn);
	}

	protected class Proxy {
		private ResourceDescriptor rd;

		public Proxy(ResourceDescriptor rd) {
			this.rd = rd;
		}

		public void setDatasourceAwsRegion(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_REGION, v);
		}

		public String getDatasourceAwsRegion() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_REGION);
		}

		public void setDatasourceAwsDbService(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_SERVICE, v);
		}

		public String getDatasourceAwsDbService() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_SERVICE);
		}

		public void setDatasourceAwsDbInstanceIdentifier(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_INSTANCE_IDENTIFIER, v);
		}

		public String getDatasourceAwsDbInstanceIdentifier() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_INSTANCE_IDENTIFIER);
		}

		public void setDatasourceAwsDbName(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_NAME, v);
		}

		public String getDatasourceAwsDbName() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_DB_NAME);
		}

		public void setDatasourceAwsAccessKey(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_ACCESS_KEY, v);
		}

		public String getDatasourceAwsAccessKey() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_ACCESS_KEY);
		}

		public void setDatasourceAwsSecretKey(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_SECRET_KEY, v);
		}

		public String getDatasourceAwsSecretKey() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_SECRET_KEY);
		}

		public void setDatasourceAwsRoleArn(String v) {
			rd.setResourceProperty(MRDatasourceAWS.PROP_DATASOURCE_AWS_ROLE_ARN, v);
		}

		public String getDatasourceAwsRoleArn() {
			return rd.getResourcePropertyValue(MRDatasourceAWS.PROP_DATASOURCE_AWS_ROLE_ARN);
		}
	}
}
