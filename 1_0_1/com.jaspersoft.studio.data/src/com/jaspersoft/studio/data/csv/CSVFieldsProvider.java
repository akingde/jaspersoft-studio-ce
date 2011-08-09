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
package com.jaspersoft.studio.data.csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.csv.CsvDataAdapter;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuter;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

public class CSVFieldsProvider implements IFieldsProvider {

	public List<JRDesignField> getFields(DataAdapterService con,
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		Map<String, Object> parameters = con.getParameters();
		ParameterUtil.setParameters(reportDataset, parameters);
		parameters.put(JRParameter.REPORT_MAX_COUNT, 2);

		JRCsvDataSource ds = null;

		CsvDataAdapter da = (CsvDataAdapter) ((AbstractDataAdapterService) con)
				.getDataAdapter();
		if (da.isQueryExecuterMode()) {
			JRCsvQueryExecuter qe = (JRCsvQueryExecuter) new JRCsvQueryExecuterFactory()
					.createQueryExecuter(reportDataset,
							ParameterUtil.convertMap(parameters));
			ds = (JRCsvDataSource) qe.createDatasource();
		} else {
			ds = (JRCsvDataSource) parameters
					.get(JRParameter.REPORT_DATA_SOURCE);
		}
		if (ds != null) {
			ds.next();
			Map<String, Integer> map = ds.getColumnNames();
			List<JRDesignField> columns = new ArrayList<JRDesignField>(map
					.keySet().size());
			for (String key : map.keySet()) {
				JRDesignField field = new JRDesignField();
				field.setName(key);
				field.setValueClass(Object.class);
				columns.add(field);
			}
			return columns;
		}
		return null;
	}

	public boolean supportsGetFieldsOperation() {
		return true;
	}

}
