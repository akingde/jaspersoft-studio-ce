package com.jaspersoft.studio.data.hive;

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

import com.jaspersoft.hadoop.hive.connection.HiveConnection;
import com.jaspersoft.studio.data.fields.IFieldsProvider;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HiveFieldsProvider implements IFieldsProvider {
	public boolean supportsGetFieldsOperation() {
		return true;
	}

	@Override
	public List<JRDesignField> getFields(DataAdapterService dataAdapterService, JRDataset reportDataset)
			throws JRException, UnsupportedOperationException {
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(JRParameter.REPORT_MAX_COUNT, 0);
		dataAdapterService.contributeParameters(parameters);
		JRField[] fields = com.jaspersoft.hadoop.hive.HiveFieldsProvider.getInstance().getFields(
				(HiveConnection) parameters.get(JRParameter.REPORT_CONNECTION), reportDataset, parameters);
		List<JRDesignField> designFields = new ArrayList<JRDesignField>();
		for (int index = 0; index < fields.length; index++) {
			designFields.add((JRDesignField) fields[index]);
		}
		return designFields;
	}
}