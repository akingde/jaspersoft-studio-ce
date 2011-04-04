/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2010 Jaspersoft Corporation. All
 * rights reserved. http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of Jaspersoft Open Studio.
 * 
 * Jaspersoft Open Studio is free software: you can redistribute it and/or modify it under the terms of the GNU Affero
 * General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version.
 * 
 * Jaspersoft Open Studio is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Affero General Public License
 * for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License along with Jaspersoft Open Studio. If not,
 * see <http://www.gnu.org/licenses/>.
 */
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
