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
package com.jaspersoft.studio.server.action.resource;

import java.text.MessageFormat;
import java.util.UUID;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.bean.BeanDataAdapter;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;
import net.sf.jasperreports.data.jndi.JndiDataAdapter;
import net.sf.jasperreports.eclipse.ui.util.UIUtils;
import net.sf.jasperreports.eclipse.util.SecureStorageUtils;

import org.eclipse.equinox.security.storage.StorageException;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.wb.swt.ResourceManager;

import com.jaspersoft.studio.JaspersoftStudioPlugin;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.bean.BeanDataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterDescriptor;
import com.jaspersoft.studio.data.jndi.JndiDataAdapterDescriptor;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.server.Activator;
import com.jaspersoft.studio.server.messages.Messages;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceBean;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJDBC;
import com.jaspersoft.studio.server.model.datasource.MRDatasourceJNDI;

/**
 * Action for importing the selected DataSource in the JRS tree as Data Adapter
 * in JSS.
 * 
 * @author Massimo Rabbi (mrabbi@users.sourceforge.net)
 * 
 */
public class ImportDataSourceInJSSAction extends Action {
	public static final String ID = "IMPORT_DATASOURCE_IN_JSS"; //$NON-NLS-1$
	private TreeViewer treeViewer;

	public ImportDataSourceInJSSAction(TreeViewer treeViewer) {
		super();
		setId(ID);
		setText(Messages.ImportDataSourceInJSSAction_ActionText);
		setToolTipText(Messages.ImportDataSourceInJSSAction_ActionTooltip);
		setImageDescriptor(ResourceManager.getPluginImageDescriptor(
				JaspersoftStudioPlugin.PLUGIN_ID,
				"/icons/resources/eclipse/etool16/import_wiz.gif")); //$NON-NLS-1$
		this.treeViewer = treeViewer;
	}

	@Override
	public boolean isEnabled() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		return firstElement != null && isValidDataSource(firstElement);
	}

	@Override
	public void run() {
		Object firstElement = ((TreeSelection) treeViewer.getSelection())
				.getFirstElement();
		importDataSourceAsDataAdapter(firstElement);
		MessageDialog.openInformation(UIUtils.getShell(), Messages.ImportDataSourceInJSSAction_OperationInfoTitle,
				Messages.ImportDataSourceInJSSAction_OperationInfoMsg);
	}

	/*
	 * Right how the allowed type of DataSource are: 
	 * 	- JDBC 
	 * 	- Bean
	 * 	- JNDI
	 */
	private boolean isValidDataSource(Object element) {
		return element instanceof MRDatasourceJDBC
				|| element instanceof MRDatasourceJNDI
				|| element instanceof MRDatasourceBean;
	}

	/*
	 * Performs the import operation.
	 */
	private void importDataSourceAsDataAdapter(Object datasource) {
		if (datasource instanceof MRDatasourceJDBC) {
			MRDatasourceJDBC jdbcDS = (MRDatasourceJDBC) datasource;
			JDBCDataAdapterDescriptor jdbcDA = new JDBCDataAdapterDescriptor();
			JdbcDataAdapter jdbcDataAdapter = (JdbcDataAdapter) jdbcDA
					.getDataAdapter();
			jdbcDataAdapter.setName(getValidName(jdbcDS.getValue().getLabel(),
					"JDBC")); //$NON-NLS-1$
			jdbcDataAdapter.setDriver(jdbcDS.getValue().getDriverClass());
			jdbcDataAdapter.setUsername(jdbcDS.getValue().getUsername());
			jdbcDataAdapter.setPassword(getSecretStorageKey(jdbcDS.getValue().getPassword()));
			jdbcDataAdapter.setUrl(jdbcDS.getValue().getConnectionUrl());
			jdbcDataAdapter.setSavePassword(true);
			DataAdapterManager.getPreferencesStorage().addDataAdapter("", //$NON-NLS-1$
					jdbcDA);
		}
		else if (datasource instanceof MRDatasourceJNDI){
			MRDatasourceJNDI jndiDS = (MRDatasourceJNDI) datasource;
			JndiDataAdapterDescriptor jndiDA = new JndiDataAdapterDescriptor();
			JndiDataAdapter jndiDataAdapter = (JndiDataAdapter) jndiDA.getDataAdapter();
			jndiDataAdapter.setName(getValidName(jndiDS.getValue().getLabel(),
					"JNDI")); //$NON-NLS-1$
			jndiDataAdapter.setDataSourceName(jndiDS.getValue().getJndiName());
			DataAdapterManager.getPreferencesStorage().addDataAdapter("", //$NON-NLS-1$
					jndiDA);
		}
		else if (datasource instanceof MRDatasourceBean){
			MRDatasourceBean beanDS = (MRDatasourceBean) datasource;
			BeanDataAdapterDescriptor beanDA = new BeanDataAdapterDescriptor();
			BeanDataAdapter beanDataAdapter = (BeanDataAdapter) beanDA.getDataAdapter();
			beanDataAdapter.setName(getValidName(beanDS.getValue().getLabel(),
					"Bean")); //$NON-NLS-1$
			beanDataAdapter.setFactoryClass(beanDS.getValue().getBeanName());
			beanDataAdapter.setMethodName(beanDS.getValue().getBeanMethod());
			DataAdapterManager.getPreferencesStorage().addDataAdapter("", //$NON-NLS-1$
					beanDA);
		}
		else {
			throw new RuntimeException(Messages.ImportDataSourceInJSSAction_DataSourceNotSupportedError);
		}
	}

	/*
	 * Gets a valid name for the new data adapter being created.
	 */
	private String getValidName(String proposedName, String prefix) {
		ADataAdapterStorage prefStorage = DataAdapterManager
				.getPreferencesStorage();
		if (prefStorage.isDataAdapterNameValid(proposedName)) {
			return proposedName;
		} else {
			MessageFormat msgF = new MessageFormat(
					Messages.ImportDataSourceInJSSAction_DataAdapterNameTemplate);
			for (int i = 1; i < 1000; i++) {
				String name = msgF.format(new Object[] { prefix,
						(i > 1) ? "(" + i + ")" : "" }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
				if (prefStorage.isDataAdapterNameValid(name)) {
					return name;
				}
			}
			throw new RuntimeException(
					Messages.ImportDataSourceInJSSAction_UnableToGetNameError);
		}
	}
	
	/*
	 * Returns the key that will be used to retrieve the information from 
	 * the secure preferences.
	 */
	private String getSecretStorageKey(String pass) {
		try {
			UUID uuidKey = UUID.randomUUID();
			SecureStorageUtils.saveToDefaultSecurePreferences(
					AbstractDataAdapterService.SECRET_CATEGORY, uuidKey.toString(), pass);
			return uuidKey.toString();
		} catch (StorageException e) {
			Activator.getDefault().logError(Messages.Common_ErrSecurePrefStorage,e);
		};
		// in case something goes wrong return the clear-text password
		// we will rely on back-compatibility
		return pass;
	}
}
