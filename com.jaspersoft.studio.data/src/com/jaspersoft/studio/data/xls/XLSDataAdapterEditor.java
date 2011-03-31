package com.jaspersoft.studio.data.xls;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class XLSDataAdapterEditor implements DataAdapterEditor {

	XLSDataAdapterComposite composite = null;
	
	@Override
	public void setDataAdapter(DataAdapter dataAdapter) {
		if (dataAdapter instanceof XLSDataAdapter) {
			this.composite.setDataAdapter((XLSDataAdapter)dataAdapter);
		}
	}

	@Override
	public DataAdapter getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	@Override
	public Composite getComposite(Composite parent, int style) {
		if (composite == null) {
			composite = new XLSDataAdapterComposite(parent, style);
		}
		return composite;
	}

	@Override
	public String getHelpContextId() {
		return this.composite.getHelpContextId();
	}
}
