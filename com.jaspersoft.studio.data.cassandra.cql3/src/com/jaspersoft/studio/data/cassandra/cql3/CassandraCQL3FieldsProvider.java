package com.jaspersoft.studio.data.cassandra.cql3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.cassandra.cql3.connection.CassandraCQL3Connection;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

/**
 * 
 * @author Raul Gallegos
 * 
 */
public class CassandraCQL3FieldsProvider implements IFieldsProvider {
	public boolean supportsGetFieldsOperation() {
		return true;
	}

	public List<JRDesignField> getFields(DataAdapterService dataAdapterService, JasperReportsConfiguration jasperReportsConfiguration, JRDataset dataset) throws JRException,
			UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(JRParameter.REPORT_MAX_COUNT, 0);
		dataAdapterService.contributeParameters(parameters);
		ParameterUtil.setParameters(jasperReportsConfiguration, dataset, parameters);
		return com.jaspersoft.cassandra.cql3.CassandraCQL3FieldsProvider.getFields(dataset, parameters, (CassandraCQL3Connection) parameters.get(JRParameter.REPORT_CONNECTION));
	}

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}
}
