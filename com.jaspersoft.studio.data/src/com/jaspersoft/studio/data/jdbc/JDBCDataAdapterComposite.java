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
package com.jaspersoft.studio.data.jdbc;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.swt.widgets.PropertiesComponent;
import com.jaspersoft.studio.utils.Misc;

public class JDBCDataAdapterComposite extends Composite {
	protected JDBCDataAdapterDescriptor dataAdapterDesc = null;
	private Text textJDBCUrl;
	private Text textServerAddress;
	private Text textDatabase;
	private Text textUsername;
	private Text textPassword;

	// private Button btnSavePassword;
	private ComboViewer comboJDBCDriver;

	private JDBCDriverDefinition currentdriver = null;

	private static JDBCDriverDefinition[] definitions = new JDBCDriverDefinition[] {
			new JDBCDriverDefinition("Cloudscape", "COM.cloudscape.JDBCDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:cloudscape:/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("IBM DB2", //$NON-NLS-1$
					"COM.ibm.db2.jdbc.app.DB2Driver", "jdbc:db2:{0}/{1}"), //$NON-NLS-1$ //$NON-NLS-2$
			new JDBCDriverDefinition("inetdae7", "com.inet.tds.TdsDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:inetdae7:{0}:1433/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("Informix", "com.informix.jdbc.IfxDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:informix-sqli://{0}:informixserver={1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("Ingres", "com.ingres.jdbc.IngresDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:ingres://{0}:II7/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("HSQLDB (file)", "org.hsqldb.jdbcDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:hsqldb:[PATH_TO_DB_FILES]/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("HSQLDB (server)", //$NON-NLS-1$
					"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://{0}"), //$NON-NLS-1$ //$NON-NLS-2$
			new JDBCDriverDefinition("JDBC-ODBC Bridge", //$NON-NLS-1$
					"sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:{1}", "DSNAME"), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
			new JDBCDriverDefinition("JDBC-ODBC Bridge", //$NON-NLS-1$
					"com.ms.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:{1}", //$NON-NLS-1$ //$NON-NLS-2$
					"DSNAME"), //$NON-NLS-1$
			new JDBCDriverDefinition("MS SQLServer", //$NON-NLS-1$
					"com.internetcds.jdbc.tds.Driver", //$NON-NLS-1$
					"jdbc:freetds:sqlserver://{0}/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("MS SQLServer (2000)", //$NON-NLS-1$
					"com.microsoft.jdbc.sqlserver.SQLServerDriver", //$NON-NLS-1$
					"jdbc:microsoft:sqlserver://{0}:1433;DatabaseName={1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("MS SQLServer (2005)", //$NON-NLS-1$
					"com.microsoft.sqlserver.jdbc.SQLServerDriver", //$NON-NLS-1$
					"jdbc:sqlserver://{0}:1433;databaseName={1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("MS SQLServer", //$NON-NLS-1$
					"net.sourceforge.jtds.jdbc.Driver", //$NON-NLS-1$
					"jdbc:jtds:sqlserver://{0}/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("MS SQLServer", //$NON-NLS-1$
					"com.merant.datadirect.jdbc.sqlserver.SQLServerDriver", //$NON-NLS-1$
					"jdbc:sqlserver://{0}:1433/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("MySQL", "org.gjt.mm.mysql.Driver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:mysql://{0}/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("MySQL", "com.mysql.jdbc.Driver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:mysql://{0}/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("Oracle", //$NON-NLS-1$
					"oracle.jdbc.driver.OracleDriver", //$NON-NLS-1$
					"jdbc:oracle:thin:@{0}:1521:{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("PostgreSQL", "org.postgresql.Driver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:postgresql://{0}:5432/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("Sybase", //$NON-NLS-1$
					"com.sybase.jdbc2.jdbc.SybDriver", //$NON-NLS-1$
					"jdbc:sybase:Tds:{0}:2638/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition("Vertica", "com.vertica.Driver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:vertica://{0}:5433/{1}") }; //$NON-NLS-1$
	private ClasspathComponent cpath;
	private PropertiesComponent cproperties;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JDBCDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));

		createPreWidgets(this);
		
		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText(Messages.JDBCDataAdapterComposite_driverlabel);

		comboJDBCDriver = new ComboViewer(this, SWT.NONE);
		Combo combo = comboJDBCDriver.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText(Messages.JDBCDataAdapterComposite_urllabel);

		textJDBCUrl = new Text(this, SWT.BORDER);
		textJDBCUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));

		CTabFolder tabFolder = new CTabFolder(this, SWT.NONE);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 3, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		createLocationTab(tabFolder);

		createPropertiesTab(tabFolder);

		createClasspathTab(tabFolder);
		tabFolder.setSelection(0);

		for (int i = 0; i < definitions.length; ++i) {
			comboJDBCDriver.add(definitions[i]);
		}

		comboJDBCDriver
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						if (comboJDBCDriver.getCombo().getSelectionIndex() >= 0) {
							currentdriver = definitions[comboJDBCDriver
									.getCombo().getSelectionIndex()];
							comboJDBCDriver.getCombo().setText(
									currentdriver.getDriverName());
							btnWizardActionPerformed();
						} else {
							currentdriver = null;
						}

					}

				});
	}

	protected void createPreWidgets(Composite parent){
		
	}
	
	private void createClasspathTab(CTabFolder tabFolder) {
		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText(Messages.JDBCDataAdapterComposite_classpath);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		tbtmNewItem_1.setControl(composite);

		cpath = new ClasspathComponent(composite);
		cpath.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	private void createPropertiesTab(CTabFolder tabFolder) {
		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem
				.setText(Messages.JDBCDataAdapterComposite_connectionproperties);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		tbtmNewItem.setControl(composite);

		cproperties = new PropertiesComponent(composite);
		cproperties.getControl()
				.setLayoutData(new GridData(GridData.FILL_BOTH));
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

		Label lblUsername = new Label(composite, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblUsername.setText(Messages.JDBCDataAdapterComposite_username);

		textUsername = new Text(composite, SWT.BORDER);
		textUsername.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		Label lblPassword = new Label(composite, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblPassword.setText(Messages.JDBCDataAdapterComposite_password);

		textPassword = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textPassword.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		// btnSavePassword = new Button(this, SWT.CHECK);
		// btnSavePassword.setText("Save Password");
		// new Label(this, SWT.NONE);

		Label lblAttentionPasswordsAre = new Label(composite, SWT.NONE);
		lblAttentionPasswordsAre.setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 2, 1));
		lblAttentionPasswordsAre
				.setText(Messages.JDBCDataAdapterComposite_attention);

	}

	/**
	 * @param e
	 */
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
	public void setDataAdapter(JDBCDataAdapterDescriptor editingDataAdapter) {
		dataAdapterDesc = editingDataAdapter;

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc
				.getDataAdapter();

		String driverName = Misc.nvl(jdbcDataAdapter.getDriver(), ""); //$NON-NLS-1$
		comboJDBCDriver.getCombo().setText(driverName);

		for (JDBCDriverDefinition d : definitions) {
			if (d.getDriverName().equals(driverName))
				currentdriver = d;
		}

		textUsername.setText(Misc.nvl(jdbcDataAdapter.getUsername(), "")); //$NON-NLS-1$
		textPassword.setText(Misc.nvl(jdbcDataAdapter.getPassword(), "")); //$NON-NLS-1$
		textServerAddress.setText(Misc.nvl(jdbcDataAdapter.getServerAddress(),
				"")); //$NON-NLS-1$
		textDatabase.setText(Misc.nvl(jdbcDataAdapter.getDatabase(), "")); //$NON-NLS-1$
		// btnSavePassword.setSelection(jdbcDataAdapter.isSavePassword());

		textJDBCUrl.setText(Misc.nvl(jdbcDataAdapter.getUrl(), "")); //$NON-NLS-1$

		cpath.setClasspaths(jdbcDataAdapter.getClasspath());
		cproperties.setProperties(jdbcDataAdapter.getProperties());
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null) {
			dataAdapterDesc = new JDBCDataAdapterDescriptor();
		}

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc
				.getDataAdapter();

		jdbcDataAdapter.setDriver(comboJDBCDriver.getCombo().getText());
		jdbcDataAdapter.setUsername(textUsername.getText());
		jdbcDataAdapter.setPassword(textPassword.getText());
		jdbcDataAdapter.setUrl(textJDBCUrl.getText());
		jdbcDataAdapter.setDatabase(textDatabase.getText());
		jdbcDataAdapter.setServerAddress(textServerAddress.getText());
		jdbcDataAdapter.setSavePassword(true);// btnSavePassword.getSelection());

		jdbcDataAdapter.setClasspath(cpath.getClasspaths());
		jdbcDataAdapter.setProperties(cproperties.getProperties());

		return dataAdapterDesc;
	}

	public String getHelpContextId() {
		return ""; //$NON-NLS-1$
	}
}
