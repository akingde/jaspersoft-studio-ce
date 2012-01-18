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
package com.jaspersoft.studio.data.jndi;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.jndi.JndiDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.ADataAdapterComposite;
import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.messages.Messages;

public class JndiDataAdapterComposite extends ADataAdapterComposite {

	private Text dataSourceName;

	/**
	 * Create the composite.
	 * 
	 * @param parent
	 * @param style
	 */
	public JndiDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
		setLayout(new GridLayout(2, false));

		Label lblNewLabel = new Label(this, SWT.NONE);
		lblNewLabel.setText(Messages.JndiDataAdapterComposite_0);

		dataSourceName = new Text(this, SWT.BORDER);
		dataSourceName.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		bindingContext.bindValue(
				SWTObservables.observeText(dataSourceName, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "dataSourceName")); //$NON-NLS-1$
	}

	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new JndiDataAdapterDescriptor();

		JndiDataAdapter dataAdapter = (JndiDataAdapter) dataAdapterDesc
				.getDataAdapter();

		dataAdapter.setDataSourceName(dataSourceName.getText());

		return dataAdapterDesc;
	}

}
