package com.jaspersoft.hadoop.hbase.adapter;



/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseDataAdapterImpl implements HBaseDataAdapter {
	private String zookeeperQuorum = "localhost";

	private String zookeeperClientPort = "2181";

	private String name = "HBaseDataAdapter";

	public String getZookeeperQuorum() {
		return zookeeperQuorum;
	}

	public void setZookeeperQuorum(String zookeeperQuorum) {
		this.zookeeperQuorum = zookeeperQuorum;
	}

	public String getZookeeperClientPort() {
		return zookeeperClientPort;
	}

	public void setZookeeperClientPort(String zookeeperClientPort) {
		this.zookeeperClientPort = zookeeperClientPort;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}
}