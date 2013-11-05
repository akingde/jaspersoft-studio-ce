package com.jaspersoft.hadoop.hbase.query;

import java.util.Map;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRValueParameter;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.query.QueryExecuterFactory;

/**
 * Query executer factory for Thrift queries. <br/>
 * This factory creates {@link ThriftQueryExecuter}
 * 
 * @author Eric Diaz
 * 
 */
public class ThriftQueryExecuterFactory implements QueryExecuterFactory {

	@Override
	public ThriftQueryExecuter createQueryExecuter(JasperReportsContext jasperReportsContext, JRDataset dataset, Map<String, ? extends JRValueParameter> parameters) throws JRException {
		return new ThriftQueryExecuter(jasperReportsContext, dataset, parameters);
	}

	@Override
	public ThriftQueryExecuter createQueryExecuter(JRDataset dataset, Map<String, ? extends JRValueParameter> parameters) throws JRException {
		return new ThriftQueryExecuter(DefaultJasperReportsContext.getInstance(), dataset, parameters);
	}

	/**
	 * Method not implemented
	 */
	public Object[] getBuiltinParameters() {
		return null;
	}

	/**
	 * Method not implemented
	 */
	public boolean supportsQueryParameterType(String queryParameterType) {
		return true;
	}

}