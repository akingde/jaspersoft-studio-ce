/*
 * JasperReports - Free Java Reporting Library. Copyright (C) 2001 - 2011 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 * 
 * Unless you have purchased a commercial license agreement from Jaspersoft, the following license terms apply:
 * 
 * This program is part of JasperReports.
 * 
 * JasperReports is free software: you can redistribute it and/or modify it under the terms of the GNU Lesser General
 * Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any
 * later version.
 * 
 * JasperReports is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along with JasperReports. If not, see
 * <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DefaultDataAdapterServiceFactory;
import net.sf.jasperreports.engine.JasperReportsContext;

/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseBand.java 4319 2011-05-17 09:22:14Z teodord $
 */
public class DataAdapterServiceFactoryImpl extends DefaultDataAdapterServiceFactory {

	/**
	 *
	 */
	private static final DataAdapterServiceFactoryImpl INSTANCE = new DataAdapterServiceFactoryImpl();

	/**
	 *
	 */
	public static DataAdapterServiceFactoryImpl getInstance() {
		return INSTANCE;
	}

	/**
	 *
	 */
	public DataAdapterService getDataAdapterService(JasperReportsContext jasperReportsContext, DataAdapter dataAdapter) {
		DataAdapterService dataAdapterService = null;

		DataAdapterFactory daf = DataAdapterManager.findFactoryByDataAdapterClass(dataAdapter.getClass().getName());
		if (daf != null)
			dataAdapterService = daf.createDataAdapterService(dataAdapter);
		if (daf == null)
			return super.getDataAdapterService(jasperReportsContext, dataAdapter);
		return dataAdapterService;
	}

}
