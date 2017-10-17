/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.jdbc;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

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

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;
import com.jaspersoft.studio.data.secret.DataAdaptersSecretsProvider;
import com.jaspersoft.studio.swt.widgets.ClasspathComponent;
import com.jaspersoft.studio.swt.widgets.PropertiesComponent;
import com.jaspersoft.studio.swt.widgets.WSecretText;
import com.jaspersoft.studio.utils.UIUtil;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.engine.JasperReportsContext;

public class JDBCDataAdapterComposite extends ADataAdapterComposite {
	
	private final static JDBCDriverDefinition[] jdbcDefinitions;
	
	static {
		List<JDBCDriverDefinition> tmpDefinitions = JaspersoftStudioPlugin.getExtensionManager().getJDBCDriverDefinitions();
		jdbcDefinitions = tmpDefinitions.toArray(new JDBCDriverDefinition[tmpDefinitions.size()]);
		Arrays.sort(jdbcDefinitions,new Comparator<JDBCDriverDefinition>() {
			@Override
			public int compare(JDBCDriverDefinition o1, JDBCDriverDefinition o2) {
				return o1.getDbName().compareTo(o2.getDbName());
			}
		});
	}

	public JDBCDriverDefinition[] getDefinitions() {
		return jdbcDefinitions;
	}

	protected Text textJDBCUrl;
	protected Text textServerAddress;
	protected Text textDatabase;
	protected Text textUsername;
	protected WSecretText textPassword;

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
	public JDBCDataAdapterComposite(Composite parent, int style, JasperReportsContext jrContext) {
		super(parent, style, jrContext);
		GridLayout layout = new GridLayout();
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		setLayout(layout);

		createPreWidgets(this);

		CTabFolder tabFolder = new CTabFolder(this, SWT.BOTTOM);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 3, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		tabFolder.setLayoutData(new GridData(GridData.FILL_BOTH));

		createLocationTab(tabFolder);

		createPropertiesTab(tabFolder);

		createClasspathTab(tabFolder);
		tabFolder.setSelection(0);
		contextId = "adapter_JDBC";
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

		cpath = new ClasspathComponent(composite) {
			@Override
			protected void handleClasspathChanged() {
				pchangesuport.firePropertyChange("dirty", false, true);
			}
		};
		cpath.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
	}

	protected void createPropertiesTab(CTabFolder tabFolder) {
		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText(Messages.JDBCDataAdapterComposite_connectionproperties);

		Composite composite = new Composite(tabFolder, SWT.NONE);
		composite.setLayout(new GridLayout());
		composite.setLayoutData(new GridData(GridData.FILL_BOTH));
		tbtmNewItem.setControl(composite);

		cproperties = new PropertiesComponent(composite);
		cproperties.getControl().setLayoutData(new GridData(GridData.FILL_BOTH));
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
					currentdriver = definitions[comboJDBCDriver.getCombo().getSelectionIndex()];
					comboJDBCDriver.getCombo().setText(currentdriver.getDriverName());
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

		textPassword = new WSecretText(composite, SWT.BORDER | SWT.PASSWORD);
		textPassword.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		// btnSavePassword = new Button(this, SWT.CHECK);
		// btnSavePassword.setText("Save Password");
		// new Label(this, SWT.NONE);

		new Label(composite, SWT.NONE);
		Composite c = new Composite(composite, SWT.NONE);
		c.setLayout(new GridLayout(2, false));

		lbl = new Label(c, SWT.NONE | SWT.BOLD);
		lbl.setText(Messages.JDBCDataAdapterComposite_attentionlable);
		UIUtil.setBold(lbl);

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
		if (currentdriver != null) {
			// && textServerAddress != null && textDatabase != null) {
			textJDBCUrl.setText(currentdriver.getUrl("localhost", "database"));
			// textServerAddress.getText(), textDatabase.getText()));
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

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc.getDataAdapter();
		if (jdbcDataAdapter.getDriver() == null)
			btnWizardActionPerformed();

		if (!textPassword.isWidgetConfigured()) {
			textPassword.loadSecret(DataAdaptersSecretsProvider.SECRET_NODE_ID, textPassword.getText());
		}
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapter;

		String driverName = Misc.nvl(jdbcDataAdapter.getDriver(), "org.hsqldb.jdbcDriver"); //$NON-NLS-1$
		comboJDBCDriver.getCombo().setText(driverName);

		for (JDBCDriverDefinition d : definitions) {
			if (d.getDriverName().equals(driverName)) {
				currentdriver = d;
				break;
			}
		}

		bindingContext.bindValue(SWTObservables.observeText(textUsername, SWT.Modify), PojoObservables.observeValue(dataAdapter, "username")); //$NON-NLS-1$
		bindingContext.bindValue(SWTObservables.observeText(textPassword, SWT.Modify), PojoObservables.observeValue(dataAdapter, "password")); //$NON-NLS-1$
		bindURLAssistant(dataAdapter);
		bindingContext.bindValue(SWTObservables.observeText(textJDBCUrl, SWT.Modify), PojoObservables.observeValue(dataAdapter, "url")); //$NON-NLS-1$

		cpath.setClasspaths(jdbcDataAdapter.getClasspath());
		cproperties.setProperties(jdbcDataAdapter.getProperties());
	}

	protected void bindURLAssistant(DataAdapter dataAdapter) {
		if (textServerAddress != null)
			bindingContext.bindValue(SWTObservables.observeText(textServerAddress, SWT.Modify), PojoObservables.observeValue(dataAdapter, "serverAddress")); //$NON-NLS-1$
		if (textDatabase != null)
			bindingContext.bindValue(SWTObservables.observeText(textDatabase, SWT.Modify), PojoObservables.observeValue(dataAdapter, "database")); //$NON-NLS-1$
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null) {
			dataAdapterDesc = new JDBCDataAdapterDescriptor();
		}

		JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc.getDataAdapter();

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

	protected String contextId;

	@Override
	public String getHelpContextId() {
		return PREFIX.concat(contextId);
	}

	public void setContextId(String contextId) {
		this.contextId = contextId;
	}

	@Override
	public void performAdditionalUpdates() {
		if (JaspersoftStudioPlugin.shouldUseSecureStorage()) {
			textPassword.persistSecret();
			// update the "password" replacing it with the UUID key saved in secure
			// preferences
			JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) dataAdapterDesc.getDataAdapter();
			jdbcDataAdapter.setPassword(textPassword.getUUIDKey());
		}
	}
}
