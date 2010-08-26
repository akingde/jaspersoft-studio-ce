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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;

public class XMLDatasourcePage extends AFileDatasourcePage {

	private Text xpathselectTXT;

	protected XMLDatasourcePage() {
		super("xmldatasourceeditor");
		setTitle("XML Datasource");
		setDescription("Creates a XML datasource.");
	}

	@Override
	public void dispose() {
		AMDatasource value = getValue();
		value.setPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT, xpathselectTXT.getText());
		super.dispose();
	}

	@Override
	protected void createMoreControls(Composite parent) {
		super.createMoreControls(parent);

		Label lbl1 = new Label(parent, SWT.NONE);
		lbl1.setText("XPath Select:");

		xpathselectTXT = new Text(parent, SWT.BORDER);
		xpathselectTXT.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	}

	@Override
	protected void setWidgets() {
		super.setWidgets();
		AMDatasource value = getValue();
		if (value != null) {
			String dsName = (String) value.getPropertyValue(MXMLDataSource.PROPERTY_XPATHSELECT);
			if (dsName == null)
				dsName = "";
			xpathselectTXT.setText(dsName);

		}
	}

	@Override
	protected String[] getFilterExt() {
		return new String[] { "*.xml", "*.*" };
	}

}
