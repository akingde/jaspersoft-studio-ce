/*
 * JasperReports - Free Java Reporting Library.
 * Copyright (C) 2001 - 2009 Jaspersoft Corporation. All rights reserved.
 * http://www.jaspersoft.com
 *
 * Unless you have purchased a commercial license agreement from Jaspersoft,
 * the following license terms apply:
 *
 * This program is part of JasperReports.
 *
 * JasperReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * JasperReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with JasperReports. If not, see <http://www.gnu.org/licenses/>.
 */
package com.jaspersoft.studio.data;

import net.sf.jasperreports.data.DataAdapter;
import net.sf.jasperreports.data.DataAdapterService;
import net.sf.jasperreports.data.DataAdapterServiceFactory;
import net.sf.jasperreports.data.bean.BeanDataAdapter;
import net.sf.jasperreports.data.bean.BeanDataAdapterService;
import net.sf.jasperreports.data.jdbc.JdbcDataAdapter;

import com.jaspersoft.studio.data.jdbc.JDBCDataAdapterService;


/**
 * @author Teodor Danciu (teodord@users.sourceforge.net)
 * @version $Id: JRBaseBand.java 4319 2011-05-17 09:22:14Z teodord $
 */
public class DataAdapterServiceFactoryImpl implements DataAdapterServiceFactory
{
    
	/**
	 *
	 */
	private static final DataAdapterServiceFactoryImpl INSTANCE = new DataAdapterServiceFactoryImpl();
	
	/**
	 *
	 */
	public static DataAdapterServiceFactoryImpl getInstance()
	{
		return INSTANCE;
	}
	
	/**
	 *
	 */
	public DataAdapterService getDataAdapterService(DataAdapter dataAdapter)
	{
		DataAdapterService dataAdapterService = null;
		
		if (dataAdapter instanceof BeanDataAdapter)
		{
			dataAdapterService = new BeanDataAdapterService((BeanDataAdapter)dataAdapter);
		}
		else if (dataAdapter instanceof JdbcDataAdapter)
		{
			dataAdapterService = new JDBCDataAdapterService((JdbcDataAdapter)dataAdapter);
		}
		
		return dataAdapterService;
	}
  
}
