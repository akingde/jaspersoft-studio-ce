package com.jaspersoft.studio.data.queryexecutor;

import java.util.Map;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;

import com.jaspersoft.studio.data.Activator;
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
	public ImageDescriptor getIcon16() {
		return AbstractUIPlugin.imageDescriptorFromPlugin(Activator.PLUGIN_ID, "icons/QueryExecutorDataAdapterIcon-16.gif");
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
