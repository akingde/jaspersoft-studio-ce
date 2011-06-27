/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.jdbc;

import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;

import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.utils.Misc;

public class JDBCDataAdapterComposite extends Composite {
	private JDBCDataAdapterDescriptor dataAdapterDesc = null;
	private Text textJDBCUrl;
	private Text textServerAddress;
	private Text textDatabase;
	private Text textUsername;
	private Text textPassword;

	private Button btnSavePassword;
	private Button btnWizard;
	private ComboViewer comboJDBCDriver;

	private JDBCDriverDefinition currentdriver = null;

	private static JDBCDriverDefinition[] definitions = new JDBCDriverDefinition[] {
			new JDBCDriverDefinition("Cloudscape", "COM.cloudscape.JDBCDriver",
					"jdbc:cloudscape:/{1}"),
			new JDBCDriverDefinition("IBM DB2",
					"COM.ibm.db2.jdbc.app.DB2Driver", "jdbc:db2:{0}/{1}"),
			new JDBCDriverDefinition("inetdae7", "com.inet.tds.TdsDriver",
					"jdbc:inetdae7:{0}:1433/{1}"),
			new JDBCDriverDefinition("Informix", "com.informix.jdbc.IfxDriver",
					"jdbc:informix-sqli://{0}:informixserver={1}"),
			new JDBCDriverDefinition("Ingres", "com.ingres.jdbc.IngresDriver",
					"jdbc:ingres://{0}:II7/{1}"),
			new JDBCDriverDefinition("HSQLDB (file)", "org.hsqldb.jdbcDriver",
					"jdbc:hsqldb:[PATH_TO_DB_FILES]/{1}"),
			new JDBCDriverDefinition("HSQLDB (server)",
					"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://{0}"),
			new JDBCDriverDefinition("JDBC-ODBC Bridge",
					"sun.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:{1}", "DSNAME"),
			new JDBCDriverDefinition("JDBC-ODBC Bridge",
					"com.ms.jdbc.odbc.JdbcOdbcDriver", "jdbc:odbc:{1}",
					"DSNAME"),
			new JDBCDriverDefinition("MS SQLServer",
					"com.internetcds.jdbc.tds.Driver",
					"jdbc:freetds:sqlserver://{0}/{1}"),
			new JDBCDriverDefinition("MS SQLServer (2000)",
					"com.microsoft.jdbc.sqlserver.SQLServerDriver",
					"jdbc:microsoft:sqlserver://{0}:1433;DatabaseName={1}"),
			new JDBCDriverDefinition("MS SQLServer (2005)",
					"com.microsoft.sqlserver.jdbc.SQLServerDriver",
					"jdbc:sqlserver://{0}:1433;databaseName={1}"),
			new JDBCDriverDefinition("MS SQLServer",
					"net.sourceforge.jtds.jdbc.Driver",
					"jdbc:jtds:sqlserver://{0}/{1}"),
			new JDBCDriverDefinition("MS SQLServer",
					"com.merant.datadirect.jdbc.sqlserver.SQLServerDriver",
					"jdbc:sqlserver://{0}:1433/{1}"),
			new JDBCDriverDefinition("MySQL", "org.gjt.mm.mysql.Driver",
					"jdbc:mysql://{0}/{1}"),
			new JDBCDriverDefinition("MySQL", "com.mysql.jdbc.Driver",
					"jdbc:mysql://{0}/{1}"),
			new JDBCDriverDefinition("Oracle",
					"oracle.jdbc.driver.OracleDriver",
					"jdbc:oracle:thin:@{0}:1521:{1}"),
			new JDBCDriverDefinition("PostgreSQL", "org.postgresql.Driver",
					"jdbc:postgresql://{0}:5432/{1}"),
			new JDBCDriverDefinition("Sybase",
					"com.sybase.jdbc2.jdbc.SybDriver",
					"jdbc:sybase:Tds:{0}:2638/{1}"),
			new JDBCDriverDefinition("Vertica", "com.vertica.Driver",
					"jdbc:vertica://{0}:5433/{1}") };
	private ClasspathComponent cpath;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JDBCDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(3, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel.setText("JDBC Driver");

		comboJDBCDriver = new ComboViewer(this, SWT.NONE);
		Combo combo = comboJDBCDriver.getCombo();
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 2,
				1));

		Label lblNewLabel_1 = new Label(this, SWT.NONE);
		lblNewLabel_1.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblNewLabel_1.setText("JDBC Url");

		textJDBCUrl = new Text(this, SWT.BORDER);
		textJDBCUrl.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 2, 1));

		Group grpJdbc = new Group(this, SWT.NONE);
		grpJdbc.setLayout(new GridLayout(3, false));
		grpJdbc.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false,
				3, 1));
		grpJdbc.setText("JDBC URL Wizard");

		Label lblServerAddress = new Label(grpJdbc, SWT.NONE);
		lblServerAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
				false, false, 1, 1));
		lblServerAddress.setText("Server Address");

		textServerAddress = new Text(grpJdbc, SWT.BORDER);
		textServerAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
				true, false, 1, 1));
		new Label(grpJdbc, SWT.NONE);

		Label lblDatabase = new Label(grpJdbc, SWT.NONE);
		lblDatabase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblDatabase.setText("Database");

		textDatabase = new Text(grpJdbc, SWT.BORDER);
		textDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
				false, 1, 1));

		btnWizard = new Button(grpJdbc, SWT.NONE);
		btnWizard.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				btnWizardActionPerformed(e);

			}
		});
		btnWizard.setText("Wizard");

		Label lblUsername = new Label(this, SWT.NONE);
		lblUsername.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblUsername.setText("Username");

		textUsername = new Text(this, SWT.BORDER);
		GridData gd_textUsername = new GridData(SWT.FILL, SWT.CENTER, false,
				false, 1, 1);
		gd_textUsername.widthHint = 200;
		textUsername.setLayoutData(gd_textUsername);
		new Label(this, SWT.NONE);

		Label lblPassword = new Label(this, SWT.NONE);
		lblPassword.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
				false, 1, 1));
		lblPassword.setText("Password");

		textPassword = new Text(this, SWT.BORDER | SWT.PASSWORD);
		GridData gd_textPassword = new GridData(SWT.LEFT, SWT.CENTER, false,
				false, 1, 1);
		gd_textPassword.widthHint = 200;
		textPassword.setLayoutData(gd_textPassword);

		btnSavePassword = new Button(this, SWT.CHECK);
		btnSavePassword.setText("Save Password");
		new Label(this, SWT.NONE);

		Label lblAttentionPasswordsAre = new Label(this, SWT.NONE);
		lblAttentionPasswordsAre.setLayoutData(new GridData(SWT.LEFT,
				SWT.CENTER, false, false, 2, 1));
		lblAttentionPasswordsAre
				.setText("Attention! Passwords are saved in clear text");

		cpath = new ClasspathComponent(this);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.horizontalSpan = 3;
		cpath.getControl().setLayoutData(gd);

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
							btnWizard.setEnabled(true);
							btnWizardActionPerformed(null);
						} else {
							btnWizard.setEnabled(false);
							currentdriver = null;
						}

					}

				});
	}

	/**
	 * @param e
	 */
	protected void btnWizardActionPerformed(SelectionEvent e) {
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

		String driverName = Misc.nvl(jdbcDataAdapter.getDriver(), "");
		comboJDBCDriver.getCombo().setText(driverName);

		for (JDBCDriverDefinition d : definitions) {
			if (d.getDriverName().equals(driverName))
				currentdriver = d;
		}

		textUsername.setText(Misc.nvl(jdbcDataAdapter.getUsername(), ""));
		textPassword.setText(Misc.nvl(jdbcDataAdapter.getPassword(), ""));
		textJDBCUrl.setText(Misc.nvl(jdbcDataAdapter.getUrl(), ""));
		textServerAddress.setText(Misc.nvl(jdbcDataAdapter.getServerAddress(),
				""));
		textDatabase.setText(Misc.nvl(jdbcDataAdapter.getDatabase(), ""));
		btnSavePassword.setSelection(jdbcDataAdapter.isSavePassword());

		cpath.setClasspaths(jdbcDataAdapter.getClasspath());
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
		jdbcDataAdapter.setSavePassword(btnSavePassword.getSelection());

		jdbcDataAdapter.setClasspath(cpath.getClasspaths());

		return dataAdapterDesc;
	}

	public String getHelpContextId() {
		return "";
	}
}
