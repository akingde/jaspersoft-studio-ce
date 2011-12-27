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

import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDriverDefinition;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.utils.Misc;

public class HiveDataAdapterComposite extends Composite {
	protected HiveDataAdapterDescriptor dataAdapterDesc = null;
	private Text textJDBCUrl;
	private Text textServerAddress;
	private Text textDatabase;
	private ComboViewer comboJDBCDriver;

	private JDBCDriverDefinition currentdriver = null;
	public final static JDBCDriverDefinition[] definitions = new JDBCDriverDefinition[] { new JDBCDriverDefinition(
			"Hadoop Hive", "org.apache.hadoop.hive.jdbc.HiveDriver", //$NON-NLS-1$ //$NON-NLS-2$
			"jdbc:hive://{0}:10000/default", "default", "localhost") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public HiveDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText(Messages.JDBCDataAdapterComposite_driverlabel);

		comboJDBCDriver = new ComboViewer(this, SWT.NONE);
		Combo combo = comboJDBCDriver.getCombo();
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setText(Messages.JDBCDataAdapterComposite_urllabel);

		textJDBCUrl = new Text(this, SWT.BORDER);
		textJDBCUrl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		CTabFolder tabFolder = new CTabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 3, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		createLocationTab(tabFolder);
		tabFolder.setSelection(0);

		for (int i = 0; i < definitions.length; ++i) {
			comboJDBCDriver.add(definitions[i]);
		}

		ISelectionChangedListener listener = new ISelectionChangedListener() {

			public void selectionChanged(SelectionChangedEvent event) {
				if (comboJDBCDriver.getCombo().getSelectionIndex() >= 0) {
					currentdriver = definitions[comboJDBCDriver.getCombo()
							.getSelectionIndex()];
					comboJDBCDriver.getCombo().setText(
							currentdriver.getDriverName());
					btnWizardActionPerformed();
				} else {
					currentdriver = null;
				}

			}

		};
		comboJDBCDriver.addSelectionChangedListener(listener);

		comboJDBCDriver.setSelection(new StructuredSelection(definitions[0]));
		listener.selectionChanged(null);
	}

	private void createLocationTab(CTabFolder tabFolder) {
		CTabItem tbtmNewItem_2 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2
				.setText(Messages.JDBCDataAdapterComposite_databaselocation);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		tbtmNewItem_2.setControl(composite);

		Label lblServerAddress = new Label(composite, SWT.NONE);
		lblServerAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblServerAddress
				.setText(Messages.JDBCDataAdapterComposite_serveraddress);

		textServerAddress = new Text(composite, SWT.BORDER);
		textServerAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));

		Label lblDatabase = new Label(composite, SWT.NONE);
		lblDatabase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblDatabase.setText(Messages.JDBCDataAdapterComposite_database);

		textDatabase = new Text(composite, SWT.BORDER);
		textDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		textServerAddress.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				btnWizardActionPerformed();
			}
		});
		textDatabase.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				btnWizardActionPerformed();
			}
		});

	}

	protected void btnWizardActionPerformed() {
		if (currentdriver != null) {
			textJDBCUrl.setText(currentdriver.getUrl(
					textServerAddress.getText(), textDatabase.getText()));
		}
	}

	/**
	 * Set the DataAdapter to edit. The UI will be updated with the content of
	 * this adapter
	 * 
	 * @param dataAdapter
	 */
	public void setDataAdapter(HiveDataAdapterDescriptor editingDataAdapter) {
		dataAdapterDesc = editingDataAdapter;

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc
				.getDataAdapter();

		String driverName = "org.apache.hadoop.hive.jdbc.HiveDriver"; //$NON-NLS-1$
		comboJDBCDriver.getCombo().setText(driverName);

		for (JDBCDriverDefinition d : definitions) {
			if (d.getDriverName().equals(driverName)) {
				currentdriver = d;
				break;
			}
		}

		textServerAddress.setText(Misc.nvl(jdbcDataAdapter.getServerAddress(),
				"")); //$NON-NLS-1$
		textDatabase.setText(Misc.nvl(jdbcDataAdapter.getDatabase(), "")); //$NON-NLS-1$ 

		textJDBCUrl.setText(Misc.nvl(jdbcDataAdapter.getUrl(), "")); //$NON-NLS-1$

		btnWizardActionPerformed();
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
		jdbcDataAdapter.setDatabase(textDatabase.getText());
		jdbcDataAdapter.setServerAddress(textServerAddress.getText());
		jdbcDataAdapter.setSavePassword(true);// btnSavePassword.getSelection());

		jdbcDataAdapter.setClasspath(new ArrayList<String>());
		jdbcDataAdapter.setProperties(new HashMap<String, String>());

		return dataAdapterDesc;
	}

	public String getHelpContextId() {
		return ""; //$NON-NLS-1$
	}
}
