package com.jaspersoft.studio.data.queryexecutor;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class QueryExecutorDataAdapterEditor implements DataAdapterEditor {
	
	QueryExecutorDataAdapterComposite composite = null;

	public void setDataAdapter(DataAdapter dataAdapter) {
		if (dataAdapter instanceof QueryExecutorDataAdapter) {
			composite.setDataAdapter((QueryExecutorDataAdapter) dataAdapter);
		}
	}

	public DataAdapter getDataAdapter() {
		return composite.getDataAdapter();
	}

	public Composite getComposite(Composite parent, int style) {
		if (composite == null) {
			composite = new QueryExecutorDataAdapterComposite(parent, style);
		}
		return composite;
	}

	public String getHelpContextId() {
		return composite.getHelpContextId();
	}
}
