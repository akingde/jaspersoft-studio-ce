package com.jaspersoft.studio.data.queryexecutor;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class QueryExecutorDataAdapterFactory implements DataAdapterFactory {

	public DataAdapter createDataAdapter() {
		return new QueryExecutorDataAdapter();
	}

	public String getDataAdapterClassName() {
		return QueryExecutorDataAdapter.class.getName();
	}

	public String getDescription() {
		return "Query Executor Data Adapter";
	}
}
