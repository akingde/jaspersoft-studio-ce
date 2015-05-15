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
package com.jaspersoft.studio.data.hive;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.engine.JasperReportsContext;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.connectors.hive.adapter.HiveDataAdapter;
import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.hive.messages.Messages;
import com.jaspersoft.studio.data.secret.DataAdaptersSecretsProvider;
import com.jaspersoft.studio.swt.widgets.WSecretText;
import com.jaspersoft.studio.utils.Pair;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveDataAdapterComposite extends ADataAdapterComposite {
	private static final URLDetails CLOUDERA_HIVE_DETAILS = new URLDetails("Hive", "jdbc:hive://localhost:10000/default");
	private static final URLDetails CLOUDERA_IMPALA_DETAILS = new URLDetails("Impala", "jdbc:hive2://localhost:21050/default;auth=noSasl");
	private Text usernameField;
	private WSecretText passwordField;
	private ComboViewer urlComboViewer;
	private Combo urlCombo;

	private HiveDataAdapterDescriptor dataAdapterDescriptor;

	public HiveDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent, style, jrContext);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout(2, false));

		createLabel(Messages.HiveDataAdapterComposite_labelurl);
		urlComboViewer = new ComboViewer(this, SWT.NONE);
		urlCombo = urlComboViewer.getCombo();
		urlCombo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		urlComboViewer.add(CLOUDERA_HIVE_DETAILS);
		urlComboViewer.add(CLOUDERA_IMPALA_DETAILS);
		urlComboViewer.addSelectionChangedListener(new ISelectionChangedListener() {
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				int selectionIndex = urlCombo.getSelectionIndex();
				if(selectionIndex==0){
					urlCombo.setText(CLOUDERA_HIVE_DETAILS.getValue());
				}
				else if(selectionIndex==1){
					urlCombo.setText(CLOUDERA_IMPALA_DETAILS.getValue());
				}
				else {
					// nothing
				}
			}
		});
		
		createLabel(Messages.HiveDataAdapterComposite_labelUsername);
		usernameField = new Text(this, SWT.BORDER);
		usernameField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		createLabel(Messages.HiveDataAdapterComposite_labelPassword);
		passwordField = new WSecretText(this, SWT.BORDER | SWT.PASSWORD);
		passwordField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	private void createLabel(String text) {
		Label urlLabel = new Label(this, SWT.NONE);
		urlLabel.setText(text);
		urlLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
	}
	
	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDescriptor == null)
			dataAdapterDescriptor = new HiveDataAdapterDescriptor();
		return dataAdapterDescriptor;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDescriptor) {
		super.setDataAdapter(dataAdapterDescriptor);
		this.dataAdapterDescriptor = (HiveDataAdapterDescriptor) dataAdapterDescriptor;
		if (!passwordField.isWidgetConfigured()) {
			passwordField.loadSecret(DataAdaptersSecretsProvider.SECRET_NODE_ID, passwordField.getText());
		}
		bindWidgets(dataAdapterDescriptor.getDataAdapter());
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeText(urlCombo), PojoObservables.observeValue(dataAdapter, "url")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(usernameField, SWT.Modify), PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(passwordField, SWT.Modify), PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
	}
	
	@Override
	public void performAdditionalUpdates() {
		if (JaspersoftStudioPlugin.shouldUseSecureStorage()) {
			passwordField.persistSecret();
			// update the "password" replacing it with the UUID key saved in secure
			// preferences
			HiveDataAdapter hiveDataAdapter = (HiveDataAdapter) dataAdapterDesc.getDataAdapter();
			hiveDataAdapter.setPassword(passwordField.getUUIDKey());
		}
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_hive"); //$NON-NLS-1$
	}
	
	private static class URLDetails extends Pair<String, String> {

		public URLDetails(String key, String value) {
			super(key, value);
		}
		
		@Override
		public String toString() {
			return getKey() + "( " + getValue() + ")";
		}
	}
}
