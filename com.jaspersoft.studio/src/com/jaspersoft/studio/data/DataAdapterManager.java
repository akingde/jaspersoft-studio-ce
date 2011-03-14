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
package com.jaspersoft.studio.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The main plugin class to be used in the desktop.
 * 
 * @author Giulio Toffoli (gt78@users.sourceforge.net)
 */
public class DataAdapterManager {

	private static List<DataAdapterFactory> dataAdapterFactories = new ArrayList<DataAdapterFactory>();
	
	private static List<DataAdapter> dataAdapters = new ArrayList<DataAdapter>();
	
	/**
	 * Add a DataAdapterFactory to the list of DataAdapterFactories in JaspersoftStudio.
	 * The new type of data adapter will then be visible when a new data adapter is created.
	 * 
	 * @param factory
	 */
	public static void addDataAdapterFactory(DataAdapterFactory factory)
	{
		if (!dataAdapterFactories.contains(factory))
		{
			dataAdapterFactories.add(factory);
		}
		
	}
	
	public static void removeDataAdapterFactory(DataAdapterFactory factory)
	{
		
		
	}
	
	public static List<DataAdapterFactory> getDataAdapterFactories()
	{
		return dataAdapterFactories;
	}
	
	
	public static List<DataAdapter> loadDataAdapters()
	{
		// Load all the configured data adapters saved somewhere in JSS
		return Collections.emptyList();
	}
	
	public static void saveDataAdapter(DataAdapter da) throws Exception
	{
		// Save the data adapter configuration somewhere in JSS
	}
	
	
		
}
