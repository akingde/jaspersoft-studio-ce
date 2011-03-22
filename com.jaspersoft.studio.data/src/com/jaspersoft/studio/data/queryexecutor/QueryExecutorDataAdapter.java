package com.jaspersoft.studio.data.queryexecutor;

import java.util.Map;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class QueryExecutorDataAdapter extends DataAdapter {
	
	public QueryExecutorDataAdapter() {
		
	}

	@Override
	public boolean isJDBCConnection() {
		return false;
	}

	@Override
	public boolean isJRDataSource() {
		return false;
	}

	@Override
	public DataAdapterEditor getEditor() {
		return new QueryExecutorDataAdapterEditor();
	}

	@Override
	public Map<String, String> getProperties() {
		// does nothing
		return super.getProperties();
	}

	@Override
	public void loadProperties(Map<String, String> map) {
		// does nothing
	}
}
