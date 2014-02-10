package com.jaspersoft.studio.data.hbase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.hadoop.hbase.HBaseFieldsProvider;
import com.jaspersoft.hadoop.hbase.connection.HBaseConnection;
import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseDataAdapterFieldsProvider implements IFieldsProvider {

	@Override
	public boolean supportsGetFieldsOperation(JasperReportsConfiguration jConfig) {
		return true;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService dataAdapterService, JasperReportsConfiguration jConfig, JRDataset jDataset) throws JRException, UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(JRParameter.REPORT_MAX_COUNT, 0);
		dataAdapterService.contributeParameters(parameters);
		ParameterUtil.setParameters(jConfig, jDataset, parameters);

		return HBaseFieldsProvider.getFields(jConfig, jDataset, parameters, (HBaseConnection) parameters.get(JRParameter.REPORT_CONNECTION));
	}
}