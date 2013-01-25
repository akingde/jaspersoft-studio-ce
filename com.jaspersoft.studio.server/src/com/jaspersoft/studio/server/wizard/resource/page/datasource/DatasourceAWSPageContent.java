/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.server.wizard.resource.page.datasource;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.ANode;
import com.jaspersoft.studio.server.model.MResource;
import com.jaspersoft.studio.utils.UIUtils;

public class DatasourceAWSPageContent extends DatasourceJDBCPageContent {

	public DatasourceAWSPageContent(ANode parent, MResource resource,
			DataBindingContext bindingContext) {
		super(parent, resource, bindingContext);
	}

	public DatasourceAWSPageContent(ANode parent, MResource resource) {
		super(parent, resource);
	}

	@Override
	public String getPageName() {
		return "com.jaspersoft.studio.server.page.datasource.aws";
	}

	public Control createContent(Composite parent) {
		Composite composite = new Composite(parent, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));

		Button ec2Cred = new Button(composite, SWT.RADIO);
		ec2Cred.setText("Use EC2 instance credentials.");
		GridData gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		ec2Cred.setLayoutData(gd);

		Button awsCred = new Button(composite, SWT.RADIO);
		awsCred.setText("Use AWS Credentials (Click here to generate using CloudFormation, see Outputs for keys after stack completes).");
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		awsCred.setLayoutData(gd);

		UIUtils.createLabel(composite, "AWS Access Key");

		Text awsAccessKey = new Text(composite, SWT.BORDER);
		awsAccessKey.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "AWS Secret Key");

		Text awsSecretKey = new Text(composite, SWT.BORDER);
		awsSecretKey.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		UIUtils.createLabel(composite, "ARN");

		Text awsArn = new Text(composite, SWT.BORDER);
		awsArn.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Control c = super.createContent(composite);
		gd = new GridData(GridData.FILL_HORIZONTAL);
		gd.horizontalSpan = 2;
		c.setLayoutData(gd);

		return composite;
	}
}
