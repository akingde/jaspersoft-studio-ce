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
package com.jaspersoft.studio.data.wizard.pages;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.PlatformUI;

import com.jaspersoft.studio.data.DataAdapterFactory;
import com.jaspersoft.studio.data.DataAdapterManager;
import com.jaspersoft.studio.swt.widgets.table.ListContentProvider;

public class DataAdaptersListPage extends WizardPage {

	java.util.List<DataAdapterFactory> dataAdapterFactories = null;

	private TableViewer tviewer;
	private Table wtable;

	/**
	 * Create the wizard.
	 */
	public DataAdaptersListPage() {
		super("dataAdapterslistpage");
		setTitle("Data Adapters");
		setDescription("Select a DataAdapter");
		setPageComplete(false);
	}

	/**
	 * Create contents of the wizard.
	 * 
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(new GridLayout());

		wtable = new Table(container, SWT.V_SCROLL | SWT.SINGLE | SWT.FULL_SELECTION | SWT.BORDER);
		GridData gd = new GridData(GridData.FILL_BOTH);
		gd.widthHint = 100;
		gd.heightHint = 250;
		wtable.setLayoutData(gd);
		wtable.setHeaderVisible(false);

		TableColumn[] col = new TableColumn[1];
		col[0] = new TableColumn(wtable, SWT.NONE);
		col[0].setText("Jar Files Path");
		col[0].pack();

		TableLayout tlayout = new TableLayout();
		tlayout.addColumnData(new ColumnWeightData(100, false));
		wtable.setLayout(tlayout);

		tviewer = new TableViewer(wtable);
		tviewer.setContentProvider(new ListContentProvider());
		tviewer.setLabelProvider(new LLabelProvider());
		wtable.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				int ind = wtable.getSelectionIndex();
				if (ind >= 0 && ind < dataAdapterFactories.size())
					setMessage(dataAdapterFactories.get(ind).getDescription());
			}

			public void widgetDefaultSelected(SelectionEvent e) {
				widgetSelected(e);
			}
		});
		updateFactoriesList();

		if (dataAdapterFactories.size() > 0) {
			wtable.setSelection(0);
			setPageComplete(wtable.getSelectionCount() > 0);
			setMessage(dataAdapterFactories.get(0).getDescription());
		}
	}

	@Override
	public boolean canFlipToNextPage() {
		return (wtable.getSelectionCount() == 1) ? true : false;
	}

	protected void factorySelected(SelectionEvent e) {
		setPageComplete(wtable.getSelectionCount() > 0);
	}

	private void updateFactoriesList() {
		dataAdapterFactories = DataAdapterManager.getDataAdapterFactories();

		tviewer.setInput(dataAdapterFactories);
	}

	public DataAdapterFactory getSelectedFactory() {

		if (dataAdapterFactories == null)
			return null; // Should never be true
		if (wtable.getSelectionIndex() < 0)
			return null; // Should never be true

		return dataAdapterFactories.get(wtable.getSelectionIndex());
	}

	@Override
	public void performHelp() {
		PlatformUI.getWorkbench().getHelpSystem().displayHelp("com.jaspersoft.studio.doc.dataAdapters_wizard_list");

	}
}
