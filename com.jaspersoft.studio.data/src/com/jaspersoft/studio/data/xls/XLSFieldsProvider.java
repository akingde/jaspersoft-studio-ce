package com.jaspersoft.studio.data.xls;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.xls.XlsDataAdapter;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.data.JRXlsDataSource;
import net.sf.jasperreports.engine.design.JRDesignField;
import net.sf.jasperreports.engine.query.JRXlsQueryExecuter;
import net.sf.jasperreports.engine.query.JRXlsQueryExecuterFactory;

import com.jaspersoft.studio.data.fields.IFieldsProvider;
import com.jaspersoft.studio.utils.parameter.ParameterUtil;

public class XLSFieldsProvider implements IFieldsProvider {

	public List<JRDesignField> getFields(DataAdapterService con,
			JRDataset reportDataset) throws JRException,
			UnsupportedOperationException {
		Map<String, Object> parameters = con.getParameters();
		ParameterUtil.setParameters(reportDataset, parameters);
		parameters.put(JRParameter.REPORT_MAX_COUNT, 2);

		JRXlsDataSource ds = null;

		XlsDataAdapter da = (XlsDataAdapter) ((AbstractDataAdapterService) con)
				.getDataAdapter();
		if (da.isQueryExecuterMode()) {
			JRXlsQueryExecuter qe = (JRXlsQueryExecuter) new JRXlsQueryExecuterFactory()
					.createQueryExecuter(reportDataset,
							ParameterUtil.convertMap(parameters));
			ds = (JRXlsDataSource) qe.createDatasource();
		} else {
			ds = (JRXlsDataSource) parameters
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

}
