/*******************************************************************************
 * Copyright (C) 2010 - 2016. TIBCO Software Inc. 
 * All Rights Reserved. Confidential & Proprietary.
 ******************************************************************************/
package com.jaspersoft.studio.data.widget;

import com.jaspersoft.studio.data.DataAdapterDescriptor;
import com.jaspersoft.studio.utils.jasper.JasperReportsConfiguration;

public interface IDataAdapterRunnable {

	public boolean isNotRunning();

	public boolean runReport(DataAdapterDescriptor myDataAdapter, boolean daAction);

	public boolean runReport(DataAdapterDescriptor myDataAdapter, boolean prmDirty, boolean daAction);

	/**
	 * Return the JasperReportsConfiguration of the loaded report
	 * 
	 * @return a JasperReportsConfiguration
	 */
	public JasperReportsConfiguration getConfiguration();
}
