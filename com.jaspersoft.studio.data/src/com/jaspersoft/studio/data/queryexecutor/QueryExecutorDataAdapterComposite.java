package com.jaspersoft.studio.data.queryexecutor;

import org.eclipse.swt.widgets.Composite;

public class QueryExecutorDataAdapterComposite extends Composite {
	
	private QueryExecutorDataAdapter queryExecutorDataAdapter = null;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public QueryExecutorDataAdapterComposite(Composite parent, int style) {
		super(parent, style);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	/**
	 * Set the MyDataAdapter to edit.
	 * The UI will be updated with the content of this adapter
	 * @param dataAdapter
	 */
	public void setDataAdapter(QueryExecutorDataAdapter queryExecutorDataAdapter) {
		this.queryExecutorDataAdapter = queryExecutorDataAdapter;
	}

	public QueryExecutorDataAdapter getDataAdapter() {
		return queryExecutorDataAdapter;
	}

	public String getHelpContextId() {
		return "";
	}
}
