/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.empty;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.layout.GridData;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.utils.UIUtils;

public class EmptyDataAdapterComposite extends Composite {

	private Spinner spinnerRecords;
	private EmptyDataAdapter emptyDataAdapter = null;
	
	/**
	 * Create the composite.
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
		spinnerRecords.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	/**
	 * Set the DataAdapter to edit.
	 * The UI will be updated with the content of this adapter
	 * @param dataAdapter
	 */
	public void setDataAdapter(EmptyDataAdapter dataAdapter) {
		emptyDataAdapter = dataAdapter;
		Integer records = emptyDataAdapter.getRecords();
		if (records != null)
		{
			UIUtils.setSpinnerSelection(spinnerRecords, records);
		}
	}
	
	public DataAdapter getDataAdapter() {
		if(emptyDataAdapter == null){
			emptyDataAdapter = new EmptyDataAdapter();
		}
		emptyDataAdapter.setRecords(spinnerRecords.getSelection());
		return emptyDataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}
}
