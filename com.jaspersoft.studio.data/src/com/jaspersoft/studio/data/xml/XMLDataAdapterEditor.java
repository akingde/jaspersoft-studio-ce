package com.jaspersoft.studio.data.xml;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class XMLDataAdapterEditor implements DataAdapterEditor {
	
	XMLDataAdapterComposite composite = null;

	public void setDataAdapter(DataAdapter dataAdapter) {
		if (dataAdapter instanceof XMLDataAdapter) {
			this.composite.setDataAdapter((XMLDataAdapter)dataAdapter);
		}
	}

	public DataAdapter getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	public Composite getComposite(Composite parent, int style) {
		if (this.composite == null) {
			composite = new XMLDataAdapterComposite(parent, style);
		}
		return this.composite;
	}

	public String getHelpContextId() {
		return this.composite.getHelpContextId();
	}
}
