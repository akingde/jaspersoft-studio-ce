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

import net.sf.jasperreports.data.mondrian.MondrianDataAdapter;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterComposite;
import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterDescriptor;
import com.jaspersoft.studio.utils.Misc;

public class MondrianDataAdapterComposite extends JDBCDataAdapterComposite {
	private Text textCatalogURI;

	public MondrianDataAdapterComposite(Composite parent, int style) {
		super(parent, style);
	}

	@Override
	protected void createPreWidgets(Composite parent) {
		new Label(parent, SWT.NONE).setText("Catalog URI");

		textCatalogURI = new Text(parent, SWT.BORDER);
		textCatalogURI.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		Button btnBrowse = new Button(parent, SWT.NONE);
		btnBrowse.setText("Browse");

		btnBrowse.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(Display.getDefault()
						.getActiveShell());
				fd.setFileName(textCatalogURI.getText());
				fd.setFilterExtensions(new String[] { "*.xml", "*.*" });
				String selection = fd.open();
				if (selection != null)
					textCatalogURI.setText(selection);
			}
		});
	}

	@Override
	public void setDataAdapter(JDBCDataAdapterDescriptor editingDataAdapter) {
		super.setDataAdapter(editingDataAdapter);
		dataAdapterDesc = editingDataAdapter;

		MondrianDataAdapter jdbcDataAdapter = (MondrianDataAdapter) dataAdapterDesc
				.getDataAdapter();

		textCatalogURI.setText(Misc.nvl(jdbcDataAdapter.getCatalogURI(), "")); //$NON-NLS-1$
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
