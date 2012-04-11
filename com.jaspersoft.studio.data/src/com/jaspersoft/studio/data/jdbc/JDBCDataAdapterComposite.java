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

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.swt.widgets.PropertiesComponent;
import com.jaspersoft.studio.utils.Misc;
import com.jaspersoft.studio.utils.UIUtils;

public class JDBCDataAdapterComposite extends ADataAdapterComposite {
	private final static JDBCDriverDefinition[] jdbcDefinitions = new JDBCDriverDefinition[] {
			new JDBCDriverDefinition("HSQLDB (server)", //$NON-NLS-1$
					"org.hsqldb.jdbcDriver", "jdbc:hsqldb:hsql://{0}"), //$NON-NLS-1$ //$NON-NLS-2$
			new JDBCDriverDefinition("HSQLDB (file)", "org.hsqldb.jdbcDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:hsqldb:[PATH_TO_DB_FILES]/{1}"), //$NON-NLS-1$
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
					"jdbc:vertica://{0}:5433/{1}"), //$NON-NLS-1$
			new JDBCDriverDefinition(
					"Hadoop Hive", "org.apache.hadoop.hive.jdbc.HiveDriver", //$NON-NLS-1$ //$NON-NLS-2$
					"jdbc:hive://{0}:10000/default", "default", "localhost") }; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$

	public JDBCDriverDefinition[] getDefinitions() {
		return jdbcDefinitions;
	}

	protected Text textJDBCUrl;
	protected Text textServerAddress;
	protected Text textDatabase;
	protected Text textUsername;
	protected Text textPassword;

	// private Button btnSavePassword;
	protected ComboViewer comboJDBCDriver;

	protected JDBCDriverDefinition currentdriver = null;

	private ClasspathComponent cpath;
	private PropertiesComponent cproperties;
	protected JDBCDriverDefinition[] definitions;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JDBCDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);

		createPreWidgets(this);

		CTabFolder tabFolder = new CTabFolder(this, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false,
				false, 3, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(
				SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createLocationTab(tabFolder);

		createPropertiesTab(tabFolder);

		createClasspathTab(tabFolder);
		tabFolder.setSelection(0);
	}

	protected void createPreWidgets(Composite parent) {

	}

	protected void createClasspathTab(CTabFolder tabFolder) {
		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText(Messages.JDBCDataAdapterComposite_classpath);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		tbtmNewItem_1.setControl(composite);

		cpath = new ClasspathComponent(composite); 
		cpath.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	protected void createPropertiesTab(CTabFolder tabFolder) {
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

	protected void createLocationTab(CTabFolder tabFolder) {
		CTabItem tbtmNewItem_2 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_2.setText(Messages.JDBCDataAdapterComposite_tablocation);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout(2, false));
		tbtmNewItem_2.setControl(composite);

		Label lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		lbl.setText(Messages.JDBCDataAdapterComposite_driverlabel);

		comboJDBCDriver = new ComboViewer(composite, SWT.NONE);
		Combo combo = comboJDBCDriver.getCombo();
		combo.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		lbl.setText(Messages.JDBCDataAdapterComposite_urllabel);

		textJDBCUrl = new Text(composite, SWT.BORDER);
		textJDBCUrl.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		createURLAssistant(composite);

		createUserPass(composite);

		definitions = getDefinitions();
		for (int i = 0; i < definitions.length; ++i)
			comboJDBCDriver.add(definitions[i]);

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

	protected void createUserPass(final Composite composite) {
		Label lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		lbl.setText(Messages.JDBCDataAdapterComposite_username);

		textUsername = new Text(composite, SWT.BORDER);
		textUsername.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		lbl = new Label(composite, SWT.NONE);
		lbl.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
		lbl.setText(Messages.JDBCDataAdapterComposite_password);

		textPassword = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// btnSavePassword = new Button(this, SWT.CHECK);
		// btnSavePassword.setText("Save Password");
		// new Label(this, SWT.NONE);

		new Label(composite, SWT.NONE);
		Composite c = new Composite(composite, SWT.NONE);
		c.setLayout(new GridLayout(2, false));

		lbl = new Label(c, SWT.NONE | SWT.BOLD);
		lbl.setText(Messages.JDBCDataAdapterComposite_attentionlable);
		UIUtils.setBold(lbl);

		lbl = new Label(c, SWT.NONE);
		lbl.setText(Messages.JDBCDataAdapterComposite_attention);
	}

	protected void createURLAssistant(final Composite composite) {
		// new Label(composite, SWT.NONE);
		//
		// Section expcmp = new Section(composite,
		// ExpandableComposite.TREE_NODE);
		// expcmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		// expcmp.setTitleBarForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		// UIUtils.setBold(expcmp);
		// expcmp.setText(Messages.JDBCDataAdapterComposite_jdbcurlassistant);
		//
		// final Composite cmp = new Composite(expcmp, SWT.NONE);
		// cmp.setLayout(new GridLayout(2, false));
		//
		// expcmp.setClient(cmp);
		// expcmp.addExpansionListener(new ExpansionAdapter() {
		// public void expansionStateChanged(ExpansionEvent e) {
		// JDBCDataAdapterComposite.this.getParent().layout(true);
		// }
		// });
		//
		// Label lbl = new Label(cmp, SWT.NONE);
		// lbl.setText(Messages.JDBCDataAdapterComposite_notedbname);
		// GridData gd = new GridData();
		// gd.horizontalSpan = 2;
		// lbl.setLayoutData(gd);
		//
		// Label lblServerAddress = new Label(cmp, SWT.NONE);
		// lblServerAddress.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER,
		// false, false, 1, 1));
		// lblServerAddress
		// .setText(Messages.JDBCDataAdapterComposite_serveraddress);
		//
		// textServerAddress = new Text(cmp, SWT.BORDER);
		// textServerAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER,
		// true, false, 1, 1));
		//
		// Label lblDatabase = new Label(cmp, SWT.NONE);
		// lblDatabase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false,
		// false, 1, 1));
		// lblDatabase.setText(Messages.JDBCDataAdapterComposite_database);
		//
		// textDatabase = new Text(cmp, SWT.BORDER);
		// textDatabase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true,
		// false, 1, 1));
		//
		// textServerAddress.addModifyListener(new ModifyListener() {
		//
		// public void modifyText(ModifyEvent e) {
		// btnWizardActionPerformed();
		// }
		// });
		// textDatabase.addModifyListener(new ModifyListener() {
		//
		// public void modifyText(ModifyEvent e) {
		// btnWizardActionPerformed();
		// }
		// });
	}

	/**
	 * @param e
	 */
	protected void btnWizardActionPerformed() {
		if (currentdriver != null && textServerAddress != null
				&& textDatabase != null) {
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
		super.setDataAdapter(editingDataAdapter);

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc
				.getDataAdapter();
		if (jdbcDataAdapter.getDriver() == null)
			btnWizardActionPerformed();
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapter;

		String driverName = Misc.nvl(jdbcDataAdapter.getDriver(),
				"org.hsqldb.jdbcDriver"); //$NON-NLS-1$
		comboJDBCDriver.getCombo().setText(driverName);

		for (JDBCDriverDefinition d : definitions) {
			if (d.getDriverName().equals(driverName)) {
				currentdriver = d;
				break;
			}
		}

		bindingContext.bindValue(
				SWTObservables.observeText(textUsername, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(
				SWTObservables.observeText(textPassword, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
		bindURLAssistant(dataAdapter);
		bindingContext.bindValue(
				SWTObservables.observeText(textJDBCUrl, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "url")); //$NON-NLS-1$

		cpath.setClasspaths(jdbcDataAdapter.getClasspath());
		cproperties.setProperties(jdbcDataAdapter.getProperties());
	}

	protected void bindURLAssistant(DataAdapter dataAdapter) {
		if (textServerAddress != null)
			bindingContext.bindValue(
					SWTObservables.observeText(textServerAddress, SWT.Modify),
					PojoObservables.observeValue(dataAdapter, "serverAddress")); //$NON-NLS-1$
		if (textDatabase != null)
			bindingContext.bindValue(
					SWTObservables.observeText(textDatabase, SWT.Modify),
					PojoObservables.observeValue(dataAdapter, "database")); //$NON-NLS-1$
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
		getDataAdapterURLAssistant(jdbcDataAdapter);
		jdbcDataAdapter.setSavePassword(true);// btnSavePassword.getSelection());

		jdbcDataAdapter.setClasspath(cpath.getClasspaths());
		jdbcDataAdapter.setProperties(cproperties.getProperties());

		return dataAdapterDesc;
	}

	protected void getDataAdapterURLAssistant(JdbcDataAdapter jdbcDataAdapter) {
		if (textDatabase != null)
			jdbcDataAdapter.setDatabase(textDatabase.getText());
		else
			jdbcDataAdapter.setDatabase("");
		if (textServerAddress != null)
			jdbcDataAdapter.setServerAddress(textServerAddress.getText());
		else
			jdbcDataAdapter.setServerAddress("");
	}

}
