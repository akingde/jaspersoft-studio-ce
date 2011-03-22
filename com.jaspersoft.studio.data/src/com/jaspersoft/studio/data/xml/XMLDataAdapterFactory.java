package com.jaspersoft.studio.data.xml;

import com.jaspersoft.studio.data.DataAdapter;
import com.jaspersoft.studio.data.DataAdapterFactory;

public class XMLDataAdapterFactory implements DataAdapterFactory {

	public DataAdapter createDataAdapter() {
		return new XMLDataAdapter();
	}

	public String getDataAdapterClassName() {
		return XMLDataAdapter.class.getName();
	}

	public String getDescription() {
		return "XML Data Adapter";
	}
}
