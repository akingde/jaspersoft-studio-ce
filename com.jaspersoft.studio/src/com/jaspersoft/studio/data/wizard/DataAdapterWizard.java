/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.wizard;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.data.storage.ADataAdapterStorage;
import com.jaspersoft.studio.data.wizard.pages.DataAdapterEditorPage;
import com.jaspersoft.studio.data.wizard.pages.DataAdaptersListPage;
import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public class DataAdapterWizard extends AbstractDataAdapterWizard implements SelectionListener {

	/**
	 * This constructor will set the data adapter wizard. The data adapters list is displayed as first page, then the edit
	 * page is shown.
	 */
	public DataAdapterWizard(ADataAdapterStorage storage) {
		setWindowTitle(Messages.DataAdapterWizard_windowtitle);
		this.storage = storage;
		setConfig(JasperReportsConfiguration.getDefaultInstance(), false);
	}

	/**
	 * Pass to this constructor a dataAdapter to be edited. This will set the wizard directly to edit page.
	 * 
	 * @param dataAdapter
	 */
	public DataAdapterWizard(DataAdapterDescriptor dataAdapter, ADataAdapterStorage storage) {
		this(storage);
		this.dataAdapter = dataAdapter;
	}

	@Override
	public void addPages() {

		if (dataAdapter == null) {
			dataAdapterListPage = new DataAdaptersListPage();
			addPage(dataAdapterListPage);
		}

		dataAdapterEditorPage = new DataAdapterEditorPage();
		dataAdapterEditorPage.setStorage(storage);
		if (dataAdapter != null) {
			dataAdapterEditorPage.setEditMode(true);
		}
		addPage(dataAdapterEditorPage);
	}

	@Override
	public IWizardPage getNextPage(IWizardPage page) {
		if (page instanceof DataAdapterEditorPage)
			((DataAdapterEditorPage) page).setJrContext(getConfig());
		return super.getNextPage(page);
	}

	@Override
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);

		if (this.dataAdapter != null) {
			DataAdapterDescriptor editedDataAdapter = DataAdapterManager.cloneDataAdapter(this.dataAdapter, getConfig());
			dataAdapterEditorPage.setDataAdapter(editedDataAdapter);
		}
	}

	// Save the new adapter using the manager
	@Override
	public boolean performFinish() {
		DataAdapterDescriptor editedDataAdapter = dataAdapterEditorPage.getDataAdapter();
		dataAdapterEditorPage.performFinishInvoked();

		if (this.dataAdapter == null) {
			this.dataAdapter = editedDataAdapter;
		} else // We are modifying an existing adapter....
		{
			// ... let's update with the adapter just modified ...
			String oldName = this.dataAdapter.getName();
			dataAdapter.setDataAdapter(editedDataAdapter.getDataAdapter());
			if (!oldName.equals(editedDataAdapter.getName())) {
				if (!storage.isDataAdapterNameValid(editedDataAdapter.getName())) {
					dataAdapter.getDataAdapter().setName(editedDataAdapter.getName());
				}
			}
		}
		return true;
	}

}
