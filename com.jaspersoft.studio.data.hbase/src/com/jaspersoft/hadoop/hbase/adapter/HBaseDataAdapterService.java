package com.jaspersoft.hadoop.hbase.adapter;

import java.util.Map;

import net.sf.jasperreports.data.AbstractDataAdapterService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperReportsContext;

import com.jaspersoft.hadoop.hbase.connection.HBaseConnection;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseDataAdapterService extends AbstractDataAdapterService {
	private HBaseConnection connection;
	private HBaseDataAdapter dataAdapter;

	public HBaseDataAdapterService(JasperReportsContext jContext, HBaseDataAdapter dataAdapter) {
		super(jContext, dataAdapter);
		this.dataAdapter = dataAdapter;
	}

	@Override
	public void contributeParameters(Map<String, Object> parameters) throws JRException {
		if (dataAdapter != null) {
			ClassLoader cl = null;
			// Object obj =
			// getJasperReportsContext().getValue(AbstractClasspathAwareDataAdapterService.CURRENT_CLASS_LOADER);
			// if (obj != null && obj instanceof ClassLoader)
			// cl = (ClassLoader) obj;
			// else
				cl = Thread.currentThread().getContextClassLoader();
			connection = new HBaseConnection(dataAdapter.getZookeeperQuorum(), dataAdapter.getZookeeperClientPort(), cl);
			parameters.put(JRParameter.REPORT_CONNECTION, connection);
		}
	}

	@Override
	public void dispose() {
		if (connection != null)
			connection.close();
	}

	@Override
	public void test() throws JRException {
		System.out.println("Testing HBase service");
		if (connection != null) {
			try {
				System.out.println(connection.test());
			} catch (Exception e) {
				e.printStackTrace();
				throw new JRException(e.getMessage());
			}
		} else {
			System.out.println("No HBaseConnection defined");
		}
	}

}