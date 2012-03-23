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
package com.jaspersoft.studio.data.fields;

import java.util.List;

import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.engine.JRDataset;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.design.JRDesignField;

import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public interface IFieldsProvider {

	public boolean supportsGetFieldsOperation();

	/**
	 * Returns the fields that are available from a query of a specific language The provider can use the passed in report
	 * to extract some additional configuration information such as report properties. The IReportConnection object can be
	 * used to execute the query.
	 * 
	 * @param con
	 *          the DataAdapter
	 * @param the
	 *          JRDataset that will be filled using the data source created by this provider. The passed in report can be
	 *          null. That means that no compiled report is available yet.
	 * @param parameters
	 *          map containing the interpreted default value of each parameter
	 * @throws UnsupportedOperationException
	 *           is the method is not supported
	 * @throws JRException
	 *           if an error occurs.
	 */
	public List<JRDesignField> getFields(DataAdapterService con, JasperReportsConfiguration jConfig, JRDataset jDataset)
			throws JRException, UnsupportedOperationException;

}
