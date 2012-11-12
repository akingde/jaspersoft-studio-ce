/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
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
package com.jaspersoft.studio.data.wizard;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;

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
		init(new JasperReportsConfiguration(DefaultJasperReportsContext.getInstance(), null));
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
	public void createPageControls(Composite pageContainer) {
		super.createPageControls(pageContainer);

		if (this.dataAdapter != null) {
			DataAdapterDescriptor editedDataAdapter = DataAdapterManager.cloneDataAdapter(this.dataAdapter);
			dataAdapterEditorPage.setDataAdapter(editedDataAdapter);
		}
	}

	// Save the new adapter using the manager
	@Override
	public boolean performFinish() {
		DataAdapterDescriptor editedDataAdapter = dataAdapterEditorPage.getDataAdapter();

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
