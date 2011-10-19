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
package com.jaspersoft.studio.data.empty;

import net.sf.jasperreports.data.empty.EmptyDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.UIUtils;

public class EmptyDataAdapterComposite extends Composite {

	private Spinner spinnerRecords;
	private EmptyDataAdapterDescriptor emptyDataAdapterDesc = null;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EmptyDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("Number of empty records" + " :");

		spinnerRecords = new Spinner(this, SWT.BORDER);
		spinnerRecords.setValues(0, 0, Integer.MAX_VALUE, 0, 1, 10);
		spinnerRecords.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Set the DataAdapter to edit. The UI will be updated with the content of this adapter
	 * 
	 * @param dataAdapter
	 */
	public void setDataAdapter(EmptyDataAdapterDescriptor dataAdapterDesc) {
		emptyDataAdapterDesc = dataAdapterDesc;
		EmptyDataAdapter dataAdapter = (EmptyDataAdapter) emptyDataAdapterDesc.getDataAdapter();
		Integer records = dataAdapter.getRecordCount();
		if (records != null) {
			UIUtils.setSpinnerSelection(spinnerRecords, records);
		}
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (emptyDataAdapterDesc == null) {
			emptyDataAdapterDesc = new EmptyDataAdapterDescriptor();
		}
		((EmptyDataAdapter) emptyDataAdapterDesc.getDataAdapter()).setRecordCount(spinnerRecords.getSelection());
		return emptyDataAdapterDesc;
	}

	public String getHelpContextId() {
		return "";
	}
}
