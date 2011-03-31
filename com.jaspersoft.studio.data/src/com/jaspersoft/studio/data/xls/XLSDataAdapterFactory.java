package com.jaspersoft.studio.data.xls;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class XLSDataAdapterFactory implements DataAdapterFactory {

	@Override
	public DataAdapter createDataAdapter() {
		return new XLSDataAdapter();
	}

	@Override
	public String getDataAdapterClassName() {
		return XLSDataAdapter.class.getName();
	}

	@Override
	public String getDescription() {
		return "Microsoft Excel(XLS) Data Adapter";
	}
}
