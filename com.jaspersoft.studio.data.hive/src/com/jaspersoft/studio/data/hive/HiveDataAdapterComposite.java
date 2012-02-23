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
package com.jaspersoft.studio.data.hive;

import java.util.ArrayList;
import java.util.HashMap;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterComposite;
import com.jaspersoft.studio.data.jdbc.JDBCDriverDefinition;

public class HiveDataAdapterComposite extends JDBCDataAdapterComposite {
	private static final JDBCDriverDefinition[] jdbcDefinitions = new JDBCDriverDefinition[] { new JDBCDriverDefinition(
			"Hadoop Hive", "org.apache.hadoop.hive.jdbc.HiveDriver", //$NON-NLS-1$ //$NON-NLS-2$
			"jdbc:hive://{0}:10000/default", "default", "localhost") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	@Override
	public JDBCDriverDefinition[] getDefinitions() {
		return jdbcDefinitions;
	}

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public HiveDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		comboJDBCDriver.getCombo().setEnabled(false);
	}

	@Override
	protected void createPropertiesTab(CTabFolder tabFolder) {
	}

	@Override
	protected void createClasspathTab(CTabFolder tabFolder) {
	}

	@Override
	protected void createUserPass(Composite composite) {
		Label lbl = new Label(composite, SWT.NONE);
		GridData gd = new GridData();
		gd.heightHint = 150;
		lbl.setLayoutData(gd);
	}

	/**
	 * Set the DataAdapter to edit. The UI will be updated with the content of
	 * this adapter
	 * 
	 * @param dataAdapter
	 */
	public void setDataAdapter(HiveDataAdapterDescriptor editingDataAdapter) {
		super.setDataAdapter(editingDataAdapter);

		btnWizardActionPerformed();
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver"; //$NON-NLS-1$
		comboJDBCDriver.getCombo().setText(driverName);

		for (JDBCDriverDefinition d : definitions) {
			if (d.getDriverName().equals(driverName)) {
				currentdriver = d;
				break;
			}
		}
		bindURLAssistant(dataAdapter);

		bindingContext.bindValue(
				SWTObservables.observeText(textJDBCUrl, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "url"));
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null) {
			dataAdapterDesc = new HiveDataAdapterDescriptor();
		}

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc
				.getDataAdapter();

		jdbcDataAdapter.setDriver(comboJDBCDriver.getCombo().getText());
		jdbcDataAdapter.setUsername("");
		jdbcDataAdapter.setPassword("");
		jdbcDataAdapter.setUrl(textJDBCUrl.getText());

		getDataAdapterURLAssistant(jdbcDataAdapter);

		jdbcDataAdapter.setSavePassword(true);// btnSavePassword.getSelection());

		jdbcDataAdapter.setClasspath(new ArrayList<String>());
		jdbcDataAdapter.setProperties(new HashMap<String, String>());

		return dataAdapterDesc;
	}

}
