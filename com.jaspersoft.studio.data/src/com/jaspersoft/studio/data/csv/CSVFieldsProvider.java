/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.csv;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jaspersoft.studio.data.FieldTypeGuesser;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.csv.CsvDataAdapter;
import net.sf.jasperreports.eclipse.util.Misc;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.design.JRDesignDataset;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuter;
import net.sf.jasperreports.engine.query.JRCsvQueryExecuterFactory;

public class CSVFieldsProvider implements IFieldsProvider {

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig,
			JRDataset reportDataset) throws JRException, UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("REPORT_PARAMETERS_MAP", new HashMap<String, Object>());
		con.contributeParameters(parameters);
		ParameterUtil.setParameters(jConfig, reportDataset, parameters);
		parameters.put(JRParameter.REPORT_MAX_COUNT, FieldTypeGuesser.SAMPLESIZE);

		JRCsvDataSource ds = null;
		List<JRDesignField> columns = new ArrayList<>();

		CsvDataAdapter da = (CsvDataAdapter) ((AbstractDataAdapterService) con).getDataAdapter();
		if (!Misc.isNullOrEmpty(da.getColumnNames()))
			for (String key : da.getColumnNames())
				createColumn(columns, key);
		if (da.isQueryExecuterMode()) {
			if (reportDataset.getQuery() == null) {
				JRDesignQuery query = new JRDesignQuery();
				query.setLanguage("csv");
				((JRDesignDataset) reportDataset).setQuery(query);
			}
			JRCsvQueryExecuter qe = (JRCsvQueryExecuter) new JRCsvQueryExecuterFactory().createQueryExecuter(jConfig,
					reportDataset, ParameterUtil.convertMap(parameters, reportDataset));
			ds = (JRCsvDataSource) qe.createDatasource();
		} else
			ds = (JRCsvDataSource) parameters.get(JRParameter.REPORT_DATA_SOURCE);

		if (ds != null) {
			ds.setUseFirstRowAsHeader(true);
			boolean hasNext = ds.next();
			if (columns.isEmpty()) {
				Map<String, Integer> map = ds.getColumnNames();
				for (String key : map.keySet())
					createColumn(columns, key);
			}
			FieldTypeGuesser.guessTypes(ds, columns, hasNext);
		}
		return columns;
	}

	private void createColumn(List<JRDesignField> columns, String key) {
		JRDesignField field = new JRDesignField();
		field.setName(StringUtils.xmlEncode(key, null));
		field.setValueClass(String.class);
		columns.add(field);
	}

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

}
