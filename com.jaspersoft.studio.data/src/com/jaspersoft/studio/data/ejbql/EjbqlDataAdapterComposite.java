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
package com.jaspersoft.studio.data.ejbql;

import net.sf.jasperreports.data.ejbql.EjbqlDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class EjbqlDataAdapterComposite extends Composite {

	private EjbqlDataAdapterDescriptor ejbqlDataAdapterDesc = null;
	private Text punitName;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public EjbqlDataAdapterComposite(Composite parent, int style) {

		super(parent, style);
		setLayout(new GridLayout(1, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText("Persistance Unit Name");

		punitName = new Text(this, SWT.BORDER);
		punitName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	public void setDataAdapter(EjbqlDataAdapterDescriptor dataAdapter) {
		this.ejbqlDataAdapterDesc = dataAdapter;

		EjbqlDataAdapter xmlDataAdapter = ejbqlDataAdapterDesc.getDataAdapter();

		punitName
				.setText(Misc.nvl(xmlDataAdapter.getPersistanceUnitName(), ""));
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (ejbqlDataAdapterDesc == null)
			ejbqlDataAdapterDesc = new EjbqlDataAdapterDescriptor();

		EjbqlDataAdapter dataAdapter = ejbqlDataAdapterDesc.getDataAdapter();

		dataAdapter.setPersistanceUnitName(punitName.getText());

		return ejbqlDataAdapterDesc;
	}

	public String getHelpContextId() {
		return "";
	}
}
