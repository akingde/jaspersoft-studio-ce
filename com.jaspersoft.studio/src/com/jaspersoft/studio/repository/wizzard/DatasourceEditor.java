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
package com.jaspersoft.studio.repository.wizzard;

import org.eclipse.jface.wizard.Wizard;

import com.jaspersoft.studio.model.datasource.AMDatasource;
import com.jaspersoft.studio.model.datasource.empty.MEmptyDataSource;
import com.jaspersoft.studio.model.datasource.file.MFileDataSource;
import com.jaspersoft.studio.model.datasource.jdbc.MJDBCDataSource;
import com.jaspersoft.studio.model.datasource.xls.MXLSDataSource;
import com.jaspersoft.studio.model.datasource.xml.MXMLDataSource;

public class DatasourceEditor extends Wizard {
	private AMDatasource value;
	private ADatasourcePage page0;

	public AMDatasource getValue() {
		if (page0 != null)
			return page0.getValue();
		return null;
	}

	public void setValue(AMDatasource value) {
		this.value = value;
	}

	public DatasourceEditor() {
		super();
		setWindowTitle("Datasource Editor");
	}

	@Override
	public void addPages() {
		if (value instanceof MJDBCDataSource) {
			page0 = new JDBCDatasourcePage();
		} else if (value instanceof MEmptyDataSource) {
			page0 = new EmptyDatasourcePage();
		} else if (value instanceof MFileDataSource) {
			page0 = new FileDatasourcePage();
			// } else if (value instanceof MODADatasources) {
			// page0 = new ODADatasourcePage();
			// } else if (value instanceof MDTPDatasources) {
			// page0 = new DTPDatasourcePage();
		} else if (value instanceof MXMLDataSource) {
			page0 = new XMLDatasourcePage();
		} else if (value instanceof MXLSDataSource) {
			page0 = new XLSDatasourcePage();
		}
		page0.setValue(value);
		addPage(page0);
	}

	@Override
	public boolean performFinish() {
		return true;
	}

}
