package com.jaspersoft.studio.data.empty;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class EmptyDataAdapterEditor implements DataAdapterEditor {
	
	EmptyDataAdapterComposite composite = null;

	public void setDataAdapter(DataAdapter dataAdapter) {
		if (dataAdapter instanceof EmptyDataAdapter)
		{
			this.composite.setDataAdapter((EmptyDataAdapter) dataAdapter);
		}
	}

	public DataAdapter getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	public Composite getComposite(Composite parent, int style) {
		if (composite == null)
		{
			composite = new EmptyDataAdapterComposite(parent, style);
		}
		return composite;
	}

	public String getHelpContextId() {
		return composite.getHelpContextId();
	}
}
