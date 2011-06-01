/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data.csv;

import java.util.List;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.csv.CsvDataAdapter;
import net.sf.jasperreports.data.csv.CsvDataAdapterImpl;
import net.sf.jasperreports.data.csv.CsvDataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.data.DataAdapterEditor;
import com.jaspersoft.studio.data.fields.IFieldsProvider;

public class CSVDataAdapterDescriptor extends DataAdapterDescriptor implements
		IFieldsProvider {
	private CsvDataAdapter csvDataAdapter = new CsvDataAdapterImpl();

	@Override
	public DataAdapter getDataAdapter() {
		return csvDataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		csvDataAdapter = (CsvDataAdapter) dataAdapter;
	}

	@Override
	public DataAdapterService getDataAdapterService() {
		CsvDataAdapterService csvDataAdapterService = new CsvDataAdapterService();
		csvDataAdapterService.setDataAdapter(csvDataAdapter);
		return csvDataAdapterService;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new CSVDataAdapterEditor();
	}

	private IFieldsProvider fprovider;

	@Override
	public List<JRDesignField> getFields(DataAdapterService con,
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		if (fprovider == null)
			fprovider = new CSVFieldsProvider();
		return fprovider.getFields(con, reportDataset);
	}
}
