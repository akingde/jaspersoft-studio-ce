package com.jaspersoft.studio.data.empty;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class EmptyDataAdapterFactory implements DataAdapterFactory {

	public DataAdapter createDataAdapter() {
		return new EmptyDataAdapter();
	}

	public String getDataAdapterClassName() {
		return EmptyDataAdapter.class.getName();
	}

	public String getDescription() {
		return "Empty Data Adapter";
	}
}
