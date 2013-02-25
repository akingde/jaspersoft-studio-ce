/*******************************************************************************
 * Copyright (C) 2010 - 2013 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, 
 * the following license terms apply:
 * 
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Jaspersoft Studio Team - initial API and implementation
 ******************************************************************************/
package com.jaspersoft.studio.data.csv;

import java.util.ArrayList;
import java.util.HashMap;
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
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

public class CSVFieldsProvider implements IFieldsProvider {

	public List<JRDesignField> getFields(DataAdapterService con,
			JasperReportsConfiguration jConfig, JRDataset reportDataset)
			throws JRException, UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("REPORT_PARAMETERS_MAP", new HashMap<String, Object>());
		con.contributeParameters(parameters);
		ParameterUtil.setParameters(jConfig, reportDataset, parameters);
		parameters.put(JRParameter.REPORT_MAX_COUNT, 2);

		JRCsvDataSource ds = null;

		CsvDataAdapter da = (CsvDataAdapter) ((AbstractDataAdapterService) con)
				.getDataAdapter();
		if (da.isQueryExecuterMode()) {
			JRCsvQueryExecuter qe = (JRCsvQueryExecuter) new JRCsvQueryExecuterFactory()
					.createQueryExecuter(jConfig, reportDataset,
							ParameterUtil.convertMap(parameters));
			ds = (JRCsvDataSource) qe.createDatasource();
		} else {
			ds = (JRCsvDataSource) parameters
					.get(JRParameter.REPORT_DATA_SOURCE);
		}
		if (ds != null) {
			ds.setUseFirstRowAsHeader(true);
			ds.next();
			Map<String, Integer> map = ds.getColumnNames();
			List<JRDesignField> columns = new ArrayList<JRDesignField>(map
					.keySet().size());
			for (String key : map.keySet()) {
				JRDesignField field = new JRDesignField();
				field.setName(key);
				field.setValueClass(String.class);
				columns.add(field);
			}
			return columns;
		}
		return null;
	}

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

}
