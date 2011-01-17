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
package com.jaspersoft.studio.repository.wizard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.messages.Messages;
import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.file.MFileDataSource;
import com.jaspersoft.studio.model.datasource.xls.MXLSDataSource;

public class XLSDatasourcePage extends AFileDatasourcePage {
	private Button firstRowHeaderTxt;
	private Text columnNamesTxt;

	protected XLSDatasourcePage() {
		super("xlsdatasourceeditor"); //$NON-NLS-1$
		setTitle(Messages.XLSDatasourcePage_xls_datasource);
		setDescription(Messages.XLSDatasourcePage_description);
	}

	@Override
	protected String[] getFilterExt() {
		return new String[] { "*.xls", "*.*" }; //$NON-NLS-1$ //$NON-NLS-2$
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();

		value.setPropertyValue(MFileDataSource.PROPERTY_COLUMNNAMES, columnNamesTxt.getText());
		value.setPropertyValue(MFileDataSource.PROPERTY_FIRSTROWASHEADER, firstRowHeaderTxt.getSelection());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		super.createMoreControls(parent);

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.FileDatasourcePage_use_first_row_as_header + ":"); //$NON-NLS-1$

		firstRowHeaderTxt = new Button(parent, SWT.CHECK);

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText(Messages.common_column_names + ":"); //$NON-NLS-1$

		columnNamesTxt = new Text(parent, SWT.BORDER);
		columnNamesTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {

			String dsName = (String) value.getPropertyValue(MXLSDataSource.PROPERTY_COLUMNNAMES);
			if (dsName == null)
				dsName = ""; //$NON-NLS-1$
			columnNamesTxt.setText(dsName);

			boolean firstRowHeader = (Boolean) value.getPropertyValue(MXLSDataSource.PROPERTY_FIRSTROWASHEADER);
			firstRowHeaderTxt.setSelection(firstRowHeader);
		}
	}

}
