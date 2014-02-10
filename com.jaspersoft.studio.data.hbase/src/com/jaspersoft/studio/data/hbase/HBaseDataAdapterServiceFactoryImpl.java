package com.jaspersoft.studio.data.hbase;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceFactory;
import net.sf.jasperreports.engine.JasperReportsContext;

import com.jaspersoft.hadoop.hbase.adapter.HBaseDataAdapter;
import com.jaspersoft.hadoop.hbase.adapter.HBaseDataAdapterService;
import com.jaspersoft.hadoop.hbase.adapter.HBaseThriftDataAdapter;
import com.jaspersoft.hadoop.hbase.adapter.HBaseThriftDataAdapterService;

/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseDataAdapterServiceFactoryImpl implements DataAdapterServiceFactory {
	private static HBaseDataAdapterServiceFactoryImpl instance = null;

	public static HBaseDataAdapterServiceFactoryImpl getInstance() {
		if (instance == null)
			instance = new HBaseDataAdapterServiceFactoryImpl();
		return instance;
	}

	@Override
	public DataAdapterService getDataAdapterService(JasperReportsContext jasperReportsContext, DataAdapter dataAdapter) {
		DataAdapterService dataAdapterService = null;
		if (dataAdapter instanceof HBaseThriftDataAdapter)
			dataAdapterService = new HBaseThriftDataAdapterService(jasperReportsContext, (HBaseThriftDataAdapter) dataAdapter);
		else if (dataAdapter instanceof HBaseDataAdapter)
			dataAdapterService = new HBaseDataAdapterService(jasperReportsContext, (HBaseDataAdapter) dataAdapter);
		return dataAdapterService;
	}
}