/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
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
			dataAdapterService = daf.createDataAdapterService(jasperReportsContext, dataAdapter);
		if (daf == null)
			return super.getDataAdapterService(jasperReportsContext, dataAdapter);
		return dataAdapterService;
	}

}
