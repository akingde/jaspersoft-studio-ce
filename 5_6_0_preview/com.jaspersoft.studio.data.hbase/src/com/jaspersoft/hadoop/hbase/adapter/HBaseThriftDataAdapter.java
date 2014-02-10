package com.jaspersoft.hadoop.hbase.adapter;

import net.sf.jasperreports.data.DataAdapter;

/**
 * 
 * @author Eric Diaz
 * 
 */
public interface HBaseThriftDataAdapter extends DataAdapter {
	public String getPort();

	public void setPort(String port);

	public String getHost();

	public void setHost(String host);
}