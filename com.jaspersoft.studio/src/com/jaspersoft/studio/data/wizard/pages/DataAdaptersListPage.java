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
package com.jaspersoft.studio.data.wizard.pages;

import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;

public class DataAdaptersListPage extends WizardPage {

	
	List list = null;
	java.util.List<DataAdapterFactory> dataAdapterFactories = null;
		
	/**
	 * Create the wizard.
	 */
	public DataAdaptersListPage() {
		super("dataAdapterslistpage");
		setTitle("DataAdapters");
		setDescription("Select a DataAdapter");
		setPageComplete(false);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		
		ListViewer listViewer = new ListViewer(container, SWT.BORDER | SWT.V_SCROLL);
		list = listViewer.getList();
		
		list.addSelectionListener(new SelectionListener() {
			
			public void widgetSelected(SelectionEvent e) {
				factorySelected(e);
			}
			
			public void widgetDefaultSelected(SelectionEvent e) {
				factorySelected(e);
			}
		});
		
	  // Fill the list with the data adapters
		updateFactoriesList(list);
		
		if (list.getItemCount() > 0)
		{
			list.setSelection(0);
			setPageComplete( list.getSelectionCount() > 0);
		}
	}
	
	public boolean canFlipToNextPage(){
		return (list.getSelectionCount() == 1) ? true : false;
	}
	
	protected void factorySelected(SelectionEvent e) {
			setPageComplete( list.getSelectionCount() > 0);
	}

	
	private void updateFactoriesList(List list) {
		
		list.removeAll();

		dataAdapterFactories = DataAdapterManager.getDataAdapterFactories();
		
		for (DataAdapterFactory dataAdapterFactory : dataAdapterFactories) {
			list.add(dataAdapterFactory.getLabel());
		}
	}

	public DataAdapterFactory getSelectedFactory() {
		
		if (dataAdapterFactories == null) return null; // Should never be true
		if (list.getSelectionIndex() < 0) return null; // Should never be true
		
		return dataAdapterFactories.get( list.getSelectionIndex() );
	}
	
	@Override
	public void performHelp() {
		
				PlatformUI.getWorkbench().getHelpSystem().displayHelp("com.jaspersoft.studio.doc.dataAdapters_wizard_list");

	}
}
