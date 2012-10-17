package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.DataAdapter;

public class DefaultDataAdapterDescriptor extends DataAdapterDescriptor {
	private DataAdapter dataAdapter;

	@Override
	public DataAdapter getDataAdapter() {
		return dataAdapter;
	}

	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		this.dataAdapter = dataAdapter;
	}

}
