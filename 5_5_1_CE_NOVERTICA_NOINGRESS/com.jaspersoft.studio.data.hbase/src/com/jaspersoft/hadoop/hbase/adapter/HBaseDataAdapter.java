package com.jaspersoft.hadoop.hbase.adapter;

import net.sf.jasperreports.data.DataAdapter;

/**
 * 
 * @author Eric Diaz
 * 
 */
public interface HBaseDataAdapter extends DataAdapter {
	public String getZookeeperQuorum();

	public void setZookeeperQuorum(String zookeeperQuorum);

	public String getZookeeperClientPort();

	public void setZookeeperClientPort(String zookeeperClientPort);
}