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
package com.jaspersoft.studio.data.mondrian;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.mondrian.MondrianDataAdapter;

import org.eclipse.core.databinding.beans.PojoObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterComposite;
import com.jaspersoft.studio.data.messages.Messages;

public class MondrianDataAdapterComposite extends JDBCDataAdapterComposite {
	private Text textCatalogURI;

	public MondrianDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void createPreWidgets(Composite parent) {
		Composite cmp = new Composite(parent, SWT.NONE);
		cmp.setLayout(new GridLayout(3, false));
		cmp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		new Label(cmp, SWT.NONE)
				.setText(Messages.MondrianDataAdapterComposite_0);

		textCatalogURI = new Text(cmp, SWT.BORDER);
		textCatalogURI.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button btnBrowse = new Button(cmp, SWT.NONE);
		btnBrowse.setText(Messages.MondrianDataAdapterComposite_1);

		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell());
				fd.setFileName(textCatalogURI.getText());
				fd.setFilterExtensions(new String[] { "*.xml", "*.*" }); //$NON-NLS-1$ //$NON-NLS-2$
				String selection = fd.open();
				if (selection != null)
					textCatalogURI.setText("file:" + selection);
			}
		});
	}

	@Override
	protected void bindWidgets(DataAdapter dataAdapter) {
		super.bindWidgets(dataAdapter);
		bindingContext.bindValue(
				SWTObservables.observeText(textCatalogURI, SWT.Modify),
				PojoObservables.observeValue(dataAdapter, "catalogURI")); //$NON-NLS-1$
	}

	@Override
	public DataAdapterDescriptor getDataAdapter() {
		if (dataAdapterDesc == null)
			dataAdapterDesc = new MondrianDataAdapterDescriptor();
		super.getDataAdapter();

		MondrianDataAdapter mDataAdapter = (MondrianDataAdapter) dataAdapterDesc
				.getDataAdapter();
		mDataAdapter.setCatalogURI(textCatalogURI.getText());

		return dataAdapterDesc;
	}

}
