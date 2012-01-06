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

import net.sf.jasperreports.data.DataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Spinner;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;

public class EmptyDataAdapterComposite extends ADataAdapterComposite {

	private Spinner spinnerRecords;

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
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(SWTObservables.observeSelection(spinnerRecords),
				PojoObservables.observeValue(dataAdapter, "recordCount"));
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new EmptyDataAdapterDescriptor();

		((EmptyDataAdapterDescriptor) dataAdapterDesc).getDataAdapter().setRecordCount(spinnerRecords.getSelection());
		return dataAdapterDesc;
	}

}
