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
/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.cassandra;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.connectors.cassandra.adapter.CassandraDataAdapter;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.secret.DataAdaptersSecretsProvider;
import com.jaspersoft.studio.swt.widgets.WSecretText;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class CassandraDataAdapterComposite extends ADataAdapterComposite {
	private Text jdbcURL;
	private Text username;
	private WSecretText password;

	private CassandraDataAdapterDescriptor dataAdapterDescriptor;

	public CassandraDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent, style, jrContext);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout(2, false));

		createLabel("JDBC URL:");
		jdbcURL = createTextField();
		
		createLabel("Username:");
		username = createTextField();
		
		createLabel("Password:");
		password = createPasswordField();
	}

	private void createLabel(String text) {
		Label label = new Label(this, SWT.NONE);
		label.setText(text);
		label.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1,
				1));
	}

	private Text createTextField() {
		Text textField = new Text(this, SWT.BORDER);
		textField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		return textField;
	}
	
	private WSecretText createPasswordField() {
		WSecretText passwd = new WSecretText(this, SWT.BORDER | SWT.PASSWORD);
		passwd.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				1, 1));
		return passwd;
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDescriptor == null) {
			dataAdapterDescriptor = new CassandraDataAdapterDescriptor();
		}
		CassandraDataAdapter cassandraDA = (CassandraDataAdapter) dataAdapterDesc.getDataAdapter();
		cassandraDA.setUsername(username.getText());
		cassandraDA.setPassword(password.getText());
		cassandraDA.setUrl(jdbcURL.getText());
		
		return dataAdapterDescriptor;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDescriptor) {
		super.setDataAdapter(dataAdapterDescriptor);

		if (!password.isWidgetConfigured()) {
			password.loadSecret(DataAdaptersSecretsProvider.SECRET_NODE_ID, password.getText());
		}
		
		this.dataAdapterDescriptor = (CassandraDataAdapterDescriptor) dataAdapterDescriptor;
//		CassandraDataAdapter dataAdapter = (CassandraDataAdapter) dataAdapterDescriptor
//				.getDataAdapter();
//		bindWidgets(dataAdapter);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(
				SWTObservables.observeText(jdbcURL, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "url")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(username, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(password, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_cassandra");
	}
	
	@Override
	public void performAdditionalUpdates() {
		if (JaspersoftStudioPlugin.shouldUseSecureStorage()) {
			password.persistSecret();
			// update the "password" replacing it with the UUID key saved in secure
			// preferences
			CassandraDataAdapter cassandraDA = (CassandraDataAdapter) dataAdapterDesc.getDataAdapter();
			cassandraDA.setPassword(password.getUUIDKey());
		}
	}
}
