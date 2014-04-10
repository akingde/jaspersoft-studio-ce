package com.jaspersoft.hadoop.hbase.query;

import java.util.Map;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;

/**
 * Query executer factory for HBase queries. <br/>
 * This factory creates {@link HBaseQueryExecuter}
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseQueryExecuterFactory implements QueryExecuterFactory {
	/**
	 * Creates an {@link HBaseQueryExecuter}.<br/>
	 * <br/>
	 * This method is called at fill time for reports/datasets having a query
	 * supported by this factory (HBaseQuery).
	 */
	public HBaseQueryExecuter createQueryExecuter(JasperReportsContext jasperReportsContext, JRDataset dataset, Map<String, ? extends JRValueParameter> parameters) throws JRException {
		return new HBaseQueryExecuter(jasperReportsContext, dataset, parameters);
	};

	@Override
	public HBaseQueryExecuter createQueryExecuter(JRDataset dataset, Map<String, ? extends JRValueParameter> parameters) throws JRException {
		return new HBaseQueryExecuter(DefaultJasperReportsContext.getInstance(), dataset, parameters);
	}

	/**
	 * Method not implemented
	 */
	@Override
	public Object[] getBuiltinParameters() {
		return null;
	}

	@Override
	public boolean supportsQueryParameterType(String queryParameterType) {
		return true;
	}
}