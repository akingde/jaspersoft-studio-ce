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
package com.jaspersoft.studio.data.hive;

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

import com.jaspersoft.hadoop.hive.adapter.HiveDataAdapter;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.hive.messages.Messages;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveDataAdapterComposite extends ADataAdapterComposite {
	private Text urlField;

	private HiveDataAdapterDescriptor dataAdapterDescriptor;

	public HiveDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent, style, jrContext);
		initComponents();
	}

	private void initComponents() {
		setLayout(new GridLayout(2, false));

		Label urlLabel = new Label(this, SWT.NONE);
		urlLabel.setText(Messages.HiveDataAdapterComposite_labelurl);
		urlLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

		urlField = new Text(this, SWT.BORDER);
		urlField.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDescriptor == null) {
			dataAdapterDescriptor = new HiveDataAdapterDescriptor();
		}
		return dataAdapterDescriptor;
	}

	@Override
	public void setDataAdapter(DataAdapterDescriptor dataAdapterDescriptor) {
		super.setDataAdapter(dataAdapterDescriptor);
		this.dataAdapterDescriptor = (HiveDataAdapterDescriptor) dataAdapterDescriptor;
		HiveDataAdapter dataAdapter = (HiveDataAdapter) dataAdapterDescriptor.getDataAdapter();
		bindWidgets(dataAdapter);
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeText(urlField, SWT.Modify), PojoObservables.observeValue(dataAdapter, "url")); //$NON-NLS-1$
	}

	@Override
	public String getHelpContextId() {
		return PREFIX.concat("adapter_hive");
	}
}
