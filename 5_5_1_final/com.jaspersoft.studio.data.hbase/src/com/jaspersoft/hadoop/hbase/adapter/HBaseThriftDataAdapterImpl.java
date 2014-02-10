package com.jaspersoft.hadoop.hbase.adapter;


/**
 * 
 * @author Eric Diaz
 * 
 */
public class HBaseThriftDataAdapterImpl implements HBaseThriftDataAdapter {
	private String host = "localhost";

	private String port = "9090";

	private String name = "HBaseThriftDataAdapter";

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
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