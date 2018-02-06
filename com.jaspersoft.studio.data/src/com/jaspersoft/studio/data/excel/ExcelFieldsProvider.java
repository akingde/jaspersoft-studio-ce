/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.excel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.excel.ExcelDataAdapter;
import net.sf.jasperreports.eclipse.util.StringUtils;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.ExcelDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.ExcelQueryExecuter;
import net.sf.jasperreports.engine.query.ExcelQueryExecuterFactory;

import com.jaspersoft.studio.data.FieldTypeGuesser;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.property.dataset.dialog.DataQueryAdapters;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

public class ExcelFieldsProvider implements IFieldsProvider {

	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig,
			JRDataset reportDataset) throws JRException, UnsupportedOperationException {
		IProgressMonitor monitor = (IProgressMonitor) jConfig.getMap().get(DataQueryAdapters.PROGRESSMONITOR);
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("REPORT_PARAMETERS_MAP", new HashMap<String, Object>());
		con.contributeParameters(parameters);
		ParameterUtil.setParameters(jConfig, reportDataset, parameters);
		parameters.put(JRParameter.REPORT_MAX_COUNT, FieldTypeGuesser.SAMPLESIZE);
		
		if (monitor != null && monitor.isCanceled())
			return null;

		ExcelDataSource ds = null;

		ExcelDataAdapter da = (ExcelDataAdapter) ((AbstractDataAdapterService) con).getDataAdapter();
		if (da.isQueryExecuterMode()) {
			ExcelQueryExecuter qe = (ExcelQueryExecuter) new ExcelQueryExecuterFactory().createQueryExecuter(jConfig,
					reportDataset, ParameterUtil.convertMap(parameters, reportDataset));
			ds = (ExcelDataSource) qe.createDatasource();
		} else
			ds = (ExcelDataSource) parameters.get(JRParameter.REPORT_DATA_SOURCE);
		if (ds != null) {
			ds.setUseFirstRowAsHeader(da.isUseFirstRowAsHeader());
			boolean hasNext = ds.next();
			Map<String, Integer> map = ds.getColumnNames();
			List<JRDesignField> columns = new ArrayList<>(map.keySet().size());
			for (String key : map.keySet()) {
				JRDesignField field = new JRDesignField();
				field.setName(StringUtils.xmlEncode(key, null));
				field.setValueClass(String.class);
				columns.add(field);
			}
			FieldTypeGuesser.guessTypes(ds, columns, hasNext, monitor);
			return columns;
		}
		return null;
	}

	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

}
