/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer.
 * Copyright (C) 2005 - 2010 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of Jaspersoft Open Studio.
 *
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Jaspersoft Open Studio is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with Jaspersoft Open Studio. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.repository.wizzard;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.file.MFileDataSource;

public class FileDatasourcePage extends AFileDatasourcePage {

	private Text recordDelimiterTxt;
	private Text columnDelimiterTxt;
	private Button firstRowHeaderTxt;
	private Text columnNamesTxt;

	protected FileDatasourcePage() {
		super("fiedatasourceeditor");
		setTitle("File Datasource");
		setDescription("Creates a file datasource.");
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		String recDelimiter = recordDelimiterTxt.getText();
		if (recDelimiter.length() > 0)
			value.setPropertyValue(MFileDataSource.PROPERTY_RECORDDELIMITER, recDelimiter);
		value.setPropertyValue(MFileDataSource.PROPERTY_COLUMNNAMES, columnNamesTxt.getText());
		String colDelimiter = columnDelimiterTxt.getText();
		if (colDelimiter.length() > 0)
			value.setPropertyValue(MFileDataSource.PROPERTY_COLUMNDELIMITER, colDelimiter.charAt(0));
		value.setPropertyValue(MFileDataSource.PROPERTY_FIRSTROWASHEADER, firstRowHeaderTxt.getSelection());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		super.createMoreControls(parent);

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Record Delimiter:");

		recordDelimiterTxt = new Text(parent, SWT.BORDER);
		recordDelimiterTxt.setTextLimit(10);
		GridData gd = new GridData();
		gd.widthHint = 50;
		recordDelimiterTxt.setLayoutData(gd);

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Field Delimiter:");

		columnDelimiterTxt = new Text(parent, SWT.BORDER);
		columnDelimiterTxt.setTextLimit(1);
		gd = new GridData();
		gd.widthHint = 20;
		columnDelimiterTxt.setLayoutData(gd);

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Use First Row As Header:");

		firstRowHeaderTxt = new Button(parent, SWT.CHECK);

		lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("Column Names:");

		columnNamesTxt = new Text(parent, SWT.BORDER);
		columnNamesTxt.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected String[] getFilterExt() {
		return new String[] { "*.csv", "*.txt", "*.*" };
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(MFileDataSource.PROPERTY_RECORDDELIMITER);
			if (dsName == null)
				dsName = "";
			recordDelimiterTxt.setText(dsName);

			dsName = (String) value.getPropertyValue(MFileDataSource.PROPERTY_COLUMNNAMES);
			if (dsName == null)
				dsName = "";
			columnNamesTxt.setText(dsName);

			Character c = (Character) value.getPropertyValue(MFileDataSource.PROPERTY_COLUMNDELIMITER);
			columnDelimiterTxt.setText(c.toString());

			boolean firstRowHeader = (Boolean) value.getPropertyValue(MFileDataSource.PROPERTY_FIRSTROWASHEADER);
			firstRowHeaderTxt.setSelection(firstRowHeader);
		}
	}

}
