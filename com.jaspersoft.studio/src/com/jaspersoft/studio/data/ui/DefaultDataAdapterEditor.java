package com.jaspersoft.studio.data.ui;

import org.eclipse.swt.widgets.Composite;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterEditor;

public class DefaultDataAdapterEditor implements DataAdapterEditor {
	
	DefaultDataAdapterEditorComposite composite = null;
	
	public void setDataAdapter(DataAdapter dataAdapter) {
		if (dataAdapter == null) { 
			// dataAdapter should never be null
		}
		this.composite.setDataAdapter(dataAdapter);
	}

	public DataAdapter getDataAdapter() {
		return this.composite.getDataAdapter();
	}

	public Composite getComposite(Composite parent, int style) {
		if (composite == null) {
			composite = new DefaultDataAdapterEditorComposite(parent, style);
		}
		return composite;
	}

	public String getHelpContextId() {
		return composite.getHelpContextId();
	}
}
