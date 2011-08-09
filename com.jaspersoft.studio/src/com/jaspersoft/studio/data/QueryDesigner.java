package com.jaspersoft.studio.data;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Text;

public class QueryDesigner implements IQueryDesigner {
	protected Text control;

	public QueryDesigner() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#getControl()
	 */
	public Control getControl() {
		return control;
	}

	public Control createControl(Composite parent) {
		control = new Text(parent, SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.H_SCROLL | SWT.BORDER);
		return control;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#setQuery(java.lang.String)
	 */
	public void setQuery(String query) {
		control.setText(query);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.jaspersoft.studio.data.IQueryDesigner#getQuery()
	 */
	public String getQuery() {
		return control.getText();
	}

	public void dispose() {

	}
}
