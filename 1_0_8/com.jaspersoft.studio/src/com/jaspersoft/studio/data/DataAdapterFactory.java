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
/*
 * Jaspersoft Open Studio - Eclipse-based JasperReports Designer. Copyright (C) 2005 - 2011 Jaspersoft Corporation. All
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

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;

import org.eclipse.swt.graphics.Image;

/*
 * 
 * @author gtoffoli
 */
public interface DataAdapterFactory {

	/**
	 * Creates a new instance of IReportConnection
	 * 
	 * @return an instance of IReportConnection
	 */
	public DataAdapterDescriptor createDataAdapter();

	/**
	 * This method returns the class name of the DataAdapter implementation. This is used from the code that must check if
	 * this connection factory is the good one to instance the connection serialized with a specific class name. Since due
	 * to the ClassLoading limitation JSS may not be able to instance the class by its self, it looks for the appropriate
	 * registered DataAdapterFactory
	 * 
	 * @return the class name of the DataAdapter implementation created by this factory class
	 */
	public String getDataAdapterClassName();

	public DataAdapterService createDataAdapterService(DataAdapter dataAdapter);

	/**
	 * This method provides the label of the data adapter type. I.e.: JDBC connection
	 */
	public String getLabel();

	/**
	 * This method provides a short description of the data adapter type. I.e.: connection to a database using JDBC
	 */
	public String getDescription();

	/**
	 * This method provides an icon for this data adapter. The icon size can be 32 or 48.
	 * 
	 */
	public Image getIcon(int size);
}
